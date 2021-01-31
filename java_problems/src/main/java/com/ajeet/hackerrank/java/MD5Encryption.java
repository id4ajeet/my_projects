package com.ajeet.hackerrank.java;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import javax.xml.bind.DatatypeConverter;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class MD5Encryption {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String s = "HelloWorld";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(s.getBytes());

        String value = DatatypeConverter.printHexBinary(digest).toLowerCase(Locale.ROOT);
        System.out.println(value);
    }
}
