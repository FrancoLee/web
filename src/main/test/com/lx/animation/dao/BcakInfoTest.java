package com.lx.animation.dao;

import com.lx.animation.admin.AdminDao;
import com.lx.animation.article.ArticleDao;
import com.lx.animation.backinfo.BackInfo;
import com.lx.animation.backinfo.BackInfoService;
import com.lx.animation.user.UserDao;
import com.lx.animation.video.VideoDao;
import com.lx.animation.videolist.VideoListDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by root on 17-12-14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext.xml"})
public class BcakInfoTest {
    @Autowired
    VideoDao videoDao;
    @Autowired
    VideoListDao videoListDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ArticleDao articleDao;
    @Autowired
    AdminDao adminDao;
    private static final Logger LOGGER= LoggerFactory.getLogger(BackInfoService.class);
    @Test
    public void test(){
        BackInfo info=new BackInfo();
//            info.setCountAdmin(adminDao.queryAllAdmin());
//            info.setCountArt(articleDao.listAllArticle());
//            info.setCountUser(userDao.queryCountAll());
//            info.setCountVideoList(videoListDao.queryAllCountList());
//            info.setCountVideo(videoDao.queryAllVideo());
//            info.setShowArt(articleDao.queryCountShow());
//            info.setShowVideoList(videoListDao.queryCountByShow(1));
//            info.setShowVideo(videoDao.queryAllShow());
//            info.setShowUser(userDao.queryBySeal(0));
//            info.setSealUser(userDao.queryBySeal(1));
    }

}
