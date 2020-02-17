package com.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * map转换工具类
 * 
 * @author LiQiang
 * @date 2017年1月5日
 */
public class MapConvert {

	/**
	 * JavaBeanToMap
	 * 
	 * @param obj
	 * @return
	 * @author LiQiang
	 * @date 2016年12月15日
	 */
	public static Map<String,String> beanToMapString(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String,String> map = new HashMap<String,String>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					if (!StringUtils.isEmpty(value)) {
						map.put(key, value.toString());
					}
				}
			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}
		return map;
	}
	
	/**
	 * JavaBeanToMap
	 * 
	 * @param obj
	 * @return
	 * @author LiQiang
	 * @date 2016年12月15日
	 */
	public static Map<String,Object> beanToMapObject(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					if (!StringUtils.isEmpty(value)) {
						map.put(key, value);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}
		return map;
	}

	/**
	 * MapToJavaBean
	 * @param type
	 * @param map
	 * @return
	 * @author LiQiang
	 * @date 2016年12月15日
	 */
	@SuppressWarnings("rawtypes")
	public static Object convertMap(Class type, Map map) {
		BeanInfo beanInfo;
		Object obj = new Object();
		try {
			beanInfo = Introspector.getBeanInfo(type);
			obj = type.newInstance(); // 创建 JavaBean 对象
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();

				if (map.containsKey(propertyName)) {
					// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
					Object value = map.get(propertyName);

					Object[] args = new Object[1];
					args[0] = value;

					descriptor.getWriteMethod().invoke(obj, args);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
}
