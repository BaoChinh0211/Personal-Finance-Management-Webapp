package bc.bean;


import jakarta.mail.internet.MimeMessage;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("mailer")
public class Mailer {
    @Autowired
    JavaMailSender mailer;

    public static String code;
    public static String getCode() {
        return code;
    }

    public static void setCode(String code) {
        Mailer.code = code;
    }

    public String getRandomCode(){
        String randomCode = RandomStringUtils.randomAlphanumeric(6);
        setCode(randomCode);
        return getCode();
    }



    public boolean sendMail(String from, String to, String subject, String body) {
        try {
            MimeMessage mail = mailer.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
            helper.setFrom(from, from);
            helper.setTo(to);
            helper.setReplyTo(from, from);
            helper.setSubject(subject);
            helper.setText(body, true);
            mailer.send(mail);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
