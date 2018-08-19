package com.wanmei.mobile.iat.live.steps;




public class CheckBroadcastSteps {
//	@StepMethodDesc(description="check broadcasts,nga live interface 7",owner="yaxiao.yu")
//	public void checkBroadcast(String parameterID) throws Exception 
//	{
//		InterfaceStepParameter parameter = (InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
//		JSONObject result = SingleInterfaceSteps.singleInterface(parameterID);
//		NgaLiveInterfaceHelper.assertCodeAndMsg(result, parameter);
//		assertCountNum(parameter, result);		
//	}
//	
//	private void assertCountNum(InterfaceStepParameter parameter,JSONObject result) throws Exception
//	{
//		String cid = GetBroadcastsSteps.setCidForSqlCommand(parameter, ">");
//		String sqlCommand = parameter.getValue("countCidInfo").replace("TID", parameter.getValue("%tid")).replace("CID", cid);
//		List<List<String>> resultSet2 = DataBaseHelper.getResultFromDBBySqlCommand(sqlCommand, parameter, "dburl");
//
//		Assert.assertEquals(String.valueOf(resultSet2.get(1).get(0)), result.getJSONObject("result").getString("count"),"count is diff!!");
//	}
}
