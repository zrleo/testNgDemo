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
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class WowRaidListSteps {
	@StepMethodDesc(description="wowRaidList,nga interface 75",owner="Ruiyun.Ren")
	public void wowRaidList(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.WowRaidList, setParam(parameter));
        if(actual.getString("code").equals("0"))
        {
        	JSONArray result=actual.getJSONArray("result");
        	assertRaidId(result);
        }

			
		}
	public void assertRaidId(JSONArray result) throws Exception
	{
		if(StepValueHelper.getStepOutputValue("type").toString().equals("guild"))
		{
			List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, Constant.Sql.Get_GUILD_RAID_Sql);
			for(int i=0;i<resultSet.size()-1;i++)
			{
				Assert.assertEquals(result.getJSONObject(i).getString("id"), resultSet.get(i+1).get(0), "raid_id is not equals");
				Assert.assertNotNull(result.getJSONObject(i).getString("name"));
				JSONArray achievement=result.getJSONObject(i).getJSONArray("achievements");
				String id=resultSet.get(i+1).get(0);
		        assertAchievementId(result, achievement,Constant.Sql.Get_GUILD_ACHIEVEMENT_ID_Sql.replace("ID", id));
		  }
		}
		else if (StepValueHelper.getStepOutputValue("type").toString().equals("character")) 
		{
			List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, Constant.Sql.Get_CHARACTER_RAID_Sql);
			for(int i=0;i<resultSet.size()-1;i++)
			{
				Assert.assertEquals(result.getJSONObject(i).getString("id"), resultSet.get(i+1).get(0), "raid_id is not equals");
				Assert.assertNotNull(result.getJSONObject(i).getString("name"));
				JSONArray achievement=result.getJSONObject(i).getJSONArray("achievements");
				String id=resultSet.get(i+1).get(0);
		        assertAchievementId(result, achievement,Constant.Sql.Get_CHARACTER_ACHIEVEMENT_ID_Sql.replace("ID", id));
		  }
		}
	}
	public void assertAchievementId(JSONArray result,JSONArray achievement,String commandSql) throws Exception
	{
		List<List<String>> resultSet1=DataBaseHelper.getResult(Constant.DB_URL_KA, commandSql);
		for(int j=0;j<resultSet1.size()-1;j++)
		{
			Assert.assertEquals(achievement.getJSONObject(j).getString("id"), resultSet1.get(j+1).get(0), "achievement_id is not equals");
			Assert.assertNotNull(achievement.getJSONObject(j).getString("name"));
			Assert.assertNotNull(achievement.getJSONObject(j).getString("icon"));
		}
		
		
	}
   

	private LinkedHashMap<String, String> setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String,String>();
		String type=ParamHelper.setParamByThreeWay(parameter, urlParameters, "type", WowHelper.getType());
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters,type);
		
		return urlParameters;
	}

}
