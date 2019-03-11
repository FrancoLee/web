package com.lx.animation.dao;

import com.lx.animation.webInfo.WebInfoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by root on 18-7-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext.xml"})
public class WebInfoDaoTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    WebInfoDao webInfoDao;
    @Test
    public void test(){
        webInfoDao.updateClick();
        System.out.println(webInfoDao.queryCountClick());
    }
}
