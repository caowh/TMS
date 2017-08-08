package tms.spring.quartz;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tms.spring.cache.CaseResultCountCache;
import tms.spring.shiro.cache.JedisCacheManager;
import tms.spring.utils.Constant;
import tms.spring.utils.HttpRequestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/7/17.
 */
public class UpdateCaseResult {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    private CaseResultCountCache cache;

    public void execute(){
        logger.info("Begin Update CaseResult Count!");
//        List<Map> testSuites=HttpRequestUtils.httpGet(Constant.TESTLINKSERVICE_ADDRESS+"getAllTestSuites", List.class);
//        cache.putAllTestSuites(testSuites);
//        for (Map testSuite : testSuites){
//            String suiteId=String.valueOf(testSuite.get("id"));
//            String planName=String.valueOf(testSuite.get("planName"));
//            Map countMap=HttpRequestUtils.httpGet(Constant.TESTLINKSERVICE_ADDRESS+"getTestCaseCount/"+planName+"/"+suiteId, Map.class);
//            Map cacheMap=cache.getCaseResultCount(planName,suiteId);
//            SimpleDateFormat format = new SimpleDateFormat(" yyyy-MM-dd HH：mm：ss");
//            String currentDateString = format.format(new Date());
//            cacheMap.put(currentDateString,countMap);
//            cache.putCaseResultCount(planName,suiteId,cacheMap);
//        }
        logger.info("Finished Update CaseResult Count!");
    }

}
