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
public class CaseBugCountAnalyse implements CaseAnalyseHandler {

    /**
     * 分析各子模块用例数与问题数量与比值
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
        String version=map.get("version");
        if(version==null){
            version=caseAnalyseUtil.getNewVersion(planName);
        }
        List<Map> childSuites=caseAnalyseUtil.getChildSuites(planName,version,node);
        Map<String,Object> result=getChartData(planName, version, childSuites);

        returnMap.putAll(result);
        returnMap.put("pass",true);
        return  returnMap;
    }

    private Map<String,Object> getChartData(String planName, String version, List<Map> childSuites) throws CaseAnalyseException {
        Map<String,Object> map=new HashMap<String, Object>();
        List<String> suiteList=new ArrayList<String>();
        List<Integer> caseCountList=new ArrayList<Integer>();
        List<Integer> bugCountList=new ArrayList<Integer>();
        List<Integer> ratioList=new ArrayList<Integer>();
        String time="";
        if(childSuites!=null&&childSuites.size()>0){
            Plan plan=new Plan();
            plan.setName(planName);
            plan.setVersion(version);
            for (Map childSuite : childSuites){
                suiteList.add(String.valueOf(childSuite.get("name")));
                plan.setNode(String.valueOf(childSuite.get("id")));
                plan.setType(PlanDataType.EXECUTE.name());
                Map executeMap=(Map)caseAnalyseUtil.getResult(plan);
                int caseCount=Integer.parseInt(String.valueOf(executeMap.get("pass")))
                        +Integer.parseInt(String.valueOf(executeMap.get("fail")));
                caseCountList.add(caseCount);
                plan.setType(PlanDataType.SEVERITY.name());
                Map severityMap=(Map)caseAnalyseUtil.getResult(plan);
                int bugCount=Integer.parseInt(String.valueOf(severityMap.get("total")));
                bugCountList.add(bugCount);
                int ratio=0;
                if(caseCount!=0){
                    DecimalFormat format = new DecimalFormat("0.00");
                    String ratioStr=format.format((float)bugCount/caseCount);
                    ratio = (int) (Float.parseFloat(ratioStr)*100);
                }
                ratioList.add(ratio);
                time=String.valueOf(severityMap.get("time"));
            }
        }else {
            throw new CaseAnalyseException("该模块不存在子节点");
        }
        map.put("suiteList",suiteList);
        map.put("caseCountList",caseCountList);
        map.put("bugCountList",bugCountList);
        map.put("ratioList",ratioList);
        map.put("time",time);
        return map;
    }

}
