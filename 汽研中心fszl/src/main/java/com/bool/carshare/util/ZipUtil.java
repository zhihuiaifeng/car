/**
 * 
 */
package com.bool.carshare.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Zip压缩工具类
 * @author wangw
 */
public class ZipUtil {
	/**
	 * 压缩
	 * @param sourceFilePath 源文件路径
	 * @param targetFolderPath 目标文件夹路径
	 * @param fileName 文件名称
	 * @throws IOException
	 */
	public static void compress(String sourceFilePath, String targetFolderPath, String fileName) throws IOException {
		File sourceFile = new File(sourceFilePath);
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(targetFolderPath+File.separator+fileName+".zip"));
		compressFiles(sourceFile, out);
		out.close();
	}
	
	/**
	 * 压缩多个文件
	 * @param file 文件
	 * @param out 输出
	 * @throws IOException
	 */
	public static void compressFiles(File file, ZipOutputStream out) throws IOException {
		compressFiles(file, out, file.getName());
	}
	
	/**
	 * 压缩多个文件
	 * @param file 文件
	 * @param out 输出
	 * @param zipEntryName 压缩入口名称
	 * @throws IOException
	 */
	public static void compressFiles(File file, ZipOutputStream out, String zipEntryName) throws IOException {
		if(file.isFile()) {
			compressFile(file, out, zipEntryName);
		}else {
			File[] files = file.listFiles();
			if(files==null || files.length==0) {
				return;
			}
			
			for(File singleFile:files) {
				compressFiles(singleFile, out, zipEntryName+File.separator+singleFile.getName());
			}
		}
	}
	
	/**
	 * 压缩文件
	 * @param file 文件
	 * @param out 输出
	 * @param zipEntryName 压缩入口名称
	 * @throws IOException
	 */
	private static void compressFile(File file, ZipOutputStream out, String zipEntryName) throws IOException {
		InputStream in = new FileInputStream(file);
		BufferedInputStream buff = new BufferedInputStream(in);
		byte[] data = new byte[buff.available()];
		buff.read(data);
		buff.close();
		
		ZipEntry zipEntry = new ZipEntry(zipEntryName);
		out.putNextEntry(zipEntry);
		out.write(data);
		out.flush();
		out.closeEntry();
	}
}