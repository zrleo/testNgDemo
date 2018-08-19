package com.wanmei.mobile.iat.post.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.testng.Assert;

import android.R.integer;
import android.database.CursorJoiner.Result;

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
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.DateHelper;
import com.wanmei.mobile.iat.common.NgaHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RandomHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class PostListSteps 
{
//	@StepMethodDesc(description="first step login",owner="yaxiao.yu")
//	 public void fisrtStepLogin(String parameterIDUsedToLogin)
//	 {
//		StepValuePool.createInstance().getValueDic().put("parameterIDUsedToLogin",parameterIDUsedToLogin);		
//	 }
//	
//	@StepMethodDesc(description="assert the login user's message_list",owner="yaxiao.yu")
//	 public void assertLoginNormalPostList(String parameterID)throws Exception
//	 {
//		HashMap<String, String> uHashMap=PostList(parameterID, "0", "1");
//	     
//	 }
//	
//	 public void assertLoginTidTooEarly(String parameterID)throws Exception
//	 {
//		 HashMap<String, String> uHashMap=PostList(parameterID, "14", "1");
//	 }
//	 
//	 public void assertLoginIllegalTid(String parameterID)throws Exception
//	 {
//		 HashMap<String, String> uHashMap=PostList(parameterID, "4", "1");
//	 }
//	 
//	 public void assertLoginIllegalUid(String parameterID)throws Exception
//	 {
//		 HashMap<String, String> uHashMap=PostList(parameterID, "151", "1");
//	 }
//	 
//	 @StepMethodDesc(description="assert the no Login",owner="yaxiao.yu")
//	 public void assertNoLoginPostList(String parameterID) throws Exception
//	 {
//		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
//		 WebConversation wc = new WebConversation();
//		 WebRequest req = new PostMethodWebRequest( parameter.getValue("url") );
//		 req.setParameter("tid", parameter.getValue("tid"));
//		 WebResponse response=wc.getResource(req);		 
//	     String code=JSONObject.fromObject(response.getText()).getString("code");
//	     Assert.assertEquals(code,"0","the returned code is not 0!!!");
//	 
//	 }
//	
//	public HashMap<String, String> PostList(String parameterID,String codeNum,String checktokenNum) throws Exception
//	{
//		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
//		 MysqlDBOperationService service = new MysqlDBOperationService(parameter.ConnectiongString);
//		 		 
//		 String parameterIDUsedToLogin=(String) StepValueHelper.getStepOutputValue("parameterIDUsedToLogin");
//		 String userid=parameter.getValue("uidlogin");
//		 String url=parameter.getValue("url");
//		 HashMap<String, String> userParameterMap=setUserParameterMapForPostList(parameter);
//	     String response=NGARequestSender.sendRequest(userid, url,parameterIDUsedToLogin,userParameterMap,codeNum);
//	     System.out.println(response);
//	     
//	     CommonHelper.assertCodeAndCheckToken(response, codeNum, checktokenNum);
//	     if(codeNum.equals("0"))
//	     {
//	        List<List<String>> result=getResultFromDB(parameter, service);
//	        JSONArray jsonArray=JSONObject.fromObject(response).getJSONArray("result");
//	        assertSize(result, jsonArray);
//	        assertBasicInfo(result, jsonArray);
//	     }
//	     return userParameterMap;
//	}
//	
//	public List<List<String>> getResultFromDB(InterfaceStepParameter parameter,MysqlDBOperationService service) throws Exception
//	{
//		List<List<String>> result=new ArrayList<List<String>>();
//	     for(int i=0;i<30;i++)
//	     {
//				 String sqlCommand=parameter.getValue("postListInfoSql").replace("[table_num]", String.valueOf(i));
//				 List<List<String>> resultSet = service.executeQuery(sqlCommand,parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
//	             if(!resultSet.isEmpty())
//	             {
//				     result.addAll(resultSet);
//	             }
//	     }
//	     return result;
//	}
//	
//	public void assertSize(List<List<String>> result,JSONArray jsonArray)
//	{
//		if(jsonArray.size()<20)
//	     {
//	         Assert.assertEquals(jsonArray.size(), result.size()-1,"result numbers are different!!!");
//	     }
//	     else 
//	     {
//	    	 Assert.assertEquals(jsonArray.size(), 20,"result numbers are different!!!"); 
//		 }
//	}
//	
//	public HashMap<String, String> setUserParameterMapForPostList(InterfaceStepParameter parameter)
//	 {
//		 LinkedHashMap<String, String> userParameterMap=new LinkedHashMap<String, String>();
//		 		 
//		 for(int i=0;i<parameter.parameters.size();i++)
//		 {
//			 
//			 if(parameter.parameters.get(i).key.equals("$#tid"))
//			 {
//				 userParameterMap.put("#tid", parameter.getValue("$#tid"));
//			 }
//			 
//			 if(parameter.parameters.get(i).key.equals("$page"))
//			 {
//				 userParameterMap.put("page",parameter.getValue("$page"));
//			 }
//			 
//			 if(parameter.parameters.get(i).key.equals("$uid"))
//			 {
//			     userParameterMap.put("uid", parameter.getValue("$uid"));
//			 }
//		 }
//		 
//		 return userParameterMap;
//	 }
//
//	public void assertBasicInfo(List<List<String>> result,JSONArray jsonArray) throws Exception
//	{
//		for(int i=0;i<jsonArray.size();i++)
//		{
//			StepValuePool.createInstance().getValueDic().put("DbResult",result);
//			PostInfo pInfo=new PostInfo(jsonArray.getJSONObject(i));
//			//System.out.println(pInfo.postdate);
//			int index=-1;
//			if(!pInfo.pid.equals("0"))
//			{
//			     index=CommonHelper.getIndexFromDatabase(pInfo.pid, result, 0);
//			     Assert.assertEquals(pInfo.pid, result.get(index).get(0),"ids are different!!!");
//			     Assert.assertEquals(pInfo.fid, result.get(index).get(1),"fids are different!!!");
//				 Assert.assertEquals(pInfo.tid, result.get(index).get(2),"tids are different!!!");
//					//Assert.assertEquals(pInfo.postdate, CommonHelper.convertDetailFromServer(String.valueOf(Integer.valueOf(result.get(index).get(3))*1000)),"postdates are different!!!");
//					//Assert.assertEquals(pInfo.content, CommonHelper.ReEncodeDataFromDB(result, index, 4),"contents are different!!!");
//					
//			}
////			else 
////			{
////				 index=CommonHelper.getIndexByTimeFromDatabase(pInfo.postdate, result, 3);
////			}
//			
//		}
//	}
//	
//	public class PostInfo
//	{
//		String pid;
//		String fid;
//		String tid;
//		String postdate;
//		String subject;
//		String content;
//		public PostInfo(JSONObject jsonObject)
//		{
//			pid=jsonObject.getString("pid");
//			fid=jsonObject.getString("fid");
//			tid=jsonObject.getString("tid");
//			postdate=jsonObject.getString("postdate");
//			subject=jsonObject.getString("subject").replace("\\", "");
//		    content=jsonObject.getString("content").replace("\\", "").replace("<br/>", "\n");
//		}
//	}
	@StepMethodDesc(description="postList,nga interface 8",owner="Ruiyun.Ren")
	public void postList(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.PostList,setParam(parameter));
		NgaHelper.assertCodeAndMsg(actual, parameter);
		if(actual.getString("code").equals("0"))
		{
			getTidAndPid(actual);
			assertParam(actual);
		}
		
		Thread.sleep(20000);
	
	}
	public void getTidAndPid(JSONObject actual)
	{
		JSONArray result=actual.getJSONArray("result");
		if(result.size()>1)
		{
			StepValueHelper.RemoveStepOutputValue("pid");
			StepValueHelper.putStepOutputValue("pid", result.getJSONObject(1).getString("pid"));
		}
		
			
	}
	public void assertParam(JSONObject actual) throws Exception
	{
		JSONArray result=actual.getJSONArray("result");
		String tid=StepValueHelper.getStepOutputValue("tid").toString();
		List<List<String>>resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA,Constant.Sql.Get_Fid_Sql+tid);
		assertPost(actual, result, resultSet);
		for(int i=0;i<result.size();i++)
		{
			JSONObject author=result.getJSONObject(i).getJSONObject("author");
			String uid=author.getString("uid");
			List<List<String>>resultSet1=DataBaseHelper.getResult(Constant.DB_URL_KA, Constant.Sql.Get_User_Info_Sql+uid);
			assertUserInfo(author, resultSet1);
			List<List<String>>resultSet2=DataBaseHelper.getResult(Constant.DB_URL_KA, Constant.Sql.Get_Id_Medal_Sql+uid);
			if(resultSet2.size()>1)
			{
				String id=resultSet2.get(1).get(0);
				List<List<String>>resultSet3=DataBaseHelper.getResult(Constant.DB_URL_KA, Constant.Sql.Get_Name_Groups_Sql+id);
				Assert.assertEquals(author.getString("bit_data"), resultSet3.get(1).get(0),"bit_data is diff");
			    String member=NgaHelper.ReEncodeDataFromDB(resultSet3, 1, 1);
			    Assert.assertEquals(author.getString("members"), member,"members is diff");
			}
			
		}
	}
	public void assertUserInfo(JSONObject author,List<List<String>>resultSet1) throws Exception
	{
		String username=NgaHelper.ReEncodeDataFromDB(resultSet1, 1, 0);
		Assert.assertEquals(author.getString("username"), username,"username is diff");
		Assert.assertEquals(author.getString("credit"), resultSet1.get(1).get(1),"credit is diff");
		Assert.assertEquals(author.getString("groupid"), resultSet1.get(1).get(2),"groupid is diff");
		Assert.assertEquals(author.getString("memberid"), resultSet1.get(1).get(3),"memberid is diff");
		Assert.assertEquals(author.getString("yz"), resultSet1.get(1).get(4),"yz is diff");
		Assert.assertEquals(author.getString("regdate"), resultSet1.get(1).get(5),"regdate is diff");
		Assert.assertEquals(author.getString("postnum"), resultSet1.get(1).get(6),"postnum is diff");
		String rvrc=String.valueOf((Integer.valueOf(resultSet1.get(1).get(7)))/10);
		Assert.assertEquals(author.getString("rvrc"), rvrc,"rvrc is diff");
		Assert.assertEquals(author.getString("money"), resultSet1.get(1).get(8),"money is diff");
//		Assert.assertEquals(author.getString("thisvisit"), resultSet1.get(1).get(9),"thisvisit is diff");

	}
	public void assertPost(JSONObject actual,JSONArray result,List<List<String>>resultSet) throws Exception
	{
		if(result.size()>0)
		{
			Assert.assertEquals(result.getJSONObject(0).getString("fid"), resultSet.get(1).get(0));
//			Assert.assertEquals(result.getJSONObject(0).getString("postdatetimestamp"), resultSet.get(1).get(1));
		}
		String subject=NgaHelper.ReEncodeDataFromDB(resultSet, 1, 2);
		Assert.assertEquals(actual.getString("tsubject"), subject,"tsubject is diff");
		Assert.assertEquals(actual.getString("tauthorid"), resultSet.get(1).get(4),"tauthorid is diff");
		String tauthor=NgaHelper.ReEncodeDataFromDB(resultSet, 1, 3);
		Assert.assertEquals(actual.getString("tauthor"), tauthor,"tauthor is diff");
			
		
	}
	private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
		String sqlCommand="select tid from pw_threads where postdate>'1450237258' order by rand() limit 1 ";
		List<List<String>>resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand);
		String tid1=resultSet.get(1).get(0);
		String tid=ParamHelper.setParamByThreeWay(parameter, urlParameters, "tid",tid1);
		String page=ParamHelper.setExistParam(parameter, urlParameters, "page");
		String uid=ParamHelper.setExistParam(parameter, urlParameters, "uid");
		String pid=ParamHelper.setExistParam(parameter, urlParameters, "pid");
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters,tid);
		return urlParameters;

		}
}
