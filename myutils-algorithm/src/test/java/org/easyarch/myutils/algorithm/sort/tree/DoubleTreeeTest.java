package org.easyarch.myutils.algorithm.sort.tree;

import org.easyarch.myutils.algorithm.tree.btree.AVLTree;
import org.easyarch.myutils.algorithm.tree.btree.BSTree;

/**
 * Created by xingtianyu on 2018/3/27.
 */
public class DoubleTreeeTest {

    public static void main(String[] args) {
        AVLTree<User> tree = new AVLTree();
//        tree.add(new User("aaa",1));
//        tree.add(new User("bbb",2));
//        tree.add(new User("ccc",3));
//        tree.add(new User("ddd",4));
//        tree.add(new User("eee",5));
//        tree.add(new User("fff",6));
//        tree.add(new User("ggg",7));
//        tree.add(new User("hhh",8));
//        tree.add(new User("hhh",9));
//        tree.add(new User("hhh",10));

        tree.add(new User("hhh",10));
        tree.add(new User("hhh",9));
        tree.add(new User("hhh",8));
        tree.add(new User("ggg",7));
        tree.add(new User("fff",6));
        tree.add(new User("eee",5));
        tree.add(new User("ddd",4));
        tree.add(new User("ccc",3));
        tree.add(new User("bbb",2));
        tree.add(new User("aaa",1));
        tree.iterate();
//        System.out.println("remove:"+tree.remove(new User("sss",8)));
//        tree.iterate();

    }
}
