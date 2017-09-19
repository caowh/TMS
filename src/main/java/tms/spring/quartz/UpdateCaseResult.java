package tms.spring.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tms.spring.cache.CaseResultCountCache;
import tms.spring.utils.Constant;
import tms.spring.utils.HttpRequestUtils;
import tms.spring.utils.PlanDataType;

import java.util.*;

/**
 * Created by user on 2017/7/17.
 */
public class UpdateCaseResult {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CaseResultCountCache cache;

    public void execute(){
        logger.info("Begin Update CaseResult Count!");
        /**
         * 根据产品得到测试计划列表
         * 根据每个测试计划得到对应的用例执行情况
         * 根据每个测试计划得到对应的严重级别分布情况
         * 根据每个测试计划得到对应的测试套
         * 根据每个测试套得到对应的用例执行情况
         * 根据每个测试套得到对应的严重级别分布情况
         * 。。。
         * 所有统计完毕，更新计划
         * 清除要使用的缓存
         * */
//        getTestPlanList(false);
//        getPlanExecuteCount();
//        getPlanSeverity();
//        getTestSuites();
//        getSuiteExecuteCount();
//        getSuiteSeverity();
//        cache.updatePlanList();
//        cache.clearUselessCache();
        logger.info("Finished Update CaseResult Count!");
    }

    private void getSuiteSeverity() {
        logger.info("Begin Get Suite Severity!");
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
        logger.info("Finished Get Suite Severity!");
    }

    private void getSuiteExecuteCount() {
        logger.info("Begin Get Suite ExecuteCount!");
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
        logger.info("Finished Get Suite ExecuteCount!");
    }

    private void getTestSuites() {
        logger.info("Begin Get Plan TestSuites!");
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
        logger.info("Finished Get Plan TestSuites!");
    }

    private void getPlanExecuteCount() {
        logger.info("Begin Get Plan CaseExecuteCount!");
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
        logger.info("Finished Get Plan CaseExecuteCount!");
    }

    private void getTestPlanList(Boolean bl) {
        logger.info("Begin Get getTestPlanList!");
        List<Map> testPlans=HttpRequestUtils.httpGet(Constant.TESTLINKSERVICE_ADDRESS+"getAllTestPlan", List.class);
        if(testPlans!=null&&testPlans.size()>0){
            cache.getTestPlanListAll().addAll(testPlans);
            if(bl){
                List<Map> testPlanList=new ArrayList<Map>();
                Map<String,Map> testPlanMap=new HashMap();
                for (Map testPlan:testPlans){
                    String planName=String.valueOf(testPlan.get("name"));
                    String[] planArr=planName.split("_");
                    float versionNumber=Float.parseFloat(planArr[1].replace("v",""));
                    String name=planArr[0];
                    Map map=testPlanMap.get(name);
                    if(map!=null){
                        String existPlanName=String.valueOf(map.get("name"));
                        float existVersion=Float.parseFloat(existPlanName.split("_")[1].replace("v",""));
                        if(existVersion<versionNumber){
                            testPlanMap.put(name,testPlan);
                        }
                    }else {
                        testPlanMap.put(name,testPlan);
                    }
                }
                Iterator<Map.Entry<String,Map>> iterator=testPlanMap.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry<String,Map> entry=iterator.next();
                    testPlanList.add(entry.getValue());
                }
                cache.getTestPlanList().addAll(testPlanList);
            }else {
                cache.getTestPlanList().addAll(testPlans);
            }
        }
        logger.info("Finished Get getTestPlanList!");
    }

    private void getPlanSeverity(){
        logger.info("Begin Get Plan CaseSeverity!");
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
        logger.info("Finished Get Plan CaseSeverity!");
    }
}
