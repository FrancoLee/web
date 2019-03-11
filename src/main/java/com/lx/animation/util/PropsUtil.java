package com.lx.animation.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by root on 18-7-2.
 */
public final class PropsUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载属性文件
     */
    public static Properties loadProps(String fileName) {
        Properties props = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                throw new FileNotFoundException(fileName + "is not found");
            }
            props = new Properties();
            props.load(is);
        }
        catch (IOException e) {
            LOGGER.error("load properties file failure",e);
        }finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.error("close input file failure",e);
                }
            }
        }
        return props;
    }
}
