package com.example.springbatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbatis.mapper.UserMapper;
import com.example.springbatis.model.User;

@RestController
@SpringBootApplication

public class SpringbatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbatisApplication.class, args);
	}
	
	@Autowired
	UserMapper userMapper;

	@GetMapping("/users")
	public String getUsers() throws Exception {
		String output = "";
		for(User user: userMapper.getUsers()) {
			output += user.toString() + "<br>";
		}
		return output;
	}
}
