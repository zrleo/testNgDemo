package com.wanmei.mobile.iat.common;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.Assert;
import org.xml.sax.SAXException;

import android.R.integer;




import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.StepValuePool;
import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.gateside.autotesting.Lib.dbService.MysqlDBOperationService;
import com.gateside.autotesting.Lib.httpunitService.HttpUnitHelper;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
/**
 * the xml file must contain the keys as follows:url,httpMethod,excelPath,sheetName,row
 * @author yaxiao.yu
 * @parameters url:interface url;excelPath:interface document that stored 
 */
public class NgaLiveInterfaceHelper {
	
	public static JSONObject requestSender(InterfaceStepParameter parameter,LinkedHashMap<String, String> urlparametersLinkedHashMap) throws IOException, SAXException, InterruptedException
	{
		if(parameter.getValue("url").contains("post"))
		{
			if(!ParamHelper.isKeyExist(parameter, "DifferentPeoplePostOrReply"))
		        Thread.sleep(15000);
		}
		
		WebConversation currentConversation=HttpUnitHelper.createConversation();
		
		WebRequest currentRequest=HttpUnitHelper.createWebRequest(parameter.getValue("url") , parameter.getValue("httpMethod"));

		appendParametersToRequestUrl(urlparametersLinkedHashMap, currentRequest);		        
		WebResponse response = currentConversation.getResponse(currentRequest);	
		Object object = response.getText();
		JSONObject result = JSONObject.fromObject(object);
	    SimpleLogger.logInfo(JSONObject.fromObject(object).toString());
		return result;
	}
	
	private static void appendParametersToRequestUrl(LinkedHashMap<String, String> urlparametersLinkedHashMap,WebRequest currentRequest)
	{
		Set keySet = urlparametersLinkedHashMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        while(iterator.hasNext())
        {
        	String nextKey=iterator.next();
        	currentRequest.setParameter(nextKey, urlparametersLinkedHashMap.get(nextKey));
        }
	}
	
	public static LinkedHashMap<String, String> setParametersForInterface(InterfaceStepParameter parameter,String token,String uid) throws Exception
	{
		LinkedHashMap<String, String> urlparametersLinkedHashMap=new LinkedHashMap<String, String>();
		String sign = "";
		for(int i=0;i<parameter.parameters.size();i++)
		{
			sign = selectParametersFromXmlForInterface(parameter, sign, i, urlparametersLinkedHashMap, uid, token);
		}
        sign = setParametersMapAndSign(urlparametersLinkedHashMap, "t", DateHelper.getTimeStampSec(), sign);
		urlparametersLinkedHashMap.put("sign", CommonHelper.md5(sign+GlobalConfig.appkey));
//        urlparametersLinkedHashMap.put("sign", Md5Util.md5(sign+GlobalConfig.appkey));

		return urlparametersLinkedHashMap;
	}
	
	public static String selectParametersFromXmlForInterface(InterfaceStepParameter parameter,String sign,int index,LinkedHashMap<String, String> urlparametersLinkedHashMap,String uid,String token) throws Exception
	{
		if(parameter.parameters.get(index).key.startsWith("%"))
		 {
			 String key = parameter.parameters.get(index).key;
			 if(key.equals("%title"))				 	
				 sign = setParametersMapAndSign(urlparametersLinkedHashMap, "title", generateTitleOrContent(10, key, parameter), sign);				 
			 else if(key.equals("%content"))
				 sign = setParametersMapAndSign(urlparametersLinkedHashMap, "content", generateTitleOrContent(40, key, parameter), sign);
			 else if(key.equals("%uid"))
				 sign = setParametersMapAndSign(urlparametersLinkedHashMap, "uid", uid, sign);
			 else if(key.equals("%token"))
				 sign = setParametersMapAndSign(urlparametersLinkedHashMap, "token", token, sign);
			 else if(key.equals("%app_id"))
				 sign = setParametersMapAndSign(urlparametersLinkedHashMap, "app_id", GlobalConfig.app_id, sign);
			 else if(key.equals("%cid"))
				 sign = setDifferentCidForDifferentInterface(urlparametersLinkedHashMap, sign, uid, parameter);
			 else if(key.equals("%aid"))
				 sign = setParametersMapAndSign(urlparametersLinkedHashMap, "aid", (String)StepValuePool.createInstance().getValueDic().get("aid"), sign);
			 else if(key.equals("%replycid"))
			 {
				 if(ParamHelper.isKeyExist(parameter, "replyJustDeleted"))
					 sign = setParametersMapAndSign(urlparametersLinkedHashMap, "replycid", (String)StepValuePool.createInstance().getValueDic().get("cid"), sign);
				 else {
					 sign = setParametersMapAndSign(urlparametersLinkedHashMap, "replycid", parameter.getValue("%replycid"), sign);
				}
			 }
			 else 
				 sign = setParametersMapAndSign(urlparametersLinkedHashMap, key.substring(1), parameter.getValue(key), sign);
		 }
		return sign;
	}
	
	public static String setDifferentCidForDifferentInterface(LinkedHashMap<String, String> urlparametersLinkedHashMap,String sign,String uid,InterfaceStepParameter parameter) throws Exception
	{
		 if(parameter.getValue("%cid").isEmpty()||parameter.getValue("%cid").equals(""))
		 {
			 if(ParamHelper.isKeyExist(parameter, "deleteTwice"))
				 sign = setParametersMapAndSign(urlparametersLinkedHashMap, "cid", (String) StepValuePool.createInstance().getValueDic().get("cid"), sign);
			 else if (ParamHelper.isKeyExist(parameter, "getCommentOrBroadcasts")) 
				 sign = setParametersMapAndSign(urlparametersLinkedHashMap, "cid", parameter.getValue("%cid"), sign);
			 else 
				 sign = selectRandomCidFromDatabase(urlparametersLinkedHashMap, sign, uid, parameter);
		 }
		 else 
			 sign = setParametersMapAndSign(urlparametersLinkedHashMap, "cid", parameter.getValue("%cid"), sign);
		 return sign;
	}
	
