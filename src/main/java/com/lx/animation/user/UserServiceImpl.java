package com.lx.animation.user;


import com.lx.animation.util.HashPassUtil;
import com.lx.animation.util.ImgUtil;
import com.lx.animation.util.PicCut;
import com.lx.animation.util.UsersUtil;

import net.sf.json.JSONArray;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * Created by root on 17-12-10.
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    UsersUtil usersUtil;
    @Autowired
    private HashPassUtil hashPassUtil;
    @Autowired
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    /**
     * 用户登录
     *
     * @param request
     * @return
     */
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        HttpSession sessionId = usersUtil.query(username);
        if (sessionId != null) {
            usersUtil.remove(username);
            sessionId.invalidate();
        }
        String password = hashPassUtil.gethash(request.getParameter("password"));
        User user = userDao.queryUser(username, password);

        if (user != null) {
            if ("disable".equals(user.getState())) {
                return "1";
            }
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            //System.out.println(user.getUsername()+user.getAuthority());
            session.setAttribute("ip", ip);
            session.setAttribute("user", user);
            //session.setAttribute("authority", user.getAuthority());
            //记录登录的用户
            usersUtil.add(username, session);
            return "2";
        } else return "0";
    }

    /**
     * 用户登出
     *
     * @param session
     */
    public void logout(HttpSession session) {
        User u = (User) session.getAttribute("user");
        String user = null;
        if (u != null)
            user = u.getUser();
        if (user != null) {
            usersUtil.remove(user);
            session.removeAttribute("user");
        }
    }

    /**
     * 验证码
     *
     * @param request
     * @param response
     */
    public void checkImg(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");
        ImgUtil img = ImgUtil.Instance();
        //  System.out.println(img.getString());
        HttpSession session = request.getSession();
        session.setAttribute("check", img.getString());
        try {
            ImageIO.write(img.getImage(), "jpg", response.getOutputStream());
        } catch (IOException e) {
            LOGGER.error("response.getOutputStream");

        }
    }

    /**
     * 判断验证码是否正确
     *
     * @param str1
     * @param str2
     * @return
     */
    public boolean checkNum(String str1, String str2) {
        return str1.equalsIgnoreCase(str2);
    }

    /**
     * 用户注册
     * @param user
     * @param password
     * @return
     */
    /**
     * 用户注册
     *
     * @param user
     * @param password
     * @return
     */
    public boolean register(String user, String password) {
        String pass = hashPassUtil.gethash(password);
        try {
            userDao.addUser(user, pass);
        } catch (Exception e) {
            LOGGER.error(user + password + "添加用户错误");
            return false;
        }
        return true;
    }

    /**
     * 用户名是否可用验证
     *
     * @param user
     * @return
     */
    public boolean regCheck(String user) {
        int c = userDao.queryUserExist(user);
//        System.out.println(c);
        if (c == 0)
            return true;
        else
            return false;
    }

    /**
     * 获取用户信息
     *
     * @param u
     * @return
     */
    public User getInfo(String u) {
        User user = null;
        try {
            user = userDao.queryByUser(u);
        } catch (Exception e) {
            LOGGER.error("query by user fail");
            return null;
        }
        return user;
    }

    private static final long serialVersionUID = 1L;

    /**
     * 上传图片并剪切
     *
     * @param file
     * @param cWidth
     * @param cHeight
     * @param height
     * @param user
     * @return
     */
    public Boolean upload(MultipartFile file, String cWidth, String cHeight, String height, User user) {
        String path = System.getProperty("webRoot");
        PicCut ima = null;
        try {
            if (!file.isEmpty()) {
                File f = new File(path + "/user_pic");
                //System.out.println(path+value);
                if (!f.exists())
                    f.mkdirs();
                file.transferTo(new File(path + "/user_pic/" + user.getUser() + ".jpg"));
                userDao.updatePic(user.getUser(), "/user_pic/" + user.getUser() + ".jpg");
                ima = new PicCut(path + "/user_pic/" + user.getUser() + ".jpg");
                int c_w = Integer.parseInt(cWidth);
                if (c_w <= 0)
                    c_w = 0;
                int c_h = Integer.parseInt(cHeight);
                if (c_h <= 0)
                    c_h = 0;
                int he = Integer.parseInt(height);
                if (he <= 0)
                    he = 600;
                ima.set_size(600, 600, c_w, c_h, he);
            }
        } catch (IOException e) {
            LOGGER.error("upload pic fail");
            return false;
        }
        return true;
    }

    /**
     * 修改个人信息
     *
     * @param user
     * @param name
     * @param hobby
     * @param info
     */
    public void infoChange(String user, String name, String hobby, String info) {
        try {
            userDao.updateInfo(user, name, hobby, info);
        } catch (Exception e) {
            LOGGER.error("update info fail");
        }
    }

    /**
     * 获取所有用户信息
     *
     * @return
     */
    public List<User> getAllUser() {
        List<User> users = null;
        try {
            users = userDao.queryAllUser();
        } catch (Exception e) {
            LOGGER.error("get all user fail");
            return null;
        }
        return users;
    }

    /**
     * 分类获取
     *
     * @param c
     * @return
     */
    public JSONArray getClassification(int c) {
        JSONArray jsonArray = null;
        try {
            List<User> users = null;
            if (c < 0 || c > 1) {
                users = userDao.queryAllUser();
            } else
                users = userDao.queryAllUserByState(c == 1 ? "disable" : "regular");
            jsonArray = JSONArray.fromObject(users);
            jsonArray.add(0, c);
        } catch (Exception e) {
            LOGGER.error("getClassification fail");
            return null;
        }
        return jsonArray;
    }

    public boolean updateSeal(String state, String user) {
        try {
            userDao.updateByState(user, state);
        } catch (Exception e) {
            LOGGER.error("update user state fail");
            return false;
        }
        return true;
    }
}
