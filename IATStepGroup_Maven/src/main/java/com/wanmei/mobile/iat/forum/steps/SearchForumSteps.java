package com.wanmei.mobile.iat.forum.steps;


import java.util.LinkedHashMap;
import java.util.List;



import org.testng.Assert;

import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RandomHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class SearchForumSteps {
	@StepMethodDesc(description="searchForum,nga interface 55",owner="Ruiyun.Ren")
	public void searchForum(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.ForumSearch,setParam(parameter));	
		if(!(actual.getString("code").equals("0")))
		{
			Assert.assertEquals(parameter.getValue("code"), actual.getString("code"));
		}
		}

		private LinkedHashMap<String, String> setParam(InterfaceStepParameter parameter) throws Exception
		{
			LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
			String key=ParamHelper.setParamExistOrValuePool(parameter, urlParameters, "key");
//			String page=ParamHelper.setParamByTwoWay(parameter, urlParameters, "page",String.valueOf(RandomHelper.getRandomNum(0, 10)));
		    String page=ParamHelper.setExistParam(parameter, urlParameters, "page");		
			RequestHelper.setNessesaryParamAndSign(parameter, urlParameters, key+page);
			return urlParameters;
		}

}
