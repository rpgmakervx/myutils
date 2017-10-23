package org.easyarch.myutils.algorithm.tree.trie;

import org.easyarch.myutils.file.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通过拼音搜索汉字关键词
 * @author xingtianyu(code4j)
 *         Created by xingtianyu(code4j) on 2017-10-22.
 */
public class WordTrie implements ITrie<WordEntity> {

    private Node root;

    private static Map<String,WordEntity> cache = new HashMap<>();

    public WordTrie(){
        root = new Node(null,null);
    }

    @Override
    public List<WordEntity> search(String word) {
        String pinyin = PinYinKits.toPinYin(word);
        return search(pinyin.toCharArray());
    }

    @Override
    public List<WordEntity> search(char[] pinyinchar) {
        List<WordEntity> result = new ArrayList<>();

        Node currentNode = root;
        for (char ch:pinyinchar){
            currentNode = currentNode.node(ch);
            if (currentNode == null){
                System.out.println("node null");
                return result;
            }
            if (currentNode.end()){
                result.addAll(currentNode.entities());
                System.out.println("result empty:"+ result);
            }
        }
        if (currentNode != null){
            List<Node> dataNode = currentNode.dfs();
            for (Node n:dataNode){
                result.addAll(n.entities());
            }
        }
        return result;
    }


    /**
     * 插入之前检查节点是否存在（字母存在）,存在则判断该节点绑定的词是否包含插入的词，
     * @param en
     */
    @Override
    public void insert(WordEntity en) {
        WordEntity entity = cache.get(en.id);
        if (entity != null){
            return;
        }
        Node currentNode = root;
        String word = en.word;
        String pinyin = PinYinKits.toPinYin(word);
        char[] pinyinchar = pinyin.toCharArray();
        int endPoint = 0;
        for (char ch:pinyinchar){
            Node objNode = currentNode.node(ch);
            if (objNode != null){
                currentNode = objNode;
                if (endPoint == pinyinchar.length - 1){
                    List<WordEntity> entities = currentNode.entities();
                    if (currentNode.isEmpty()){
                        currentNode.addEntity(en);
                        return ;
                    }
                    for (WordEntity e:entities){
                        if (!e.equals(en.word)){
                            currentNode.addEntity(en);
                            return;
                        }
                    }
                }
            }else{
                WordEntity e = null;
                if (endPoint == pinyinchar.length - 1){
                    e = en;
                }
                Node newNode = new Node(ch,e);
                currentNode.nodes().add(newNode);
                currentNode = newNode;
            }
            endPoint++;
        }

    }

    @Override
    public void build(List<WordEntity> nodes) {

    }

    private WordEntity find(char[] pinyin){
        return null;
    }

    @Override
    public String toString() {
        return "WordTrie{" +
                "root=" + root +
                '}';
    }

    public static void main(String[] args) throws Exception {
        WordTrie trie = new WordTrie();
//        WordEntity entity1 = new WordEntity("维修");
//        WordEntity entity2 = new WordEntity("维护");
//        WordEntity entity3 = new WordEntity("保护");
//        WordEntity entity4 = new WordEntity("未修");
//        WordEntity entity5 = new WordEntity("维");
//        WordEntity entity6 = new WordEntity("围城");
//        WordEntity entity7 = new WordEntity("保证金");
//        WordEntity entity8 = new WordEntity("保证");
//        WordEntity entity9 = new WordEntity("保证书");
//        WordEntity entity10 = new WordEntity("保证明天完成");
//        trie.insert(entity1);
//        trie.insert(entity2);
//        trie.insert(entity3);
//        trie.insert(entity4);
//        trie.insert(entity5);
//        trie.insert(entity6);
//        trie.insert(entity7);
//        trie.insert(entity8);
//        trie.insert(entity9);
//        trie.insert(entity10);
        List<String> words = FileUtils.getLines("E:\\ftp\\main.dic");
        System.out.println("word count:"+words.size());
        for (String word:words){
            WordEntity e = new WordEntity(word);
            trie.insert(e);
        }
        long begin = System.nanoTime();
        System.out.println(trie.search("jingqing"));
        System.out.println("COST:"+(System.nanoTime() - begin));
    }
}
