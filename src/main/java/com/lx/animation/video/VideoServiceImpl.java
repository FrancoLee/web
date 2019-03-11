package com.lx.animation.video;

import com.alibaba.fastjson.JSONObject;
import com.lx.animation.user.User;
import com.lx.animation.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by root on 17-12-12.
 */
@Service("VideoService")
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoDao videoDao;
    private static final Logger LOGGER= LogManager.getLogger(VideoService.class);

    /**
     * 获取用户视频列表
     * @param id
     * @param user
     * @return
     */
    public List<Video> getVideoList(int id,User user){
        List<Video> videos=null;
        String username=null;
        if(user!=null)
            username=user.getUser();
        try {
            videos=videoDao.queryByListIdUser(id,username);
        } catch (Exception e) {
            LOGGER.error("get videos fail");
            return null;
        }
        return videos;
    }

    /**
     * 获取视频信息
     * @param id
     * @return
     */
    public Video getVideo(int id){
        Video video=null;
        try {
            video=videoDao.queryById(id);
        } catch (Exception e) {
            LOGGER.error("get video fail");
            return null;
        }
        return video;
    }

    /**
     * 上传视频文件
     * @param user
     * @param listId
     * @param file
     * @return
     */
    public JSONObject videoUpload(User user, int listId, MultipartFile file){
        JSONObject json=new JSONObject();
        if(user==null||user.getUser()==null||listId<0||file==null||file.isEmpty()){
            json.put("vid",-1);
            return json;
        }

        String videoUrl=null;
        String path = System.getProperty("webRoot");
        String username=user.getUser();
        try {
            if(!file.isEmpty()){
                File f = new File(path + "/user_video/"+username);
                if(!f.exists())
                    f.mkdirs();
                videoUrl= "/user_video/"+username+"/" +String.valueOf(System.currentTimeMillis()+file.getOriginalFilename());
                file.transferTo(new File(path +videoUrl));
            }
            Video video=new Video();
            video.setVidUrl(videoUrl);
            video.setListId(listId);
            video.setUser(username);
            videoDao.addVideo(video);
            json.put("vid",video.getVid());
            return json;
        } catch (IOException e) {
          e.printStackTrace();
            json.put("vid",-1);
            return json;
        }

    }

    /**
     * 添加视频信息
     * @param epi
     * @param vid
     * @param title
     * @return
     */
    public Boolean addVideo(int epi,int vid,String title){
        try {
            videoDao.updateById(vid,epi,title);
        } catch (Exception e) {
            LOGGER.error("add video fail");
            return false;

        }
        return true;
    }
    public boolean deleteVideo(int vid,String username){
        try {
            videoDao.deleteByIdUser(vid,username);
        } catch (Exception e) {
            LOGGER.error("delete video fail");
            return false;
        }
        return true;
    }

    /**
     * 根据listId删除视频
     * @param id
     * @return
     */
    public boolean deleteVideoByListId(int id){
        try {
            List<String> picList=videoDao.queryUrlByAdminListId(id);

            String path=System.getProperty("webRoot");
            for (String url :
                    picList) {
                FileUtil.deleteFile(url);
            }
            videoDao.deleteByListId(id);
        } catch (Exception e) {
            LOGGER.error("delete video by list id fail");
            return false;
        }
        return true;
    }

    /**
     * 后台获取视频列表
     * @param id
     * @return
     */
    public List<Video> getVideoList(int id){
        List<Video> videos=null;
        try {
            videos=videoDao.queryByListId(id);
        } catch (Exception e) {
            LOGGER.error("get videos fail");
            return null;
        }
        return videos;
    }

    /**
     * 审核视频
     * @param id
     * @param show
     * @return
     */
    public boolean updateShowById(int id,int show){
        try {
            videoDao.updateShow(id,show);
        } catch (Exception e) {
            LOGGER.error("update show fail");
            return false;
        }
        return true;
    }

    /**
     * 删除视频
     * @param id
     * @return
     */
    public boolean deleteById(int id){
        try {
            String  url=videoDao.queryUrlById(id);
            FileUtil.deleteFile(url);
            videoDao.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("delete video fail");
            return false;
        }
        return true;
    }

    /**
     * 批量删除
     * @param check
     * @return
     */
    public boolean deleteByArrId(String check){
        try {
            String[] arrId=check.split(",");
            List<String> stringList=videoDao.queryUrlByArrId(arrId);
            //System.out.println("123");
            for (String url :
                    stringList) {
                FileUtil.deleteFile(url);
            }
            videoDao.deleteByArrId(arrId);
        } catch (Exception e) {
            LOGGER.error("batch delete video fail");
            return false;
        }
        return true;
    }
    public int queryByNew(int listId){
        int count;
        try {
            count=videoDao.queryCountShowByListId(listId);
        } catch (Exception e) {
            LOGGER.error("query new fail");
            return -1;
        }
        return count;
    }
}
