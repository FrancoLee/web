package com.lx.animation.dao;

import com.lx.animation.user.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by root on 17-12-6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext.xml"})
public class UserDaoTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private UserDao userDao;
    private final String test="0112";
    private static final Logger LOGGER= LoggerFactory.getLogger(UserDaoTest.class);
    @Test
    public void testAdd() {
        try {
//            userDao.addUser(test, "123");
////            System.out.println(userDao.queryBySeal(0));
//            List<User> userList=userDao.queryAllUser();
//            for (User user:userList
//                 ) {
//                System.out.println(user.getUser());
//          }
//            User user=userDao.queryUser(test,"123");
//            System.out.println(user.getState());
//            userDao.updateBySeal(test,1);
//            user=userDao.queryUser(test,"123");
//            System.out.println(user.getState());
//            userList=userDao.queryAllUserBySeal(1);
//            for (User u:userList
//                 )
//                System.out.println(u.getUser());
//            System.out.println(user.getPicUrl());
            int count=userDao.queryUserExist("sssq");
            System.out.println(count);
//            if(count>=0){
//                System.out.println("用户已经注册");
//            }
        }catch (Exception e){
            //System.out.println("ss");
            LOGGER.error("error");
        }
    }
}
