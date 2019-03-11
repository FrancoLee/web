package com.lx.animation.admin;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * Created by root on 18-7-22.
 */

public interface AdminService {

    Admin login(String user,String password,String ip);

    void logout(HttpSession session);
}
