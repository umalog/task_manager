package services;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    private static final Logger logger = Logger.getLogger(PasswordEncoder.class);
    static {
        PropertyConfigurator.configure("/log4j.properties");
    }

    public static String encode(String password) {
        String result = md5(password) + "qweqwe";
        StringBuilder a = new StringBuilder(result).reverse();
        result = a.toString();
        result = md5(result);
        return result;
    }
    private static String md5(String password) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(password.getBytes());
            result = new String(array);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        }
        return result;
    }
}