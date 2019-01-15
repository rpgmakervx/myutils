package org.easyarch.myutils.algorithm.struct.link;

/**
 * Created by xingtianyu on 2019/1/15.
 */
public class LinkedList<T> {


    private Node<T> head;

    private Node<T> tail;

    public LinkedList() {
        this.head = new Node<>(null,null,this.tail);
        this.tail = new Node<>(this.head,null,null);
    }

    public T get(int index) {
        Node<T> tmp = this.head.next;
        for (int i = 0; i < index; i++) {
            if (tmp == null) {
                return null;
            }
            tmp = tmp.next;
        }
        return tmp.data;
    }

    public void add(T data){
        Node<T> newNode = new Node<>(this.head,data,this.tail);
        if (this.head.next == null){
            this.head.next = newNode;
            this.tail.prev = newNode;
            return ;
        }
        Node<T> prev = this.tail.prev;
        prev.next = newNode;
        newNode.prev = prev;
        this.tail.prev = newNode;
        newNode.next = this.tail;
    }

    /**
     * 链表反转
     *
     */
    public void reverse(){
        Node<T> pos = this.head.next;
        while (pos != this.tail){
            Node<T> prev = pos.prev;
            Node<T> next = pos.next;
            pos.prev = next;
            pos.next = prev;
            pos = pos.prev;
        }
        //切换头和尾的职责。
        Node<T> next = this.head.next;
        Node<T> prev = this.tail.prev;
        this.head.next = prev;
        prev.prev = this.head;
        this.tail.prev = next;
        next.next = this.tail;
    }

    public int find(T data){
        Node<T> tmp = this.head.next;
        int index = 0;
        while (tmp != this.tail){
            if (tmp.data.equals(data)){
                return index;
            }
            index++;
            tmp = tmp.next;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        Node<T> tmp = this.head.next;
        buffer.append("[");
        while (tmp != this.tail){
            buffer.append(tmp.data);
            if (tmp.next != this.tail){
                buffer.append(", ");
            }
            tmp = tmp.next;
        }
        buffer.append("]");
        return buffer.toString();
    }

    class Node<T> {

        Node<T> prev;

        T data;

        Node<T> next;

        public Node(Node<T> prev, T data, Node<T> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list);
        list.reverse();
        System.out.println(list);
//        System.out.println(list.get(2));
//        System.out.println(list.find(1));
    }
}
