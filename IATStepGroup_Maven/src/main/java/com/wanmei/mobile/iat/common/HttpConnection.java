package com.wanmei.mobile.iat.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.apache.http.HttpStatus;

import java.io.*;
import java.net.*;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;



public class HttpConnection {
	public static final String CHARSET_UTF8 = "UTF-8";
	private static final double MAX_IMAGE_SIZE = 1.5 * 1024 * 1024;
	private static String BOUNDARY = java.util.UUID.randomUUID().toString();
	private static String PREFIX = "--", LINEND = "\r\n";
	private static String MULTIPART_FROM_DATA = "multipart/form-data";
	private static String SUPPORT_FORMAT = "image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, application/x-shockwave-flash, application/x-quickviewplus, */*";
	
	public static String getResultContentWithMultiFormData(String url, Map<String, String> params, String resourceName,
	  String resource) {
		DataOutputStream output = null;
		HttpURLConnection conn = null;
		URL uri;
		try {
			uri = new URL(url);
			conn = (HttpURLConnection) uri.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("Charset", CHARSET_UTF8);
			conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);
			conn.setRequestProperty("Accept", SUPPORT_FORMAT);
			conn.connect();
			output = new DataOutputStream(conn.getOutputStream());
			addFormField(params, output);
				if (isImage(resource)) {
					addImageContent(resourceName, resource, output);
				} else {
					
					addFileContent(resourceName, resource, output);
				}
			output.writeBytes(PREFIX + BOUNDARY + PREFIX + LINEND);
			output.flush();
			int res = conn.getResponseCode();
			//System.out.println(res);
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

	private static boolean isImage(String filePath) {
		return (filePath.endsWith(".png")) || (filePath.endsWith(".jpg")) || (filePath.endsWith(".png"))
		  || (filePath.endsWith(".gif")) || (filePath.endsWith(".bmp")) || (filePath.endsWith(".jepg"))
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
			sb.append(LINEND);
		}
		//	 output.writeBytes(sb.toString()); 
		
		output.write(sb.toString().getBytes(CHARSET_UTF8));
	}

	
	private static void addImageContent(String paramsName, String fileName, DataOutputStream output) throws IOException {
		File file = new File(fileName);  
		byte[] compressImageBytes = null;
		if (file.length() > MAX_IMAGE_SIZE) { 
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
	
}
