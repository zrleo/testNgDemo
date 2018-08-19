package com.wanmei.mobile.iat.wow.steps;

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
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class WowGuildRankingSteps {
	@StepMethodDesc(description="wowGuildRanking,nga interface 76",owner="Ruiyun.Ren")
	public void wowGuildRanking(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.WowGuildRanking, setParam(parameter));
        if(actual.getString("code").equals("0"))
        {
        	JSONArray result=actual.getJSONArray("result");
        	assertResult(result);
        }

			
		}
	public void assertResult(JSONArray result) throws Exception
	{
		String id=StepValueHelper.getStepOutputValue("realm_id").toString();
		String ranking_type=StepValueHelper.getStepOutputValue("ranking_type").toString();
		if(id.equals("0") || id.equals(""))
		{
			List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, Constant.Sql.Get_GUILD_RANKING1_Sql);
			for(int i=0;i<result.size();i++)
			{
				param(result, i, resultSet, ranking_type);
			}
		}
		else {
			List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, Constant.Sql.Get_GUILD_RANKING_Sql.replace("ID", id));
			for(int i=0;i<result.size();i++)
			{
				param(result, i, resultSet, ranking_type);
			}
		}
	}
   public void param(JSONArray result,int i,List<List<String>> resultSet,String ranking_type)
   {
	   Assert.assertEquals(result.getJSONObject(i).getString("id"), resultSet.get(i+1).get(0), "id is not equals");
	   Assert.assertEquals(result.getJSONObject(i).getString("locale"), resultSet.get(i+1).get(1), "locale is not equals");
	   Assert.assertNotNull(result.getJSONObject(i).getString("name"));
	   Assert.assertNotNull(result.getJSONObject(i).getString("faction"));
	   Assert.assertEquals(result.getJSONObject(i).getString("realm_id"), resultSet.get(i+1).get(2), "realm_id is not equals");
	   Assert.assertNotNull(result.getJSONObject(i).getString("realm_name"));
	   Assert.assertNotNull(result.getJSONObject(i).getString(ranking_type));
	   Assert.assertNotNull(result.getJSONObject(i).getString("rank"));
   }
   

	private LinkedHashMap<String, String> setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String,String>();
		String ranking_type=ParamHelper.setParamByThreeWay(parameter, urlParameters, "ranking_type", WowHelper.getRankingTypeForGuild());
		List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, Constant.Sql.Get_REALM_ID_Sql);
		String realm_id=ParamHelper.setParamByThreeWay(parameter, urlParameters, "realm_id", resultSet.get(1).get(0));
		ParamHelper.setParamExistOrValuePool(parameter, urlParameters, "type");
		ParamHelper.setParamExistOrValuePool(parameter, urlParameters, "boss_id");
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters,ranking_type+realm_id);
		return urlParameters;
	}

}
