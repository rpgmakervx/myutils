package org.easyarch.myutils.algorithm.tree.trie;

/**
 * @author xingtianyu(code4j)
 *         Created by xingtianyu(code4j) on 2017-10-22.
 */
public class WordEntity {

    public String id;

    public String word;

    public WordEntity(String word) {
        if (word != null){
            this.word = word.trim();
        }
    }



    @Override
    public String toString() {
        return word;
    }
}
