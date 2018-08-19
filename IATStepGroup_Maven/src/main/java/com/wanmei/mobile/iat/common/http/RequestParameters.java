package com.wanmei.mobile.iat.common.http;

import java.security.MessageDigest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.xslf.model.geom.SinArcTanExpression;

import com.meterware.httpunit.WebConversation;
import com.wanmei.mobile.iat.common.GlobalConfig;

public class RequestParameters 
{
	public String app_id=GlobalConfig.app_id;
	public String accessToken="";
	public String timestamp=String.valueOf(Calendar.getInstance().getTimeInMillis()/1000);
	public String uid="";
	public String appkey=GlobalConfig.appkey;
	public String requestUrl="";
	public String sign="";
	public Map<String,String> userParameters=new HashMap<String, String>();
	public RequestParameters(String token,String userid,String url,Map<String,String> uparamters) throws Exception 
	{
		this.accessToken=token;
		this.uid=userid;
		this.userParameters=uparamters;
		this.requestUrl=url;
		this.sign=getSignCode();
	}
	
	
	 private String getSignCode() throws Exception
	 {
        String signString=getSignString();
        return getMD5Code(signString);
	 }
	 
	 private String getSignString()
	 {
		 String signString=this.app_id+this.uid+this.accessToken;
			if(userParameters!=null)
			{
				Iterator<Entry<String, String>> it =  userParameters.entrySet().iterator();
		        while(it.hasNext())
		        {          
		           Entry  parameter = (Entry) it.next();
		           if(parameter.getKey().toString().startsWith("#"))
		           {
		        	   signString=signString+parameter.getValue().toString();
		           }
		        }
			}
			signString=signString+timestamp+appkey;
			return null;
	 }
	 
	 private String getMD5Code(String str) throws Exception
	    {
	        byte [] buf = str.getBytes();
	        MessageDigest md5 = MessageDigest.getInstance("MD5");
	        md5.update(buf);
	        byte [] tmp = md5.digest();
	        StringBuilder sb = new StringBuilder();
	        for (byte b:tmp) {
	            sb.append(Integer.toHexString(b&0xff));
	        }
	        return sb.toString();
	    }
}
