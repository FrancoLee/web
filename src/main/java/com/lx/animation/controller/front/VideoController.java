package com.lx.animation.controller.front;

import com.lx.animation.user.User;
import com.lx.animation.video.Video;
import com.lx.animation.video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by root on 17-12-13.
 */
@Controller
public class VideoController {
    @Autowired
    @Qualifier("VideoService")
    VideoService videoService;

    @RequestMapping("/video/play")
    public String playVideo(Model model, @RequestParam(value = "id", required = false, defaultValue = "-1") int id,
                            @RequestParam(value = "epi", required = false, defaultValue = "-1") int epi) {
        if (id < 0 && epi < 0)
            return "/web/no";
        Video video = videoService.getVideo(id);
        model.addAttribute("video_play", video);
        return "/web/play_video";
    }

    @RequestMapping("/video/delete")
    public String deleteVideo(@RequestParam("vid") int vid,
                              @RequestParam("listId") int listId,
                              HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "/web/no";
        if (videoService.deleteVideo(vid, user.getUser()))
            return "redirect:/videoList/show_list?id=" + listId;
        else
            return "/web/no";
    }
}
