package com.wanmei.mobile.iat.main.steps;

import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.AssertStepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;

public class GetCommentsSteps {
	@StepMethodDesc(description="get comments,nga live interface 6",owner="yaxiao.yu")
	 public void getComments(String parameterID) throws Exception 
	{
		JSONObject result = SingleInterfaceSteps.singleInterface(parameterID);
	}
}
