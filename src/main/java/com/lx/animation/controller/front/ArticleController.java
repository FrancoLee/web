package com.lx.animation.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.lx.animation.admin.Admin;
import com.lx.animation.article.Article;
import com.lx.animation.article.ArticleService;
import com.lx.animation.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by root on 17-12-11.
 */
@Controller
@SessionAttributes("user")
public class ArticleController {
    @Autowired
    @Qualifier("ArticleService")
    ArticleService articleService;

    @RequestMapping("/article/show")
    public String showArticle(HttpSession session,
                              Model model,
                              @RequestParam(value = "artId", required = false, defaultValue = "-1") int id) {
        if (id < 0)
            return "/web/no";
        User user = (User) session.getAttribute("user");
        Admin admin = (Admin) session.getAttribute("admin");
        Article article = articleService.getArticle(user, id, admin);
        if (article == null)
            return "/web/no";
        else {
            model.addAttribute("art", article);
            return "/web/show_article";
        }
    }

    @RequestMapping("/article/user_art")
    public String userArtList(HttpSession session, Model model, @RequestParam(name = "page", required = false, defaultValue = "1") int page) {

        User user = (User) session.getAttribute("user");
        if (articleService.getUserArtListPage(user, model, page)) {
            return "web/art_list";
        } else
            return "/web/no";
    }

    @RequestMapping("/article/delete")
    @ResponseBody
    public String deleteArt(HttpSession session,
                            @RequestParam("page") int page,
                            @RequestParam("artId") int artId) {
        User user = (User) session.getAttribute("user");
        JSONObject jsonObject = null;
        if (articleService.delArticle(user, artId)) {
            jsonObject = articleService.getUserArtListPage(user, page);
        } else {
            jsonObject = new JSONObject();
            jsonObject.put("error", 1);
        }
        return jsonObject.toString();
    }

    @RequestMapping("/article/click")
    public String ClickArt(Model model, @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
        if (articleService.getClickArtList(model, page))
            return "/web/art_click";
        else
            return "/web/no";
    }
}
