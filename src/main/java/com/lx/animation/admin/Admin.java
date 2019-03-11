package com.lx.animation.admin;

import com.lx.animation.util.AddressUtil;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by root on 17-5-13.
 */
@Component
public class Admin {
    private Integer id;
    private String user;
    private String password;
    private Timestamp lastTime;
    private long lastIp;
    private long nowIp;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastTime() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(lastTime);
    }

    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNowIp() {
        return new AddressUtil().ipToString(nowIp);
    }

    public void setNowIp(int nowIp) {
        this.nowIp = nowIp;
    }

    public String getLastIp() {
        return new AddressUtil().ipToString(lastIp);
    }

    public void setLastIp(long lastIp) {
        this.lastIp = lastIp;
    }
}
