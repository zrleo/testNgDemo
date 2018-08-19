package com.wanmei.mobile.iat.post.steps;

import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.testng.Assert;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.NgaHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class UserReplysSteps {
	@StepMethodDesc(description="userReplys,nga interface 23",owner="Ruiyun.Ren")
	public void userReplys(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.UserDetail,setParam(parameter));	
        NgaHelper.assertCodeAndMsg(actual, parameter);
        if(actual.getString("code").equals("0"))
        {
        	asserUserInfo(actual);
//        	asserUserMedal(actual);
//        	asserUserGroup(actual);
        }
        
			
		}
	public void asserUserInfo(JSONObject actual) throws Exception
	{   
		String sqlCommand="select uid,username,groupid,memberid,regdate,medal,lastpost,lastvisit,thisvisit,rvrc,money from pw_members where uid=UID";
		String uid=(String)StepValueHelper.getStepOutputValue("uid");
		List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand.replace("UID", uid));
		Assert.assertEquals(actual.getJSONObject("result").getString("uid"), resultSet.get(1).get(0),"uid are different!!!");
		Assert.assertEquals(actual.getJSONObject("result").getString("username"), resultSet.get(1).get(1),"username are different!!!");
		if(resultSet.get(1).get(2).equals("-1"))
		{
			Assert.assertEquals(actual.getJSONObject("result").getString("gid"), resultSet.get(1).get(3),"gid are different!!!");
			Assert.assertEquals(actual.getJSONObject("result").getString("groupid"), resultSet.get(1).get(3),"groupid are different!!!");
			Assert.assertEquals(actual.getJSONObject("result").getString("memberid"), resultSet.get(1).get(3),"memberid are different!!!");
		}
		else {
			Assert.assertEquals(actual.getJSONObject("result").getString("gid"), resultSet.get(1).get(2),"gid are different!!!");
			Assert.assertEquals(actual.getJSONObject("result").getString("groupid"), resultSet.get(1).get(2),"groupid are different!!!");
			Assert.assertEquals(actual.getJSONObject("result").getString("memberid"), resultSet.get(1).get(2),"memberid are different!!!");
		}
		Assert.assertEquals(actual.getJSONObject("result").getString("regdate"), resultSet.get(1).get(4),"regdate are different!!!");
//		Assert.assertEquals(actual.getJSONObject("result").getString("medal"), resultSet.get(1).get(5),"medal are different!!!");
		Assert.assertEquals(actual.getJSONObject("result").getString("lastpost"), resultSet.get(1).get(6),"lastpost are different!!!");
		Assert.assertEquals(actual.getJSONObject("result").getString("lastvisit"), resultSet.get(1).get(7),"lastvisit are different!!!");
		Assert.assertEquals(actual.getJSONObject("result").getString("thisvisit"), resultSet.get(1).get(8),"thisvisit are different!!!");
		Assert.assertEquals(actual.getJSONObject("result").getString("rvrc"), resultSet.get(1).get(9),"rvrc are different!!!");
		Assert.assertEquals(actual.getJSONObject("result").getString("money"), resultSet.get(1).get(10),"money are different!!!");
	}
	public void asserUserGroup(JSONObject actual) throws Exception
	{   
		String sqlCommand="select user_forum,admin,super,greater,lesser from pw_groups where name=Name";
		String Name=(String)StepValueHelper.getStepOutputValue("name");
		List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand.replace("Name", Name));
		Assert.assertEquals(actual.getJSONObject("result").getString("userForum"), resultSet.get(1).get(0));
		Assert.assertEquals(actual.getJSONObject("result").getString("_admin"), resultSet.get(1).get(1));
		Assert.assertEquals(actual.getJSONObject("result").getString("_super"), resultSet.get(1).get(2));
		Assert.assertEquals(actual.getJSONObject("result").getString("_greater"), resultSet.get(1).get(3));
		Assert.assertEquals(actual.getJSONObject("result").getString("_lesser"), resultSet.get(1).get(4));

	}
	public void asserUserMedal(JSONObject actual) throws Exception
	{   
		String sqlCommand="select name from pw_medal where owneruid=UID";
		String uid=(String)StepValueHelper.getStepOutputValue("uid");
		List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand.replace("UID", uid));
		StepValueHelper.putStepOutputValue("name", resultSet.get(1).get(0));
	}

	private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
		String sqlCommand="select uid from pw_members ORDER BY RAND() LIMIT 1";
	    List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand);
		String uid=ParamHelper.setParamByTwoWay(parameter, urlParameters, "uid", resultSet.get(1).get(0));
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters, uid);
		return urlParameters;
	}

}
