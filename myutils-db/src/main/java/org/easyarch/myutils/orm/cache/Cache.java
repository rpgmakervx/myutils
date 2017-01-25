package org.easyarch.myutils.orm.cache;

/**
 * Description :
 * Created by xingtianyu on 17-1-19
 * 下午7:17
 * description:
 */

public interface Cache<K,V> {

    V get(K key);

    void set(K key,V value);

    V remove(K key);

    void clear();
}
