package com.lx.animation.controller.admin;

import com.lx.animation.video.Video;
import com.lx.animation.video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by root on 17-12-18.
 */
@Controller
public class BackVideoController {
    @Autowired
    @Qualifier("VideoService")
    VideoService videoService;

    @RequestMapping("/back/video/show")
    public String getVideos(Model model, @RequestParam("listId") int listId) {
        List<Video> videoList = videoService.getVideoList(listId);
        if (videoList != null)
            model.addAttribute("video_list", videoList);
        model.addAttribute("c", "全部分类");
        return "/back/web/video_show";
    }

    @RequestMapping("/back/video/batchDelete")
    @ResponseBody
    public String batchDelete(@RequestParam("check") String check) {
        if (videoService.deleteByArrId(check))
            return "1";
        else
            return "0";
    }

    @RequestMapping("/back/video/delete")
    @ResponseBody
    public String deleteVideo(@RequestParam("vid") int id) {
        if (videoService.deleteById(id))
            return "1";
        else
            return "0";
    }

    @RequestMapping("/back/video/updateShow")
    @ResponseBody
    public String updateShow(@RequestParam("vid") int vid, @RequestParam("show") int show) {
        if (videoService.updateShowById(vid, show))
            return "1";
        else
            return "0";
    }

    @RequestMapping("/back/video/queryNew")
    @ResponseBody
    public String queryNew(@RequestParam("listId") int listId) {
        int count = videoService.queryByNew(listId);
        if (count <= 0)
            return "1";
        else
            return "2";
    }
}
