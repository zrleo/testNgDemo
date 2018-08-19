package com.wanmei.mobile.iat.post.steps;

import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.NgaHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RandomHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

import javax.sound.midi.Soundbank;

public class GetAddressListSteps {
	@StepMethodDesc(description="getAddressList,nga interface 61",owner="Ruiyun.Ren")
	public void getAddressList(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.GetAddressList,setParam(parameter));
		NgaHelper.assertCodeAndMsg(actual, parameter);
		getAddress(actual);
		
	
	}
	public void getAddress(JSONObject actual)
	{
		JSONArray result=actual.getJSONArray("result");
		if(result.size()>0)
		{
			StepValueHelper.putStepOutputValue("latitude", result.getJSONObject(0).getString("latitude"));
			StepValueHelper.putStepOutputValue("longitude", result.getJSONObject(0).getString("longitude"));
			StepValueHelper.putStepOutputValue("name", result.getJSONObject(0).getString("name"));
		}
		
		
	}
	private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
		String latitude=ParamHelper.setParamByThreeWay(parameter, urlParameters, "latitude","");
		String longitude=ParamHelper.setParamByThreeWay(parameter, urlParameters, "longitude","");
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters,longitude+latitude);
		System.out.println("=========================");
		System.out.println(urlParameters);
		return urlParameters;
	}

}
