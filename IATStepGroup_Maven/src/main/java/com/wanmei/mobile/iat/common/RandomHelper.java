package com.wanmei.mobile.iat.common;



import java.util.Random;
import java.util.UUID;

import android.R.integer;

public class RandomHelper {
	
	
	public static String getRandom(int n) {
        if (n < 1 || n > 10) {
            throw new IllegalArgumentException("cannot random " + n + " bit number");
        }
        Random ran = new Random();
        if (n == 1) {
            return String.valueOf(ran.nextInt(10));
        }
        int bitField = 0;
        char[] chs = new char[n];
        for (int i = 0; i < n; i++) {
            while(true) {
                int k = ran.nextInt(10);
                if( (bitField & (1 << k)) == 0) {
                    bitField |= 1 << k;
                    chs[i] = (char)(k + '0');
                    break;
                }
            }
        }
        return new String(chs);
	}
	
	public static int getRandomInt(int n) {
        int random = Integer.valueOf(getRandom(n));
        return random;
	}

	
	public static String getRandomString(int length,String baseValue) 
	{   
	    String base = baseValue;   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) 
	    {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }  
	
	
	public static int getRandomNum(int length) 
	{   
		Random rand = new Random(); 
		int randNum = rand.nextInt(length)+1;
		return randNum;  
	 }
	
	
	public static int getRandomNumFromZero(int length) 
	{   
		Random rand = new Random(); 
		int randNum = rand.nextInt(length);
		return randNum;
	 }
	
	
	public static int getRandomNum(int min ,int max) 
	{   
		Random random = new Random(); 
		int randNum = random.nextInt(max)%(max-min+1) + min;
		return randNum;
	 }
	
	
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		StringBuilder sb = new StringBuilder();
		sb.append(uuid.substring(0, 8));
		sb.append(uuid.substring(9, 13));
		sb.append(uuid.substring(14, 18));
		sb.append(uuid.substring(19, 23));
		sb.append(uuid.substring(24));
		return sb.toString();
	}
	
   public static String getContent(int i){
	   String contentes ="收费dd发广告法股份的股份个非官方~!@#$%^*()__)(*个非官方方sdfasdfdxcvbjbhjhnbnbfffsadfasdf1232地方给发个电饭锅435654675!@#$%^*()";
	   Random random =new Random();
	   StringBuilder content =new StringBuilder();   
	   for(int a=0;a<i;a++){
		   int b =random.nextInt(contentes.length());
		   char c =contentes.charAt(b);
		   content.append(c);		   		   
	   }
	   String contents =content.toString();
	   return contents; 
   }
	
}
