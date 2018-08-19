package com.wanmei.mobile.iat.sdk.steps;

import java.nio.charset.Charset;
import java.util.LinkedHashMap;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;

import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.gateside.autotesting.Gat.util.StepValuePool;
import com.wanmei.mobile.iat.common.CommonHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.ParamHelper;
import com.wanmei.mobile.iat.common.RandomHelper;
import com.wanmei.mobile.iat.common.RequestHelper;


public class RegisterFor178 {

	private static final String APP_ID = "1051";
	private static final String APP_KEY = "de98247c02a3fcb26fa849d3e19bb1552104c222";
	private static final String AES_KEY = APP_KEY.substring(APP_KEY.length() - 16, APP_KEY.length());
	
	public static void register() throws Exception {
	    JSONObject actual = RequestHelper.requestSender(Constant.Url.REGISTER, setParam());
	    JSONObject result = actual.getJSONObject("result");
	    StepValuePool.createInstance().getValueDic().put("uid", result.getString("uid"));
	    System.out.println((String)StepValueHelper.getStepOutputValue("email"));
	    
	}

	private static LinkedHashMap<String, String> setParam() throws Exception
    {
		LinkedHashMap<String, String> urlParameters=new LinkedHashMap<String, String>();
		long time = System.currentTimeMillis() / 1000;
		String t = String.valueOf(time) + "";
		String password = Constant.PWD;
		String encryptedPwd = Base64.encodeBase64String(AESEncrypt(password,
				AES_KEY));
		String nickname = "test"+RandomHelper.getRandomString(6, Constant.RANDOM_VALUE);
		String email = nickname+"@test.com";
		String sign = CommonHelper.md5(APP_ID+email+nickname+encryptedPwd+t+APP_KEY);
		ParamHelper.setParameterToValuePool(urlParameters, "app_id", APP_ID);
		ParamHelper.setParameterToValuePool(urlParameters, "t", t);
		ParamHelper.setParameterToValuePool(urlParameters, "email", email);
		ParamHelper.setParameterToValuePool(urlParameters, "nickname", nickname);
		urlParameters.put("password", encryptedPwd);
		StepValuePool.createInstance().getValueDic().put("password", password);
		ParamHelper.setParameterToValuePool(urlParameters, "sign", sign);
		return urlParameters;
    }

	private static byte[] AESEncrypt(String data, String key) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		return cipher.doFinal(data.getBytes(Charset.forName("utf-8")));
	}
	
}
