package com.whoami.mailsignup.controller;

import com.whoami.mailsignup.vo.UserVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController(value = "/user")
public class EmailUserController {

    @PostMapping(value = "register")
    public String register(UserVo userVo, HttpSession session){
        return "success!";
    }
}
