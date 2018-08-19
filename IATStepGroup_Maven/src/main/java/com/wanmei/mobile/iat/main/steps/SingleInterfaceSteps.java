package com.wanmei.mobile.iat.main.steps;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.Assert;

import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.gateside.autotesting.Gat.dataobject.AssertStepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.wanmei.mobile.iat.common.CommonHelper;
import com.wanmei.mobile.iat.common.NgaLiveInterfaceHelper;



public class SingleInterfaceSteps 
{
	@StepMethodDesc(description="����Ҫ��¼�Ľӿڵ��ã�ֱ���ӿ�5��6��7��8��13��14",owner="yaxiao.yu")
	 public static JSONObject singleInterface(String parameterID) throws Exception 
		{
			InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
			String token = "";
			String uid = "";
	        LinkedHashMap<String, String> urlparametersLinkedHashMap=NgaLiveInterfaceHelper.setParametersForInterface(parameter,token,uid);
	        JSONObject result = NgaLiveInterfaceHelper.requestSender(parameter, urlparametersLinkedHashMap);
	       
	        return result;
		}

}
