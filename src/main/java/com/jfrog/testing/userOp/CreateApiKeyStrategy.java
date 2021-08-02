package com.jfrog.testing.userOp;

import com.jfrog.testing.common.EnumRestApi;
import com.jfrog.testing.common.RestApiStrategy;

public class CreateApiKeyStrategy implements RestApiStrategy {

	@Override
	public String restApiCall() {
		 String restApiCall = "curl -u " + EnumRestApi.USERNAME.getVal() + ":" +  EnumRestApi.PASSWORD.getVal() + 
	 			 	" -X POST " + '"'+ EnumRestApi.SERVER_HOST.getVal() + EnumRestApi.Security.getVal() + EnumRestApi.ApiKey.getVal() + '"';
		 return restApiCall;
	}

}
