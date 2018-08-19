package com.wanmei.mobile.iat.post.steps;

import java.util.LinkedHashMap;


import net.sf.json.JSONObject;


import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;

import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RequestHelper;


public class GiftListSteps {
	@StepMethodDesc(description="giftList,nga interface 50",owner="Ruiyun.Ren")
	public void giftList(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.GiftList,setParam(parameter));	
			
		}

		private LinkedHashMap<String, String> setParam(InterfaceStepParameter parameter) throws Exception
		{
			LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
			String page=ParamHelper.setParamExistOrValuePool(parameter, urlParameters, "page");
			RequestHelper.setNessesaryParamAndSign(parameter, urlParameters, page);
			return urlParameters;
		}

}
