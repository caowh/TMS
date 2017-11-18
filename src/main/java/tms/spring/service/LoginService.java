package tms.spring.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.WebUtils;
import tms.spring.dao.UserDao;
import tms.spring.entity.User;
import tms.spring.exception.MailException;
import tms.spring.exception.RegisterException;
import tms.spring.shiro.ShiroRealm;
import tms.spring.shiro.filter.ShiroFilterUtils;
import tms.spring.utils.MailCilent;
import tms.spring.utils.VerifyCodeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/15 0015.
 */
@Service
public class LoginService {

    @Autowired
    private ShiroRealm shiroRealm;
    @Autowired
    private UserDao userDao;
    @Autowired
    private MailCilent mailCilent;

    public void login(HttpServletRequest request,Map<String,String> jsonMap) throws Exception {
        String username=jsonMap.get("username");
        String password=jsonMap.get("password");
        String realValiteCode=jsonMap.get("valiteCode");
        String rememberStr=jsonMap.get("remember");
        if(realValiteCode==null||username==null||password==null){
            throw new Exception("填写的登录信息不完善");
        }
        Boolean remember=true;
        if (rememberStr==null){
            remember=false;
        }
        String validateCode= (String) WebUtils.getSessionAttribute(request,VerifyCodeUtils.V_CODE);
        if(null==validateCode){
            throw new Exception("验证码失效");
        }
        if(!validateCode.equals(realValiteCode.toLowerCase())){
            throw new Exception("验证码错误");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, ShiroFilterUtils.encryptPassword(password));
        Subject subject = SecurityUtils.getSubject();
        subject.getSession();
        token.setRememberMe(remember);
        subject.login(token);
    }

    public void logout(){
        shiroRealm.clearCachedAuthorizationInfo();
        SecurityUtils.getSubject().logout();
    }

    public void register(HttpServletRequest request,Map<String,String> jsonMap) throws RegisterException {
        String realValiteCode=jsonMap.get("valiteCode");
        String username=jsonMap.get("username");
        String password=jsonMap.get("password");
        String nickname=jsonMap.get("nickname");
        String department=jsonMap.get("department");
        String email=jsonMap.get("email");
        if(realValiteCode==null||username==null||password==null||email==null){
            throw new RegisterException("填写的注册信息不完善");
        }
        String validateCode=String.valueOf(WebUtils.getSessionAttribute(request,email));
        if(null==validateCode){
            throw new RegisterException("验证码失效或与邮箱不匹配");
        }
        if(!validateCode.equals(realValiteCode)){
            throw new RegisterException("验证码错误");
        }
        synchronized (this){
            User userByName=userDao.selectUserByName(username);
            User userByEmail=userDao.selectUserByEmail(email);
            if(null!=userByName){
                throw new RegisterException("用户名已存在");
            }
            if(null!=userByEmail){
                throw new RegisterException("邮箱已存在");
            }
            User user=new User();
            user.setUsername(username);
            user.setPassword(ShiroFilterUtils.encryptPassword(password));
            user.setCreateTime(new Date());
            user.setDepartment(department);
            user.setEmail(email);
            user.setNickname(nickname);
            user.setStatus(User._1);
            userDao.insertUser(user);
        }
    }

    public void checkUserName(String username) throws Exception {
        User user=userDao.selectUserByName(username);
        if(null!=user){
            throw new Exception("用户名已存在");
        }
    }

    public void checkEmail(String email) throws Exception {
        User user=userDao.selectUserByEmail(email);
        if(null!=user){
            throw new Exception("邮箱已被注册");
        }
    }

    public void registerToGetValidateCode(HttpServletRequest request, String email) throws MailException {
        if (email==null){
            throw new MailException("你输入的邮件地址为空");
        }
        User user=userDao.selectUserByEmail(email);
        if(null!=user){
            throw new MailException("邮箱已被注册");
        }
        String content="你正在注册账号！";
        String validateCode=mailCilent.sendHtmlMail(email,content);
        WebUtils.setSessionAttribute(request, email, validateCode);
    }

    public void updatePwdToGetValidateCode(HttpServletRequest request,String email) throws MailException {
        if (email==null){
            throw new MailException("你输入的邮件地址为空");
        }
        User user=userDao.selectUserByEmail(email);
        if(null==user){
            throw new MailException("该邮箱未被注册");
        }
        String content="你正在找回密码！";
        String validateCode=mailCilent.sendHtmlMail(email,content);
        WebUtils.setSessionAttribute(request, email, validateCode);
    }

    public void updatePasswordByEmail(HttpServletRequest request,@RequestBody Map<String,String> jsonMap) throws Exception {
        String realValiteCode=jsonMap.get("valiteCode");
        String password=jsonMap.get("password");
        String email=jsonMap.get("email");
        if(realValiteCode==null||password==null||email==null){
            throw new Exception("填写的信息不完善");
        }
        String validateCode=String.valueOf(WebUtils.getSessionAttribute(request,email));
        if(null==validateCode||validateCode.equals("null")){
            throw new RegisterException("验证码无效");
        }
        if(!validateCode.equals(realValiteCode)){
            throw new Exception("验证码错误");
        }
        User user=new User();
        user.setEmail(email);
        user.setPassword(ShiroFilterUtils.encryptPassword(password));
        userDao.updatePasswordByEmail(user);
    }

    @Transactional
    public void updatePwd(Map<String, String> jsonMap) throws Exception {
        String old=jsonMap.get("old");
        String newPwd=jsonMap.get("new");
        if(old==null||newPwd==null){
            throw new Exception("输入的密码为空！");
        }
        User user=userDao.selectUserByName(SecurityUtils.getSubject().getPrincipal().toString());
        if(!user.getPassword().equals(ShiroFilterUtils.encryptPassword(old))){
            throw new Exception("输入的旧密码错误！");
        }
        user.setPassword(ShiroFilterUtils.encryptPassword(newPwd));
        userDao.updatePasswordByEmail(user);
    }
}
