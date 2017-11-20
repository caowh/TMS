import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;
import java.util.Random;

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

//    @Test
//    public  void main() throws Exception {
//        Properties prop = new Properties();
//        prop.setProperty("mail.debug", "true");
//        prop.setProperty("mail.host", "smtp.exmail.qq.com");
//        prop.setProperty("mail.transport.protocol", "smtp");
//        prop.setProperty("mail.smtp.auth", "true");
//        // 1、创建session
//        Session session = Session.getInstance(prop);
//        Transport ts = null;
//        // 2、通过session得到transport对象
//        ts = session.getTransport();
//        // 3、连上邮件服务器
//        ts.connect("smtp.exmail.qq.com", "caowh@geovis.com.cn", "Cwh123456");
//        // 4、创建邮件
//        MimeMessage message = new MimeMessage(session);
//        // 邮件消息头
//        message.setFrom(new InternetAddress("caowh@geovis.com.cn")); // 邮件的发件人
//        message.setRecipient(Message.RecipientType.TO, new InternetAddress("1240597349@qq.com")); // 邮件的收件人
////        message.setRecipient(Message.RecipientType.CC, new InternetAddress(MAIL_CC)); // 邮件的抄送人
////        message.setRecipient(Message.RecipientType.BCC, new InternetAddress(MAIL_BCC)); // 邮件的密送人
//        message.setSubject("TMS用户注册验证码"); // 邮件的标题
//        String htmlContent = "<h1>欢迎您使用TMS！</h1><br><br>尊敬的用户，您的验证码为：<span style='color:blue;font-size:18px'>123456</span>，" +
//                "请在1分钟内使用<br><br>如果你在使用过程中有任何疑问或建议，请通过此邮件发件人与我们联系，你的满意是我们最大的心愿！";
//        MimeBodyPart text = new MimeBodyPart();
//        text.setContent(htmlContent, "text/html;charset=UTF-8");
//        // 邮件消息体
//        MimeMultipart mm = new MimeMultipart();
//        mm.addBodyPart(text);
//        message.setContent(mm);
//        message.saveChanges();
//        // 5、发送邮件
//        ts.sendMessage(message, message.getAllRecipients());
//        ts.close();
//    }

    @Test
    public void createValidateCode(){
        String val = "";
        Random random = new Random();
        for(int i = 0; i < 6; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if( "char".equalsIgnoreCase(charOrNum) ) {
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        System.out.print(val);
    }
}
