package com.wanmei.mobile.iat.forum.steps;

import java.util.LinkedHashMap;



import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.NgaHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class HomeHasnewSteps {
	@StepMethodDesc(description="homeHasnew,nga interface 1",owner="Ruiyun.Ren")
	public void homeHasnew(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.HomeHasnew,setParam(parameter));
		NgaHelper.assertCodeAndMsg(actual, parameter);
		

       
			
		}
	private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
		String last_synctime=ParamHelper.setExistParam(parameter, urlParameters, "last_synctime");
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters, last_synctime);
		return urlParameters;
	}


}
