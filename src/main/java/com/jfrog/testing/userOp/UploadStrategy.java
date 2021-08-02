package com.jfrog.testing.userOp;

import com.jfrog.testing.common.EnumRestApi;
import com.jfrog.testing.common.RestApiStrategy;


public class UploadStrategy implements RestApiStrategy {

	@Override
	public String restApiCall(String filePath) {

		 System.out.println("=== UPLOADING " + filePath + " ====");
		 String restApiCall = EnumRestApi.Curl.getVal() + " -u " + EnumRestApi.USERNAME.getVal() + ":" +  EnumRestApi.PASSWORD.getVal() + 
 			 	" -X PUT " + '"'+ EnumRestApi.SERVER_HOST.getVal() + "test-libs-release-local/" + '"' + " -T " +   EnumRestApi.TargetDir.getVal() + filePath;
		return restApiCall;
	}

}
