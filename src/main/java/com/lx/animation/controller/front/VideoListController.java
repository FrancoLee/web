package com.lx.animation.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.lx.animation.user.User;
import com.lx.animation.video.Video;
import com.lx.animation.videolist.VideoList;
import com.lx.animation.videolist.VideoListService;
import com.lx.animation.video.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by root on 17-12-12.
 */
@Controller
public class VideoListController {
    @Autowired
    @Qualifier("VideoListService")
    VideoListService videoListService;
    @Autowired
    @Qualifier("VideoService")
    VideoService videoService;
    private static final Logger LOGGER = LoggerFactory.getLogger(VideoListController.class);

    @RequestMapping("/videoList/user_video")
    public String userList(HttpSession session,
                           Model model,
                           @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        if (videoListService.getUserVideoListPage(user, model, page)) {
            return "/web/video_list";
        } else
            return "/web/no";
    }

    @RequestMapping("/videoList/show_list")
    public String showList(HttpSession session,
                           Model model,
                           @RequestParam(value = "listId", required = false, defaultValue = "-1") int id) {
        if (id < 0)
            return "/web/no";
        User user = (User) session.getAttribute("user");
        VideoList videoList = videoListService.getVideoList(user, id);
        List<Video> videos = videoService.getVideoList(id, user);
        model.addAttribute("videoList", videoList);
        model.addAttribute("videos", videos);
        return "/web/show_video";
    }

    @RequestMapping("/videoList/click")
    public String clickList(Model model,
                            @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
        if (videoListService.getClickVideoListPage(model, page)) {
            return "web/video_click";
        } else
            return "/web/no";

    }

    @RequestMapping("/videoList/new")
    public String NewList(Model model,
                          @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
        if (videoListService.getNewVideoListPage(model, page)) {
            return "web/video_new";
        } else
            return "/web/no";

    }


    @RequestMapping("/video/video_upload")
    @ResponseBody
    public String videoUpload(HttpSession session,
                              HttpServletRequest request,
                              @RequestParam(value = "vid", required = false, defaultValue = "-1") int id,
                              @RequestParam(value = "file", required = false, defaultValue = "") MultipartFile file
    ) {
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("");
        }
//        System.out.println(file);
//        if (!file.isEmpty())
//            System.out.println("qqq");
        //  String result = new ActionEnter(request, rootPath).exec();
        //   System.out.println(result);
        //  return result;
//        System.out.println("123");
//        String id=request.getParameter("id");
//        System.out.println(id);
        User user = (User) session.getAttribute("user");
        JSONObject json = null;
        json = videoService.videoUpload(user, id, file);
        // System.out.println(json.toJSONString());
        return json.toJSONString();
    }

    @RequestMapping("/videoList/add_video")
    public String addVideo(@RequestParam(value = "listId", required = false, defaultValue = "-1") int listId,
                           @RequestParam(value = "title") String title,
                           @RequestParam(value = "epi", required = false, defaultValue = "1") int epi,
                           @RequestParam(value = "vid", required = false, defaultValue = "-1") int vid) {
        if (listId < 0 || vid < 0)
            return "/web/no";
        if (videoService.addVideo(epi, vid, title)) {
            return "redirect:/videoList/show_list?listId=" + listId;
        } else
            return "/web/no";
    }

    @RequestMapping("/videoList/delete")
    @ResponseBody
    public String deleteVideoList(HttpSession session,
                                  @RequestParam(value = "listId", required = false, defaultValue = "-1") int listId,
                                  @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
        User user = (User) session.getAttribute("user");
        JSONObject jsonObject = null;
//        if (true) {
        if (videoListService.deleteVideoList(listId, user)) {
            jsonObject = videoListService.getUserVideoListPage(user, page);
            return jsonObject.toString();
        } else {
            jsonObject = new JSONObject();
            jsonObject.put("error", "删除失败");
            return jsonObject.toString();
        }

    }

}
