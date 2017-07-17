package tms.spring.controller;


import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
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
@RequestMapping("/main")
public class mainController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }

    @RequestMapping(value="logout")
    @ResponseBody
    public Map<String, Object> logout(){
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("begin logout!");
        try {
            loginService.logout();
            map.put("code", 1);
        }catch (Exception e){
            logger.error("logout errorMessage:" + e.getMessage());
            map.put("code", 0);
            map.put("message",e.getMessage());
        }
        return map;
    }
}
