package com.wanmei.mobile.iat.main.steps;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.Assert;

import com.gateside.autotesting.Lib.common.SimpleLogger;
import com.gateside.autotesting.Gat.dataobject.AssertStepMethodDesc;
import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.ParameterHelper;
import com.gateside.autotesting.Lib.dbService.MysqlDBOperationService;

public class AnnouncerSteps 
{
	@AssertStepMethodDesc(description="compare announcer information between the actual and information get from the database ",owner="yaxiao.yu")
	 public void announerInfoComparation(String parameterID,String expectResut,String actualResult) throws Exception
	 {
		 InterfaceStepParameter parameter=(InterfaceStepParameter)ParameterHelper.getInterfaceStepParameter(parameterID);
		 JSONObject object=JSONObject.fromObject(actualResult);
		 
		 assertPersonNum(object, parameter);
		 assertAnnouncerInfo(object, parameter);
		 assertAnnouncerTaobao(object, parameter);
		// assertAnnouncerUpdateTime(object, parameter);
		
		 
	 }
	private List<String> getPersonInforFromJson(JSONObject object,Integer personIndex)
	 {
		 ArrayList<String> personInfo=new ArrayList<String>();
		 String id = object.getJSONArray("content").getJSONObject(personIndex).getString("id");  
		 String name = object.getJSONArray("content").getJSONObject(personIndex).getString("name");
		 String avatar = object.getJSONArray("content").getJSONObject(personIndex).getString("avatar");
		 personInfo.add(id);
		 personInfo.add(name);
		 personInfo.add(avatar);
		 //SimpleLogger.logInfo("getPersonAvatar");
		 return personInfo;
	 }
	 
	 private Integer getPersonIndexInResultset(String personName,List<List<String>> resultSet)
	 {
		 Integer index=-1;
		 for(Integer i=1;i<resultSet.size();i++)
		 {
			 if(personName.equals(resultSet.get(i).get(1))) 
			 {
				 index=i;
				 break;
			 }
		 }
		 
		 return index;
	 }
	 
	 private Integer getPersonIndexByIdFromDatebase(String personId,List<List<String>> resultSet)
	 {	 
		 Integer index=-1;
		 for(Integer i=1;i<resultSet.size();i++)
		 {
			 if(personId.equals(resultSet.get(i).get(0))) 
			 {
				 index=i;
				 break;
			 }
		 }
		 return index;

	 }
	 
	 private void assertPersonNum(JSONObject object,InterfaceStepParameter parameter) throws Exception
	 {
		 JSONArray arrayPerson = object.getJSONArray("content");
	     MysqlDBOperationService service=new MysqlDBOperationService(parameter.ConnectiongString);
		 List<List<String>> resultSet= service.executeQuery(parameter.getValue("announcerInfoSql"),parameter.getValue("user"),parameter.getValue("password"));
		 Assert.assertEquals(arrayPerson.size(), resultSet.size()-1,"!!!the announcer number from interface is different from the number from database!!!");
	 }
	 
	 private void assertAnnouncerInfo(JSONObject object,InterfaceStepParameter parameter) throws Exception
	 {
		 JSONArray arrayPerson = object.getJSONArray("content");
		 MysqlDBOperationService service=new MysqlDBOperationService(parameter.ConnectiongString);
		 List<List<String>> resultSet= service.executeQuery(parameter.getValue("announcerInfoSql"),parameter.getValue("user"),parameter.getValue("password"));
		 for(int i=0; i<arrayPerson.size(); i++)
		 {
			 List<String> personInfo=getPersonInforFromJson(object,i);
			 Integer personIndex=getPersonIndexByIdFromDatebase(personInfo.get(0), resultSet);			 
			 
			 for(int j=0;j<personInfo.size()-1;j++)
			 {	
				 Assert.assertEquals(personInfo.get(j), resultSet.get(personIndex).get(j),"the detail information of announcer dismatched ");
			 }
			 String avatarInDatabase=resultSet.get(personIndex).get(personInfo.size()-1);
//			 if(avatarInDatabase.startsWith("/"))
//			 {
//				 avatarInDatabase="http://bigeye.tv/uploads/images"+avatarInDatabase;  
//			 }
//			 Assert.assertEquals(personInfo, avatarInDatabase,"the detail information of announcer dismatched ");
		 }	
	 }
	 
