package org.easyarch.myutils.test.service;

import org.easyarch.myutils.orm.session.DBSession;
import org.easyarch.myutils.orm.session.DBSessionFactory;
import org.easyarch.myutils.orm.session.DBSessionFactoryBuilder;
import org.easyarch.myutils.orm.utils.ResourcesUtil;
import org.easyarch.myutils.test.dao.UserMapper;
import org.easyarch.myutils.test.pojo.User;

import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 17-2-9
 * 上午2:22
 * description:
 */

public class UserService {

    private UserMapper mapper;

    private DBSession session;

    public UserService(){
        try {
            DBSessionFactory sessionFactory = new DBSessionFactoryBuilder().build(
                    ResourcesUtil.getResourceAsStream("/config.xml"));
            session = sessionFactory.newDelegateSession();
            mapper = session.getMapper(UserMapper.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public User getUser(String id){
        return mapper.findById(id);
    }
    public List<User> getUsers(User user){
        return mapper.findByUser(user);
    }

    public void saveUser(User user){
        mapper.insert(user);
    }
}
