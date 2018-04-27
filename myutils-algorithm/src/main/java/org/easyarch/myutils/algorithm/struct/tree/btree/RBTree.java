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
            this.root = new RBNode<>(NIL,elem,NIL,NIL,BLACK);
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
        if (cParent == NIL){
            System.out.println("cParent is null,root is"+this.root.elem+",current is:"+currentNode.elem);
        }
        //父节点为黑色
        if (cParent.color){
            return;
        }
        if (cParent != NIL){
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
                if (currentNode == cParent.right){
                    leftRotate(cParent);
                    cParent = currentNode;
                    cGrand = currentNode.parent;
                }
                cGrand.color = RED;
                cParent.color= BLACK;
                rightRotate(cGrand);
            }else{
                //父节点在祖节点右边
                if(currentNode == cParent.left){
                    rightRotate(cParent);
                    cParent = currentNode;
                    cGrand = currentNode.parent;
                }
                cGrand.color = RED;
                cParent.color= BLACK;
                leftRotate(cGrand);
            }
        }
    }

    private void leftRotate(RBNode<E> currentNode){
        RBNode<E> cRight = currentNode.right;
        RBNode<E> cParent = currentNode.parent;
        //父节点指向新的子节点前先判断是绑定到左子树还是右子树
        if (cParent != NIL){
            //记得判断左边或右边为空的情况，空则认为不是那一侧的
            if (cParent.left != NIL && currentNode == cParent.left){
                cParent.left = cRight;
            }else if (cParent.right != NIL && currentNode == cParent.right){
                cParent.right = cRight;
            }
            cRight.parent = cParent;
        }else{
            //当前节点为根节点的情况
            cRight.parent = NIL;
            this.root = cRight;
        }
        //当前节点包括一个左分支的情况
        if (cRight.left != NIL){
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

        if (cParent != NIL){
            if (cParent.left != NIL && currentNode == cParent.left){
                cParent.left = cLeft;
            }else if (cParent.right != NIL && currentNode == cParent.right ){
                cParent.right = cLeft;
            }
            cLeft.parent = cParent;
        }else{
            cLeft.parent = NIL;
            this.root = cLeft;
        }
        if (cLeft.right != NIL){
            currentNode.left = cLeft.right;
            cLeft.right.parent = currentNode;
        }else{
            currentNode.left = NIL;
        }
        cLeft.right = currentNode;
        currentNode.parent = cLeft;
    }

    public boolean remove(E elem){
        if (elem == null){
            return false;
        }
        if (root == null){
            return false;
        }
        RBNode<E> currentNode = root;
        return remove(currentNode,elem);
    }

    private boolean remove(RBNode<E> currentNode, E elem){
        if (currentNode == null || currentNode == NIL){
            return false;
        }
        if (elem.compareTo(currentNode.elem)>0){
            return remove(currentNode.right,elem);
        }else if (elem.compareTo(currentNode.elem)<0){
            return remove(currentNode.left,elem);
        }else{
            RBNode<E> cParent = currentNode.parent;
            //被删除节点是叶子节点
            if (currentNode.left == NIL && currentNode.right == NIL){
                if (cParent.right == currentNode){
                    cParent.right = NIL;
                }else if (cParent.left == currentNode){
                    cParent.left = NIL;
                }
                currentNode.free();
                rebalance(cParent);
                return true;
            }
            //被删除节点有左叶子
            if (currentNode.left != NIL && currentNode.right == NIL){
                RBNode<E> cLeft = currentNode.left;
                cLeft.parent = currentNode.parent;
                currentNode.parent.left = cLeft;
                currentNode.free();
                rebalance(cParent);
                return true;
            }
            //被删除节点有右叶子
            if (currentNode.left == NIL && currentNode.right != NIL){
                RBNode<E> cRight = currentNode.right;
                cRight.parent = currentNode.parent;
                currentNode.parent.right = cRight;
                currentNode.free();
                rebalance(cParent);
                return true;
            }
            //被删除节点存在左右子树
            RBNode<E> promotedNode = promoteNode(currentNode,currentNode);
            //把要删除的节点赋予被提升节点的值，然后把问题转变为：如何删除要提升的节点。
            currentNode.elem = promotedNode.elem;
            return remove(promotedNode,promotedNode.elem);
        }
    }

    private RBNode<E> promoteNode(RBNode<E> currentNode, RBNode<E> deletedNode){
        if (currentNode == null){
            return null;
        }
        if (currentNode == deletedNode){
            RBNode<E> rightNode = currentNode.right;
            if (rightNode == null || rightNode == NIL){
                return currentNode.left == null?currentNode:currentNode.left;
            }
            return promoteNode(rightNode,deletedNode);
        }
        RBNode<E> leftNode = currentNode.left;
        if (leftNode == null || leftNode == NIL){
            return currentNode;
        }
        return promoteNode(leftNode,deletedNode);
    }

    public void iterate(){
        iterate(root);
    }

    private void iterate(RBNode node){
        if (node == null||node == NIL){
            return ;
        }
        System.out.println("iterate:"+node.elem);
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

        public void free(){
            this.left = null;
            this.right = null;
            this.parent = null;
            this.elem = null;
        }

        @Override
        public String toString() {
            return "RBNode{" +
                    "left=" + left +
                    ", right=" + right +
                    ", parent=" + parent +
                    ", elem=" + elem +
                    ", color=" + color +
                    '}';
        }
    }
}
