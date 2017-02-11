package org.easyarch.myutils.test.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.easyarch.myutils.test.dao.UserMapper;
import org.easyarch.myutils.test.pojo.User;

import java.io.IOException;

/**
 * Description :
 * Created by xingtianyu on 17-2-11
 * 下午3:02
 * description:
 */

public class MybatisService {

    private SqlSession sqlSession;

    private UserMapper userMapper;
    public MybatisService() {
        sqlSession = getSessionFactory().openSession();
        this.userMapper = sqlSession.getMapper(UserMapper.class);
    }

    public User getUser(String id){
        User user = userMapper.findById(id);
        return user;
    }

    private static SqlSessionFactory getSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        String resource = "mybatis.xml";
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources
                    .getResourceAsReader(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }



}
