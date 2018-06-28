package tms.spring.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tms.spring.dao.RoleDao;
import tms.spring.dao.UserDao;
import tms.spring.entity.Role;
import tms.spring.entity.User;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by user on 2017/7/14.
 */
public class ShiroRealm  extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录时输入的用户名
        String loginName=(String) principalCollection.fromRealm(getName()).iterator().next();
        //到数据库查是否有此对象
        User user=userDao.selectUserByName(loginName);
        if(user!=null){
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            //用户的角色集合
            Set<Role> roles=userDao.selectRolesById(user.getId());
            Set<String> roleNames=new HashSet<String>();
            Set<String> permissionUrls=new HashSet<String>();
            for (Role role: roles) {
                if(role!=null){
                    roleNames.add(role.getName());
                    Set<String> rolePermissionUrls=roleDao.selectPermissionUrlsById(role.getId());
                    for (String rolePermissionUrl:rolePermissionUrls) {
                        if (null!=rolePermissionUrl&&!permissionUrls.contains(rolePermissionUrl)){
                            permissionUrls.add(rolePermissionUrl);
                        }
                    }
                }
            }
            info.setRoles(roleNames);
            info.setStringPermissions(permissionUrls);
            return info;
        }
        return null;
    }

    /**
     * 登录认证;
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        //查出是否有此用户
        User user=userDao.selectUserByName(token.getUsername());
        if(user==null){
            throw new UnknownAccountException("用户名不存在");
        }else {
            if (!user.getPassword().equals(String.valueOf(token.getPassword()))){
                throw new IncorrectCredentialsException("密码错误");
            }else {
                if (user.getStatus().equals(User._0)){
                    throw new DisabledAccountException("你已经被禁止登陆");
                }else {
                    user.setLastLoginTime(new Date());
                    userDao.updateLastLoginTime(user);
                    return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
                }
            }
        }
    }

    /**
     * 清空当前用户权限信息
     */
    public  void clearCachedAuthorizationInfo() {
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
    /**
     * 指定principalCollection 清除
     */
    public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
}
