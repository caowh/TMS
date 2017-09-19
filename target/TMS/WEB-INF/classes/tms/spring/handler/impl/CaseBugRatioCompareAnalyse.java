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
public class CaseBugRatioCompareAnalyse implements CaseAnalyseHandler {

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
        List<String> suiteList=new ArrayList<String>();
        List<Map<String,Integer>> caseBugRatioList=new ArrayList<Map<String,Integer>>();
        for(String version : versionList){
            List<Map> childSuites=caseAnalyseUtil.getChildSuites(planName,version,node);
            caseBugRatioList.add(getCaseBugRatioAndSuites(planName,version, childSuites,returnMap,suiteList));
        }
        List<List<Integer>> result=new ArrayList<List<Integer>>();
        for(Map<String,Integer> caseBugRatioMap: caseBugRatioList){
            List<Integer> list=new ArrayList<Integer>();
            for(String suiteName:suiteList){
                int ratio=0;
                try {
                    ratio=caseBugRatioMap.get(suiteName);
                }catch (Exception e){
                    //
                }
                list.add(ratio);
            }
            result.add(list);
        }
        returnMap.put("result",result);
        returnMap.put("suiteList",suiteList);
        returnMap.put("versions",caseAnalyseUtil.getUutVersion(versionList,planName));
        returnMap.put("pass",false);
        return  returnMap;
    }

    private Map<String,Integer> getCaseBugRatioAndSuites(String planName, String version, List<Map> childSuites,
                                          Map<String,Object> returnMap, List<String> suiteList
                                                                        ) throws CaseAnalyseException {
        Map<String,Integer> map=new HashMap<String, Integer>();
        String time="";
        if(childSuites!=null&&childSuites.size()>0){
            Plan plan=new Plan();
            plan.setName(planName);
            plan.setVersion(version);
            for (Map childSuite : childSuites){
                String suiteName=String.valueOf(childSuite.get("name"));
                if(!suiteList.contains(suiteName)){
                    suiteList.add(suiteName);
                }
                plan.setNode(String.valueOf(childSuite.get("id")));
                plan.setType(PlanDataType.EXECUTE.name());
                Map executeMap=(Map)caseAnalyseUtil.getResult(plan);
                int caseCount=Integer.parseInt(String.valueOf(executeMap.get("pass")))
                        +Integer.parseInt(String.valueOf(executeMap.get("fail")));
                plan.setType(PlanDataType.SEVERITY.name());
                Map severityMap=(Map)caseAnalyseUtil.getResult(plan);
                int bugCount=Integer.parseInt(String.valueOf(severityMap.get("total")));
                int ratio=0;
                if(caseCount!=0){
                    DecimalFormat format = new DecimalFormat("0.00");
                    String ratioStr=format.format((float)bugCount/caseCount);
                    ratio = (int) (Float.parseFloat(ratioStr)*100);
                }
                map.put(suiteName,ratio);
                time=String.valueOf(severityMap.get("time"));
            }
        }
        returnMap.put("time",time);
        return map;
    }


}
