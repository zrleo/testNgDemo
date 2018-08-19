package com.wanmei.mobile.iat.common;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.xml.sax.SAXException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;







import android.R.integer;
import android.R.string;

import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.gateside.autotesting.Gat.util.StepValuePool;
import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.gateside.autotesting.Lib.dbService.MysqlDBOperationService;
import com.gateside.autotesting.Lib.httpunitService.HttpUnitHelper;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;


public class NgaHelper {
	 /**
	 * 判定parameter 中是否存在指定的key
	 * @param parameter
	 * @param key
	 * @return
	 */
 public static boolean containedSpecifiedKey(InterfaceStepParameter parameter,String key)
 {
	boolean flag=false;
	for(int i=0;i<parameter.parameters.size();i++)
	{
		if(parameter.parameters.get(i).key.equals(key))
		{
			flag=true;
			break;
		}
	}
	return flag;
 }

 public static void assertCodeAndMsgObjecResult(JSONObject actual,InterfaceStepParameter parameter,String par,String param)
 {
	 if(containedSpecifiedKey(parameter, "code"))
	 {
		 Assert.assertEquals(actual.getString("code"),(parameter.getValue("code")));
		 Assert.assertEquals(actual.getString("msg"),(parameter.getValue("msg")));
	 }
	 else
	 {
		 JSONObject result=actual.getJSONObject("result");
		 String para=result.getString(param);
         StepValueHelper.putStepOutputValue(par, para);
      }
 }
 public static void assertCodeAndMsgArrayResult(JSONObject actual,InterfaceStepParameter parameter,String par,int i,String param)
 {
	 if(containedSpecifiedKey(parameter, "code"))
	 {
		 Assert.assertEquals(actual.getString("code"),(parameter.getValue("code")));
		 Assert.assertEquals(actual.getString("msg"),(parameter.getValue("msg")));
	 }
	 else
	 {
		 JSONArray result=actual.getJSONArray("result");
		 String para=result.getJSONObject(i).getString(param);
         StepValueHelper.putStepOutputValue(par, para);
      }
 }
 public static void assertCodeAndMsg(JSONObject actual,InterfaceStepParameter parameter)
 {
	 if(containedSpecifiedKey(parameter, "code"))
	 {
		 Assert.assertEquals(actual.getString("code"),(parameter.getValue("code")),"code is diff");
		 Assert.assertEquals(actual.getString("msg"),(parameter.getValue("msg")),"msg is diff");
	 }
	 
 }
 public static String ReEncodeDataFromDB(List<List<String>> resultSet,int row, int colum) throws Exception
	{
		byte[] b2=resultSet.get(row).get(colum).getBytes("ISO-8859-1");
		String name=new String(b2,"GBK");
		return name;
	}

}


