package com.lx.animation.dao;

import com.lx.animation.videolist.VideoList;
import com.lx.animation.videolist.VideoListDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by root on 17-12-10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext.xml"})
public class VideoListDaoTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    VideoListDao videoListDao;
    @Test
    public void test(){
        List<VideoList> list=videoListDao.queryByUserPage("煞笔王波波",3,6);
        if(list==null)
            System.out.println("1");
    }
}