package com.wanmei.mobile.iat.message.steps;





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

public class MessageReplySteps {
	@StepMethodDesc(description="messageReply,nga interface 21",owner="Ruiyun.Ren")
	public void messageReply(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.MessageReply,setParam(parameter));
		NgaHelper.assertCodeAndMsg(actual, parameter);
		
	
	}
	private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
		String subject=ParamHelper.setParamByTwoWay(parameter, urlParameters, "subject",RandomHelper.getContent(10));
		String content=ParamHelper.setParamByTwoWay(parameter, urlParameters, "content",RandomHelper.getContent(25));
		String did=ParamHelper.setParamExistOrValuePool(parameter, urlParameters, "did");
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters,subject+content+did);
		return urlParameters;
	}
	
//	@StepMethodDesc(description="first step login",owner="yaxiao.yu")
//	 public void fisrtStepLogin(String parameterIDUsedToLogin)
//	 {
//		StepValuePool.createInstance().getValueDic().put("parameterIDUsedToLogin",parameterIDUsedToLogin);		
//	 }
//	
//	@StepMethodDesc(description="assert the login user's message_list",owner="yaxiao.yu")
//	 public void assertLoginNotEmptyMessageReply(String parameterID)throws Exception
//	 {
//		HashMap<String, String> uHashMap=MessageReply(parameterID, "0", "1");
//	     if(uHashMap.get("index").equals("-1"))
//	     {
//	    	 Assert.assertEquals(uHashMap.get("subject"), "databaseSubject","reply failed!!!");
//	     }
//	 }
//	
//	@StepMethodDesc(description="assert the login user's message_list",owner="yaxiao.yu")
//	 public void assertLoginMessageReplyOthersMid(String parameterID)throws Exception
//	 {
//		HashMap<String, String> uHashMap=MessageReply(parameterID, "209", "1");
//	     if(!uHashMap.get("index").equals("-1"))
//	     {
//	    	 Assert.assertEquals(uHashMap.get("subject"), "databaseSubject","reply sucesss,but we are not permitted to reply other's mid!!!");
//	     }
//	 }
//	
//	@StepMethodDesc(description="assert the login user's message_list",owner="yaxiao.yu")
//	 public void assertLoginMessageReplyBoardMid(String parameterID)throws Exception
//	 {
//		HashMap<String, String> uHashMap=MessageReply(parameterID, "208", "1");
//	     if(!uHashMap.get("index").equals("-1"))
//	     {
//	    	 Assert.assertEquals(uHashMap.get("subject"), "databaseSubject","reply sucesss,but we are not permitted to reply other's mid!!!");
//	     }
//	 }
//	
//	@StepMethodDesc(description="assert the login user's message_list",owner="yaxiao.yu")
//	 public void assertLoginMessageReplyTitleContentOutBound(String parameterID)throws Exception
//	 {
//		HashMap<String, String> uHashMap=MessageReply(parameterID, "10", "1");
//	     if(!uHashMap.get("index").equals("-1"))
//	     {
//	    	 Assert.assertEquals(uHashMap.get("subject"), "databaseSubject","reply sucesss,but we are not permitted to reply other's mid!!!");
//	     }
//	 }
//	
//	@StepMethodDesc(description="assert the login user's message_list",owner="yaxiao.yu")
//	 public void assertLoginMessageReplyLackParameters(String parameterID)throws Exception
//	 {
//		HashMap<String, String> uHashMap=MessageReply(parameterID, "4", "1");
//	     if(!uHashMap.get("index").equals("-1"))
//	     {
//	    	 Assert.assertEquals(uHashMap.get("subject"), "databaseSubject","reply sucesss,but we are not permitted to reply other's mid!!!");
//	     }
//	 }
//	
//	@StepMethodDesc(description="assert the login user's message_list",owner="yaxiao.yu")
//	 public void assertNoLoginMessageReply(String parameterID)throws Exception
//	 {
//		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
//		HashMap<String, String> userParameters=new HashMap<String, String>();
//		userParameters.put("subject", parameter.getValue("subject"));
//		userParameters.put("content", parameter.getValue("content"));
//		userParameters.put("did", parameter.getValue("did"));
//		NGARequestSender.noLoginSendRequest(parameter, userParameters, "12");
//	 }
//	
//	@StepMethodDesc(description="assert the login user's message_list",owner="yaxiao.yu")
//	 public void assertLoginMessageReplySameSubjectAndSameContent(String parameterID)throws Exception
//	 {
////    	 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
////		 MysqlDBOperationService service = new MysqlDBOperationService(parameter.ConnectiongString);
////		 List<List<String>> resultSet = service.executeQuery(parameter.getValue("userMessageDetailBasicInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
////		 //List<String> indexListBefore=CommonHelper.getIndexListFromDatabase(parameter.getValue("subject"), resultSet, 1);
////
////		 String parameterIDUsedToLogin=(String) StepValueHelper.getStepOutputValue("parameterIDUsedToLogin");
////		 String userid=parameter.getValue("uidlogin");
////		 String url=parameter.getValue("url");
//////		 LinkedHashMap<String, String> userParameterMap=new LinkedHashMap<String, String>();
//////		 userParameterMap.put("subject", parameter.getValue("$#subject"));
//////		 userParameterMap.put("content", parameter.getValue("$#content"));
//////		 userParameterMap.put("did", parameter.getValue("$#did"));
////
////		 NGARequestSender.sendRequest(userid, url, parameterIDUsedToLogin, parameter.getURLParametersMap(), "0");
////		 NGARequestSender.sendRequest(userid, url, parameterIDUsedToLogin, parameter.getURLParametersMap(), "0");
////		 List<String> indexListAfter=CommonHelper.getIndexListFromDatabase(parameter.getValue("subject"), resultSet, 1);
////		 //if(!((indexListAfter.size()-indexListBefore.size())==2))
////		 if(!((indexListAfter.size())==2))
////		 {
////			 Assert.assertEquals(parameter.getValue("subject"), "databaseSubject","reply same message failed!!!"); 
////		 }
//	 }
//	
//	public HashMap<String, String> MessageReply(String parameterID,String codeNum,String checktokenNum) throws Exception
//	{
//		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
//		 MysqlDBOperationService service = new MysqlDBOperationService(parameter.ConnectiongString);
//		 		 
//		 String parameterIDUsedToLogin=(String) StepValueHelper.getStepOutputValue("parameterIDUsedToLogin");
//		 String userid=parameter.getValue("uidlogin");
//		 String url=parameter.getValue("url");
//		 HashMap<String, String> userParameterMap=CommonHelper.setUserParameterMapForMessageReply(parameter);
//	     String response=NGARequestSender.sendRequest(userid, url,parameterIDUsedToLogin,userParameterMap,codeNum);
//	     System.out.println(response);
//	     
//	     CommonHelper.assertCodeAndCheckToken(response, codeNum, checktokenNum);	    
//	     Thread.sleep(10000);
//	     List<List<String>> resultSet = service.executeQuery(parameter.getValue("userMessageDetailBasicInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
//	     //int index=CommonHelper.findIndexOfNewReply(resultSet, userParameterMap,1);
//	     int index=CommonHelper.getIndexFromDatabase(userParameterMap.get("#subject"), resultSet, 1);
//	     userParameterMap.put("index", String.valueOf(index));
//	     return userParameterMap;
//	}
//	
//	 

}
