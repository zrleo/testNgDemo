package com.wanmei.mobile.iat.common.http;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import net.sf.json.JSONObject;

import org.apache.http.HttpStatus;

import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.gateside.autotesting.Lib.httpunitService.HttpUnitHelper;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.protocol.UploadFileSpec;
import com.wanmei.mobile.iat.common.CommonHelper;
import com.wanmei.mobile.iat.common.GlobalConfig;

import java.io.*;
import java.net.*;
import java.util.LinkedHashMap;
import java.util.Map;



public class UploadAttachments {
	public static final String CHARSET_UTF8 = "UTF-8";
	private static final double MAX_IMAGE_SIZE = 1.5 * 1024 * 1024;
	private static String BOUNDARY = java.util.UUID.randomUUID().toString();
	private static String PREFIX = "--", LINEND = "\r\n";
	private static String MULTIPART_FROM_DATA = "multipart/form-data";
	private static String SUPPORT_FORMAT = "image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, application/x-shockwave-flash, application/x-quickviewplus, */*";
	
	/** 发送请求
	 * @param url 请求的地址
	 * @param params 其他参数包括sign
	 * @param resourceName 资源包头命名
	 * @param resource     资源
	 * @return
	 * @throws Exception 
	 */
	public static String getResultContentWithMultiFormData( InterfaceStepParameter parameter, String resourceName,String resource,String token,String uid
	  ) throws Exception {
		DataOutputStream output = null;
		HttpURLConnection conn = null;
		URL uri;
		String url2 = parameter.getValue("url") + setParametersForUri(parameter, token, uid);
		System.out.println(url2);
		try {
			uri = new URL(url2);
			conn = (HttpURLConnection) uri.openConnection();
			conn.setDoInput(true);// 允许输入
			conn.setDoOutput(true);// 允许输出
			conn.setUseCaches(false);
			conn.setRequestMethod("POST"); // Post方式
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("Charset", CHARSET_UTF8);
			conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);
			conn.setRequestProperty("Accept", SUPPORT_FORMAT);
			conn.connect();
			output = new DataOutputStream(conn.getOutputStream());
			
				if (isImage(resource)) {
					addImageContent(resourceName, resource, output);
				} else {
					
					addFileContent(resourceName, resource, output);
				}
			// 请求结束标志
				output.writeBytes(PREFIX + BOUNDARY + PREFIX + LINEND);
			output.flush();
			// 得到响应码
			int res = conn.getResponseCode();
			if (res == HttpStatus.SC_OK) {
				InputStream in = conn.getInputStream();
				InputStreamReader isReader = new InputStreamReader(in,CHARSET_UTF8);
				BufferedReader bufReader = new BufferedReader(isReader);
				StringBuilder result = new StringBuilder();
				String tmp;
				while ((tmp = bufReader.readLine()) != null) {
					result.append(tmp);
				}
				bufReader.close();
				String content = result.toString();
				return content;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (output != null)
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (conn != null)
				conn.disconnect();
		}
		return null;
	}
	public static String setParametersForUri(InterfaceStepParameter parameter,String token,String uid) throws Exception
	{
		
		String sign = "?";
		String s = "";
		for(int i=0;i<parameter.parameters.size();i++)
		{
			if(parameter.parameters.get(i).key.startsWith("%"))
			 {
				 String key = parameter.parameters.get(i).key;
				 if(key.equals("%uid"))
				 {
					 System.out.println("uid:"+uid);
					 s = s + uid;
					 sign = sign +key.substring(1) +"=" + uid +"&";
				 }
				 else if(key.equals("%token"))
				 {
					 System.out.println("token:"+token);
					 s = s + token;
					 sign = sign +key.substring(1) +"=" + token +"&";
				 }	
				 else if(key.equals("%app_id"))
				 {
					 s = s + GlobalConfig.app_id;
					 sign = sign +key.substring(1) +"=" + GlobalConfig.app_id +"&";					 
				 }	
				 else {
					 s = s + parameter.getValue(key);
					 sign = sign +key.substring(1) +"=" + parameter.getValue(key) +"&";					 
				}
				 
			 }
		}
//		String t = CommonHelper.getTimeStampSec();
//		 s = s + t;
//        
//        String sg =CommonHelper.md5(s+GlobalConfig.appkey);
//        sign = sign + "t=" + t + "&sign=" + sg + " ";


		return sign;
	}

