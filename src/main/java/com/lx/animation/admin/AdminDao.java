package com.lx.animation.admin;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by root on 17-12-5.
 */
@Repository
public interface AdminDao {

    /**
     * 添加用户
     *
     * @param user
     * @param password
     */
    void addAdmin(@Param("user") String user, @Param("password") String password);

    /**
     * 修改登录时间
     *
     * @param id
     */
    void updateLastTime(@Param("id") int id);

    void updateOnLogin(@Param("id") int id, @Param("lastIp") long lastIp, @Param("nowIp") long nowIp);

    /**
     * 查询所有管理员数量
     *
     * @return
     */
    int queryAllAdmin();

    /**
     * 获取管理员信息
     *
     * @param user
     * @param password
     * @return
     */

    Admin queryAdmin(@Param("user") String user, @Param("password") String password);

}
