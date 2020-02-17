package com.converter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonInputMessage;
import org.springframework.util.StringUtils;

import com.framework.exception.ServiceException;
import com.utils.DateUtil;
import com.utils.EmojiFilter;
import com.utils.MD5;
import com.utils.em.CodeType;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JavaType;

/**
 * 数据转换
 * 
 * @author LiQiang
 * @date 2017年3月2日
 */
public class DataToBeanConverter extends MappingJackson2HttpMessageConverter implements MessageConverterHandler<Object>{

	@Override
	public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		JavaType javaType = getJavaType(type, contextClass);
		Object object = readJavaType(javaType, inputMessage);
//		String xType = inputMessage.getHeaders().getFirst("x-type");
//		this.readAfter(object);
		return object;
	}

	@SuppressWarnings("deprecation")
	private Object readJavaType(JavaType javaType, HttpInputMessage inputMessage) {
		try {
			if (inputMessage instanceof MappingJacksonInputMessage) {
				Class<?> deserializationView = ((MappingJacksonInputMessage) inputMessage).getDeserializationView();
				if (deserializationView != null) {
					return this.objectMapper.readerWithView(deserializationView).withType(javaType).readValue(inputMessage.getBody());
				}
			}
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();  
//			byte[] buffer = new byte[1024];  
//			int len;  
//			while ((len = inputMessage.getBody().read(buffer)) > -1 ) {  
//			    baos.write(buffer, 0, len);  
//			}  
//			baos.flush();
//			//拷贝数据流
//			InputStream isCheck = new ByteArrayInputStream(baos.toByteArray());
//			BufferedReader reader = new BufferedReader(new InputStreamReader(isCheck,"UTF-8"));
//			StringBuilder sb = new StringBuilder();
//			String line = null;
//			while ((line = reader.readLine()) != null) {
//				sb.append(line + "\n");
//			}
//			this.readAfter(sb);
//			InputStream isBody = new ByteArrayInputStream(baos.toByteArray());
//			baos.close();
			//转义字符
			this.objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
			return this.objectMapper.readValue(inputMessage.getBody(), javaType);
		}
		catch (IOException ex) {
			throw new HttpMessageNotReadableException("Could not read document: " + ex.getMessage(), ex);
		}
	}

	@Override
	public void readAfter(Object obj) {
		Map<String,String> map = beanToMap(obj);
		if(map.size()!=0){
			if(!MD5.checkSign(map)){
				throw new ServiceException(CodeType.ILLEGAL_VISIT.getLabel());
			}
		}
	}
	
	private Map<String,String> beanToMap(Object obj) {
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
						if(!(value instanceof Collection)){
							if(value instanceof Date){
								map.put(key,DateUtil.dateToString((Date)value, DateUtil.FORMAT_DATE_TIME));
							}else if(value instanceof String){
//								if(EmojiFilter.containsEmoji(String.valueOf(value))){
//									throw new ServiceException("内容不能包含表情");
//								}
								String v = EmojiFilter.filterEmoji(String.valueOf(value));
								if(!StringUtils.isEmpty(value)){
									map.put(key,v);
								}
							}else{
								map.put(key,String.valueOf(value));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return map;
	}
	
}
