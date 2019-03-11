package com.lx.animation.user;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by root on 17-12-6.
 */
@Repository
public interface UserDao {
    /**
     * 添加用户
     *
     * @param user
     * @param password
     */

    void addUser(@Param("user") String user, @Param("password") String password);

    /**
     * 查询用户数量
     */
    int queryCountAll();

    /**
     * 查询用户是否被封数量
     *
     * @param state
     * @return
     */
    int queryByState(@Param("state") String state);
//    public int count() {

    /**
     * 修改用户状态
     *
     * @param user
     * @param state
     */
    void updateByState(@Param("user") String user, @Param("state") String state);

    /**
     * 查询所有用户的信息
     *
     * @return
     */
    List<User> queryAllUser();

    /**
     * 查询所有seal用户状态信息
     *
     * @param state
     * @return
     */
    List<User> queryAllUserByState(@Param("state") String state);

    /**
     * 查询用户是否存在
     *
     * @param user
     * @return
     */
    int queryUserExist(@Param("user") String user);

    /**
     * 获取用户信息
     *
     * @param user
     * @param password
     * @return
     */
    User queryUser(@Param("user") String user, @Param("password") String password);

    /**
     * 修改用户头像
     *
     * @param user
     * @param url
     */
    void updatePic(@Param("user") String user, @Param("url") String url);

    /**
     * 修改用户信息
     *
     * @param user
     * @param name
     * @param hobby
     * @param info
     */
    void updateInfo(@Param("user") String user, @Param("name") String name, @Param("hobby") String hobby, @Param("info") String info);

    /**
     * 查询用户信息
     *
     * @param user
     * @return
     */
    User queryByUser(@Param("user") String user);
}
