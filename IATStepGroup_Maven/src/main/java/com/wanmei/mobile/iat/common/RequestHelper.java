package com.wanmei.mobile.iat.common;
//package com.wanmei.mobile.iat.common.http;


import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.gateside.autotesting.Lib.httpunitService.HttpUnitHelper;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class RequestHelper {
	
	public static JSONObject requestSender(String url,LinkedHashMap<String, String> urlParameters) throws Exception
	{
		WebConversation wc=HttpUnitHelper.createConversation();
		WebRequest req=HttpUnitHelper.createWebRequest(url,Constant.HTTP_METHOD);
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
			 SimpleLogger.logInfo(RequestHelper.class, "urlParameters are not completed");
		 }
		 WebResponse response = wc.getResource(req);
		 SimpleLogger.logInfo(RequestHelper.class, response.getText());
		 JSONObject object=JSONObject.fromObject(response.getText());
		 return object;
	}
	public static JSONObject requestSender1(String url,LinkedHashMap<String,String> urlParameters,String token,String agent) throws Exception
	{
		WebConversation wc=HttpUnitHelper.createConversation();
		WebRequest req=HttpUnitHelper.createWebRequest(url,Constant.HTTP_METHOD);
		System.out.println(url);
		setHeader(req, urlParameters, token, agent);
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
			 SimpleLogger.logInfo(RequestHelper.class, "urlParameters are not completed");
		 }
		 WebResponse response = wc.getResource(req);
		 SimpleLogger.logInfo(RequestHelper.class, response.getText());
		 JSONObject object=JSONObject.fromObject(response.getText());
		 return object;
	}
	      
	public static void setNessesaryParamAndSign(InterfaceStepParameter parameter,LinkedHashMap<String,String>urlParameters,String param) throws Exception{
		String access_uid = setParamFromValuePool(urlParameters, "access_uid"); 	
		String access_token = setParamFromValuePool(urlParameters, "access_token");
		String t=DateHelper.getTimeStampSec();
//		String os = ParamHelper.setParamByThreeWay(parameter, urlParameters, "os", "andorid");
		urlParameters.put("app_id", Constant.APP_ID);
		urlParameters.put("t", t);
//		urlParameters.put("api_version","1");
		String sign=Constant.APP_ID+access_uid+access_token+param+t+Constant.APP_KEY;	
		System.out.println("SIGN:"+sign);
		urlParameters.put("sign", CommonHelper.md5(sign));
	}
	
	private static String setParamFromValuePool(HashMap<String,String>urlParameters,String paramName){
		String param = "";
		if (StepValueHelper.getStepOutputValue(paramName) != null) {
			param = StepValueHelper.getStepOutputValue(paramName).toString();
			urlParameters.put(paramName,param);
		}
		return param;
	}
//	public static void setHeader(WebRequest req,LinkedHashMap<String,String>urlParameters) throws Exception{
//		String agent = RequestParam.AppParams.USER_AGENT_ANDROID;
//		System.out.println(RequestParam.AppParamsKey.USER_AGENT + ":" + agent);
//		req.setHeaderField(RequestParam.AppParamsKey.USER_AGENT, agent);	 
//	}
	private static void setHeader(WebRequest req,LinkedHashMap<String,String> urlParameters,String token,String agent) throws Exception{
//		String authorization = generateAuthorization(urlParameters, token, Long.valueOf(DateHelper.getTimeInMillis())/1000+300);
		if (agent.equals("1")) {
			agent = RequestParams.AppParams.USER_AGENT_ANDROID;
		}else if(agent.equals("2")){
			agent = RequestParams.AppParams.USER_AGENT_IOS;
		}
		System.out.println(RequestParams.AppParamsKey.USER_AGENT + ":" + agent);
//		System.out.println(RequestParam.AppParamsKey.AUTHORIZATION + ":" + authorization);
		req.setHeaderField(RequestParams.AppParamsKey.USER_AGENT, agent);	 
//		req.setHeaderField(RequestParam.AppParamsKey.AUTHORIZATION, authorization);
	}
	public static JSONObject sendRequest_Old(InterfaceStepParameter parameter ,LinkedHashMap<String, String> setParam ,String url,String resourceName) throws Exception {
		LinkedHashMap<String, String> urlParameters = setParam;
		String urlValue=url;
		System.out.println(urlValue+"?" );
		CommonHelper.printMap(urlParameters);
	    String act =HttpConnection.getResultContentWithMultiFormData(urlValue,urlParameters, resourceName, GlobalConfig.getRootDir()+parameter.getValue(resourceName));	
	    SimpleLogger.logInfo(act);
	    return JSONObject.fromObject(act);
	}
	 
	

		
}
