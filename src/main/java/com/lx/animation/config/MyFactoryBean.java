package com.lx.animation.config;

import com.lx.animation.util.JedisCache;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;

public class MyFactoryBean implements FactoryBean<JedisCache>,MyInterface {
    @Override
    public JedisCache getObject() throws Exception {
        return new JedisCache();
    }

    @Override
    public Class<?> getObjectType() {
        return JedisCache.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }


    @Override
    public void setQccc(List<String> list) {

    }
}
