/**
 * 
 */
package com.bool.carshare.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件工具类
 * @author wangw
 */
public class FileUtil {
	/**
	 * 创建目录
	 * @param folderPath 文件夹路径
	 * @return
	 */
	public static Boolean makeDirectory(String folderPath){
		File file = new File(folderPath);
		
		if(file.isDirectory()){
			return true;
		}else{
			Boolean makeFlag = file.mkdirs();
			
			return makeFlag;
		}
	}
	
	/**
	 * 上传文件
	 * @param file 文件
	 * @param folderPath 文件夹路径
	 * @param fileName 文件名称
	 * @return
	 */
	public static Boolean uploadFile(MultipartFile file, String folderPath, String fileName) {
		if(file==null || file.isEmpty()) {
			return false;
		}
		
		Boolean makeFlag = makeDirectory(folderPath);
		if(!makeFlag) {
			return false;
		}
		
		File targetFile = new File(folderPath+File.separator+fileName);
		try {
			file.transferTo(targetFile);
			
			return true;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * 复制某一文件到指定的目录下
	 * @param sourceFilePath 源文件路径
	 * @param targetFolderPath 目标文件夹路径
	 * @param targetFileName 目标文件名称
	 * @return
	 * @throws IOException 
	 */
	public static Boolean copyFile(String sourceFilePath, String targetFolderPath, String targetFileName) throws IOException {
		Boolean makeFlag = makeDirectory(targetFolderPath);
		if(!makeFlag) {
			return false;
		}
		
		File sourceFile = new File(sourceFilePath);
		File targetFile = new File(targetFolderPath+File.separator+targetFileName);
		
		FileInputStream in = null;
		FileChannel inFC = null;
		
		FileOutputStream out = null;
		FileChannel outFC = null;
		
		try {
			in = new FileInputStream(sourceFile);
			inFC = in.getChannel();
			
			out = new FileOutputStream(targetFile);
			outFC = out.getChannel();
			
			inFC.transferTo(0, inFC.size(), outFC);
			
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			in.close();
			inFC.close();
			
			out.close();
			outFC.close();
		}
		
		return false;
	}
	
	/**
	 * 获得某一文件的字节数组
	 * @param filePath 文件路径
	 * @return
	 * @throws IOException
	 */
	public static byte[] getFileByteArray(String filePath) throws IOException {
		InputStream in = null;
		BufferedInputStream buff = null;
		
		try {
			in = new FileInputStream(filePath);
			buff = new BufferedInputStream(in);
			byte[] byteArray = new byte[buff.available()];
			buff.read(byteArray);
			
			return byteArray;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			buff.close();
		}
		
		return null;
	}
	
	/**
	 * 获得某一文件
	 * @param filePath 文件路径
	 * @return
	 */
	public static File getFile(String filePath) {
		File file = new File(filePath);
		
		return file;
	}
	
	/**
	 * 获得某一文件的名称
	 * @param filePath 文件路径
	 * @return
	 */
	public static String getFileName(String filePath) {
		File file = getFile(filePath);
		String fileName = file.getName();
		
		return fileName;
	}
	
	/**
	 * 获得某一文件的类型
	 * @param fileName 文件名称
	 * @return
	 */
	public static String getFileType(String fileName) {
		if(fileName == null) {
			return null;
		}
		
		String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
		
		return fileType;
	}
	
	/**
	 * 是否是文件
	 * @param filePath 文件路径
	 * @return
	 */
	public static Boolean isFile(String filePath) {
		File file = getFile(filePath);
		if(file.isFile()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 下载文件
	 * @param filePath 文件路径
	 * @param downloadName 下载名称
	 * @param response 响应
	 * @throws IOException
	 */
	public static void downloadFile(String filePath, String downloadName, HttpServletResponse response) throws IOException {
		if(!isFile(filePath)) {
			ResponseUtil.alert(response, "文件不存在");
			
			return;
		}
		
		if("".equals(downloadName) || downloadName==null) {
			downloadName = getFileName(filePath);
		}else {
			String fileOriginalName = getFileName(filePath);
			String fileType = getFileType(fileOriginalName);
			
			downloadName += fileType==null?"":("."+fileType);
		}
		
		downloadName = URLEncoder.encode(downloadName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="+downloadName);
		response.setContentType("application/octet-stream");
		
		OutputStream out = null;
		try {
			byte[] fileByteArray = getFileByteArray(filePath);
			
			out = response.getOutputStream();
			out.write(fileByteArray);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
			
			ResponseUtil.alert(response, "下载文件失败");
		} finally {
			out.close();
		}
	}
	
	/**
	 * 下载多个文件（zip打包压缩）
	 * @param filePathArray 文件路径数组
	 * @param downloadName 下载名称
	 * @param response 响应
	 * @throws IOException
	 */
	public static void downloadFiles(String[] filePathArray, String downloadName, HttpServletResponse response) throws IOException {
		downloadName = URLEncoder.encode(downloadName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="+downloadName+".zip");
		response.setContentType("application/octet-stream");
		
		ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
		for(int i=0; i<filePathArray.length; i++) {
			File file = getFile(filePathArray[i]);
			ZipUtil.compressFiles(file, out);
			
			response.flushBuffer();
		}
		
		out.close();
	}
}