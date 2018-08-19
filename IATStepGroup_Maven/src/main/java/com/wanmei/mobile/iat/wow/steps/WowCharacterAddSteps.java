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

public class WowCharacterAddSteps {
	@StepMethodDesc(description="wowCharacterAdd,nga interface 71",owner="Ruiyun.Ren")
	public void wowCharacterAdd(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.WowCharacterAdd, setParam(parameter));
        if(actual.getString("code").equals("0"))
        {
        	if(actual.getString("result").equals("1"))
        	{
        		SimpleLogger.logInfo("成功添加了1个角色");
        	}
        	else if(actual.getString("result").equals("0")){
        		SimpleLogger.logInfo("用户已添加过此角色");
				
			}
        	else if (actual.getString("result").equals("-1")) {
				SimpleLogger.logInfo("角色关系添加成功，角色数据请求已加入爬虫队列");
			}
        }

			
		}
   

	private LinkedHashMap<String, String> setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String,String>();
		String realm_name=ParamHelper.setParamExistOrValuePool(parameter, urlParameters, "realm_name");
		String character_name=ParamHelper.setParamExistOrValuePool(parameter, urlParameters, "character_name");
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters,realm_name+character_name);
		return urlParameters;
	}


}
