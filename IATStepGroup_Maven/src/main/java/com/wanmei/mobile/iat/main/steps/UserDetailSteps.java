package com.wanmei.mobile.iat.main.steps;

import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.InflaterInputStream;

import org.testng.Assert;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



import com.wanmei.mobile.iat.common.CommonHelper;
import com.wanmei.mobile.iat.common.NGARequestSender;
import com.wanmei.mobile.iat.common.NgaHelper;
import com.wanmei.mobile.iat.common.UserRelated;


import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.gateside.autotesting.Gat.util.StepValuePool;
import com.gateside.autotesting.Lib.dbService.MysqlDBOperationService;
import com.gateside.autotesting.Lib.httpunitService.HttpUnitHelper;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class UserDetailSteps {
	@StepMethodDesc(description="first step login",owner="yaxiao.yu")
	 public void fisrtStepLogin(String parameterIDUsedToLogin)
	 {
		StepValuePool.createInstance().getValueDic().put("parameterIDUsedToLogin",parameterIDUsedToLogin);		
	 }
	
	 @StepMethodDesc(description="assert the login user and other's information",owner="yaxiao.yu")
	 public void assertUserBasicInfo(String parameterID) throws Exception
	 {		 
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 MysqlDBOperationService service = new MysqlDBOperationService(parameter.ConnectiongString);
		 List<List<String>> resultSet = service.executeQuery(parameter.getValue("userBasicInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
		 List<List<String>> resultSet2 = service.executeQuery(parameter.getValue("userMedalBasicInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
		 List<List<String>> resultSet3 = service.executeQuery(parameter.getValue("userGroupBasicInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));

		 String parameterIDUsedToLogin=(String) StepValueHelper.getStepOutputValue("parameterIDUsedToLogin");
		 String userid=parameter.getValue("uidlogin");
		 String url=parameter.getValue("url");
	     String response=NGARequestSender.sendRequest(userid, url,parameterIDUsedToLogin, parameter.getURLParametersMap(),"0");
	     String code=JSONObject.fromObject(response).getString("code");
	     Assert.assertEquals(code,"0","the returned code is not 0!!!");
	     String checktoken=JSONObject.fromObject(response).getString("checktoken");
	     Assert.assertEquals(checktoken,"1","the returned checktoken is not 1!!!");
	     JSONObject jsonObject=JSONObject.fromObject(response).getJSONObject("result");
	     UserBasic ub=new UserBasic(jsonObject);
	     assertUserBasic(ub,resultSet,resultSet3);	
	     assertUserMedal(jsonObject.getJSONArray("medal"),resultSet2);
	     LoginUserSpecific ub2=new LoginUserSpecific(jsonObject);
	     assertLoginUserSpecificInfo(ub2, resultSet);
	 }
	 
	 @StepMethodDesc(description="assert other's information",owner="yaxiao.yu")
	 public void assertOtherUserBasicInfo(String parameterID) throws Exception
	 {		 
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 MysqlDBOperationService service = new MysqlDBOperationService(parameter.ConnectiongString);
		 List<List<String>> resultSet = service.executeQuery(parameter.getValue("userBasicInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
		 List<List<String>> resultSet2 = service.executeQuery(parameter.getValue("userMedalBasicInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
		 List<List<String>> resultSet3 = service.executeQuery(parameter.getValue("userGroupBasicInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));

		 String parameterIDUsedToLogin=(String) StepValueHelper.getStepOutputValue("parameterIDUsedToLogin");
		 String userid=parameter.getValue("uidlogin");
		 String url=parameter.getValue("url");
	     String response=NGARequestSender.sendRequest(userid, url,parameterIDUsedToLogin, parameter.getURLParametersMap(),"0");
	     String code=JSONObject.fromObject(response).getString("code");
	     Assert.assertEquals(code,"0","the returned code is not 0!!!");
	     String checktoken=JSONObject.fromObject(response).getString("checktoken");
	     Assert.assertEquals(checktoken,"1","the returned checktoken is not 1!!!");
	     JSONObject jsonObject=JSONObject.fromObject(response).getJSONObject("result");
	     UserBasic ub=new UserBasic(jsonObject);
	     assertUserBasic(ub,resultSet,resultSet3);	
	     assertUserMedal(jsonObject.getJSONArray("medal"),resultSet2);
	 }
	 
	 @StepMethodDesc(description="assert the no login user's information ",owner="yaxiao.yu")
	 public void assertNoLoginUserBasicInfo(String parameterID) throws Exception
	 {		 
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 MysqlDBOperationService service = new MysqlDBOperationService(parameter.ConnectiongString);
		 List<List<String>> resultSet = service.executeQuery(parameter.getValue("userBasicInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
		 List<List<String>> resultSet2 = service.executeQuery(parameter.getValue("userMedalBasicInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
		 List<List<String>> resultSet3 = service.executeQuery(parameter.getValue("userGroupBasicInfoSql"),parameter.getValue("dbuser"),parameter.getValue("dbpassword"));
		 WebConversation wc = new WebConversation();
		 WebRequest req = new PostMethodWebRequest( parameter.getValue("url") );
		 req.setParameter("uid", parameter.getValue("uid"));
		 WebResponse response=wc.getResource(req);
	     String code=JSONObject.fromObject(response.getText()).getString("code");
	     Assert.assertEquals(code,"0","the returned code is not 0!!!");
//		 String checktoken=JSONObject.fromObject(response.getText()).getString("checktoken");
//		 Assert.assertEquals(checktoken,"-1","the returned checktoken is not 1!!!");
		 JSONObject jsonObject=JSONObject.fromObject(response.getText()).getJSONObject("result");
		 UserBasic ub=new UserBasic(jsonObject);
		 assertUserBasic(ub,resultSet,resultSet3);
		 assertUserMedal(jsonObject.getJSONArray("medal"),resultSet2);
	 }
	 
	 @StepMethodDesc(description="assert the login user's specific information ",owner="yaxiao.yu")
	 public void assertLoginUserSpecificInfo(LoginUserSpecific ub,List<List<String>> resultSet)
	 {
		 Assert.assertEquals(ub.verified,resultSet.get(1).get(8),"verifieds are different!!!");
		 Assert.assertEquals(ub.yz,resultSet.get(1).get(8),"yzs are different!!!");
		 Assert.assertEquals(ub.newpm,resultSet.get(1).get(16),"newpms are different!!!");
		 if(!ub.email.startsWith(resultSet.get(1).get(11).substring(0, 4)))
		 {
		     Assert.assertEquals(ub.email,resultSet.get(1).get(11),"emails are different!!!");
		 }
		 Assert.assertEquals(ub.items,"1","items are different!!!");
	 }
	 
	 public void assertUserBasic(UserBasic ub,List<List<String>> resultSet,List<List<String>> resultSet3) throws Exception
	 {
		 Assert.assertEquals(ub.uid,resultSet.get(1).get(0),"uids are different!!!");
		 Assert.assertEquals(ub.username,resultSet.get(1).get(1),"usernames are different!!!");
		 if(resultSet.get(1).get(2).equals("-1"))
		 {
			 Assert.assertEquals(ub.gid,resultSet.get(1).get(3),"memberids are different!!!");
		     Assert.assertEquals(ub.groupid,resultSet.get(1).get(3),"groupids are different!!!");
		     Assert.assertEquals(ub.memberid,resultSet.get(1).get(3),"memberids are different!!!");
		 }
		 else 
		 {
			 Assert.assertEquals(ub.gid,resultSet.get(1).get(2),"memberids are different!!!");
			 Assert.assertEquals(ub.groupid,resultSet.get(1).get(2),"groupids are different!!!"); 
			 Assert.assertEquals(ub.memberid,resultSet.get(1).get(2),"memberids are different!!!");
		 }		 
		 Assert.assertEquals(ub.posts,resultSet.get(1).get(4),"postnums are different!!!");
		 Assert.assertEquals(ub.fame,resultSet.get(1).get(5),"rvrcs are different!!!");
		 if(String.valueOf((Float.valueOf(resultSet.get(1).get(5))/10)).endsWith(".0"))
		 {
			 Assert.assertEquals(ub.rvrc,String.valueOf(Integer.valueOf(Integer.valueOf(resultSet.get(1).get(5))/10)),"rvrcs are different!!!");
		 }
		 else 
		 {
			 Assert.assertEquals(ub.rvrc,String.valueOf(Float.valueOf(resultSet.get(1).get(5))/10),"rvrcs are different!!!");
		 }
		 
		 Assert.assertEquals(ub.money,resultSet.get(1).get(6),"moneys are different!!!");
		 Assert.assertEquals(ub.honor,resultSet.get(1).get(7),"honors are different!!!");		
		 Assert.assertEquals(ub.lastpost,resultSet.get(1).get(9),"lastposts are different!!!");
		 Assert.assertEquals(ub.regdate,resultSet.get(1).get(10),"regdates are different!!!");	
		 Assert.assertEquals(ub.avatar,resultSet.get(1).get(12),"avatars are different!!!");
		 Assert.assertEquals(ub.sign,NgaHelper.ReEncodeDataFromDB(resultSet, 1, 13),"signs are different!!!");
		 if(resultSet.get(1).get(14).isEmpty())
		 {
			 Assert.assertEquals(ub.muteTime,"0","muteTimes are different!!!");
		 }
		 else 
		 {
			 Assert.assertEquals(ub.muteTime,resultSet.get(1).get(14),"muteTimes are different!!!");
		 }	
		 Assert.assertEquals(ub.group,NgaHelper.ReEncodeDataFromDB(resultSet3,1,0),"group names are different!!!");
		 Assert.assertEquals(ub._admin,resultSet3.get(1).get(1),"admins are different!!!");
		 Assert.assertEquals(ub._super,resultSet3.get(1).get(2),"supers are different!!!");
		 Assert.assertEquals(ub._greater,resultSet3.get(1).get(3),"greaters are different!!!");
		 Assert.assertEquals(ub._lesser,resultSet3.get(1).get(4),"lessers are different!!!");
		 if(resultSet3.get(1).get(5).equals("0"))
		 {
			 Assert.assertEquals(ub.userForum,"false","userForums are different!!!");
		 }
		 else 
		 {
			 //Assert.assertEquals(ub.userForum,"true","userForums are different!!!");
		 }
	 }
	 
	 @StepMethodDesc(description="assert the empty user's id in interface user_detail",owner="yaxiao.yu")
	 public void assertNoLoginEmptyUserBasicInfo(String parameterID) throws Exception
	 {
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 WebConversation wc = new WebConversation();
		 WebRequest req = new PostMethodWebRequest( parameter.getValue("url") );
		 req.setParameter("uid", parameter.getValue("uid"));
		 WebResponse response=wc.getResource(req);		 
	     String code=JSONObject.fromObject(response.getText()).getString("code");
	     Assert.assertEquals(code,"12","the returned code is not 12!!!");
	 
	 }
	 
	 @StepMethodDesc(description="assert the no exist user's id in interface user_detail",owner="yaxiao.yu")
	 public void assertNoLoginNoExistUserBasicInfo(String parameterID) throws Exception
	 {
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 WebConversation wc = new WebConversation();
		 WebRequest req = new PostMethodWebRequest( parameter.getValue("url") );
		 req.setParameter("uid", parameter.getValue("uid"));
		 WebResponse response=wc.getResource(req);		 
	     String code=JSONObject.fromObject(response.getText()).getString("code");
	     Assert.assertEquals(code,"16","the returned code is not 16!!!");
	 
	 }
	 
	 public void assertUserMedal(JSONArray jsonArray,List<List<String>> resultSet2)
	 {
		 Assert.assertEquals(jsonArray.size(), resultSet2.size()-1,"medal numbers are different!!!");
		 for(int i=0;i<jsonArray.size();i++)
		 {
			 UserMedal md=new UserMedal(jsonArray.getJSONObject(i));
			 Assert.assertEquals(md.icon, resultSet2.get(i).get(0),"icons are different!!!");
			 Assert.assertEquals(md.dscp, resultSet2.get(i).get(1),"dscps are different!!!");
			 Assert.assertEquals(md.name, resultSet2.get(i).get(2),"names are different!!!");
			 Assert.assertEquals(md.id, resultSet2.get(i).get(3),"ids are different!!!");
		 }
	 }
	 
	 public class UserMedal
	 {
		 String icon;
		 String name;
		 String dscp;
		 String id;
		 public UserMedal(JSONObject jsonObject)
		 {
			 icon=jsonObject.getString("icon");
			 name=jsonObject.getString("name");
			 dscp=jsonObject.getString("dscp");
			 id=jsonObject.getString("id");
		 }
	 }
	 
	 public class LoginUserSpecific
	 {
		 String newpm;
		 String yz;
		 String verified;
		 String email;
		 String items;
		 public LoginUserSpecific(JSONObject jsonObject)
		 {
			 newpm=jsonObject.getString("newpm");
			 yz=jsonObject.getString("yz");
			 email=jsonObject.getString("email");
			 verified=jsonObject.getString("verified");
			 items=jsonObject.getString("items");
		 }
	 }
	 
	 public class UserBasic
	 {
		 String uid;
		 String username;
		 String gid;
		 String fame;
		 String bit;
		 String groupid;
		 String memberid;
		 String group;
		 String posts;
		 String rvrc;
		 String money;
		 String title;
		 String honor;
		 String lastpost;
		 String regdate;
		 String muteTime;
		 String avatar;
		 String sign;
		 String userForum;
		 String _admin;
		 String _super;
		 String _greater;
		 String _lesser;
		 public UserBasic(JSONObject jsonObject)
		 {
			 uid=jsonObject.getString("uid");
			 username=jsonObject.getString("username");
			 groupid=jsonObject.getString("groupid");
			 memberid=jsonObject.getString("memberid");
			 group=jsonObject.getString("group");
			 posts=jsonObject.getString("posts");
			 rvrc=jsonObject.getString("rvrc");
			 money=jsonObject.getString("money");
			 title=jsonObject.getString("title");
			 honor=jsonObject.getString("honor");
			 lastpost=jsonObject.getString("lastpost");
			 regdate=jsonObject.getString("regdate");
			 muteTime=jsonObject.getString("muteTime");
			 avatar=jsonObject.getString("avatar");
			 sign=jsonObject.getString("sign");
			 gid=jsonObject.getString("gid");
			 fame=jsonObject.getString("fame");
			 bit=jsonObject.getString("bit");
			 userForum=jsonObject.getString("userForum");
			 _admin=jsonObject.getString("_admin");
			 _super=jsonObject.getString("_super");
			 _greater=jsonObject.getString("_greater");
			 _lesser=jsonObject.getString("_lesser");
		 }
	 }
}
