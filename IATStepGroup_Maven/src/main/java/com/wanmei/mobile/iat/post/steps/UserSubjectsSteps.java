package com.wanmei.mobile.iat.post.steps;

import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.testng.Assert;

import android.R.integer;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.NgaHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

import javax.sound.midi.Soundbank;

public class UserSubjectsSteps {
	@StepMethodDesc(description="userSubjects,nga interface 22",owner="Ruiyun.Ren")
	public void userSubjects(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.UserSubjects,setParam(parameter));	
        NgaHelper.assertCodeAndMsg(actual, parameter);
        if(actual.getString("code").equals("0"))
        {
        	asserPostInfo(actual);

        }
        
			
		}

	public void asserPostInfo(JSONObject actual) throws Exception
	{
		String sqlCommand="select username from pw_members where uid=UID";
	    String uid=(String)StepValueHelper.getStepOutputValue("uid");
	    List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand.replace("UID", uid));
	    String username=NgaHelper.ReEncodeDataFromDB(resultSet, 1, 0);
	    String sqlCommand1="SELECT tid,fid,titlefont,author,authorid,subject,postdate,lastpost,replies,type FROM `pw_threads` where author='USERNAME' ORDER BY lastpost DESC";
	    List<List<String>>resultSet1=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand1.replace("USERNAME", username));
		JSONArray data=actual.getJSONObject("result").getJSONArray("data");
		for(int i=0;i<data.size();i++)
		{
			Assert.assertEquals(data.getJSONObject(i).getString("tid"), resultSet1.get(i+1).get(0),"tid are different!!!");
			Assert.assertEquals(data.getJSONObject(i).getString("fid"), resultSet1.get(i+1).get(1),"fid are different!!!");
			if(!(data.getJSONObject(i).getString("titlefont").equals("")))
				Assert.assertEquals(data.getJSONObject(i).getString("titlefont"),resultSet1.get(i+1).get(2),"titlefont are different!!!");
			String subject=NgaHelper.ReEncodeDataFromDB(resultSet1,i+1,5);
			if(!(data.getJSONObject(i).getString("subject").equals("帐号权限不足")||data.getJSONObject(i).getString("subject").equals("访客不能直接访问")))
			{
				String author=NgaHelper.ReEncodeDataFromDB(resultSet1, i+1, 3);
				Assert.assertEquals(data.getJSONObject(i).getString("author"),author,"author are different!!!");
				Assert.assertEquals(data.getJSONObject(i).getString("authorid"), resultSet1.get(i+1).get(4),"authorid are different!!!");
				Assert.assertEquals(data.getJSONObject(i).getString("subject"),subject,"subject are different!!!");
				Assert.assertEquals(data.getJSONObject(i).getString("postdate"), resultSet1.get(i+1).get(6),"postdate are different!!!");
				Assert.assertEquals(data.getJSONObject(i).getString("lastpost"), resultSet1.get(i+1).get(7),"lastpost are different!!!");
				Assert.assertEquals(data.getJSONObject(i).getString("replies"), resultSet1.get(i+1).get(8),"replies are different!!!");
				
			}
			Assert.assertEquals(data.getJSONObject(i).getString("type"), resultSet1.get(i+1).get(9),"type are different!!!");
		}
		
	}

	private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
		String sqlCommand="select uid from pw_members ORDER BY RAND() LIMIT 1";
	    List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand);
	    String sqlCommand1="select fid from pw_threads ORDER BY RAND() LIMIT 1";
	    List<List<String>> resultSet1=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand1);
		String uid=ParamHelper.setParamByTwoWay(parameter, urlParameters, "uid", resultSet.get(1).get(0));
		String page=ParamHelper.setParamByTwoWay(parameter, urlParameters, "page","");
		String fid=ParamHelper.setParamByTwoWay(parameter, urlParameters, "fid", resultSet1.get(1).get(0));
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters, uid+page+fid);

		System.out.println(urlParameters);
		return urlParameters;
	}

}
