package com.wanmei.mobile.iat.post.steps;

import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.gateside.autotesting.Gat.util.StepValuePool;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.NgaHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RandomHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class PostCheckSteps {
//	@StepMethodDesc(description="check broadcasts,nga live interface 3",owner="yaxiao.yu")
//	public void postCheck(String parameterID) throws Exception 
//	{
//		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
//		/* String userid=(String) StepValueHelper.getStepOutputValue("uid");
//		 String url=parameter.getValue("url");
//		 HashMap<String, String> userParameterMap=NgaForumRequestHelper.setUserParameterForNgaForum(parameter);
//	     String response=NgaForumRequestHelper.sendRequest(userid, url,userParameterMap,"0");
//	     System.out.println(response);*/
//		 JSONObject object = MultiInterfaceSteps.ngaForumMultiInterface(parameterID);
//		 StepValuePool.createInstance().getValueDic().put("fid", object.getJSONObject("result").getString("fid"));
//	}
	@StepMethodDesc(description="postCheck,nga interface 9",owner="Ruiyun.Ren")
	public void postCheck(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.PostCheck,setParam(parameter));
		NgaHelper.assertCodeAndMsg(actual, parameter);
		Thread.sleep(20000);
		getUrl(actual);
		
	
	}
	public void getUrl(JSONObject actual)
	{
		JSONObject result=actual.getJSONObject("result");
		if(result.size()>0)
		{
			StepValueHelper.putStepOutputValue("attach_url", result.getString("attach_url"));
			StepValueHelper.putStepOutputValue("auth", result.getString("auth"));
		}
		
	}
	private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
		String sqlCommand="select tid,fid from pw_threads order by rand() limit 1";
		List<List<String>>resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand);
		String tid1=resultSet.get(1).get(0);
		String fid1=resultSet.get(1).get(1);
		if(parameter.getValue("action").equals("new"))
		{
			String fid=ParamHelper.setParamByThreeWay(parameter, urlParameters, "fid",fid1);
		}
		else
		{
			String tid=ParamHelper.setParamByThreeWay(parameter, urlParameters, "tid",tid1);
			String pid=ParamHelper.setParamByThreeWay(parameter, urlParameters, "pid","");
		}
		String action=ParamHelper.setParamByThreeWay(parameter, urlParameters, "action", "");
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters,action);
		return urlParameters;
	}
}
