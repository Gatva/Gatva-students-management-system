package com.Gatva.controller.impl;

import com.Gatva.controller.UserController;
import com.Gatva.dao.UserDao;
import com.Gatva.dao.impl.UserDaoImpl;
import com.Gatva.model.User;


/**
 * 与管理员用户相关的,业务操作的实现
 */
public class UserControllerImpl implements UserController {

    // 业务处理需要获取数据,所以需要依赖数据处理层
    private UserDaoImpl userDao = new UserDaoImpl();

    /**
     * 判断能否登陆，返回一个int状态值
     * 其中：
     * 0，表示正常成功登陆
     * 1，表示用户不存在
     * 2，表示密码输入错误
     * @param user 用户输入用户对象
     * @return int 登陆状态判断
     */
    public int judgeLogin(User user) {

        UserDao ud = new UserDaoImpl();
        //用户不存在
        User matchedUser = ud.isExitUserName(user.getUsername());
        if (matchedUser == null) {
            return 1;
        }

        // 密码错误

        if (!ud.isRightUserPassword(user.getPassword(), matchedUser)) {
            return 2;
        }

        return 0;
    }

}
