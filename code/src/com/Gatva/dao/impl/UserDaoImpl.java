package com.Gatva.dao.impl;

import com.Gatva.dao.UserDao;
import com.Gatva.model.User;
import com.Gatva.model.UserData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 与管理员用户相关的，所有数据处理都在该类下进行
 */
public class UserDaoImpl implements UserDao {


    // 从数据源获取数据
    private List<User> users = UserData.USERS;

    @Override
    public User isExitUserName(String name) {

        if (name == null || "".equals(name)) {
            return null;
        }
        // 遍历数据，匹配
        for (User user :
                users) {
            if (user != null && user.getUsername().equals(name)) {

                return user;
            }
        }


        return null;
    }

    @Override
    public boolean isRightUserPassword(String password, User user) {

        if (user.getPassword().equals(password)) {
            return true;
        }
        return false;

    }

}
