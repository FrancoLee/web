package com.lx.animation.article;


import com.alibaba.fastjson.JSONObject;
import com.lx.animation.admin.Admin;
import com.lx.animation.article.Article;
import com.lx.animation.user.User;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by root on 18-7-22.
 */

public interface ArticleService {
    List<Article> getArticleList(int limit);

    BigInteger addArticle(String user, String title, String content);

    Article getArticle(User user, int id, Admin admin);

    JSONObject getUserArtListPage(User user, int index);

    boolean getUserArtListPage(User user, Model model, int index);

    boolean delArticle(User user, int id);

    boolean getClickArtList(Model model, int index);

    List<Article> getAllArticleByDelete(boolean isDelete);

    JSONArray getClassification(int c);

    boolean delArticleToRecycle(int id);

    boolean delArticleToRecycle(String check);

    boolean checkArticle(int check, int id);

    boolean reduction(int id);

    boolean deleteComplete(int id);

    boolean deleteBatchComplete(String check);

    List<Article> getArticleProcess(String user);
}
