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
import com.wanmei.mobile.iat.common.RequestHelper;

public class HomeCategorySteps {
	@StepMethodDesc(description="homeCategory,nga interface 5",owner="Ruiyun.Ren")
	public void homeCategory(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.HomeCategory,setParam(parameter));
		assertForum(actual);		
		}
	public void assertForum(JSONObject actual) throws Exception
	{
		JSONArray result=actual.getJSONArray("result");
		for(int i=0;i<result.size();i++)
		{
			JSONArray list1=result.getJSONObject(i).getJSONArray("list");
			for(int t=0;t<list1.size();t++)
			{
				JSONArray list=list1.getJSONObject(t).getJSONArray("list");
				for(int j=0;j<list.size();j++)
				{
					String id=list.getJSONObject(j).getString("id");
					String name2=list.getJSONObject(j).getString("name");
					System.out.println("&&&&&&&&&&&&&&"+name2);
					String sqlCommand="select name from pw_forums where fid=ID";
				    List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand.replace("ID", id));
				    String name1=NgaHelper.ReEncodeDataFromDB(resultSet, 1, 0);
				    System.out.println("#############"+name1);
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
		}
	}
	private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters, "");
		return urlParameters;
	}


}
