package com.lx.animation.controller.front;

import com.lx.animation.article.ArticleService;
import com.lx.animation.user.User;
import com.lx.animation.videolist.VideoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;

/**
 * Created by root on 17-12-11.
 */
@Controller
public class EditController {
    @Autowired
    @Qualifier("ArticleService")
    ArticleService articleService;
    @Autowired
    @Qualifier("VideoListService")
    VideoListService videoListService;

    @RequestMapping("/edit")
    public String edit() {
        return "/web/edit";
    }

    @RequestMapping("/article/add_art")
    public String addArt(HttpSession session,
                         @RequestParam(value = "title", required = false, defaultValue = "暂无") String title,
                         @RequestParam(value = "editorValue", required = false, defaultValue = "暂无") String content) {
        User user = (User) session.getAttribute("user");
        System.out.println(user.getUser());
        BigInteger id = articleService.addArticle(user.getUser(), title, content);
        if (id.compareTo(BigInteger.ZERO)==0)
            return "/web/edit";
        else
            return "redirect:/article/show?artId=" + id;
    }

    @RequestMapping("/videoList/add_videoList")
    public String addVideoList(HttpSession session,
                               @RequestParam(value = "pic", required = false, defaultValue = "") MultipartFile pic,
                               @RequestParam(value = "title", required = false, defaultValue = "暂无") String title) {
        User user = (User) session.getAttribute("user");
        int id = videoListService.addVideoList(title, user, pic);
        if (id < 0)
            return "/web/no";
        else
            return "redirect:/videoList/show_list?listId=" + id;
    }
}
