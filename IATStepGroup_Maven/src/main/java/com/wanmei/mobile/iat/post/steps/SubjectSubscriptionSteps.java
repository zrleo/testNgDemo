package com.wanmei.mobile.iat.post.steps;

import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.testng.Assert;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import android.R.string;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.gateside.autotesting.Gat.util.StepValuePool;
import com.wanmei.mobile.iat.common.CommonHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.NgaHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class SubjectSubscriptionSteps {
	@StepMethodDesc(description="subjectList,nga interface 54",owner="Ruiyun.Ren")
	public void subjectSubscription(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.SubjectSubscription,setParam(parameter));
		NgaHelper.assertCodeAndMsg(actual, parameter);
		System.out.println("****************"+actual.getString("msg"));
		
	
	}

	private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
		String ufid=ParamHelper.setParamByThreeWay(parameter, urlParameters, "ufid","");
		String fid=ParamHelper.setParamByThreeWay(parameter, urlParameters, "fid","");
		String type=ParamHelper.setParamByThreeWay(parameter, urlParameters, "type","");
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters,ufid+fid+type);
		return urlParameters;
		}
//	@StepMethodDesc(description="subjectSubscription",owner="Ruiyun.Ren")
//	public void subjectSubscription(String parameterID)throws Exception{	
//		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
//		JSONObject actual=NgaHelper.requestSender(Constant.Url.SubjectSubscription, Constant.HTTP_METHOD, setParam(parameter));	
//        if(parameter.getValue("code")=="1")
//        {
//        	Assert.assertEquals(actual.getString("code"), parameter.getValue("code"));
//        	Assert.assertEquals(actual.getString("msg"), parameter.getValue("msg"));
//        }
//			
//		}
//
//		private TreeMap<String, String> setParam(InterfaceStepParameter parameter) throws Exception
//		{
//			TreeMap<String, String> urlparameters= new TreeMap<String,String>();
//			String sign = "";		
//			urlparameters.put("app_id", GlobalConfig.app_id);
//			sign = sign + GlobalConfig.app_id;	
//			if(StepValueHelper.getStepOutputValue("uid")!=null)
//			{
//				String uid=(String)StepValueHelper.getStepOutputValue("uid");
//				urlparameters.put("access_uid",uid);
//				sign=sign+uid;
//			}
//			if(StepValueHelper.getStepOutputValue("token")!=null)
//			{
//				String access_token=(String)StepValueHelper.getStepOutputValue("token");
//				urlparameters.put("access_token",access_token);
//				sign=sign+access_token;
//			}
//	        urlparameters.put("ufid", parameter.getValue("ufid"));
//			sign=sign+parameter.getValue("ufid");
//			urlparameters.put("fid", parameter.getValue("fid"));
//			sign=sign+parameter.getValue("fid");
//			urlparameters.put("type", parameter.getValue("type"));
//			sign=sign+parameter.getValue("type");
//			String t = CommonHelper.getTimeStampSec();
//	        urlparameters.put("t", t);       
//			urlparameters.put("sign", CommonHelper.md5(sign+t+GlobalConfig.appkey));
//			return urlparameters;
//		}

}
