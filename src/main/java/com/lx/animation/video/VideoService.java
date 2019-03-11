package com.lx.animation.video;

import com.alibaba.fastjson.JSONObject;
import com.lx.animation.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by root on 18-7-22.
 */
public interface VideoService {
    List<Video> getVideoList(int id, User user);
    Video getVideo(int id);
    JSONObject videoUpload(User user, int listId, MultipartFile file);
    Boolean addVideo(int epi,int vid,String title);
    boolean deleteVideo(int vid,String username);
    boolean deleteVideoByListId(int id);
    List<Video> getVideoList(int id);
    boolean updateShowById(int id,int show);
    boolean deleteById(int id);
    boolean deleteByArrId(String check);
    int queryByNew(int listId);
}
