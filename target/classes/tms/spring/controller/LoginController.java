package tms.spring.controller;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;
import tms.spring.entity.User;
import tms.spring.exception.MailException;
import tms.spring.exception.RegisterException;
import tms.spring.service.LoginService;
import tms.spring.utils.Constant;
import tms.spring.utils.VerifyCodeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
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
            logger.error("login error,"+e.getMessage());
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
            map.put("code",Constant.CODE_SUCCESS);
        }catch (Exception e){
            logger.error("checkUserName error,"+e.getMessage());
            map.put("code",Constant.CODE_FAILED);   //预料之外的错误
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

    @RequestMapping(value = "registerToGetValidateCode")
    @ResponseBody
    public Map<String, Object> registerToGetValidateCode(HttpServletRequest request, @RequestBody Map<String,String> jsonMap){
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("begin registerToGetValidateCode!");
        try {
            loginService.registerToGetValidateCode(request,jsonMap.get("email"));
            map.put("code",Constant.CODE_SUCCESS);
        }catch (MailException e){
            logger.error("registerToGetValidateCode error,"+e.getMessage());
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }


    @RequestMapping(value = "updatePwdToGetValidateCode")
    @ResponseBody
    public Map<String, Object> updatePwdToGetValidateCode(HttpServletRequest request, @RequestBody Map<String,String> jsonMap){
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("begin updatePwdToGetValidateCode!");
        try {
            loginService.updatePwdToGetValidateCode(request,jsonMap.get("email"));
            map.put("code",Constant.CODE_SUCCESS);
        }catch (MailException e){
            logger.error("updatePwdToGetValidateCode error,"+e.getMessage());
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }


    @RequestMapping(value = "register")
    @ResponseBody
    public Map<String, Object> register(HttpServletRequest request,@RequestBody Map<String,String> jsonMap){
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("begin registerToGetValidateCode!");
        try {
            loginService.register(request,jsonMap);
            map.put("code",Constant.CODE_SUCCESS);
        }catch (RegisterException e){
            logger.error("registerToGetValidateCode error,"+e.getMessage());
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }catch (Exception e){
            logger.error("registerToGetValidateCode error,"+e.getMessage());
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "updatePasswordByEmail",produces="text/html;charset=UTF-8")
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

    @RequestMapping(value = "isLogin")
    @ResponseBody
    public Map<String, Object> isLogin(){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Object userName=SecurityUtils.getSubject().getPrincipal();
            if(userName!=null){
                map.put("result",true);
            }else {
                map.put("result",false);
            }
            map.put("code",Constant.CODE_SUCCESS);
        }catch (Exception e){
            logger.error("判断是否登录发生异常：%s",e.getMessage());
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }

    /**
     * 查看个人图片
     */
    @RequestMapping(value="getUserPicture")
    public void getUserPicture(@RequestParam("name") String name, HttpServletResponse response) {
        byte[] bytes = loginService.getUserPicture(name);
        if(bytes!=null&&bytes.length>0){
            OutputStream out;
            try {
                out = response.getOutputStream();
                out.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpg");
    }


    /**
     * 查看个人资料
     */
    @RequestMapping(value="getUserProfile")
    @ResponseBody
    public void getUserProfile(@RequestParam("name") String name, HttpServletResponse response) {
        OutputStream out;
        User user=loginService.getUserProfile(name);
        String res="success_jsonpCallback("+JSON.toJSONString(user)+")";
        try {
            out = response.getOutputStream();
            out.write(res.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setHeader("content-type","text/html;charset=gb2312");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "No-cache");
        response.setDateHeader("Expires", 0);
    }

}
