package org.easyarch.myutils.db.exec;/**
 * Description : 
 * Created by YangZH on 16-11-3
 *  下午2:30
 */

import org.easyarch.myutils.reflect.ReflectUtils;

import java.beans.PropertyDescriptor;
import java.sql.*;

/**
 * Description :
 * Created by code4j on 16-11-3
 * 下午2:30
 */

public abstract class AbstractExecutor {

    protected boolean supportMeta;

    public AbstractExecutor() {
        this.supportMeta = true;
    }

    public AbstractExecutor(boolean supportMeta) {
        this.supportMeta = supportMeta;
    }


    protected PreparedStatement prepareStatement(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    protected void fillStatement(PreparedStatement ps, Object... params) {
        try {
            ParameterMetaData meta = ps.getParameterMetaData();
            int count = meta.getParameterCount();
            int paramLength = params == null ? 0 : params.length;
            if (paramLength != count) {
                throw new IllegalArgumentException("your param not match query string's param");
            }
            for (int index = 0; index < paramLength; index++) {
                if (params[index] == null) {
                    ps.setNull(index, Types.VARCHAR);
                    continue;
                }
                ps.setObject(index + 1, params[index]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void fillStatementWithBean(PreparedStatement ps, Object bean) {
        PropertyDescriptor[] descriptors = ReflectUtils.propertyDescriptors(bean.getClass());
        Object[] params = new Object[descriptors.length];
        for (int index = 0; index < params.length; index++) {
            Object value = ReflectUtils.getter(bean, descriptors[index].getName());
            params[index] = value;
        }
        fillStatement(ps, params);
    }
}
