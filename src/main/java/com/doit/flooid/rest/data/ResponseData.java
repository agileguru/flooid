package com.doit.flooid.rest.data;

import java.io.Serializable;

public class ResponseData implements Serializable {

	private static final long serialVersionUID = 5405837937785046409L;
	private String appName;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

}
