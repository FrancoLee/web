package com.lx.animation.videolist;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by root on 17-12-9.
 */
@Repository
public interface VideoListDao {
    /**
     * 添加视频列表
     * @param videoList
     */
     void addList(VideoList videoList);

    /**
     * 跟新点击率
     * @param id
     * @param user
     */
    void updateClick(@Param("id") int id,@Param("user") String user);

    /**
     * 获取图片链接
     * @param id
     * @param user
     * @return
     */
    String queryPicUrl(@Param("id") String id,@Param("user") String user);

    /**
     * 通过用户id和名字删除
     * @param listID
     * @param user
     */
    int deleteByIdUser(@Param("listId") int listID,@Param("user") String user);
    /**
     * 查询图片链接
     * @param id
     * @return
     */
    String queryPicUrlById(@Param("id") int id);

    /**
     * 更新视频列表信息
     * @param title
     * @param picUrl
     * @param vid
     */
    void updateById(@Param("title") String title,@Param("picUrl") String picUrl,@Param("vid") int vid);

    /**
     * 查询视频列表信息
     * @param id
     * @param user
     * @return
     */
    VideoList queryById(@Param("id") int id,@Param("user") String user);

    /**
     * 查询最新的视频列表
     * @param limit
     * @return
     */
    List<VideoList> queryByNew(@Param("limit")  int limit);

    /**
     * 查询最多点击视频列表
     * @param limit
     * @return
     */
    List<VideoList> queryByClick(@Param("limit") int limit);

    /**
     * 查询用户的所有视频列表个数
     * @param user
     * @return
     */
    int queryCountByUser(@Param("user") String user);

    /**
     * 查询用户的所有审核通过视频列表个数
     * @param show
     * @return
     */
    int queryCountByShow(@Param("show") int show);

    /**
     * 分页查询最多点击视频列表
     * @param sPage
     * @param ePage
     * @return
     */
    List<VideoList> queryByClickPage(@Param("sPage") int sPage,@Param("ePage") int ePage);

    /**
     * 分页查询最新视频列表
     * @param sPage
     * @param ePage
     * @return
     */
    List<VideoList> queryByNewPage(@Param("sPage") int sPage,@Param("ePage") int ePage);

    /**
     * 查询所有视频列表
     * @return
     */
    int queryAllCountList();

    /**
     * 根据用户名分页查询
     * @param user
     * @param sPage
     * @param ePage
     * @return
     */
    List<VideoList> queryByUserPage(@Param("user") String user,@Param("sPage") int sPage,@Param("ePage") int ePage);

    /**
     * 查询所有未删除视频
     * @return
     */
    List<VideoList> queryAllList();
    /**
     * 审核视频列表
     * @param id
     * @param show
     */
    void updateByIdShow(@Param("id") int id,@Param("show") int show);

    /**
     * 删除视频
     * @param id
     */
    void deleteById(@Param("id") int id);

    /**
     * 根据审核信息查询
     * @param show
     * @return
     */
    List<VideoList> queryByShow(@Param("show") int show);
}
