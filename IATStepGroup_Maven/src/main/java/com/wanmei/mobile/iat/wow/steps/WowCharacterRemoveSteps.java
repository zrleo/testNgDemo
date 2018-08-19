package com.wanmei.mobile.iat.wow.steps;

import java.util.LinkedHashMap;

import net.sf.json.JSONObject;

import org.testng.Assert;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class WowCharacterRemoveSteps {
	@StepMethodDesc(description="wowCharacterRemove,nga interface 72",owner="Ruiyun.Ren")
	public void wowCharacterRemove(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.WowCharacterRemove, setParam(parameter));
        if(actual.getString("code").equals("0"))
        {
        	if(actual.getString("result").equals("0"))
        		SimpleLogger.logInfo("用户并没有添加过此角色");
        	else if(actual.getString("result").equals("1"))
        		SimpleLogger.logInfo("成功删除了1个角色");
        	
        }

			
		}
   

	private LinkedHashMap<String, String> setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String,String>();
		String wc_id=ParamHelper.setParamExistOrValuePool(parameter, urlParameters, "wc_id");
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters,wc_id);
		return urlParameters;
	}
	

}
