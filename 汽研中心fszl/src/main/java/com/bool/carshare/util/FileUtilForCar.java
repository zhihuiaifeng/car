/**
 * 
 */
package com.bool.carshare.util;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import com.bool.carshare.entity.CarInfo;

/**
 * 文件工具类（车辆管理）
 * @author wangw
 */
public class FileUtilForCar {
	/**
	 * 获得下载附件的名称
	 * @param carInfo 车辆信息
	 * @return
	 */
	public static String getDownloadAttachmentName(CarInfo carInfo) {
		if(carInfo == null) {
			return null;
		}
		
		File file = FileUtil.getFile(carInfo.getAttachment());
		long lastModifiedTime = file.lastModified();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(lastModifiedTime);
		String lastModifiedDateTime = DateUtil.toString(calendar.getTime(), "yyyyMMdd");
		String attachmentName = carInfo.getCvin() + "_" + lastModifiedDateTime;
		
		return attachmentName;
	}
	
	/**
	 * 下载多个文件（zip打包压缩）
	 * @param carInfoList 车辆信息列表
	 * @param downloadName 下载名称
	 * @param response 响应
	 * @throws IOException
	 */
	public static void downloadFiles(List<CarInfo> carInfoList, String downloadName, HttpServletResponse response) throws IOException {
		downloadName = URLEncoder.encode(downloadName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="+downloadName+".zip");
		response.setContentType("application/octet-stream");
		
		ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
		for(int i=0; i<carInfoList.size(); i++) {
			CarInfo carInfo = carInfoList.get(i);
			
			if(carInfo != null) {
				String attachment = carInfo.getAttachment();
				File file = FileUtil.getFile(attachment);
				
				String fileOriginalName = FileUtil.getFileName(attachment);
				String fileType = FileUtil.getFileType(fileOriginalName);
				String downloadAttachmentName = getDownloadAttachmentName(carInfo);
				String zipEntryName = downloadAttachmentName + (fileType==null?"":("."+fileType));
				
				ZipUtil.compressFiles(file, out, zipEntryName);
				response.flushBuffer();
			}
		}
		
		out.close();
	}
}