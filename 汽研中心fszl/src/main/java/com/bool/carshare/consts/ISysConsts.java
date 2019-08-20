/**
 * 
 */
package com.bool.carshare.consts;

import java.io.File;

/**
 * ISysConsts
 * @author wangw
 */
public interface ISysConsts {
	public static final String FILE_SEPARATOR = File.separator;
	
	/**
	 * 上传文件夹
	 */
	public static final String UPLOAD_FOLDER = "upload";
	
	/**
	 * 图片文件夹
	 */
	public static final String IMAGES_FOLDER = "images";
	
	/**
	 * 文件文件夹
	 */
	public static final String FILES_FOLDER = "files";
	
	/**
	 * 压缩包文件夹
	 */
	public static final String ZIP_FOLDER = "zip";
	
	/**
	 * 图片上传文件夹
	 */
	public static final String IMAGES_UPLOAD_FOLDER = UPLOAD_FOLDER + FILE_SEPARATOR + IMAGES_FOLDER;
	
	/**
	 * 文件上传文件夹
	 */
	public static final String FILES_UPLOAD_FOLDER = UPLOAD_FOLDER + FILE_SEPARATOR + FILES_FOLDER;
	
	/**
	 * 压缩包
	 */
	public static final String ZIP_UPLOAP_FOLDER = UPLOAD_FOLDER + FILE_SEPARATOR + ZIP_FOLDER;
}