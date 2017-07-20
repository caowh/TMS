package tms.spring.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by user on 2017/7/17.
 */
public class UpdateCaseResult {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void execute(){
        logger.info("get UpdateCaseResult message");
    }
}
