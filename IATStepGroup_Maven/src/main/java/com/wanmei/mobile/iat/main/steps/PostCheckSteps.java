package com.wanmei.mobile.iat.main.steps;

import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValuePool;

public class PostCheckSteps {
	@StepMethodDesc(description="check broadcasts,nga live interface 3",owner="yaxiao.yu")
	public void postCheck(String parameterID) throws Exception 
	{
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		/* String userid=(String) StepValueHelper.getStepOutputValue("uid");
		 String url=parameter.getValue("url");
		 HashMap<String, String> userParameterMap=NgaForumRequestHelper.setUserParameterForNgaForum(parameter);
	     String response=NgaForumRequestHelper.sendRequest(userid, url,userParameterMap,"0");
	     System.out.println(response);*/
		 JSONObject object = MultiInterfaceSteps.ngaForumMultiInterface(parameterID);
		 StepValuePool.createInstance().getValueDic().put("fid", object.getJSONObject("result").getString("fid"));
	}
}
