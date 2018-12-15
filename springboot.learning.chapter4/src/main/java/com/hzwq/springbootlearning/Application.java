package com.hzwq.springbootlearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hzwq.springbootlearning.proxy.ProxyBean;
import com.hzwq.springbootlearning.service.HelloService;
import com.hzwq.springbootlearning.service.impl.HelloServiceImpl;
import com.hzwq.springbootlearning.service.impl.MyInterceptor;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
		HelloService helloService = new HelloServiceImpl();
		HelloService proxyBean = (HelloService) ProxyBean.getProxyBean(helloService, new MyInterceptor());
		proxyBean.sayHello("");
	}

}

