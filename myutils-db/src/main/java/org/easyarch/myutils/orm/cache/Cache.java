package org.easyarch.myutils.orm.cache;

/**
 * Description :
 * Created by xingtianyu on 17-1-19
 * 下午7:17
 * description:
 */

public interface Cache {

    Object get();

    void set(String key,Object value);

    Object remove(String key);

    void clear();
}
