package tms.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tms.spring.exception.CaseAnalysesException;
import tms.spring.service.CaseAnalyseService;
import tms.spring.utils.CaseAnalyseUtil;
import tms.spring.utils.Constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/8/8.
 */
@Controller
@RequestMapping("/CaseResult")
public class CaseResultController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CaseAnalyseService caseAnalyseService;

    @Autowired
    private CaseAnalyseUtil caseAnalyseUtil;

    @RequestMapping(value = "analyse")
    @ResponseBody
    public Map<String, Object> analyse(@RequestBody Map<String,String> jsonMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.putAll(caseAnalyseService.analyse(jsonMap));
            map.put("code", Constant.CODE_SUCCESS);
        } catch (Exception e){
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "total")
    public String total(@RequestParam("name") String name,Model model){
        model.addAttribute("planName",name);
        model.addAttribute("planMessage",caseAnalyseService.getNewMessage(name));
        Map<String,String> map=new HashMap();
        map.put("planName",name);
        map.put("node","0");
        map.put("detail","total");
        try {
            map.put("type","severity");
            int severityCount=caseAnalyseService.getCaseTotalCount(map);
            map.put("type","execute");
            int caseTotalCount=caseAnalyseService.getCaseTotalCount(map);
            map.put("detail","pass,fail");
            int executeCount=caseAnalyseService.getCaseTotalCount(map);
            map.put("detail","fail");
            int failCount=caseAnalyseService.getCaseTotalCount(map);
            model.addAttribute("severityCount",severityCount);
            model.addAttribute("caseTotalCount",caseTotalCount);
            model.addAttribute("executeCount",executeCount);
            model.addAttribute("failCount",failCount);
        } catch (CaseAnalysesException e) {
            logger.error("统计用例总数失败，失败原因："+e.getMessage());
            model.addAttribute("severityCount",-1);
            model.addAttribute("caseTotalCount",-1);
            model.addAttribute("executeCount",-1);
            model.addAttribute("failCount",-1);
        }
        return "totalCaseResult";
    }

    @RequestMapping(value = "caseTotalCount")
    @ResponseBody
    public Map<String, Object> caseTotalCount(@RequestBody Map<String,String> jsonMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int total=caseAnalyseService.getCaseTotalCount(jsonMap);
            map.put("code", Constant.CODE_SUCCESS);
            map.put("total",total);
        } catch (Exception e){
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "planNameList")
    @ResponseBody
    public List<String> planNameList() {
        return caseAnalyseService.getPlanNameList();
    }


    @RequestMapping(value = "updatePlanStatus")
    @ResponseBody
    public Map<String, Object> updatePlanStatus(@RequestBody Map<String,String> jsonMap) {
        String planId=jsonMap.get("planId");
        String update=jsonMap.get("update");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            caseAnalyseUtil.setPlanUpdateStatus(planId,Boolean.parseBoolean(update));
            map.put("code", Constant.CODE_SUCCESS);
        } catch (Exception e){
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }
}