	 private void assertAnnouncerTaobao(JSONObject object,InterfaceStepParameter parameter) throws Exception
	 {   
		 JSONArray arrayPerson = object.getJSONArray("content");
		 MysqlDBOperationService service=new MysqlDBOperationService(parameter.ConnectiongString);
		 List<List<String>> resultSet= service.executeQuery(parameter.getValue("announcerTaobaoInfoSql"),parameter.getValue("user"),parameter.getValue("password"));
		
		 for(int i=0; i<arrayPerson.size(); i++)
		 {
			 JSONObject taobao=arrayPerson.getJSONObject(i).getJSONObject("taobao");
			 SimpleLogger.logInfo("===============");
			 System.out.print(taobao);
			 if(taobao.getString("scheme").equals(""))return;
			 if(taobao.getString("link").equals(""))return;
			 
			 String personid=arrayPerson.getJSONObject(i).getString("id");
			 Integer personIndex=getPersonIndexByIdFromDatebase(personid, resultSet);
			 
			 //System.out.println(personIndex);======");
			 TaoBao taoBao=new TaoBao(taobao);
			 //TaoBao taoBao=arrayPerson.getJSONObject(personIndex).getJSONObject("taobao")
			 System.out.println(personIndex);
			 Assert.assertEquals(taoBao.scheme, resultSet.get(personIndex).get(1),"taoBao scheme dismatched ");
			 Assert.assertEquals(taoBao.link, resultSet.get(personIndex).get(2),"taoBao link dismatched ");
		 }	
	 }
	 
	 private void assertAnnouncerUpdateTime(JSONObject object,InterfaceStepParameter parameter) throws Exception
	 {
		 JSONArray arrayPerson = object.getJSONArray("content");
		 MysqlDBOperationService service=new MysqlDBOperationService(parameter.ConnectiongString);
		 List<List<String>> resultSet= service.executeQuery(parameter.getValue("announcerUpdateTimeInfoSql"),parameter.getValue("user"),parameter.getValue("password"));
		 for(int i=0; i<arrayPerson.size(); i++)
		 {
			 List<String> personInfo=getPersonInforFromJson(object,i);
			 Integer personIndex=getPersonIndexByIdFromDatebase(personInfo.get(0), resultSet);
			 System.out.println(personIndex);
			 SimpleLogger.logInfo("======getPersonIndex======");
			 String updateDate=object.getJSONArray("content").getJSONObject(i).getString("last_update_date");
			 Assert.assertEquals(updateDate, resultSet.get(personIndex).get(1),"updateTime dismatched ");
			 //Assert.assertEquals(taoBao.scheme, resultSet.get(personIndex).get(1),"taoBao link dismatched ");
		 }	
	 }
	 
	 private TaoBao getTaoBaoInfoFromInterface(JSONArray arrayPerson,int personIndex) throws Exception
	 {
		 if(arrayPerson.getJSONObject(personIndex).size()<6)
		 {
			 SimpleLogger.logInfo("=!!!!can't find taobao information from interface!!!!!=");
			 TaoBao taobao=new TaoBao();
			 return taobao;
		 }
		 else
		 {
			 TaoBao taobao=new TaoBao(arrayPerson.getJSONObject(personIndex).getJSONObject("taobao"));
			 return taobao;	
		 }   
		 	 
		 
	 }
	 
	 class TaoBao
	 {
		 public String scheme="";
		 public String link="";
		 
		 public TaoBao(JSONObject jsonObject)
		 {
			 scheme=jsonObject.getString("scheme");
			 link=jsonObject.getString("link").replace("\\", "");
		 }
		 
		 public TaoBao()
		 {
			 scheme="can't find scheme";
			 link="can't find link";
		 }
	 }

}
