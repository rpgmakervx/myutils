package org.easyarch.myutils.algorithm.sort.tree;

import org.easyarch.myutils.algorithm.struct.tree.btree.AVLTree;
import org.easyarch.myutils.algorithm.struct.tree.btree.RBTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xingtianyu on 2018/4/18.
 */
public class BTreeTest {

    public static void main(String[] args) {
        RBTree<Integer> rbTree = new RBTree<>();
        AVLTree<Integer> avlTree = new AVLTree<>();
//        tree.add(new User("hhh",65));
//        tree.add(new User("hhh",93));
//        tree.add(new User("hhh",82));
//        tree.add(new User("ggg",15));
//        tree.add(new User("fff",55));
//        tree.add(new User("eee",16));
//        tree.add(new User("ddd",22));
//        tree.add(new User("ccc",104));
//        tree.add(new User("bbb",72));
//        tree.add(new User("aaa",32));
//
//        tree.add(new User("aaa",81));
//        tree.add(new User("aaa",45));
//        tree.add(new User("aaa",69));
//        tree.add(new User("aaa",97));
//        tree.add(new User("aaa",5));
//        tree.add(new User("aaa",117));
//
//        tree.remove(new User("xx",4));
        List<Integer> list = new ArrayList<>();
        for (int index = 0;index < 100000;index++){
            list.add(index);
        }
        Collections.shuffle(list);
//        System.out.println(list);
        long begin = System.currentTimeMillis();
        for (Integer e:list){
            rbTree.add(e);
        }
        System.out.println("build rbTree cost:"+(System.currentTimeMillis() - begin));
        begin = System.currentTimeMillis();
        for (Integer e:list){
            avlTree.add(e);
        }
        System.out.println("build avlTree cost:"+(System.currentTimeMillis() - begin));
//        biSearch(list,48698);
//        begin = System.currentTimeMillis();
//        tree.find(48698);
//        System.out.println("tree cost:"+(System.currentTimeMillis() - begin));

//        avlTree.iterate();
//        System.out.println("--------------");
//        rbTree.iterate();
    }

    public static Integer biSearch(List<Integer> array,int a){
        int lo=0;
        int hi=array.size()-1;
        int mid;
        while(lo<=hi){
            mid=(lo+hi)/2;
            if(array.get(mid)==a){
                return mid+1;
            }else if(array.get(mid)<a){
                lo=mid+1;
            }else{
                hi=mid-1;
            }
        }
        return -1;
    }
}
