package com.utils;

import org.springframework.beans.BeanUtils;

/**
 * @author LiQiang
 * @date 2016年10月31日
 */
public class BeanUtil{

	public static <T> void copyProperties(T source,T target){
		if(source!=null){
			BeanUtils.copyProperties(source, target);
		}
	}
	
}
