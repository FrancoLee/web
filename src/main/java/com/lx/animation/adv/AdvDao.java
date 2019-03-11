package com.lx.animation.adv;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by root on 17-12-7.
 */
@Repository
public interface AdvDao {
    /**
     * 添加广告栏
     *
     * @param adv
     */
    void addAdv(Adv adv);

    /**
     * 修改广告栏
     *
     * @param adv
     */
    void updateAdv(Adv adv);

    /**
     * 修改logo图片
     *
     * @param picUrl
     */
    void updateLogo(@Param("picUrl") String picUrl);

    /**
     * 查询logo是否存在
     *
     * @return
     */
    int queryLogoExist();

    /**
     * 修改logo信息
     *
     * @param info
     */
    void updateLogoInfo(@Param("info") String info);

    /**
     * 查询logo信息
     *
     * @return
     */
    Adv queryLogo();

    /**
     * 查询广告图片的url
     *
     * @param id
     * @return
     */
    String queryAdvPicUrl(@Param("id") int id);

    /**
     * 获取轮播图信息
     *
     * @return
     */
    List<Adv> queryCarousel();

    /**
     * 删除广告
     *
     * @param id
     */
    void deleteById(@Param("id") int id);

    /**
     * 查询所有广告
     *
     * @return
     */
    List<Adv> queryAllAdv();

    /**
     * 通过id获取广告图片
     *
     * @param arrId
     * @return
     */
    List<String> queryPicUrlByArrId(String[] arrId);

    /**
     * 通过ID批量删除广告
     *
     * @param arrId
     */
    void deleteByArrId(String[] arrId);

    /**
     * 修改图片信息
     *
     * @param id
     * @param picUrl
     */
    void updateById(@Param("id") int id, @Param("picUrl") String picUrl);
}
