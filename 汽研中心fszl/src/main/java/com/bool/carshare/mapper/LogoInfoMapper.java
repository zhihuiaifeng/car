/**
 * 
 */
package com.bool.carshare.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.bool.carshare.entity.LogoInfo;

/**
 * LogoInfoMapper
 * @author wangw
 */
@Mapper
public interface LogoInfoMapper {
	/**
	 * 保存Logo
	 * @param logoInfo
	 * @return
	 */
	public int saveLogoInfo(LogoInfo logoInfo);
	
	/**
	 * 查询Logo
	 * @param userID
	 * @return
	 */
	public LogoInfo getLogoInfo(int userID);
	
	/**
	 * 删除Logo
	 * @param userID
	 * @return
	 */
	public int deleteLogoInfo(int userID);
}