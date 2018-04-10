package org.easyarch.myutils.algorithm.tree.btree;

import sun.reflect.generics.tree.Tree;

/**
 * @author xingtianyu(code4j) Created on 2018-4-10.
 */
public class AVLTree<E extends Comparable> {

    private TreeNode<E> root;

    public void add(E elem){
        if (elem == null){
            return ;
        }
        TreeNode<E> parent = add(this.root,elem);
        rebalance(parent);
    }


    private TreeNode<E> add(TreeNode currentNode, E elem){
        if (root == null){
            root = new TreeNode<>(null,elem,null,null);
            return null;
        }
        if (elem.compareTo(currentNode.elem) > 0){
            if (currentNode.right == null){
                currentNode.right = new TreeNode(null,elem,null,currentNode);
                return currentNode;
            }else{
                return add(currentNode.right,elem);
            }
        }else{
            if (currentNode.left == null){
                currentNode.left = new TreeNode(null,elem,null,currentNode);
                return currentNode;
            }else{
                return add(currentNode.left,elem);
            }
        }
    }

    private void rebalance(TreeNode<E> currentNode){

    }

    /**
     * 当前节点向左旋转
     * @param currentNode
     */
    private void leftRotate(TreeNode<E> currentNode){
        TreeNode<E> cRight = currentNode.right;
        TreeNode<E> cParent = currentNode.parent;
        if (cParent != null){
            cRight.parent = cParent;
            cParent.right = cRight;
        }else{
            cRight.parent = null;
            this.root = cRight;
        }
        cRight.left = currentNode;
        if (cRight.left != null){
            currentNode.right = cRight.left;
            cRight.left.parent = currentNode;
        }
        currentNode.parent = cRight;
        currentNode.right = null;
    }

    /**
     * 当前节点向右旋转
     * @param currentNode
     */
    private void rightRotate(TreeNode<E> currentNode){
        TreeNode<E> cLeft = currentNode.left;
        TreeNode<E> cParent = currentNode.parent;
        //当前节点不是root节点,则直接把当前节点的父作为左子树的父，把父节点的左子树指向当前节点左子树
        if (cParent != null){
            cParent.left = cLeft;
            cLeft.parent = cParent;
        }else{
            cLeft.parent = null;
            this.root = cLeft;
        }
        cLeft.right = currentNode;
        if (cLeft.right != null){
            currentNode.left = cLeft.right;
            cLeft.right.parent = currentNode;
        }
        currentNode.parent = cLeft;
        currentNode.left = null;
    }

    public E find(E elem){
        if (elem == null){
            return null;
        }
        if (root == null){
            return null;
        }
        TreeNode currentNode = root;
        return find(currentNode,elem);
    }

    private E find(TreeNode currentNode, E elem){
        if (currentNode == null){
            return null;
        }
        if (elem.equals(currentNode.elem)){
            return (E) currentNode.elem;
        }
        if (elem.compareTo(currentNode.elem)>0){
            currentNode = currentNode.right;
        }else if (elem.compareTo(currentNode.elem)<0){
            currentNode = currentNode.left;
        }else{
            return (E) currentNode.elem;
        }
        return find(currentNode,elem);
    }

    public class TreeNode<E extends Comparable>{

        public TreeNode<E> left;

        public TreeNode<E> right;

        public TreeNode<E> parent;

        private E elem;

        public TreeNode(TreeNode left, E elem, TreeNode right, TreeNode parent){
            this.left = left;
            this.elem = elem;
            this.right = right;
            this.parent = parent;
        }
    }
}
