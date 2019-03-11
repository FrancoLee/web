package com.lx.animation.adv;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by root on 17-12-10.
 */
@Service("AdvService")
public class AdvServiceImpl implements AdvService {
    @Autowired
    AdvDao advDao;
    private static final Logger LOGGER = LogManager.getLogger(AdvServiceImpl.class);

    /**
     * 获取logo信息
     *
     * @return
     */
    public Adv getLogo() {
        Adv adv = null;
        try {
            adv = advDao.queryLogo();
        } catch (Exception e) {
            LOGGER.error("advDao query logo fail");
        }
        return adv;
    }

    /**
     * 获取轮播图信息
     *
     * @return
     */
    public List<Adv> listCarousel() {
        List<Adv> list = null;
        try {
            list = advDao.queryCarousel();
        } catch (Exception e) {
            LOGGER.error("advDao query carousel fail");
        }
        return list;
    }

    /**
     * 删除轮播
     *
     * @param id
     * @return
     */
    public boolean deleteCarousel(int id) {
        try {
            String pic = advDao.queryAdvPicUrl(id);
            String path = System.getProperty("webRoot");
            File file = new File(path + pic);
            if (file.exists())
                file.delete();
            advDao.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("delete adv fail");
            return false;
        }
        return true;
    }

    /**
     * 批量删除
     *
     * @param check
     * @return
     */
    public boolean deleteBatchCarousel(String check) {
        try {
            String[] arrId = check.split(",");
            String path = System.getProperty("webRoot");
            List<String> arrPic = advDao.queryPicUrlByArrId(arrId);
            for (String pic :
                    arrPic) {
                File file = new File(path + pic);
                if (file.exists())
                    file.delete();
            }
            advDao.deleteByArrId(arrId);
        } catch (Exception e) {
            LOGGER.error("batch delete carousel fail");
            return false;
        }
        return true;
    }

    /**
     * 修改图片信息
     *
     * @param id
     * @param file
     * @return
     */
    public boolean updateAdvPic(int id, MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                String path = System.getProperty("webRoot");
                File f = new File(path + "/nav_pic/");
                if (!f.exists())
                    f.mkdir();
                String picUrl = "/nav_pic/" + String.valueOf(System.currentTimeMillis() + file.getOriginalFilename());
                file.transferTo(new File(path + picUrl));
                String pic = advDao.queryAdvPicUrl(id);
                if (pic != null) {
                    File file1 = new File(path + pic);
                    if (file1.exists())
                        file1.delete();
                }
                advDao.updateById(id, picUrl);
            }
        } catch (IOException e) {
            LOGGER.error("update carousel pic fail");
            return false;
        }
        return true;
    }

    /**
     * 修改logo图片
     *
     * @param file
     * @return
     */
    public boolean updateLogoPic(MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                String path = System.getProperty("webRoot");
                File f = new File(path + "/logo_pic/");
                if (!f.exists())
                    f.mkdir();
                String picUrl = "/logo_pic/" + String.valueOf(System.currentTimeMillis() + file.getOriginalFilename());
                file.transferTo(new File(path + picUrl));
                String pic = advDao.queryLogo().getPicUrl();
                if (pic != null) {
                    File file1 = new File(path + pic);
                    if (file1.exists())
                        file1.delete();
                }
                advDao.updateLogo(picUrl);
            }
        } catch (IOException e) {
            LOGGER.error("update carousel pic fail");
            return false;
        }
        return true;
    }

    /**
     * 添加轮播图片
     *
     * @param file
     * @return
     */
    public int addCarousel(MultipartFile file) {
        Adv adv = null;
        try {
            if (!file.isEmpty()) {
                String path = System.getProperty("webRoot");
                File f = new File(path + "/nav_pic/");
                if (!f.exists())
                    f.mkdir();
                String picUrl = "/nav_pic/" + String.valueOf(System.currentTimeMillis() + file.getOriginalFilename());
                //System.out.println(path+picUrl);
                file.transferTo(new File(path + picUrl));
                adv = new Adv();
                //   System.out.println("2");
                adv.setType(2);
                adv.setPicUrl(picUrl);
                advDao.addAdv(adv);
            }
        } catch (IOException e) {
            LOGGER.error("add carousel fail");
            return -1;
        }
        return adv.getId();
    }

    /**
     * 更新轮播信息
     *
     * @param id
     * @param info
     * @param rank
     * @return
     */
    public boolean updateCarousel(int id, String info, int rank) {
        try {
            Adv adv = new Adv();
            adv.setId(id);
            adv.setInfo(info);
            adv.setRank(rank);
            adv.setType(2);
            advDao.updateAdv(adv);
        } catch (Exception e) {
            LOGGER.error("update carousel fail");
            return false;
        }
        return true;
    }

    /**
     * 修改图片信息
     *
     * @param info
     * @return
     */
    public boolean updateLogoInfo(String info) {
        try {
            advDao.updateLogoInfo(info);
        } catch (Exception e) {
            LOGGER.error("update logo info fail");
            return false;
        }
        return true;
    }
}
