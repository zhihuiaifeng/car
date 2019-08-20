/**
 * 
 */
package com.bool.carshare.util;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * WebUtil
 * @author wangw
 */
public class WebUtil {
	/**
	 * 请求中保存JSONRequest对象的Key
	 */
	public static final String JSON_REQUEST_DATA_KEY = "data";
	
	/**
	 * 发送JSON数据
	 * @param jsonResponse
	 * @param response
	 */
	public static void sendJSONData(JSONResponse jsonResponse, HttpServletResponse response) {
		String jsonResponseData = JSON.toJSONString(jsonResponse, SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullListAsEmpty);
		
		sendJSONData(jsonResponseData, response);
	}
	
	/**
	 * 发送JSON数据
	 * @param jsonData
	 * @param response
	 */
	public static void sendJSONData(String jsonData, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		
		ServletOutputStream out;
		try {
			response.flushBuffer();
			out = response.getOutputStream();
			byte[] jsonDataBytes = jsonData.getBytes("utf-8");
			out.write(jsonDataBytes);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
		}
	}
	
	/**
	 * 获得参数对象中的值
	 * @param para
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object getParaValue(Object para, String key) {
		Map<String, Object> paraMap = (Map<String, Object>) para;
		Object paraValue = paraMap.get(key);
		
		return paraValue;
	}
	
	/**
	 * 从请求中获得JSONRequest对象
	 * @param request
	 * @return
	 */
	public static JSONRequest getJSONRequest(HttpServletRequest request) {
		String jsonRequestData = request.getParameter(JSON_REQUEST_DATA_KEY);
		
		JSONRequest jsonRequest = JSON.parseObject(jsonRequestData, JSONRequest.class);
		if(jsonRequest == null) {
			jsonRequest = new JSONRequest();
		}
		
		return jsonRequest;
	}
}