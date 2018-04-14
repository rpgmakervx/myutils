package org.easyarch.myutils.algorithm.struct.tree.trie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * node的数据结构：
 * char--list(word),比如字母a可以绑定的word有：啊，阿，吖等等。
 * @author xingtianyu(code4j)
 *         Created by xingtianyu(code4j) on 2017-10-22.
 */
public class Node {

    private boolean isRoot = false;

    private boolean isSolid = false;

    private List<WordEntity> wordEntities = new ArrayList<>();

    private Character character;

    private int level;

    private LinkedList<Node> children;

    public Node(Character character,WordEntity entity) {
        if (this.character == null){
            this.isRoot = true;
        }
        if (entity != null){
            this.wordEntities.add(entity);
            this.isSolid = true;
        }
        this.character = character;
        children = new LinkedList<>();
    }

    public boolean end(){
        return children.size() == 0;
    }

    public int level(){
        return level;
    }
    public void addLevel(int superLevel){
        this.level = superLevel + 1;
    }

    public boolean root(){
        return isRoot;
    }

    public boolean isEmpty(){
        return wordEntities== null||wordEntities.size() == 0;
    }

    public void addEntity(WordEntity entity){
        entities().add(entity);
        this.isSolid = true;
    }

    public List<WordEntity> entities(){
        return wordEntities;
    }

    public Character character(){
        return character;
    }

    public Node node(Character character){
        if (character == null){
            return null;
        }
        for (Node child:this.children){
            if (Objects.equals(child.character(), character)){
                return child;
            }
        }
        return null;
    }

    public LinkedList<Node> nodes(){
        return children;
    }

    public List<Node> dfs(){
        List<Node> childNodes = new ArrayList<>();
        iterate(childNodes,this);
        return childNodes;
    }

    private void iterate(List<Node> childNodes,Node node){
        List<Node> children = node.children;
        if (children == null||children.size() == 0){
            return ;
        }
        for (Node n:children){
            if (!n.isEmpty()){
                childNodes.add(n);
            }
            iterate(childNodes,n);
        }
    }


    @Override
    public String toString() {
        return "Node{" +
                "isRoot=" + isRoot +
                ", isEnd=" + end() +
                ", wordEntities=" + wordEntities +
                ", character=" + character +
                ", level=" + level +
                ", nodes=" + children +
                '}';
    }

    public static void main(String[] args) {
        List<WordEntity> wordEntities = new ArrayList<>(2);
        wordEntities.add(null);
        for (WordEntity e:wordEntities){
            System.out.println(e);
        }
    }
}
