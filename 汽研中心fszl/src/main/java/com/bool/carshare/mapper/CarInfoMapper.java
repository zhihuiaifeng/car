package com.bool.carshare.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bool.carshare.entity.CarInfo;
import com.bool.carshare.util.PageRequest;

@Mapper
@Repository("carInfoDao")
public interface CarInfoMapper {
	
	void saveCarInfo(CarInfo carInfo);
	
	List<CarInfo> getCarInfos(CarInfo condition);
	
	int updateCarInfoById(CarInfo carInfo);
	
	CarInfo getCarInfoById(@Param("cid")Integer cid);
	
	int findCarsByDotId(@Param("dotId") Integer dotId);

	List<CarInfo> findCarInfoAll(PageRequest<CarInfo> pageRequest);
	
	int findCarInfoAllNum(CarInfo carInfo);
	
	List<String> findCarModel();
	
	int deleteCarInfo(@Param("cids")int[] cids);
	
	int upCarInfoByTerminal(CarInfo carInfo);
	/**
	 * 设置车辆的运营状态
	 * @param plateNumber
	 * @param runState
	 * @return
	 */
	public int setCarRunState(@Param("plateNumber")String plateNumber, @Param("runState")String runState);
	
	CarInfo getCarInfoByVin(@Param("cvin")String cvin);
	
	/**
	 * 上传附件
	 * @param carID 车辆ID
	 * @param storageLocation 存储位置
	 * @return
	 */
	public int uploadAttachment(@Param("carID")Integer carID, @Param("storageLocation")String storageLocation);
	
	/**
	 * 获得车辆信息列表
	 * @param carIDArray 车辆ID数组
	 * @return
	 */
	public List<CarInfo> getCarInfoList(@Param("carIDArray")int[] carIDArray);
}
