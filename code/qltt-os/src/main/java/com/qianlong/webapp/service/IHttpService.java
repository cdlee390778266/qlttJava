package com.qianlong.webapp.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpRequestBase;

import com.alibaba.fastjson.JSONObject;

public interface IHttpService {
	
	/**
	 * 
	 * @param scheme
	 * @param host
	 * @param path
	 * @param nvps
	 * @param clazz
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public <T> T httpGet(String scheme, String host, String path, List<NameValuePair> nvps, Class<T> clazz)
			throws URISyntaxException, ClientProtocolException, IOException;

	/**
	 * 
	 * @param scheme 协议类型
	 * @param host 地址
	 * @param port 端口
	 * @param path 请求路径
	 * @param nvps 请求参数对
	 * @param clazz
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	<T> T httpGet(String scheme, String host, int port, String path, List<NameValuePair> nvps, Class<T> clazz)
			throws URISyntaxException, ClientProtocolException, IOException;
	
	/**
	 * 
	 * @param scheme
	 * @param host
	 * @param path
	 * @param nvps
	 * @param json
	 * @param clazz
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public <T> T httpPost(String scheme, String host, String path, List<NameValuePair> nvps, JSONObject json,
			Class<T> clazz) throws URISyntaxException, ClientProtocolException, IOException;

	/**
	 * 
	 * @param scheme 协议类型
	 * @param host 地址
	 * @param port 端口
	 * @param path 请求路径
	 * @param nvps 附着在URL后的请求参数对
	 * @param json JSON格式的post请求体
	 * @param clazz
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	<T> T httpPost(String scheme, String host, int port, String path, List<NameValuePair> nvps, JSONObject json, Class<T> clazz)
			throws URISyntaxException, ClientProtocolException, IOException;

	/**
	 * 
	 * @param requestBase 请求类型
	 * @param scheme 协议类型
	 * @param host 地址
	 * @param port 端口
	 * @param path 请求路径
	 * @param nvps 请求参数对
	 * @param clazz
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	<T> T httpRequest(HttpRequestBase requestBase, String scheme, String host, int port, String path, List<NameValuePair> nvps,
			Class<T> clazz) throws URISyntaxException, ClientProtocolException, IOException;
}
