package com.wanmei.mobile.iat.main.steps;

import java.util.HashMap;
import java.util.LinkedHashMap;

import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;

public class PostNewSteps {
	@StepMethodDesc(description="check broadcasts,nga live interface 3",owner="yaxiao.yu")
	public void postNew(String parameterID) throws Exception 
	{
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 JSONObject object = MultiInterfaceSteps.ngaForumMultiInterface(parameterID);
	}
	
	
}
