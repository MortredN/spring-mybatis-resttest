package com.example.springbatis.mapper;

import java.util.ArrayList;
import java.util.HashMap;

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
				
				HashMap<String, ArrayList<String>> CnVMap = getCnV(user);
				
				String columns = ""; boolean cInit = false;
				for(String c: CnVMap.get("columns")) {
					if(!cInit) {
						columns += c;
						cInit = true;
					}
					else
						columns += ", " + c;
				}
				
				String values = ""; boolean vInit = false;
				for(String v: CnVMap.get("values")) {
					if(!vInit) {
						values += String.format("\'%s\'", v);
						vInit = true;
					}
					else
						values += String.format(", \'%s\'", v);
				}
					
				VALUES(columns, values);
			}
		}.toString();
	}
	
	public String updateUser(int id, User user) {
		return new SQL() {
			{
				UPDATE("user");
				
				HashMap<String, ArrayList<String>> CnVMap = getCnV(user);
				
				String setQuery = ""; boolean init = false;
				for(int i = 0; i < CnVMap.get("columns").size(); i++) {
					if(!init) {
						setQuery += String.format("%s = \'%s\'", CnVMap.get("columns").get(i), CnVMap.get("values").get(i));
						init = true;
					}
					else
						setQuery += String.format(", %s = \'%s\'", CnVMap.get("columns").get(i), CnVMap.get("values").get(i));
				}
				
				SET(setQuery);
				WHERE("id = " + id);
			}
		}.toString();
	}
	
	public String deleteUser(int id) {
		return new SQL() {
			{
				DELETE_FROM("user");
				WHERE("id = " + id);
			}
		}.toString();
	}
	
	// CnV = Columns and Values
	private HashMap<String, ArrayList<String>> getCnV(User user) {
		@SuppressWarnings("serial")
		HashMap<String, ArrayList<String>> CnVMap = new HashMap<>() {{
			put("columns", new ArrayList<String>());
			put("values", new ArrayList<String>());
		}};
		
		if(user.getName() != null) {
			CnVMap.get("columns").add("name");
			CnVMap.get("values").add(user.getName());
		}
//		if(user.getNextAttr() != null) {
//			CnVMap.get("columns").add("nextAttr");
//			CnVMap.get("values").add(user.getNextAttr());
//		}
		
		return CnVMap;
	}
	
}