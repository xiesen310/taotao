package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

public class FtpTest {
	@Test
	public void testFtpClient()throws Exception {
		// 创建一个ftpClient对象
		FTPClient ftpClient = new FTPClient(); 
		// 创建ftp连接
		ftpClient.connect("192.168.159.130", 21);
		// 登录ftp服务器,使用用户名和密码
		ftpClient.login("ftpuser", "ftpuser");
		// 读取本地文件
		FileInputStream inputStream = new FileInputStream(new File("D:\\images\\dddddddddddd (1).jpg")); 
		// 设置上传路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		// 修改上传文件的格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		// 上传文件
		// 参数1:服务端文档名,参数2:上传文件的inputStream
		ftpClient.storeFile("hello1.jpg", inputStream);
		// 关闭连接
		ftpClient.logout();
	}
	
	@Test
	public void testFtpUtil() throws Exception {
		FileInputStream inputStream = new FileInputStream(new File("D:\\images\\dddddddddddd (1).jpg")); 
		FtpUtil.uploadFile("192.168.159.130", 21, "ftpuser", "ftpuser", "/home/ftpuser/www/images","/2017/09/16", "hello.jpg", inputStream);
	}
}
