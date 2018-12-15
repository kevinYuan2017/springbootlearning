package com.hzwq.springbootlearning.service;

import java.lang.reflect.InvocationTargetException;

import com.hzwq.springbootlearning.invoke.Invocation;

public interface Interceptor {
	// 事前方法
	boolean before();
	
	// 时候方法
	boolean after();
	
	/**
	 * 取代原有实现方法
	 * @param invocation	-- 回调参数， 可以通过它的proceed方法， 回调原有事件
	 * @return	原有事件返回对象
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException;
	
	// 时候返回方法， 事件没有发生异常时执行
	void afterReturning();
	
	// 时候异常方法， 当事件发生异常后执行
	void afterThrowing();
	
	// 是否使用around方法取代原有的方法
	boolean useAround();
}
