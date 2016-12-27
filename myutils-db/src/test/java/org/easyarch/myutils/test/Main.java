package org.easyarch.myutils.test;

import org.apache.commons.dbcp.BasicDataSource;
import org.easyarch.myutils.db.handler.BeanListResultSetHadler;
import org.easyarch.myutils.db.test.User;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.easyarch.myutils.db.ConnectionUtils.executor;

/**
 * Description :
 * Created by xingtianyu on 16-11-30
 * 下午11:51
 */

public class Main {

    public static void main(String[] args) throws SQLException {
        final BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/database?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false");
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("123456");

        ds.setInitialSize(100); // 初始的连接数；
        ds.setMaxIdle(50);
        ds.setMinIdle(20);
        ds.setMaxWait(60 * 1000L);
        ExecutorService pool = Executors.newCachedThreadPool();
        System.out.println("开始");
        for (int index = 0;index<20;index++){
            final int finalIndex = index;
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    Thread.currentThread().setName("thread"+ finalIndex);
                    int count = 0;
                    while (count < 100) {
                        try {
                            List<User> user = executor.query(ds.getConnection(),"select * from user "
                                    ,new BeanListResultSetHadler<User>(User.class));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " count:" + count);
                        count++;
                    }
                    System.out.println("++++++++"+Thread.currentThread().getName()+" ended +++++++++");
                }
            });
        }
        pool.shutdown();
//        executor.query(createConnection(),"select * from user ",
//                            new BeanListResultSetHadler<User>(User.class));
        System.out.println("programme ended");

    }
}
