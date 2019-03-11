package com.lx.animation.config;

import com.lx.animation.admin.Admin;
import com.lx.animation.admin.AdminDao;
import com.lx.animation.adv.AdvDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

@Service
public class UserTestService {
    @Autowired
    private AdminDao adminDao;
    @Transactional
    public void txTest() {
      //  adminDao.addAdmin("lx", "123qwe");
    }
}
