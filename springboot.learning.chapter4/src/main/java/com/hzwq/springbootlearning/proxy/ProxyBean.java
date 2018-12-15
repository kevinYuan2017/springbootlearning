package com.hzwq.springbootlearning.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.hzwq.springbootlearning.invoke.Invocation;
import com.hzwq.springbootlearning.service.Interceptor;

public class ProxyBean implements InvocationHandler {
	
	private Object target;
	private Interceptor interceptor;
	
	public static Object getProxyBean(Object target, Interceptor interceptor) {
		ProxyBean proxyBean = new ProxyBean();
		// 保存被代理对象
		proxyBean.target = target;
		// 保存拦截器
		proxyBean.interceptor = interceptor;
		// 生成代理对象
		Object proxyInstance = Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), 
				proxyBean);
		return proxyInstance;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 异常标示
		boolean exceptionFlag = false;
		Invocation invocation = new Invocation(target, method, args);
		Object returnObj = null;
		try {
			if(this.interceptor.before()) {
				returnObj = this.interceptor.around(invocation);
			}else {
				returnObj = method.invoke(target, args);
			}
		}catch(Exception ex) {
			// 产生异常
			exceptionFlag = true;
		}
		this.interceptor.after();
		if(exceptionFlag) {
			this.interceptor.afterThrowing();
		}else {
			this.interceptor.afterReturning();
			return returnObj;
		}
		return null;
	}
}
