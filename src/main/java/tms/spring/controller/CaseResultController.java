package tms.spring.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tms.spring.cache.CaseResultCountCache;
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

    @Autowired
    private CaseResultCountCache cache;

    @RequestMapping(value = "getAllTestSuites")
    @ResponseBody
    public Map<String, Object> getAllTestSuites() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Map> testSuites=cache.getAllTestSuites();
            String str=JSON.toJSONString(testSuites);
            map.put("message", str);
            map.put("code", Constant.CODE_SUCCESS);
        } catch (Exception e){
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "getTestCaseCount")
    @ResponseBody
    public Map<String, Object> getTestCaseCount(@RequestBody Map<String,String> jsonMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String planName=jsonMap.get("planName");
            String suiteId=jsonMap.get("suiteId");
            Map<String,Object> caseResultMap=cache.getCaseResultCount(planName,suiteId);
            String str=JSON.toJSONString(caseResultMap);
            map.put("message", str);
            map.put("code", Constant.CODE_SUCCESS);
        } catch (Exception e){
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }

}
