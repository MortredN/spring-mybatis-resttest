package com.example.springbatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbatis.mapper.UserMapper;
import com.example.springbatis.model.User;

@RestController
public class SpringbatisController {
	
	@Autowired
	UserMapper userMapper;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getUsers() throws Exception {
		String output = "";
		for(User user: userMapper.getUsers()) {
			output += user.toString() + "<br>";
		}
		return output;
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public String getUser(@PathVariable int id) {
		return userMapper.getUserById(id).toString();
	}
}
