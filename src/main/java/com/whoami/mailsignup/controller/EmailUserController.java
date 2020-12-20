package com.whoami.mailsignup.controller;

import com.whoami.mailsignup.service.EmailUserService;
import com.whoami.mailsignup.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController(value = "/user")
public class EmailUserController {
    @Autowired
    private EmailUserService emailUserService;

    /**
     * 注册
     * @param userVo
     * @param session
     * @return
     */
    @PostMapping(value = "register")
    public String register(UserVo userVo, HttpSession session){
        return emailUserService.registered(userVo,session);
    }

    /**
     * 登录
     * @param email
     * @param password
     * @return
     */
    @PostMapping(value = "login")
    public String login(String email,String password){
        return emailUserService.loginIn(email,password);
    }

    @PostMapping(value = "sendEmail")
    public String sendEmail(String email,HttpSession session){
        return emailUserService.sendMimeEmail(email, session);
    }
}
