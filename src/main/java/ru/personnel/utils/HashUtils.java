package ru.personnel.utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class HashUtils {

    public static String getMD5Hash(byte[] bytes) throws Exception{
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        md.update(bytes);
        return DatatypeConverter.printHexBinary(md.digest());
    }

    public static String getMD5Hash(String str) throws Exception{
        return getMD5Hash(str.getBytes()).toLowerCase();
    }

}
