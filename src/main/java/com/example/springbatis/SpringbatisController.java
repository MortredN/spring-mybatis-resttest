package com.example.springbatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbatis.mapper.UserMapper;
import com.example.springbatis.model.User;

@RestController
public class SpringbatisController {
	
	@Autowired
	UserMapper userMapper;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public String getUsers() throws Exception {
		String output = "";
		for(User user: userMapper.getUsers()) {
			output += user.toString() + "<br>";
		}
		return output;
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getUser(@PathVariable int id) {
		return userMapper.getUserById(id).toString();
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST, headers = { "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String createUser(@RequestBody User user) {
		userMapper.createUser(user);
		return "Successfully added User: " + user.toString();
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT, headers = { "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String updateUser(@PathVariable int id, @RequestBody User user) {
		userMapper.updateUser(id, user);
		return "Successfully updated User ID no." + id;
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteUser(@PathVariable int id) {
		userMapper.deleteUser(id);
		return "Successfully deleted User ID no." + id;
	}
}
