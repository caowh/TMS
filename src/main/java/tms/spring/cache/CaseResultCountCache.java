package tms.spring.cache;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tms.spring.dao.PlanDao;
import tms.spring.entity.Plan;
import tms.spring.shiro.cache.JedisCacheManager;
import tms.spring.utils.PlanDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/8/8.
 */
@Component
public class CaseResultCountCache {

    @Autowired
    private JedisCacheManager cacheManager;

    @Autowired
    private PlanDao planDao;

    private List<Map> testPlanList=new ArrayList<Map>();

    private Map<String,List<String>> planSuiteMap=new HashMap<String, List<String>>();

    private List<Plan> plans=new ArrayList<Plan>();

    public List<Map> getTestPlanList() {
        return testPlanList;
    }

    public Map<String, List<String>> getPlanSuiteMap() {
        return planSuiteMap;
    }


    private Cache getCache() {
        return cacheManager.getCache("CaseResultCount");
    }

    public Object getResult(String key){
        return getCache().get(key);
    }

    public void putPlanResult(String planName, String node, Object result,PlanDataType type){
        /**
         * 1、解析数据
         * 2、存入redis
         * 3、存入mysql
        */
        String[] planArr=planName.split("_");
        Plan plan=new Plan();
        plan.setName(planArr[0]);
        plan.setVersion(planArr[1]);
        plan.setNode(node);
        plan.setType(type.name());
        String key=JSON.toJSONString(plan);
        getCache().put(key,result);
        plan.setResult(JSON.toJSONString(result));
        plans.add(plan);
    }

    public void updatePlanList(){
        if(testPlanList!=null&&testPlanList.size()>0){
            getCache().put("planList",testPlanList);
            if(plans!=null&&plans.size()>0){
                planDao.insertPlans(plans);
            }
        }
    }

    public void clearUselessCache() {
        testPlanList.clear();
        planSuiteMap.clear();
        plans.clear();
    }
}
