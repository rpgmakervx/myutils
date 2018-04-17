package org.easyarch.myutils.algorithm.struct.tree.btree;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.regexp.internal.RE;

/**
 * Created by xingtianyu on 2018/4/15.
 */
public class RBTree<E extends Comparable> {

    private static final boolean RED = false;

    private static final boolean BLACK = true;

    //所有叶子节点默认都是nil节点，黑色
    private final RBNode NIL = new RBNode<>(null,null,null,null,BLACK);

    private RBNode<E> root;

    public void add(E elem){
        System.out.println("add elem:"+elem);
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
            if (currentNode.right == NIL){
                currentNode.right = new RBNode(NIL,elem,NIL,currentNode, RED);
                rebalance(currentNode.right);
            }else{
                add(currentNode.right,elem);
            }
        }else if (elem.compareTo(currentNode.elem) < 0){
            if (currentNode.left == NIL){
                currentNode.left = new RBNode(NIL,elem,NIL,currentNode,RED);
                rebalance(currentNode.left);
            }else{
                add(currentNode.left,elem);
            }
        }
    }

    /**
     * @param currentNode 表示当前判定节点
     */
    private void rebalance(RBNode<E> currentNode){
        if (currentNode == this.root) {
            //根节点为红色
            if (!this.root.color){
                this.root.color = BLACK;
            }
            return;
        }
        RBNode<E> cParent = currentNode.parent;
        if (cParent == null){
            System.out.println("cParent is null,root is"+this.root.elem+",current is:"+currentNode.elem);
        }
        //父节点为黑色
        if (cParent.color){
            return;
        }
        if (cParent != null){
            //黑父，无需处理
            if (cParent.color){
                return;
            }
            RBNode<E> cUncle;
            RBNode<E> cGrand = cParent.parent;
            if (cGrand.left == cParent){
                cUncle = cGrand.right;
            }else{
                cUncle = cGrand.left;
            }
            //叔节点是红色
            if (!cUncle.color){
                cParent.color = BLACK;
                cGrand.color = RED;
                cUncle.color = BLACK;
                rebalance(cGrand);
                return;
            }
            //叔节点是黑色
            if (cParent == cGrand.left){
                //父节点在祖节点左边
                if (currentNode == cParent.left){
                    //新节点在父节点左边，此时右旋。祖父变红，父亲变黑，当前节点仍然是红色
                    currentNode.color = RED;
                    cGrand.color = RED;
                    cParent.color= BLACK;
                    rightRotate(cGrand);
                }else{
                    //新节点在父节点右边，先左旋再右旋。祖父变红，当前节点变黑，父亲仍然是红色
                    cParent.color = RED;
                    cGrand.color = RED;
                    currentNode.color = BLACK;
                    leftRotate(cParent);
                    rightRotate(cGrand);
                }
            }else{
                //父节点在祖节点右边
                if(currentNode == cParent.right){
                    //新节点在父节点右边，此时左旋.
                    cGrand.color = RED;
                    cParent.color= BLACK;
                    currentNode.color = RED;
                    leftRotate(cGrand);
                }else{
                    //新节点在父节点左边，先右旋再左旋
                    cParent.color = RED;
                    cGrand.color = RED;
                    currentNode.color = BLACK;
                    rightRotate(cParent);
                    leftRotate(cGrand);
                }
            }
        }
    }
//
//    private void rebalance(RBNode<E> currentNode,RBNode<E> newNode){
//        //父亲节点为黑色则不作任何处理
//        if (currentNode.color){
//            return;
//        }
//        if (currentNode == this.root){
//            //根节点为红色
//            if (!this.root.color){
//               this.root.color = BLACK;
//            }
//        }
//        if (currentNode.parent != null){
//            RBNode<E> cParent = currentNode.parent;
//            //黑父，无需处理
//            if (currentNode.color){
//                return ;
//            }
//            //父是红色，此时要看叔节点的颜色
//            RBNode<E> cUncle;
//            if (currentNode == cParent.left){
//                cUncle = cParent.right;
//            }else{
//                cUncle = cParent.left;
//            }
//            //叔节点是红色
//            if (!cUncle.color){
//                currentNode.color = BLACK;
//                cParent.color = RED;
//                cUncle.color = BLACK;
//                rebalance(cParent,newNode);
//                return;
//            }
//            //叔节点是黑色
//            if (currentNode == cParent.left){
//                //父节点在祖节点左边
//                if (newNode == currentNode.left){
//                    //新节点在父节点左边，此时右旋。祖父变红，父亲变黑，当前节点仍然是红色
//                    cParent.color = RED;
//                    currentNode.color= BLACK;
//                    newNode.color = RED;
//                    rightRotate(cParent);
//                }else{
//                    //新节点在父节点右边，先左旋再右旋。祖父变红，当前节点变黑，父亲仍然是红色
//                    cParent.color = RED;
//                    newNode.color = BLACK;
//                    currentNode.color = RED;
//                    leftRotate(currentNode);
//                    rightRotate(cParent);
//                }
//            }else{
//                //父节点在祖节点右边
//                if(newNode == currentNode.right){
//                    //新节点在父节点右边，此时左旋.
//                    cParent.color = RED;
//                    currentNode.color= BLACK;
//                    newNode.color = RED;
//                    leftRotate(cParent);
//                }else{
//                    //新节点在父节点左边，先右旋再左旋
//                    cParent.color = RED;
//                    newNode.color = BLACK;
//                    currentNode.color = RED;
//                    rightRotate(currentNode);
//                    leftRotate(cParent);
//                }
//            }
//        }
//    }

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
            cRight.parent = null;
            this.root = cRight;
        }
        //当前节点包括一个左分支的情况
        if (cRight.left != null){
            currentNode.right = cRight.left;
            cRight.left.parent = currentNode;
        }else{
            currentNode.right = NIL;
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
            currentNode.parent = NIL;
        }
        cLeft.right = currentNode;
        currentNode.parent = cLeft;
    }

    public void iterate(){
        iterate(root);
    }

    private void iterate(RBNode node){
        if (node == null){
            return ;
        }
        System.out.println(node.elem);
        iterate(node.left);
        iterate(node.right);
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
