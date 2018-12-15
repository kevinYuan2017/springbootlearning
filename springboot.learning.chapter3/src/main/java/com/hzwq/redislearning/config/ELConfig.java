package com.hzwq.redislearning.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ELConfig {
	@Value("#{T(System).currentTimeMillis()}")
	private long currentDateTime;
	
//	@Value("#{systemEnvironment['JAVA_HOME']}")
	@Value("#{T(System).getenv('JAVA_HOME')}")
	private String javaHome;
	
	public long getCurrentDateTime() {
		return currentDateTime;
	}

	public void setCurrentDateTime(long currentDateTime) {
		this.currentDateTime = currentDateTime;
	}

	public String getJavaHome() {
		return javaHome;
	}

	public void setJavaHome(String javaHome) {
		this.javaHome = javaHome;
	}
}
