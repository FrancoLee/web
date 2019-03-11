package com.lx.animation.admin;

import com.lx.animation.util.AddressUtil;
import com.lx.animation.util.AdminUtil;
import com.lx.animation.util.HashPassUtil;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by root on 17-12-14.
 */
@Service("AdminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;
    @Autowired
    HashPassUtil hashPassUtil;
    static final Logger LOGGER = LogManager.getLogger(AdminService.class);

    public Admin login(String user, String password, String ip) {
        if (user == null || password == null)
            return null;
        HttpSession sessionId = AdminUtil.query(user);
        if (sessionId != null) {
            AdminUtil.remove(user);
            sessionId.invalidate();
        }
        Admin admin = null;
        try {
            admin = adminDao.queryAdmin(user, hashPassUtil.gethash(password));
            AddressUtil addressUtil = new AddressUtil();
           adminDao.updateOnLogin(admin.getId(), addressUtil.stringToLong(admin.getNowIp()), addressUtil.stringToLong(ip));
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return null;
        }
        return admin;
    }

    public void logout(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null && admin.getUser() != null)
            AdminUtil.remove(admin.getUser());
        session.removeAttribute("admin");
    }
}
