package com.wanmei.mobile.iat.common;

import com.gateside.autotesting.Libs.memcachedService.XMemcacheClientHelper;

public class MemoryCacheHelper {



	
	private static String getMemCache(String head ,String key )throws Exception{
		XMemcacheClientHelper xm = new XMemcacheClientHelper(Constant.MEMOCACHE_IP, Constant.MEMOCACHE_PORT);	
		String value = (String)xm.getValue(head+key);
		System.out.println(head+key);
		System.out.println(value);
		int i = value.indexOf(",");		
		String code = value.substring(i+1);
		return code;
	}
	

	
	public static void deleteMemCache(String key)throws Exception {
		XMemcacheClientHelper xm = new XMemcacheClientHelper(Constant.MEMOCACHE_IP, Constant.MEMOCACHE_PORT);
		xm.delete(key);
	}

}
