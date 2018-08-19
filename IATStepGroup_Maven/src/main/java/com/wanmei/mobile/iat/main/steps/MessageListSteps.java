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
import com.wanmei.mobile.iat.common.CommonHelper;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.NGARequestSender;
import com.wanmei.mobile.iat.common.NgaHelper;

public class MessageListSteps {
	
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
	     assertCodeAndCheckToken(response);
	     JSONArray jsonArray=JSONObject.fromObject(response).getJSONArray("result");
	     Assert.assertEquals(jsonArray.size(), resultSet.size()-1,"message list numbers are different!!!");
	     assertMessageList(jsonArray, resultSet);	     
	 }
	
	@StepMethodDesc(description="assert the login user's message_list",owner="yaxiao.yu")
	 public void assertLoginEmptyMessageList(String parameterID)throws Exception
	 {
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		
		 String parameterIDUsedToLogin=(String) StepValueHelper.getStepOutputValue("parameterIDUsedToLogin");
		 String userid=parameter.getValue("uidlogin");
		 String url=parameter.getValue("url");
	     String response=NGARequestSender.sendRequest(userid, url,parameterIDUsedToLogin, parameter.getURLParametersMap(),"0");
	     assertCodeAndCheckToken(response);
	     JSONArray jsonArray=JSONObject.fromObject(response).getJSONArray("result");
	     Assert.assertEquals(jsonArray.size(), 0,"result is not empty!!!");
	 }
	
	@StepMethodDesc(description="assert the Nologin block_list interface with parameter page",owner="yaxiao.yu")
	 public void assertNoLoginMessageListInterfacewithParameterPage(String parameterID)throws Exception
	 {
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 Map<String, String> userParameters=new HashMap<String, String>();
		 userParameters.put("page",parameter.getValue("page"));
		 NGARequestSender.noLoginSendRequest(parameter, userParameters, "1");
	 }
	
	@StepMethodDesc(description="assert the Nologin block_list interface with no parameter",owner="yaxiao.yu")
	 public void assertNoLoginMessageListInterfacewithNoParameter(String parameterID)throws Exception
	 {
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 Map<String, String> userParameters=new HashMap<String, String>();
		 NGARequestSender.noLoginSendRequest(parameter, userParameters, "1");
	 }
	
	@StepMethodDesc(description="assert Code And CheckToken",owner="yaxiao.yu")
	 public void assertCodeAndCheckToken(String response)throws Exception
	 {
		 String code=JSONObject.fromObject(response).getString("code");
	     Assert.assertEquals(code,"0","the returned code is not 0!!!");
	     String checktoken=JSONObject.fromObject(response).getString("checktoken");
	     Assert.assertEquals(checktoken,"1","the returned checktoken is not 1!!!");
	 }
	
	@StepMethodDesc(description="assert the message_list info easy to find from pw_message_data ",owner="yaxiao.yu")
	 public void assertMessageList(JSONArray jsonArray,List<List<String>> resultSet)throws Exception
	 {
		 for(int i=0;i<jsonArray.size();i++)
		 {
			 MessageList mList=new MessageList(jsonArray.getJSONObject(i));
			 int index=CommonHelper.getIndexFromDatabase(mList.did, resultSet, 0);
			 Assert.assertEquals(mList.did, resultSet.get(index).get(0),"dids are different!!");
			 Assert.assertEquals(mList.bit, resultSet.get(index).get(1),"bits are different!!");
			 Assert.assertEquals(mList.subject, NgaHelper.ReEncodeDataFromDB(resultSet, index, 2),"subjects are different!!");
			 Assert.assertEquals(mList.from_uid, resultSet.get(index).get(3),"from_uids are different!!");
			 if(!mList.from_uid.equals("0"))
			 {
				 String un=NgaHelper.ReEncodeDataFromDB(resultSet, index, 4);
				 Assert.assertEquals(mList.from_username, un,"from_usernames are different!!");
			 }		
			 Assert.assertEquals(mList.addTime, resultSet.get(index).get(5),"addTimes are different!!");
			 Assert.assertEquals(mList.posts_num, resultSet.get(index).get(6),"posts_nums are different!!");
			 Assert.assertEquals(mList.last_modify, resultSet.get(index).get(7),"last_modifys are different!!");
			 Assert.assertEquals(mList.last_from, resultSet.get(index).get(8),"last_froms are different!!");
			 if(!mList.last_from.equals("0"))
			 {
				 String un=NgaHelper.ReEncodeDataFromDB(resultSet, index, 9);
				 Assert.assertEquals(mList.last_username, un,"last_usernames are different!!");
			 }	
		 }
	 }
	
	@StepMethodDesc(description="assert the message_list subject info find from pw_message_data ",owner="yaxiao.yu")
	 public void assertMessageListSubject(JSONArray jsonArray,List<List<String>> resultSet)throws Exception
	 {
		 for(int i=0;i<jsonArray.size();i++)
		 {
			 MessageList mList=new MessageList(jsonArray.getJSONObject(i));
			 int index=CommonHelper.getIndexFromDatabase(mList.did, resultSet, 0);
			 String subject=NgaHelper.ReEncodeDataFromDB(resultSet, index, 2);			 
			 Assert.assertEquals(mList.subject, subject,"subjects are different!!");
		 }
	 }
	
	@StepMethodDesc(description="assert the message_list subject info find from pw_message_data ",owner="yaxiao.yu")
	 public void assertMessageListFromUsername(JSONArray jsonArray,List<List<String>> resultSet)throws Exception
	 {
		 for(int i=0;i<jsonArray.size();i++)
		 {
			 MessageList mList=new MessageList(jsonArray.getJSONObject(i));
			 if(mList.from_uid.equals("0"))
			 {
				 ;
			 }
			 else 
			 {
				 int index=CommonHelper.getIndexFromDatabase(mList.did, resultSet, 0);
				 String subject=CommonHelper.ReEncodeDataFromDB(resultSet, index, 2);
				 Assert.assertEquals(mList.from_username, subject,"from_usernames are different!!");
			 }			 
		 }
	 }
	
	@StepMethodDesc(description="assert the message_list subject info find from pw_message_data ",owner="yaxiao.yu")
	 public void assertMessageListLastFromUsername(JSONArray jsonArray,List<List<String>> resultSet)throws Exception
	 {
		 for(int i=0;i<jsonArray.size();i++)
		 {
			 MessageList mList=new MessageList(jsonArray.getJSONObject(i));
			 if(mList.last_from.equals("0"))
			 {
				 ;
			 }
			 else 
			 {
				 int index=CommonHelper.getIndexFromDatabase(mList.did, resultSet, 0);
				 String subject=CommonHelper.ReEncodeDataFromDB(resultSet, index, 2);
				 Assert.assertEquals(mList.last_username, subject,"last_usernames are different!!");
			 }				 
		 }
	 }
	
	public class MessageList
	{
		String did;
		String bit;
		String subject;
		String from_uid;
		String from_username;
		String addTime;
		String posts_num;
		String last_modify;
		String last_from;
		String last_username;
		public MessageList(JSONObject jsonObject)
		{
			did=jsonObject.getString("did");
			bit=jsonObject.getString("bit");
			subject=jsonObject.getString("subject");
			from_uid=jsonObject.getString("from_uid");
			from_username=jsonObject.getString("from_username");
			addTime=jsonObject.getString("addTime");
			posts_num=jsonObject.getString("posts_num");
			last_modify=jsonObject.getString("last_modify");
			last_from=jsonObject.getString("last_from");
			last_username=jsonObject.getString("last_from_username"); 
		}
	}

}
