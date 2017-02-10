package org.easyarch.myutils.test.controlle;


import org.easyarch.myutils.orm.jdbc.exec.MySqlExecutor;
import org.easyarch.myutils.orm.jdbc.exec.SqlExecutor;
import org.easyarch.myutils.orm.jdbc.handler.BeanListResultSetHadler;
import org.easyarch.myutils.orm.jdbc.pool.DBCPoolFactory;
import org.easyarch.myutils.orm.utils.ResourcesUtil;
import org.easyarch.myutils.test.pojo.User;
import org.easyarch.myutils.test.service.UserService;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

/**
 * Description :
 * Created by xingtianyu on 17-2-9
 * 上午2:23
 * description:
 */

public class UserController {

    public static void main(String[] args) throws Exception {
        UserService service = new UserService();
        long begin1 = System.currentTimeMillis();
        for (int index = 0;index<100;index++){
            service.getUser("ewrgthgdsvng");
        }
        long end1 = System.currentTimeMillis();
//        User user = new User();
//        user.setPhone("13652179825");
//        user.setUserName("xingtianyu");
//        System.out.println(service.getUsers(user));
        Properties prop = new Properties();
        prop.load(ResourcesUtil.getResourceAsStream("/db.properties"));
//        ConnConfig.config("root", "123456",
//                "jdbc:mysql://localhost:3306/shopping?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false", "com.mysql.jdbc.Driver");
        DataSource dataSource = DBCPoolFactory.newConfigedDBCPool(prop);
        final SqlExecutor executor = new MySqlExecutor(dataSource.getConnection());
        Object[] params = new Object[]{"ewrgthgdsvng"};
        long begin2 = System.currentTimeMillis();
        for (int index = 0;index<100;index++){
            List<User> users = executor.query("select * from user where client_id = ? ",
                    new BeanListResultSetHadler<User>(User.class), params);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("jdbc use:"+(end2- begin2));
        System.out.println("frame work use:"+(end1 - begin1));
    }

}
