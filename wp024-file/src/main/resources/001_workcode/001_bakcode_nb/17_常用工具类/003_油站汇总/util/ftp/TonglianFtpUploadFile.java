package com.catt.common.util.ftp;

import java.io.InputStream;


public class TonglianFtpUploadFile {

	private String saveName;

	private InputStream inputStream;
	
	
	public TonglianFtpUploadFile(String saveName, InputStream inputStream) {
		super();
		this.saveName = saveName;
		this.inputStream = inputStream;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	

}