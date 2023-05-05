package com.Gatva.model;

/**
 * 模拟用户数据源
 *
 * @since 11:08
 * @author wuguidong@cskaoyan.onaliyun.com
 */
public class UserData {
    /*
    该类为了模拟管理员用户的数据源，正常开发中，我们的数据是从数据库中得来的
    我们还没有接触数据库，这里简单使用一个数组代替数据库，做以下约定：
    User对象数组中，一个User对象表示一个可用于登陆的管理员对象
     */
    public static final User[] USERS = new User[5];

    static {
        // 这里给出两个可以登陆的用户
        USERS[0] = new User("admin", "admin");
        USERS[1] = new User("root", "root");
    }
}
