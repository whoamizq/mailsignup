package com.whoami.mailsignup.vo;

import com.whoami.mailsignup.pojo.EmailUser;

public class UserVoToUser {

    /**
     * 将表单中的对象转化为数据库中存储的用户对象（剔除表单中的code）
     * @param userVo
     * @return
     */
    public static EmailUser toUser(UserVo userVo){
        // 创建一个数据库中存储的对象
        EmailUser user = new EmailUser();
        // set值
        user.setUsername(userVo.getUsername());
        user.setPassword(userVo.getPassword());
        user.setEmail(userVo.getEmail());
        return user;
    }
}
