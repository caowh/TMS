package tms.spring.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;
import tms.spring.dao.UserDao;
import tms.spring.entity.User;
import tms.spring.exception.MailException;
import tms.spring.exception.RegisterException;
import tms.spring.shiro.ShiroRealm;
import tms.spring.shiro.filter.ShiroFilterUtils;
import tms.spring.utils.MailCilent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

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

    public void login(String username,String password,Boolean remember){
        UsernamePasswordToken token = new UsernamePasswordToken(username, ShiroFilterUtils.encryptPassword(password));
        Subject subject = SecurityUtils.getSubject();
        token.setRememberMe(remember);
        subject.login(token);
    }

    public void logout(){
        shiroRealm.clearCachedAuthorizationInfo();
        SecurityUtils.getSubject().logout();
    }

    public void register(HttpServletRequest request) throws RegisterException {
        String realValiteCode=request.getParameter("valiteCode");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String nickname=request.getParameter("nickname");
        String department=request.getParameter("department");
        String email=request.getParameter("email");
        if(realValiteCode==null||username==null||password==null||email==null){
            throw new RegisterException("填写的注册信息不完善");
        }
        HttpSession session=request.getSession(false);
        if (null==session){
            throw new RegisterException("验证码失效");
        }
        String validateCode=String.valueOf(session.getAttribute(email));
        if(null==validateCode){
            throw new RegisterException("验证码与邮箱不匹配");
        }
        if(!validateCode.equals(realValiteCode)){
            throw new RegisterException("验证码错误");
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

    public void checkUserName(String username) throws Exception {
        User user=userDao.selectUserByName(username);
        if(null!=user){
            throw new Exception("查找用户名失败");
        }
    }

    public void checkEmail(String email) throws Exception {
        User user=userDao.selectUserByEmail(email);
        if(null!=user){
            throw new Exception("查找邮箱失败");
        }
    }

    public void sendEmailToGetValidateCode(HttpServletRequest request) throws MailException {
        String email=request.getParameter("email");
        if (email==null){
            throw new MailException("你输入的邮件地址为空");
        }
        String validateCode=mailCilent.sendHtmlMail(email);
        HttpSession session=request.getSession();
        //设置有效时间为60s
        session.setMaxInactiveInterval(60);
        session.setAttribute(email,validateCode);
    }
}
