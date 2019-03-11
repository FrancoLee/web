package com.lx.animation.webInfo;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Created by root on 18-7-22.
 */
@Service
public class WebInfoService {
    @Autowired
    private WebInfoDao webInfoDao;

    private static final Logger LOGGER = LogManager.getLogger(WebInfoService.class);
    /**
     *  查询网站总点击量
     */
    public long getClick() {
        long sum=0;
        try {
            sum=webInfoDao.queryCountClick();
        } catch (Exception e) {
            LOGGER.error("query web sun(click) fail");
        }
        return sum;
    }
    /**
     *  跟新点击量
     */
    public void updateClick(){
        try {
            webInfoDao.updateClick();
        } catch (Exception e) {
            LOGGER.error("update web click fail");
        }
    }
}
