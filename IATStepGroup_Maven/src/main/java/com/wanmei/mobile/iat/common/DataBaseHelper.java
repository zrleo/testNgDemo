package com.wanmei.mobile.iat.common;

import java.util.List;
import java.util.Random;

import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Lib.dbService.MysqlDBOperationService;

public class DataBaseHelper {

	
	public static List<List<String>> getResult(String url,String sqlCommand) throws Exception
	{
		MysqlDBOperationService service = new MysqlDBOperationService(url);
		List<List<String>> resultSet= service.executeQuery(sqlCommand,Constant.DB_USER,Constant.DB_PWD);
		return resultSet;
	}
	
	public static void sqlCommand(String dbUrl,String sqlCommand) throws Exception
	{
		MysqlDBOperationService service = new MysqlDBOperationService(dbUrl);
		service.executeNoneQuery(sqlCommand,Constant.DB_USER,Constant.DB_PWD);
	}
	public static List<List<String>> getResultFromDBBySqlCommand(String sqlCommand,InterfaceStepParameter parameter,String urlKeyword) throws Exception
	{
		MysqlDBOperationService service = new MysqlDBOperationService(GlobalConfig.dburlPrefix+parameter.getValue(urlKeyword));
		List<List<String>> resultSet = service.executeQuery(sqlCommand,
				GlobalConfig.dbuser,GlobalConfig.dbpassword);
		return resultSet;
	}
	public static int getRandomIndexOfOneItemFromDb(int n) throws Exception 
	{
		Random random = new Random();
		int num = random.nextInt(n);
		while(num == 0)
		{
			num = random.nextInt(n);
		}
		return num;
	}
	/**
	 * 可跨库；用sql操作数据库，可用后面添加字符串方式修改xml中sql语句，通过xml中的sqlName
	 */
	public static List<List<String>> getResultFromDBBySqlNameInXml(InterfaceStepParameter parameter,String urlKeyword, String SQLKeyword, String addition) throws Exception
	{
		System.out.println(parameter.getValue(SQLKeyword)+addition);
		MysqlDBOperationService service = new MysqlDBOperationService(GlobalConfig.dburlPrefix+parameter.getValue(urlKeyword));
		List<List<String>> resultSet= service.executeQuery(parameter.getValue(SQLKeyword)+addition,
				GlobalConfig.dbuser,GlobalConfig.dbpassword);
		return resultSet;
	}

	
}
