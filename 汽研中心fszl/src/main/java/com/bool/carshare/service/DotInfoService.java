package com.bool.carshare.service;

import com.bool.carshare.bean.WebObject;
import com.bool.carshare.entity.DotInfo;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.Result;

public interface DotInfoService {

	
	Result getDotInfos(PageRequest<DotInfo> pageRequest);
	
	Result updateDotInfo(DotInfo dotInfo, WebObject webObject);
	
	Result saveDotInfo(DotInfo dotInfo, WebObject webObject);
	
	Result getDotInfosAll();
	
	Result deleteDotInfo(Integer dot_id, WebObject webObject);
	
	Result appUpdateDot(Integer dot_id);
}
