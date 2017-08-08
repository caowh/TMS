package tms.spring.cache;

import org.apache.shiro.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tms.spring.shiro.cache.JedisCacheManager;
import tms.spring.utils.Constant;

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

    private Cache getCache() {
        return cacheManager.getCache("CaseResultCount");
    }

    public Map<String,Object> getCaseResultCount(String planName,String suiteId){
        String key=Constant.PRODUCT_ID+"_"+planName+"_"+suiteId;
        Object object=getCache().get(key);
        if (null!=object){
            return (Map)object;
        }else {
            return new HashMap<String, Object>();
        }
    }

    public void putCaseResultCount(String planName,String suiteId, Map<String,Object> map){
        String key=Constant.PRODUCT_ID+"_"+planName+"_"+suiteId;
        getCache().put(key,map);
    }

    public void putAllTestSuites(List<Map> list){
        String key=Constant.PRODUCT_ID+"_AllTestSuites";
        getCache().put(key,list);
    }

    public List<Map> getAllTestSuites(){
        String key=Constant.PRODUCT_ID+"_AllTestSuites";
        return (List<Map>)getCache().get(key);
    }
}
