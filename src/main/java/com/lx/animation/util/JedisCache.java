package com.lx.animation.util;


import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by root on 18-7-2.
 */

public class JedisCache implements Cache {
    private static final Logger LOGGER = LoggerFactory.getLogger(JedisCache.class);
    @Autowired
    private JedisConnectionFactory jedisPool;
    private String id;

    //        if(jedisPool==null){
//            Properties redisProperties=PropsUtil.loadProps("redis.properties");
//            JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
//            jedisPoolConfig.setMaxIdle(Integer.parseInt(redisProperties.getProperty("redisPool.maxIdle")));
//            jedisPoolConfig.setMaxTotal(Integer.parseInt(redisProperties.getProperty("redisPool.maxActive")));
//            jedisPoolConfig.setMinIdle(Integer.parseInt(redisProperties.getProperty("redisPool.minIdle")));
//            jedisPoolConfig.setMaxWaitMillis(Long.parseLong(redisProperties.getProperty("redisPool.maxWait")));
//            jedisPoolConfig.setTestOnBorrow(Boolean.parseBoolean(redisProperties.getProperty("redisPool.testOnBorrow")));
//            jedisPoolConfig.setTestOnReturn((Boolean) redisProperties.get("redisPool.testOnReturn"));
//            jedisPool=new JedisPool(jedisPoolConfig,redisProperties.getProperty("redis.host"), Integer.parseInt(redisProperties.getProperty("redis.port")), Integer.parseInt(redisProperties.getProperty("redis.timeout")),redisProperties.getProperty("redis.password"));
//            Jedis redis=jedisPool.getResource();
//
//        }
    @Override
    public String getId() {
        return id;
    }

    public void putObject(Object o, Object o1) {

    }

    public Object getObject(Object o) {
        return null;
    }

    public Object removeObject(Object o) {
        return null;
    }

    public void clear() {

    }

    public int getSize() {
        return 0;
    }

    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}

