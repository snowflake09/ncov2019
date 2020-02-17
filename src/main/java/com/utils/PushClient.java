package com.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 极光推送，使用jar，gson-2.2.4.jar,jpush-client-3.2.9.jar,slf4j-log4j12-1.7.7.jar
 * 
 * @author LiQiang
 * @date 2016年11月4日
 */
public class PushClient {

	public static String appKey_shop = "";
	public static String masterSecret_shop = "";
	
	public static String appKey_personnel = "";
	public static String masterSecret_personnel = "";
	
	public static String appKey_customer = "";
	public static String masterSecret_customer = "";
	
	/**
	 * 推送【固定推送】
	 */
	public static final int PUSH = 1;
	/**
	 * 推送【推送所有】
	 */
	public static final int PUSH_ALL = 2;

	public static void main(String[] args){
		Map<String,String> map = new HashMap<String,String>();
		map.put("shopId","2");
		map.put("shopName", "欧美");
		map.put("type","-2");
		System.out.println(pushRegistrationId(masterSecret_shop,appKey_shop,1,"18171adc033d61ac00e","hello1","能看到我么",map));
	}
	
	/**
	 * 别名推送
	 * @param masterSecret 密钥
	 * @param appKey 帐号
	 * @param type 推送类型
	 * @param alias 推送目标
	 * @param content 通知内容
	 * @param message 消息
	 * @param map 推行内容
	 * @return
	 * @author LiQiang
	 * @date 2016年11月12日
	 */
	@SuppressWarnings("deprecation")
	public static boolean pushAlias(String masterSecret,String appKey,int type,String alias,String content,String message,Map<String,String> map){
		JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
		PushPayload payload = null;
		try {
			if(type==PUSH){
				payload = buildPushObject_all_alias_alert(alias,content,message,map);
			}else{
				if(!StringUtils.isEmpty(content)){
					payload = buildPushObject_all_all_content(content,message,map);
				}else{
					payload = buildPushObject_all_all_no_content(message,map);
				}
			}
			jpushClient.sendPush(payload);
			return true;
		} catch (APIConnectionException e) {
			
        } catch (APIRequestException e) {
            
        }
		return false;
	}
	
	/**
	 * 注册号推送
	 * @param masterSecret 密钥
	 * @param appKey 帐号
	 * @param type 推送类型
	 * @param registrationId 推送目标（设备注册号ID）
	 * @param content 通知内容
	 * @param message 消息
	 * @param map 推行内容
	 * @return
	 * @author LiQiang
	 * @date 2017年3月17日
	 */
	@SuppressWarnings("deprecation")
	public static boolean pushRegistrationId(String masterSecret,String appKey,int type,String registrationId,String content,String message,Map<String,String> map){
		JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
		PushPayload payload = null;
		try {
			if(type==PUSH){
				if(!StringUtils.isEmpty(content)){
					payload = buildPushObject_all_registrationId_content(registrationId,content,message,map);
				}else{
					payload = buildPushObject_all_registrationId_no_content(registrationId,message,map);
				}
			}else{
				if(!StringUtils.isEmpty(content)){
					payload = buildPushObject_all_all_content(content,message,map);
				}else{
					payload = buildPushObject_all_all_no_content(message,map);
				}
			}
			jpushClient.sendPush(payload);
			return true;
		} catch (APIConnectionException e) {
			
        } catch (APIRequestException e) {
            
        }
		return false;
	}
	
