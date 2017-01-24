package org.easyarch.myutils.orm.session;

import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 16-12-28
 * 上午1:09
 * description:
 */

public interface DBSession {

    public <T> T selectOne(String bind,Class<T> clazz,Object ...parameter);

    public <E> List<E> selectList(String bind,Class<E> clazz,Object... parameter);

    public int update(String bind,Object... parameter);
    public int update(Object bean);

    public int delete(String bind,Object... parameter);
    public int delete(Object bean);

    public int insert(String bind,Object... parameter);
    public int insert(Object bean);


}
