package com.lx.animation.videolist;

import com.alibaba.fastjson.JSONObject;
import com.lx.animation.page.Page;
import com.lx.animation.user.User;
import com.lx.animation.util.FileUtil;
import com.lx.animation.util.PageUtil;
import com.lx.animation.video.VideoService;
import net.sf.json.JSONArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by root on 17-12-10.
 */
@Service("VideoListService")
public class VideoListServiceImpl implements VideoListService {
    @Autowired
    private VideoListDao videoListDao;
    @Autowired
    private VideoService videoService;
    private static final Logger LOGGER= LogManager.getLogger(VideoListService.class);

    /**
     * 获取最新的视频集
     * @param limit
     * @return
     */
    public List<VideoList> getNewList(int limit){
        List<VideoList> list=null;
        try {
            list=videoListDao.queryByNew(limit);
        } catch (Exception e) {
            LOGGER.error("videoLisDao query new fail");
        }
        return list;
    }

    /**
     * 获取最多点击的视频集
     * @param limit
     * @return
     */
    public List<VideoList> getClickList(int limit) {
        List<VideoList> list = null;
        try {
            list = videoListDao.queryByClick(limit);
        } catch (Exception e) {
            LOGGER.error("videoListDao query click fail");
        }
        return list;
    }

    /**
     * 获取分页的json
     * @param user
     * @param index
     * @return
     */
    public JSONObject getUserVideoListPage(User user,int index){
        JSONObject jsonObject=new JSONObject();
        int count;
        count=videoListDao.queryCountByUser(user.getUser());
        List<VideoList> videoLists =videoListDao.queryByUserPage(user.getUser(),(index-1)*5,index*5);
        //System.out.println(videoLists);
//        if(count>0&&videoLists.size()==0){
//            index--;
//            videoLists =videoListDao.queryByUserPage(user.getUser(),(index-1)*5,index*5);
//        }
//        if(videoLists.size()==0||count<0){
//            jsonObject.put("error",1);
//        }
        jsonObject.put("videos",videoLists);
        Page page=new PageUtil().setPage(index,count);
        jsonObject.put("counts",videoLists.size());
        jsonObject.put("page",page);
        return jsonObject;
    }
    /**
     * 获取用户视频列表
     * @param user
     * @param model
     * @param index
     * @return
     */
    public boolean getUserVideoListPage(User user, Model model, int index){
        int count;
        count=videoListDao.queryCountByUser(user.getUser());
        List<VideoList> videoLists =videoListDao.queryByUserPage(user.getUser(),(index-1)*5,index*5);
        if(videoLists==null||count<0)
            return false;
        model.addAttribute("show_video",videoLists);
        Page page=new PageUtil().setPage(index,count);
        model.addAttribute("size",videoLists.size());
        model.addAttribute("page",page);
        return true;
    }

    /**
     * 获取视频列表信息
     * @param user
     * @param id
     * @return
     */
    public VideoList getVideoList(User user,int id){
        String username=null;
        if(user!=null)
            username=user.getUser();
       VideoList videoList=null;
        try {
            videoListDao.updateClick(id,username);
            videoList=videoListDao.queryById(id,username);
        } catch (Exception e) {
            LOGGER.error("video list show Article fail");
            return null;
        }
        return videoList;
    }
    /**
     * 添加视频列表
     * @param title
     * @param user
     * @param file
     * @return
     */
    public int addVideoList(String title ,User user ,MultipartFile file){
        if(user==null||file==null)
            return -1;
        int id=-1;
        String path = System.getProperty("webRoot");
        String picUrl=null;
        try {
            if(!file.isEmpty()){
                File f=new File(path+"/video_pic/"+user.getUser());
                if(!f.exists())
                    f.mkdirs();
                picUrl="/video_pic/" + user.getUser() + "/" + String.valueOf(System.currentTimeMillis()) + file.getOriginalFilename();
                file.transferTo(new File(path+picUrl));
                VideoList videoList=new VideoList();
                videoList.setPicUrl(picUrl);
                videoList.setTitle(title);
                videoList.setUser(user.getUser());
                videoListDao.addList(videoList);
                id=videoList.getListId();
//                System.out.println(videoList.getListId());
            }
        } catch (IOException e) {
            LOGGER.error("add video List fail");
            return -1;
        }
        return id;
    }

