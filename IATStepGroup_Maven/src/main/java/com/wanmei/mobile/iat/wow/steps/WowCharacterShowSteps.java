package com.wanmei.mobile.iat.wow.steps;

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
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class WowCharacterShowSteps {
	@StepMethodDesc(description="wowCharacterShow,nga interface 73",owner="Ruiyun.Ren")
	public void wowCharacterShow(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.WowCharacterShow, setParam(parameter));
        if(actual.getString("code").equals("0"))
        {
        	JSONObject result=actual.getJSONObject("result");
        	JSONObject profile=result.getJSONObject("profile");
        	assertProfile(profile);
        }

			
		}
	public void assertProfile(JSONObject profile) throws Exception
	{
		String id=StepValueHelper.getStepOutputValue("wc_id").toString();
		List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, Constant.Sql.Get_PROFILR_Sql.replace("ID", id));
		Assert.assertEquals(profile.getString("bind_uid"), resultSet.get(1).get(0), "bind_uid is not equals");
		Assert.assertNotNull(profile.getString("bind_uname"));
		Assert.assertEquals(profile.getString("locale"), resultSet.get(1).get(1), "locale is not equals");
		Assert.assertEquals(profile.getString("realm_id"), resultSet.get(1).get(2), "realm_id is not equals");
		Assert.assertNotNull(profile.getString("realm_name"));
		Assert.assertNotNull(profile.getString("name"));
		if(!(resultSet.get(1).get(6)==null))
		{
			String base_info=resultSet.get(1).get(6);
			String le=base_info.substring(base_info.indexOf("class"));
			String lev=le.substring(le.indexOf(":")+1,le.indexOf("}"));
			Assert.assertEquals(profile.getString("level"), getParam(resultSet, "level"), "level is not equals");
			Assert.assertEquals(profile.getString("faction_id"), getParam(resultSet, "faction"), "faction is not equals");
			Assert.assertEquals(profile.getString("race_id"), getParam(resultSet, "race"), "race is not equals");
			Assert.assertEquals(profile.getString("class_id"), lev, "class is not equals");
			Assert.assertNotNull(profile.getString("avatar"));
			Assert.assertNotNull(profile.getString("race"));
			Assert.assertNotNull(profile.getString("faction"));
//			SimpleLogger.logInfo(result.getJSONObject(i).getString("class"));
//			Assert.assertNotNull(result.getJSONObject(i).getString("class"));
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
		List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, Constant.Sql.Get_WC_ID_Sql);
		String id=resultSet.get(1).get(0);
		String wc_id=ParamHelper.setParamByThreeWay(parameter, urlParameters, "wc_id", id);
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters,wc_id);
		return urlParameters;
	}

}
