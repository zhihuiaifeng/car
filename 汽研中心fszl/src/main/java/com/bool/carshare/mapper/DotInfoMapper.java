package com.bool.carshare.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bool.carshare.entity.DotInfo;
import com.bool.carshare.util.PageRequest;

@Mapper
@Repository("dotInfoDao")
public interface DotInfoMapper {

	
	void saveDotInfo(DotInfo dotInfo);
	
	List<DotInfo> getDotInfos(PageRequest<DotInfo> pageRequest);
	
	void updateDotInfo(DotInfo dotInfo);
	
	Integer getDotInfosNum(DotInfo dotInfo);
	
	List<DotInfo> getDotInfosAll();
	
	int deleteDotInfo(@Param(value = "dot_id")Integer dot_id);

	DotInfo getDotInfoById(@Param(value = "dot_id")Integer dot_id);
}
