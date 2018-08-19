package com.wanmei.mobile.iat.message.steps;

import java.util.LinkedHashMap;
import java.util.List;



import net.sf.json.JSONArray;
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

public class MessageListSteps {
	@StepMethodDesc(description="messageList,nga interface 18",owner="Ruiyun.Ren")
	public void messageList(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		Thread.sleep(5000);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.MessageList,setParam(parameter));
		NgaHelper.assertCodeAndMsg(actual, parameter);
		assertParam(actual);
		
	
	}
	public void assertParam(JSONObject actual) throws Exception
	{
		JSONArray result=actual.getJSONArray("result");
		for(int i=0;i<result.size();i++)
		{
			String uid=(String)StepValueHelper.getStepOutputValue("access_uid");
			String sqlCommand="SELECT u.mid,u.bit,u.last_modify,s.did,s.time,s.posts FROM `pw_message_user` u join pw_message_subject s on s.id=u.mid  where u.to=UID ORDER BY u.last_modify DESC";
		    List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand.replace("UID", uid));
		    String did1=resultSet.get(i+1).get(3);
		    String sqlCommand1="select subject from pw_message_data where id=ID";
		    List<List<String>> resultSet1=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand1.replace("ID", did1));
		    String did=result.getJSONObject(i).getString("did");
		    StepValueHelper.putStepOutputValue("did", did);
//		    StepValueHelper.UpdateStepOutputValue("did", did);
		    Assert.assertEquals(did, resultSet.get(i+1).get(0));
		    Assert.assertEquals(result.getJSONObject(i).getString("bit"), resultSet.get(i+1).get(1));
		    String subject=NgaHelper.ReEncodeDataFromDB(resultSet1, 1, 0);
		    Assert.assertEquals(result.getJSONObject(i).getString("subject"),subject);
		    Assert.assertEquals(result.getJSONObject(i).getString("addTime"), resultSet.get(i+1).get(4));
		    Assert.assertEquals(result.getJSONObject(i).getString("posts_num"), resultSet.get(i+1).get(5));
		    Assert.assertEquals(result.getJSONObject(i).getString("last_modify"), resultSet.get(i+1).get(2));
		}
		
	}
	private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
		String page=ParamHelper.setParamByTwoWay(parameter, urlParameters, "Page", "");
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters,page);
		return urlParameters;
	}
}
