package tms.spring.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/15 0015.
 */
@Service
public class LoginService {

    public void login(String username,String password,Boolean remember){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        token.setRememberMe(remember);
        subject.login(token);
    }
}
