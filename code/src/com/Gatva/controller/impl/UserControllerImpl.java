package com.Gatva.controller.impl;

import com.Gatva.controller.UserController;
import com.Gatva.dao.impl.UserDaoImpl;
import com.Gatva.model.User;
/**
 * 1、先在接口中定义标准和规范，再实现接口
 *2、未对UserDao的数据做预处理
 *3、密码应该和该用户名下的密码匹配而不是去数据库中配
 */

/**
 * 与管理员用户相关的,业务操作的实现
 * @since 20:18
 * @author wuguidong@cskaoyan.onaliyun.com
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
     * @since 20:26
     * @param user 用户输入用户对象
     * @return int 登陆状态判断
     * @author wuguidong@cskaoyan.onaliyun.com
     */
    public int judgeLogin(User user) {

        String name=user.getUsername();
        String password=user.getPassword();

        //如果用户名不存在，返回1
        User re=userDao.isExitUserName(name);
        if(re==null){
            return 1;
        }else{
            //用户名存在，但是密码和该用户名对应的密码不匹配，返回2

            if(!userDao.isRightUserPassword(password,re)){
                return 2;
            }else{
                //完全正确
                return 0;
            }
        }

    }
}
