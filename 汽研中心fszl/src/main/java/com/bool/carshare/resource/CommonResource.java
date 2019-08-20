/**
 * 
 */
package com.bool.carshare.resource;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bool.carshare.bean.ResultBean;
import com.bool.carshare.controller.UniversalAbstractController;
import com.bool.carshare.service.CommonService;
import com.bool.carshare.util.Message;
import com.bool.carshare.util.Result;

/**
 * CommonResource
 * @author wangw
 */
@RestController
@RequestMapping("carshare")
public class CommonResource extends UniversalAbstractController{
	private static final Logger logger = LoggerFactory.getLogger(CommonResource.class);
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value="common",method=RequestMethod.POST)
	public void common(HttpServletRequest request, HttpServletResponse response) {
		this.initController(request, response);
		
		this.invokeMethod(CommonService.class, this.commonService);
	}
	
	/**
	 * 上传照片
	 * @param file 上传文件
	 * @param request 请求
	 * @return
	 */
	@RequestMapping(value="uploadPhoto",method={RequestMethod.POST})
	public Result uploadPhoto(@RequestParam("file")MultipartFile file, HttpServletRequest request){
		ResultBean resultBean = this.commonService.uploadPhoto(file, request);
		
		if(resultBean.getStatus()) {
			return Result.ResultBuilder.buildSuccessResult(Message.OK, resultBean.getData());
		}
		
		return Result.ResultBuilder.buildFailerResult(Message.ERROR, null);
	}
	
	/**
	 * 上传文件
	 * @param file 上传文件
	 * @param request 请求
	 * @return
	 */
	@RequestMapping(value="uploadFile",method={RequestMethod.POST})
	public Result uploadFile(@RequestParam("file")MultipartFile file, HttpServletRequest request){
		ResultBean resultBean = this.commonService.uploadFile(file, request);
		
		if(resultBean.getStatus()) {
			return Result.ResultBuilder.buildSuccessResult(Message.OK, resultBean.getData());
		}
		
		return Result.ResultBuilder.buildFailerResult(Message.ERROR, null);
	}
	
	/**
	 * 下载文件
	 * @param filePath
	 * @param downloadName
	 * @param response
	 */
	@RequestMapping(value="downloadFile",method={RequestMethod.POST})
	public void downloadFile(@RequestParam("filePath")String filePath, @RequestParam("downloadName")String downloadName, HttpServletResponse response) {
		try {
			this.commonService.downloadFile(filePath, downloadName, response);
		} catch (IOException e) {
			e.printStackTrace();
			
			logger.error("下载文件失败");
		}
	}
}