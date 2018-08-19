package com.wanmei.mobile.iat.wow.steps;

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
import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class WowCharacterListSteps {
	@StepMethodDesc(description="wowCharacterList,nga interface 70",owner="Ruiyun.Ren")
	public void wowCharacterList(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.WowCharacterList, setParam(parameter));
        if(actual.getString("code").equals("0"))
        {
        	if(!(actual.getString("result").equals("null")))
        {
        	JSONArray result=actual.getJSONArray("result");
        	assertProfile(result);
        }
        }	
		}
	public void assertProfile(JSONArray result) throws Exception
	{
		for(int i=0;i<result.size();i++)
		{
			String id=result.getJSONObject(i).getString("id");
			StepValueHelper.putStepOutputValue("wc_id", id);
			List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, Constant.Sql.Get_PROFILR_Sql.replace("ID", id));
			Assert.assertEquals(result.getJSONObject(i).getString("bind_uid"), resultSet.get(1).get(0), "bind_uid is not equals");
			Assert.assertNotNull(result.getJSONObject(i).getString("bind_uname"));
			Assert.assertEquals(result.getJSONObject(i).getString("locale"), resultSet.get(1).get(1), "locale is not equals");
			Assert.assertEquals(result.getJSONObject(i).getString("realm_id"), resultSet.get(1).get(2), "realm_id is not equals");
			Assert.assertNotNull(result.getJSONObject(i).getString("realm_name"));
			Assert.assertNotNull(result.getJSONObject(i).getString("name"));
			if(!(resultSet.get(1).get(6)==null))
			{
				String base_info=resultSet.get(1).get(6);
				String le=base_info.substring(base_info.indexOf("class"));
				String lev=le.substring(le.indexOf(":")+1,le.indexOf("}"));
				Assert.assertEquals(result.getJSONObject(i).getString("level"), getParam(resultSet, "level"), "level is not equals");
				Assert.assertEquals(result.getJSONObject(i).getString("faction_id"), getParam(resultSet, "faction"), "faction is not equals");
				Assert.assertEquals(result.getJSONObject(i).getString("race_id"), getParam(resultSet, "race"), "race is not equals");
				Assert.assertEquals(result.getJSONObject(i).getString("class_id"), lev, "class is not equals");
				Assert.assertNotNull(result.getJSONObject(i).getString("avatar"));
				Assert.assertNotNull(result.getJSONObject(i).getString("race"));
				Assert.assertNotNull(result.getJSONObject(i).getString("faction"));
//				SimpleLogger.logInfo(result.getJSONObject(i).getString("class"));
//				Assert.assertNotNull(result.getJSONObject(i).getString("class"));
			}
			
			
			
		}
		
	}
	public String getParam(List<List<String>> resultSet,String param)
	{
		String base_info=resultSet.get(1).get(6);
		String le=base_info.substring(base_info.indexOf(param));
		String lev=le.substring(le.indexOf(":")+1,le.indexOf(","));
		return lev;
	}

	private LinkedHashMap<String, String> setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String,String>();
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters,"");
		return urlParameters;
	}

}
