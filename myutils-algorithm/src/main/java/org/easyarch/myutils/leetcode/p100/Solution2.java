package org.easyarch.myutils.leetcode.p100;

/**
 * Created by xingtianyu on 2018/9/7.
 */
public class Solution2 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null&&q==null){
            return true;
        }
        if (q == null){
            return false;
        }
        if (p == null){
            return false;
        }
        if (p.val == q.val){
            return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
        }
        return false;
    }
}
