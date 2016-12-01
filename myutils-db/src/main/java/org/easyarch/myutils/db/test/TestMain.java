package org.easyarch.myutils.db.test;/**
 * Description : 
 * Created by YangZH on 16-11-7
 *  下午2:13
 */

import org.easyarch.myutils.cp.cfg.PoolConfig;
import org.easyarch.myutils.cp.factory.DBCPoolFactory;
import org.easyarch.myutils.db.cfg.ConnConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

/**
 * Description :
 * Created by code4j on 16-11-7
 * 下午2:13
 */

public class TestMain {

    private static final int BARRIER = 100;

    public static void main(String[] args) throws SQLException {
        ConnConfig.config("root", "123456",
                "jdbc:mysql://localhost:3306/database?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false", "com.mysql.jdbc.Driver");
        PoolConfig.config(200, 50, 20, 3 * 1000L);
        DataSource ds = DBCPoolFactory.newConfigedDBCPool();
        List<Connection> cons = new LinkedList<>();
        Connection con1 = ds.getConnection();
        Connection con2 = ds.getConnection();
        Connection con3 = ds.getConnection();
        Connection con4 = ds.getConnection();
        Connection con5 = ds.getConnection();
        cons.add(con1);
        cons.add(con2);
        cons.add(con3);
        cons.add(con4);
        cons.add(con5);
        Connection c1 = cons.get(0);
        Connection c2 = cons.get(1);
        Connection c3 = cons.get(2);
        Connection c4 = cons.get(3);
        Connection c5 = cons.get(4);
        String s = new String("12");
        System.out.println(s.equals(s));
        System.out.println(con1.equals(c1));
    }

    static class Block implements Runnable{
        private CyclicBarrier barrier;
        public Block(CyclicBarrier barrier){
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
