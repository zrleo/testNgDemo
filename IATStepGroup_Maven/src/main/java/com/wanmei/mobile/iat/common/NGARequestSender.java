package com.wanmei.mobile.iat.common;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.testng.Assert;

import net.sf.json.JSONObject;




import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.gateside.autotesting.Lib.httpunitService.HttpUnitHelper;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;


public class NGARequestSender {

	public static String sendRequest(String userID, String url, String parameterID,HashMap<String, String> userParameters,String codeNum) throws Exception 
	{
		String result = "";
		int count = 0;
		UserRelated detailSteps = new UserRelated();
		while (count < 30) 
		{
			String accessToken = detailSteps.loginToGetLatestToken(parameterID);
			String timeStamp = String.valueOf(Calendar.getInstance()
					.getTimeInMillis() / 1000);
			String signString = getSignCode(GlobalConfig.app_id, userID,
					accessToken, userParameters, timeStamp, GlobalConfig.appkey);
			WebConversation wc = new WebConversation();
			WebRequest req = new PostMethodWebRequest(url);
			req.setParameter("app_id", GlobalConfig.app_id);
			req.setParameter("access_uid", userID);
			req.setParameter("access_token", accessToken);

			setUserParameter(req, userParameters);
			req.setParameter("t", timeStamp);
			req.setParameter("app_key", GlobalConfig.appkey);
			req.setParameter("sign", signString);
			
			WebResponse response = wc.getResource(req);
			

			String code = JSONObject.fromObject(response.getText()).getString("code");
			if (code.equals(codeNum)) 
			{
				result = response.getText();
				response.close();
				break;
			}
			SimpleLogger.logInfo(response.getText());
			Thread.sleep(800);
			response.close();
			count++;
		}
		return result;
	}

	public static void noLoginSendRequest(InterfaceStepParameter parameter,Map<String, String> userParameters,String codeNum)throws Exception
	 {
		 
		 WebConversation wc = new WebConversation();
		 WebRequest req = new PostMethodWebRequest( parameter.getValue("url") );
		 if (userParameters != null) 
		 {
				Iterator<Entry<String, String>> it = userParameters.entrySet().iterator();
				while (it.hasNext()) 
				{
					Entry parameterEntry = (Entry) it.next();
					req.setParameter(parameterEntry.getKey().toString(), parameterEntry.getValue().toString());
				}
		 }
		 WebResponse response=wc.getResource(req);
	     String code=JSONObject.fromObject(response.getText()).getString("code");
	     Assert.assertEquals(code,codeNum,"the returned code is not "+codeNum+" !!!");
	 }
	
	private static void setUserParameter(WebRequest request,
			Map<String, String> userParameters) 
	{
		Iterator<Entry<String, String>> it = userParameters.entrySet()
				.iterator();
		while (it.hasNext()) 
		{
			Entry parameter = (Entry) it.next();
			String parameterKey = parameter.getKey().toString();
			if (parameterKey.startsWith("#")) 
			{
				parameterKey = parameterKey.substring(1);
			}
			request.setParameter(parameterKey, parameter.getValue().toString());
		}
	}

	private static String getSignCode(String app_id, String uid,
			String accessToken, HashMap<String, String> userParameters,
			String timeStamp, String appkey) throws Exception 
	{
		String signString = getSignString(app_id, uid, accessToken,
				userParameters, timeStamp, appkey);
		return getMD5Code(signString);
	}

	private static String getSignString(String app_id, String uid,
			String accessToken, HashMap<String, String> userParameters,
			String timeStamp, String appkey) {
		String signString = app_id + uid + accessToken;
		if (userParameters != null) {
			Iterator<Entry<String, String>> it = userParameters.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry parameter = (Entry) it.next();
				if (parameter.getKey().toString().startsWith("#"))
				{
					signString = signString + parameter.getValue().toString();
				}
				
			}
		}
		signString = signString + timeStamp + appkey;
		return signString;
	}

	public static String getMD5Code(String str) throws Exception 
	{
		byte[] buf = str.getBytes();
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(buf);
		byte[] tmp = md5.digest();
		StringBuilder sb = new StringBuilder();
		for (byte b : tmp) {
			sb.append(Integer.toHexString(b & 0xff));
		}
		return sb.toString();
	}
	public static JSONObject requestSender(String url,String method,TreeMap<String, String> urlParameters,String token,String agent) throws Exception
	{
		WebConversation wc=HttpUnitHelper.createConversation();
		WebRequest req=HttpUnitHelper.createWebRequest(url,method);
		System.out.println(url);
		setHeader(req, urlParameters);
		CommonHelper.printMap(urlParameters);
		 if(urlParameters!=null)
		 {
			 Iterator<Entry<String, String>> it = urlParameters.entrySet().iterator();
		        while(it.hasNext())
		        {          
		           Entry  parameter = (Entry) it.next();
		           req.setParameter(parameter.getKey().toString(),parameter.getValue().toString());
		        }
		 }else 	
		 {
			 SimpleLogger.logInfo(NGARequestSender.class, "urlParameters are not completed");
		 }
		 WebResponse response = wc.getResource(req);
		 SimpleLogger.logInfo(NGARequestSender.class, response.getText());
		 JSONObject object=JSONObject.fromObject(response.getText());
		 return object;
	}
	
	
	public static void setHeader(WebRequest req,TreeMap<String, String> urlParameters) throws Exception{
		String agent = RequestParams.AppParams.USER_AGENT_ANDROID;
		System.out.println(RequestParams.AppParamsKey.USER_AGENT + ":" + agent);
		req.setHeaderField(RequestParams.AppParamsKey.USER_AGENT, agent);	 
	}
	
	public static JSONObject requestSenderOld(String url,String method,TreeMap<String, String> urlParameters) throws Exception
	{
		WebConversation wc=HttpUnitHelper.createConversation();
		WebRequest req=HttpUnitHelper.createWebRequest(url,method);
		System.out.println(url);
		CommonHelper.printMap(urlParameters);
		 if(urlParameters!=null)
		 {
			 Iterator<Entry<String, String>> it = urlParameters.entrySet().iterator();
		        while(it.hasNext())
		        {          
		           Entry  parameter = (Entry) it.next();
		           req.setParameter(parameter.getKey().toString(),parameter.getValue().toString());
		        }
		 }else 
		 {
			 SimpleLogger.logInfo(NGARequestSender.class, "urlParameters are not completed");
		 }
		 WebResponse response = wc.getResource(req);
		 SimpleLogger.logInfo(NGARequestSender.class, response.getText());
		 JSONObject object=JSONObject.fromObject(response.getText());
		 return object;
	}

}
