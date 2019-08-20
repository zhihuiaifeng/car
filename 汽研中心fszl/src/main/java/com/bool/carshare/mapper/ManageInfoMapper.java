package com.bool.carshare.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bool.carshare.entity.ManageInfo;
import com.bool.carshare.entity.NodeInfo;
import com.bool.carshare.entity.RoleInfo;
import com.bool.carshare.util.PageRequest;

@Mapper
@Repository("manageInfoMapper")
public interface ManageInfoMapper {
	public int saveAdminInfo(ManageInfo manageInfo);
	
	public List<ManageInfo> getAllAdminInfo(PageRequest<ManageInfo> pageRequest);
	
	public int getAllAdminInfoNum(ManageInfo manageInfo);
	
	public int updateAdminInfo(ManageInfo manageInfo);
	
	public int updatePassword(@Param("manageID")Integer manageID,
			@Param("newPassword")String newPassword);
	
	public ManageInfo login(@Param("manageLogin")String manageLogin,
			@Param("password")String password); 
	
	public int getEqualUser(@Param("manageLogin")String manageLogin);
	
	public int deleteAdminInfo(@Param("manageId")Integer manageId);
	
	public int saveManageNode(@Param("manageId")Integer manageId,
			@Param("nodeList")List<NodeInfo> nodeList);
	
	public int deleteManageNode(@Param("manageId")Integer manageId);
	
	public ManageInfo getManageInfo(@Param("manageID")Integer manageID);
	
	public List<NodeInfo> getNodeList(@Param("manageID")Integer manageID);
	
	public List<RoleInfo> getRoleList(@Param("manageID")Integer manageID);
}
