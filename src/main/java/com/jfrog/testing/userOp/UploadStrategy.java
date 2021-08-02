package com.jfrog.testing.userOp;

import com.jfrog.testing.common.EnumRestApi;
import com.jfrog.testing.common.RestApiStrategy;
import com.jfrog.testing.common.Utils;


public class UploadStrategy implements RestApiStrategy {

	@Override
	public String restApiCall() {
		 String fileName = Utils.retrieveMVNFile();
		 System.out.println("=== UPLOADING " + fileName + " ====");
		 String restApiCall = EnumRestApi.Curl.getVal() + " -u " + EnumRestApi.USERNAME.getVal() + ":" +  EnumRestApi.PASSWORD.getVal() + 
 			 	" -X PUT " + '"'+ EnumRestApi.SERVER_HOST.getVal() + "test-libs-release-local/" + '"' + " -T " +   EnumRestApi.TargetDir.getVal() + fileName;
		return restApiCall;
	}

}
