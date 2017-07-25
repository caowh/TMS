package tms.spring.controller;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;
import tms.spring.exception.MailException;
import tms.spring.exception.RegisterException;
import tms.spring.service.LoginService;
import tms.spring.utils.Constant;
import tms.spring.utils.VerifyCodeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public Map<String, Object> login(HttpServletRequest request,@RequestBody Map<String,String> jsonMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("begin login!");
        try {
            loginService.login(request,jsonMap);
            map.put("code", Constant.CODE_SUCCESS);
        } catch (Exception e){
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "checkUserName")
    @ResponseBody
    public Map<String, Object> checkUserName(@RequestBody Map<String,String> jsonMap){
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("begin checkUserName!");
        try {
            loginService.checkUserName(jsonMap.get("username"));
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
    public Map<String, Object> checkEmail(@RequestBody Map<String,String> jsonMap){
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("begin checkEmail!");
        try {
            loginService.checkEmail(jsonMap.get("email"));
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
    public Map<String, Object> sendEmailToGetValidateCode(HttpServletRequest request,@RequestBody Map<String,String> jsonMap){
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("begin sendEmailToGetValidateCode!");
        try {
            loginService.sendEmailToGetValidateCode(request,jsonMap.get("username"));
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
    public Map<String, Object> register(HttpServletRequest request,@RequestBody Map<String,String> jsonMap){
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("begin sendEmailToGetValidateCode!");
        try {
            loginService.register(request,jsonMap);
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
    public Map<String, Object> updatePasswordByEmail(HttpServletRequest request,@RequestBody Map<String,String> jsonMap){
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("begin forgetPassword!");
        try {
            loginService.updatePasswordByEmail(request,jsonMap);
            map.put("code",Constant.CODE_SUCCESS);
        }catch (Exception e){
            logger.error("updatePasswordByEmail error,"+e.getMessage());
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "getLoginValidateJpg")
    public void getLoginValidateJpg(HttpServletRequest request,HttpServletResponse response){
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpg");
            String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
            WebUtils.setSessionAttribute(request,VerifyCodeUtils.V_CODE, verifyCode.toLowerCase());
            //生成图片
            int w = 146, h = 33;
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (Exception e) {
            logger.error("获取验证码异常：%s",e.getMessage());
        }
    }


}
