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
import tms.spring.entity.AutoCase;
import tms.spring.entity.AutoCaseHelper;
import tms.spring.entity.TreeNode;
import tms.spring.exception.AutoCaseRepertoryException;
import tms.spring.exception.CaseAnalysesException;
import tms.spring.service.AutoCaseRepertoryService;
import tms.spring.utils.CaseAnalyseUtil;
import tms.spring.utils.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
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
        try{
            autoCaseRepertoryService.upload(request);
            model.addAttribute("result", "上传用例成功");
        }catch (Exception e){
            model.addAttribute("result", "上传用例失败，失败原因："+e.getMessage());
        }
        String username=SecurityUtils.getSubject().getPrincipal().toString();
        if(username.equals("admin")){
            model.addAttribute("writer", "<input type=\"text\" name=\"name\" class=\"form-control required\" maxlength=\"10\">");
        }else {
            model.addAttribute("writer", "<input type=\"text\" name=\"name\" class=\"form-control required\" value=\""+username+"\" readonly=\"readonly\">");
        }
        model.addAttribute("username", username);
        return "autoCaseRepertory";
    }


    @RequestMapping(value = "searchAutoCase")
    @ResponseBody
    public Map<String, Object> searchAutoCase(@RequestParam("node") String node) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<TreeNode> treeNodes=caseAnalyseUtil.getProjectTree();
            List<AutoCase> autoCases=autoCaseRepertoryService.searchAutoCase(node,treeNodes);
            map.put("result",autoCaseRepertoryService.convertToAutoCaseHelper(autoCases,treeNodes));
            map.put("code", Constant.CODE_SUCCESS);
        } catch (Exception e){
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }


    @RequestMapping(value = "deleteAutoCase")
    @ResponseBody
    public Map<String, Object> deleteAutoCase(@RequestParam("ids") String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            autoCaseRepertoryService.deleteAutoCase(ids);
            map.put("code", Constant.CODE_SUCCESS);
        } catch (Exception e){
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "moveAutoCase")
    @ResponseBody
    public Map<String, Object> moveAutoCase(@RequestParam("ids") String ids,@RequestParam("nodeName") String nodeName) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            autoCaseRepertoryService.moveAutoCase(ids,nodeName);
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
            model.addAttribute("before", "window.earth = new GV.GeoCanvas(\"#container\", {map : {supported_mode: '23D', current_mode: '3D'} })\n" +
                    "var username = 'dongb';\n" +
                    "var password = 'dongbo7690600';\n" +
                    "earth.plugincenter = `${GV.Center.PluginCenter.getPluginCenter}?name=${username}&pwd=${password}`;\n" +
                    "earth.onReady(function() {\n" +
                    "    earth.usingWidget('coordsinfo');\n" +
                    "    var source = new GV.TileMapServiceSource({\n" +
                    "        url: 'http://127.0.0.1:3000/docResource/tms/tilemapresource.xml'\n" +
                    "    });\n" +
                    "    earth.addImageLayer({\n" +
                    "        name: 'quanqiu\\ngrst',\n" +
                    "        source: source\n" +
                    "    });\n" +
                    "    window.scene = new GV.GraphicScene();\n" +
                    "    earth.addScene(scene);\n" +
                    "})");
            return "autoCaseExecute";
        }else if(type==1){
            model.addAttribute("waitTime","5000");
            return "autoCaseExecutePM";
        }else {
            model.addAttribute("result", "对不起，你所输入的用例类型错误或不一致，无法运行！");
            return "autoCaseRepertoryResult";
        }
    }


    @RequestMapping(value = "createPMExecutePlan")
    public String createPMExecutePlan(Model model,HttpServletRequest request) {
        String key=autoCaseRepertoryService.savePMExecutePlan(request);
        model.addAttribute("username", SecurityUtils.getSubject().getPrincipal());
        model.addAttribute("result", "你的执行秘钥为：“"+key+"”，可访问：“http://"+request.getLocalAddr()+":"
                + request.getLocalPort()
                +"/autoCaseRepertory/executePMCase.do?key="+key+"”进行测试，" +
                "本次测试请直接点击！<a href=\"/autoCaseRepertory/executePMCase.do?key="+key+"\" class=\"btn btn-primary\">执行测试</a>");
        return "autoCaseRepertoryResult";
    }


    @RequestMapping(value = "executePMCase")
    public String executePMCase(Model model,HttpServletRequest request,HttpServletResponse response) {
        model.addAttribute("username", SecurityUtils.getSubject().getPrincipal());
        try {
            byte[] bytes=autoCaseRepertoryService.createPostManTest(request);
            OutputStream out=response.getOutputStream();
            out.write(bytes);
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + "postmanAutoCase.zip");
        } catch (Exception e) {
            model.addAttribute("result", "下载执行文件失败，失败原因："+e.getMessage());
            return "autoCaseRepertoryResult";
        }
        return null;
    }


    @RequestMapping(value = "createGVMLExecutePlan")
    public String createGVMLExecutePlan(Model model,HttpServletRequest request) {
        String strategy=request.getParameter("strategy");
        String sendToTestlink=request.getParameter("sendToTestlink");
        String before=request.getParameter("before");
        String statement=request.getParameter("statement");
        String planName=request.getParameter("planName");
        String ids=request.getParameter("ids");
        String key;
        try {
            key = autoCaseRepertoryService.saveGVMLExecutePlan(strategy,sendToTestlink,before,statement,planName,ids);
        } catch (AutoCaseRepertoryException e) {
            model.addAttribute("result","获取执行秘钥失败：失败原因："+e.getMessage());
            this.prepareExecute(model,ids);
            return "autoCaseExecute";
        }
        model.addAttribute("username", SecurityUtils.getSubject().getPrincipal());
        model.addAttribute("result", "你的执行秘钥为：“"+key+"”，请在GVML专用浏览器访问：http://"+request.getLocalAddr()+":"
                + request.getLocalPort()
                +"/executeGVMLCase.jsp?key="+key+"进行测试，" +
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
            model.addAttribute("result", "用例更新成功！");
        } catch (Exception e) {
            model.addAttribute("result", "用例更新失败，失败原因："+e.getMessage());
        }
        try {
            autoCaseHelper=autoCaseRepertoryService.searchAutoCaseById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("autoCaseHelper", autoCaseHelper);
        return "lookAutoCase";
    }
}
