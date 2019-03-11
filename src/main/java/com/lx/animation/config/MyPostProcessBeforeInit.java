package com.lx.animation.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyPostProcessBeforeInit implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(final Object bean, String beanName) throws BeansException {
        if(bean instanceof MyInterface){
            List<String> arr=new ArrayList<>();
            arr.add("sss");
            ((MyInterface) bean).setQccc(arr);
//            System.out.println("xxx");
        }
        return bean;
    }
}
