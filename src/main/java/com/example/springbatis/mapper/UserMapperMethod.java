package com.example.springbatis.mapper;

import org.apache.ibatis.jdbc.SQL;

import com.example.springbatis.model.User;

public class UserMapperMethod {

	public String getUsers() {
		return new SQL(){
			{
				SELECT("*");
				FROM("user");
			}
		}.toString();
	}

	public String getUserById(int id) {
		return new SQL(){
			{
				SELECT("*");
				FROM("user");
				WHERE("id = #{id}");
			}
		}.toString();
	}
	
	public String createUser(User user) {
		return new SQL() {
			{
				INSERT_INTO("user");
				VALUES("name", String.format("\'%s\'", user.getName()));
			}
		}.toString();
	}
}