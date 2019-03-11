package com.lx.animation.controller.admin;

import com.lx.animation.admin.Admin;
import com.lx.animation.admin.AdminService;
import com.lx.animation.backinfo.BackInfo;
import com.lx.animation.backinfo.BackInfoService;
import com.lx.animation.util.AddressUtil;
import com.lx.animation.util.JwtTokenUtil;
import org.apache.commons.fileupload.RequestContext;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by root on 17-12-14.
 */
@Controller
public class BackIndexController {
    @Autowired
    @Qualifier("AdminService")
    AdminService adminService;
    @Autowired
    @Qualifier("BackInfoService")
    BackInfoService backInfoService;
    @Autowired
    AddressUtil addressUtil;
    @RequestMapping("/admin/noLogin")
    public String noLogin() {
        return "/back/web/no_login";
    }

    @RequestMapping("/admin")
    public String admin() {

        return "/back/web/login";
    }

    @RequestMapping("/admin/login")
    @ResponseBody
    public Boolean adminLogin(@RequestParam("user") String user,
                              @RequestParam("password") String password,
                              HttpSession session,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        String ip = new AddressUtil().getIP(request);
        Admin admin = adminService.login(user, password, ip);
        if (admin == null)
            return false;
//        String token = JwtTokenUtil.getToken(admin.getUser());
//        response.setHeader("token",token);
        session.setAttribute("admin", admin);
        return true;
    }

    @RequestMapping("/admin/logout")
    public String logout(HttpSession session) {
        adminService.logout(session);
        return "/back/web/login";
    }

    @RequestMapping("/back/index")
    public String index(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        BackInfo backInfo = backInfoService.getBackInfo();
        session.setAttribute("back_info", backInfo);
        return "/back/web/index";
    }

    @RequestMapping("/back/test")
    public String test(HttpServletRequest request, HttpServletResponse response) {
//        Enumeration enumeration=request.getAttributeNames();
//        while (enumeration.hasMoreElements()){
//            System.out.println(enumeration.nextElement());
//        }s
        //  System.out.println(admin.toString());
        WebApplicationContext webApplicationContext = (WebApplicationContext) request.getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT");
        String[] beanNames = webApplicationContext.getBeanNamesForType(Object.class);
        for (String beanName : beanNames) {
            Class<?> beanType = webApplicationContext.getType(beanName);
            if (beanType != null) {
                if (AnnotatedElementUtils.hasAnnotation(beanType, Controller.class) ||
                        AnnotatedElementUtils.hasAnnotation(beanType, RequestMapping.class))
                    System.out.println("is Handler:" + beanName);
            }
        }
        return "/back/web/index";
    }
}
