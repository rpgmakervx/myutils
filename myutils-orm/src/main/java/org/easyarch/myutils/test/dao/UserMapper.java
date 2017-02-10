package org.easyarch.myutils.test.dao;

import org.easyarch.myutils.orm.annotation.sql.SqlParam;
import org.easyarch.myutils.test.pojo.User;

import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 17-2-9
 * 上午2:07
 * description:
 */

public interface UserMapper {

    public User findById(@SqlParam(name = "id") String id);

    public List<User> findByUser(User user);
}
