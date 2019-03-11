package com.lx.animation.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

import java.util.Collection;

public class MyEventMulticaster extends SimpleApplicationEventMulticaster {
    public MyEventMulticaster(BeanFactory beanFactory){
        super(beanFactory);
    }
    public Collection<ApplicationListener<?>> getAppListers(){
        return super.getApplicationListeners();
    }
}
