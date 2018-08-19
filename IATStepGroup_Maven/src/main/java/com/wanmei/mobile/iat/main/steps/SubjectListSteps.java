package com.wanmei.mobile.iat.main.steps;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.testng.Assert;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.gateside.autotesting.Gat.util.StepValuePool;
import com.gateside.autotesting.Lib.dbService.MysqlDBOperationService;
import com.wanmei.mobile.iat.common.CommonHelper;
import com.wanmei.mobile.iat.common.NGARequestSender;

public class SubjectListSteps 
{
	
	@StepMethodDesc(description="first step login",owner="yaxiao.yu")
	 public void fisrtStepLogin(String parameterIDUsedToLogin)
	 {
		StepValuePool.createInstance().getValueDic().put("parameterIDUsedToLogin",parameterIDUsedToLogin);		
	 }
	
	@StepMethodDesc(description="assert the login user's message_list",owner="yaxiao.yu")
	 public void assertLoginNotEmptyMessageList(String parameterID)throws Exception
	 {
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 MysqlDBOperationService service = new MysqlDBOperationService(parameter.ConnectiongString);
		 List<List<String>> resultSet = service.executeQuery(parameter.getValue("userMessageListBasicInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
		 String parameterIDUsedToLogin=(String) StepValueHelper.getStepOutputValue("parameterIDUsedToLogin");
		 String userid=parameter.getValue("uidlogin");
		 String url=parameter.getValue("url");
	     String response=NGARequestSender.sendRequest(userid, url,parameterIDUsedToLogin, parameter.getURLParametersMap(),"0");
	     CommonHelper.assertCodeAndCheckToken(response,"0","1");
	     JSONArray jsonArray=JSONObject.fromObject(response).getJSONArray("result");
	     Assert.assertEquals(jsonArray.size(), resultSet.size()-1,"message list numbers are different!!!");
	     assertSubjectList(jsonArray, resultSet);	     
	 }
	 
	public void assertSubjectList(JSONArray jsonArray,List<List<String>> resultSet)
	{
		

	}
	
	public class SubjectList
	{
		String id;
		String name;
		String info;
		public SubjectList(JSONObject jsonObject)
		{
			id=jsonObject.getString("id");
			name=jsonObject.getString("name");
			info=jsonObject.getString("info");
		}
	}

}
