package com.lx.animation.user;

import net.sf.json.JSONArray;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by root on 18-7-22.
 */
public interface UserService {
    String login(HttpServletRequest request) ;
    void logout(HttpSession session) ;
    void checkImg(HttpServletRequest request, HttpServletResponse response) ;
    boolean checkNum(String str1, String str2) ;
    boolean register(String user, String password) ;
    boolean regCheck(String user) ;
    User getInfo(String u) ;
    Boolean upload(MultipartFile file, String cWidth, String cHeight, String height, User user) ;
    void infoChange(String user,String name,String hobby,String info);
    List<User> getAllUser();
    JSONArray getClassification(int c);
    boolean updateSeal(String state,String user);
}
