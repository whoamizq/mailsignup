package com.whoami.mailsignup.mapper;

import com.whoami.mailsignup.pojo.EmailUser;

public interface EmailUserMapper {
    int insertEmailUser(EmailUser emailUser);

    EmailUser queryByEmail(String email);
}
