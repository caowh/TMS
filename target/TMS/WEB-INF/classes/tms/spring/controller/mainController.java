package tms.spring.controller;


import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tms.spring.service.LoginService;
import tms.spring.utils.Constant;

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
    public String index(Model model) {
        model.addAttribute("username",SecurityUtils.getSubject().getPrincipal());
        return "index";
    }

    @RequestMapping(value="logout")
    @ResponseBody
    public Map<String, Object> logout(){
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("begin logout!");
        try {
            loginService.logout();
            map.put("code", Constant.CODE_SUCCESS);
        }catch (Exception e){
            logger.error("logout errorMessage:" + e.getMessage());
            map.put("code", Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }
}
