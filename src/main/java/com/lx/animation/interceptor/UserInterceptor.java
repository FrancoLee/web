package com.lx.animation.interceptor;

import com.lx.animation.user.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by root on 17-12-20.
 */
public class UserInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        User user=(User) httpServletRequest.getSession().getAttribute("user");
        System.out.println(httpServletRequest.getServletPath());
        System.out.println(user);
        if(user==null){
//            httpServletRequest.getRequestDispatcher("/index").forward(httpServletRequest,httpServletResponse);
            httpServletResponse.sendRedirect("/index");
            return false;
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
