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
import com.wanmei.mobile.iat.common.RequestHelper;

public class BlockListSteps 
{
	@StepMethodDesc(description="blockList,nga interface 25",owner="Ruiyun.Ren")
	public void blockList(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.BlockList,setParam(parameter));	
		if(actual.getString("code").equals("0"))
		{
			assertBlockMember(actual);
		}
	}
	public void assertBlockMember(JSONObject actual) throws Exception
	{
		String uid=(String)StepValueHelper.getStepOutputValue("access_uid");
		String sqlCommand="select p.from,m.username from pw_message_block p join pw_members m on m.uid=p.from where p.to=UID ";
		List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand.replace("UID", uid));
        for(int j=0;j<actual.getJSONArray("result").size();j++)
        {
        	JSONArray result=actual.getJSONArray("result");
        	String uid1=result.getJSONObject(j).getString("uid");
        	StepValueHelper.putStepOutputValue("uid", uid1);
            Assert.assertEquals(uid1, resultSet.get(j+1).get(0));
            String name=NgaHelper.ReEncodeDataFromDB(resultSet, j+1, 1);
            Assert.assertEquals(result.getJSONObject(j).getString("username"),name);
        	
        }
   }
			

		private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
		{
			LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
			RequestHelper.setNessesaryParamAndSign(parameter, urlParameters, "");
			return urlParameters;
		}

}
