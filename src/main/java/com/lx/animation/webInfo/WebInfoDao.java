package com.lx.animation.webInfo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by root on 18-7-22.
 */
@Repository
public interface WebInfoDao {
    /**
     * 查询网站点击率
     * @return 网站点击量
     */
    long queryCountClick();

    /**
     * 更新点击量
     */
    void updateClick();
}
