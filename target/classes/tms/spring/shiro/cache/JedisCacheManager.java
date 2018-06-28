package tms.spring.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

/**
 * Created by user on 2017/8/8.
 */
public class JedisCacheManager implements CacheManager, Destroyable {

    private JedisManager jedisManager;

    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new JedisShiroCache<K, V>(s, getJedisManager());
    }

    public void destroy() throws Exception {
        //如果和其他系统，或者应用在一起就不能关闭
        //getJedisManager().getJedis().shutdown();
    }

    public JedisManager getJedisManager() {
        return jedisManager;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }
}
