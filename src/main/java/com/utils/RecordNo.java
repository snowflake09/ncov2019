package com.utils;

import java.util.Date;

/**
 * 生成随机记录号
 * 
 * @author LiQiang
 * @date 2017年3月23日
 */
public class RecordNo {
	
	public static final String YEAR_MONTH = "yyMM";
	public static final String DAY_HOUR = "ddHH";

	public static String create(String type,String dateValue){
		return type+DateUtil.dateToString(new Date(),dateValue)+(int)((Math.random()*9000000)+1000000);
	}

}
