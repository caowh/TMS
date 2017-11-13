package tms.spring.controller;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import tms.spring.entity.AutoCaseHelper;
import tms.spring.service.AutoCaseRepertoryService;
import tms.spring.utils.CaseAnalyseUtil;
import tms.spring.utils.Constant;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/11/8.
 */
@Controller
@RequestMapping("/autoCaseRepertory")
public class autoCaseRepertoryController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AutoCaseRepertoryService autoCaseRepertoryService;

    @Autowired
    private CaseAnalyseUtil caseAnalyseUtil;

    @RequestMapping(value = "upload")
    public String upload(Model model,HttpServletRequest request) {
        String name=request.getParameter("name");
        String type=request.getParameter("type");
        String planName=request.getParameter("planName");
        String updateReason=request.getParameter("updateReason");
        String node=request.getParameter("node");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files=multipartRequest.getFiles("multipartFiles");
        try{
            autoCaseRepertoryService.upload(updateReason,name,type,planName,node,files);
            model.addAttribute("result", "上传用例成功");
        }catch (Exception e){
            model.addAttribute("result", "上传用例失败，失败原因："+e.getMessage());
        }
        model.addAttribute("username", SecurityUtils.getSubject().getPrincipal());
        model.addAttribute("planList", caseAnalyseUtil.getPlanList());
        return "autoCaseRepertory";
    }


    @RequestMapping(value = "searchAutoCase")
    @ResponseBody
    public Map<String, Object> searchAutoCase(@RequestBody Map<String,String> jsonMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("result",autoCaseRepertoryService.convertToAutoCaseHelper(autoCaseRepertoryService.searchAutoCase(jsonMap)));
            map.put("code", Constant.CODE_SUCCESS);
        } catch (Exception e){
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }


    @RequestMapping(value = "prepareExecute")
    public String prepareExecute(Model model, @RequestParam("ids") String ids) {
        model.addAttribute("username", SecurityUtils.getSubject().getPrincipal());
        model.addAttribute("ids", ids);
        model.addAttribute("planList", caseAnalyseUtil.getPlanList());
        int type=autoCaseRepertoryService.checkAutoCaseType(ids);
        if(type==2){
            model.addAttribute("planName", autoCaseRepertoryService.getPlanName(ids));
            return "autoCaseExecute";
        }else {
            model.addAttribute("result", "对不起，你所输入的用例类型错误或不一致，无法运行！");
            return "autoCaseRepertoryResult";
        }
    }


    @RequestMapping(value = "createGVMLExecutePlan")
    public String createGVMLExecutePlan(Model model,HttpServletRequest request) {
        String strategy=request.getParameter("strategy");
        String sendToTestlink=request.getParameter("sendToTestlink");
        String before=request.getParameter("before");
        String statement=request.getParameter("statement");
        String planName=request.getParameter("planName");
        String ids=request.getParameter("ids");
        String key=autoCaseRepertoryService.saveGVMLExecutePlan(strategy,sendToTestlink,before,statement,planName,ids);
        model.addAttribute("username", SecurityUtils.getSubject().getPrincipal());
        model.addAttribute("result", "你的执行秘钥为：“"+key+"”，请在GVML专用浏览器访问：http://ip:port/autoCaseRepertory/executeGVMLCase.jsp?key="+key+"进行测试，" +
                "如果浏览器正确，请直接点击！<a href=\"/executeGVMLCase.jsp?key="+key+"\" class=\"btn btn-primary\">执行测试</a>");
        return "autoCaseRepertoryResult";
    }


    @RequestMapping(value = "execute")
    public String execute(Model model) {
        model.addAttribute("username", SecurityUtils.getSubject().getPrincipal());
        return "execute";
    }


    @RequestMapping(value = "executeTestPlan")
    @ResponseBody
    public Map<String, Object> executeTestPlan(@RequestParam("key") String key) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("result",autoCaseRepertoryService.createGVMLTestScript(key));
            map.put("code", Constant.CODE_SUCCESS);
        } catch (Exception e){
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }


    @RequestMapping(value = "lookAutoCase")
    public String lookAutoCase(Model model,@RequestParam("id") String id) {
        model.addAttribute("username", SecurityUtils.getSubject().getPrincipal());
        AutoCaseHelper autoCaseHelper=null;
        try {
            autoCaseHelper=autoCaseRepertoryService.searchAutoCaseById(id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("用例不存在，id:"+id);
        }
        model.addAttribute("autoCaseHelper", autoCaseHelper);
        return "lookAutoCase";
    }


    @RequestMapping(value = "updateAutoCase")
    public String updateAutoCase(Model model,HttpServletRequest request) {
        String id=request.getParameter("id");
        String caseId=request.getParameter("caseId");
        String describe=request.getParameter("describe");
        String content=request.getParameter("content");
        String updateReason=request.getParameter("updateReason");
        AutoCaseHelper autoCaseHelper=null;
        try {
            autoCaseRepertoryService.updateGVMLAutoCase(caseId,describe,content,updateReason,id);
            autoCaseHelper=autoCaseRepertoryService.searchAutoCaseById(id);
            model.addAttribute("result", "用例更新成功！");
        } catch (Exception e) {
            model.addAttribute("result", "用例更新失败，失败原因："+e.getMessage());
        }
        model.addAttribute("autoCaseHelper", autoCaseHelper);
        return "lookAutoCase";
    }


}
