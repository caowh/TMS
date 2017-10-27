package tms.spring.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by user on 2017/10/26.
 */
public class RegExpValidatorUtilsTest {

    @Test
    public void isUrl() throws Exception {
        String url="http://w.xx.com";
        assertTrue(RegExpValidatorUtils.IsUrl(url));
    }

}