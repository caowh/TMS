package tms.spring.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.util.SafeEncoder;
import tms.spring.utils.SerializeUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 2017/8/8.
 */
public class JedisShiroCache<K, V> implements Cache<K, V> {

    /**
     * 为了不和其他的缓存混淆，采用追加前缀方式以作区分
     */
    private static final String REDIS_SHIRO_CACHE = "shiro-cache:";
    /**
     * Redis 分片(分区)，也可以在配置文件中配置
     */
    private static final int DB_INDEX = 2;

    private JedisManager jedisManager;

    private String name;

    private static Logger logger = LoggerFactory.getLogger(JedisShiroCache.class);


    public JedisShiroCache(String name, JedisManager jedisManager) {
        this.name = name;
        this.jedisManager = jedisManager;
    }

    /**
     * 自定义relm中的授权/认证的类名加上授权/认证英文名字
     */
    public String getName() {
        if (name == null)
            return "";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public V get(K key) throws CacheException {
        byte[] byteKey = SafeEncoder.encode(buildCacheKey(key));
        byte[] byteValue = new byte[0];
        try {
            byteValue = jedisManager.getValueByKey(DB_INDEX, byteKey);
        } catch (Exception e) {
            logger.error("get value by cache throw exception",e);
        }
        return (V) SerializeUtil.deserialize(byteValue);
    }

    public V put(K key, V value) throws CacheException {
        V previos = get(key);
        try {
            jedisManager.saveValueByKey(DB_INDEX, SafeEncoder.encode(buildCacheKey(key)),
                    SerializeUtil.serialize(value), -1);
        } catch (Exception e) {
            logger.error("put cache throw exception",e);
        }
        return previos;
    }

    public V remove(K key) throws CacheException {
        V previos = get(key);
        try {
            jedisManager.deleteByKey(DB_INDEX, SafeEncoder.encode(buildCacheKey(key)));
        } catch (Exception e) {
            logger.error("remove cache  throw exception",e);

        }
        return previos;
    }

    public List<String> scan(String pattern){
        List<String> list = new ArrayList<String>();
        try {
            List<String> results=jedisManager.getKeysByPattern(DB_INDEX,REDIS_SHIRO_CACHE + getName() + ":"+pattern);
            if(results!=null&&results.size()>0){
                for(String result:results){
                    list.add(result.replace(REDIS_SHIRO_CACHE + getName() + ":",""));
                }
            }
        } catch (Exception e) {
            logger.error("scan cache  throw exception",e);
        }
        return list;
    }

    public void clear() throws CacheException {
        //TODO--
    }

    public int size() {
        if (keys() == null)
            return 0;
        return keys().size();
    }

    public Set<K> keys() {
        //TODO
        return null;
    }

    public Collection<V> values() {
        //TODO
        return null;
    }

    private String buildCacheKey(Object key) {
        return REDIS_SHIRO_CACHE + getName() + ":" + key;
    }

}
