package com.whoami.mailsignup.service.impl;

import com.whoami.mailsignup.mapper.EmailUserMapper;
import com.whoami.mailsignup.pojo.EmailUser;
import com.whoami.mailsignup.service.EmailUserService;
import com.whoami.mailsignup.vo.UserVo;
import com.whoami.mailsignup.vo.UserVoToUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Service
public class EmailUserServiceImpl implements EmailUserService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EmailUserMapper emailUserMapper;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 校验验证码是否一致
     * @param userVo
     * @param session
     */
    @Override
    @Transactional
    public String registered(UserVo userVo, HttpSession session) {
        // 获取session中的验证信息
        String email = (String) session.getAttribute("email");
        String code = (String) session.getAttribute("code");
        // 获取表单中的提交信息
        String voCode = userVo.getCode();
        // 数据为空或不一致,注册失败
        if (email==null || email.isEmpty()){
            return "error,请重新注册";
        }
        if (!code.equals(voCode)){
            return "error,请重新注册";
        }
        // 保存数据
        EmailUser emailUser = UserVoToUser.toUser(userVo);
        int res = emailUserMapper.insertEmailUser(emailUser);
        return res>0?"success!":"error,请重新注册";
    }

    /**
     * 通过输入email查询password，然后比较两个password，如果一样，登录成功
     * @param email
     * @param password
     * @return
     */
    @Override
    public String loginIn(String email, String password) {
        EmailUser user = emailUserMapper.queryByEmail(email);
        if (StringUtils.isEmpty(user)) {
            return "用户不存在！";
        }
        if (!user.getPassword().equals(password)){
            return "密码错误！";
        }
        System.out.println("登录成功：用户名："+user.getUsername()+"，密码："+user.getPassword());
        return "登录成功！";
    }

    /**
     * 给前端输入的邮箱，发送验证码
     * @param email
     * @param session
     * @return
     */
    @Override
    public String sendMimeEmail(String email, HttpSession session) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("验证码邮件");// 主题
            // 生成随机数
            String code = randomCode();
            // 放置session中
            session.setAttribute("email",email);
            session.setAttribute("code",code);
            mailMessage.setText("您收到的验证码是："+code);// 内容
            mailMessage.setTo(email);// 发给谁
            mailMessage.setFrom(from); // 自己邮箱
            mailSender.send(mailMessage); // 发送
            return "发送成功！";
        }catch (Exception e){
            e.printStackTrace();
            return "发送失败！";
        }
    }

    /**
     * 随机生成6位数的验证码
     * @return code
     */
    private String randomCode() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i=0;i<6;i++){
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }
}
