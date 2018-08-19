package com.wanmei.mobile.iat.common;

import static java.lang.Math.PI;

public class LbsHelper {

	private static final double R = 6378137; 
    
    private static final double PI_DIV =  PI / 180.0;
	
    public static int getDistance(double long1, double lat1, double long2, double lat2) {
        double a, b;
        lat1 = lat1 * PI_DIV;
        lat2 = lat2 * PI_DIV;
        a = lat1 - lat2;
        b = (long1 - long2) * PI_DIV;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        return  (int) (2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2)));
    }


    
}
