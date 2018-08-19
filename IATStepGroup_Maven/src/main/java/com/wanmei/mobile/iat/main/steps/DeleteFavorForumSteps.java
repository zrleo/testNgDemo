package com.wanmei.mobile.iat.main.steps;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;









import net.sf.json.JSONObject;










import com.gateside.autotesting.Gat.dataobject.StepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.wanmei.mobile.iat.common.CommonHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.DateHelper;
import com.wanmei.mobile.iat.common.GlobalConfig;
import com.wanmei.mobile.iat.common.NgaHelper;
import com.wanmei.mobile.iat.common.RequestHelper;


public class DeleteFavorForumSteps {
	@StepMethodDesc(description="deleteFavorForum",owner="Ruiyun.Ren")
	public void deleteFavorForum(String parameterID)throws Exception{	
	InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
	JSONObject actual=RequestHelper.requestSender(Constant.Url.DeleteFavorForum,setParam(parameter));	
//	if(actual.getString("code").equals("0"))
//	{
//		JSONObject result = actual.getJSONObject("result");
//		Assert.assertNotNull(result.getString("last_id"));
//		JSONArray giftList = result.getJSONArray("gift_list");
//		for (int i = 0; i < giftList.size()-1; i++) {
//			JSONObject gift = giftList.getJSONObject(i);
//			Assert.assertNotNull(gift.get("gift_left"));
//			Assert.assertNotNull(gift.get("gift_id"));
//			Assert.assertNotNull(gift.get("gift_name"));
////			Assert.assertNotNull(gift.get("gift_msg"));
//			Assert.assertNotNull(gift.get("gift_type"));
//			Assert.assertNotNull(gift.get("gift_state"));
//			Assert.assertNotNull(gift.get("gift_date"));
//			Assert.assertNotNull(gift.get("gift_avatar"));
//		}
//	}
		
	}

	private LinkedHashMap<String, String> setParam(InterfaceStepParameter parameter) throws Exception
	{
		LinkedHashMap<String, String> urlparameters= new LinkedHashMap<String, String>();
		String sign = "";		
		urlparameters.put("app_id", GlobalConfig.app_id);
		sign = sign + GlobalConfig.app_id;	
		if(StepValueHelper.getStepOutputValue("fid")!=null)
		{
			String sqlCommand="select fid from pw_forums ORDER BY RAND() LIMIT 1";
			List<List<String>> resultSet=DataBaseHelper.getResult(Constant.DB_URL_KA, sqlCommand);
			String fid=resultSet.get(1).get(0);
			urlparameters.put("fid",fid);
			sign = sign +fid;
		}				
		String t =DateHelper.getTimeStampSec();
        urlparameters.put("t", t);       
		urlparameters.put("sign", CommonHelper.md5(sign+t+GlobalConfig.appkey));
		return urlparameters;
	}

}
