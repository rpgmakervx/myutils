package org.easyarch.myutils.algorithm.struct.link;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xingtianyu on 2018/3/25.
 */
public class DoubleWayLinkList<E> implements Iterable<E>{

    private Node head ;
    private Node tail ;

    int length;

    public E get(int index) {
        Node<E> next = head;
        for (int i = 0;i<index;i++){
            next = next.next;
            if (next == null){
                return null;
            }
        }
        return next.elem;
    }

    public void add(E element){
        Node<E> newNode = new Node<>(null,element,null);
        length++;
        if (head == null) {
            head = newNode;
        }else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        //把插入的节点作为新的节点
        tail = newNode;
    }

    public int size() {
        return length;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    public class Node<E>{
        E elem;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev,E elem, Node<E> next) {
            this.prev = prev;
            this.elem = elem;
            this.next = next;
        }

    }

}
