package com.lx.animation.article;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by root on 18-8-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext.xml"})
public class ArticleTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    ArticleDao articleDao;
//    @Autowired
//    Article article;
    @Autowired
    private ArticleService articleService;
    @Test
    public void test(){
//        List<Article> articleList;
//        articleList=articleDao.conId();
        Article article=new Article();
        article.setArtUser("newq");
        article.setTitle("123a");
        article.setPicUrl("");
        article.setContent("ssqe");
        articleService.addArticle("newq","1234a","sqeeqq");
//        articleDao.addArticle(article);
//        for(Article article:articleList)
//        System.out.println(article.toString());
//        String t="123";
//        BigInteger bigInteger=BigInteger.valueOf(2);
//        bigInteger=bigInteger.pow(60);
//        System.out.println(bigInteger);
//        articleDao.addContent(bigInteger,t);
//        for(Article article:articleList){//
//            System.out.println(article.toString());
//        }
//        Article article=new Article();
//        List<BigInteger> list=new ArrayList<BigInteger>();
//
//        for(int i=0;i<20;i++){
//            article.setPicUrl("!23");
//            article.setTitle(String.valueOf(i));
//            article.setArtUser("rqwe");
//            articleDao.addArticle(article);
//        }
//        for(BigInteger bigInteger:list){
//            System.out.println(bigInteger);
//        }
//        new TheadTest().run();
//        new TheadTest().run();
//        new TheadTest().run();
//        new TheadTest().run();
//        new TheadTest().run();

    }
}
