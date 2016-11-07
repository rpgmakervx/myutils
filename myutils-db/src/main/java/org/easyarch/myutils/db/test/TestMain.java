package org.easyarch.myutils.db.test;/**
 * Description : 
 * Created by YangZH on 16-11-7
 *  下午2:13
 */

import org.easyarch.myutils.cp.cfg.PoolConfig;
import org.easyarch.myutils.cp.factory.DBCPoolFactory;
import org.easyarch.myutils.db.cfg.ConnConfig;
import org.easyarch.myutils.db.exec.MySqlExecutor;
import org.easyarch.myutils.db.exec.SqlExecutor;
import org.easyarch.myutils.db.handler.BeanListResultSetHadler;

import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description :
 * Created by code4j on 16-11-7
 * 下午2:13
 */

public class TestMain {

    private static final int BARRIER = 500;

    public static void main(String[] args) {
        ConnConfig.config("root", "123456",
                "jdbc:mysql://localhost:3306/database?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false", "com.mysql.jdbc.Driver");
        PoolConfig.config(200, 100, 50, 5 * 1000L);
        PoolConfig.print();
        final SqlExecutor executor = new MySqlExecutor(DBCPoolFactory.newConfigedDBCPool());

//        while (index < 3000) {
//            List<User> user = executor.query("select * from user ",true,
//                    new BeanListResultSetHadler<User>(User.class), null);
//            index++;
//        }
//        final SqlExecutor executor = new MySqlExecutor();
        ExecutorService pool = Executors.newCachedThreadPool();
//        CyclicBarrier barrier = new CyclicBarrier(BARRIER,new Runnable() {
//            @Override
//            public void run() {
//                int index = 0;
//                while (index < 300) {
//                    List<User> user = executor.query("select * from user ",true,
//                            new BeanListResultSetHadler<User>(User.class), null);
//                    System.out.println(Thread.currentThread().getName() + " index:" + index);
//                    index++;
//                }
//                System.out.println("thread1 ended");
//            }
//        });
        for (int index = 0;index<BARRIER;index++){
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    int index = 0;
                    while (index < 20) {
                        try {
//                            Connection conn = DriverManager.getConnection(ConnConfig.getUrl()
//                                    , ConnConfig.getUser(), ConnConfig.getPassword());
                            List<User> user = executor.query("select * from user ",true,
                                    new BeanListResultSetHadler<User>(User.class), (Object[]) null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " index:" + index);
                        index++;
                    }
                    System.out.println("thread1 ended");
                }
            });
        }
        pool.shutdown();
//        executor.query(createConnection(),"select * from user ",
//                            new BeanListResultSetHadler<User>(User.class));

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
