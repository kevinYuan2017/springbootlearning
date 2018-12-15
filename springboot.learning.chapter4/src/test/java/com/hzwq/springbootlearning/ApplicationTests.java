package com.hzwq.springbootlearning;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hzwq.springbootlearning.proxy.ProxyBean;
import com.hzwq.springbootlearning.service.HelloService;
import com.hzwq.springbootlearning.service.Interceptor;
import com.hzwq.springbootlearning.service.impl.HelloServiceImpl;
import com.hzwq.springbootlearning.service.impl.MyInterceptor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	@Autowired
	private HelloService helloService;
	@Autowired
	private Interceptor interceptor;
	@Test
	public void contextLoads() {
//		HelloService helloService = new HelloServiceImpl();
		HelloService proxyBean = (HelloService) ProxyBean.getProxyBean(helloService, interceptor);
		proxyBean.sayHello("张三");
		proxyBean.sayHello(null);
	}

}

