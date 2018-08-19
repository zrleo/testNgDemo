package com.wanmei.mobile.iat.main.steps;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import net.sf.json.JSONObject;
import android.R.integer;

import com.gateside.autotesting.Gat.dataobject.AssertStepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValuePool;
import com.wanmei.mobile.iat.common.CommonHelper;
import com.wanmei.mobile.iat.common.NgaLiveInterfaceHelper;

public class DelCommentSteps {
	@StepMethodDesc(description="delete comments,nga live interface 12",owner="yaxiao.yu")
	public void delComment(String parameterID) throws Exception 
	{
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject result = MultiInterfaceSteps.multiInterface(parameterID);
		NgaLiveInterfaceHelper.assertCodeAndMsg(result, parameter);		
	}	
	
	@StepMethodDesc(description="delete comments,nga live interface 12",owner="yaxiao.yu")
	public void delCommentByStableCid(String parameterID) throws Exception 
	{
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject result = MultiInterfaceSteps.multiInterface(parameterID);
		NgaLiveInterfaceHelper.assertCodeAndMsg(result, parameter);		
	}	
}
