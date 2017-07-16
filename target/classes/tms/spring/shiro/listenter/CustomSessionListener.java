package tms.spring.shiro.listenter;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomSessionListener implements SessionListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 一个回话的生命周期开始
     */
    public void onStart(Session session) {
        //TODO
        logger.info("%s on start",session.getId());
    }
    /**
     * 一个回话的生命周期结束
     */
    public void onStop(Session session) {
        //TODO
        logger.info("%s on stop",session.getId());
    }

    public void onExpiration(Session session) {
        //TODO
        logger.info("%s on onExpiration",session.getId());
    }


}

