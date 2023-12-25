package bc.util;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;

public class PasswordHashing {
    public static String toSha1(String password){
        String salt = "f1nd1;2;ngn3fsvbakjvajdpqjw0";
        String result = null;
        password = password + salt;

        try{
            byte[] dataBytes = password.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(md.digest(dataBytes));
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
}
