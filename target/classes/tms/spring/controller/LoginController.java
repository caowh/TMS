package tms.spring.controller;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tms.spring.service.LoginService;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by user on 2017/7/14.
 */
@Controller
@RequestMapping("/beforeLogin")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "login")
    @ResponseBody
    public Map<String, Object> login(@Param("username") String username,@Param("password") String password,@Param("remember") Boolean remember) {
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("begin login!");
        try {
            loginService.login(username,password,remember);
            map.put("code","1");
            map.put("message","");
        } catch (IncorrectCredentialsException e) {
            map.put("code","0");
            map.put("message",e.getMessage());
        } catch (UnknownAccountException e) {
            map.put("code","0");
            map.put("message",e.getMessage());
        } catch (UnauthorizedException e) {
            map.put("code","0");
            map.put("message",e.getMessage());
        }
        return map;
    }
}
