package com.lx.animation.video;

import org.springframework.stereotype.Component;

/**
 * Created by root on 17-5-6.
 */
@Component
public class Video {
    private int vid;
    private int epi;
    private String vidUrl;
    private int listId;
    private String title;
    private int delete;
    private int show;
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public int getEpi() {
        return epi;
    }

    public void setEpi(int epi) {
        this.epi = epi;
    }

    public String getVidUrl() {
        return vidUrl;
    }

    public void setVidUrl(String vidUrl) {
        this.vidUrl = vidUrl;
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
}
