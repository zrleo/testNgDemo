package com.wanmei.mobile.iat.message.steps;

import java.util.LinkedHashMap;

import net.sf.json.JSONObject;



import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.NgaHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RequestHelper;


public class BlackStoreListSteps {
	@StepMethodDesc(description="blackStoreList,nga interface 43",owner="Ruiyun.Ren")
	public void blackStoreList(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.BlackStoreList,setParam(parameter));	
        NgaHelper.assertCodeAndMsgArrayResult(actual, parameter, "gid", 0, "id");
	}

		private LinkedHashMap<String, String> setParam(InterfaceStepParameter parameter) throws Exception
		{
			LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
//			String page =ParamHelper.setParamByTwoWay(parameter, urlParameters, "page", String.valueOf(RandomHelper.getRandomNum(0, 10)));		
			String page=ParamHelper.setExistParam(parameter, urlParameters, "page");
			RequestHelper.setNessesaryParamAndSign(parameter, urlParameters, page);
			return urlParameters;
		}

}
