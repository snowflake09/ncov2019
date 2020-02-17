package com.utils;

public class GeoUtils {
	
	public static double LENGTH_UNIT = 0.009009009; //经纬度单位/km

	 /**
     * 计算两经纬度点之间的距离（单位：米）
     * @param lng1  经度
     * @param lat1  纬度
     * @param lng2
     * @param lat2
     * @return
     */
    public static double getDistance(double lng1,double lat1,double lng2,double lat2){
        double radLat1 = Math.toRadians(lat1);
        double radLat2 = Math.toRadians(lat2);
        double a = radLat1 - radLat2;
        double b = Math.toRadians(lng1) - Math.toRadians(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
                * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
        s = Math.round(s * 10000) / 10000;
        return s;
    }
    
    /**
     * 获得两经纬度之间的距离（超过1000以km为单位）
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @return
     * @author LiQiang
     * @date 2016年12月2日
     */
    public static String convertDis(String lng1,String lat1,String lng2,String lat2){
    	String v = "";
    	double dlng1 = Double.valueOf(lng1);
    	double dlat1 = Double.valueOf(lat1);
    	double dlng2 = Double.valueOf(lng2);
    	double dlat2 = Double.valueOf(lat2);
    	double dis = getDistance(dlng1,dlat1,dlng2,dlat2);
    	if(dis>1000){
    		v = dis/1000 + "km";
    	}else{
    		v = dis + "m";
    	}
		return v;
    }
    
    /**
     * 获得经纬度单位
     * @param mi
     * @return
     * @author LiQiang
     * @date 2016年12月2日
     */
    public static double coordinateUnit(int mi){
    	double unit = LENGTH_UNIT*((double)mi/1000);
		return unit;
    }

	public static void main(String[] args) {
		System.out.println(convertDis("31.3012410000", "120.5987680000","31.3018280000", "120.5783630000"));
		System.out.println(coordinateUnit(2265));
	}

}
