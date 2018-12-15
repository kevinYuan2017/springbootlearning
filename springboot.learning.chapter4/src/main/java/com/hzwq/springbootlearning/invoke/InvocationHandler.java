package com.hzwq.springbootlearning.invoke;

import java.lang.reflect.Method;

public interface InvocationHandler {
	/**
	 * 处理代理对象方法逻辑
	 * @param proxy	代理对象
	 * @param method  当前方法
	 * @param args	运行参数
	 * @return	方法调用结果
	 */
	public Object invoke(Object proxy, Method method, Object[] args);
}
