package com.wanmei.mobile.iat.sdk.steps;


import java.util.LinkedHashMap;
import net.sf.json.JSONObject;
import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

import javax.sound.midi.Soundbank;

public class LoginSteps {
	
	@StepMethodDesc(description="178 login interface to get uid and token ",owner="yaxiao.yu")
	public void login(String parameterID) throws Exception 
	{
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject result = RequestHelper.requestSender(Constant.Url.LOGIN, setParametersFor178Login(parameter));
		StepValueHelper.putStepOutputValue("access_uid", result.getJSONObject("result").getString("uid"));
		StepValueHelper.putStepOutputValue("access_token", result.getJSONObject("result").getString("access_token"));  
	}
	
	
	private LinkedHashMap<String, String> setParametersFor178Login(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters = new LinkedHashMap<String, String>();
		String email = ParamHelper.setParamExistOrValuePool(parameter, urlParameters, "email");
		String password = ParamHelper.setParameterToValuePool(urlParameters, "password", ParamHelper.getPwd(parameter, "password"));
		System.out.println(urlParameters + "++++++++++++++++++++++++");
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters, email+password);
		System.out.println("=========================");
		System.out.println(urlParameters);
		return urlParameters;
	}
	

}
