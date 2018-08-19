package com.wanmei.mobile.iat.post.steps;

import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.gateside.autotesting.Lib.httpclientService.HttpClientHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.NgaHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RandomHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class PostTitletypeSteps {
	@StepMethodDesc(description="postTitletype,nga interface 12",owner="Ruiyun.Ren")
	public void postTitletype(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender1(Constant.Url.PostTitletype,setParam(parameter),"","1");
		NgaHelper.assertCodeAndMsg(actual, parameter);
		Thread.sleep(20000);
		

		
	
	}
	private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
		String sqlCommand="select fid from pw_threads order by rand() limit 1";
		List<List<String>>resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand);
		String fid1=resultSet.get(1).get(0);
		String fid=ParamHelper.setParamByThreeWay(parameter, urlParameters, "fid",fid1);
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters,fid);
		return urlParameters;
		}
	

}
