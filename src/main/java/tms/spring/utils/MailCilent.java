package tms.spring.utils;

import tms.spring.exception.MailException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import java.util.Random;

/**
 * Created by user on 2017/7/17.
 */
public class MailCilent {

    private String sender;
    private String password;
    private String host;

    private String debug="true";
    private String transportProtocol="smtp";
    private String smtpAuth="true";

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String sendHtmlMail(String recipients,String content) throws MailException {
        String validateCode=createValidateCode();
        Properties prop = new Properties();
        prop.setProperty("mail.debug", debug);
        prop.setProperty("mail.host", host);
        prop.setProperty("mail.transport.protocol", transportProtocol);
        prop.setProperty("mail.smtp.auth", smtpAuth);
        // 1、创建session
        Session session = Session.getInstance(prop);
        Transport ts = null;
        // 2、通过session得到transport对象
        try {
            ts = session.getTransport();
        } catch (NoSuchProviderException e) {
            throw new MailException("get mail session failed");
        }
        // 3、连上邮件服务器
        try {
            ts.connect(host, sender, password);
        } catch (MessagingException e) {
            throw new MailException("connect mail host failed");
        }
        // 4、创建邮件
        MimeMessage message = new MimeMessage(session);
        // 邮件消息头
        try {
            message.setFrom(new InternetAddress(sender)); // 邮件的发件人
        } catch (MessagingException e) {
            throw new MailException("get mail InternetAddress of "+sender+" failed");
        }
        try {
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients)); // 邮件的收件人
        } catch (MessagingException e) {
            throw new MailException("get mail InternetAddress of "+recipients+" failed");
        }
//        message.setRecipient(Message.RecipientType.CC, new InternetAddress(MAIL_CC)); // 邮件的抄送人
//        message.setRecipient(Message.RecipientType.BCC, new InternetAddress(MAIL_BCC)); // 邮件的密送人
        try {
            message.setSubject("TMS用户注册验证码"); // 邮件的标题
        } catch (MessagingException e) {
            throw new MailException("set mail subject failed");
        }
        String htmlContent = "<h1>欢迎您使用TMS！</h1><br>尊敬的用户，"+content+"<br>您的验证码为：<span style='color:blue;font-size:18px'>"+validateCode+"</span>，" +
                "请在15分钟内使用<br><br>如果您在使用过程中有任何疑问或建议，请通过此邮件发件人与我们联系，您的满意是我们最大的心愿！";
        MimeBodyPart text = new MimeBodyPart();
        try {
            text.setContent(htmlContent, "text/html;charset=UTF-8");
        } catch (MessagingException e) {
            throw new MailException("set mail html content failed");
        }
        // 邮件消息体
        MimeMultipart mm = new MimeMultipart();
        try {
            mm.addBodyPart(text);
            message.setContent(mm);
            message.saveChanges();
        } catch (MessagingException e) {
            throw new MailException("add mail message body failed");
        }
        // 5、发送邮件
        try {
            ts.sendMessage(message, message.getAllRecipients());
        } catch (MessagingException e) {
            throw new MailException("prepare all,then send mail failed");
        }
        try {
            ts.close();
        } catch (MessagingException e) {
            throw new MailException("mail transport closed failed");
        }
        return validateCode;
    }

    private String createValidateCode(){
        String val = "";
        Random random = new Random();

        for(int i = 0; i < 6; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
}
