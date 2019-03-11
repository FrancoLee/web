package com.lx.animation.dao;

import com.lx.animation.adv.Adv;
import com.lx.animation.adv.AdvDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by root on 17-12-7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext.xml"})
public class AdvDaoTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private AdvDao advDao;
    private static final Logger LOGGER= LoggerFactory.getLogger(AdvDaoTest.class);
    @Test
    public void test() {
        Adv adv=new Adv();
        adv.setId(43);
        adv.setType(1);
        adv.setInfo("1231");
        adv.setPicUrl("123");
        advDao.updateAdv(adv);
        adv=advDao.queryLogo();
        System.out.println(adv.toString());
     //   advDao.addAdv("sss");
        advDao.updateLogo("ccc");
        advDao.updateLogoInfo("aaaa");
//        String str="43,44,45";
//        String[] arr=str.split(",");
//        List<String> picArr=advDao.queryPicUrlByArrId(arr);
//        for (String s:picArr
//             ) {
//            System.out.println(s);
//        }
        System.out.println(advDao.queryAdvPicUrl(43));
        advDao.deleteById(46);
        List<Adv> advList=advDao.queryAllAdv();
        for (Adv a:advList
             ) {
            System.out.println(a.toString());
        }

        advList=advDao.queryCarousel();
        for (Adv a:advList
                ) {
            System.out.println(a.toString());
        }
        System.out.println(advDao.queryLogoExist());

    }
}
