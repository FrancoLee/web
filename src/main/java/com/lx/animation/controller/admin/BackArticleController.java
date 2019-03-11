package com.lx.animation.controller.admin;

import com.lx.animation.admin.Admin;
import com.lx.animation.article.Article;
import com.lx.animation.article.ArticleService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by root on 17-12-15.
 */
@Controller
public class BackArticleController {
    @Autowired
    @Qualifier("ArticleService")
    ArticleService articleService;

    @RequestMapping("/back/art/process")
    public String getArticleProcess(Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        System.out.println(admin.getUser());
        if (admin != null) {
            List<Article> articleList = articleService.getArticleProcess(admin.getUser());
            if (articleList != null) {
                model.addAttribute("art_list", articleList);
                return "/back/web/article_process";
            }
        }
        return "/web/no";

    }

    @RequestMapping("/back/art/list")
    public String getArticleNoDeleteList(Model model) {
        List<Article> articleList = articleService.getAllArticleByDelete(false);
        if (articleList != null) {
            model.addAttribute("art_list", articleList);
            model.addAttribute("c", "全部分类");
            return "/back/web/article_list";
        }
        return "/web/no";
    }

    @RequestMapping("/back/art/select_class")
    @ResponseBody
    public String getClassification(@RequestParam(value = "class", required = false, defaultValue = "0") int cla) {
        JSONArray json = articleService.getClassification(cla);
//        System.out.println(json.toString());
        return json.toString();
    }

    //'/back/art/delete?artId='+id
    @RequestMapping("/back/art/delete")
    @ResponseBody
    public String deleteById(@RequestParam(value = "artId", required = false, defaultValue = "-1") int id) {
        if (id < 0)
            return "0";
        if (articleService.delArticleToRecycle(id))
            return "1";
        return "0";
    }

    @RequestMapping("/back/art/delete_batch")
    @ResponseBody
    public String deleteByArrId(@RequestParam(value = "check", required = false, defaultValue = "") String check) {
        if (articleService.delArticleToRecycle(check))
            return "1";
        return "0";
    }

    @RequestMapping("/back/art/check")
    @ResponseBody
    public String check(@RequestParam(value = "artId", required = false, defaultValue = "-1") int artId,
                        @RequestParam(value = "check", required = false, defaultValue = "0") int check) {
        if (articleService.checkArticle(check, artId))
            return "1";
        return "0";
    }

    //    mark
    @RequestMapping("/back/art/recycle")
    public String Recycle(Model model) {
        List<Article> articleList = articleService.getAllArticleByDelete(true);
        if (articleList != null) {
            model.addAttribute("art_list", articleList);
            return "/back/web/article_del";
        }
        return "/web/no";
    }

    @RequestMapping("/back/art/delete_complete")
    @ResponseBody
    public String deleteComplete(@RequestParam(value = "artId", required = false, defaultValue = "-1") int artId) {
//        if(artId<0)
//            return "0";
        if (articleService.deleteComplete(artId)) {
            return "1";
        }
        return "0";
    }

    @RequestMapping("/back/art/deleteBatch_complete")
    @ResponseBody
    public String deleteBatchComplete(@RequestParam(value = "check", required = false, defaultValue = "") String check) {
        if (articleService.deleteBatchComplete(check))
            return "1";
        return "0";
    }

    @RequestMapping("/back/art/reduction")
    @ResponseBody
    public String reduction(@RequestParam("artId") int artId) {
        if (articleService.reduction(artId))
            return "1";
        return "0";
    }
}
