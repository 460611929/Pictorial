package com.pictorial.net;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;

/**
 * @author zhangbing1
 * 
 */

@SuppressLint("NewApi")
public class RequestManager {
	static RequestQueue mRequestQueue;

	private RequestManager() {
	}

	public static void init(Context context) {
		mRequestQueue = Volley.newRequestQueue(context);
	}

	/**
	 * 返回String
	 * 
	 * @param url
	 *            连接
	 * @param tag
	 *            上下文
	 * @param listener
	 *            回调
	 */
	public static void get(String url, Object tag, RequestListener listener) {
		ByteArrayRequest request = new ByteArrayRequest(Request.Method.GET,
				url, null, responseListener(listener, false, null,
						(Context) tag), responseError(listener, false, null,
						(Context) tag));
		addRequest(request, tag);
	}

	/**
	 * 返回String
	 * 
	 * @param url
	 *            连接
	 * @param tag
	 *            上下文
	 * 
	 * @param params
	 *            请求参数
	 * 
	 * @param listener
	 *            回调
	 */
	public static void get(String url, Object tag, RequestParams params,
			RequestListener listener) {

		ByteArrayRequest request = new ByteArrayRequest(Request.Method.GET,
				url, params, responseListener(listener, false, null,
						(Context) tag), responseError(listener, false, null,
						(Context) tag));
		addRequest(request, tag);
	}

	/**
	 * 返回String 带进度条
	 * 
	 * @param url
	 *            连接
	 * @param tag
	 *            上下文
	 * @param progressTitle
	 *            进度条文字
	 * @param listener
	 *            回调
	 */
	public static void get(String url, Object tag, String progressTitle,
			RequestListener listener) {
		ProgressDialog dialog = new ProgressDialog((Context) tag);
		// LoadingFragment dialog = new LoadingFragment();
		// dialog.show(((FragmentActivity) tag).getSupportFragmentManager(),
		// "Loading");
		dialog.setTitle(progressTitle);
		dialog.show();
		ByteArrayRequest request = new ByteArrayRequest(Request.Method.GET,
				url, null, responseListener(listener, true, dialog,
						(Context) tag), responseError(listener, true, dialog,
						(Context) tag));
		addRequest(request, tag);
	}

	/**
	 * 返回对象
	 * 
	 * @param url
	 *            连接
	 * @param tag
	 *            上下文
	 * @param classOfT
	 *            类对象
	 * @param listener
	 *            回调
	 */
	public static <T> void get(String url, Object tag, Class<T> classOfT,
			RequestJsonListener<T> listener) {
		ByteArrayRequest request = new ByteArrayRequest(Request.Method.GET,
				url, null, responseListener(listener, classOfT, false, null),
				responseError(listener, false, null));
		addRequest(request, tag);
	}

	/**
	 * 返回对象
	 * 
	 * @param url
	 *            连接
	 * @param tag
	 *            上下文
	 * @param classOfT
	 *            类对象
	 * @param progressTitle
	 *            进度条文字
	 * @param listener
	 *            回调
	 */
	public static <T> void get(String url, Object tag, Class<T> classOfT,
			String progressTitle, boolean LoadingShow,
			RequestJsonListener<T> listener) {
		ByteArrayRequest request = new ByteArrayRequest(Request.Method.GET,
				url, null, responseListener(listener, classOfT, LoadingShow,
						null), responseError(listener, LoadingShow, null));
		addRequest(request, tag);
	}

	/**
	 * 返回String
	 * 
	 * @param url
	 *            接口
	 * @param tag
	 *            上下文
	 * @param params
	 *            post需要传的参数
	 * @param listener
	 *            回调
	 */
	public static void post(String url, Object tag, RequestParams params,
			RequestListener listener) {
		ByteArrayRequest request = new ByteArrayRequest(Request.Method.POST,
				url, params, responseListener(listener, false, null,
						(Context) tag), responseError(listener, false, null,
						(Context) tag));
		addRequest(request, tag);
	}

	/**
	 * 返回String 带进度条
	 * 
	 * @param url
	 *            接口
	 * @param tag
	 *            上下文
	 * @param params
	 *            post需要传的参数
	 * @param progressTitle
	 *            进度条文字
	 * @param listener
	 *            回调
	 */
	public static void post(String url, Object tag, RequestParams params,
			String progressTitle, RequestListener listener) {
		ProgressDialog dialog = new ProgressDialog((Context) tag);
		dialog.setCancelable(false);
		dialog.setTitle(progressTitle);

		if (isValidContext((Context) tag))
			dialog.show();
		ByteArrayRequest request = new ByteArrayRequest(Request.Method.POST,
				url, params, responseListener(listener, true, dialog,
						(Context) tag), responseError(listener, true, dialog,
						(Context) tag));
		addRequest(request, tag);
	}

