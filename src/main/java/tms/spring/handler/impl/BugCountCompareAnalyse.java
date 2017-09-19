package tms.spring.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tms.spring.entity.Plan;
import tms.spring.exception.CaseAnalyseException;
import tms.spring.handler.CaseAnalyseHandler;
import tms.spring.utils.CaseAnalyseUtil;
import tms.spring.utils.PlanDataType;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by user on 2017/8/28.
 */
@Component
public class BugCountCompareAnalyse implements CaseAnalyseHandler {

    /**
     * 分析模块问题数随版本变化趋势
     * 需要参数planName，version，node
     * 分析准则：待定中。。。
     * */
    @Autowired
    private CaseAnalyseUtil caseAnalyseUtil;

    public Map<String,Object> analyse(Map<String,String> map) throws CaseAnalyseException {
        Map<String,Object> returnMap=new HashMap<String, Object>();
        String planName=map.get("planName");
        String node=map.get("node");
        if(planName==null||node==null){
            throw new CaseAnalyseException("输入的分析信息不完善");
        }
        String versions=map.get("version");
        List<String> versionList;
        if(versions==null||versions.equals("")){
            versionList=caseAnalyseUtil.getNewTwoVersion(planName);
        }else {
            versionList= Arrays.asList(versions.split(","));
        }
        if(versionList==null||versionList.size()<2){
            throw new CaseAnalyseException("不存在两个及两个以上的版本数据");
        }
        Plan plan=new Plan();
        plan.setName(planName);
        plan.setNode(node);
        List<Integer> sList=new ArrayList<Integer>();
        List<Integer> eList=new ArrayList<Integer>();
        String time = null;
        for(String version : versionList){
            plan.setVersion(version);
            plan.setType(PlanDataType.SEVERITY.name());
            Map data= (Map) caseAnalyseUtil.getResult(plan);
            int total=Integer.parseInt(String.valueOf(data.get("total")));
            time=String.valueOf(data.get("time"));
            sList.add(total);
            plan.setType(PlanDataType.EXECUTE.name());
            Map eData= (Map) caseAnalyseUtil.getResult(plan);
            int eTotal=Integer.parseInt(String.valueOf(eData.get("pass")))+
                    Integer.parseInt(String.valueOf(eData.get("fail")));
            eList.add(eTotal);
        }
        List<List<Integer>> lists=new ArrayList<List<Integer>>();
        lists.add(eList);
        lists.add(sList);
        returnMap.put("result",lists);
        returnMap.put("time",time);
        returnMap.put("versions",caseAnalyseUtil.getUutVersion(versionList,planName));
        returnMap.put("pass",true);
        return  returnMap;
    }


}
