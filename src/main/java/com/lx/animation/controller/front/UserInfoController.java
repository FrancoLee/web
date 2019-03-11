package com.lx.animation.controller.front;

import com.lx.animation.user.User;
import com.lx.animation.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * Created by root on 17-12-11.
 */
@Controller
public class UserInfoController {
    @Autowired
    @Qualifier("UserService")
    UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);

    @RequestMapping("/user/info")
    public String userInfo(HttpSession session) {
        User user = (User) session.getAttribute("user");
        user = userService.getInfo(user.getUser());
        session.setAttribute("user", user);
        return "/web/user_info";
    }

    @RequestMapping("/user/pic_upload")
    public String picUpload(HttpSession session,
                            @RequestParam(value = "c_width", required = false, defaultValue = "0") String cWidth,
                            @RequestParam(value = "c_height", required = false, defaultValue = "0") String cHeight,
                            @RequestParam(value = "height", required = false, defaultValue = "600") String height,
                            @RequestParam("img") MultipartFile file) {
        User user = (User) session.getAttribute("user");
//        System.out.println(c_height+c_width+height);
//        System.out.println(file);
        userService.upload(file, cWidth, cHeight, height, user);
        return "redirect:/user/info";
    }

    @RequestMapping("/user/info_change")
    public String infoChange(HttpSession session,
                             @RequestParam("name") String name,
                             @RequestParam("hobby") String hobby,
                             @RequestParam("info") String info) {
        User user = (User) session.getAttribute("user");
        userService.infoChange(user.getUser(), name, hobby, info);
        return "redirect:/user/info";
    }

}
