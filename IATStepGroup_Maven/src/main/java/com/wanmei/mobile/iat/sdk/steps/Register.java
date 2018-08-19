package com.wanmei.mobile.iat.sdk.steps;

import java.util.List;

import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.wanmei.mobile.iat.common.Constant;
import com.wanmei.mobile.iat.common.DataBaseHelper;
import com.wanmei.mobile.iat.common.PhoneNumberGenerator;
import com.wanmei.mobile.iat.common.RandomHelper;

public class Register {
	
	public static String register(String uid,String pwd,String email, String username,String t) throws Exception{
		String date = String.valueOf(Integer.valueOf(t)-172880);
		String phone = PhoneNumberGenerator.getTelePhoneNum();
		String insertIntoMembers = "INSERT INTO pw_members SET uid = '"+ uid +"',email = '"+ email +"',username = '"+username+"',cookieid = '"
				+RandomHelper.getRandomString(16, Constant.RANDOM_VALUE)+ "',regdate = '"+ date +
				"',groupid = '-1',rvrc='10',yz = '1',lastvisit = '"+date+"',thisvisit= '"+date+"',icon = '',signature = '',introduce='',icq = ''"
				+ ",phone = '"+phone+"',honor ='',mute_time = '',banpm = '',recommend_by='0',nickname='',misc_data='',reputation='';";
		System.out.println(insertIntoMembers);
//		String insertIntoPwd = "INSERT INTO pw_members_password SET uid = '"+ uid +"',password = '"
//		+ CommonHelper.md5(pwd) + "' ON DUPLICATE KEY UPDATE password ="+CommonHelper.md5(pwd);			
//		System.out.println(insertIntoPwd);
		String re = null ;
		re = sqlcmd(Constant.DB_URL_KA,insertIntoMembers, uid, username);
		return re;
	}
	
	

	private static String sqlcmd(String dbUrl,String sqlCommand ,String uid,String username) throws Exception{
		String re =null;
		System.out.println(sqlCommand);
		try {
			DataBaseHelper.sqlCommand(dbUrl, sqlCommand);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
//		DataBaseHelper.sqlCommand(dbUrl, dbUser, dbPwd, dbCmd);
		List<List<String>> res = DataBaseHelper.getResult(dbUrl,"select uid from pw_members where username = '"+username+"'");
		System.out.println(username);
		System.out.println(res);
		if(res.get(1).get(0).equals(uid)){				
			re = "用户名为："+username+"密码为：asd123";
		}else{
			re = "输入失败";
		}
		return re;
		
	}
	
	public static String regforTest() throws Exception {
		RegisterFor178.register();
		return Register.register(StepValueHelper.getStepOutputValue("uid").toString(), StepValueHelper.getStepOutputValue("password").toString(),
				StepValueHelper.getStepOutputValue("email").toString(), StepValueHelper.getStepOutputValue("nickname").toString(),
				StepValueHelper.getStepOutputValue("t").toString());
	}
	

	
	public static String changeCharsetToGB2312(String str)throws Exception {
		return new String(str.getBytes("ISO-8859-1"), "GB2312");	
	}
	


}
