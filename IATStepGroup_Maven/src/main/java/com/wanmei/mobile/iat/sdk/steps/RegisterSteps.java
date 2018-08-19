package com.wanmei.mobile.iat.sdk.steps;

import java.util.LinkedHashMap;



import org.testng.Assert;

import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RandomHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

import javax.sound.midi.Soundbank;

public class RegisterSteps {
	public void register(String parameterID) throws Exception 
	{
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject result = RequestHelper.requestSender(Constant.Url.REGISTER, setParametersFor178Register(parameter));
		if(result.getString("code").equals("0"))
		{
			StepValueHelper.putStepOutputValue("access_uid", result.getJSONObject("result").getString("uid"));
			StepValueHelper.putStepOutputValue("access_token", result.getJSONObject("result").getString("access_token"));	
		}
		else 
			Assert.assertEquals(parameter.getValue("code"), result.getString("code"));
		
		  
	}
	
	private LinkedHashMap<String, String> setParametersFor178Register(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters = new LinkedHashMap<String, String>();
		String email=ParamHelper.setParamByTwoWay(parameter, urlParameters, "email", 
				RandomHelper.getRandomString(10, Constant.RANDOM_VALUE_ALL)+"@163.com");
		String nickname=ParamHelper.setParamByTwoWay(parameter, urlParameters, "nickname",
				RandomHelper.getRandomString(6, Constant.RANDOM_VALUE_ALL));
		String password=ParamHelper.setParameterToValuePool(urlParameters, "password", ParamHelper.getPwd(parameter, "password"));
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters, email+nickname+password);
		System.out.println("==================");
		System.out.println(urlParameters);
		return urlParameters;
	}

	
}
