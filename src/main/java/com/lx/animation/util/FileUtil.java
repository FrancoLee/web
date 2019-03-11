package com.lx.animation.util;

import java.io.File;

/**
 * Created by root on 17-12-18.
 */
public class FileUtil {
    public static void deleteFile(String url){
        if(url!=null){
            String path=System.getProperty("webRoot");
            File file=new File(path+url);
            if(file.exists())
                file.delete();
        }
    }
}
