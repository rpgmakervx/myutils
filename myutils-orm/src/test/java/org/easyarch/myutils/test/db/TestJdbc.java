package org.easyarch.myutils.test.db;

import org.easyarch.myutils.orm.jdbc.cfg.ConnConfig;
import org.easyarch.myutils.orm.jdbc.exec.MySqlExecutor;
import org.easyarch.myutils.orm.jdbc.exec.SqlExecutor;
import org.easyarch.myutils.orm.jdbc.handler.BeanListResultSetHadler;
import org.easyarch.myutils.orm.pool.cfg.PoolConfig;
import org.easyarch.myutils.orm.pool.ds.DBCPoolFactory;
import org.testng.annotations.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 17-2-6
 * 上午11:41
 * description:
 */

public class TestJdbc {

    @Test
    public void testJdbc() throws SQLException {
        ConnConfig.config("root", "123456",
                "jdbc:mysql://localhost:3306/daojiatestdb?connectTimeout=1000&amp;useUnicode=true&amp;characterEncoding=utf-8", "com.mysql.jdbc.Driver");
//        ConnConfig.config("daojia_root", "aaa111",
//                "jdbc:mysql://djcard.dbt.djdns.cn:3612/dbdj_zt_reconciliation?connectTimeout=1000&amp;useUnicode=true&amp;characterEncoding=utf-8", "com.mysql.jdbc.Driver");
        DataSource dataSource = DBCPoolFactory.newConfigedDBCPool(PoolConfig.config(200, 50, 5, 3 * 1000L).getProperties());
        final SqlExecutor executor = new MySqlExecutor(dataSource.getConnection());
//        List<CashPoolCheck> checks = executor.query("select * from check_result_order_cashpool ",
//                new BeanListResultSetHadler<CashPoolCheck>(CashPoolCheck.class), null);
        List<PartnerCheck> checks = executor.query("select * from check_result_order_parternerorder ",
                new BeanListResultSetHadler<PartnerCheck>(PartnerCheck.class), null);
        System.out.println(checks);
    }
}
