package com.lx.animation.article;

import com.alibaba.fastjson.JSONObject;
import com.lx.animation.admin.Admin;
import com.lx.animation.page.Page;
import com.lx.animation.user.User;
import com.lx.animation.util.FileUtil;
import com.lx.animation.util.PageUtil;
import net.sf.json.JSONArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;

import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by root on 17-12-9.
 */
@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    private static final Logger LOGGER = LogManager.getLogger(ArticleServiceImpl.class);

    /**
     * 获取最新的文章列表
     *
     * @param limit
     * @return
     */
    @Override
    public List<Article> getArticleList(int limit) {
        List<Article> list = null;
        try {
            list = articleDao.listByClick(5);
        } catch (Exception e) {
            LOGGER.error("articleDao.listByClick fail");
            return null;
        }
        return list;
    }

    /**
     * 添加文章
     *
     * @param user
     * @param title
     * @param content
     * @return
     */
    @Transactional
    public BigInteger addArticle(String user, String title, String content) {
        Article article = new Article();
        article.setId(BigInteger.ZERO);
        String artPic = "";
        if (user != null && title != null || content != null) {
            Pattern p = Pattern.compile("<img .*?src=['\"]([^<>]*?)['\"]");
            Matcher m = p.matcher(content);
            if (m.find()) {
                artPic = m.group(1);
            }
            article.setContent(content);
            try {
                articleDao.addContent(article);
            } catch (Exception e) {
                LOGGER.error("add Content fail");
            }
            if (article.getLinkId() != null) {
                article.setArtUser(user);
                System.out.println(article.getArtUser());
                article.setPicUrl(artPic);
                article.setTitle(title);
                try {
                    articleDao.addArticle(article);
                } catch (Exception e) {
                    LOGGER.error("add article fail");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
            }
        }
        return article.getId();
    }

    /**
     * 获取文章
     *
     * @param user
     * @param id
     * @return
     */
    public Article getArticle(User user, int id, Admin admin) {
        String username = null;
        Article article = null;
        try {
            if (admin != null) {
                article = articleDao.getByIdOnAdmin(id);
            } else {
                if (user != null)
                    username = user.getUser();
                articleDao.updateClick(id, username);
                article = articleDao.getById(id, username);
            }
        } catch (Exception e) {
            LOGGER.error("article show Article fail");
            return null;
        }
        return article;
    }

    /**
     * 分页获取用户文章
     *
     * @param user
     * @param index
     * @return
     */
    public JSONObject getUserArtListPage(User user, int index) {
        JSONObject jsonObject = new JSONObject();
        int count;
        count = articleDao.countByUser(user.getUser());
        List<Article> articleList = articleDao.countByPageUser(user.getUser(), (index - 1) * 5, index * 5);
        // System.out.println(articleList);
//        if(count>0&&articleList.size()==0){
//            index--;
//            articleList =articleDao.countByPageUser(user.getUser(),(index-1)*5,index*5);
//        }
//        if(articleList.size()==0||count<0){
//            jsonObject.put("error",1);
//        }
        jsonObject.put("articles", articleList);
        Page page = new PageUtil().setPage(index, count);
        jsonObject.put("counts", articleList.size());
        jsonObject.put("page", page);
        return jsonObject;
    }

    /**
     * 分页获取文章列表
     *
     * @param user
     * @param model
     * @param index
     * @return
     */
    public boolean getUserArtListPage(User user, Model model, int index) {
        int count;
        String username = "";
        if (user != null)
            username = user.getUser();
        count = articleDao.countByUser(username);
        List<Article> userList = articleDao.countByPageUser(username, (index - 1) * 5, index * 5);
        if (userList == null || count < 0)
            return false;
        model.addAttribute("show_art", userList);
        model.addAttribute("size", userList.size());
        Page page = new PageUtil().setPage(index, count);
        model.addAttribute("page", page);
        return true;
    }

    /**
     * 删除文章
     * mark
     *
     * @param user
     * @param id
     * @return
     */
    public boolean delArticle(User user, int id) {
        try {
            Article article = articleDao.getContentById(id);
//            if (user == null || !user.getUser().equals(article.getUser())) {
//                return false;
//            }
            String content = article.getContent();
            String path = System.getProperty("webRoot");
            if (content != null) {
                Pattern p = Pattern.compile("<img .*?src=['\"]([^<>]*?)['\"]");
                Matcher m = p.matcher(content);
                while (m.find()) {
                    FileUtil.deleteFile(m.group(1));
                }
            }
            articleDao.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("delete article fail");
            return false;
        }
        return true;
    }

    /**
     * 获取点击最多的文章
     *
     * @param model
     * @param index
     * @return
     */
    public boolean getClickArtList(Model model, int index) {
        int count;
        try {
            count = articleDao.countByStatus("accept");
        } catch (Exception e) {
            LOGGER.error("query count by click fail");
            return false;
        }
//        if(pageIndex!=null)
//            index=Integer.parseInt(pageIndex);
        List<Article> userList = null;
        try {
            userList = articleDao.listByAllClick((index - 1) * 5, index * 5);
        } catch (Exception e) {
            LOGGER.error("query all click fail");
            return false;
        }
        if (userList == null || count < 0)
            return false;
        model.addAttribute("show_art", userList);
        Page page = new PageUtil().setPage(index, count);
        model.addAttribute("size", userList.size());
        model.addAttribute("page", page);
        return true;
    }

    /**
     * 获取所有文章
     *
     * @return
     */
    public List<Article> getAllArticleByDelete(boolean isDelete) {
        List<Article> articleList = null;
        try {
            if (isDelete)
                articleList = articleDao.listByStatus("delete");
            else
                articleList = articleDao.listByNoStatus("delete");
        } catch (Exception e) {
            LOGGER.error("query no delete fail");
            return null;
        }
        return articleList;
    }

    /**
     * 获取文章分类json
     *
     * @param c
     * @return
     */
    public JSONArray getClassification(int c) {
        JSONArray jsonArray = null;
        try {
            List<Article> list = null;
            if (c < 0 || c > 2) {
                list = articleDao.listByNoStatus("delete");
                c = 3;
            } else
                list = articleDao.queryByShow(c);
            jsonArray = JSONArray.fromObject(list);
            jsonArray.add(0, c);
//            System.out.println(jsonArray.toString());
        } catch (Exception e) {
            LOGGER.error("getClassification fail");
            return null;
        }
        return jsonArray;
    }

    /**
     * 放入回收站
     *
     * @param id
     * @return
     */
    public boolean delArticleToRecycle(int id) {
        try {
            articleDao.updateDelete(id, 1);
        } catch (Exception e) {
            LOGGER.error("delete article id fail");
            return false;
        }
        return true;
    }

    /**
     * 批量放入回收站
     *
     * @param check
     * @return
     */
    public boolean delArticleToRecycle(String check) {
        try {
            String[] arrId = check.split(",");
            articleDao.updateByArrIdToDelete(1, arrId);
        } catch (Exception e) {
            LOGGER.error("delete batch fail");
            return false;
        }
        return true;
    }

    /**
     * 审核文章
     *
     * @param check
     * @param id
     * @return
     */
    public boolean checkArticle(int check, int id) {
        try {
            articleDao.updateStatus(id, check);
        } catch (Exception e) {
            LOGGER.error("check article fail");
            return false;
        }
        return true;
    }

    /**
     * 恢复文章
     *
     * @param id
     * @return
     */
    public boolean reduction(int id) {
        try {
            articleDao.updateDelete(id, 0);
        } catch (Exception e) {
            LOGGER.error("reduction fail");
            return false;
        }
        return true;
    }

    /**
     * 彻底删除文章
     *
     * @param id
     * @return
     */
    public boolean deleteComplete(int id) {
        try {
            articleDao.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("complete delete fail");
            return false;
        }
        return true;
    }

    /**
     * 批量彻底删除
     *
     * @param check
     * @return
     */
    public boolean deleteBatchComplete(String check) {
        String[] arrId = check.split(",");
        try {
            List<String> stringList = articleDao.listContentByArrId(arrId);
            String path = System.getProperty("webRoot");
            for (String str :
                    stringList) {
                Pattern p = Pattern.compile("<img .*?src=['\"]([^<>]*?)['\"]");
                Matcher m = p.matcher(str);
                while (m.find()) {
                    FileUtil.deleteFile(m.group(1));
                }
            }
            articleDao.deleteByArrId(arrId);
        } catch (Exception e) {
            LOGGER.error("complete delete batch fail");
            return false;
        }
        return true;
    }

    public List<Article> getArticleProcess(String processor) {
        List<Article> articleList = null;
        try {
            articleDao.updateArticleStateToProcessing(processor);

            articleList = articleDao.listUnprocessedByProcessor(processor);
        } catch (Exception e) {
            LOGGER.error(" get article unprocessed fail");

        }
        return articleList;
    }
}
