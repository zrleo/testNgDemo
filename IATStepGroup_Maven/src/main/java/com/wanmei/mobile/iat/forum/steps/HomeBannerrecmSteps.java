package com.wanmei.mobile.iat.forum.steps;

import java.util.LinkedHashMap;
import java.util.List;

import org.testng.Assert;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.NgaHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class HomeBannerrecmSteps {
	@StepMethodDesc(description="homeBannerrecm,nga interface 2",owner="Ruiyun.Ren")
	public void homeBannerrecm(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.HomeBannerrecm,setParam(parameter));
		NgaHelper.assertCodeAndMsg(actual, parameter);
		assertParam(actual);
	
	}
	public void assertParam(JSONObject actual) throws Exception
	{
		JSONArray categories=actual.getJSONObject("result").getJSONArray("categories");
		JSONArray banners=actual.getJSONObject("result").getJSONArray("banners");
		for(int j=0;j<banners.size();j++)
		{
			String tid=banners.getJSONObject(j).getString("tid");
			String sqlCommand="select author,subject,postdate,replies from pw_threads where tid=TID";
		    List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand.replace("TID", tid));
		    String author=NgaHelper.ReEncodeDataFromDB(resultSet, 1, 0);
		    String subject1=NgaHelper.ReEncodeDataFromDB(resultSet, 1, 1);
		    Assert.assertEquals(banners.getJSONObject(j).getString("author"), author);
		    if(subject1.contains("[]"))
		    {
		    	String subject=subject1.substring(subject1.indexOf("]")+1,subject1.length());
		    	Assert.assertEquals(banners.getJSONObject(j).getString("subject"),subject);
		    }
		    Assert.assertEquals(banners.getJSONObject(j).getString("postdate"), resultSet.get(1).get(2));
		    Assert.assertEquals(banners.getJSONObject(j).getString("replies"), resultSet.get(1).get(3));
			
		}
		
		for(int i=0;i<categories.size();i++)
		{
			String id=categories.getJSONObject(i).getString("id");
			String sqlCommand="select fid,name from pw_forums where fid=ID";
		    List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand.replace("ID", id));
		    String name1=NgaHelper.ReEncodeDataFromDB(resultSet, 1, 1);
		    String name2=categories.getJSONObject(i).getString("name");
		    if(name1.contains(name2))
		    {
		    	Assert.assertTrue(true, "name1 equals(name2)");
		    }
		    else if(name1.equals("Diablo3 讨论区"))
		    {
		    	String name="暗黑破坏神3";
			    Assert.assertEquals(name2,name);
		    }
		}
	}
	private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters, "");
		return urlParameters;
	}

}
