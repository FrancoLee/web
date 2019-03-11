package com.lx.animation.config;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class MyApplicationContext extends AnnotationConfigWebApplicationContext {
    private ApplicationEventMulticaster applicationEventMulticaster=null;
    public MyApplicationContext(){

    }
    @Override
    protected void onRefresh() throws BeansException {
        System.out.println(this.applicationEventMulticaster);
    }
}
