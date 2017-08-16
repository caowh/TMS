package tms.spring.shiro;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tms.spring.dao.BaseDaoTest;
import tms.spring.dao.RoleDao;
import tms.spring.dao.UserDao;
import tms.spring.entity.Role;
import tms.spring.entity.User;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by user on 2017/8/16.
 */
public class ShiroRealmTest extends BaseDaoTest{

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Test
    public void doGetAuthorizationInfo() throws Exception {
        User user=userDao.selectUserByName("caowh");
//        if(user!=null){
//            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
//            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
//            //用户的角色集合
//            Set<Role> roles=userDao.selectRolesById(user.getId());
//            Set<String> roleNames=new HashSet<String>();
//            Set<String> permissionUrls=new HashSet<String>();
//            for (Role role: roles) {
//                roleNames.add(role.getName());
//                Set<String> rolePermissionUrls=roleDao.selectPermissionUrlsById(role.getId());
//                for (String rolePermissionUrl:rolePermissionUrls) {
//                    if (null!=rolePermissionUrl&&!permissionUrls.contains(rolePermissionUrl)){
//                        permissionUrls.add(rolePermissionUrl);
//                    }
//                }
//            }
//            info.setRoles(roleNames);
//            info.setStringPermissions(permissionUrls);
//    }

}
}