package org.easyarch.myutils.algorithm.struct.tree.btree;

import com.sun.org.apache.regexp.internal.RE;

/**
 * Created by xingtianyu on 2018/4/15.
 */
public class RBTree<E extends Comparable> {

    private static final boolean RED = false;

    private static final boolean BLACK = false;

    //所有叶子节点默认都是nil节点，黑色
    private final RBNode NIL = new RBNode<>(null,null,null,null,true);

    private RBNode<E> root;

    public void add(E elem){
        if (elem == null){
            return;
        }
        add(this.root,elem);
    }

    /**
     * 若根节点不存在，初始化根节点，根节点是黑色。
     *
     * @param currentNode
     * @param elem
     */
    private void add(RBNode<E> currentNode,E elem){
        if (this.root == null){
            this.root = new RBNode<>(NIL,elem,NIL,null,BLACK);
            return;
        }
        if (elem.compareTo(currentNode.elem) > 0){
            if (currentNode.right == null){
                currentNode.right = new RBNode(NIL,elem,NIL,currentNode, RED);

            }else{
                add(currentNode.right,elem);
            }
        }else if (elem.compareTo(currentNode.elem) < 0){
            if (currentNode.left == null){
                currentNode.left = new RBNode(NIL,elem,NIL,currentNode,RED);

            }else{
                add(currentNode.left,elem);
            }
        }
    }

    private void rebalance(RBNode<E> currentNode){
        //父亲节点为黑色则不作任何处理
        if (currentNode.color){
            return;
        }
        if (currentNode.parent != null){
            RBNode<E> cParent = currentNode.parent;
            if (currentNode == cParent.left){
                RBNode<E> cUncle = cParent.right;
            }else if (currentNode == cParent.right){
                RBNode<E> cUncle = cParent.left;
            }else{

            }

        }
    }

    private void leftRotate(RBNode<E> currentNode){
        RBNode<E> cRight = currentNode.right;
        RBNode<E> cParent = currentNode.parent;
        //父节点指向新的子节点前先判断是绑定到左子树还是右子树
        if (cParent != null){
            //记得判断左边或右边为空的情况，空则认为不是那一侧的
            if (cParent.left != null && currentNode.elem.compareTo(cParent.left.elem) == 0){
                cParent.left = cRight;
            }else if (cParent.right != null && currentNode.elem.compareTo(cParent.right.elem) == 0){
                cParent.right = cRight;
            }
            cRight.parent = cParent;
        }else{
            //当前节点为根节点的情况
            cRight.parent = NIL;
            this.root = cRight;
        }
        //当前节点包括一个左分支的情况
        if (cRight.left != null){
            currentNode.right = cRight.left;
            cRight.left.parent = currentNode;
        }else{
            currentNode.right = null;
        }
        cRight.left = currentNode;
        currentNode.parent = cRight;
    }

    private void rightRotate(RBNode<E> currentNode){
        RBNode<E> cLeft = currentNode.left;
        RBNode<E> cParent = currentNode.parent;

        if (cParent != null){
            if (cParent.left != null && currentNode.left.elem.compareTo(cLeft.elem) == 0){
                cParent.left = cLeft;
            }else if (cParent.right != null && currentNode.right.elem.compareTo(cLeft.elem) == 0){
                cParent.right = cLeft;
            }
            cLeft.parent = cParent;
        }else{
            cLeft.parent = NIL;
            this.root = cLeft;
        }
        if (cLeft.right != null){
            currentNode.left = cLeft.right;
            cLeft.right.parent = currentNode;
        }else{
            currentNode.parent = null;
        }
        cLeft.right = currentNode;
        currentNode.parent = cLeft;
    }

    private class RBNode<E extends Comparable> {

        private RBNode<E> left;

        private RBNode<E> right;

        private RBNode<E> parent;

        private E elem;

        //红色为false，黑色为true
        private boolean color;

        public RBNode(RBNode<E> left, E elem, RBNode<E> right, RBNode<E> parent, boolean color) {
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.elem = elem;
            this.color = color;
        }
    }
}
