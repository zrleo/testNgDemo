package com.wanmei.mobile.iat.post.steps;

import java.util.LinkedHashMap;

import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.NgaHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class GetVideoHistoriesSteps {
	@StepMethodDesc(description="getVideoHistories,nga interface 60",owner="Ruiyun.Ren")
	public void getVideoHistories(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.GetVideoHistories,setParam(parameter));
		NgaHelper.assertCodeAndMsg(actual, parameter);
		
	
	}
	private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters,"");
		return urlParameters;
	}

}
