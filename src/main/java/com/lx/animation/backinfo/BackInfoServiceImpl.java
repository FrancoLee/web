package com.lx.animation.backinfo;

import com.lx.animation.admin.AdminDao;
import com.lx.animation.article.ArticleDao;
import com.lx.animation.user.UserDao;
import com.lx.animation.video.VideoDao;
import com.lx.animation.videolist.VideoListDao;
import com.lx.animation.webInfo.WebInfoDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by root on 17-12-14.
 */
@Service("BackInfoService")
public class BackInfoServiceImpl implements BackInfoService {
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
    @Autowired
    WebInfoDao webInfoDao;
    @Autowired
    BackInfo info;
    private static final Logger LOGGER= LogManager.getLogger(BackInfoService.class);
    public BackInfo getBackInfo(){
        try {
            info.setCountAdmin(adminDao.queryAllAdmin());
            info.setCountArt(articleDao.listAllArticle());
            info.setCountUser(userDao.queryCountAll());
            info.setCountVideoList(videoListDao.queryAllCountList());
            info.setCountVideo(videoDao.queryAllVideo());
            info.setShowArt(articleDao.countByNoStatus("unprocessed"));
            info.setShowVideoList(videoListDao.queryCountByShow(1));
            info.setShowVideo(videoDao.queryAllShow());
            info.setShowUser(userDao.queryByState("regular"));
            info.setSealUser(userDao.queryByState("disable"));
            info.setCountClick(webInfoDao.queryCountClick());
        } catch (Exception e) {
            LOGGER.error("get back info fail");
            return null;
        }
        return info;
    }
}
