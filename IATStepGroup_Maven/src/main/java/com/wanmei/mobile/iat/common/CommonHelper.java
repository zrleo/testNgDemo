package com.wanmei.mobile.iat.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;






import net.sf.json.JSONObject;

import org.junit.Assert;

import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;

public class CommonHelper {

public static String md5(String string) 
{
	byte[] hash;
    try {
      hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
    } catch (NoSuchAlgorithmException e) {
    	throw new RuntimeException("Huh, MD5 should be supported?", e);
    } catch (UnsupportedEncodingException e) {
        throw new RuntimeException("Huh, UTF-8 should be supported?", e);
    }
      StringBuilder hex = new StringBuilder(hash.length * 2);
    for (byte b : hash) {
       if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
    }
   return hex.toString();
}


 public static void printMap(Map<?, ?> hm) {
	 Set<?> s = hm.keySet();        
     Iterator<?> i = s.iterator();
     while(i.hasNext()) {
     Object o = i.next();
     System.out.println("&"+o +"="+hm.get(o));
     }
 }
 
 public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();

	}
 public static void assertCodeAndCheckToken(String response,String codeNum,String checktokenNum)throws Exception
 {
	 String code=JSONObject.fromObject(response).getString("code");
     Assert.assertEquals(code,codeNum,"the returned code is not "+codeNum+" !!!");
     if(JSONObject.fromObject(response).containsKey("checktoken"))
     {
         String checktoken=JSONObject.fromObject(response).getString("checktoken");
         String checktokenmsg=JSONObject.fromObject(response).getString("checktokenmsg");
         Assert.assertEquals(checktoken,checktokenNum,"the returned checktoken is not "+checktokenNum+" !!!");
         Assert.assertEquals(checktokenmsg,"","the returned checktokenmsg is not null !!!");
     }
 }

public static String ReEncodeDataFromDB(List<List<String>> resultSet,int row, int colum) throws Exception
{
	byte[] b2=resultSet.get(row).get(colum).getBytes("ISO-8859-1");
	String name=new String(b2,"GBK");
	return name;
}
public static String convertDetailFromServer(String mill) 
{
	Date date = new Date(Long.parseLong(mill) * 1000L);
	String strs = "";	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	strs = sdf.format(date);

	return strs;
}
public static int getIndexByTimeFromDatabase(String name,List<List<String>> resultSet,int indexInResultSet)
{
	int index=-1;
	for(int i=0;i<resultSet.size();i++)
	{
		if(name.equals(convertDetailFromServer(String.valueOf(Integer.valueOf(resultSet.get(i).get((indexInResultSet)))*1000))))
		{
			index=i;
			break;
		}
	}
	return index;
}

public static int getIndexFromDatabase(String name,List<List<String>> resultSet,int indexInResultSet)
{
	int index=-1;
	for(int i=0;i<resultSet.size();i++)
	{
		if(name.equals(resultSet.get(i).get(indexInResultSet)))
		{
			index=i;
			break;
		}
	}
	return index;
}
public static List<String> getIndexListFromDatabase(String stringToBeMatched,List<List<String>> resultSet,int indexInResultSet)
{
	List<String> idIndexList=new ArrayList<String>();
	for(int i=1; i<resultSet.size();i++)
	{
		if(stringToBeMatched.equals(resultSet.get(i).get(indexInResultSet)))//name在数据库中查询时为第2个字段
		{
			String index=String.valueOf(i);
			idIndexList.add(index);
		}
	}
	
	return idIndexList;
}
public static HashMap<String, String> setUserParameterMapForMessageReply(InterfaceStepParameter parameter)
{
	 LinkedHashMap<String, String> userParameterMap=new LinkedHashMap<String, String>();
	 String randomSubject=RandomHelper.getRandomString(Integer.valueOf(parameter.getValue("subjectLength")),Constant.RANDOM_VALUE_ALL);
	 String randomContent=RandomHelper.getRandomString(Integer.valueOf(parameter.getValue("contentLength")),Constant.RANDOM_VALUE_ALL);
	 
	 for(int i=0;i<parameter.parameters.size();i++)
	 {
		 
		 if(parameter.parameters.get(i).key.equals("$#subject"))
		 {
			 userParameterMap.put("#subject", randomSubject);
		 }
		 
		 if(parameter.parameters.get(i).key.equals("$#content"))
		 {
			 userParameterMap.put("#content", randomContent);
		 }
		 
		 if(parameter.parameters.get(i).key.equals("$#did"))
		 {
		     userParameterMap.put("#did", parameter.getValue("$#did"));
		 }
	 }
	 
	 return userParameterMap;
}

 
 
   public static void main (String args[]){
/*	   String a = "\u5b9a\u65f6\u53d1\u5e0313:00:00\u6807\u9898";
	   String b= CommonHelper.getUTF8XMLString(a);
	   System.out.println(b);
	   System.out.println("定时发布13:00:00标题".equals(b)+b);
	   System.out.println(a+"select * from b wher title=c");*/
	   
	   String a = decodeUnicode("\u5b9a\u65f6\u53d1\u5e0313:00:00\u6807\u9898");
	   System.out.println(a);
	   
   }
 

}
