package com.wanmei.mobile.iat.message.steps;

import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.NgaHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class BlackStoreExchangeSteps {
	@StepMethodDesc(description="blackStoreExchange,nga interface 45",owner="Ruiyun.Ren")
	public void blackStoreExchange(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.BlackStoreExchange,setParam(parameter));	
		NgaHelper.assertCodeAndMsg(actual, parameter);
			
		}

		private LinkedHashMap<String, String> setParam(InterfaceStepParameter parameter) throws Exception
		{
			LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
			List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, Constant.Sql.Rand_OrderSn_From_Order_Sql);
			String order_sn=ParamHelper.setParamByThreeWay(parameter, urlParameters, "order_sn", resultSet.get(1).get(0));
			RequestHelper.setNessesaryParamAndSign(parameter, urlParameters, order_sn);
			return urlParameters;
		}

}
