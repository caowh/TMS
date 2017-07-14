package tms.spring.controller;


import com.alibaba.fastjson.JSON;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by user on 2017/7/14.
 */
@Controller
@RequestMapping("/")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model) {
        logger.info("open index page!");
        return "index";
    }


    @RequestMapping(value = "loginTest", method = RequestMethod.GET)
    public String loginTest(Model model) {
        logger.info("open index loginTest page!");
        return "/view/index1";
    }

    @RequestMapping(value = "doLogin", method = RequestMethod.POST,produces = {
            "application/json; charset=utf-8" })
    public String doLogin(@Param("username") String username,@Param("password") String password) {
        logger.info("open index page!");
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                map.put("code","1");
                map.put("message","登录认证成功");
            } else {
                map.put("code","0");
                map.put("message","登录认证失败");
            }
        } catch (IncorrectCredentialsException e) {
            map.put("code","0");
            map.put("message","登录密码错误");
        } catch (UnknownAccountException e) {
            map.put("code","0");
            map.put("message","登录账号不存在");
        } catch (UnauthorizedException e) {
            map.put("code","0");
            map.put("message","未获取登录权限");
        }
        return JSON.toJSONString(map);
    }
}
