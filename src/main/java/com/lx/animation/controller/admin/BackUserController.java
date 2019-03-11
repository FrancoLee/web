package com.lx.animation.controller.admin;

import com.lx.animation.user.User;
import com.lx.animation.user.UserService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by root on 17-12-18.
 */
@Controller
public class BackUserController {
    @Autowired
    @Qualifier("UserService")
    UserService userService;

    @RequestMapping("/back/users/show")
    public String getAllUser(Model model) {
        List<User> userList = null;
        userList = userService.getAllUser();
        if (userList != null)
            model.addAttribute("user_list", userList);
        model.addAttribute("user_class", "全部分类");
        return "/back/web/user_list";
    }

    @RequestMapping("/back/users/selectClass")
    @ResponseBody
    public String getClassification(@RequestParam("class") int cla) {
        JSONArray json = userService.getClassification(cla);
        return json.toString();
    }

    @RequestMapping("/back/users/state")
    @ResponseBody
    public String updateUserSeal(@RequestParam("state") String state, @RequestParam("user") String user) {
        if (userService.updateSeal(state, user))
            return "1";
        else
            return "0";
    }
}
