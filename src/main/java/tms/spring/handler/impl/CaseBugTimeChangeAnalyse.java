package tms.spring.handler.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tms.spring.dao.PlanDao;
import tms.spring.entity.Plan;
import tms.spring.exception.CaseAnalyseException;
import tms.spring.handler.CaseAnalyseHandler;
import tms.spring.utils.CaseAnalyseUtil;
import tms.spring.utils.PlanDataType;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/8/28.
 */
@Component
public class CaseBugTimeChangeAnalyse implements CaseAnalyseHandler {

    /**
     * 分析各模块用例数与问题数量随时间变化趋势
     * 需要参数planName，version，node
     * 分析准则：待定中。。。
     * */
    @Autowired
    private CaseAnalyseUtil caseAnalyseUtil;

    @Autowired
    private PlanDao planDao;

    public Map<String,Object> analyse(Map<String,String> map) throws CaseAnalyseException {
        Map<String,Object> returnMap=new HashMap<String, Object>();
        String planName=map.get("planName");
        String node=map.get("node");
        if(planName==null||node==null){
            throw new CaseAnalyseException("输入的分析信息不完善");
        }
        String version=map.get("version");
        if(version==null){
            version=caseAnalyseUtil.getNewVersion(planName);
        }
        Plan plan=new Plan();
        plan.setName(planName);
        plan.setNode(node);
        plan.setVersion(version);
        plan.setType(PlanDataType.EXECUTE.name());
        List<String> executeResults=planDao.selectPlanResults(plan);
        List<String> times=new ArrayList<String>();
        List<Integer> executeLists=new ArrayList<Integer>();
        List<Integer> severityLists=new ArrayList<Integer>();
        if(executeResults!=null&&executeResults.size()>0){
            for(String result:executeResults){
                Map<String,Object> executeMap=JSON.parseObject(result,Map.class);
                times.add(String.valueOf(executeMap.get("time")));
                executeLists.add(Integer.parseInt(String.valueOf(executeMap.get("pass")))+
                        Integer.parseInt(String.valueOf(executeMap.get("fail"))));
            }
        }
        plan.setType(PlanDataType.SEVERITY.name());
        List<String> severityResults=planDao.selectPlanResults(plan);
        if(severityResults!=null&&severityResults.size()>0){
            for(String result:severityResults){
                Map<String,Object> severityMap=JSON.parseObject(result,Map.class);
                severityLists.add(Integer.parseInt(String.valueOf(severityMap.get("total"))));
            }
        }
        List<List<Integer>> arrayLists=new ArrayList<List<Integer>>();
        arrayLists.add(executeLists);
        arrayLists.add(severityLists);
        returnMap.put("times",times);
        returnMap.put("time",times.get(times.size()-1));
        returnMap.put("result",arrayLists);
        returnMap.put("pass",true);
        return  returnMap;
    }


}
