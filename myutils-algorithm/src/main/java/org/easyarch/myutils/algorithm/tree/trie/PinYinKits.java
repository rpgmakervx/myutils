package org.easyarch.myutils.algorithm.tree.trie;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.easyarch.myutils.lang.StringUtils;

/**
 * @author xingtianyu(code4j)
 *         Created by xingtianyu(code4j) on 2017-10-22.
 */
public class PinYinKits {

    public static HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

    static {
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(PinYinKits.toPinYin("58到家"));
    }

    public static String toPinYin(String source) {
        if (StringUtils.isBlank(source)){
            return null;
        }
        StringBuilder builder = new StringBuilder();
        char[] chars = source.toCharArray();
        for (char ch:chars){
            if (!isChinese(ch)){
                builder.append(ch);
                continue;
            }
            String[] pinyin = new String[0];
            try {
                pinyin = PinyinHelper.toHanyuPinyinStringArray(ch,format);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
            if (pinyin != null && pinyin.length != 0){
                builder.append(pinyin[0]);
            }
        }
        return builder.toString();
    }

    private static boolean isChinese(char c) {
        return ((c >= 0x4e00) && (c <= 0x9fbb));
    }
}
