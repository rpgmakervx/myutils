package org.easyarch.myutils.algorithm.sort.tree;

import org.easyarch.myutils.algorithm.struct.tree.btree.RBTree;

/**
 * Created by xingtianyu on 2018/4/18.
 */
public class BTreeTest {

    public static void main(String[] args) {
        RBTree<User> tree = new RBTree<>();
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
//
//        tree.remove(new User("xx",4));

        tree.add(new User("xxx",10));
        tree.add(new User("xxx",5));
        tree.add(new User("xxx",7));
        tree.iterate();
    }
}
