package org.easyarch.myutils.algorithm.tree.trie;

import java.util.List;

/**
 * @author xingtianyu(code4j)
 *         Created by xingtianyu(code4j) on 2017-10-22.
 */
public interface ITrie<T> {

    List<T> search(String word);
    List<T> search(char[] pinyin);

    void insert(T node);

    void build(List<T> nodes);

}
