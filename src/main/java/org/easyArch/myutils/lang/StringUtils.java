package org.easyArch.myutils.lang;

/**
 * Description :
 * Created by code4j on 16-10-30
 * abbreviate("0123456789",0,10)
 * ^
 * |
 * 上午1:24
 */

public class StringUtils {

    public static String headAbbreviate(final String src, int begin, int ellipsisLength) {
        final int length = src.length();
        if (src == null || src.isEmpty() || begin > length - ellipsisLength) {
            return src;
        }
        String ellipsis = "...";
        if (ellipsisLength > 3) {
            for (int index = 0; index < ellipsisLength - 3; index++) {
                ellipsis.concat(".");
            }
        }
        String newStr = src.substring(0, begin) + ellipsis;
        return newStr;
    }

    public static String tailAbbreviate(int end, int ellipsisLength, final String src) {
        final int length = src.length();
        if (src == null || src.isEmpty() || length - end < ellipsisLength) {
            return src;
        }
        String ellipsis = "...";
        if (ellipsisLength > 3) {
            for (int index = 0; index < ellipsisLength - 3; index++) {
                ellipsis.concat(".");
            }
        }
        String newStr = ellipsis + src.substring(length - end, length);
        return newStr;
    }

    public static String capitalize(final String str) {
        int length = str.length();
        if (str == null || length == 0) {
            return str;
        }
        char first = str.charAt(0);
        if (Character.isTitleCase(first)) {
            return str;
        }
        return String.valueOf(Character.toTitleCase(first))
                .concat(str.substring(1));
    }

    public static String append(String src, String plugin) {
        return src.concat(plugin);
    }

    public static String preAppend(String src, String plugin) {
        return plugin.concat(src);
    }

    public static String center(String src, int pad, String symbol) {
        if (src == null || src.isEmpty() || pad <= 0) {
            return src;
        }
        String symbolDump = "";
        for (int index = 0; index < pad; index++) {
            symbolDump += symbol;
        }
        return append(preAppend(src, symbolDump), symbolDump);
    }

    public static String center(String src, int pad) {
        return center(src, pad, " ");
    }

    public static String trimAll(String src) {
        String[] slices = src.split(" ");
        StringBuffer buffer = new StringBuffer();
        for (String str : slices) {
            buffer.append(str);
        }
        return buffer.toString();
    }

    public static String trimToEmpty(final String str) {
        return str == null ? "" : str.trim();
    }

    public static String insert(final String src, String plugin, int index) {
        if (index == 0) {
            return preAppend(src, plugin);
        }
        if (index == src.length()) {
            System.out.println("append!!");
            return append(src, plugin);
        }
        String midstr = src.substring(0, index - 1);
        StringBuffer buffer = new StringBuffer(midstr);
        return buffer.append(plugin).append(
                src.substring(index - 1, src.length())).toString();
    }

    public static String difference(String str1, String str2) {
        if (str1 == null) {
            return str2;
        }
        if (str2 == null) {
            return str1;
        }
        final int at = indexOfDifference(str1, str2);
        if (at == -1) {
            return "";
        }
        String diff = str2.substring(at);
        if (diff.isEmpty()) {
            diff = difference(str2, str1);
        }
        return diff;
    }

    private static int indexOfDifference(final CharSequence cs1, final CharSequence cs2) {
        if (cs1 == cs2) {
            return -1;
        }
        if (cs1 == null || cs2 == null) {
            return 0;
        }
        int i;
        for (i = 0; i < cs1.length() && i < cs2.length(); ++i) {
            if (cs1.charAt(i) != cs2.charAt(i)) {
                break;
            }
        }
        if (i < cs2.length() || i < cs1.length()) {
            return i;
        }
        return -1;
    }

    public static String reverse(final String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return new StringBuffer(str).reverse().toString();
    }

    public static boolean isPalindrom(String src) {
        return new StringBuffer(src).reverse()
                .toString().equals(src);
    }

    public static boolean isPalindromIgnoreCase(String src) {
        return new StringBuffer(src).reverse()
                .toString().equalsIgnoreCase(src);
    }

    private static int[] next(char[] t) {
        int[] next = new int[t.length];
        next[0] = -1;
        int i = 0;
        int j = -1;
        while (i < t.length - 1) {
            if (j == -1 || t[i] == t[j]) {
                i++;
                j++;
                if (t[i] != t[j]) {
                    next[i] = j;
                } else {
                    next[i] = next[j];
                }
            } else {
                j = next[j];
            }
        }
        return next;
    }

    /**
     * KMP匹配字符串
     *
     * @param s 主串
     * @param t 模式串
     * @return 若匹配成功，返回下标，否则返回-1
     */
    public static int kmp(String s, String t) {
        int[] next = next(t.toCharArray());
        int i = 0;
        int j = 0;
        while (i <= s.length() - 1 && j <= t.length() - 1) {
            if (j == -1 || s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j < t.length()) {
            return -1;
        } else
            return i - t.length(); // 返回模式串在主串中的头下标
    }

    public static String join(String[] srcs, String separator, boolean edge) {
        StringBuffer buffer = new StringBuffer();
        int index = 0;
        for (String src : srcs) {
            if (edge && index == 0) {
                buffer.append(separator);
            }
            buffer.append(src);
            if (index != srcs.length - 1 ||
                    (index == srcs.length - 1 && edge))
                buffer.append(separator);
            index++;
        }
        return buffer.toString();
    }

    public static String join(String[] srcs, String separator) {
        return join(srcs, separator, false);
    }

    public static String replaceAccurate(String src, String target, String replace) {
        int begin = kmp(src, target);
        StringBuffer buffer = new StringBuffer();
        return buffer.append(src.substring(0, begin)).append(replace).
                append(src.substring(target.length() + begin, src.length())).toString();
    }

    public static String hightLightAccurate(final String src, String key, String symbol) {
        int begin = 0;
        int end = 0;
        String result = src;
        String sr = src;
        String replaceChar = "";
        for (int index = 0; index < key.length(); index++) {
            replaceChar += " ";
        }
        int n = 0;
        while (true) {
            begin = kmp(sr, key);
            if (begin == -1)
                break;
            end = key.length() + begin;
            sr = replaceAccurate(sr, key, replaceChar);
            result = insert(result, symbol, begin + symbol.length() * n + 1);
            n++;
            result = insert(result, symbol, end + symbol.length() * n + 1);
            n++;
        }
        return result;
    }

    public static void main(String[] args) {
//        String[] arrays = {"aaa","bbb","ccc"};
//        System.out.println(kmp("abcfdeabcffffabc","abc"));replaceAccurate("12ab345678ab90", "ab", "")
        System.out.println(hightLightAccurate("12ab345678ab90", "ab", "<#>"));
//        System.out.println(insert("12<b>ab<b>345678<b>ab90","<b>",22));
    }
}
