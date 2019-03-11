package com.lx.animation.interceptor;

import com.lx.animation.admin.Admin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by root on 17-12-20.
 */
public class AdminInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getServletPath();
        if (url.endsWith(".js") || url.endsWith(".css"))
            return true;
        Admin admin = (Admin) httpServletRequest.getSession().getAttribute("admin");
        if (admin == null) {
            httpServletRequest.getRequestDispatcher("/admin/noLogin").forward(httpServletRequest, httpServletResponse);
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
