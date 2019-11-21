package com.catt.common.util.net;

/**
 * Created by xiang on 15-9-30.
 */

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

public class FTPUtil
{
    private static final Log LOG = LogFactory.getLog(com.catt.common.util.net.FTPUtil.class);
    private static final String ENCODE_GBK = "GBK";
    private static final String ENCODE_ISO = "ISO-8859-1";

    public static int upload(String connectUrl, String username, String password, String targetDir, String localFilePath, String localFileName)
    {
        int result = 0;
        FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null;
        try {
            ftpClient.setRemoteVerificationEnabled(false);
            ftpClient.connect(connectUrl);
            ftpClient.login(username, password);
            File srcFile = new File(localFilePath);
            fis = new FileInputStream(srcFile);
            LOG.info(new StringBuilder().append("--------ftp服务上传文件夹路径：【").append(targetDir).append("】").toString());
            if (!(ftpClient.changeWorkingDirectory(targetDir))) {
                if (!(ftpClient.makeDirectory(targetDir))) {
                    LOG.info(new StringBuilder().append("--------ftp服务创建文件夹失败：【").append(targetDir).append("】").toString());
                }
                ftpClient.changeWorkingDirectory(targetDir);
            }
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("GBK");
            ftpClient.setFileType(2);
            result = (ftpClient.storeFile(new String(localFileName.getBytes("GBK"), "ISO-8859-1"), fis)) ? 1 : 0;
            LOG.info(new StringBuilder().append("--------ftp服务上传附件：【").append(localFileName).append("】状态：").append((result == 1) ? "成功" : "失败").toString());
        } catch (IOException e) {
            LOG.error(new StringBuilder().append("FTP客户端出错---上传附件：").append(localFileName).toString(), e);
        } finally {
            IOUtils.closeQuietly(fis);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                LOG.error("关闭FTP连接发生异常", e);
            }
        }
        return result;
    }

    /**
     * 通用上传文件到FTP，当中涉及多目录上传
     * @param connectUrl
     * @param username
     * @param password
     * @param targetDir
     * @param baseTargetDir
     * @param localFilePath
     * @param localFileName
     * @return
     */
    public static int uploadWithDir(String connectUrl, String username, String password, String targetDir,String baseTargetDir, String localFilePath, String localFileName)
    {
        int result = 0;
        FTPClient ftpClient = new FTPClient();
        InputStream inputStream = null;
        try {
            ftpClient.setRemoteVerificationEnabled(false);
            ftpClient.connect(connectUrl);
            ftpClient.login(username, password);
            File file = new File(localFilePath);
            inputStream = new FileInputStream(file);
            if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                String str = StringUtils.replace(targetDir, "\\", "/");
                String directory = StringUtils.substringBeforeLast(str, "/");
                if (!ftpClient.changeWorkingDirectory(directory)) {
                    String[] paths = StringUtils.split(directory, "/");
                    String p = baseTargetDir;
                    ftpClient.changeWorkingDirectory(p);
                    for (String s : paths) {
                        p += s + "/";
                        if (!ftpClient.changeWorkingDirectory(p)) {
                            ftpClient.makeDirectory(s);
                            ftpClient.changeWorkingDirectory(p);
                        }
                    }
                }
                ftpClient.setBufferSize(1024);
                ftpClient.setFileType(2);
                result = (ftpClient.storeFile(localFileName, inputStream)) ? 1 : 0;
                ftpClient.logout();
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    public static int download(String connectUrl, String username, String password, String targetFilePath, String localFilePath)
    {
        int result = 0;
        FTPClient ftpClient = new FTPClient();
        FileOutputStream fos = null;
        try {
            ftpClient.setRemoteVerificationEnabled(false);
            ftpClient.connect(connectUrl);
            ftpClient.login(username, password);
            fos = new FileOutputStream(localFilePath);
            ftpClient.setBufferSize(1024);
            ftpClient.setFileType(2);
            result = (ftpClient.retrieveFile(new String(targetFilePath.getBytes("GBK"), "ISO-8859-1"), fos)) ? 1 : 0;
            LOG.info(new StringBuilder().append("--------ftp服务下载附件：【").append(targetFilePath).append("】状态：").append((result == 1) ? "成功" : "失败").toString());
        } catch (IOException e) {
            LOG.error(new StringBuilder().append("FTP客户端出错---下载附件：").append(targetFilePath).toString(), e);
        } finally {
            IOUtils.closeQuietly(fos);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                LOG.error("关闭FTP连接发生异常", e);
            }
        }
        return result;
    }

    public static int testUpload(String fileName) {
        String connectUrl = "localhost";
        String username = "sa";
        String password = "sa";
        String targetDir = "/";
        String localFilePath = new StringBuilder().append("D:/test/other/201604/").append(fileName).toString();
        String localFileName = fileName;
        System.out.println("localFilePath="+localFilePath);
        System.out.println("localFileName="+localFileName);
        return upload(connectUrl, username, password, targetDir, localFilePath, localFileName);
    }

    public static int testUploadWithDir(String fileName) {
        String connectUrl = "localhost";
        String username = "sa";
        String password = "sa";
        String targetDir = "/other/201604/";
        String localFilePath = new StringBuilder().append("D:/test/other/201604/").append(fileName).toString();
        String localFileName = fileName;
        return uploadWithDir(connectUrl, username, password, targetDir,"/", localFilePath, localFileName);
    }

    public static int deleteFile(String targetFilePath)
    {
        int result = 0;
        FTPClient ftpClient = new FTPClient();
        try {
            result = ftpClient.dele(targetFilePath);
            LOG.info(new StringBuilder().append("--------ftp服务删除附件：【").append(targetFilePath).append("】状态：").append((result == 1) ? "成功" : "失败").toString());
        } catch (IOException e) {
            LOG.error(new StringBuilder().append("FTP客户端出错---删除附件：").append(targetFilePath).toString(), e);
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                LOG.error("关闭FTP连接发生异常", e);
            }
        }
        return result;
    }

    public static int testDownload(String fileName)
    {
        String connectUrl = "localhost";
        String username = "liujieming";
        String password = "123456";
        String targetDir = "apache-maven-3.3.1.zip";
        String localFilePath = "E:\\ICETemplatePath\\apache-maven-3.3.1.zip";
        return download(connectUrl, username, password, targetDir, localFilePath);
    }

    public static void main(String[] args) {
        try {
            System.out.println("启动中...");
//            testDownload(null);
//            testUpload("f96f807e-d615-4bd4-8f89-a558c195e87f.jpg");
            testUploadWithDir("f96f807e-d615-4bd4-8f89-a558c195e87f.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}