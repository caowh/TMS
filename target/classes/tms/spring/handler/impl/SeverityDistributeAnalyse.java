package tms.spring.handler.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tms.spring.entity.Plan;
import tms.spring.exception.AutoCaseRepertoryException;
import tms.spring.exception.CaseAnalysesException;
import tms.spring.handler.CaseAnalyseHandler;
import tms.spring.utils.CaseAnalyseUtil;
import tms.spring.utils.PlanDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/8/28.
 */
@Component
public class SeverityDistributeAnalyse implements CaseAnalyseHandler {

    /**
     * 分析测试用例的严重级别分别情况
     * 需要参数planName，version，node
     * 分析准则：致命问题<=3%，严重问题<=6%，一般问题<=75%，建议问题<=16%
     * */
    @Autowired
    private CaseAnalyseUtil caseAnalyseUtil;

    public Map<String,Object> analyse(Map<String,String> map) throws CaseAnalysesException {
        Map<String,Object> returnMap=new HashMap<String, Object>();
        String planName=map.get("planName");
        String node=map.get("node");
        if(planName==null||node==null){
            throw new CaseAnalysesException("输入的分析信息不完善");
        }
        Plan plan=new Plan();
        plan.setName(planName);
        plan.setNode(node);
        plan.setType(PlanDataType.SEVERITY.name());
        String version=map.get("version");
        if(version==null||version.equals("")){
            plan.setVersion(caseAnalyseUtil.getNewVersion(planName));
        }else {
            plan.setVersion(version);
        }
        Map data= (Map) caseAnalyseUtil.getResult(plan);
        int fatal=Integer.parseInt(String.valueOf(data.get("fatal")));
        int serious=Integer.parseInt(String.valueOf(data.get("serious")));
        int common=Integer.parseInt(String.valueOf(data.get("common")));
        int advise=Integer.parseInt(String.valueOf(data.get("advise")));
        int total=Integer.parseInt(String.valueOf(data.get("total")));
        Boolean bl=reckonIsPass(fatal,serious,common,advise,total);
        returnMap.put("pass",bl);
        String result= getChartsData(fatal,serious,common,advise);
        returnMap.put("result",result);
        returnMap.put("time",String.valueOf(data.get("time")));
        return  returnMap;
    }

    private String getChartsData(int fatal, int serious, int common, int advise) {
        List<Map> list=new ArrayList<Map>();
        Map<String,Object> map1=new HashMap();
        map1.put("value",fatal);
        map1.put("name","致命问题");
        Map<String,Object> map2=new HashMap();
        map2.put("value",serious);
        map2.put("name","严重问题");
        Map<String,Object> map3=new HashMap();
        map3.put("value",common);
        map3.put("name","一般问题");
        Map<String,Object> map4=new HashMap();
        map4.put("value",advise);
        map4.put("name","建议问题");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        return JSON.toJSONString(list);
    }

    private Boolean reckonIsPass(int fatal, int serious, int common, int advise, int total) {
        if((float)fatal/total<=0.03&&(float)serious/total<=0.06&&(float)common/total<=0.75&&(float)advise/total<=0.16){
            return true;
        }else {
            return false;
        }
    }
}
