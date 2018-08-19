package com.wanmei.mobile.iat.message.steps;


import java.util.LinkedHashMap;
import java.util.List;





import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.testng.Assert;

import android.R.integer;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.NgaHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RandomHelper;
import com.wanmei.mobile.iat.common.RequestHelper;


public class MessageDetailSteps {
	@StepMethodDesc(description="messageDetail,nga interface 19",owner="Ruiyun.Ren")
	public void messageDetail(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.MessageDetail,setParam(parameter));
		NgaHelper.assertCodeAndMsg(actual, parameter);
		assertParam(actual);
		
	
	}
	public void assertParam(JSONObject actual) throws Exception
	{
		JSONArray result=actual.getJSONArray("result");
		for(int i=0;i<result.size();i++)
		{
			String id=result.getJSONObject(i).getString("id");
		    String sqlCommand="select d.from,d.subject,d.time from pw_message_data d where d.id=ID";
		    List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand.replace("ID", id));
		    String subject=NgaHelper.ReEncodeDataFromDB(resultSet, 1, 1);
		    Assert.assertEquals(result.getJSONObject(i).getString("subject"),subject);
		    Assert.assertEquals(result.getJSONObject(i).getString("addTime"), resultSet.get(1).get(2));
		    JSONObject user=result.getJSONObject(i).getJSONObject("user");
		    String uid=resultSet.get(1).get(0);
		    if(!(uid.equals("0")))
		    {
		    	String sqlCommand1="select username from pw_members where uid=UID";
			    List<List<String>>resultSet1=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand1.replace("UID", uid));
			    String username=NgaHelper.ReEncodeDataFromDB(resultSet1, 1, 0);
			    Assert.assertEquals(user.getString("username"), username);
		    }
		}
		
	}
	private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
		String did=ParamHelper.setParamByThreeWay(parameter, urlParameters, "did", "");
		int page1=RandomHelper.getRandomInt(10);
		String page=ParamHelper.setParamByTwoWay(parameter, urlParameters, "page",String.valueOf(page1));
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters,did+page);
		return urlParameters;
	}

}
