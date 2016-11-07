package org.easyarch.myutils.db.exec;/**
 * Description : 
 * Created by YangZH on 16-11-3
 *  下午2:30
 */

import org.easyarch.myutils.reflect.ReflectUtils;

import javax.sql.DataSource;
import java.beans.PropertyDescriptor;
import java.sql.*;

/**
 * Description :
 * Created by code4j on 16-11-3
 * 下午2:30
 */

public abstract class AbstractExecutor {

    protected boolean supportMeta;
    protected final DataSource ds;
    public AbstractExecutor(){
        ds = null;
    }
    public AbstractExecutor(DataSource ds) {
        this.ds = ds;
    }
    public AbstractExecutor(DataSource ds,boolean supportMeta){
        this.ds = ds;
        this.supportMeta = supportMeta;
    }


    protected PreparedStatement prepareStatement(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    protected void fillStatement(PreparedStatement ps, Object... params) {
        try {
            int paramLength = params == null ? 0 : params.length;
            ParameterMetaData meta = ps.getParameterMetaData();
            int count = meta.getParameterCount();
            if (params == null||meta == null||count == 0)
                return;
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
        if (bean == null)
            return;
        PropertyDescriptor[] descriptors = ReflectUtils.propertyDescriptors(bean.getClass());
        Object[] params = new Object[descriptors.length];
        for (int index = 0; index < params.length; index++) {
            Object value = ReflectUtils.getter(bean, descriptors[index].getName());
            params[index] = value;
        }
        fillStatement(ps, params);
    }
}
