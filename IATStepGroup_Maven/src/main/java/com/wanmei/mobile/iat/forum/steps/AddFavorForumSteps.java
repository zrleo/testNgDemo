package com.wanmei.mobile.iat.forum.steps;

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


public class AddFavorForumSteps {
	@StepMethodDesc(description="addFavorForum,nga interface 57",owner="Ruiyun.Ren")
	public void addFavorForum(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.AddFavorForum, setParam(parameter));
        if(!(actual.getString("code").equals("0")))
        {
        	Assert.assertEquals(parameter.getValue("code"), actual.getString("code"));
        }

			
		}

		private LinkedHashMap<String, String> setParam(InterfaceStepParameter parameter) throws Exception
		{
			LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String,String>();
			List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, Constant.Sql.Rand_Fid_From_Forums_Sql);
			String fid=ParamHelper.setParamByTwoWay(parameter, urlParameters,"fid", resultSet.get(1).get(0));
			RequestHelper.setNessesaryParamAndSign(parameter, urlParameters, fid);
			return urlParameters;
		}

}
