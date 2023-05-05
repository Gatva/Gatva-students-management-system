package com.Gatva.dao;

import com.Gatva.model.User;

public interface UserDao {

    User[] getResult();

    public User isExitUserName(String name);

    public boolean isRightUserPassword(String password, User user);

}
