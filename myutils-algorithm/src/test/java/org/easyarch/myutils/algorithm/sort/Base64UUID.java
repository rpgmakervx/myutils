package org.easyarch.myutils.algorithm.sort;

import java.util.Base64;
import java.util.UUID;

/**
 * Created by xingtianyu on 2018/9/20.
 */
public class Base64UUID {
    public static byte[] hex2Bytes(String hex) {
        if (hex == null || hex.isEmpty()) {
            return new byte[0];
        }
        byte[] bytes = hex.getBytes();
        int n = bytes.length >> 1;
        byte[] buf = new byte[n];
        for (int i = 0; i < n; i++) {
            int index = i << 1;
            buf[i] = (byte) ((byte2Int(bytes[index]) << 4) | byte2Int(bytes[index + 1]));
        }
        return buf;
    }

    private static int byte2Int(byte b) {
        return (b <= '9') ? b - '0' : b - 'a' + 10;
    }

    public static String compressUUID(String uuid){
        String hex = uuid.replace("-", "");
        byte[] bytes = hex2Bytes(hex);
        return new String(Base64.getEncoder().encode(bytes));
    }
    private static String toHex(byte[] bytes) {
        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }
    //0010 0001
    public static void main(String[] args) {
        System.out.println((33 & 0xf0) >> 4);
        System.out.println(33 & 0x0f);
    }
}
