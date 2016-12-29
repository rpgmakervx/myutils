package org.easyarch.myutils.test.lang;

import org.easyarch.myutils.lang.StringUtils;
import org.junit.Test;

import static org.easyarch.myutils.lang.StringUtils.tagKeyWord;

/**
 * Description :
 * Created by xingtianyu on 16-12-30
 * 上午12:00
 * description:
 */

public class TestString {

    @Test
    public void testAppend(){
        System.out.println(StringUtils.append("aaabbb","ccc"));
        System.out.println(StringUtils.preAppend("ccc","aaabbb"));
    }

    @Test
    public void testCapital(){
        System.out.println(StringUtils.capitalize("aaabbbccc"));
    }

    @Test
    public void testJoin(){
        System.out.println(StringUtils.join(new String[]{"aaa","bbb","ccc"},"#"));
    }

    @Test
    public void testKeyWord(){
        System.out.println(tagKeyWord("this book told about javaweb programming","java","<br>","</br>"));
    }

    @Test
    public void testDifference(){
        System.out.println(StringUtils.difference("aaabbb","aaabbbccc"));
    }

    @Test
    public void testCenter(){
        System.out.println(StringUtils.center("aaabbbccc",2,"#"));
        System.out.println(StringUtils.center("aaabbbccc",5));
    }

    @Test
    public void testInsert(){
        System.out.println(StringUtils.insert("aaaccc","bbb",4));
    }

    @Test
    public void testKMP(){
        System.out.println(StringUtils.kmp("aaabbbccc","abbbccc"));
    }

    @Test
    public void testPalindrom(){
        System.out.println(StringUtils.isPalindrom("aaabaaacaaabaaa"));
        System.out.println(StringUtils.isPalindrom("aaabbaacaaabaaa"));
        System.out.println(StringUtils.isPalindromIgnoreCase("aaabAAAcaaabAAA"));
    }

    @Test
    public void testReverse(){
        System.out.println(StringUtils.reverse("abcdefg"));
    }

    @Test
    public void testAbbreviate(){
        String message = "this is an message digest";
        System.out.println(StringUtils.headAbbreviate(message,message.length()-10,5));
        System.out.println(StringUtils.tailAbbreviate(message,message.length()-10,5));
    }

    @Test
    public void testTrim(){
        System.out.println(StringUtils.trimAll(" aaa  bbbb  ccc  "));
        System.out.println(StringUtils.trimToEmpty(null));
    }

    @Test
    public void testRandom(){
        System.out.println(StringUtils.randomNumeric(10));
        System.out.println(StringUtils.randomString(10));
        System.out.println(StringUtils.uuid());
    }
}
