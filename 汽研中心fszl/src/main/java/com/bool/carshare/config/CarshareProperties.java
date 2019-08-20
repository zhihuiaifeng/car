/**
 * 
 */
package com.bool.carshare.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 属性配置
 * @author wangw
 */
@Component
@ConfigurationProperties("carshare")
public class CarshareProperties {
	/**
	 * 服务名称
	 */
	private String serverName;
	
	/**
	 * 资源文件夹
	 */
	private String resourceFolder;
	
	/**
	 * 服务URL
	 */
	private String serverURL;
	
	/**
	 * 上传备份路径
	 */
	private String backupUploadPath;
	
	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getResourceFolder() {
		return resourceFolder;
	}

	public void setResourceFolder(String resourceFolder) {
		this.resourceFolder = resourceFolder;
	}

	public String getServerURL() {
		return serverURL;
	}

	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}

	public String getBackupUploadPath() {
		return backupUploadPath;
	}

	public void setBackupUploadPath(String backupUploadPath) {
		this.backupUploadPath = backupUploadPath;
	}
}