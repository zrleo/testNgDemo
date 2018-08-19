package com.wanmei.mobile.iat.message.steps;

import java.util.LinkedHashMap;
import java.util.List;



import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.NgaHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RandomHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class MessageSendSteps {
	@StepMethodDesc(description="messageSend,nga interface 20",owner="Ruiyun.Ren")
	public void messageSend(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.MessageSend,setParam(parameter));
		NgaHelper.assertCodeAndMsg(actual, parameter);
		
	
	}
	private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
		String subject=ParamHelper.setParamByTwoWay(parameter, urlParameters, "subject",RandomHelper.getContent(10));
		String content=ParamHelper.setParamByTwoWay(parameter, urlParameters, "content",RandomHelper.getContent(25));
		String sqlCommand="select uid from pw_members order by rand() limit 1";
		List<List<String>>resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand);
		String uid=resultSet.get(1).get(0);
		String to=ParamHelper.setParamByThreeWay(parameter, urlParameters, "to", uid);
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters,subject+content+to);
		return urlParameters;
	}

}
