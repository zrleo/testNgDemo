package com.wanmei.mobile.iat.live.steps;

import java.util.List;

import org.testng.Assert;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import android.R.integer;

import com.gateside.autotesting.Gat.dataobject.AssertStepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValuePool;
import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.wanmei.mobile.iat.common.CommonHelper;
import com.wanmei.mobile.iat.common.DataBaseHelper;

public class GetBroadcastsSteps {
//	@StepMethodDesc(description="get broadcasts,nga live interface 5",owner="yaxiao.yu")
//	public void getBroadcasts(String parameterID) throws Exception 
//	{
//		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);		
//		JSONObject result = SingleInterfaceSteps.singleInterface(parameterID);
//		List<List<String>> resultSet = DataBaseHelper.getResultFromDBBySqlNameInXml(parameter, "dburl","landOwnerInfo", parameter.getValue("%tid"));
//		String perpage = setPerpageForSqlCommand(parameter);
//		String sqlCommand = parameter.getValue("commentInfo").replace("TID", parameter.getValue("%tid")).replace("PERPAGE", perpage);
//		List<List<String>> resultSet2 = DataBaseHelper.getResultFromDBBySqlCommand(sqlCommand, parameter, "dburl");
//        
//		assertGetBroadcast(result, parameter, resultSet, resultSet2);
//		
//	}
//	
//	@StepMethodDesc(description="get broadcasts,nga live interface 5",owner="yaxiao.yu")
//	public void getBroadcastsAfterPost(String parameterID) throws Exception 
//	{
//		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);		
//		JSONObject result = SingleInterfaceSteps.singleInterface(parameterID);
//		List<List<String>> resultSet = DataBaseHelper.getResultFromDBBySqlNameInXml(parameter, "dburl","landOwnerInfo", parameter.getValue("%tid"));
//		String perpage = setPerpageForSqlCommand(parameter);
//		String sqlCommand = parameter.getValue("commentInfo").replace("TID", parameter.getValue("%tid")).replace("PERPAGE", perpage);
//		List<List<String>> resultSet2 = DataBaseHelper.getResultFromDBBySqlCommand(sqlCommand, parameter, "dburl");
//        
//		assertGetBroadcast(result, parameter, resultSet, resultSet2);
//		Assert.assertEquals(result.getJSONObject("result").getJSONArray("comments").getJSONObject(0).getString("cid"),(String)StepValuePool.createInstance().getValueDic().get("cid"),"cid is diff!!");
//		
//	}
//	
//	@StepMethodDesc(description="get broadcasts,nga live interface 5",owner="yaxiao.yu")
//	public void getBroadcastsBelowSpecifiedCid(String parameterID) throws Exception 
//	{
//		getBroadcastBelowOrGreatSpecifiedCid(parameterID, "<");
//	}
//	
//	@StepMethodDesc(description="get broadcasts,nga live interface 5",owner="yaxiao.yu")
//	public void getBroadcastsGreatSpecifiedCid(String parameterID) throws Exception 
//	{
//		getBroadcastBelowOrGreatSpecifiedCid(parameterID,">");
//	}
//	
//	private void getBroadcastBelowOrGreatSpecifiedCid(String parameterID,String belowOrGreat) throws Exception 
//	{
//		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
//		JSONObject result = SingleInterfaceSteps.singleInterface(parameterID);
//		List<List<String>> resultSet = DataBaseHelper.getResultFromDBBySqlNameInXml(parameter, "dburl","landOwnerInfo", parameter.getValue("%tid"));
//		
//		String perpage = setPerpageForSqlCommand(parameter);
//		String cid = setCidForSqlCommand(parameter,belowOrGreat);
//		String sqlCommand = parameter.getValue("commentInfo").replace("TID", parameter.getValue("%tid")).replace("PERPAGE", perpage).replace("CID", cid);
//		List<List<String>> resultSet2 = DataBaseHelper.getResultFromDBBySqlCommand(sqlCommand, parameter, "dburl");
//      
//		assertGetBroadcast(result, parameter, resultSet, resultSet2);
//	}
//	
//	@StepMethodDesc(description="set cid For SqlCommand",owner="yaxiao.yu")
//	public static String setCidForSqlCommand(InterfaceStepParameter parameter,String belowOrGreat)
//	{
//		String cid = "";
//		if(!(parameter.getValue("%cid").equals("")||parameter.getValue("%cid").isEmpty()))
//		{
//			cid = belowOrGreat+"'"+parameter.getValue("%cid")+"'";
//		}
//		return cid;
//	}
//	
//	private void assertAttachment(Comment ct,InterfaceStepParameter parameter,JSONArray jArray) throws Exception
//	{
//		String sqlCommand = parameter.getValue("attachmentInfo").replace("CID", ct.cid).replace("UID", ct.uid);
//		List<List<String>> resultSet = DataBaseHelper.getResultFromDBBySqlCommand(sqlCommand, parameter, "dburl");
//		for(int j=0;j<resultSet.size()-1;j++)
//    	{
//    	    Attachments at = new Attachments(jArray.getJSONObject(j));
//    	    Assert.assertEquals(at.aid, resultSet.get(j+1).get(0),"aid is diff!!");
//    	    assertUrlOrThumb(at, resultSet, j);
//    	}
//		
//	}
//	
//	private void assertUrlOrThumb(Attachments at,List<List<String>> resultSet,int j)
//	{
//		System.out.println(at.url);
//		System.out.println(resultSet.get(j+1).get(1));
//		if(!(at.url.contains(resultSet.get(j+1).get(1))||at.url.contains(at.aid)))
//	    {
//	        System.out.println("url is diff!!");
//	        //Assert.assertEquals(actual, expected);
//	        Assert.assertEquals(at.url, resultSet.get(j+1).get(1),"url is diff!!");
//	    }
//	    String pre = resultSet.get(j+1).get(1).substring(0,resultSet.get(j+1).get(1).indexOf("."))+"_thumb";
//	    
//	    if(!(at.thumb.contains(pre)||at.url.contains(at.aid)||at.thumb.contains(resultSet.get(j+1).get(1))))
//	    {
//	    	System.out.println("thumb is diff!!");
//	    	Assert.assertEquals(at.thumb, resultSet.get(j+1).get(1),"thumb is diff!!");
//	    }
//	}
//	
// 	private String setPerpageForSqlCommand(InterfaceStepParameter parameter)
//	{
//		String perpage = "";
//		if(parameter.getValue("%perpage").equals(""))
//		{
//			perpage = "20";
//		}
//		else 
//		{
//			perpage = parameter.getValue("%perpage");
//		}
//		return perpage;
//	}
//	
//	private void assertGetBroadcast(JSONObject result,InterfaceStepParameter parameter,List<List<String>> resultSet,List<List<String>> resultSet2) throws Exception
//	{
//		NgaLiveInterfaceHelper.assertCodeAndMsg(result, parameter);		
//		assertUserInfo(result, resultSet);
//		assertCommentInfo(result, resultSet2,parameter);
//	}
//	
//	private void assertUserInfo(JSONObject result,List<List<String>> resultSet) throws Exception
//	{
//		UserInfo usi = new UserInfo(result.getJSONObject("result"));
//		Assert.assertEquals(usi.uid, resultSet.get(1).get(0),"uid is diff!");
//		Assert.assertEquals(usi.title, resultSet.get(1).get(1),"title is diff!");
//		Assert.assertEquals(usi.nickname, resultSet.get(1).get(2),"nickname is diff!");
//		assertAvatar(usi.avatar, usi.uid, 1, 4, resultSet);
//	}
//	
//	private void assertCommentInfo(JSONObject result,List<List<String>> resultSet,InterfaceStepParameter parameter) throws Exception
//	{
//		JSONArray jsonArray = result.getJSONObject("result").getJSONArray("comments");
//		for(int i=1;i<resultSet.size();i++)
//		{
//			JSONObject object = jsonArray.getJSONObject(i-1);
//		    Comment ct = new Comment(object);
//		    assertEverySingleComment(ct, i, resultSet,parameter);
//		    assertReplyOrAttachments(object, ct, parameter);
//		}
//
//	}
//	
//	private void assertReplyOrAttachments(JSONObject object,Comment ct,InterfaceStepParameter parameter) throws Exception
//	{
//		if(object.containsKey("reply"))
//	    {
//	    	Comment reply = new Comment(object.getJSONObject("reply"));
//	    	assertReply(ct, parameter, reply);
//	    }
//	    if(object.containsKey("attachments"))
//	    {
//	    	JSONArray jArray = object.getJSONArray("attachments");
//	    	assertAttachment(ct, parameter, jArray);
//	    }
//	}
//	
//	private void assertEverySingleComment(Comment ct,int i,List<List<String>> resultSet,InterfaceStepParameter parameter) throws Exception
//	{
//		Assert.assertEquals(ct.cid, resultSet.get(i).get(1),"cid is diff!");
//		Assert.assertEquals(ct.uid, resultSet.get(i).get(2),"uid is diff!");		
//		Assert.assertEquals(ct.replycid, resultSet.get(i).get(4),"replycid is diff!");
//		Assert.assertEquals(ct.is_quote, resultSet.get(i).get(5),"is_quote is diff!");
//		Assert.assertEquals(ct.time, resultSet.get(i).get(6),"time is diff!");
//		assertContent(ct, i, resultSet);
//		assertAvatar(ct.avatar, ct.uid, i, 9, resultSet);
//		Assert.assertEquals(ct.nickname, resultSet.get(i).get(8),"nickname is diff!");
//	}
//	
//	private void assertContent(Comment ct,int i,List<List<String>> resultSet)
//	{
//		if(!resultSet.get(i).get(7).equals("3"))
//		{
//		    Assert.assertEquals(ct.deleted, "false","deleted is diff!");
//		    Assert.assertEquals(ct.content, resultSet.get(i).get(3),"content is diff!");
//		}
//		else {
//			Assert.assertEquals(ct.content, "�������ѱ�ɾ��","content is diff!");
//		}
//	}
//	
//	private void assertReply(Comment ct,InterfaceStepParameter parameter,Comment reply) throws Exception
//	{
//		if(!ct.replycid.equals("0"))
//		{
//			String sqlCommand = parameter.getValue("replyInfo").replace("CID", ct.replycid);
//			List<List<String>> resultSet = DataBaseHelper.getResultFromDBBySqlCommand(sqlCommand, parameter, "dburl");
//			assertEverySingleComment(reply, 1, resultSet, parameter);
//		}
//	}
//	
//	private void assertAvatar(String avatar,String uid,int row,int coloumn,List<List<String>> resultSet)
//	{
//		if(resultSet.get(row).get(coloumn).isEmpty()||resultSet.get(row).get(coloumn).equals("null")||resultSet.get(row).get(coloumn).equals(""))
//		{
//			if(!avatar.endsWith(uid + ".jpg"))
//			{
//			    Assert.assertEquals(avatar, uid + ".jpg","avatar is diff!");
//			}
//		}
//		else 
//		{
//			Assert.assertEquals(avatar, resultSet.get(row).get(coloumn),"avatar is diff!");
//		}
//	}
//	
//	public class Attachments
//	{
//		String aid = "";
//		String url = "";
//		String thumb = "";
//		public Attachments(JSONObject result)
//		{
//			aid = result.getString("aid");
//			url = result.getString("url");
//			thumb = result.getString("thumb");
//		}
//	}
//	
//	public class UserInfo
//	{
//		String uid = "";
//		String title = "";
//		String nickname = "";
//		String avatar = "";
//		public UserInfo(JSONObject result)
//		{
//			uid = result.getString("uid");
//			title = result.getString("title");
//			nickname = result.getString("nickname");
//			avatar = result.getString("avatar");
//		}		
//	}
//	
//	public class Comment
//	{
//		String cid = "";
//		String uid = "";
//		String content = "";
//		String replycid = "";
//		String is_quote = "";
//		String time = "";
//		String deleted = "";
//		String nickname = "";
//		String avatar = "";
//		
//		public Comment(JSONObject result)
//		{
//			cid = result.getString("cid");
//			uid = result.getString("uid");
//			content = result.getString("content");
//			replycid = result.getString("replycid");
//			is_quote = result.getString("is_quote");
//			time = result.getString("time");
//			deleted = result.getString("deleted");
//			nickname = result.getString("nickname");
//			avatar = result.getString("avatar");
//		}
//		
//	}
}
