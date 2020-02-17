package com.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 递归算法获取树形json
 * 
 * @author LiQiang
 * @date 2016年11月3日
 */
public class TreeJson {

	public static JSONArray treeMenuList(JSONArray menuList, int parentId) {
		JSONArray childMenu = new JSONArray();
		for (Object object : menuList) {
			JSONObject jsonMenu = (JSONObject) JSONObject.toJSON(object);
			int menuId = jsonMenu.getIntValue("id");
			int pid = jsonMenu.getIntValue("parentId");
			if (parentId == pid) {
				JSONArray c_node = treeMenuList(menuList, menuId);
				jsonMenu.put("list", c_node);
				childMenu.add(jsonMenu);
			}
		}
		return childMenu;
	}
	
	public static void main(String args[]) {  
	       JSONArray jsonArray = new JSONArray();  
	       JSONObject jsonObject = new JSONObject();
	       jsonObject.put("id", 1);
	       jsonObject.put("parentId", 0);
	       jsonArray.add(jsonObject);  
	       JSONObject jsonObject1 = new JSONObject();
	       jsonObject1.put("id", 2);
	       jsonObject1.put("parentId",0);
	       jsonArray.add(jsonObject1); 
	       JSONObject jsonObject2 = new JSONObject();
	       jsonObject2.put("id", 3);
	       jsonObject2.put("parentId",1);
	       jsonArray.add(jsonObject2); 
	       JSONObject jsonObject3 = new JSONObject();
	       jsonObject3.put("id", 4);
	       jsonObject3.put("parentId",3);
	       jsonArray.add(jsonObject3); 
	  
	       System.out.print(TreeJson.treeMenuList(jsonArray,0));  
	   }  

}
