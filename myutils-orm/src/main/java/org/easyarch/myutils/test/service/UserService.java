package org.easyarch.myutils.test.service;

import org.easyarch.myutils.orm.session.DBSession;
import org.easyarch.myutils.orm.session.DBSessionFactory;
import org.easyarch.myutils.orm.session.DBSessionFactoryBuilder;
import org.easyarch.myutils.orm.utils.ResourcesUtil;
import org.easyarch.myutils.test.dao.UserMapper;
import org.easyarch.myutils.test.pojo.User;

/**
 * Description :
 * Created by xingtianyu on 17-2-9
 * 上午2:22
 * description:
 */

public class UserService {

    static {
//        DBSessionFactory factory = new DBSessionFactoryBuilder().build()
    }

    private DBSessionFactory sessionFactory;

    private UserMapper mapper;

    public UserService(){
        try {
            sessionFactory = new DBSessionFactoryBuilder().build(
                    ResourcesUtil.getResourceAsStream("/config.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUser(String id){
        DBSession session = sessionFactory.newDelegateSession();
        mapper = session.getMapper(UserMapper.class);
        return mapper.findById(id);
    }
}
