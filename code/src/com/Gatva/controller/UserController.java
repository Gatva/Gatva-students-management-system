package com.Gatva.controller;

import com.Gatva.model.User;
public interface UserController {

    // 登录状态（成功orUserName不存在orPassword错误)
    int judgeLogin(User user);
}
