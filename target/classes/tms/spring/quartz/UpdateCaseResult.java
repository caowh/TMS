package tms.spring.quartz;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tms.spring.shiro.cache.JedisCacheManager;

import java.util.Date;

/**
 * Created by user on 2017/7/17.
 */
public class UpdateCaseResult {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JedisCacheManager cacheManager;

    public void execute(){
        logger.info("get UpdateCaseResult message");
        String time=new Date().toString();
        getCache().put(time,"1");
        logger.info(String.valueOf(getCache().get(time)));
    }

    public Cache getCache() {
        return cacheManager.getCache("CaseResult");
    }
}
