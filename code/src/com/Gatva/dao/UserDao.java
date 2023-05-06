package com.Gatva.dao;

import com.Gatva.model.User;

public interface UserDao {

    // 匹配UserName
    public User isExitUserName(String name);

    // 匹配登录密码（和相同用户名下的密码匹配）
    public boolean isRightUserPassword(String password, User user);

}
