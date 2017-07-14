import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

/**
 * Created by user on 2017/7/14.
 */
public class test {

    @Test
    public void test(){
        String str = "h";
        String salt = "123"; //盐
        //单纯md5加密，易破解
        System.out.println(new Md5Hash(str).toString());
        //加干扰数据，即盐
        System.out.println(new Md5Hash(str,salt).toString());
        //指定散列次数，如2次：：md5(md5(str))
        System.out.println(new Md5Hash(str,salt,2).toString());
        //SHA256 算法 还有如SHA1、SHA512算法
        System.out.println(new Sha256Hash(str,salt).toString());
        //shiro通用散列支持,内部使用了Java 的MessageDigest实现
        System.out.println(new SimpleHash("md5",str,salt).toString());
    }
}
