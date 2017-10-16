package services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {


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
            e.printStackTrace();
        }
        return result;
    }
}