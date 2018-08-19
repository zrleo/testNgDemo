package com.wanmei.mobile.iat.forum.steps;

import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.testng.Assert;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.NgaHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class HomeRecmthreadsSteps {
	@StepMethodDesc(description="homeRecmthreads,nga interface 3",owner="Ruiyun.Ren")
	public void homeRecmthreads(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.HomeRecmthreads,setParam(parameter));
		NgaHelper.assertCodeAndMsg(actual, parameter);
		assertParam(actual);
	
	}
	public void assertParam(JSONObject actual) throws Exception
	{
		JSONArray result=actual.getJSONArray("result");
		for(int i=0;i<result.size();i++)
		{
			String tid=result.getJSONObject(i).getString("tid");
			String sqlCommand="select author,subject,postdate,replies from pw_threads where tid=TID";
		    List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand.replace("TID", tid));
		    String author=NgaHelper.ReEncodeDataFromDB(resultSet, 1, 0);
		    String subject1=NgaHelper.ReEncodeDataFromDB(resultSet, 1, 1);
		    Assert.assertEquals(result.getJSONObject(i).getString("author"), author);
		    if(subject1.contains("[]"))
		    {
		    	String subject=subject1.substring(subject1.indexOf("]")+1,subject1.length());
		    	Assert.assertEquals(result.getJSONObject(i).getString("subject"),subject);
		    }
		    Assert.assertEquals(result.getJSONObject(i).getString("postdate"), resultSet.get(1).get(2));
		    Assert.assertEquals(result.getJSONObject(i).getString("replies"), resultSet.get(1).get(3));
			
		}
		
	}
	private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
		
		String fidString=ParamHelper.setParamByTwoWay(parameter, urlParameters, "fidString", "");
		String page=ParamHelper.setParamByTwoWay(parameter, urlParameters, "page", "");
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters, fidString+page);
		return urlParameters;
	}

}
