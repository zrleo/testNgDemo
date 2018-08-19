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
import com.wanmei.mobile.iat.common.Cipher;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.NgaHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class WowRealmListSteps {
	@StepMethodDesc(description="wowRealmlist,nga interface 69",owner="Ruiyun.Ren")
	public void wowRealmList(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.WowRealmList, setParam(parameter));
        
		if(actual.getString("code").equals("0"))
        {
        	JSONArray result=actual.getJSONArray("result");
        	assertWowRealm(result);
        }

			
		}
	private void assertWowRealm(JSONArray result) throws Exception
	{
		List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, Constant.Sql.Get_NAME_Sql);
		for(int i=0;i<result.size();i++)
		{
			Assert.assertEquals(result.getJSONObject(i).getString("id"), resultSet.get(i+1).get(0), "id is not equals");
//			String name=NgaHelper.ReEncodeDataFromDB(resultSet, i+1, 1);
//			Assert.assertEquals(result.getJSONObject(i).getString("name"), name, "name is not equals");
		}
	}
	private LinkedHashMap<String, String> setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String,String>();
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters, "");
		return urlParameters;
	}
	

		


}
