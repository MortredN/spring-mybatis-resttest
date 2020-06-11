package com.example.springbatis.mapper;

import org.apache.ibatis.jdbc.SQL;

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
}