package org.easyarch.myutils.lang;/**
 * Description : 
 * Created by YangZH on 16-10-31
 *  上午9:43
 */

/**
 * Description :
 * Created by code4j on 16-10-31
 * 上午9:43
 */

public class NumberUtils {

    public static boolean isNumber(final String str) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(str)) {
            return false;
        }
        final char[] chars = str.toCharArray();
        int sz = chars.length;
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;
        // deal with any possible sign up front
        final int start = (chars[0] == '-') ? 1 : 0;
        if (sz > start + 1 && chars[start] == '0') { // leading 0
            if ((chars[start + 1] == 'x') ||
                    (chars[start + 1] == 'X')
                    ) { // leading 0x/0X
                int i = start + 2;
                if (i == sz) {
                    return false; // str == "0x"
                }
                // checking hex (it can't be anything else)
                for (; i < chars.length; i++) {
                    if ((chars[i] < '0' || chars[i] > '9')
                            && (chars[i] < 'a' || chars[i] > 'f')
                            && (chars[i] < 'A' || chars[i] > 'F')) {
                        return false;
                    }
                }
                return true;
            } else if (Character.isDigit(chars[start + 1])) {
                // leading 0, but not hex, must be octal
                int i = start + 1;
                for (; i < chars.length; i++) {
                    if (chars[i] < '0' || chars[i] > '7') {
                        return false;
                    }
                }
                return true;
            }
        }
        sz--; // don't want to loop to the last char, check it afterwords
        // for type qualifiers
        int i = start;
        // loop to the next to last char or to the last char if we need another digit to
        // make a valid number (e.g. chars[0..5] = "1234E")
        while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                foundDigit = true;
                allowSigns = false;

            } else if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points or dec in exponent
                    return false;
                }
                hasDecPoint = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                // we've already taken care of hex.
                if (hasExp) {
                    // two E's
                    return false;
                }
                if (!foundDigit) {
                    return false;
                }
                hasExp = true;
                allowSigns = true;
            } else if (chars[i] == '+' || chars[i] == '-') {
                if (!allowSigns) {
                    return false;
                }
                allowSigns = false;
                foundDigit = false; // we need a digit after the E
            } else {
                return false;
            }
            i++;
        }
        if (i < chars.length) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                // no type qualifier, OK
                return true;
            }
            if (chars[i] == 'e' || chars[i] == 'E') {
                // can't have an E at the last byte
                return false;
            }
            if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points or dec in exponent
                    return false;
                }
                // single trailing decimal point after non-exponent is ok
                return foundDigit;
            }
            if (!allowSigns
                    && (chars[i] == 'd'
                    || chars[i] == 'D'
                    || chars[i] == 'f'
                    || chars[i] == 'F')) {
                return foundDigit;
            }
            if (chars[i] == 'l'
                    || chars[i] == 'L') {
                // not allowing L with an exponent or decimal point
                return foundDigit && !hasExp && !hasDecPoint;
            }
            // last character is illegal
            return false;
        }
        return !allowSigns && foundDigit;
    }

    public static boolean isDigist(final String number) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(number)) {
            return false;
        }
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static Integer toInt(final String number, int defaultValue) {
        try {
            return StringUtils.isEmpty(number) ? defaultValue : Integer.parseInt(number);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static Integer toInt(final String number) {
        return toInt(number, 0);
    }

    public static Float toFloat(final String number, float defaultValue) {
        try {
            return StringUtils.isEmpty(number) ? defaultValue : Float.parseFloat(number);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static Float toFloat(final String number) {
        return toFloat(number, 0f);
    }

    public static Double toDouble(final String number, double defaultValue) {
        try {
            return StringUtils.isEmpty(number) ? defaultValue : Double.parseDouble(number);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static Double toDouble(final String number) {
        return toDouble(number, 0.0d);
    }

    public static Long toLong(final String number,long defaultValue){
        try {
            return StringUtils.isEmpty(number) ? defaultValue : Long.parseLong(number);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }
    public static Long toLong(final String number){
        return toLong(number);
    }

    public static Byte toByte(final String number, byte defaultValue) {
        try {
            return StringUtils.isEmpty(number) ? defaultValue : Byte.parseByte(number);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static Byte toByte(final String number) {
        return toByte(number, (byte) 0);
    }

    public static Short toShort(final String number, short defaultValue) {
        try {
            return StringUtils.isEmpty(number) ? defaultValue : Short.parseShort(number);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static Short toShort(final String number) {
        return toShort(number, (short) 0);
    }


    public static int max(final int... array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        int max = array[1];
        for (int index = 0; index < array.length; index++) {
            if (array[index] > max) {
                max = array[index];
            }
        }
        return max;
    }

    public static float max(final float... array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        float max = array[1];
        for (int index = 0; index < array.length; index++) {
            if (array[index] > max) {
                max = array[index];
            }
        }
        return max;
    }

    public static double max(final double... array){
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        double max = array[1];
        for (int index = 0; index < array.length; index++) {
            if (array[index] > max) {
                max = array[index];
            }
        }
        return max;
    }

    public static long max(final long... array){
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        long max = array[1];
        for (int index = 0; index < array.length; index++) {
            if (array[index] > max) {
                max = array[index];
            }
        }
        return max;
    }

    public static short max(final short... array){
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        short max = array[1];
        for (int index = 0; index < array.length; index++) {
            if (array[index] > max) {
                max = array[index];
            }
        }
        return max;
    }

    public static byte max(final byte... array){
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        byte max = array[1];
        for (int index = 0; index < array.length; index++) {
            if (array[index] > max) {
                max = array[index];
            }
        }
        return max;
    }

    public static int min(final int...array){
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        int min = array[1];
        for (int index = 0; index < array.length; index++) {
            if (array[index] < min) {
                min = array[index];
            }
        }
        return min;
    }

    public static float min(final float... array){
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        float min = array[1];
        for (int index = 0; index < array.length; index++) {
            if (array[index] < min) {
                min = array[index];
            }
        }
        return min;
    }

    public static double min(final double... array){
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        double min = array[1];
        for (int index = 0; index < array.length; index++) {
            if (array[index] < min) {
                min = array[index];
            }
        }
        return min;
    }

    public static long min(final long... array){
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        long min = array[1];
        for (int index = 0; index < array.length; index++) {
            if (array[index] < min) {
                min = array[index];
            }
        }
        return min;
    }

    public static short min(final short... array){
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        short min = array[1];
        for (int index = 0; index < array.length; index++) {
            if (array[index] < min) {
                min = array[index];
            }
        }
        return min;
    }
    public static byte min(final byte... array){
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        byte min = array[1];
        for (int index = 0; index < array.length; index++) {
            if (array[index] < min) {
                min = array[index];
            }
        }
        return min;
    }

    public static void main(String[] args) {

        System.out.println(max(new int[]{5, 9, 2, 10, 4, 0, 7}));
    }

}
