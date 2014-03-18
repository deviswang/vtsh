package com.cardinfolink.vtsh.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserControlInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -7208941288956218029L;

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		Map<String,Object> session=ai.getInvocationContext().getSession();
		String user = (String)session.get("User");
		if(user==null){
			return "home";
		}
		return ai.invoke();
	}

}
