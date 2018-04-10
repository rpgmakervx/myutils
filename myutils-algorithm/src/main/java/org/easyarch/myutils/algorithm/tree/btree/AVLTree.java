package org.easyarch.myutils.algorithm.tree.btree;

import sun.reflect.generics.tree.Tree;

import java.util.Map;

/**
 * @author xingtianyu(code4j) Created on 2018-4-10.
 */
public class AVLTree<E extends Comparable> {

    private static final int THRESHOLD = 2;

    private static final int EMPTY = 0;

    private TreeNode<E> root;

    public void add(E elem){
        if (elem == null){
            return ;
        }
        TreeNode<E> parent = add(this.root,elem);
        if (parent == null){
            System.out.println("current is root,"+elem);
        }else{
            System.out.println("add elem is:"+elem+" and parent is "+parent.elem);
        }
    }


    private TreeNode<E> add(TreeNode<E> currentNode, E elem){
        if (root == null){
            root = new TreeNode<>(null,elem,null,null);
            return null;
        }
        if (elem.compareTo(currentNode.elem) > 0){
            if (currentNode.right == null){
                currentNode.right = new TreeNode(null,elem,null,currentNode);
                rebalance(currentNode,currentNode.right);
                return currentNode;
            }else{
                return add(currentNode.right,elem);
            }
        }else{
            if (currentNode.left == null){
                currentNode.left = new TreeNode(null,elem,null,currentNode);
                rebalance(currentNode,currentNode.left);
                return currentNode;
            }else{
                return add(currentNode.left,elem);
            }
        }
    }

    private void rebalance(TreeNode<E> currentNode,TreeNode<E> newNode){
        if (currentNode == null||newNode == null){
            return;
        }
        int lDeep = deep(currentNode.left);
        int rDeep = deep(currentNode.right);
        int factor = lDeep - rDeep;
        // already balanced
        if (Math.abs(factor) < THRESHOLD){
            rebalance(currentNode.parent,newNode);
            return ;
        }
        //左深右浅，LL/LR
        if (factor >= THRESHOLD){
            if (newNode.elem.compareTo(currentNode.elem) > 0){
                //LR，先左旋再右旋
                leftRotate(currentNode);
                rightRotate(currentNode);
            }else{
               //LL
                rightRotate(currentNode);
            }
            return ;
        }
        if (factor <= -THRESHOLD){
            if (newNode.elem.compareTo(currentNode.elem) > 0){
                //RL，先右旋再左旋
                rightRotate(currentNode);
                leftRotate(currentNode);
            }else{
                //RR
                leftRotate(currentNode);
            }
            return;
        }

    }

    /**
     * 当前节点向左旋转
     * @param currentNode
     */
    private void leftRotate(TreeNode<E> currentNode){
        TreeNode<E> cRight = currentNode.right;
        TreeNode<E> cParent = currentNode.parent;
        //父节点指向新的子节点前先判断是绑定到左子树还是右子树
        if (cParent != null){
            if (cParent.left.elem.compareTo(cRight.elem) == 0){
                cParent.right = cRight;
            }else if (cParent.right.elem.compareTo(cRight.elem) == 0){
                cParent.left = cRight;
            }
            cRight.parent = cParent;
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
        //当前节点不是root节点,则直接把当前节点的父作为左子树的父，把父节点的左子树指向当前节点左子树.否则左子树作为root节点
        if (cParent != null){
            if (cParent.left.elem.compareTo(currentNode.elem) == 0){
                cParent.left = cLeft;
            }else if (cParent.right.elem.compareTo(currentNode.elem) == 0){
                cParent.right = cLeft;
            }
            cLeft.parent = cParent;
        }else{
            cLeft.parent = null;
            this.root = cLeft;
        }
        if (cLeft.right != null){
            currentNode.left = cLeft.right;
            cLeft.right.parent = currentNode;
        }
        cLeft.right = currentNode;
        currentNode.parent = cLeft;
        currentNode.left = null;
    }

    private int deep(TreeNode<E> currentNode){
        if (currentNode == null){
            return EMPTY;
        }
        if (currentNode.right == null && currentNode.left == null){
            return 1;
        }
        return 1+ Math.max(deep(currentNode.left),deep(currentNode.right));
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
        if (elem.compareTo(currentNode.elem) == 0){
            return (E) currentNode.elem;
        }
        if (elem.compareTo(currentNode.elem)>0){
            return find(currentNode.right,elem);
        }else if (elem.compareTo(currentNode.elem)<0){
            return find(currentNode.left,elem);
        }else{
            return (E) currentNode.elem;
        }
    }

    public void iterate(){
        iterate(root);
    }

    private void iterate(TreeNode node){
        if (node == null){
            return ;
        }
        System.out.println(node.elem);
        iterate(node.left);
        iterate(node.right);
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
