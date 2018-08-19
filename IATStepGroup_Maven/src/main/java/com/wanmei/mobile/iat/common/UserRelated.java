package com.wanmei.mobile.iat.common;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValuePool;
import com.gateside.autotesting.Lib.httpunitService.HttpUnitHelper;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class UserRelated {
		    
    @StepMethodDesc(description="first step login to get latest token",owner="yaxiao.yu")
	public String loginToGetLatestToken(String parameterID)throws Exception
	{
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		WebConversation currentConversation=HttpUnitHelper.createConversation();
	    WebRequest currentRequest=HttpUnitHelper.createWebRequest(parameter.getValue("url"),parameter.getValue("httpmethod"));
	    HashMap<String, String> urlParameters=parameter.getURLParametersMap();
	     if(urlParameters.size()!=0)
	     {
	         HttpUnitHelper.setParameters(currentRequest,urlParameters);
	     }
	     WebResponse response=currentConversation.getResponse(currentRequest);
	     String lastestToken=JSONObject.fromObject(response.getText()).getJSONObject("result").getString("access_token");
	     StepValuePool.createInstance().getValueDic().put("userToken",lastestToken);
	     return lastestToken;
	}
	 
}
