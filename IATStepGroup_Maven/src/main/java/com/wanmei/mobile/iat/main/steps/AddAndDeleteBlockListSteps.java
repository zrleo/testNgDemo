package com.wanmei.mobile.iat.main.steps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.testng.Assert;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.gateside.autotesting.Gat.util.StepValuePool;
import com.gateside.autotesting.Lib.dbService.MysqlDBOperationService;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.wanmei.mobile.iat.common.CommonHelper;
import com.wanmei.mobile.iat.common.NGARequestSender;
import com.wanmei.mobile.iat.common.NgaHelper;

public class AddAndDeleteBlockListSteps 
{
	@StepMethodDesc(description="first step login",owner="yaxiao.yu")
	 public void fisrtStepLogin(String parameterIDUsedToLogin)
	 {
		StepValuePool.createInstance().getValueDic().put("parameterIDUsedToLogin",parameterIDUsedToLogin);		
	 }
	
	@StepMethodDesc(description="assert the login user's block_list",owner="yaxiao.yu")
	 public void assertAddBlockList(String parameterID)throws Exception
	 {
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 MysqlDBOperationService service = new MysqlDBOperationService(parameter.ConnectiongString);
		 List<List<String>> resultSet = service.executeQuery(parameter.getValue("userAddBlockListInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
         int blockListSize=resultSet.size()-1;
		 String parameterIDUsedToLogin=(String) StepValueHelper.getStepOutputValue("parameterIDUsedToLogin");
		 String userid=parameter.getValue("uidlogin");
		 String url=parameter.getValue("url");
	     String response=NGARequestSender.sendRequest(userid, url,parameterIDUsedToLogin, parameter.getURLParametersMap(),"0");
	     String code=JSONObject.fromObject(response).getString("code");
	     Assert.assertEquals(code,"0","the returned code is not 0!!!");
	     String checktoken=JSONObject.fromObject(response).getString("checktoken");
	     Assert.assertEquals(checktoken,"1","the returned checktoken is not 1!!!");
	     List<List<String>> resultSet2 = service.executeQuery(parameter.getValue("userAddBlockListInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
	     Assert.assertEquals(blockListSize+1,resultSet2.size()-1,"add block list failed!!");
	     
//	     String uid=parameter.getValue("$#uid");
//	     for(int i=0;i<resultSet2.size()-1;i++)
//	     {
//	    	 
//	     }
	 }
	
	@StepMethodDesc(description="assert the login user's block_list",owner="yaxiao.yu")
	 public void assertAddOneslefToBlockListAgain(String parameterID)throws Exception
	 {
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 String parameterIDUsedToLogin=(String) StepValueHelper.getStepOutputValue("parameterIDUsedToLogin");
		 String userid=parameter.getValue("uidlogin");
		 String url=parameter.getValue("url");
	     String response=NGARequestSender.sendRequest(userid, url,parameterIDUsedToLogin, parameter.getURLParametersMap(),"10");
	     String code=JSONObject.fromObject(response).getString("code");
	     Assert.assertEquals(code,"10","the returned code is not 10!!!");
	 }
	
	 @StepMethodDesc(description="assert the login user's block_list",owner="yaxiao.yu")
	 public void assertDeleteBlockListInfo(String parameterID) throws Exception
	 {
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 MysqlDBOperationService service = new MysqlDBOperationService(parameter.ConnectiongString);
		 List<List<String>> resultSet = service.executeQuery(parameter.getValue("userDelBlockListInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
		 int blockListSize=resultSet.size()-1;
		 String parameterIDUsedToLogin=(String) StepValueHelper.getStepOutputValue("parameterIDUsedToLogin");
		 String userid=parameter.getValue("uidlogin");
		 String url=parameter.getValue("url");
	     String response=NGARequestSender.sendRequest(userid, url,parameterIDUsedToLogin, parameter.getURLParametersMap(),"0");
	     String code=JSONObject.fromObject(response).getString("code");
	     List<List<String>> resultSet2 = service.executeQuery(parameter.getValue("userDelBlockListInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
	     Assert.assertEquals(resultSet2.size()-1, resultSet.size()-2,"block_list numbers are different!!!");
	     Assert.assertEquals(code,"0","the returned code is not 0!!!");
	     String checktoken=JSONObject.fromObject(response).getString("checktoken");
	     Assert.assertEquals(checktoken,"1","the returned checktoken is not 1!!!");
	     JSONArray jsonArray=JSONObject.fromObject(response).getJSONArray("result");
	     	     
	 }
	 
	 @StepMethodDesc(description="assert the login user's block_list",owner="yaxiao.yu")
	 public void assertDeleteBlockListInfoAgain(String parameterID) throws Exception
	 {
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 MysqlDBOperationService service = new MysqlDBOperationService(parameter.ConnectiongString);
		 List<List<String>> resultSet = service.executeQuery(parameter.getValue("userDelBlockListInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
	
		 String parameterIDUsedToLogin=(String) StepValueHelper.getStepOutputValue("parameterIDUsedToLogin");
		 String userid=parameter.getValue("uidlogin");
		 String url=parameter.getValue("url");
	     String response=NGARequestSender.sendRequest(userid, url,parameterIDUsedToLogin, parameter.getURLParametersMap(),"0");
	     String code=JSONObject.fromObject(response).getString("code");
	     List<List<String>> resultSet2 = service.executeQuery(parameter.getValue("userDelBlockListInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
	     Assert.assertEquals(resultSet2.size()-1, resultSet.size()-1,"block_list numbers are different!!!");

	     //Assert.assertEquals(code,"10","the returned code is not 10!!!");
	     
	 }
	 
	 @StepMethodDesc(description="assert the Nologin block_list interface",owner="yaxiao.yu")
	 public void assertNoLoginInfo(String parameterID)throws Exception
	 {
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 Map<String, String> userParameters=new HashMap<String, String>();
		 userParameters.put("uid",parameter.getValue("uid"));
		 NGARequestSender.noLoginSendRequest(parameter, userParameters, "12");
	 }
	 
	 @StepMethodDesc(description="assert the login user's block_list",owner="yaxiao.yu")
	 public void assertBlockListInfo(String parameterID) throws Exception
	 {
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 MysqlDBOperationService service = new MysqlDBOperationService(parameter.ConnectiongString);
		 List<List<String>> resultSet = service.executeQuery(parameter.getValue("userBlockListInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
	     
		 String parameterIDUsedToLogin=(String) StepValueHelper.getStepOutputValue("parameterIDUsedToLogin");
		 String userid=parameter.getValue("uidlogin");
		 String url=parameter.getValue("url");
	     String response=NGARequestSender.sendRequest(userid, url,parameterIDUsedToLogin, parameter.getURLParametersMap(),"0");
	     String code=JSONObject.fromObject(response).getString("code");
	     Assert.assertEquals(code,"0","the returned code is not 0!!!");
	     String checktoken=JSONObject.fromObject(response).getString("checktoken");
	     Assert.assertEquals(checktoken,"1","the returned checktoken is not 1!!!");
	     JSONArray jsonArray=JSONObject.fromObject(response).getJSONArray("result");
	     Assert.assertEquals(jsonArray.size(), resultSet.size()-1,"block_list numbers are different!!!");
	     for(int i=0;i<jsonArray.size();i++)
	     {
	    	 SingleBlockMember singleBlockMember=new SingleBlockMember(jsonArray.getJSONObject(i));
	         assertSingleBlockMember(singleBlockMember,i+1,resultSet);
	     }
	 }
	 
	 public void assertSingleBlockMember(SingleBlockMember singleBlockMember,int index, List<List<String>> resultSet)throws Exception
	 {
		 Assert.assertEquals(singleBlockMember.uid,resultSet.get(index).get(0),"uids are different!!!");
		 Assert.assertEquals(singleBlockMember.username,NgaHelper.ReEncodeDataFromDB(resultSet, index, 1),"usernames are different!!!");
	 }
	 
	 public class SingleBlockMember
	 {
		 String uid;
		 String username;
		 public SingleBlockMember(JSONObject jsonObject) 
		 {
			uid=jsonObject.getString("uid");
			username=jsonObject.getString("username");
		}
	 }

	 public void assertLoginAddIllegalToBlockList(String parameterID) throws Exception
	 {
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 String parameterIDUsedToLogin=(String) StepValueHelper.getStepOutputValue("parameterIDUsedToLogin");
		 String userid=parameter.getValue("uidlogin");
		 String url=parameter.getValue("url");
	     String response=NGARequestSender.sendRequest(userid, url,parameterIDUsedToLogin, parameter.getURLParametersMap(),"211");
	     String code=JSONObject.fromObject(response).getString("code");
	     Assert.assertEquals(code,"211","the returned code is not 211!!!");
	 }
	 public void assertLoginDelIllegalFromBlockList(String parameterID) throws Exception
	 {
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 String parameterIDUsedToLogin=(String) StepValueHelper.getStepOutputValue("parameterIDUsedToLogin");
		 String userid=parameter.getValue("uidlogin");
		 String url=parameter.getValue("url");
	     String response=NGARequestSender.sendRequest(userid, url,parameterIDUsedToLogin, parameter.getURLParametersMap(),"4");
	     String code=JSONObject.fromObject(response).getString("code");
	     Assert.assertEquals(code,"4","the returned code is not 4!!!");
	 }
}
