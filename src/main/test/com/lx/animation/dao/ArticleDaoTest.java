package com.lx.animation.dao;

import com.lx.animation.article.Article;
import com.lx.animation.article.ArticleDao;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

/**
 * Created by root on 17-12-9.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext.xml"})
public class ArticleDaoTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private ArticleDao articleDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(AdvDaoTest.class);

    @Test
    public void test() {
//        String str="9,10,11";
//        String[] s=str.split(",");
//        try {
//            List<Article> articleList=articleDao.queryByShow(1);
//            for (Article article:articleList
//                 ) {
//                System.out.println(article.toString());
//            }
//            articleDao.updateByArrIdToDelete(1,s);
//        } catch (Exception e) {
//            LOGGER.error("error");
//
//        }
//        List<Article>list = null;
//        try {
//            list = articleDao.listByClick(5);
//        } catch (Exception e) {
//            System.out.println("sss");
//            LOGGER.error("q23");
//
//        }
//        for (Article a :
//                list) {
//            System.out.println(a.getPicUrl());
//        }
//        Article article=new Article();
//        article.setUser("Qwest");
//        article.setContent("content");
//        article.setPicUrl("artPic");
//        article.setTitle("title");
//        articleDao.addArticle(article);
//

        List<Article> List=articleDao.queryByShow(1);
        List.size();
//       JSONArray jsonArray= JSONArray.fromObject(List);
//        jsonArray.add(0,"1");
//       System.out.println(jsonArray.toString());
        String check="51,52";
        String[] arrId=check.split(",");
        List<String> stringList=articleDao.listContentByArrId(arrId);
        for (String str :
                stringList) {
            System.out.println(str);
        }
    }
}

