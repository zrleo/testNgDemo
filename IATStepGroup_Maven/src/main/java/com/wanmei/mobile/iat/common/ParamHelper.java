package com.wanmei.mobile.iat.common;


import java.util.LinkedHashMap;





import com.gateside.autotesting.Gat.dataobject.stepparameter.InterfaceStepParameter;
import com.gateside.autotesting.Gat.util.StepValueHelper;
import com.gateside.autotesting.Gat.util.StepValuePool;




public class ParamHelper {
	
	
	
	/**
	 * 从参数池中读入或者从xml中导入，导入后添加到valuepool中
	 * @param parameter
	 * @param urlParameters
	 * @param param
	 * @throws Exception
	 */
	public static String setParamExistOrValuePool(InterfaceStepParameter parameter,LinkedHashMap<String, String> urlParameters,String param) throws Exception
	{
		String value= null;
		if(StepValueHelper.getStepOutputValue(param) != null ){
			value= (String)StepValueHelper.getStepOutputValue(param);
			urlParameters.put(param, value);			
			return value;
		}
		 else if(isKeyExist(parameter,param)){ 
			 return setParameterToValuePoolFromXml(parameter, urlParameters, param);		
		 }
		return value;
	}
	
	/**
	 * 将参数从Xml读出并存在Map和valuePool中（覆盖）
	 * @param parameter
	 * @param urlParameters
	 * @param param 参数名
	 * @return 
	 */
	public static String setParameterToValuePoolFromXml(InterfaceStepParameter parameter,LinkedHashMap<String, String> urlParameters,String param){
		String value = parameter.getValue(param);
		setParameterToValuePool(urlParameters, param, value);
		return value;
	}
	
	/**
	 * 将参数从Xml读出并存在Map和valuePool中（不覆盖）
	 * @param parameter
	 * @param urlParameters
	 * @param param
	 * @return 
	 */
	public static String setParameterToValueHelperFromXml(InterfaceStepParameter parameter,LinkedHashMap<String, String> urlParameters,String param){
		String value = parameter.getValue(param);
		setParameterToValueHelper(urlParameters, param, value);
		return value;
	}
	
	/**
	 * 将参数从外部传入出并存在Map和valuePool中（覆盖）
	 * @param param
	 * @param urlParameters
	 * @param param 参数名
	 */
	public static String setParameterToValuePool(LinkedHashMap<String, String> urlParameters,String param,String value){
		urlParameters.put(param,value);
		StepValuePool.createInstance().getValueDic().put(param, value);
		return value;
	}
	
	/**
	 * 将参数从外部传入读出并存在Map和valuePool中（不覆盖）
	 * @param param
	 * @param urlParameters
	 * @param param 参数名
	 */
	public static String setParameterToValueHelper(LinkedHashMap<String, String> urlParameters,String param,String value){
		urlParameters.put(param,value);
		StepValueHelper.putStepOutputValue(param, value);
		return value;
	}
	
	

	
	/**
	 * 如果xml中有参数值就将其填入，如果没有就从外部导入
	 * @param parameter
	 * @param urlParameters
	 * @param param 参数名
	 * @param value 外部导入的参数值
	 */
	public static String setParamByTwoWay(InterfaceStepParameter parameter,LinkedHashMap<String, String> urlParameters,String param,String value){
		if (isKeyExist(parameter, param)) {		
			return setParameterToValueHelperFromXml(parameter, urlParameters, param);
		 }else {
			return setParameterToValuePool(urlParameters, param, value);
		 }
	}
	
	/**
	 * 如果xml中有参数值就将其填入，如果没有就ValuePool中导入，如果没有就从外部导入
	 * @param parameter
	 * @param urlParameters
	 * @param param
	 */
	public static String setParamByThreeWay(InterfaceStepParameter parameter,LinkedHashMap<String, String> urlParameters,String param,String value){
		if (isKeyExist(parameter, param)) {
			return	setParameterToValuePoolFromXml(parameter, urlParameters, param);
		 }else if(StepValueHelper.getStepOutputValue(param)!=null){
			 return setParameterToValuePool(urlParameters, param,StepValueHelper.getStepOutputValue(param).toString());	
		 }else {
			 return setParameterToValuePool(urlParameters, param, value);
		 }
		 
	}
	
	/**
	 * 如果xml中有参数值就将其填入并存入valuePool
	 * @param parameter
	 * @param urlParameters
	 * @param param 参数名
	 * @param
	 */
	public static String setExistParam(InterfaceStepParameter parameter,LinkedHashMap<String, String> urlParameters,String param){
		if (isKeyExist(parameter, param)) {		
			return	setParameterToValuePoolFromXml(parameter, urlParameters, param);
		 }
		return param;
	}
	
	public static String getPwd(InterfaceStepParameter parameter,String param){
		if (isKeyExist(parameter, param)) {
			String pwd = parameter.getValue(param);
			return Cipher.base64Encode(Cipher.encrypt(pwd, Constant.APP_KEY.substring(Constant.APP_KEY.length()-16, Constant.APP_KEY.length())));
		 }else if(StepValueHelper.getStepOutputValue(param)!=null){
			 return StepValueHelper.getStepOutputValue(param).toString();	
		 }else{
			return Cipher.base64Encode(Cipher.encrypt(Constant.PWD, Constant.APP_KEY.substring(Constant.APP_KEY.length()-16, Constant.APP_KEY.length())));
		 }
	}
	
	 public static boolean isKeyExist(InterfaceStepParameter parameter,String key)
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
	
}
