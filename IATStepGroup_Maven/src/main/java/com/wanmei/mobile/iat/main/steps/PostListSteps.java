package com.wanmei.mobile.iat.main.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.wanmei.mobile.iat.common.CommonHelper;
import com.wanmei.mobile.iat.common.NGARequestSender;

public class PostListSteps 
{
	@StepMethodDesc(description="first step login",owner="yaxiao.yu")
	 public void fisrtStepLogin(String parameterIDUsedToLogin)
	 {
		StepValuePool.createInstance().getValueDic().put("parameterIDUsedToLogin",parameterIDUsedToLogin);		
	 }
	
	@StepMethodDesc(description="assert the login user's message_list",owner="yaxiao.yu")
	 public void assertLoginNormalPostList(String parameterID)throws Exception
	 {
		HashMap<String, String> uHashMap=PostList(parameterID, "0", "1");
	     
	 }
	
	 public void assertLoginTidTooEarly(String parameterID)throws Exception
	 {
		 HashMap<String, String> uHashMap=PostList(parameterID, "14", "1");
	 }
	 
	 public void assertLoginIllegalTid(String parameterID)throws Exception
	 {
		 HashMap<String, String> uHashMap=PostList(parameterID, "4", "1");
	 }
	 
	 public void assertLoginIllegalUid(String parameterID)throws Exception
	 {
		 HashMap<String, String> uHashMap=PostList(parameterID, "151", "1");
	 }
	 
	 @StepMethodDesc(description="assert the no Login",owner="yaxiao.yu")
	 public void assertNoLoginPostList(String parameterID) throws Exception
	 {
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 WebConversation wc = new WebConversation();
		 WebRequest req = new PostMethodWebRequest( parameter.getValue("url") );
		 req.setParameter("tid", parameter.getValue("tid"));
		 WebResponse response=wc.getResource(req);		 
	     String code=JSONObject.fromObject(response.getText()).getString("code");
	     Assert.assertEquals(code,"0","the returned code is not 0!!!");
	 
	 }
	
	public HashMap<String, String> PostList(String parameterID,String codeNum,String checktokenNum) throws Exception
	{
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 MysqlDBOperationService service = new MysqlDBOperationService(parameter.ConnectiongString);
		 		 
		 String parameterIDUsedToLogin=(String) StepValueHelper.getStepOutputValue("parameterIDUsedToLogin");
		 String userid=parameter.getValue("uidlogin");
		 String url=parameter.getValue("url");
		 HashMap<String, String> userParameterMap=setUserParameterMapForPostList(parameter);
	     String response=NGARequestSender.sendRequest(userid, url,parameterIDUsedToLogin,userParameterMap,codeNum);
	     System.out.println(response);
	     
	     CommonHelper.assertCodeAndCheckToken(response, codeNum, checktokenNum);
	     if(codeNum.equals("0"))
	     {
	        List<List<String>> result=getResultFromDB(parameter, service);
	        JSONArray jsonArray=JSONObject.fromObject(response).getJSONArray("result");
	        assertSize(result, jsonArray);
	        assertBasicInfo(result, jsonArray);
	     }
	     return userParameterMap;
	}
	
	public List<List<String>> getResultFromDB(InterfaceStepParameter parameter,MysqlDBOperationService service) throws Exception
	{
		List<List<String>> result=new ArrayList<List<String>>();
	     for(int i=0;i<30;i++)
	     {
				 String sqlCommand=parameter.getValue("postListInfoSql").replace("[table_num]", String.valueOf(i));
				 List<List<String>> resultSet = service.executeQuery(sqlCommand,parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
	             if(!resultSet.isEmpty())
	             {
				     result.addAll(resultSet);
	             }
	     }
	     return result;
	}
	
	public void assertSize(List<List<String>> result,JSONArray jsonArray)
	{
		if(jsonArray.size()<20)
	     {
	         Assert.assertEquals(jsonArray.size(), result.size()-1,"result numbers are different!!!");
	     }
	     else 
	     {
	    	 Assert.assertEquals(jsonArray.size(), 20,"result numbers are different!!!"); 
		 }
	}
	
	public HashMap<String, String> setUserParameterMapForPostList(InterfaceStepParameter parameter)
	 {
		 LinkedHashMap<String, String> userParameterMap=new LinkedHashMap<String, String>();
		 		 
		 for(int i=0;i<parameter.parameters.size();i++)
		 {
			 
			 if(parameter.parameters.get(i).key.equals("$#tid"))
			 {
				 userParameterMap.put("#tid", parameter.getValue("$#tid"));
			 }
			 
			 if(parameter.parameters.get(i).key.equals("$page"))
			 {
				 userParameterMap.put("page",parameter.getValue("$page"));
			 }
			 
			 if(parameter.parameters.get(i).key.equals("$uid"))
			 {
			     userParameterMap.put("uid", parameter.getValue("$uid"));
			 }
		 }
		 
		 return userParameterMap;
	 }

	public void assertBasicInfo(List<List<String>> result,JSONArray jsonArray) throws Exception
	{
		for(int i=0;i<jsonArray.size();i++)
		{
			StepValuePool.createInstance().getValueDic().put("DbResult",result);
			PostInfo pInfo=new PostInfo(jsonArray.getJSONObject(i));
			//System.out.println(pInfo.postdate);
			int index=-1;
			if(!pInfo.pid.equals("0"))
			{
			     index=CommonHelper.getIndexFromDatabase(pInfo.pid, result, 0);
			     Assert.assertEquals(pInfo.pid, result.get(index).get(0),"ids are different!!!");
			     Assert.assertEquals(pInfo.fid, result.get(index).get(1),"fids are different!!!");
				 Assert.assertEquals(pInfo.tid, result.get(index).get(2),"tids are different!!!");
					//Assert.assertEquals(pInfo.postdate, CommonHelper.convertDetailFromServer(String.valueOf(Integer.valueOf(result.get(index).get(3))*1000)),"postdates are different!!!");
					//Assert.assertEquals(pInfo.content, CommonHelper.ReEncodeDataFromDB(result, index, 4),"contents are different!!!");
					
			}
//			else 
//			{
//				 index=CommonHelper.getIndexByTimeFromDatabase(pInfo.postdate, result, 3);
//			}
			
		}
	}
	
	public class PostInfo
	{
		String pid;
		String fid;
		String tid;
		String postdate;
		String subject;
		String content;
		public PostInfo(JSONObject jsonObject)
		{
			pid=jsonObject.getString("pid");
			fid=jsonObject.getString("fid");
			tid=jsonObject.getString("tid");
			postdate=jsonObject.getString("postdate");
			subject=jsonObject.getString("subject").replace("\\", "");
		    content=jsonObject.getString("content").replace("\\", "").replace("<br/>", "\n");
		}
	}
}
