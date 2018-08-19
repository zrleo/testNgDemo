package com.wanmei.mobile.iat.post.steps;

import java.util.LinkedHashMap;
import java.util.List;

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

public class PostReplySteps {
	@StepMethodDesc(description="postReply,nga interface 14",owner="Ruiyun.Ren")
	public void postReply(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.PostReply,setParam(parameter));
		NgaHelper.assertCodeAndMsg(actual, parameter);
		Thread.sleep(20000);
		
	
	}
	private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
		String sqlCommand="select tid from pw_threads order by rand() limit 1 ";
		List<List<String>>resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand);
		String tid1=resultSet.get(1).get(0);
		String tid=ParamHelper.setParamByThreeWay(parameter, urlParameters, "tid",tid1);
		String pid=ParamHelper.setParamByThreeWay(parameter, urlParameters, "pid","");
		String content=ParamHelper.setParamByThreeWay(parameter, urlParameters, "content",RandomHelper.getContent(25));
		if(StepValueHelper.getStepOutputValue("latitude")==null)
		{
			String address=ParamHelper.setExistParam(parameter, urlParameters, "address");
		}
		else {
			String latitude=StepValueHelper.getStepOutputValue("latitude").toString();
			String longitude=StepValueHelper.getStepOutputValue("longitude").toString();
			String name=StepValueHelper.getStepOutputValue("name").toString();
			String address1=latitude+","+longitude+","+name;
			String address=ParamHelper.setParamByThreeWay(parameter, urlParameters, "address",address1);
		}
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters,tid+pid+content);
		return urlParameters;
		}

}
