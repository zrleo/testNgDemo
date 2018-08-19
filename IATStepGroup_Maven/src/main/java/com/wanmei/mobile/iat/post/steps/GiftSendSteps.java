package com.wanmei.mobile.iat.post.steps;

import java.util.LinkedHashMap;
import java.util.TreeMap;

import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.wanmei.mobile.iat.common.CommonHelper;
import com.wanmei.mobile.iat.common.Constant;


public class GiftSendSteps {
//	@StepMethodDesc(description="giftSend,nga interface 51",owner="Ruiyun.Ren")
//	public void giftSend(String parameterID)throws Exception{	
//		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
//		JSONObject actual=NgaHelper.requestSender(Constant.Url.GiftSend, Constant.HTTP_METHOD, setParam(parameter));	
//        
//			
//		}
//
//		private LinkedHashMap<String, String> setParam(InterfaceStepParameter parameter) throws Exception
//		{
//			LinkedHashMap<String, String> urlparameters= new LinkedHashMap<String, String>;
//			String sign = "";		
//			urlparameters.put("app_id", GlobalConfig.app_id);
//			sign = sign + GlobalConfig.app_id;	
//			if(StepValueHelper.getStepOutputValue("uid")!=null)
//			{
//				String uid=(String)StepValueHelper.getStepOutputValue("uid");
//				urlparameters.put("access_uid",uid);
//				sign=sign+uid;
//			}
//			if(StepValueHelper.getStepOutputValue("token")!=null)
//			{
//				String access_token=(String)StepValueHelper.getStepOutputValue("token");
//				urlparameters.put("access_token",access_token);
//				sign=sign+access_token;
//			}
//			urlparameters.put("userid", parameter.getValue("userid"));
//			urlparameters.put("username", parameter.getValue("username"));
//			urlparameters.put("tid", parameter.getValue("tid"));
//	        sign=sign+parameter.getValue("tid");
//			if(parameter.getValue("pid")!=null)
//	        {
//	        	urlparameters.put("pid", parameter.getValue("pid"));
//	        	sign=sign+parameter.getValue("pid");
//	        }
//	        urlparameters.put("id", parameter.getValue("id"));
//	        sign=sign+parameter.getValue("id")+parameter.getValue("userid")+parameter.getValue("username");
//			String t = CommonHelper.getTimeStampSec();
//	        urlparameters.put("t", t);       
//			urlparameters.put("sign", CommonHelper.md5(sign+t+GlobalConfig.appkey));
//			return urlparameters;
//		}

}