	/**
	 * 所有平台，目标推送（包含通知）
	 * @param registrationId
	 * @param content
	 * @param message
	 * @param map
	 * @return
	 * @author LiQiang
	 * @date 2017年3月17日
	 */
	public static PushPayload buildPushObject_all_registrationId_content(String registrationId,String content,String message,Map<String,String> map) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.registrationId(registrationId))
				.setNotification(Notification.alert(content)).setMessage(Message.newBuilder().setMsgContent(message).addExtras(map).build()).build();
	}
	
	/**
	 * 所有平台，目标推送（不包含通知）
	 * @param registrationId
	 * @param message
	 * @param map
	 * @return
	 * @author LiQiang
	 * @date 2017年3月17日
	 */
	public static PushPayload buildPushObject_all_registrationId_no_content(String registrationId,String message,Map<String,String> map) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.registrationId(registrationId)).setMessage(Message.newBuilder().setMsgContent(message).addExtras(map).build()).build();
	}

	/**
	 * 所有平台，所有设备（包含通知）
	 * @param content 推送信息
	 * @param message
	 * @param map
	 * @return
	 * @author LiQiang
	 * @date 2016年11月4日
	 */
	public static PushPayload buildPushObject_all_all_content(String content,String message,Map<String,String> map) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.all()).setNotification(Notification.alert(content))
				.setMessage(Message.newBuilder().setMsgContent(message).addExtras(map).build()).build();
	}
	
	/**
	 * 所有平台，所有设备（不包含通知）
	 * @param content 推送信息
	 * @param message
	 * @param map
	 * @return
	 * @author LiQiang
	 * @date 2016年11月4日
	 */
	public static PushPayload buildPushObject_all_all_no_content(String message,Map<String,String> map) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.all())
				.setMessage(Message.newBuilder().setMsgContent(message).addExtras(map).build()).build();
	}

	/**
	 * 所有平台，目标推送
	 * @param alias 目标别名
	 * @param content 推送信息
	 * @return
	 * @author LiQiang
	 * @date 2016年11月4日
	 */
	public static PushPayload buildPushObject_all_alias_alert(String alias, String content,String message,Map<String,String> map) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(alias))
				.setNotification(Notification.alert(content)).setMessage(Message.newBuilder().setMsgContent(message).addExtras(map).build()).build();
	}

	/**
	 * 平台是 Android，目标推送
	 * @param tag 目标设备
	 * @param content 推送信息
	 * @param title 推送标题
	 * @return
	 * @author LiQiang
	 * @date 2016年11月4日
	 */
	public static PushPayload buildPushObject_android_tag_alertWithTitle(String tag, String content, String title) {
		return PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.tag(tag))
				.setNotification(Notification.android(content, title, null)).build();
	}

	/**
	 * 平台是 iOS，推送目标是 "tag1", "tag_all" 的并集，推送内容同时包括通知与消息 - 通知信息是 ALERT，角标数字为
	 * 5，通知声音为 "happy"，并且附加字段 from = "JPush"；消息内容是 MSG_CONTENT。通知是 APNs
	 * 推送通道的，消息是 JPush 应用内消息通道的。APNs 的推送环境是“生产”（如果不显式设置的话，Library 会默认指定为开发）
	 * @return
	 * @author LiQiang
	 * @date 2016年11月4日
	 */
	public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
		return PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(Audience.tag_and("tag1", "tag_all"))
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(IosNotification.newBuilder().setAlert("内容").setBadge(5)
								.setSound("happy").addExtra("from", "JPush").build())
						.build())
				.setMessage(Message.content("消息内容")).setOptions(Options.newBuilder().setApnsProduction(true).build())
				.build();
	}

	/**
	 * 平台是 Andorid 与 iOS，推送目标是 （"tag1" 与 "tag2" 的交集）并（"alias1" 与 "alias2"
	 * 的交集），推送内容是 - 内容为 MSG_CONTENT 的消息，并且附加字段 from = JPush。
	 * @return
	 * @author LiQiang
	 * @date 2016年11月4日
	 */
	public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
		return PushPayload.newBuilder().setPlatform(Platform.android_ios())
				.setAudience(Audience.newBuilder().addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
						.addAudienceTarget(AudienceTarget.alias("alias1", "alias2")).build())
				.setMessage(Message.newBuilder().setMsgContent("消息内容").addExtra("from", "JPush").build()).build();
	}
	
}
