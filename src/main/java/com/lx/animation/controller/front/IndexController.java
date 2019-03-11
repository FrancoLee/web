package com.lx.animation.controller.front;

import com.lx.animation.adv.Adv;
import com.lx.animation.adv.AdvService;
import com.lx.animation.article.Article;
import com.lx.animation.article.ArticleService;
import com.lx.animation.user.UserService;
import com.lx.animation.videolist.VideoList;
import com.lx.animation.videolist.VideoListService;
import com.lx.animation.webInfo.WebInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by root on 17-12-5.
 */
@Controller
public class IndexController {
    @Autowired
    @Qualifier("ArticleService")
    ArticleService articleService;
    @Autowired
    @Qualifier("VideoListService")
    VideoListService videoListService;
    @Autowired
    @Qualifier("AdvService")
    AdvService advService;
    @Autowired
    @Qualifier("UserService")
    UserService userService;
    @Autowired
    WebInfoService webInfoService;
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {

        List<Article> articleList = articleService.getArticleList(5);
        ServletContext context = request.getServletContext();
        context.setAttribute("articleClick", articleList);
        List<VideoList> newList = videoListService.getNewList(5);
        context.setAttribute("videoListNew", newList);
        List<VideoList> clickList = videoListService.getClickList(5);
        context.setAttribute("videoListClick", clickList);
        Adv logo = advService.getLogo();
        context.setAttribute("logo", logo);
        List<Adv> advList = advService.listCarousel();
        context.setAttribute("advList", advList);
        webInfoService.updateClick();
        return "/web/index";
    }

    @RequestMapping("/index/login")
    @ResponseBody
    public String login(HttpServletRequest request) {
        return userService.login(request);
    }

    @RequestMapping("/index/logout")
    public String logout(HttpSession session) {
        userService.logout(session);
        return "redirect:/index";
    }

    @RequestMapping("/index/check_num")
    @ResponseBody
    public String checkNum(@RequestParam("num") String num, HttpSession session) {
        String s1 = (String) session.getAttribute("check");
        if (userService.checkNum(num, s1))
            return "1";
        else
            return "0";
    }

    @RequestMapping("/index/Check_img")
    public void CheckImg(HttpServletRequest request, HttpServletResponse response) {
        // System.out.println("sss");
        userService.checkImg(request, response);
    }

    @RequestMapping("/index/register")
    public String register(@RequestParam("username") String user, @RequestParam("password") String password, HttpServletRequest request) {
        if (userService.register(user, password)) {
            userService.login(request);
            return "/web/index";
        } else
            return "/web/no";
    }

    @RequestMapping("/index/reg_check")
    @ResponseBody
    public String regCheck(@RequestParam("username") String user) {
        //     System.out.println("123");
        boolean t = userService.regCheck(user);
        //   System.out.println("456");
        if (t) {
//            System.out.println("0");
            return "1";
        } else {
//            System.out.println("1");
            return "0";
        }
    }

}
