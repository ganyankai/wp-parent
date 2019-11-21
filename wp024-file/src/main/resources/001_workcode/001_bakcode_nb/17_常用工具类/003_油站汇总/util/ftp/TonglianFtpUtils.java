package com.catt.common.util.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class TonglianFtpUtils {
	
	private final static org.slf4j.Logger log = org.slf4j.LoggerFactory
            .getLogger(com.catt.common.util.ftp.TonglianFtpUtils.class);

	/** yyyyMMdd */
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");

	public void uploadSingle(FTPClient ftpClient, String bizUserId, String saveName, InputStream inputStream) {
		inWorkingDirectory(ftpClient, bizUserId, "/");
		try {
			if (ftpClient.storeFile(saveName, inputStream)) {
				log.info("ftp server upload file success, save name : {}", saveName);
			} else {
				log.info("ftp server upload file fail, save name : {}", saveName);
				throw new RuntimeException();
			}
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			disconnect(ftpClient);
		}
	}

	public static void uploadMultiple(FTPClient ftpClient, String bizUserId, List<TonglianFtpUploadFile> list) {
		inWorkingDirectory(ftpClient, bizUserId, "/");
		try {
			for (TonglianFtpUploadFile uploadFile : list) {
				String saveName = uploadFile.getSaveName();
				InputStream inputStream = uploadFile.getInputStream();
				
				if (ftpClient.storeFile(saveName, inputStream)) {
					inputStream.close();
					log.info("ftp server upload file success, save name : {}", saveName);
				} else {
					log.info("ftp server upload replyCode : {}", ftpClient.getReplyCode());
					log.info("ftp server upload replyString : {}", new String(ftpClient.getReplyString().getBytes("iso-8859-1"),"GBK"));
					
					inputStream.close();
					log.info("ftp server upload file fail, save name : {}", saveName);
					throw new RuntimeException();
				}
			}
		} catch (Exception e) {
			log.info("ftp server upload Exception : {}", e);
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			disconnect(ftpClient);
		}
	}
	
	private static void disconnect(FTPClient ftpClient) {
		if (ftpClient.isConnected()) {
			try {
				ftpClient.disconnect();
				log.info("ftp server disconnect success");
			} catch (IOException e) {
				log.warn("ftp server disconnect fail");
				e.printStackTrace();
			}
		}
	}

	public static FTPClient getTonglianFtpClient(String host, Integer port, String username, String password) {
		try {
			FTPClient ftpClient = new FTPClient();
			ftpClient.setRemoteVerificationEnabled(false);
			ftpClient.connect(host, port);
			log.info("ftp server connect success");
			if (!ftpClient.login(username, password)) {
				log.warn("ftp server login fail, please check username and password");
				disconnect(ftpClient);
				throw new RuntimeException();
			}
			ftpClient.setBufferSize(1024);
			ftpClient.enterLocalActiveMode();
//			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.setControlEncoding("UTF-8");
			log.info("ftp server login success");
			return ftpClient;
		} catch (IOException e) {
			log.warn("ftp server connect fail, please check the server configuration");
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
//	public static FTPClient getTonglianFtpClient(String host, Integer port, String username, String password, String activeHost, Integer activePort) {
//		try {
//			FTPClient ftpClient = new FTPClient();
////			ftpClient.setRemoteVerificationEnabled(false);
//			ftpClient.connect(host, port);
//			log.info("ftp server connect success");
//			if (!ftpClient.login(username, password)) {
//				log.warn("ftp server login fail, please check username and password");
//				disconnect(ftpClient);
//				throw new RuntimeException();
//			}
//			ftpClient.setBufferSize(1024);
//			ftpClient.enterLocalActiveMode();
////			ftpClient.enterRemoteActiveMode(InetAddress.getByName(activeHost), ftpClient.getLocalPort());
////			ftpClient.enterLocalPassiveMode();
//			
////			ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
//			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//			ftpClient.setControlEncoding("UTF-8");
//			log.info("ftp server login success");
//			return ftpClient;
//		} catch (IOException e) {
//			log.warn("ftp server connect fail, please check the server configuration");
//			e.printStackTrace();
//			throw new RuntimeException();
//		}
//	}

	private static void inWorkingDirectory(FTPClient ftpClient, String bizUserId, String rootPath) {
		try {
			String dir = rootPath + bizUserId;
			if (!ftpClient.changeWorkingDirectory(dir)) {
				if (!ftpClient.makeDirectory(dir)) {
					log.warn("make directory fail, dir : {}", dir);
					throw new RuntimeException();
				}
				
				if (!ftpClient.changeWorkingDirectory(dir)) {
					log.warn("change working directory fail, dir : {}", dir);
					throw new RuntimeException();
				}
			}

		} catch (IOException e) {
			log.warn("in working directory fail");
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
