package com.jfrog.testing.userOp;

import com.jfrog.testing.common.EnumRestApi;
import com.jfrog.testing.common.RestApiStrategy;

public class RevokeApiKeyStrategy implements RestApiStrategy {

	@Override
	public String restApiCall() {
		 String restApiCall = "curl -u " + EnumRestApi.USERNAME.getVal() + ":" +  EnumRestApi.PASSWORD.getVal() + 
	 			 	" -X DELETE " + '"'+ EnumRestApi.SERVER_HOST.getVal() + EnumRestApi.Security.getVal() + EnumRestApi.ApiKey.getVal() + '"';
		 return restApiCall;
	}

}