	public static String selectRandomCidFromDatabase(LinkedHashMap<String, String> urlparametersLinkedHashMap,String sign,String uid,InterfaceStepParameter parameter) throws Exception
	{
		 String sqlCommand = parameter.getValue("randomCidInfo").replace("UID", uid);
	     List<List<String>> resultSet = DataBaseHelper.getResultFromDBBySqlCommand(sqlCommand, parameter, "dburl");
	     String cid = resultSet.get(DataBaseHelper.getRandomIndexOfOneItemFromDb(resultSet.size())).get(0);
	     urlparametersLinkedHashMap.put("cid", cid); 
	     sign = sign + cid;
	     return sign;
	}
	
	public static String setParametersMapAndSign(LinkedHashMap<String, String> urlparametersLinkedHashMap,String key,String value,String sign)
	{
		urlparametersLinkedHashMap.put(key, value);
		sign = sign + value;
		return sign;
	}
	
	public static String generateTitleOrContent(int length,String key,InterfaceStepParameter parameter)
	{
		String content = "";
		if(parameter.getValue(key).isEmpty()||parameter.getValue(key).equals(""))
		 {
		     content = getRandomString(length);
		 }
		 else 
		 {
			 content = parameter.getValue(key);
			 
		 }
		return content;
	}
	
	public static String getRandomString(int length) 
	{   
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ~!@#$%^&*()_+{}:|<>?,./\';][=-`";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) 
	    {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }  
	
	public static void assertCodeAndMsg(JSONObject result,InterfaceStepParameter parameter)
	{
		Assert.assertEquals(result.getString("code"), parameter.getValue("code"),"code is diff");
		Assert.assertEquals(result.getString("msg"), parameter.getValue("msg"),"msg is diff");
	}

    public static List<List<String>> getDataFromDb(InterfaceStepParameter parameter,String sqlKeyWordInXml) throws Exception
    {
    	MysqlDBOperationService service = new MysqlDBOperationService(parameter.getValue("dburl"));
		List<List<String>> resultSet = service.executeQuery(GlobalConfig.dburlPrefix+parameter.getValue(sqlKeyWordInXml),GlobalConfig.dbuser,GlobalConfig.dbpassword);
        return resultSet;
    }

    public static String updateLivePic( String PicPath, String filename, String attachUrl, String tid, String uid, String token, String app_id, String time, String Sign) throws IOException {
        String Boundary = "-----------------------------7db1c5232222b";
        String errorStr = null;
        if (!isValidImage(PicPath)) {
        	System.out.print("picture is invalid!!!"); 
            return null;
        }
        int index = PicPath.lastIndexOf("/");
        filename = PicPath.substring(index + 1);

        final byte header[] = buildLiveHeader(Boundary, "multipart/form-data;boundary="+Boundary, tid, uid, token, app_id, time, Sign, filename).getBytes();
        final byte tail[] = buildTail(Boundary).getBytes();

        String html = null;
        URL url;
        
            url = new URL(attachUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + Boundary);
            File file = new File(PicPath);
            conn.setRequestProperty("Content-Length", String.valueOf(header.length + file.length() + tail.length));
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("Host", "zhibo.178.com");
            System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            DataOutputStream ds =
                    new DataOutputStream(conn.getOutputStream());
            byte[] buf = new byte[1024];
            int len;
            ds.write(header);
            FileInputStream fStream = new FileInputStream(PicPath);
            while ((len = fStream.read(buf)) != -1)
                ds.write(buf, 0, len);
            ds.write(tail);
            ds.flush();
            fStream.close();
            InputStream httpInputStream = conn.getInputStream();
            html = IOUtils.toString(httpInputStream, "UTF-8");

            ds.close();
        

        return html;
    }

    private static String buildLiveHeader(String Boundary, String contentType, String tid, String uid, String token, String app_id, String time, String Sign, String filename) {
        StringBuilder sb = new StringBuilder();
        final String keys[] = {"tid", "uid", "token", "app_id", "t", "sign"};
        final String values[] = {tid, uid, token, app_id, time, Sign};
        for (int i = 0; i < keys.length; ++i) {
            sb = sb.append("--");
            sb = sb.append(Boundary);
            sb = sb.append("\r\n");
            sb = sb.append("Content-Disposition: form-data; name=\"").append(keys[i]).append("\"\r\n\r\n");
            sb = sb.append(values[i]);
            sb = sb.append("\r\n");
        }
        sb.append("--" + Boundary + "\r\n");
        sb.append("Content-Disposition: form-data; name=\"Filedata\"; ");
        sb.append("filename=\"");
        sb.append(filename);
        sb.append("\"");
        sb.append("\r\n");
        sb.append("Content-Type: ");
        sb.append(contentType);
        sb.append("\r\n\r\n");
        return sb.toString();
    }

    private static String buildTail(String Boundary) {
        return ("\r\n--" + Boundary + "--\r\n");
    }

    private static boolean isValidImage(String PicPath) {
        if (isEmpty(PicPath)) {
            return false;
        }
        String extType = PicPath.substring(PicPath.lastIndexOf(".") + 1).toLowerCase();
        if (!extType.equals("jpg") && !extType.equals("png")
                && !extType.equals("jpeg") && !extType.equals("gif") && !extType.equals("bmp")) {
            return false;
        } else {
            return true;
        }
    }
    
    private static boolean isEmpty(String str) {
        if (str != null && !"".equals(str)) {
            return false;
        } else {
            return true;
        }
    }
}
