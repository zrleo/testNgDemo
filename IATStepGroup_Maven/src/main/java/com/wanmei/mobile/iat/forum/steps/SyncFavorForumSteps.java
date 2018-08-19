package com.wanmei.mobile.iat.forum.steps;

import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONObject;

import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.wanmei.mobile.iat.common.CommonHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.DateHelper;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RequestHelper;

public class SyncFavorForumSteps {
	@StepMethodDesc(description="syncFavorForum,nga interface 56",owner="Ruiyun.Ren")
	public void syncFavorForum(String parameterID)throws Exception{	
		InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		JSONObject actual=RequestHelper.requestSender(Constant.Url.SyncFavorForum,setParam(parameter));	

			
		}

		private LinkedHashMap<String, String>setParam(InterfaceStepParameter parameter) throws Exception
		{
			LinkedHashMap<String, String> urlParameters= new LinkedHashMap<String, String>();
			String sqlCommand="select fid from pw_forums ORDER BY RAND() LIMIT 1";
		    List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand);
			String fidlist=ParamHelper.setParamByTwoWay(parameter, urlParameters, "fidlist", resultSet.get(1).get(0));
			RequestHelper.setNessesaryParamAndSign(parameter, urlParameters, fidlist);
			return urlParameters;
		}
}
