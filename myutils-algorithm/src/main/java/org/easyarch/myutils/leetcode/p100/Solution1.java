package org.easyarch.myutils.leetcode.p100;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingtianyu on 2018/9/7.
 * 相同的树（中序遍历相等）
 */
public class Solution1 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 1;
        System.out.println(a.intValue() == b);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        List<Integer> plist = new ArrayList<>();
        List<Integer> qlist = new ArrayList<>();
        iterate(p,plist);
        iterate(q,qlist);
        if (plist.size() != qlist.size()){
            return false;
        }
        for (int index = 0;index < plist.size();index++){
            if (plist.get(index) != null&&qlist.get(index) != null){
                if (plist.get(index).intValue() != qlist.get(index).intValue())
                    return false;
            }else if (plist.get(index) != qlist.get(index)){
                return false;
            }
        }
        return true;
    }

    public static void iterate(TreeNode node, List<Integer> array){
        if (node == null){
            array.add(null);
            return ;
        }
        array.add(node.val);
        iterate(node.left,array);
        iterate(node.right,array);
    }
}
