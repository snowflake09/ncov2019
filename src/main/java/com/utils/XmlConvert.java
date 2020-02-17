package com.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import com.utils.wechatpay.WeChatPayReturnModel;

/**
 * xml转换类
 * 
 * @author LiQiang
 * @date 2016年12月15日
 */

public class XmlConvert {

	/**
	 * javaBean转Xml
	 * 
	 * @param t
	 * @param cdata1
	 * @return
	 * @author LiQiang
	 * @date 2016年12月15日
	 */
	public static <T> String beanToXml(T t, final boolean cdata1) {
		XStream xstream = new XStream(new XppDriver(new NoNameCoder()) {
			@Override
			public HierarchicalStreamWriter createWriter(Writer out) {
				return new PrettyPrintWriter(out) {
					// 对所有xml节点的转换都增加CDATA标记
					boolean cdata = cdata1;

					@Override
					@SuppressWarnings("rawtypes")
					public void startNode(String name, Class clazz) {
						super.startNode(name, clazz);
					}

					@Override
					public String encodeNode(String name) {
						return name;
					}

					@Override
					protected void writeText(QuickWriter writer, String text) {
						if (cdata) {
							writer.write("<![CDATA[");
							writer.write(text);
							writer.write("]]>");
						} else {
							writer.write(text);
						}
					}
				};
			}
		});
		String xml = xstream.toXML(t);
		return xml;
	};

	/**
	 * xml转javaBean
	 * 
	 * @param c
	 * @param xml
	 * @return
	 * @author LiQiang
	 * @date 2016年12月28日
	 */
	@SuppressWarnings("rawtypes")
	public static Object xmlToBean(Class c, String xml) {
		XStream xStream = new XStream(){
		    @Override
		    protected MapperWrapper wrapMapper(MapperWrapper next) {
		        return new MapperWrapper(next) {
		            @Override
		            public boolean shouldSerializeMember(Class definedIn, String fieldName) {
		                if (definedIn == Object.class) {
		                    return false;
		                }
		                return super.shouldSerializeMember(definedIn, fieldName);
		            }
		        };
		    }
		};
		xStream.alias("xml", c);
		xStream.processAnnotations(c);
		return xStream.fromXML(xml);
	}

	/**
	 * xml转javaBean
	 * 
	 * @param c
	 * @param is
	 * @return
	 * @author LiQiang
	 * @date 2016年12月28日
	 */
	@SuppressWarnings("rawtypes")
	public static Object xmlToBean(Class c, InputStream is) {
		XStream xStream = new XStream(){
		    @Override
		    protected MapperWrapper wrapMapper(MapperWrapper next) {
		        return new MapperWrapper(next) {
		            @Override
		            public boolean shouldSerializeMember(Class definedIn, String fieldName) {
		                if (definedIn == Object.class) {
		                    return false;
		                }
		                return super.shouldSerializeMember(definedIn, fieldName);
		            }
		        };
		    }
		};
		xStream.alias("xml", c);
		xStream.processAnnotations(c);
		return xStream.fromXML(is);
	}

	/**
	 * xml转map
	 * @param xml
	 * @return
	 * @author LiQiang
	 * @date 2017年1月5日
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> xmlToMap(String xml) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			Iterator<Element> it = root.elementIterator();
			while (it.hasNext()) {
				Element element = it.next();
				map.put(element.getName(), element.getTextTrim());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static void main(String[] args) {
		String xml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx7db0291ced506b56]]></appid><mch_id><![CDATA[1508811931]]></mch_id><nonce_str><![CDATA[80ledh0G4qLrMtyT]]></nonce_str><sign><![CDATA[925F2F46B31303A42910B8DC1B54F504]]></sign><result_code><![CDATA[SUCCESS]]></result_code><openid><![CDATA[oshfn0XxRTiSFYLdeptEDUcso-40]]></openid><is_subscribe><![CDATA[N]]></is_subscribe><trade_type><![CDATA[JSAPI]]></trade_type><bank_type><![CDATA[PAB_CREDIT]]></bank_type><total_fee>20000</total_fee><coupon_fee>500</coupon_fee><fee_type><![CDATA[CNY]]></fee_type><transaction_id><![CDATA[4200000438201911177694391601]]></transaction_id><out_trade_no><![CDATA[HD191117193256200]]></out_trade_no><attach><![CDATA[]]></attach><time_end><![CDATA[20191117195030]]></time_end><trade_state><![CDATA[SUCCESS]]></trade_state><coupon_id_0><![CDATA[8145513523]]></coupon_id_0><coupon_fee_0>500</coupon_fee_0><coupon_count>1</coupon_count><cash_fee>19500</cash_fee><trade_state_desc><![CDATA[支付成功]]></trade_state_desc><cash_fee_type><![CDATA[CNY]]></cash_fee_type></xml>";
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		WeChatPayReturnModel weChatPayReturnModel = (WeChatPayReturnModel) xmlToBean(WeChatPayReturnModel.class, is);
		System.out.println(JSON.toJSONString(weChatPayReturnModel));
	}

}