	private static boolean isValidContext(Context c) {

		Activity a = (Activity) c;

		if (a.isDestroyed() || a.isFinishing()) {
			Log.i("YXH",
					"Activity is invalid." + " isDestoryed-->"
							+ a.isDestroyed() + " isFinishing-->"
							+ a.isFinishing());
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 返回对象 带进度条
	 * 
	 * @param url
	 *            接口
	 * @param tag
	 *            上下文
	 * @param classOfT
	 *            类对象
	 * @param params
	 *            post需要传的参数
	 * @param progressTitle
	 *            进度条文字
	 * @param LoadingShow
	 *            true (显示进度) false (不显示进度)
	 * @param listener
	 *            回调
	 */
	public static <T> void post(String url, Object tag, Class<T> classOfT,
			RequestParams params, String progressTitle, boolean LoadingShow,
			RequestJsonListener<T> listener) {
		ByteArrayRequest request = new ByteArrayRequest(Request.Method.POST,
				url, params, responseListener(listener, classOfT, LoadingShow,
						null), responseError(listener, LoadingShow, null));
		addRequest(request, tag);
	}

	/**
	 * 成功消息监听 返回对象
	 * 
	 * @param l
	 * @return
	 */
	protected static <T> Response.Listener<byte[]> responseListener(
			final RequestJsonListener<T> l, final Class<T> classOfT,
			final boolean flag, final LoadingFragment p) {
		return new Response.Listener<byte[]>() {
			@Override
			public void onResponse(byte[] arg0) {
				String data = null;
				try {
					data = new String(arg0, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				l.requestSuccess(JsonUtils.object(data, classOfT));
				if (flag) {
					if (p.getShowsDialog()) {
						p.dismiss();
					}
				}
			}
		};
	}

	/**
	 * 对象返回错误监听
	 * 
	 * @param l
	 *            回调
	 * @param flag
	 *            flag true 带进度条 flase不带进度条
	 * @param p
	 *            进度条的对象
	 * @return
	 */
	protected static <T> Response.ErrorListener responseError(
			final RequestJsonListener<T> l, final boolean flag,
			final LoadingFragment p) {
		return new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError e) {
				l.requestError(e);
				if (flag) {
					if (p.getShowsDialog()) {
						p.dismiss();
					}
				}
			}
		};
	}

	/**
	 * 成功消息监听 返回String
	 * 
	 * @param l
	 *            String 接口
	 * @param flag
	 *            true 带进度条 flase不带进度条
	 * @param p
	 *            进度条的对象
	 * @return
	 */
	protected static Response.Listener<byte[]> responseListener(
			final RequestListener l, final boolean flag,
			final ProgressDialog p, final Context context) {
		return new Response.Listener<byte[]>() {
			@Override
			public void onResponse(byte[] arg0) {
				String data = null;
				try {
					data = new String(arg0, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				l.requestSuccess(data);
				if (flag) {
					if (isValidContext(context) && p.isShowing()) {
						p.dismiss();
					}
				}
			}
		};
	}

	/**
	 * String 返回错误监听
	 * 
	 * @param l
	 *            String 接口
	 * @param flag
	 *            true 带进度条 flase不带进度条
	 * @param p
	 *            进度条的对象
	 * @return
	 */
	protected static Response.ErrorListener responseError(
			final RequestListener l, final boolean flag,
			final ProgressDialog p, final Context context) {
		return new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError e) {
				l.requestError(e);
				if (flag) {
					if (isValidContext(context) && p.isShowing()) {
						p.dismiss();
					}
				}
			}
		};
	}

	public static void addRequest(Request<?> request, Object tag) {
		if (tag != null) {
			request.setTag(tag);
		}
		if (mRequestQueue != null) {
			request.setShouldCache(false);//数据量大的时候 设置为false
			mRequestQueue.add(request);
		}
	}

	/**
	 * 当主页面调用协议 在结束该页面调用此方法
	 * 
	 * @param tag
	 */
	public static void cancelAll(Object tag) {
		mRequestQueue.cancelAll(tag);
	}
}
