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
import tms.spring.exception.MailException;
import tms.spring.exception.RegisterException;
import tms.spring.service.LoginService;
import tms.spring.utils.Constant;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by user on 2017/7/14.
 */
@Controller
@RequestMapping("/before")
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
            map.put("code", Constant.CODE_SUCCESS);
        } catch (IncorrectCredentialsException e) {
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        } catch (UnknownAccountException e) {
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        } catch (UnauthorizedException e) {
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "checkUserName")
    @ResponseBody
    public Map<String, Object> checkUserName(@Param("username") String username){
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("begin checkUserName!");
        try {
            loginService.checkUserName(username);
            map.put("code",1);
        }catch (Exception e){
            logger.error("checkUserName error,"+e.getMessage());
            map.put("code",0);   //预料之外的错误
            map.put("message",e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "checkEmail")
    @ResponseBody
    public Map<String, Object> checkEmail(@Param("email") String email){
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("begin checkEmail!");
        try {
            loginService.checkEmail(email);
            map.put("code",Constant.CODE_SUCCESS);
        }catch (Exception e){
            logger.error("checkEmail error,"+e.getMessage());
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "sendEmailToGetValidateCode")
    @ResponseBody
    public Map<String, Object> sendEmailToGetValidateCode(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("begin sendEmailToGetValidateCode!");
        try {
            loginService.sendEmailToGetValidateCode(request);
            map.put("code",Constant.CODE_SUCCESS);
        }catch (MailException e){
            logger.error("sendEmailToGetValidateCode error,"+e.getMessage());
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "register")
    @ResponseBody
    public Map<String, Object> register(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("begin sendEmailToGetValidateCode!");
        try {
            loginService.register(request);
            map.put("code",Constant.CODE_SUCCESS);
        }catch (RegisterException e){
            logger.error("sendEmailToGetValidateCode error,"+e.getMessage());
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }catch (Exception e){
            logger.error("sendEmailToGetValidateCode error,"+e.getMessage());
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "updatePasswordByEmail")
    @ResponseBody
    public Map<String, Object> updatePasswordByEmail(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("begin forgetPassword!");
        try {
            loginService.updatePasswordByEmail(request);
            map.put("code",Constant.CODE_SUCCESS);
        }catch (Exception e){
            logger.error("updatePasswordByEmail error,"+e.getMessage());
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }

}
