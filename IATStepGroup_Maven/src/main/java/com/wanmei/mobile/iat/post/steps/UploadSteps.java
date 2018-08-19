package com.wanmei.mobile.iat.post.steps;

import java.util.LinkedHashMap;
import java.util.List;

import org.testng.Assert;

import net.sf.json.JSONObject;


import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValuePool;
import com.wanmei.mobile.iat.common.CommonHelper;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.NgaLiveInterfaceHelper;


public class UploadSteps {
	@StepMethodDesc(description="upload attachments,nga live interface 9",owner="yaxiao.yu")
	public void upload(String parameterID) throws Exception 
	{
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		List<List<String>> resultSet = DataBaseHelper.getResultFromDBBySqlCommand(parameter.getValue("aidInfo"), parameter, "dburl");

		JSONObject object = uploadAttachment(parameter);	
		StepValuePool.createInstance().getValueDic().put("aid", object.getJSONObject("result").getString("aid"));	
		List<List<String>> resultSet2 = DataBaseHelper.getResultFromDBBySqlCommand(parameter.getValue("aidInfo"), parameter, "dburl");

		assertAidInfo(object, parameter,resultSet,resultSet2);
		assertDataStoredInDatabase(parameter,resultSet, resultSet2);					
	}
	
	public JSONObject uploadAttachment(InterfaceStepParameter parameter) throws Exception
	{
		String token = (String) StepValuePool.createInstance().getValueDic().get("token");
		String uid = (String) StepValuePool.createInstance().getValueDic().get("uid");
		LinkedHashMap< String, String> params = NgaLiveInterfaceHelper.setParametersForInterface(parameter, token, uid);
		System.out.println(parameter.getValue("url")+"?");
		CommonHelper.printMap(params);
		String result = NgaLiveInterfaceHelper.updateLivePic(parameter.getValue("Filedata")+parameter.getValue("picName"),parameter.getValue("picName"),parameter.getValue("url"), params.get("tid"), uid, token, params.get("app_id"), params.get("t"), params.get("sign"));
		System.out.println(result);	
		
		JSONObject object = JSONObject.fromObject(result);	
		return object;
	}
	
	private void assertDataStoredInDatabase(InterfaceStepParameter parameter,List<List<String>> resultSet,List<List<String>> resultSet2) throws Exception
	{
		Assert.assertEquals(resultSet2.get(1).get(0),String.valueOf(Integer.valueOf(resultSet.get(1).get(0))+1),"aid is diff!!!");
		Assert.assertEquals(resultSet2.get(1).get(4),StepValuePool.createInstance().getValueDic().get("uid"),"uid is diff!!!");
	    
		List<List<String>> resultSet3 = DataBaseHelper.getResultFromDBBySqlCommand(parameter.getValue("aidInfo"), parameter, "dburl");
		String picName = parameter.getValue("picName");
		String picType = "image/"+picName.substring(picName.indexOf(".")+1).toLowerCase();
		if(picType.equals("image/jpg"))
			picType = "image/jpeg";
		Assert.assertEquals(picType, resultSet3.get(1).get(6),"picture type stored in database live_attachment is diff!!");
	}
	@StepMethodDesc(description="upload attachments not support bmp format",owner="yaxiao.yu")
	public void uploadNotSupportImage(String parameterID) throws Exception 
	{
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		String token = (String) StepValuePool.createInstance().getValueDic().get("token");
		String uid = (String) StepValuePool.createInstance().getValueDic().get("uid");
		LinkedHashMap< String, String> params = NgaLiveInterfaceHelper.setParametersForInterface(parameter, token, uid);
		System.out.println(parameter.getValue("Filedata")+parameter.getValue("picName"));
		String result = NgaLiveInterfaceHelper.updateLivePic(parameter.getValue("Filedata")+parameter.getValue("picName"),parameter.getValue("picName"),parameter.getValue("url"), params.get("tid"), uid, token, params.get("app_id"), params.get("t"), params.get("sign"));
		System.out.println(result);
		JSONObject object = JSONObject.fromObject(result);
		NgaLiveInterfaceHelper.assertCodeAndMsg(object, parameter);
	}
	
	private void assertAidInfo(JSONObject object,InterfaceStepParameter parameter,List<List<String>> resultSet,List<List<String>> resultSet2)
	{
		NgaLiveInterfaceHelper.assertCodeAndMsg(object, parameter);
		JSONObject result = object.getJSONObject("result");
		
		if(result.getString("url").contains("index"))
		{
			Assert.assertEquals(result.getString("url").replace("\\", "").replace("index", "thumb"), result.getString("thumb").replace("\\", ""),"url and thumb not match!!!");
		}
		Assert.assertEquals(result.getString("aid"),String.valueOf(Integer.valueOf(resultSet.get(1).get(0))+1),"aid is diff!!!");
	
	}
}
