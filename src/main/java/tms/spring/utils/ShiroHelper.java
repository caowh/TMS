package tms.spring.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by user on 2017/7/14.
 */
public class ShiroHelper {

    public static String encryptPassword(String password){
            return new Md5Hash(password,Constant.ENCRYPT_SALT,Constant.ENCRYPT_HASHITERATIONS).toString();
    }


}
