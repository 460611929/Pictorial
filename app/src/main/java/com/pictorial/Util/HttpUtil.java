package com.pictorial.Util;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.HttpEntity;
import org.apache.http.protocol.HTTP;

public class HttpUtil {

	private static AsyncHttpClient client = new AsyncHttpClient(); // 实例话对象

	static {

		client.setTimeout(10000); // 设置链接超时，如果不设置，默认为10s
	}

	// 用一个完整url获取一个string对象
	public static void get(String urlString, AsyncHttpResponseHandler res) {

		client.get(urlString, res);

	}

	// url里面带参数
	public static void get(String urlString, RequestParams params,
			AsyncHttpResponseHandler res) {

		client.get(urlString, params, res);

	}

	// 不带参数，获取json对象或者数组
	public static void get(String urlString, JsonHttpResponseHandler res) {
		client.get(urlString, res);

	}

	// 不带参数，获取json对象或者数组
	public static void get(String urlString, TextHttpResponseHandler res) {
		client.get(urlString, res);

	}

	// 带参数，获取json对象或者数组
	public static void get(String urlString, RequestParams params,
			JsonHttpResponseHandler res) {

		client.get(urlString, params, res);

	}

	// 下载数据使用，会返回byte数据
	public static void get(String uString, BinaryHttpResponseHandler bHandler) {

		client.get(uString, bHandler);

	}

	public static void post(String urlString, RequestParams params,
			TextHttpResponseHandler bHandler) {

		client.post(urlString, params, bHandler);

	}

	public static void post1(Context context, String urlString,
			HttpEntity entity, JsonHttpResponseHandler bHandler) {

		// client.post(context, urlString, null, params, contentType, bHandler);
		client.post(context, urlString, entity, HTTP.CONTENT_TYPE, bHandler);

	}

	// 下载数据使用，会返回byte数据
	public static void put(String urlString, RequestParams params,
			JsonHttpResponseHandler bHandler) {
		client.put(urlString, params, bHandler);

	}

	public static AsyncHttpClient getClient() {

		return client;
	}

	public static void cancelRequst(Context context) {
		client.cancelRequests(context, true);
	}

}
