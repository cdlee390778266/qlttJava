package com.qlcd.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ToZMQProxyHandler implements InvocationHandler {
	
	 private Object busProxy;   
     
	  public ToZMQProxyHandler( Object busProxy )   
	  {   
	    this.busProxy = busProxy;   
	  }   

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		 Object result = method.invoke(busProxy, args);  
		 
		 return result;
	}

}
