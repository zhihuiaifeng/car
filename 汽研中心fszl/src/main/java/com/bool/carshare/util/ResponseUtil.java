/**
 * 
 */
package com.bool.carshare.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * 响应工具类
 * @author wangw
 */
public class ResponseUtil {
	/**
	 * 提示
	 * @param response
	 * @param content
	 */
	public static void alert(HttpServletResponse response, String content) {
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
			
			out.write("<script>alert('" + content + "');</script>");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}