package com.example.springbatis.config;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.springbatis.mapper")
@EnableAutoConfiguration
public class PersistentConfig {

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/new_schema";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "abcd1234";
	
	@Bean
	public static DataSource dataSource() {
		return new PooledDataSource(DRIVER, URL, USERNAME, PASSWORD);
	}
	
	@Bean
	public static SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		return factoryBean.getObject();
	}
}
