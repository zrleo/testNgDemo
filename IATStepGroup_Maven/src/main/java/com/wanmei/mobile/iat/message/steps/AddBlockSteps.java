package com.wanmei.mobile.iat.message.steps;

import java.util.LinkedHashMap;
import java.util.List;

import org.testng.Assert;

import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.NgaHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class AddBlockSteps {
	@StepMethodDesc(description="addBlock,nga interface 26",owner="Ruiyun.Ren")
	public void addBlock(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.AddBlock,setParam(parameter));
		NgaHelper.assertCodeAndMsg(actual, parameter);
		if(actual.getString("code").equals("0"))
		{
			assertAddBlockMember(actual);
		}

       
			
		}
	public void assertAddBlockMember(JSONObject actual) throws Exception
	{
		List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, Constant.Sql.Block_Member_From_Block_Sql);
        String uid=resultSet.get(resultSet.size()-1).get(0);
        String actualUid=(String)StepValueHelper.getStepOutputValue("uid");
        Assert.assertEquals(actualUid,uid);
    }
	private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
	    List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, Constant.Sql.Rand_Uid_From_Members_Sql);
		String uid=ParamHelper.setParamByTwoWay(parameter, urlParameters, "uid", resultSet.get(1).get(0));
		RequestHelper.setNessesaryParamAndSign(parameter, urlParameters, uid);
		return urlParameters;
	}


		
}
