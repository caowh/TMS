package tms.spring.cache;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.cache.Cache;
import org.quartz.core.QuartzScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tms.spring.dao.PlanDao;
import tms.spring.entity.Plan;
import tms.spring.shiro.cache.JedisCacheManager;
import tms.spring.shiro.cache.JedisShiroCache;
import tms.spring.utils.PlanDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/8/8.
 */
@Component
public class CaseResultCountCache implements SmartInitializingSingleton {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JedisCacheManager cacheManager;

    @Autowired
    private PlanDao planDao;

    private List<Map> testPlanList=new ArrayList<Map>();

    private List<Map> testPlanListAll=new ArrayList<Map>();

    private Map<String,List<String>> planSuiteMap=new HashMap<String, List<String>>();

    private Map<Object,Object> resultMap=new HashMap<Object, Object>();

    private List<Plan> plans=new ArrayList<Plan>();

    public List<Map> getTestPlanList() {
        return testPlanList;
    }

    public Map<String, List<String>> getPlanSuiteMap() {
        return planSuiteMap;
    }


    private JedisShiroCache getCache() {
        return (JedisShiroCache)cacheManager.getCache("CaseResultCount");
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
        resultMap.put(key,result);
        plan.setResult(JSON.toJSONString(result));
        plans.add(plan);
    }

    public void updatePlanList(){
        /**
         * 1、遍历最新的计划列表，删除掉缓存中无效的计划
         * 2、遍历最新的模块列表，删除已经无效的模块
         * 3、将最新获取到的数据存入redis
         * 3、将被存入缓存中的所有信息存入mysql，备份以及作为时间维度的展示
         */
        if(testPlanListAll!=null&&testPlanListAll.size()>0){
            String pattern="{\"id\":0,\"name\":*";
            List<String> keys=getCache().scan(pattern);
            for(String key:keys){
                Plan plan=JSON.parseObject(key,Plan.class);
                String name=plan.getName();
                String version=plan.getVersion();
                boolean bl=false;
                for(Map testPlan:testPlanListAll){
                    String planName=String.valueOf(testPlan.get("name"));
                    if(planName.equals(name+"_"+version)){
                        bl=true;
                        break;
                    }
                }
                if(!bl){
                    getCache().remove(key);
                }else {
                    String node=plan.getNode();
                    if(!node.equals("0")){
                        for(Map testPlan:testPlanList){
                            String planName=String.valueOf(testPlan.get("name"));
                            if(planName.equals(name+"_"+version)){
                                List<String> nodes=planSuiteMap.get(name+"_"+version);
                                if(nodes!=null&&nodes.size()>0){
                                    if(!nodes.contains(node)){
                                        getCache().remove(key);
                                    }
                                }else {
                                    getCache().remove(key);
                                }
                                break;
                            }
                        }
                    }
                }
            }
            getCache().put("planList",testPlanListAll);
            if(resultMap!=null&&resultMap.size()>0){
                for(Map.Entry<Object,Object> entry:resultMap.entrySet()){
                    Object key=entry.getKey();
                    Object value=entry.getValue();
                    getCache().put(key,value);
                }
            }
            if(plans!=null&&plans.size()>0){
                try{
                    planDao.insertPlans(plans);
                }catch (Exception e){
                    logger.error("insert plans into mysql throw exception:"+e);
                }
            }
        }
    }

    public void clearUselessCache() {
        testPlanList.clear();
        planSuiteMap.clear();
        plans.clear();
        testPlanListAll.clear();
        resultMap.clear();
    }

    public List<Map> getTestPlanListAll() {
        return testPlanListAll;
    }

    public Map<String, Boolean> getUpdateMap() {
        Object obj=getCache().get("updateMap");
        if(obj==null){
            return new HashMap<String, Boolean>();
        }
        return (Map<String, Boolean>) obj;
    }

    public void setUpdateMap(Map<String, Boolean> updateMap) {
        getCache().put("updateMap",updateMap);
    }

    public void afterSingletonsInstantiated() {
        logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
