package com.wanmei.mobile.iat.common;


import java.io.IOException;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.Assert;

import net.sf.json.JSONObject;
import android.R.integer;



import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.gateside.autotesting.Gat.util.StepValuePool;
import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class NgaForumRequestHelper {

		public static String sendRequest(String userID, String url,HashMap<String, String> userParameters,String codeNum) throws Exception 
		{
			String result = "";
			int count = 0;

			while (count < 100) 
			{
				String accessToken = (String) StepValuePool.createInstance().getValueDic().get("token");
				String timeStamp = String.valueOf(Calendar.getInstance()
						.getTimeInMillis() / 1000);
				String signString = getSignCode(GlobalConfig.app_id, userID,
						accessToken, userParameters, timeStamp, GlobalConfig.appkey);				
				WebConversation wc = new WebConversation();
				WebRequest req = new PostMethodWebRequest(url);
				req.setParameter("app_id", GlobalConfig.app_id);
				req.setParameter("access_uid", userID);
				req.setParameter("access_token", accessToken);			
				appendParameterToRequest(req, userParameters);
				 CommonHelper.printMap(userParameters);
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
		
		public static HashMap<String, String> setParameterMapForNgaForum(InterfaceStepParameter parameter)
		 {
			 LinkedHashMap<String, String> userParameterMap=new LinkedHashMap<String, String>();		 		 
			 for(int i=0;i<parameter.parameters.size();i++)
			 {
				 String key = parameter.parameters.get(i).key;
				 userParameterMap=setInterfaceParameters(key, parameter, userParameterMap);
			 }	
			 CommonHelper.printMap(userParameterMap);
			 return userParameterMap;
		 }
		
		public static LinkedHashMap<String, String>  setInterfaceParameters(String key,InterfaceStepParameter parameter,LinkedHashMap<String, String> userParameterMap)
		{
			if(key.startsWith("%"))
			 {
				 if(key.equals("%#fid"))
					 userParameterMap = setFidForPostNew(key, parameter, userParameterMap);
				 else if (key.equals("%#subject")) 
					 userParameterMap = setTitleOrContentForPostNew(10, key, parameter, userParameterMap);
				 else if (key.equals("%#content")) 
					 userParameterMap = setTitleOrContentForPostNew(20, key, parameter, userParameterMap);
				 else 
					 userParameterMap.put(key.substring(1), parameter.getValue(key));
			 }
			return userParameterMap;
		}
		
		private static LinkedHashMap<String, String> setFidForPostNew(String key,InterfaceStepParameter parameter,LinkedHashMap<String, String> userParameterMap)
		{
			 if(!(parameter.getValue(key).isEmpty()||parameter.getValue(key).equals("")))
				 userParameterMap.put(key.substring(1), parameter.getValue(key));
			 else 
				 userParameterMap.put(key.substring(1), (String) StepValueHelper.getStepOutputValue("fid"));
			 return userParameterMap;
		}
		
		private static LinkedHashMap<String, String> setTitleOrContentForPostNew(int length,String key,InterfaceStepParameter parameter,LinkedHashMap<String, String> userParameterMap)
		{
			 if((parameter.getValue(key).isEmpty()||parameter.getValue(key).equals("")))
				 userParameterMap.put(key.substring(1),RandomHelper.getRandomString(length,Constant.RANDOM_VALUE_ALL) );
			 else 
				 userParameterMap.put(key.substring(1), parameter.getValue(key));
			 return userParameterMap;
		}
		
		private static void appendParameterToRequest(WebRequest request,
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
		public static void assertCodeAndCheckToken(String response,String codeNum,String checktokenNum)throws Exception
		 {
			 String code=JSONObject.fromObject(response).getString("code");
		     Assert.assertEquals(code,codeNum,"the returned code is not "+codeNum+" !!!");
		     if(JSONObject.fromObject(response).containsKey("checktoken"))
		     {
		         String checktoken=JSONObject.fromObject(response).getString("checktoken");
		         String checktokenmsg=JSONObject.fromObject(response).getString("checktokenmsg");
		         Assert.assertEquals(checktoken,checktokenNum,"the returned checktoken is not "+checktokenNum+" !!!");
		         Assert.assertEquals(checktokenmsg,"","the returned checktokenmsg is not null !!!");
		     }
		 }
		
		public static int getIndexFromDatabase(String name,List<List<String>> resultSet,int indexInResultSet)
		{
			int index=-1;
			for(int i=0;i<resultSet.size();i++)
			{
				if(name.equals(resultSet.get(i).get(indexInResultSet)))
				{
					index=i;
					break;
				}
			}
			return index;
		}
	}


