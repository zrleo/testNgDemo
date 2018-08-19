package com.wanmei.mobile.iat.post.steps;


import java.util.LinkedHashMap;
import java.util.List;



import net.sf.json.JSONObject;



import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.gateside.autotesting.Lib.httpclientService.HttpClientHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.NgaHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RandomHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class PostNewSteps {
//	@StepMethodDesc(description="check broadcasts,nga live interface 3",owner="yaxiao.yu")
//	public void postNew(String parameterID) throws Exception 
//	{
//		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
//		 JSONObject object = MultiInterfaceSteps.ngaForumMultiInterface(parameterID);
//	}
	@StepMethodDesc(description="postNew,nga interface 11",owner="Ruiyun.Ren")
	public void postNew(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender1(Constant.Url.PostNew,setParam(parameter),"","1");
		NgaHelper.assertCodeAndMsg(actual, parameter);
		Thread.sleep(20000);
		if(ParamHelper.isKeyExist(parameter, "attachments"))
		{
			System.out.println("************"+GlobalConfig.getRootDir()+parameter.getValue("attachments"));
			String url=StepValueHelper.getStepOutputValue("attach_url").toString();
			System.out.println("&&&&&&&&&&&&&"+url);
			HttpClientHelper.uploadFile(GlobalConfig.getRootDir()+parameter.getValue("attachments"), url, "Filedata");
		}

		
	
	}
	private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
		String sqlCommand="select fid from pw_threads order by rand() limit 1";
		List<List<String>>resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand);
		String fid1=resultSet.get(1).get(0);
		String fid=ParamHelper.setParamByThreeWay(parameter, urlParameters, "fid",fid1);
		String subject=ParamHelper.setParamByThreeWay(parameter, urlParameters, "subject",RandomHelper.getContent(10));
		String content=ParamHelper.setParamByThreeWay(parameter, urlParameters, "content",RandomHelper.getContent(25));
		if(StepValueHelper.getStepOutputValue("latitude")==null)
		{
			String address=ParamHelper.setExistParam(parameter, urlParameters, "address");
		}
		else {
			String latitude=StepValueHelper.getStepOutputValue("latitude").toString();
			String longitude=StepValueHelper.getStepOutputValue("longitude").toString();
			String name=StepValueHelper.getStepOutputValue("name").toString();
			String address1=latitude+","+longitude+","+name;
			String address=ParamHelper.setParamByThreeWay(parameter, urlParameters, "address",address1);
		}
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters,fid+subject+content);
		return urlParameters;
		}
	}
	
	

