package org.easyarch.myutils.leetcode.p104;

/**
 * Created by xingtianyu on 2018/9/9.
 */
public class Solution1 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode t8 = new TreeNode(8);
        TreeNode t5 = new TreeNode(5);
        TreeNode t3 = new TreeNode(5);
        TreeNode t11 = new TreeNode(5);
        TreeNode t7 = new TreeNode(5);
        TreeNode t17 = new TreeNode(5);
        TreeNode t10 = new TreeNode(5);
        TreeNode t20 = new TreeNode(5);
        t8.right = t10;
//        t8.

//        System.out.println(maxDepth());
    }

    public static int maxDepth(TreeNode root){
        return maxDepth(root,0);
    }

    public static int maxDepth(TreeNode root,int deep) {
        if (root.left == null && root.right == null){
            return deep;
        }
        deep++;
        return Math.max(maxDepth(root.left,deep),maxDepth(root.right,deep));
    }

}
