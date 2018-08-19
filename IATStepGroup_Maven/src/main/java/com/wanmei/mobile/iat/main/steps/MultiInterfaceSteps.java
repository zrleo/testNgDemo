package com.wanmei.mobile.iat.main.steps;

import java.util.HashMap;
import java.util.LinkedHashMap;

import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.AssertStepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.gateside.autotesting.Gat.util.StepValuePool;
import com.wanmei.mobile.iat.common.CommonHelper;
import com.wanmei.mobile.iat.common.NgaForumRequestHelper;
import com.wanmei.mobile.iat.common.NgaLiveInterfaceHelper;


public class MultiInterfaceSteps {

	@StepMethodDesc(description="��Ҫ��¼�Ľӿڵ��ã�ֱ���ӿ�3��4��9��12",owner="yaxiao.yu")
	public static JSONObject multiInterface(String parameterID) throws Exception 
	{
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		String token = (String) StepValuePool.createInstance().getValueDic().get("token");
		String uid = (String) StepValuePool.createInstance().getValueDic().get("uid");
        LinkedHashMap<String, String> urlparametersLinkedHashMap = NgaLiveInterfaceHelper.setParametersForInterface(parameter,token,uid);
        if(urlparametersLinkedHashMap.containsKey("cid"))
        {
        	StepValuePool.createInstance().getValueDic().put("cid", urlparametersLinkedHashMap.get("cid"));
        }
        CommonHelper.printMap(urlparametersLinkedHashMap);
        JSONObject result = NgaLiveInterfaceHelper.requestSender(parameter, urlparametersLinkedHashMap);
	    return result;    
	}
	
	public static JSONObject multiInterfaceUploadAttachments(String parameterID) throws Exception 
	{
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		String token = (String) StepValuePool.createInstance().getValueDic().get("token");
		String uid = (String) StepValuePool.createInstance().getValueDic().get("uid");
        LinkedHashMap<String, String> urlparametersLinkedHashMap = NgaLiveInterfaceHelper.setParametersForInterface(parameter,token,uid);
        if(urlparametersLinkedHashMap.containsKey("cid"))
        {
        	StepValuePool.createInstance().getValueDic().put("cid", urlparametersLinkedHashMap.get("cid"));
        }
        JSONObject result = NgaLiveInterfaceHelper.requestSender(parameter, urlparametersLinkedHashMap);
	    return result;    
	}
	
	public static JSONObject ngaForumMultiInterface(String parameterID) throws Exception 
	{
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 String uid=(String) StepValueHelper.getStepOutputValue("uid");
		 String url=parameter.getValue("url");
		 HashMap<String, String> userParameterMap=NgaForumRequestHelper.setParameterMapForNgaForum(parameter);
	     String response=NgaForumRequestHelper.sendRequest(uid, url,userParameterMap,"0");
	     System.out.println(response);
	     JSONObject object = JSONObject.fromObject(response);
	     return object;
	}

}
