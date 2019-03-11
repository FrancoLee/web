package com.lx.animation.videolist;


import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by root on 17-5-4.
 */
@Component
public class VideoList {
    private int listId;
    private String title;
    private String picUrl;
    private String user;
    private int click;
    private Timestamp time;
    private int Delete;
    private int Show;
    private int maxEpi;
    private int countNew;

    public int getCountNew() {
        return countNew;
    }

    public void setCountNew(int countNew) {;
        this.countNew = countNew;
    }

    public int getMaxEpi() {
        return maxEpi;
    }

    public void setMaxEpi(int maxEpi) {
        this.maxEpi = maxEpi;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public String getTime() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getDelete() {
        return Delete;
    }

    public void setDelete(int delete) {
        this.Delete = delete;
    }

    public int getShow() {
        return Show;
    }

    public void setShow(int show) {
        this.Show = show;
    }
}
