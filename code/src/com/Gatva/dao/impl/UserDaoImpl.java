package com.Gatva.dao.impl;

import com.Gatva.dao.UserDao;
import com.Gatva.model.User;
import com.Gatva.model.UserData;
import java.util.Objects;

/**
 * 与管理员用户相关的，所有数据处理都在该类下进行
 *
 * @since 19:36
 * @author wuguidong@cskaoyan.onaliyun.com
 */
public class UserDaoImpl implements UserDao {




    // 从数据源获取数据
    private  User[] users = UserData.USERS;

    @Override
    public User[] getResult() {

        int cnt=0;
        for (User user:users){
            //System.out.println(user);
            if(user!=null){
                cnt++;
            }
        }

        User[] result=new User[cnt];

        int index=0;
        for (User user:users){
            //System.out.println(user);
            if(user!=null){
                result[index++]=user;
            }
        }
        return result;
    }

    @Override
    public User isExitUserName(String name) {

        //为空
        if(name==null){
            return null;
        }

        //依次匹配，记录匹配结果
        User[] re=getResult();
        int i=0;
        while (i<re.length){
            if(re[i].getUsername().equals(name)){
                return re[i];
            }
            i++;
        }

        return null;
    }

    @Override
    public boolean isRightUserPassword(String password,User user) {

        if(Objects.equals(user.getPassword(),password)){
            return true;
        }else{
            return false;
        }
    }



}
