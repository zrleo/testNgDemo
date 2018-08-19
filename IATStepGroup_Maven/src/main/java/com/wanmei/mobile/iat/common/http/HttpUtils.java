package com.wanmei.mobile.iat.common.http;


import java.util.concurrent.Future;

import android.content.Context;

/**
 * @ClassName: HttpUtils
 * @Description: Http
 * @author yingjun fan
 * @date 2012-10-29 
 * @email yingjun@leju.com
 *
*/
public class HttpUtils {
	private static AsyncHttpClient client = new AsyncHttpClient();
  
	public static Future<?> get(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		//Utils.log("HTTP_GET", url + "?" + (params == null ? "" : params.toString()));
		return client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	public static Future<?> post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		//Utils.log("HTTP_POSTURL", url + "\n---POSTDATA:" + (params == null ? "" : params.toString()));
		return client.post(getAbsoluteUrl(url), params, responseHandler);
	}
	
	public static void setUserAgent(String userAgent){
		client.setUserAgent(userAgent);
	}
	/**
	 * 
	 * @param context
	 */
	public static void cancelRequest(Context context) {
		client.cancelRequests(context, true);
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		//return BASE_URL + relativeUrl;
		return relativeUrl;
	}
}
