package com.lx.animation.user;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
public class User {
    private String user;
    private String picUrl;
    private String name;
    private String hobby;
    private String info;
    private Timestamp regTime;
    private String state;

    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", name='" + name + '\'' +
                ", hobby='" + hobby + '\'' +
                ", info='" + info + '\'' +
                ", time=" + regTime +
                ", state=" + state +
                '}';
    }

    public String getRegTime() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(regTime);
    }

    public void setRegTime(Timestamp regTime) {
        this.regTime = regTime;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    public String getName() {
        return name;
    }

    public String getHobby() {
        return hobby;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
