package com.bool.carshare.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bool.carshare.util.Result;

/**
 * 
 * 文件上传和下载
 *
 */
public class FileResource {

	public Result uploadZip(@RequestParam("file-zip")MultipartFile file, HttpServletRequest request){
		
		
		return null;
	}
}
