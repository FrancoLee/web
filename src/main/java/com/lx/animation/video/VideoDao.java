package com.lx.animation.video;

import com.lx.animation.video.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by root on 17-12-9.
 */
@Repository
public interface VideoDao {
    /**
     * 添加视频
     *
     * @param video
     */
    void addVideo(Video video);

    /**
     * 获取视频信息
     * @param id
     * @return
     */
    Video queryById(@Param("id") int id);
    /**
     * 通过id修改视频信息
     *
     * @param vid
     * @param epi
     * @param title
     */
    void updateById(@Param("vid") int vid, @Param("epi") int epi, @Param("title") String title);

    /**
     * 通过vListId和用户名查询视频链接
     *
     * @param vListId
     * @param user
     * @return
     */
    List<String> queryUrlByListId(@Param("vListId") int vListId, @Param("user") String user);

    /**
     * 通过id和用户名查询视频链接
     *
     * @param vid
     * @param user
     * @return
     */
    String queryUrlByIdUser(@Param("vid") int vid, @Param("user") String user);

    /**
     * 通过id查询视频链接
     * @param vid
     * @return
     */
    String queryUrlById(@Param("vid") int vid);

    /**
     * 通过id和用户名删除
     * @param vid
     * @param user
     * @return
     */
    void deleteByIdUser(@Param("vid") int vid,@Param("user") String user);

    /**
     * 通过vListId和epi查询视频信息
     * @param vListId
     * @param epi
     * @return
     */
    Video queryByListIdEpi(@Param("vListId") int vListId,@Param("epi") int epi);

    /**
     * 查询所有视频
     * @return
     */
    int queryAllVideo();

    /**
     * 查询所有审核通过的视频
     * @return
     */
    int queryAllShow();

    /**
     * 查询视频集合中所有审核未通过的视频数量
     * @return
     */
    int queryAllListShow();

    /**
     * 通过vListId查询list下所有视频
     * @param vListId
     * @return
     */
    List<Video> queryByListId(@Param("listId") int listId);

    /**
     * 通过listId和用户名查询视频信息
     * @param listId
     * @param user
     * @return
     */
    List<Video> queryByListIdUser(@Param("listId") int listId,@Param("user") String user);

    /**
     * 通过id修改show
     * @param vid
     * @param show
     */
    void updateShow(@Param("vid") int vid,@Param("show") int show);

    /**
     * 通过id数组获取视频
     * @param arrId
     * @return
     */
    List<String> queryUrlByArrId(String[] arrId);

    /**
     * 通过id数组删除视频
     * @param arrId
     */
    void deleteByArrId(String[] arrId);

    /**
     * 通过id删除视频
     * @param vid
     */
    void deleteById(@Param("vid") int vid);

    /**
     * 获取视频链接
     * @param listId
     * @return
     */
    List<String> queryUrlByAdminListId(@Param("listId") int listId);

    /**
     * 根据listId删除视频
     * @param listId
     */
    void deleteByListId(@Param("listId") int listId);

    /**
     * 根据listId查询未审核数量
     * @param listId
     */
    int queryCountShowByListId(@Param("listId") int listId);
}
