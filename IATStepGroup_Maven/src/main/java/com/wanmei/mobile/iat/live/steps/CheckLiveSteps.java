package com.wanmei.mobile.iat.live.steps;

import org.testng.Assert;

import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.AssertStepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.wanmei.mobile.iat.common.CommonHelper;


public class CheckLiveSteps {
//	@StepMethodDesc(description="check live,nga live interface 14",owner="yaxiao.yu")
//	 public void checkLive(String parameterID) throws Exception 
//	{		
//		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
//		JSONObject result = SingleInterfaceSteps.singleInterface(parameterID);
//		assertCheckLive(result, parameter);
//	}
//	
//	private void assertCheckLive(JSONObject result,InterfaceStepParameter parameter)
//	{
//		NgaLiveInterfaceHelper.assertCodeAndMsg(result, parameter);
//		Assert.assertEquals(result.getJSONObject("result").getString("live"), parameter.getValue("live"),"live is diff");
//	}
}