    /**
     * 分页获取最多点击
     * @param model
     * @param index
     * @return
     */
    public boolean getClickVideoListPage( Model model, int index){
        try {
            int count;
            count=videoListDao.queryCountByShow(1);
//        if(pageIndex!=null)
//            index=Integer.parseInt(pageIndex);
            List<VideoList> videoLists =videoListDao.queryByClickPage((index-1)*5,index*5);
            if(videoLists==null||count<0)
                return false;
            model.addAttribute("show_video",videoLists);
            Page page=new PageUtil().setPage(index,count);
            model.addAttribute("size",videoLists.size());
            model.addAttribute("page",page);
        } catch (Exception e) {
            LOGGER.error("get click video list page fail");
            return false;
        }
        return true;
    }

    /**
     * 分页获取最新视频列表
     * @param model
     * @param index
     * @return
     */
    public boolean getNewVideoListPage(Model model,int index){
        try {
            int count;
            count=videoListDao.queryCountByShow(1);
//        if(pageIndex!=null)
//            index=Integer.parseInt(pageIndex);
            List<VideoList> videoLists =videoListDao.queryByNewPage((index-1)*5,index*5);
            if(videoLists==null||count<0)
                return false;
            model.addAttribute("show_video",videoLists);
            Page page=new PageUtil().setPage(index,count);
            model.addAttribute("size",videoLists.size());
            model.addAttribute("page",page);
        } catch (Exception e) {
            LOGGER.error("get New video list page fail");
            return false;
        }
        return true;
    }

//    /**
//     * 分页获取最新视频列表
//     * @param index
//     * @return
//     */
//    public JSONObject getClickVideoListPage(int index){
//        JSONObject jsonObject=new JSONObject();
//        int count;
//        count=videoListDao.queryAllCountList();
//        List<VideoList> videoLists =videoListDao.queryByNewPage((index-1)*5,index*5);
//        if(count>0&&videoLists.size()==0){
//            index--;
//            videoLists =videoListDao.queryByNewPage((index-1)*5,index*5);
//        }
//        if(videoLists.size()==0||count<0){
//            jsonObject.put("error",1);
//        }
//        jsonObject.put("videos",videoLists);
//        Page page=new PageUtil().setPage(index,count);
//        jsonObject.put("page",page);
//        return jsonObject;
//    }
    /**
     * 删除视频列表
     * @param listId
     * @param user
     * @return
     */
    public boolean deleteVideoList(int listId,User user){
        String username="";
        if(user!=null)
            username=user.getUser();
        try {
            int id=videoListDao.deleteByIdUser(listId,username);
            //System.out.println(id);
            if(id>0){
                videoService.deleteVideoByListId(listId);
            }
        } catch (Exception e) {
            LOGGER.error("delete video List");
            return false;
        }
        return true;

    }

    /**
     * 获取所有视频列表
     * @return
     */
    public List<VideoList> getAllVideoList(){
        List<VideoList> videoListList=null;
        try {
            videoListList=videoListDao.queryAllList();
        } catch (Exception e) {
            LOGGER.error("get all video list");
            return null;
        }
        return videoListList;
    }

    /**
     * 视频列表审核
     * @param listId
     * @param show
     * @return
     */
    public boolean updateVideoListShow(int listId,int show){
        try {
            videoListDao.updateByIdShow(listId,show);
        } catch (Exception e) {
            LOGGER.error("update video list show fail");
            return false;
        }
        return true;
    }

    /**
     * 删除视频
     * @param id
     * @return
     */
    public boolean deleteVideoList(int id){
        String picUrl=null;
        try {
            if(!videoService.deleteVideoByListId(id))
                return false;
            picUrl=videoListDao.queryPicUrlById(id);
            FileUtil.deleteFile(picUrl);
            videoListDao.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("delete video list fail");
            return false;
        }
        return true;
    }

    /**
     * 获取分类
     * @param c
     * @return
     */
    public JSONArray getClassification(int c){
        JSONArray jsonArray = null;
        try {
            List<VideoList> list = null;
            if (c < 0 || c > 2) {
                list = videoListDao.queryAllList();
                c=3;
            }else
                list=videoListDao.queryByShow(c);
            jsonArray = JSONArray.fromObject(list);
            jsonArray.add(0, c);
        } catch (Exception e) {
            LOGGER.error("getClassification fail");
            return null;
        }
        return jsonArray;
    }
}
