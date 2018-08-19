package com.wanmei.mobile.iat.wow.steps;

import java.util.LinkedHashMap;
import java.util.List;

import java_cup.runtime.virtual_parse_stack;
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

public class WowCharacterRankingSteps {
	@StepMethodDesc(description="wowCharacterRanking,nga interface 74",owner="Ruiyun.Ren")
	public void wowCharacterRanking(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.WowCharacterRanking, setParam(parameter));
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
			List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, Constant.Sql.Get_CHARACTER_RANKING1_Sql.replace("TYPE", ranking_type));
			for(int i=0;i<result.size();i++)
			{
				param(result, i, resultSet, ranking_type);
			}
		}
		else {
			List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, Constant.Sql.Get_CHARACTER_RANKING_Sql.replace("ID", id).replace("TYPE", ranking_type));
			for(int i=0;i<result.size();i++)
			{
				param(result, i, resultSet, ranking_type);
			}
		}
	}
   public void param(JSONArray result,int i,List<List<String>> resultSet,String ranking_type)
   {
	   Assert.assertEquals(result.getJSONObject(i).getString("id"), resultSet.get(i+1).get(0), "id is not equals");
	   Assert.assertEquals(result.getJSONObject(i).getString("bind_uid"), resultSet.get(i+1).get(1), "bind_uid is not equals");
	   Assert.assertNotNull(result.getJSONObject(i).getString("bind_uname"));
	   Assert.assertEquals(result.getJSONObject(i).getString("locale"), resultSet.get(i+1).get(2), "locale is not equals");
	   Assert.assertNotNull(result.getJSONObject(i).getString("name"));
	   Assert.assertEquals(result.getJSONObject(i).getString("realm_id"), resultSet.get(i+1).get(3), "realm_id is not equals");
	   Assert.assertNotNull(result.getJSONObject(i).getString(ranking_type));
   }

	private LinkedHashMap<String, String> setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String,String>();
		String ranking_type=ParamHelper.setParamByThreeWay(parameter, urlParameters, "ranking_type", WowHelper.getRankingTypeForCharacter());
		List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, Constant.Sql.Get_REALM_ID_Sql);
		String realm_id=ParamHelper.setParamByThreeWay(parameter, urlParameters, "realm_id", resultSet.get(1).get(0));
		ParamHelper.setParamExistOrValuePool(parameter, urlParameters, "page");
        ParamHelper.setParamExistOrValuePool(parameter, urlParameters, "boss_id");
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters,ranking_type+realm_id);
		return urlParameters;
	}

}
