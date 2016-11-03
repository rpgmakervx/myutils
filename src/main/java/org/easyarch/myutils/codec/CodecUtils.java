package org.easyarch.myutils.codec;/**
 * Description : MD5Util
 * Created by YangZH on 16-5-25
 *  下午2:25
 */

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.easyarch.myutils.codec.HashType.MD5;
import static org.easyarch.myutils.codec.HashType.SHA1;
/**
 * Description : MD5Util
 * Created by YangZH on 16-5-25
 * 下午2:25
 */

public class CodecUtils {

    public static String hash(byte[] bytes,String encName){
        MessageDigest md = null;
        String strDes = null;
        try {
            if (encName == null || encName.isEmpty()) {
                encName = "MD5";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bytes);
            strDes = bytes2Hex(md.digest()); //to HexString
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Invalid algorithm.");
            return null;
        }
        return strDes;
    }

    public static String sha1(byte[] bytes){
        return hash(bytes,SHA1);
    }

    public static String md5(byte[] bytes){
        return hash(bytes,MD5);
    }

    public static String hash(String strSrc, String encName) {
        return hash(strSrc.getBytes(),encName);
    }
    public static long hash(String key) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance(SHA1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md5.update(key.getBytes());
        byte[] bKey = md5.digest();
        long res = ((long) (bKey[3] & 0xFF) << 24) | ((long) (bKey[2] & 0xFF) << 16) | ((long) (bKey[1] & 0xFF) << 8)
                | (long) (bKey[0] & 0xFF);
        return res & 0xffffffffL;
    }

    public static byte[] hmacSHA1(byte[] data, String key) {
        SecretKey secretKey;
        byte[] bytes = null;
        try {
            secretKey = new SecretKeySpec(decodeBase64(key), "HmacSHA1");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static String encodeBase64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * BASE64 解密
     * @param key 需要解密的字符串
     * @return 字节数组
     * @throws Exception
     */
    public static byte[] decodeBase64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    public static long murmurHash64(String text) {
        byte[] bytes = text.getBytes();
        return murmurHash64(bytes, bytes.length, -512093083);
    }

    public static long murmurHash64(byte[] data, int length, int seed) {
        long m = -4132994306676758123L;
        boolean r = true;
        long h = (long)seed & 4294967295L ^ (long)length * -4132994306676758123L;
        int length8 = length / 8;

        for(int i = 0; i < length8; ++i) {
            int i8 = i * 8;
            long k = ((long)data[i8 + 0] & 255L) + (((long)data[i8 + 1] & 255L) << 8) + (((long)data[i8 + 2] & 255L) << 16) + (((long)data[i8 + 3] & 255L) << 24) + (((long)data[i8 + 4] & 255L) << 32) + (((long)data[i8 + 5] & 255L) << 40) + (((long)data[i8 + 6] & 255L) << 48) + (((long)data[i8 + 7] & 255L) << 56);
            k *= -4132994306676758123L;
            k ^= k >>> 47;
            k *= -4132994306676758123L;
            h ^= k;
            h *= -4132994306676758123L;
        }

        switch(length % 8) {
            case 7:
                h ^= (long)(data[(length & -8) + 6] & 255) << 48;
            case 6:
                h ^= (long)(data[(length & -8) + 5] & 255) << 40;
            case 5:
                h ^= (long)(data[(length & -8) + 4] & 255) << 32;
            case 4:
                h ^= (long)(data[(length & -8) + 3] & 255) << 24;
            case 3:
                h ^= (long)(data[(length & -8) + 2] & 255) << 16;
            case 2:
                h ^= (long)(data[(length & -8) + 1] & 255) << 8;
            case 1:
                h ^= (long)(data[length & -8] & 255);
                h *= -4132994306676758123L;
            default:
                h ^= h >>> 47;
                h *= -4132994306676758123L;
                h ^= h >>> 47;
                return h;
        }
    }

    private static String bytes2Hex(byte[] bts) {
        StringBuffer des = new StringBuffer();
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString();

    }


}
