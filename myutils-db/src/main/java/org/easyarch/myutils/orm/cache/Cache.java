package org.easyarch.myutils.orm.cache;

/**
 * Description :
 * Created by xingtianyu on 17-1-19
 * 下午7:17
 * description:
 */

public interface Cache<T> {

    T get(String key);

    void set(String key,T value);

    T remove(String key);

    void clear();
}
