package org.easyarch.myutils.orm.session;

import java.util.List;
import java.util.Map;

/**
 * Description :
 * Created by xingtianyu on 16-12-28
 * 上午1:09
 * description:
 */

public interface DBSession {

    public <T> T selectOne(String bind,Class<T> clazz,Object ...parameter);

    public <E> List<E> selectList(String bind,Class<E> clazz,Object... parameter);

    public int selectCount(String bind,Object... parameters);

    public List<Map<String,Object>> selectMap(String bind,Object ... parameters);

    public int update(String bind,Object... parameter);

    public int delete(String bind,Object... parameter);

    public int insert(String bind,Object... parameter);

    public <T> T getMapper(Class<T> clazz);

    public Configuration getConfiguration();
    public void close();
    public void rollback();

}
