package tms.spring.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tms.spring.cache.CaseResultCountCache;
import tms.spring.entity.Plan;
import tms.spring.exception.CaseAnalysesException;

import java.util.*;

/**
 * Created by user on 2017/8/28.
 */
@Component
public class CaseAnalyseUtil {

    private static Logger logger = LoggerFactory.getLogger(CaseAnalyseUtil.class);

    @Autowired
    private CaseResultCountCache cache;

    public List<Map> getPlanList(){
        return (List<Map>)cache.getResult("planList");
    }

    public String getNewVersion(String planName){
        String version="";
        List<Map> planList = (List<Map>)cache.getResult("planList");
        if(planList!=null&&planList.size()>0){
            float number = 0;
            for (Map testPlan : planList){
                String currentPlanName=String.valueOf(testPlan.get("name"));
                String[] planArr=currentPlanName.split("_");
                if(planArr[0].equals(planName)){
                    float versionNumber=Float.parseFloat(planArr[1].replace("v",""));
                    if(versionNumber>number){
                        number=versionNumber;
                    }
                }
            }
            version="v"+number;
        }
        return version;
    }

    public List<String> getNewTwoVersion(String planName){
        List<String> versions=new ArrayList<String>();
        List<Map> planList = (List<Map>)cache.getResult("planList");
        if(planList!=null&&planList.size()>0){
            List<Float> floats=new ArrayList<Float>();
            for (Map testPlan : planList){
                String currentPlanName=String.valueOf(testPlan.get("name"));
                String[] planArr=currentPlanName.split("_");
                if(planArr[0].equals(planName)){
                    float versionNumber=Float.parseFloat(planArr[1].replace("v",""));
                    floats.add(versionNumber);
                }
            }
            if(floats!=null&&floats.size()>=2){
                Collections.sort(floats);
                versions.add("v"+floats.get(floats.size()-1));
                versions.add("v"+floats.get(floats.size()-2));
            }
        }
        return versions;
    }

    public Object getResult(Plan plan) throws CaseAnalysesException {
        String key=JSON.toJSONString(plan);
        Object data=cache.getResult(key);
        if(data!=null){
            return data;
        }else {
            logger.error("get key from redis is null ,key is :"+key);
            throw new CaseAnalysesException("找不到该数据");
        }
    }

    public Map<String,String> getMessage(String planName,String version){
        String message="";
        String truePlanName=planName+"_"+version;
        List<Map> planList = (List<Map>)cache.getResult("planList");
        if(planList!=null&&planList.size()>0){
            for (Map testPlan : planList){
                String currentPlanName=String.valueOf(testPlan.get("name"));
                if(currentPlanName.equals(truePlanName)){
                    message=String.valueOf(testPlan.get("notes")).replace("<p>","").replace("</p>","").replace("\n","").replace("\t","").trim();
                }
            }
        }
        Map<String,String> map= new HashMap<String, String>();
        try {
            String[] arrMessage=message.split(",");
            map.put("uut",arrMessage[0].split(":")[1].trim());
            map.put("environment",arrMessage[1].split(":")[1].trim());
            map.put("leader",arrMessage[2].split(":")[1].trim());
        } catch (Exception e) {
            logger.error("get message from test plan name:"+planName+",version:"+version+",data is "+message);
        }
        return map;
    }

    public List<Map> getChildSuites(String planName, String version, String node) throws CaseAnalysesException {
        List<Map> list=new ArrayList<Map>();
        Plan plan=new Plan();
        plan.setVersion(version);
        plan.setName(planName);
        plan.setNode("0");
        plan.setType(PlanDataType.SUITES.name());
        List<Map> testSuites= (List<Map>) getResult(plan);
        if (testSuites!=null&&testSuites.size()>0){
            if(node.equals("0")){
                String planId="";
                for (Map testSuite : testSuites){
                    String suiteName=String.valueOf(testSuite.get("name"));
                    if(planName.equals(suiteName)){
                        planId=String.valueOf(testSuite.get("id"));
                        break;
                    }
                }
                getChildSuitesById(list, testSuites, planId);
            }else {
                getChildSuitesById(list, testSuites, node);
            }
        }
        return list;
    }

    private void getChildSuitesById(List<Map> list, List<Map> testSuites, String planId) {
        for (Map testSuite : testSuites){
            String suiteParentId=String.valueOf(testSuite.get("parent_id"));
            if(suiteParentId.equals(planId)){
                list.add(testSuite);
            }
        }
    }

    public List<String> getUutVersion(List<String> versionList,String planName){
        List<String> list=new ArrayList<String>();
        for(String version: versionList){
            String message=getMessage(planName,version).get("uut");
            if(message!=null){
                list.add(message);
            }else {
                list.add("获取版本号失败");
            }
        }
        return list;
    }

    public List<String> getAllDeepCNode(String planName,String node) throws CaseAnalysesException {
        List<String> list=new ArrayList<String>();
        String[] planTrueName=planName.split("_");
        Plan plan=new Plan();
        plan.setVersion(planTrueName[1]);
        plan.setName(planTrueName[0]);
        plan.setNode("0");
        plan.setType(PlanDataType.SUITES.name());
        List<Map> testSuites= (List<Map>) getResult(plan);
        if(testSuites==null||testSuites.size()==0){
            return list;
        }
        getCNodeRecursion(node, list, testSuites);
        return list;
    }

    private void getCNodeRecursion(String node, List<String> list, List<Map> testSuites) {
        for (Map testSuite : testSuites){
            String currentPNode=String.valueOf(testSuite.get("parent_id"));
            if(currentPNode.equals(node)){
                String currentNode=String.valueOf(testSuite.get("id"));
                if(!list.contains(currentNode)){
                    list.add(currentNode);
                    getCNodeRecursion(currentNode,list,testSuites);
                }
            }
        }
    }

    public String selectNodeNameById(String planName,String node) throws CaseAnalysesException {
        String name="";
        String[] planTrueName=planName.split("_");
        Plan plan=new Plan();
        plan.setVersion(planTrueName[1]);
        plan.setName(planTrueName[0]);
        plan.setNode("0");
        plan.setType(PlanDataType.SUITES.name());
        List<Map> testSuites= (List<Map>) getResult(plan);
        if(testSuites==null||testSuites.size()==0){
            return name;
        }
        for (Map testSuite : testSuites){
            String currentNode=String.valueOf(testSuite.get("id"));
            if(node.equals(currentNode)){
                name=String.valueOf(testSuite.get("name"));
                break;
            }
        }
        return name;
    }
}
