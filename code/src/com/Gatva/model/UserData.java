package com.Gatva.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟用户数据源
 */
public class UserData {
    /*
    该类为了模拟管理员用户的数据源，正常开发中，我们的数据是从数据库中得来的
    我们还没有接触数据库，这里简单使用一个数组代替数据库，做以下约定：
    User对象数组中，一个User对象表示一个可用于登陆的管理员对象
     */
    public static final List<User> USERS = new ArrayList<>();

    static {
        // 这里给出两个可以登陆的用户
        USERS.add(new User("admin", "admin"));
        USERS.add(new User("root", "root"));

    }
}