	private static boolean isImage(String filePath) {
		return (filePath.endsWith(".png")) || (filePath.endsWith(".jpg")) || (filePath.endsWith(".png"))
		  || (filePath.endsWith(".gif")) || (filePath.endsWith(".bmp")) || (filePath.endsWith(".jpeg"))
		  || (filePath.endsWith(".ico")) || (filePath.endsWith(".jpe"));
	}

	private static void addFormField(Map<String, String> params, DataOutputStream output) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append(PREFIX);
			sb.append(BOUNDARY);
			sb.append(LINEND);
			sb.append("Content-Disposition: form-data; name=\"").append(entry.getKey()).append("\"").append(LINEND);
			sb.append("Content-Type: text/plain; charset=").append(CHARSET_UTF8).append(LINEND);
			sb.append("Content-Transfer-Encoding: 8bit").append(LINEND);
			sb.append(LINEND);
			sb.append(entry.getValue());
			System.out.println(entry.getValue());
			sb.append(LINEND);
		}
		//	 output.writeBytes(sb.toString()); //������ܵ�����������
		output.write(sb.toString().getBytes(CHARSET_UTF8));
	}

	
	private static void addImageContent(String paramsName, String fileName, DataOutputStream output) throws IOException {
		File file = new File(fileName);
		byte[] compressImageBytes = null;
		if (file.length() > MAX_IMAGE_SIZE) { // ����ͼƬѹ�������ϴ�
			int compress = (int) (MAX_IMAGE_SIZE / file.length() * 100);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Bitmap bitmap = BitmapFactory.decodeFile(fileName);
			bitmap.compress(Bitmap.CompressFormat.JPEG, compress, baos);
			compressImageBytes = baos.toByteArray();
			if (bitmap != null && !bitmap.isRecycled()) {
				bitmap.recycle();
				bitmap = null;
			}
			baos.close();
		}

		if (file.length() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(PREFIX);
			sb.append(BOUNDARY);
			sb.append(LINEND);
			sb.append("Content-Disposition: form-data; name=\"").append(paramsName).append("\"; filename=\"")
			  .append(file.getName()).append("\"").append(LINEND);
			sb.append("Content-Type: application/octet-stream; charset=").append(CHARSET_UTF8).append(LINEND);
			sb.append(LINEND);
			output.writeBytes(sb.toString());

			if (compressImageBytes != null && compressImageBytes.length > 0) {
				output.write(compressImageBytes, 0, compressImageBytes.length);
			} else {
				InputStream is = new FileInputStream(fileName);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					output.write(buffer, 0, len);
				}
				is.close();
			}
			output.writeBytes(LINEND);
		}
	}

	private static void addFileContent(String paramsName, String fileName, DataOutputStream output) throws IOException {
		File file = new File(fileName);
		if (file.length() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(PREFIX);
			sb.append(BOUNDARY);
			sb.append(LINEND);
			sb.append("Content-Disposition: form-data; name=\"").append(paramsName).append("\"; filename=\"")
			  .append(file.getName()).append("\"").append(LINEND);
			sb.append("Content-Type: application/octet-stream; charset=").append(CHARSET_UTF8).append(LINEND);
			sb.append(LINEND);
			output.writeBytes(sb.toString());

			InputStream is = new FileInputStream(fileName);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				output.write(buffer, 0, len);
			}
			is.close();
			output.writeBytes(LINEND);
		}
	}
	
	public static JSONObject requestSendFlie(String url,String method,LinkedHashMap<String, String> urlParameters,String fileParam,String filein_path) throws Exception
	{
		WebConversation wc=HttpUnitHelper.createConversation();
		WebRequest req=HttpUnitHelper.createWebRequest(url,method);
		PostMethodWebRequest request = new PostMethodWebRequest(url);


		CommonHelper.printMap(urlParameters);
		 if(urlParameters!=null)
		 {
			 HttpUnitHelper.setParameters(req, urlParameters);
		 }else 
		 {
			 SimpleLogger.logError("!!!!!!!!!!urlParameters are not completed!!!!!!!!!!");
		 }		 
		 
		 File file=new File(filein_path);
		 System.out.println(filein_path);
		 UploadFileSpec[] ufs = {new UploadFileSpec(file,"multipart/form-data")}; 	
//		 req.setParameter(fileParam, ufs);
		 req.selectFile(fileParam, file, "multipart/form-data");
		 WebResponse response = wc.getResource(req);
		 SimpleLogger.logInfo(response.getText());
		 JSONObject object=JSONObject.fromObject(response.getText());
		 return object;

	}
}
