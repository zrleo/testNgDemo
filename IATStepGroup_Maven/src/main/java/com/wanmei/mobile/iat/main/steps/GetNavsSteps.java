package com.wanmei.mobile.iat.main.steps;

import java.util.List;

import org.testng.Assert;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.AssertStepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.wanmei.mobile.iat.common.DataBaseHelper;


public class GetNavsSteps {
//	@StepMethodDesc(description="get navs,nga live interface 13",owner="yaxiao.yu")
//	public void getNavs(String parameterID) throws Exception 
//	{
//		InterfaceStepParameter parameter = (InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
//		JSONObject result = SingleInterfaceSteps.singleInterface(parameterID);
//		NgaLiveInterfaceHelper.assertCodeAndMsg(result, parameter);
//		assertNavsList(parameter, result);
//	}
//	
//	private void assertNavsList(InterfaceStepParameter parameter,JSONObject result) throws Exception 
//	{
//		String sqlCommand = parameter.getValue("gavInfo").replace("FID", parameter.getValue("%fid"));
//		List<List<String>> resultSet = DataBaseHelper.getResultFromDBBySqlCommand(sqlCommand, parameter, "dburl");
//		JSONArray navArray = result.getJSONObject("result").getJSONArray("navs");
//        if(navArray.size()==0)
//        {
//        	Assert.assertEquals(String.valueOf(resultSet.size()-1), "0","size is diff!!");
//        }
//        else 
//        {
//        	for (int i = 1; i < resultSet.size(); i++) 
//        	{
//        		assertSingleNavInfo(navArray, parameter, resultSet, i);
//			}        	
//		}		
//	}
//	
//	private void assertSingleNavInfo(JSONArray navArray,InterfaceStepParameter parameter,List<List<String>> resultSet,int i) throws Exception
//	{
//		JSONObject object = navArray.getJSONObject(i-1);
//		Nav nav = new Nav(object);
//		
//		Assert.assertEquals(String.valueOf(resultSet.get(i).get(0)), nav.id,"id is diff!!");
//		Assert.assertEquals(String.valueOf(resultSet.get(i).get(1)), nav.title,"title is diff!!");
//		Assert.assertEquals(String.valueOf(resultSet.get(i).get(2)), nav.type,"type is diff!!");
//		Assert.assertEquals(String.valueOf(resultSet.get(i).get(3)), nav.time,"time is diff!!");
//		assertTidFidOrUrlInfo(nav, parameter, resultSet, i);
//	}
//	
//	private void assertTidFidOrUrlInfo(Nav nav,InterfaceStepParameter parameter,List<List<String>> resultSet,int i) throws Exception
//	{
//		if(nav.type.equals("1"))
//		{
//			assertTidAndLive(nav, parameter, resultSet, i);     		
//		}
//		else if(nav.type.equals("0"))
//		{
//			Assert.assertEquals(String.valueOf(resultSet.get(i).get(4)), nav.fid,"fid is diff!!");
//    		Assert.assertEquals(String.valueOf(resultSet.get(i).get(5)), nav.recommend,"recommend is diff!!");
//		} 
//		else 
//		{
//			Assert.assertEquals(String.valueOf(resultSet.get(i).get(7)), nav.url,"url is diff!!");
//		}
//	}
//	
//	private void assertTidAndLive(Nav nav,InterfaceStepParameter parameter,List<List<String>> resultSet,int i) throws Exception
//	{
//		String sqlCommand2 = parameter.getValue("tidInfo").replace("TID", nav.tid);
//		List<List<String>> resultSet2 = DataBaseHelper.getResultFromDBBySqlCommand(sqlCommand2, parameter, "dburl");
//		Assert.assertEquals(String.valueOf(resultSet.get(i).get(6)), nav.tid,"tid is diff!!");
//		if(resultSet2.size()==1)
//		{
//			Assert.assertEquals("0", nav.live,"live is diff!!");
//		}
//		else if(resultSet2.size()==2)
//		{
//			Assert.assertEquals("1", nav.live,"live is diff!!");
//		}    
//	}
//	
//	public class Nav
//	{
//		String id = "";
//		String title = "";
//		String type = "";
//		String time = "";
//		String fid = "";
//		String recommend = "";
//		String tid = "";
//		String live = "";
//		String url = "";
//		public Nav(JSONObject object)
//		{
//			id = object.getString("id");
//			title = object.getString("title");
//			type = object.getString("type");
//			time = object.getString("time");
//			if(object.containsKey("tid"))
//			{
//				tid = object.getString("tid");
//				live = object.getString("live");
//			}
//			else if(object.containsKey("fid"))
//			{
//				fid = object.getString("fid");
//				recommend = object.getString("recommend");
//			}	
//			else 
//			{
//				url = object.getString("url");
//			}
//		}
//	}
}
