package com.lx.animation.dao;

import com.lx.animation.video.VideoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by root on 17-12-12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext.xml"})
public class VideoDaoTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    VideoDao videoDao;
    @Test
    public void test(){
        List<String> picList=videoDao.queryUrlByAdminListId(63);
        for (String url :
                picList) {
            System.out.println(url);
        }
        videoDao.deleteByListId(63);
        //List<Video> videos=videoDao.queryByListIdUser(50,"小桥流水人家");

//        video.setUser("煞笔王波波");
//        video.setVidUrl("pqweqw");
//        video.setListId(60);
//       videoDao.addVideo(video);
//        System.out.println(video.getVid());
    }
}
