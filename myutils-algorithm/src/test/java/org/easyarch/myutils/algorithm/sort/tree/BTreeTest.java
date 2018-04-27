package org.easyarch.myutils.algorithm.sort.tree;

import org.easyarch.myutils.algorithm.struct.tree.btree.RBTree;

/**
 * Created by xingtianyu on 2018/4/18.
 */
public class BTreeTest {

    public static void main(String[] args) {
        RBTree<User> tree = new RBTree<>();
        tree.add(new User("hhh",65));
        tree.add(new User("hhh",93));
        tree.add(new User("hhh",82));
        tree.add(new User("ggg",15));
        tree.add(new User("fff",55));
        tree.add(new User("eee",16));
        tree.add(new User("ddd",22));
        tree.add(new User("ccc",104));
        tree.add(new User("bbb",72));
        tree.add(new User("aaa",32));

        tree.add(new User("aaa",81));
        tree.add(new User("aaa",45));
        tree.add(new User("aaa",69));
        tree.add(new User("aaa",97));
        tree.add(new User("aaa",5));
        tree.add(new User("aaa",117));
//
//        tree.remove(new User("xx",4));

        tree.iterate();
    }
}
