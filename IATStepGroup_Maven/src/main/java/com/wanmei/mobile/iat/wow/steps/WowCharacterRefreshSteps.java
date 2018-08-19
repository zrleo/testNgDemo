package com.wanmei.mobile.iat.wow.steps;

import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.testng.Assert;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class WowCharacterRefreshSteps {
	@StepMethodDesc(description="wowCharacterRefresh,nga interface 77",owner="Ruiyun.Ren")
	public void wowCharacterRefresh(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.WowCharacterRefresh, setParam(parameter));
        if(!(actual.getString("code").equals("0")))
        {
        	Assert.assertEquals(parameter.getValue("code"), actual.getString("code"));
        }

			
		}
   

	private LinkedHashMap<String, String> setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String,String>();
		List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, Constant.Sql.Get_WC_ID_Sql);
		String wc_id=ParamHelper.setParamByThreeWay(parameter, urlParameters, "wc_id", resultSet.get(1).get(0));
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters,wc_id);
		return urlParameters;
	}

}
