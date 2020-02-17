package com.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class StringToDateConverter implements Converter<String, Date> {
	
	private String dateFormat = "yyyy-MM-dd HH:mm:ss";
	
	public StringToDateConverter(String dateFormat){
		this.dateFormat = dateFormat;
	}

	public StringToDateConverter() {
		super();
	}

	@Override
	public Date convert(String source) {
		if(!StringUtils.isEmpty(source)){
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sdf.setLenient(false);
			try {
				return sdf.parse(source);
			} catch (ParseException e) {
				e.printStackTrace();
			}	
		}
		return null;
	}

}
