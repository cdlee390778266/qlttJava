package com.qianlong.webapp.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qianlong.webapp.domain.HttpContent;
import com.qianlong.webapp.service.IHttpService;
import com.qianlong.webapp.utils.Constants;

@Service("httpService")
public class HttpServiceImpl implements IHttpService {

	private Logger logger = LoggerFactory.getLogger(HttpServiceImpl.class);
	
	@Override
	public <T, E> HttpContent<T, E> httpGet(String scheme, String host, String path, List<NameValuePair> nvps, Class<T> content, Class<E> message)
			throws URISyntaxException, ClientProtocolException, IOException {
		return httpGet(scheme, host, -1, path, nvps, content, message);
	}

	@Override
	public <T, E> HttpContent<T, E> httpGet(String scheme, String host, int port, String path, List<NameValuePair> nvps, Class<T> content, Class<E> message)
			throws URISyntaxException, ClientProtocolException, IOException {
		return httpRequest(new HttpGet(), scheme, host, port, path, nvps, content, message);
	}
	
	@Override
	public <T, E> HttpContent<T, E> httpPost(String scheme, String host, String path, List<NameValuePair> nvps, JSONObject json,
			Class<T> content, Class<E> message) throws URISyntaxException, ClientProtocolException, IOException {
		return httpPost(scheme, host, -1, path, nvps, json, content, message);
	}

	@Override
	public <T, E> HttpContent<T, E> httpPost(String scheme, String host, int port, String path, List<NameValuePair> nvps, JSONObject json,
			Class<T> content, Class<E> message) throws URISyntaxException, ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost();
		logger.debug(String.format("传入的json为:%s", json.toJSONString()));
		HttpEntity entity = EntityBuilder.create().setText(json.toJSONString()).setContentType(ContentType.APPLICATION_JSON).setContentEncoding("utf-8").build();
		httpPost.setEntity(entity);
		return httpRequest(httpPost, scheme, host, port, path, nvps, content, message);
	}

	@Override
	public <T, E> HttpContent<T, E> httpRequest(HttpRequestBase requestBase, String scheme, String host, int port, String path,
			List<NameValuePair> nvps, Class<T> content, Class<E> message) throws URISyntaxException, ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		URIBuilder builder = new URIBuilder();
		
		if (!StringUtils.isEmpty(scheme)) {
			builder.setScheme(scheme);
		}
		if (!StringUtils.isEmpty(host)) {
			builder.setHost(host);
		}
		if (port > 0) {
			builder.setPort(port);
		}
		if (!StringUtils.isEmpty(path)) {
			builder.setPath(path);
		}
		if (!CollectionUtils.isEmpty(nvps)) {
			builder.setParameters(nvps);
		}
		
		URI uri = builder.build();
		logger.debug("URI为:{}", uri.toString());
		requestBase.setURI(uri);
		CloseableHttpResponse response = client.execute(requestBase);
		HttpEntity entity = response.getEntity();
		ByteArrayOutputStream bi = new ByteArrayOutputStream();
		entity.writeTo(bi);
		
		JSONObject result = JSON.parseObject(bi.toString());
		bi.close();
		
		HttpContent<T, E> httpContent = new HttpContent<>();
		if (result.containsKey(Constants.USER_SERV_IDENTIFICATION_CODE) || result.containsKey(Constants.WECHAT_IDENTIFICATION_CODE)) {
			E e = JSON.parseObject(result.toJSONString(), message);
			httpContent.setMessage(e);
		} else {
			T t = JSON.parseObject(result.toJSONString(), content);
			httpContent.setContent(t);
		}
		return httpContent;
	}

}
