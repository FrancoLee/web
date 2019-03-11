package com.lx.animation.controller.admin;

import com.lx.animation.videolist.VideoList;
import com.lx.animation.videolist.VideoListService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by root on 17-12-17.
 */
@Controller
public class BackVideoListController {
    @Autowired
    @Qualifier("VideoListService")
    VideoListService videoListService;

    @RequestMapping("/back/videoList/index")
    public String getVideoList(Model model) {
        List<VideoList> videoList = videoListService.getAllVideoList();
        if (videoList != null)
            model.addAttribute("video_list", videoList);
        model.addAttribute("c", "全部分类");
        return "/back/web/video_list";
    }

    @RequestMapping("/back/videoList/updateShow")
    @ResponseBody
    public String updateVideoListShow(@RequestParam("listId") int listId,
                                      @RequestParam("show") int show) {
        if (videoListService.updateVideoListShow(listId, show))
            return "1";
        else
            return "0";
    }

    @RequestMapping("/back/videoList/select_class")
    @ResponseBody
    public String getClassification(@RequestParam(value = "class", required = false, defaultValue = "0") int cla) {
        JSONArray json = videoListService.getClassification(cla);
//        System.out.println(json.toString());
        return json.toString();
    }

    @RequestMapping("/back/videoList/deleteVideoList")
    @ResponseBody
    public String deleteVideoList(@RequestParam("listId") int listId) {
        if (videoListService.deleteVideoList(listId))
            return "1";
        else
            return "0";
    }


}
