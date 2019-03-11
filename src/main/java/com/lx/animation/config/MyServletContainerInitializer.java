package com.lx.animation.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;

import javax.jws.HandlerChain;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

@HandlesTypes(value = {BeanFactoryPostProcessor.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("sss");
        for (Class<?> claz : set) {
            System.out.println(claz.getName());
        }
    }
}
