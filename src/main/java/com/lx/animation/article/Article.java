package com.lx.animation.article;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 17-4-30.
 */
@Component
public class Article {
    private BigInteger id;
    private String title;
    private String content;
    private String picUrl;
    private String artUser;
    private BigInteger click;
    private Date proTime;
    private String processor;
    private BigInteger linkId;
    private Date linkTime;
    private Date gmtCreate;
    private Date gmtModified;
    private String artState;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", artUser='" + artUser + '\'' +
                ", click=" + click +
                ", proTime=" + proTime +
                ", processor='" + processor + '\'' +
                ", linkId=" + linkId +
                ", linkTime=" + linkTime +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", artState='" + artState + '\'' +
                '}';
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getArtUser() {
        return artUser;
    }

    public void setArtUser(String artUser) {
        this.artUser = artUser;
    }

    public BigInteger getClick() {
        return click;
    }

    public void setClick(BigInteger click) {
        this.click = click;
    }

    public Date getProTime() {
        return proTime;
    }

    public void setProTime(Date proTime) {
        this.proTime = proTime;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public BigInteger getLinkId() {
        return linkId;
    }

    public void setLinkId(BigInteger linkId) {
        this.linkId = linkId;
    }

    public Date getLinkTime() {
        return linkTime;
    }

    public void setLinkTime(Date linkTime) {
        this.linkTime = linkTime;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getArtState() {
        return artState;
    }

    public void setArtState(String artState) {
        this.artState = artState;
    }
}
