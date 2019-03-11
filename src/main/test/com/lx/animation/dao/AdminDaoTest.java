package com.lx.animation.dao;

import com.lx.animation.admin.Admin;
import com.lx.animation.admin.AdminDao;
import com.lx.animation.util.HashPassUtil;
import com.lx.animation.util.UsersUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by root on 17-12-6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext.xml","classpath*:spring-servlet.xml"})
public class AdminDaoTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    AdminDao adminDao;
    @Autowired
    private Admin admin;
    @Autowired
    UsersUtil usersUtil;
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDaoTest.class);
    private final String USER = "12345";
    private final String PASSWORD = "`123qwe";
    @Autowired
    HashPassUtil hashPassUtil;
    @Test
    public void testAdd() {
        System.out.println(admin);
        try {
//            admin.getLastTime();

           adminDao.addAdmin(USER, hashPassUtil.gethash(PASSWORD));
     //       Admin admin = adminDao.queryAdmin(USER, PASSWORD);
       //     System.out.println(admin.getLastTime());
        //    Timestamp timestamp = new Timestamp(123213);
        //    adminDao.updateLastTime(USER, timestamp);
     //       admin = adminDao.queryAdmin(USER, PASSWORD);
            System.out.println(admin.getUser() + "/" + admin.getLastTime());
       //     System.out.println(adminDao.queryAllAdmin());
        } catch (Exception e) {
            LOGGER.error("sss");

        }


    }


}
