package com.example.springbatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.example.springbatis.model.User;

@Mapper
public interface UserMapper {
	
	@SelectProvider(type = UserMapperMethod.class, method = "getUsers")
	@Results(value = {
			@Result(property = "id", column = "id"),
			@Result(property = "name", column = "name")
	})
	List<User> getUsers();
	
	@SelectProvider(type = UserMapperMethod.class, method = "getUserById")
	@Results(value = {
			@Result(property = "id", column = "id"),
			@Result(property = "name", column = "name")
	})
	User getUserById(int id);
	
	@InsertProvider(type = UserMapperMethod.class, method = "createUser")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	void createUser(User user);
	
	@UpdateProvider(type = UserMapperMethod.class, method = "updateUser")
	void updateUser(int id, User user);
	
	@DeleteProvider(type = UserMapperMethod.class, method = "deleteUser")
	void deleteUser(int id);
}
