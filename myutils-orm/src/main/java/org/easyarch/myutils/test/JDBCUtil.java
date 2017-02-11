package org.easyarch.myutils.test;

import org.easyarch.myutils.orm.jdbc.cfg.ConnConfig;
import org.easyarch.myutils.orm.jdbc.pool.DBCPoolFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Description :
 * Created by xingtianyu on 17-2-11
 * 下午3:18
 * description:
 */

public class JDBCUtil {


    static {
        ConnConfig.config("root","123456",
                "jdbc:mysql://localhost:3306/shopping?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false",
                "com.mysql.jdbc.Driver");
    }
    public static DataSource pool = DBCPoolFactory.newCachedDBCPool();

    /**
     * 获取数据库连接
     * */
    public static Connection getConnection() throws SQLException {
        return pool.getConnection();
    }
}
