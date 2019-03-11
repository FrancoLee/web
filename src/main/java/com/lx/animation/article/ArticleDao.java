package com.lx.animation.article;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by root on 17-12-8.
 */
@Repository
public interface ArticleDao {
    /**
     * 添加文章
     * @param article
     */
    void addArticle(Article article);

    /**
     * 添加文章内容
     * @param article
     */
    void addContent(Article article);
    /**
     * mark
     */
    List<Article> listArticleInfo();

    /**
     * 获取文章,包括内容
     * @return
     */
    List<Article> listArticleAll();
    /**
     * 根据id删除文章
     *
     * @param id
     */
    void deleteById(@Param("id") int id);
    /**
     * mark
     * @param processor
     * @return
     */
    List<Article> listUnprocessedByProcessor(@Param("processor") String processor);
    /**
     * 修改5条未审核的记录的状态为正在审核
     */
    void updateArticleStateToProcessing(@Param("user") String user);

    /**
     * 将30分钟内未审核完的记录回退为未更新状态
     */
    void updateArticleStateToUnprocessed();
    /**
     * 修改文章状态
     * @param id
     * @param state
     */
    void updateArticleState(@Param("id") BigInteger id,@Param("state") String state);
    /**
     * 根据id删除文章内容
     * @param id
     */
    void deleteContentById(@Param("id") int id);
    /**
     * 根据id删除文章内容
     *
     * @param id
     * @return
     */
    Article getContentById(@Param("id") int id);

    /**
     * 根据id查询文章信息
     *
     * @param id
     * @return
     */
    Article getById(@Param("id") int id, @Param("user") String user);

    /**
     * 根据文章id和作者更新点击
     *
     * @param id
     * @param user
     */
    void updateClick(@Param("id") int id, @Param("user") String user);

    /**
     * 查询用户的所有文章数量
     *
     * @param user
     * @return
     */
    int countByUser(@Param("user") String user);

    /**
     * 根据用户名和页号获取文章
     *
     * @param user
     * @param ePage
     * @param sPage
     * @return
     */
    List<Article> countByPageUser(@Param("user") String user, @Param("sPage") int sPage, @Param("ePage") int ePage);

    /**
     * 查询审核通过的文章数量
     *
     * @return
     */
    int queryCountShow();
//    int queryCountNoShow();

    /**
     * 查询不是status的文章数量
     * @param status
     * @return
     */
    int countByNoStatus(@Param("status") String status);
    /**
     * 查询是status文章的数量
     * @param status
     * @return
     */
    int countByStatus(@Param("status") String status);
    /**
     * 根据点击率查询文章
     *
     * @param limit (记录条数)
     * @return
     */
    List<Article> listByClick(@Param("limit") int limit);

    /**
     * 根据分页点击率查询文章
     *
     * @param sPage
     * @param ePage
     * @return
     */
    List<Article> listByAllClick(@Param("sPage") int sPage, @Param("ePage") int ePage);

    /**
     * 查询所有文章数量
     *
     * @return
     */
    int listAllArticle();

    /**
     * 根据id数组查询content
     *
     * @param idArr
     * @return
     */
    List<String> listContentByArrId(String[] idArr);

    /**
     * 根据id数组删除文章
     *
     * @param idArr
     */
    void deleteByArrId(String[] idArr);

    /**
     * 根据审核情况查询文章
     * makr
     * @param show
     * @return
     */
    List<Article> queryByShow(@Param("show") int show);

    /**
     * 查询状态为state的文章
     * @param state
     * @return
     */
    List<Article> listByNoStatus(@Param("state") String state);

    /**
     * 查询状态不为state的文章
     * @param state
     * @return
     */
    List<Article> listByStatus(@Param("state") String state);


    /**
     * 审核文章
     *
     * @param id
     * @param show
     */
    void updateStatus(@Param("id") int id, @Param("show") int show);

    /**
     * 是否放入回收站
     *
     * @param id
     * @param delete
     */
    void updateDelete(@Param("id") int id, @Param("delete") int delete);

    /**
     * 根据id数组修改delete
     *
     * @param idArr
     */
    void updateByArrIdToDelete(@Param("delete") int delete, @Param("array") String[] idArr);

    /**
     * 查询已审核文章数量
     *
     * @return
     */
    int queryCountByClick();

    /**
     * 查询文章
     * @param id
     * @return
     */
    Article getByIdOnAdmin(@Param("id") int id);
}
