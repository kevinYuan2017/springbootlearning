package com.hzwq.redislearning.config;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

@Profile("dev")
@Validated
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.dev")
public class MysqlDBDEVConfig {
	private static Logger logger = LoggerFactory.getLogger(MysqlDBDEVConfig.class);
	private String url;
	private String driverClassName;
	@NotNull(message = "数据库用户名不可以为空！")
	private String username;
	@NotNull(message = "数据库密码不可以为空！")
	private String password;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		logger.info("mysqlDB username: " + username);
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		logger.info("mysqlDB password: " + password);
		this.password = password;
	}
	
	
}
