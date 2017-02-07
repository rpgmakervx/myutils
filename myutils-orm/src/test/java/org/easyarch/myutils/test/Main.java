package org.easyarch.myutils.test;

/**
 * Description :
 * Created by xingtianyu on 16-11-30
 * 下午11:51
 */

public class Main {

//    public static void main(String[] args) throws SQLException {
//        final BasicDataSource pool = new BasicDataSource();
//        pool.setUrl("jdbc:mysql://localhost:3306/database?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false");
//        pool.setDriverClassName("com.mysql.jdbc.Driver");
//        pool.setUsername("root");
//        pool.setPassword("123456");
//
//        pool.setInitialSize(100); // 初始的连接数；
//        pool.setMaxIdle(50);
//        pool.setMinIdle(20);
//        pool.setMaxWait(60 * 1000L);
//        ExecutorService pool = Executors.newCachedThreadPool();
//        System.out.println("开始");
//        for (int index = 0;index<20;index++){
//            final int finalIndex = index;
//            pool.submit(new Runnable() {
//                @Override
//                public void run() {
//                    Thread.currentThread().setNames("thread"+ finalIndex);
//                    int count = 0;
//                    while (count < 100) {
//                        try {
//                            List<User> user = executor.query(pool.getConnection(),"select * from user "
//                                    ,new BeanListResultSetHadler<User>(User.class));
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println(Thread.currentThread().getNames() + " count:" + count);
//                        count++;
//                    }
//                    System.out.println("++++++++"+Thread.currentThread().getNames()+" ended +++++++++");
//                }
//            });
//        }
//        pool.shutdown();
////        executor.query(createConnection(),"select * from user ",
////                            new BeanListResultSetHadler<User>(User.class));
//        System.out.println("programme ended");
//
//    }
}
