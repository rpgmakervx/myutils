package org.easyarch.myutils.algorithm.struct.tree.btree;

/**
 * Created by xingtianyu on 2018/3/26.
 */
public class BSTree<E extends Comparable> {

    private TreeNode<E> root ;

    public void add(E elem){
        if (elem == null){
            return;
        }
        add(root,elem);
    }

    private void add(TreeNode currentNode,E elem){
        if (root == null){
            root = new TreeNode<>(null,elem,null,null);
            return ;
        }
        if (elem.compareTo(currentNode.elem) > 0){
            if (currentNode.right == null){
                currentNode.right = new TreeNode(null,elem,null,currentNode);
            }else{
                add(currentNode.right,elem);
            }
        }else{
            if (currentNode.left == null){
                currentNode.left = new TreeNode(null,elem,null,currentNode);
            }else{
                add(currentNode.left,elem);
            }
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

    private E find(TreeNode currentNode,E elem){
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

        public TreeNode(TreeNode left,E elem,TreeNode right,TreeNode parent){
            this.left = left;
            this.elem = elem;
            this.right = right;
            this.parent = parent;
        }
    }
}
