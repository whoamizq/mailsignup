package com.whoami.mailsignup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.whoami.mailsignup.mapper")
public class MailsignupApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailsignupApplication.class, args);
	}

}
