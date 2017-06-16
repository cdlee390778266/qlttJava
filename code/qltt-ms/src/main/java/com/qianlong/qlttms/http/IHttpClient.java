package com.qianlong.qlttms.http;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpRequestBase;

import com.alibaba.fastjson.JSONObject;
import com.qianlong.qlttms.domain.HttpContent;

public interface IHttpClient {

	<T, E> HttpContent<T, E> httpGet(String scheme, String host, String path,
			List<NameValuePair> nvps, Class<T> content, Class<E> message)
			throws URISyntaxException, ClientProtocolException, IOException;

	<T, E> HttpContent<T, E> httpGet(String scheme, String host, int port,
			String path, List<NameValuePair> nvps, Class<T> content,
			Class<E> message)
			throws URISyntaxException, ClientProtocolException, IOException;

	<T, E> HttpContent<T, E> httpPost(String scheme, String host, String path,
			List<NameValuePair> nvps, JSONObject json, Class<T> content,
			Class<E> message)
			throws URISyntaxException, ClientProtocolException, IOException;

	<T, E> HttpContent<T, E> httpPost(String scheme, String host, int port,
			String path, List<NameValuePair> nvps, JSONObject json,
			Class<T> content, Class<E> message)
			throws URISyntaxException, ClientProtocolException, IOException;

	<T, E> HttpContent<T, E> httpRequest(HttpRequestBase requestBase,
			String scheme, String host, int port, String path,
			List<NameValuePair> nvps, Class<T> content, Class<E> message)
			throws URISyntaxException, ClientProtocolException, IOException;
}
