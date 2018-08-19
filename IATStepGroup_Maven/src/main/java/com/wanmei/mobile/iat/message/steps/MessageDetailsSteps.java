package com.wanmei.mobile.iat.message.steps;

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
import com.wanmei.mobile.iat.common.CommonHelper;
import com.wanmei.mobile.iat.common.NGARequestSender;


public class MessageDetailsSteps {
//	@StepMethodDesc(description="first step login",owner="yaxiao.yu")
//	 public void fisrtStepLogin(String parameterIDUsedToLogin)
//	 {
//		StepValuePool.createInstance().getValueDic().put("parameterIDUsedToLogin",parameterIDUsedToLogin);		
//	 }
//	
//	@StepMethodDesc(description="assert the login user's message_list",owner="yaxiao.yu")
//	 public void assertLoginNotEmptyMessageDetail(String parameterID)throws Exception
//	 {
//		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
//		 MysqlDBOperationService service = new MysqlDBOperationService(parameter.ConnectiongString);
//		 List<List<String>> resultSet = service.executeQuery(parameter.getValue("userMessageListBasicInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
//		 
//		 String parameterIDUsedToLogin=(String) StepValueHelper.getStepOutputValue("parameterIDUsedToLogin");
//		 String userid=parameter.getValue("uidlogin");
//		 String url=parameter.getValue("url");
//	     String response=NGARequestSender.sendRequest(userid, url,parameterIDUsedToLogin, parameter.getURLParametersMap(),"0");
//	     CommonHelper.assertCodeAndCheckToken(response, "0", "1");
//	     JSONArray jsonArray=JSONObject.fromObject(response).getJSONArray("result");
//	     if(jsonArray.size()<20)
//	     {
//	         Assert.assertEquals(jsonArray.size(), resultSet.size()-1,"message list numbers are different!!!");
//	     }
//	     else 
//	     {
//	    	 Assert.assertEquals(jsonArray.size(), 20,"message list numbers are different!!!");
//		 }
//	     assertMessageDetail(jsonArray, resultSet);	     
//	 }
//	
//	@StepMethodDesc(description="assert the login user's message_list",owner="yaxiao.yu")
//	 public void assertLoginMessageDetailWithNoDid(String parameterID)throws Exception
//	 {
//		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
//		
//		 String parameterIDUsedToLogin=(String) StepValueHelper.getStepOutputValue("parameterIDUsedToLogin");
//		 String userid=parameter.getValue("uidlogin");
//		 String url=parameter.getValue("url");
//	     String response=NGARequestSender.sendRequest(userid, url,parameterIDUsedToLogin, parameter.getURLParametersMap(),"4");
//	     String code=JSONObject.fromObject(response).getString("code");
//	     Assert.assertEquals(code,"4","the returned code is not 4!!!");
//	 }
//	
//	@StepMethodDesc(description="assert the login user's empty message_list",owner="yaxiao.yu")
//	 public void assertLoginEmptyMessageDetailWithNoExistDid(String parameterID)throws Exception
//	 {
//		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
//		
//		 String parameterIDUsedToLogin=(String) StepValueHelper.getStepOutputValue("parameterIDUsedToLogin");
//		 String userid=parameter.getValue("uidlogin");
//		 String url=parameter.getValue("url");
//	     String response=NGARequestSender.sendRequest(userid, url,parameterIDUsedToLogin, parameter.getURLParametersMap(),"23");
//	     String code=JSONObject.fromObject(response).getString("code");
//	     Assert.assertEquals(code,"23","the returned code is not 23!!!");
//	 }
//	
//	@StepMethodDesc(description="assert the login user's empty message_list",owner="yaxiao.yu")
//	 public void assertLoginMessageDetailWithNoExistPage(String parameterID)throws Exception
//	 {
//		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
//		 MysqlDBOperationService service = new MysqlDBOperationService(parameter.ConnectiongString);
//		 List<List<String>> resultSet = service.executeQuery(parameter.getValue("userMessageListBasicInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
//		 
//		 String parameterIDUsedToLogin=(String) StepValueHelper.getStepOutputValue("parameterIDUsedToLogin");
//		 String userid=parameter.getValue("uidlogin");
//		 String url=parameter.getValue("url");
//	     String response=NGARequestSender.sendRequest(userid, url,parameterIDUsedToLogin, parameter.getURLParametersMap(),"151");
//	     System.out.println(response);
//	     String code=JSONObject.fromObject(response).getString("code");
//	     Assert.assertEquals(code,"151","the returned code is not 151!!!");
//	 }
//
//	@StepMethodDesc(description="assert the login user's empty message_list",owner="yaxiao.yu")
//	 public void assertLoginMessageDetailWithLargeDid(String parameterID)throws Exception
//	 {
//		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
//		 MysqlDBOperationService service = new MysqlDBOperationService(parameter.ConnectiongString);
//		 List<List<String>> resultSet = service.executeQuery(parameter.getValue("userMessageListBasicInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
//		 
//		 String parameterIDUsedToLogin=(String) StepValueHelper.getStepOutputValue("parameterIDUsedToLogin");
//		 String userid=parameter.getValue("uidlogin");
//		 String url=parameter.getValue("url");
//	     String response=NGARequestSender.sendRequest(userid, url,parameterIDUsedToLogin, parameter.getURLParametersMap(),"23");
//	     System.out.println(response);
//	     String code=JSONObject.fromObject(response).getString("code");
//	     Assert.assertEquals(code,"23","the returned code is not 23!!!");
//	     Assert.assertEquals(JSONObject.fromObject(response).getString("msg"),"���Ӳ�����","the returned msg is not: post is not exist!!!");
//	 }
//	
//	@StepMethodDesc(description="assert the Nologin message_detail interface with parameter did and page",owner="yaxiao.yu")
//	 public void assertNoLoginMessageListInterfacewithParameter(String parameterID)throws Exception
//	 {
//		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
//		 Map<String, String> userParameters=new HashMap<String, String>();
//		 userParameters.put("did", parameter.getValue("did"));
//		 userParameters.put("page",parameter.getValue("page"));
//		 NGARequestSender.noLoginSendRequest(parameter, userParameters, "12");
//	 }
//	
//	@StepMethodDesc(description="assert the Nologin message_detail interface with parameter did ",owner="yaxiao.yu")
//	 public void assertNoLoginMessageListInterfacewithParameterDid(String parameterID)throws Exception
//	 {
//		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
//		 Map<String, String> userParameters=new HashMap<String, String>();
//		 userParameters.put("did", parameter.getValue("did"));
//		 NGARequestSender.noLoginSendRequest(parameter, userParameters, "12");
//	 }
//	
//	@StepMethodDesc(description="assert the Nologin message_detail interface with no parameter",owner="yaxiao.yu")
//	 public void assertNoLoginMessageListInterfacewithNoParameter(String parameterID)throws Exception
//	 {
//		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
//		 Map<String, String> userParameters=new HashMap<String, String>();
//		 NGARequestSender.noLoginSendRequest(parameter, userParameters, "12");
//	 }
//
//	
//	@StepMethodDesc(description="assert the message_list info easy to find from pw_message_data ",owner="yaxiao.yu")
//	 public void assertMessageDetail(JSONArray jsonArray,List<List<String>> resultSet)throws Exception
//	 {
//		 for(int i=0;i<jsonArray.size();i++)
//		 {
//			 MessageDetail md=new MessageDetail(jsonArray.getJSONObject(i));
//			 if(!md.id.equals(0))
//			 {
//			     int index=CommonHelper.getIndexFromDatabase(md.id, resultSet, 0);
//			     Assert.assertEquals(md.id, resultSet.get(index).get(0),"ids are different!!");
//			     Assert.assertEquals(md.subject, CommonHelper.ReEncodeDataFromDB(resultSet, index, 1),"subjects are different!!");
//			     Assert.assertEquals(md.content, CommonHelper.ReEncodeDataFromDB(resultSet, index, 2),"contents are different!!");
//			     Assert.assertEquals(md.addTime, resultSet.get(index).get(3),"addTimes are different!!");
//			     MessageUser mu=new MessageUser(jsonArray.getJSONObject(i).getJSONObject("user"));
//			     Assert.assertEquals(mu.uid, resultSet.get(index).get(4),"uids are different!!");
//			     if(mu.uid.equals(0))
//			     {
//			    	 Assert.assertEquals(mu.username, "#SYSTEM#","username is not system!!!");
//			     }
//			     else 
//			     {
//			    	 Assert.assertEquals(mu.username, CommonHelper.ReEncodeDataFromDB(resultSet, index, 5),"usernames are different!!");
//				 }
//			 }
//		 }
//	 }
//		 	
//	public class MessageDetail
//	{
//		String id;
//		String subject;
//		String content;
//		String addTime;
//		public MessageDetail(JSONObject jsonObject)
//		{
//			id=jsonObject.getString("id");
//			subject=jsonObject.getString("subject");
//			addTime=jsonObject.getString("addTime");
//			content=jsonObject.getString("content"); 
//		}
//	}
//	
//	public class MessageUser
//	{
//		String uid;
//		String username;
//		public MessageUser(JSONObject jsonObject)
//		{
//			uid=jsonObject.getString("uid");
//			username=jsonObject.getString("username"); 
//		}
//	}


}
