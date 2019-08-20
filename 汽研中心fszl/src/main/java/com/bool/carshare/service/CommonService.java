/**
 * 
 */
package com.bool.carshare.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.bool.carshare.bean.ResultBean;

/**
 * CommonService
 * @author wangw
 */
public interface CommonService {
	/**
	 * 上传照片
	 * @param file 上传文件
	 * @param request 请求
	 * @return
	 */
	public ResultBean uploadPhoto(MultipartFile file, HttpServletRequest request);
	
	/**
	 * 上传文件
	 * @param file 上传文件
	 * @param request 请求
	 * @return
	 */
	public ResultBean uploadFile(MultipartFile file, HttpServletRequest request);
	
	/**
	 * 下载文件
	 * @param filePath
	 * @param downloadName
	 * @param response
	 * @throws IOException
	 */
	public void downloadFile(String filePath, String downloadName, HttpServletResponse response) throws IOException;
}