package com.jfrog.testing.common;

public enum EnumRestApi {
	 USERNAME("admin"),
	 PASSWORD("password"),
	 SERVER_HOST("http://localhost:8082/artifactory/"),
	 DesktopDir(System.getProperty("user.home").replace("\\", "/") +"/Desktop"),
	 TargetDir(System.getProperty("user.dir").replace("\\", "/") + "/target/"),
	 Security("api/security/"),
	 ApiKey("apiKey"),
	 Put("PUT"),
	 Curl("curl");
	 
	
	private String val;
	
	EnumRestApi(String val) {
		this.val = 	val;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
}
