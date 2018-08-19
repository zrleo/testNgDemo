package com.wanmei.mobile.iat.main.steps;
import java.util.List;

import org.testng.Assert;

import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.AssertStepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.wanmei.mobile.iat.common.NgaLiveInterfaceHelper;

public class PostCommentSteps {
	
	@StepMethodDesc(description="check comments,nga live interface 4",owner="yaxiao.yu")
	public void postComment(String parameterID) throws Exception 
	{
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		List<List<String>> resultSet = NgaLiveInterfaceHelper.getDataFromDb(parameter, "sqlInfo");
		String cid = String.valueOf(Integer.valueOf(resultSet.get(1).get(0))+1);
		JSONObject result = MultiInterfaceSteps.multiInterface(parameterID);
		assertPostBroadcast(result, parameter,cid);
		
		List<List<String>> resultSet2 = NgaLiveInterfaceHelper.getDataFromDb(parameter, "dbInfo");
		assertDataInDb(resultSet2, parameter);
	}
	
	private void assertPostBroadcast(JSONObject result,InterfaceStepParameter parameter,String cid) throws Exception
	{
		NgaLiveInterfaceHelper.assertCodeAndMsg(result, parameter);
		Assert.assertEquals(result.getJSONObject("result").getString("cid"), cid,"cid is diff");
	}
	
	private void assertDataInDb(List<List<String>> resultSet,InterfaceStepParameter parameter) throws Exception
	{
		String replycid = setParameterForDbToVerify(parameter, "%replycid");
		Assert.assertEquals(resultSet.get(1).get(6), replycid,"replycid in database is diff");
		
		String is_quote = setParameterForDbToVerify(parameter,"%is_quote");		
		Assert.assertEquals(resultSet.get(1).get(7), is_quote,"is_quote in database is diff");
		Assert.assertEquals(resultSet.get(1).get(8), parameter.getValue("type"),"type in database is diff");
	}
	
	private String setParameterForDbToVerify(InterfaceStepParameter parameter,String keyWord)
	{
		String key = "";
		if(parameter.getValue(keyWord).equals(""))
		{
			key = "0";
		}
		else
		{
			key = parameter.getValue(keyWord);
		}
		return key;
	}
}
