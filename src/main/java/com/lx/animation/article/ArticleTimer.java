package com.lx.animation.article;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 * Created by root on 18-8-22.
 */
@Controller
public class ArticleTimer {
    private static final Logger LOGGER= LoggerFactory.getLogger(ArticleTimer.class);
    @Autowired
    ArticleDao articleDao;

    @Scheduled(cron="0 0/20 * * * ?")
    public void resetArticleProcessing(){
        try {
            articleDao.updateArticleStateToUnprocessed();
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }
    }
}
