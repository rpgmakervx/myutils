package org.easyarch.myutils.test;

import org.easyarch.myutils.lang.NumberUtils;

/**
 * Description :
 * Created by xingtianyu on 16-12-8
 * 下午11:46
 */

public class Main {

    public static void main(String[] args) {
//        List<String> list = CollectionUtil.newArrayList(
//                        "I","have","a","pen","I","have","an","apple","oh","apple","pen");
//        List<Object> newList = list.stream().map(new Function<String, String>() {
//            public String apply(String s) {
//                return s+" 0001";
//            }
//        }).collect(Collectors.toList());
//        System.out.println(newList);
        System.out.println(NumberUtils.getMaxCommonDivisor(8,16,20));;
    }
}
