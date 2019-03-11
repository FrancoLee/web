package com.lx.animation.videolist;

import com.alibaba.fastjson.JSONObject;
import com.lx.animation.user.User;
import net.sf.json.JSONArray;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by root on 18-7-22.
 */
public interface VideoListService {
    List<VideoList> getNewList(int limit);

    List<VideoList> getClickList(int limit);

    JSONObject getUserVideoListPage(User user, int index);

    boolean getUserVideoListPage(User user, Model model, int index);

    VideoList getVideoList(User user, int id);

    int addVideoList(String title, User user, MultipartFile file);

    boolean getClickVideoListPage(Model model, int index);

    boolean getNewVideoListPage(Model model, int index);

    // JSONObject getClickVideoListPage(int index);
    boolean deleteVideoList(int listId, User user);

    List<VideoList> getAllVideoList();

    boolean updateVideoListShow(int listId, int show);

    boolean deleteVideoList(int id);

    JSONArray getClassification(int c);
}
