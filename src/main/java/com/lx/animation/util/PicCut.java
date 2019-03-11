package com.lx.animation.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

/**
 * Created by root on 17-12-11.
 */
public class PicCut {
    String path;
    private static final Logger LOGGER= LoggerFactory.getLogger(PicCut.class);
    public PicCut(String path) {
        // TODO Auto-generated constructor stub
        this.path=path;
    }
    public void set_size(int height,int width,int x,int y,int c_w) {
        BufferedOutputStream out=null;
        BufferedInputStream in=null;
        try {
            //File srcf= new File(this.path) ;
            in=new BufferedInputStream(new FileInputStream(this.path));
            // 获取文件格式
            String ext = this.path.substring(this.path.lastIndexOf(".") + 1);
            Image image= ImageIO.read(in);
            BufferedImage tag=new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
            out=new BufferedOutputStream(new FileOutputStream(this.path));
            tag.getGraphics().drawImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            ImageIO.write(tag,ext, out);
            image_cut(x,y,c_w,ext);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            LOGGER.error("FileNotFound Exception");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            LOGGER.error("IOException");
        }finally {
            try {
                if(in!=null)
                    in.close();
                if(out!=null)
                    out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                LOGGER.error("IOException");
            }

        }
    }
    private void image_cut(int x,int y,int c_w,String ext) {
        ImageInputStream iis = null;
        FileInputStream is=null;
        try {
            is=new FileInputStream(this.path);
            // ImageReader声称能够解码指定格式
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(ext);
            // System.out.println(ext);
            ImageReader reader = it.next();
            iis = ImageIO.createImageInputStream(is);
            reader.setInput(iis, true);
            // 描述如何对流进行解码
            ImageReadParam param = reader.getDefaultReadParam();
            // 图片裁剪区域
            Rectangle rect = new Rectangle(x, y,c_w, c_w);
            // 提供一个 BufferedImage，将其用作解码像素数据的目标
            param.setSourceRegion(rect);
            // 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象
            BufferedImage bi = reader.read(0, param);
            ImageIO.write(bi, ext, new File(this.path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            LOGGER.error("IOException");
        }finally {
            try {
                if(is!=null){
                    is.close();
                }
                if(iis!=null){
                    iis.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                LOGGER.error("IOException");
            }

        }

    }
}
