package org.easyarch.myutils.algorithm.sort.struct.link;

import org.easyarch.myutils.algorithm.struct.link.DoubleWayLinkList;

/**
 * Created by xingtianyu on 2018/3/25.
 */
public class TestLinklist {
    public static void main(String[] args) {
        DoubleWayLinkList<Integer> list = new DoubleWayLinkList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));
        System.out.println(list.get(4));
        System.out.println(list.get(5));
        System.out.println(list);
    }
}
