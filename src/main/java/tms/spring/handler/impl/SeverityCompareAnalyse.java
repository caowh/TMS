package tms.spring.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tms.spring.entity.Plan;
import tms.spring.exception.CaseAnalyseException;
import tms.spring.handler.CaseAnalyseHandler;
import tms.spring.utils.CaseAnalyseUtil;
import tms.spring.utils.PlanDataType;

import java.util.*;

/**
 * Created by user on 2017/8/28.
 */
@Component
public class SeverityCompareAnalyse implements CaseAnalyseHandler {

    /**
     * 分析多版本之间用例的严重级别变化
     * 需要参数planName，versions，node
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
        Plan plan=new Plan();
        plan.setName(planName);
        plan.setNode(node);
        plan.setType(PlanDataType.SEVERITY.name());
        String versions=map.get("versions");
        List<String> versionList;
        if(versions==null||versions.equals("")){
            versionList=caseAnalyseUtil.getNewTwoVersion(planName);
        }else {
            versionList=Arrays.asList(versions.split(","));
        }
        if(versionList==null||versionList.size()<2){
            throw new CaseAnalyseException("不存在两个及两个以上的版本数据");
        }
        List<List<Integer>> results=new ArrayList<List<Integer>>();
        String time="";
        for(String version : versionList){
            plan.setVersion(version);
            Map data= (Map) caseAnalyseUtil.getResult(plan);
            results.add(getSeverityList(data));
            time=String.valueOf(data.get("time"));
        }
        returnMap.put("pass",true);
        returnMap.put("result",results);
        returnMap.put("versions",caseAnalyseUtil.getUutVersion(versionList,planName));
        returnMap.put("time",time);
        return  returnMap;
    }

    private List<Integer> getSeverityList(Map map) {
        List<Integer> list=new ArrayList<Integer>();
        int fatal=Integer.parseInt(String.valueOf(map.get("fatal")));
        list.add(fatal);
        int serious=Integer.parseInt(String.valueOf(map.get("serious")));
        list.add(serious);
        int common=Integer.parseInt(String.valueOf(map.get("common")));
        list.add(common);
        int advise=Integer.parseInt(String.valueOf(map.get("advise")));
        list.add(advise);
        int total=Integer.parseInt(String.valueOf(map.get("total")));
        list.add(total);
        return list;
    }
}
