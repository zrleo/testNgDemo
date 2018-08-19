package com.wanmei.mobile.iat.message.steps;

import java.util.LinkedHashMap;
import java.util.List;

import org.testng.Assert;

import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class DelBlockSteps {
	@StepMethodDesc(description="delBlock,nga interface 27",owner="Ruiyun.Ren")
	public void delBlock(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		String sqlCommand="select p.from from pw_message_block p join pw_members m on m.uid=p.from where p.to=26228182 ORDER BY p.id ";
		List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.DelBlock,setParam(parameter));	
		String sqlCommand1="select p.from from pw_message_block p join pw_members m on m.uid=p.from where p.to=26228182 ORDER BY p.id ";
		List<List<String>> resultSet1=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand1);
		if(actual.getString("code").equals("0"))
		{
			Assert.assertEquals(resultSet1.size(), resultSet.size()-1);
			
		}
		Thread.sleep(5000);
			
		}
	@StepMethodDesc(description="delBlock",owner="Ruiyun.Ren")
	public void delNotExistAndSameBlock(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		String sqlCommand="select p.from from pw_message_block p join pw_members m on m.uid=p.from where p.to=26228182 ORDER BY p.id ";
		List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.DelBlock,setParam(parameter));	
		String sqlCommand1="select p.from from pw_message_block p join pw_members m on m.uid=p.from where p.to=26228182 ORDER BY p.id ";
		List<List<String>> resultSet1=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand1);
		if(actual.getString("code").equals("0"))
		{
			Assert.assertEquals(resultSet1.size(), resultSet.size());
			
		}
		Thread.sleep(5000);
			
		}

		private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
		{
			LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
		    String uid=ParamHelper.setParamExistOrValuePool(parameter, urlParameters, "uid");
			RequestHelper.setNessesaryParamAndSign(parameter, urlParameters, uid);
			return urlParameters;
		}

}
