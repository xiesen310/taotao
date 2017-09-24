package com.taotao.httpclient;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {
	@Test
	public void doGet()throws Exception{
		// 创建一个httpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建一个Get对象
		HttpGet get = new HttpGet("http://www.sogou.com?id=123&name=456");
		// 执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		// 取响应的结果
		int statusCode = response.getStatusLine().getStatusCode();// 获取状态码
		System.out.println(statusCode);
		HttpEntity entity = response.getEntity(); //获取文内容
		String string = EntityUtils.toString(entity,"utf-8");
		System.out.println(string);
		// 关闭httpClient
		response.close();
		httpClient.close();
	}
	
	@Test
	public void doGetWithParam()throws Exception{
		// 创建一个httpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建一个uri对象
		URIBuilder uriBuilder = new URIBuilder("http://www.sogou.com/web");
		uriBuilder.addParameter("query", "朝鲜发生地震");
		HttpGet get = new HttpGet(uriBuilder.build());
		// 执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		// 取响应的结果
		int statusCode = response.getStatusLine().getStatusCode();// 获取状态码
		System.out.println(statusCode);
		HttpEntity entity = response.getEntity(); //获取文内容
		String string = EntityUtils.toString(entity,"utf-8");
		System.out.println(string);
		// 关闭httpClient
		response.close();
		httpClient.close();
	}
	
	@Test
	public void doPost()throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建一个post对象
		HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.html");
		// 执行Post请求
		CloseableHttpResponse response = httpClient.execute(post);
		String string = EntityUtils.toString(response.getEntity());
		System.out.println(string);
		response.close();
		httpClient.close();
	}
	
	@Test
	public void doPostWithParam()throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// 创建一个post对象
		HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.html");
		// 创建一个Entity，模拟表单
		List<NameValuePair> kvList = new ArrayList<>();
		kvList.add(new BasicNameValuePair("username", "zhangsan"));
		kvList.add(new BasicNameValuePair("password", "123"));
		// 包装成一个Entity对象
		StringEntity entity = new UrlEncodedFormEntity(kvList);
		// 设置请求的内容
		post.setEntity(entity);
		
		// 执行Post请求
		CloseableHttpResponse response = httpClient.execute(post);
		String string = EntityUtils.toString(response.getEntity());
		System.out.println(string);
		response.close();
		httpClient.close();
	}
}