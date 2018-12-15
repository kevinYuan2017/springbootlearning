package com.hzwq.redislearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hzwq.redislearning.config.ELConfig;
import com.hzwq.redislearning.pojo.bean.CatMan;
import com.hzwq.redislearning.pojo.bean.PigMan;
import com.hzwq.redislearning.pojo.scope.ScopeBean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
//		CatMan catMan = context.getBean(CatMan.class);
//		PigMan pigMan = context.getBean(PigMan.class);
//		catMan.service();
//		pigMan.service();
		
//		ScopeBean scopeBean1 = context.getBean(ScopeBean.class);
//		ScopeBean scopeBean2 = context.getBean(ScopeBean.class);
//		System.out.println("scopeBean1.equals(scopeBean2)： " + scopeBean1.equals(scopeBean2));
		ELConfig elConfig = context.getBean(ELConfig.class);
		System.out.println(elConfig.getCurrentDateTime());
		System.out.println(elConfig.getJavaHome());
	}

}

