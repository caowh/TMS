package tms.spring.quartz;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tms.spring.cache.CaseResultCountCache;
import tms.spring.utils.Constant;
import tms.spring.utils.HttpRequestUtils;
import tms.spring.utils.PlanDataType;
import tms.spring.utils.WebSocketUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by user on 2017/7/17.
 */
public class UpdateCaseResult {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CaseResultCountCache cache;

    public void execute(){

        String message="开始获取最新数据!";
        logger.info(message);
        WebSocketUtil.broadcast(message,"服务器","0");
        try{
//            getTestPlanList();
//            getPlanExecuteCount();
//            getPlanSeverity();
//            getTestSuites();
//            getSuiteExecuteCount();
//            getSuiteSeverity();
//            message="开始更新数据!";
//            WebSocketUtil.broadcast(message,"服务器","0");
//            cache.updatePlanList();
        }catch (Exception e){
            message="数据更新过程出现异常:"+e.getMessage();
            logger.error(message);
            WebSocketUtil.broadcast(message,"服务器","0");
        }
        cache.clearUselessCache();
        message="数据更新结束!";
        logger.info(message);
        WebSocketUtil.broadcast(message,"服务器","0");
    }


    private void getSuiteSeverity() {
        Iterator<Map.Entry<String, List<String>>> entries = cache.getPlanSuiteMap().entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, List<String>> entry = entries.next();
            String planName=entry.getKey();
            List<String> suiteIds=entry.getValue();
            for (String suiteId : suiteIds){
                Map severityMap=HttpRequestUtils.httpGet(Constant.TESTLINKSERVICE_ADDRESS+
                        "getSuiteSeverity/"+planName+"/"+suiteId, Map.class);
                if(severityMap!=null){
//                    logger.info("planName:"+planName);
//                    logger.info("suiteId:"+suiteId);
//                    logger.info(JSON.toJSONString(severityMap));
                cache.putPlanResult(planName,suiteId,severityMap, PlanDataType.SEVERITY);
                }
            }
        }
    }

    private void getSuiteExecuteCount() {
        Iterator<Map.Entry<String, List<String>>> entries = cache.getPlanSuiteMap().entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, List<String>> entry = entries.next();
            String planName=entry.getKey();
            List<String> suiteIds=entry.getValue();
            for (String suiteId : suiteIds){
                Map executeMap=HttpRequestUtils.httpGet(Constant.TESTLINKSERVICE_ADDRESS+
                        "getSuiteExecuteCount/"+planName+"/"+suiteId, Map.class);
                if(executeMap!=null){
//                    logger.info("planName:"+planName);
//                    logger.info("suiteId:"+suiteId);
//                    logger.info(JSON.toJSONString(executeMap));
                cache.putPlanResult(planName,suiteId,executeMap, PlanDataType.EXECUTE);
                }
            }
        }
    }

    private void getTestSuites() {
        if (cache.getTestPlanList()!=null&&cache.getTestPlanList().size()>0){
            for (Map testPlan : cache.getTestPlanList()){
                String planName=String.valueOf(testPlan.get("name"));
                String planId=String.valueOf(testPlan.get("id"));
                List<Map> testSuites=HttpRequestUtils.httpGet(Constant.TESTLINKSERVICE_ADDRESS+"getTestSuites/"+planId, List.class);
                if (testSuites!=null&&testSuites.size()>0){
                    for (Map testSuite : testSuites){
                        String suiteId=String.valueOf(testSuite.get("id"));
                        List<String> suiteIds=cache.getPlanSuiteMap().get(planName);
                        if(suiteIds!=null&&suiteIds.size()>0){
                            suiteIds.add(suiteId);
                            cache.getPlanSuiteMap().put(planName,suiteIds);
                        }else {
                            List<String> init_suiteIds=new ArrayList<String>();
                            init_suiteIds.add(suiteId);
                            cache.getPlanSuiteMap().put(planName,init_suiteIds);
                        }
                    }
//                    logger.info(JSON.toJSONString(cache.getPlanSuiteMap()));
                    cache.putPlanResult(planName,"0",testSuites, PlanDataType.SUITES);
                }
            }
        }
    }

    private void getPlanExecuteCount() {
        if (cache.getTestPlanList()!=null&&cache.getTestPlanList().size()>0){
            for (Map testPlan : cache.getTestPlanList()){
                String planName=String.valueOf(testPlan.get("name"));
                String planId=String.valueOf(testPlan.get("id"));
                Map executeMap=HttpRequestUtils.httpGet(Constant.TESTLINKSERVICE_ADDRESS+"getPlanExecuteCount/"+planId, Map.class);
                if(executeMap!=null){
//                    logger.info("planName:"+planName);
//                    logger.info(JSON.toJSONString(executeMap));
                    cache.putPlanResult(planName,"0",executeMap, PlanDataType.EXECUTE);
                }
            }
        }
    }

    private void getTestPlanList() {
        List<Map> testPlans=HttpRequestUtils.httpGet(Constant.TESTLINKSERVICE_ADDRESS+"getAllTestPlan", List.class);
        if(testPlans!=null&&testPlans.size()>0){
            cache.getTestPlanListAll().addAll(testPlans);
            Map<String, Boolean> updateMap=cache.getUpdateMap();
            for (Map testPlan:testPlans){
                String planId=String.valueOf(testPlan.get("id"));
                Boolean bl=updateMap.get(planId);
                if(bl!=null){
                    if(bl){
                        cache.getTestPlanList().add(testPlan);
                    }
                }else {
                    cache.getTestPlanList().add(testPlan);
                    updateMap.put(planId,true);
                }
            }
            cache.setUpdateMap(updateMap);
        }
        if(cache.getTestPlanList()!=null&&cache.getTestPlanList().size()>0){
            logger.info("Update planList:"+ JSON.toJSONString(cache.getTestPlanList()));
        }else {
            logger.warn("Update planList is null!");
        }
//            if(bl){
//                List<Map> testPlanList=new ArrayList<Map>();
//                /**只更新最新版本的计划*/
//                Map<String,Map> testPlanMap=new HashMap();
//                for (Map testPlan:testPlans){
//                    String planName=String.valueOf(testPlan.get("name"));
//                    String[] planArr=planName.split("_");
//                    float versionNumber=Float.parseFloat(planArr[1].replace("v",""));
//                    String name=planArr[0];
//                    Map map=testPlanMap.get(name);
//                    if(map!=null){
//                        String existPlanName=String.valueOf(map.get("name"));
//                        float existVersion=Float.parseFloat(existPlanName.split("_")[1].replace("v",""));
//                        if(existVersion<versionNumber){
//                            testPlanMap.put(name,testPlan);
//                        }
//                    }else {
//                        testPlanMap.put(name,testPlan);
//                    }
//                }
//                Iterator<Map.Entry<String,Map>> iterator=testPlanMap.entrySet().iterator();
//                while (iterator.hasNext()){
//                    Map.Entry<String,Map> entry=iterator.next();
//                    testPlanList.add(entry.getValue());
//                }
//                cache.getTestPlanList().addAll(testPlanList);
//            }else {
//                cache.getTestPlanList().addAll(testPlans);
//            }
//        }
    }

    private void getPlanSeverity(){
        if (cache.getTestPlanList()!=null&&cache.getTestPlanList().size()>0){
             for (Map testPlan : cache.getTestPlanList()){
                String planName=String.valueOf(testPlan.get("name"));
                String planId=String.valueOf(testPlan.get("id"));
                Map severityMap=HttpRequestUtils.httpGet(Constant.TESTLINKSERVICE_ADDRESS+"getPlanSeverity/"+planId, Map.class);
                if(severityMap!=null){
//                    logger.info("planName:"+planName);
//                    logger.info(JSON.toJSONString(severityMap));
                    cache.putPlanResult(planName,"0",severityMap, PlanDataType.SEVERITY);
                }
            }
        }
    }
}
