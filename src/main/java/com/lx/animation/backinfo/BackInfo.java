package com.lx.animation.backinfo;

import org.springframework.stereotype.Component;

/**
 * Created by root on 17-12-14.
 */
@Component
public class BackInfo {
    private int countUser;
    private int countAdmin;
    private int countArt;
    private int countVideo;
    private int countVideoList;
    private int showArt;
    private int showVideo;
    private int showVideoList;
    private int showUser;
    private int sealUser;
    private long countClick;

    public int getSealUser() {
        return sealUser;
    }

    public void setSealUser(int sealUser) {
        this.sealUser = sealUser;
    }

    public int getCountVideoList() {
        return countVideoList;
    }

    public void setCountVideoList(int countVideoList) {
        this.countVideoList = countVideoList;
    }

    public int getShowArt() {
        return showArt;
    }

    public void setShowArt(int showArt) {
        this.showArt = showArt;
    }

    public int getShowVideo() {
        return showVideo;
    }

    public void setShowVideo(int showVideo) {
        this.showVideo = showVideo;
    }

    public int getShowVideoList() {
        return showVideoList;
    }

    public void setShowVideoList(int showVideoList) {
        this.showVideoList = showVideoList;
    }

    public int getShowUser() {
        return showUser;
    }

    public void setShowUser(int showUser) {
        this.showUser = showUser;
    }

    public int getCountUser() {
        return countUser;
    }

    public void setCountUser(int countUser) {
        this.countUser = countUser;
    }

    public int getCountAdmin() {
        return countAdmin;
    }

    public void setCountAdmin(int countAdmin) {
        this.countAdmin = countAdmin;
    }

    public int getCountArt() {
        return countArt;
    }

    public void setCountArt(int countArt) {
        this.countArt = countArt;
    }

    public int getCountVideo() {
        return countVideo;
    }

    public void setCountVideo(int countVideo) {
        this.countVideo = countVideo;
    }

    public long getCountClick() {
        return countClick;
    }

    public void setCountClick(long countClick) {
        this.countClick = countClick;
    }
}
