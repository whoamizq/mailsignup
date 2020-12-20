package com.whoami.mailsignup.service;

import com.whoami.mailsignup.vo.UserVo;

import javax.servlet.http.HttpSession;

public interface EmailUserService {
    String registered(UserVo userVo, HttpSession session);

    String loginIn(String email, String password);

    String sendMimeEmail(String email, HttpSession session);
}
