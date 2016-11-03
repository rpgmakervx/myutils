package org.easyarch.myutils.db.processor;/**
 * Description : 
 * Created by YangZH on 16-11-2
 *  下午7:17
 */

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description :
 * Created by code4j on 16-11-2
 * 下午7:17
 */

public interface Wrapper {

    public<T> List<T> list(ResultSet rs,Class<T> type);

    public<T> Set<T> set(ResultSet rs,Class<T> type);

    public Object[] array(ResultSet rs,Class type);

    public<T> T bean(ResultSet rs,Class<T> type);

    public<T> Map<String,T> map(ResultSet rs,Class<T> type);

}


