/**
 * 
 */
package com.bool.carshare.service.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bool.carshare.bean.ResultBean;
import com.bool.carshare.config.CarshareProperties;
import com.bool.carshare.consts.ISysConsts;
import com.bool.carshare.service.CommonService;
import com.bool.carshare.util.FileUtil;
import com.bool.carshare.util.UUIDUtils;

/**
 * CommonServiceImpl
 * @author wangw
 */
@Service
public class CommonServiceImpl implements CommonService{
	@Autowired
	private CarshareProperties carshareProperties;
	
	/**
	 * 上传照片
	 * @param file 上传文件
	 * @param request 请求
	 * @return
	 */
	@Override
	@Transactional
	public ResultBean uploadPhoto(MultipartFile file, HttpServletRequest request) {
		ResultBean resultBean = new ResultBean();
		
		String sysPath = request.getServletContext().getRealPath("");
		//上传路径
		String uploadPath = sysPath.replace(this.carshareProperties.getServerName(), this.carshareProperties.getResourceFolder());
		//上传文件夹
		String uploadFolder = ISysConsts.IMAGES_UPLOAD_FOLDER;
		String uuid = UUIDUtils.uuid();
		//文件名称
		String fileName = uuid + "-" + file.getOriginalFilename();
		//备份路径
		String backupPath = this.carshareProperties.getBackupUploadPath();
		//备份文件夹
		String backupFolder = uploadFolder;
		String uploadFolderPath = uploadPath + ISysConsts.FILE_SEPARATOR + uploadFolder;
		
		//上传照片
		Boolean uploadFlag = FileUtil.uploadFile(file, uploadFolderPath, fileName);
		if(uploadFlag) {
			//备份照片
			try {
				String backupFolderPath = backupPath + ISysConsts.FILE_SEPARATOR + backupFolder;
				
				FileUtil.copyFile(uploadFolderPath+ISysConsts.FILE_SEPARATOR+fileName, backupFolderPath, fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//访问照片的网络地址
			String webAddress = this.carshareProperties.getServerURL() + ISysConsts.FILE_SEPARATOR + this.carshareProperties.getResourceFolder() + ISysConsts.FILE_SEPARATOR + uploadFolder + ISysConsts.FILE_SEPARATOR + fileName;
			
			resultBean.setStatus(true);
			resultBean.setData(webAddress);
			
			return resultBean;
		}else {
			resultBean.setStatus(false);
			
			return resultBean;
		}
	}
	
	/**
	 * 上传文件
	 * @param file 上传文件
	 * @param request 请求
	 * @return
	 */
	@Override
	@Transactional
	public ResultBean uploadFile(MultipartFile file, HttpServletRequest request) {
		ResultBean resultBean = new ResultBean();
		
		String sysPath = request.getServletContext().getRealPath("");
		//上传路径
		String uploadPath = sysPath.replace(this.carshareProperties.getServerName(), this.carshareProperties.getResourceFolder());
		//上传文件夹
		String uploadFolder = ISysConsts.FILES_UPLOAD_FOLDER;
		String uuid = UUIDUtils.uuid();
		//文件名称
		String fileName = uuid + "-" + file.getOriginalFilename();
		//备份路径
		String backupPath = this.carshareProperties.getBackupUploadPath();
		//备份文件夹
		String backupFolder = uploadFolder;
		String uploadFolderPath = uploadPath + ISysConsts.FILE_SEPARATOR + uploadFolder;
		
		//上传文件
		Boolean uploadFlag = FileUtil.uploadFile(file, uploadFolderPath, fileName);
		if(uploadFlag) {
			//备份文件
			try {
				String backupFolderPath = backupPath + ISysConsts.FILE_SEPARATOR + backupFolder;
				
				FileUtil.copyFile(uploadFolderPath+ISysConsts.FILE_SEPARATOR+fileName, backupFolderPath, fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//文件的存储位置
			String storageLocation = uploadFolderPath + ISysConsts.FILE_SEPARATOR + fileName;
			
			resultBean.setStatus(true);
			resultBean.setData(storageLocation);
			
			return resultBean;
		}else {
			resultBean.setStatus(false);
			
			return resultBean;
		}
	}
	
	/**
	 * 下载文件
	 * @param filePath
	 * @param downloadName
	 * @param response
	 * @throws IOException
	 */
	@Override
	@Transactional(readOnly=true)
	public void downloadFile(String filePath, String downloadName, HttpServletResponse response) throws IOException {
		FileUtil.downloadFile(filePath, downloadName, response);
	}
}