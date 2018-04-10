package org.easyarch.myutils.algorithm.sort.tree;

import org.easyarch.myutils.algorithm.tree.btree.AVLTree;
import org.easyarch.myutils.algorithm.tree.btree.BSTree;

/**
 * Created by xingtianyu on 2018/3/27.
 */
public class DoubleTreeeTest {

    public static void main(String[] args) {
        AVLTree<User> tree = new AVLTree();
        tree.add(new User("aaa",20));
        tree.add(new User("bbb",17));
        tree.add(new User("ccc",11));
        tree.add(new User("ddd",18));
        tree.add(new User("eee",16));
        tree.add(new User("fff",28));
        tree.add(new User("ggg",24));
        tree.add(new User("hhh",22));
        System.out.println("find:"+tree.find(new User("sss",23)));
        tree.iterate();

    }
}
