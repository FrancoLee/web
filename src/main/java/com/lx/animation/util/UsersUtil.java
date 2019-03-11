package com.lx.animation.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by root on 17-4-29.
 */
@Component
public class UsersUtil {
    private Map<String,HttpSession> userlist=new ConcurrentHashMap<String, HttpSession>();
    public  void add(String user,HttpSession session){
        synchronized (userlist) {
            userlist.put(user, session);
        }
    }
    public  HttpSession query(String user){
        synchronized (userlist) {
            return userlist.get(user);
        }
    }
    public  void remove(String user){
        synchronized (userlist) {
            userlist.remove(user);
        }
    }
    public List<String> getAllUsers(){
        List<String> list=new ArrayList<String>();
        synchronized (userlist){
            for(Map.Entry<String,HttpSession> user:userlist.entrySet()){
                list.add(user.getKey()+" "+user.getValue());
            }
        }
        return list;
    }
}
