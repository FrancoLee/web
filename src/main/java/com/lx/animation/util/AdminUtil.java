package com.lx.animation.util;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by root on 17-5-13.
 */
public class AdminUtil {
    private static Map<String,HttpSession> adminlist=new ConcurrentHashMap<String, HttpSession>();
    public static void add(String user,HttpSession session){
        synchronized (adminlist) {
            adminlist.put(user, session);
        }
    }
    public static HttpSession query(String user){
        synchronized (adminlist) {
            return adminlist.get(user);
        }
    }
    public static void remove(String user){
        synchronized (adminlist) {
            adminlist.remove(user);
        }
    }
}
