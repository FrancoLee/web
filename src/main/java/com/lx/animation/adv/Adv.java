package com.lx.animation.adv;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by root on 17-5-11.
 */
@Component
public class Adv {
    private Integer id;
    private Integer type;
    private String picUrl;
    private String info;
    private Integer rank;

    public String getInfo() {
        return info;
    }


    public void setInfo(String info) {
        this.info = info == null ? "暂无" : info;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public int getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Adv{" +
                "id=" + id +
                ", type=" + type +
                ", pic_url='" + picUrl + '\'' +
                ", info='" + info + '\'' +
                ", rank=" + rank +
                '}';
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
