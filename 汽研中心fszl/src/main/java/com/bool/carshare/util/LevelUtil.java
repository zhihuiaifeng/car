package com.bool.carshare.util;
/**
 * 
 * @author 73482
 *
 */
public class LevelUtil {

	public static String getUserLevel(Integer totalCredit){
		//总积分
		if(totalCredit != null && totalCredit<500){
			return "1";
		}else if(totalCredit != null && totalCredit<1500 && totalCredit>500){
			return "2";
		}else if(totalCredit != null && totalCredit<3000 && totalCredit>1500){
			return "3";
		}else if(totalCredit != null && totalCredit<5000 && totalCredit>3000){
			return "4";
		}else if(totalCredit != null && totalCredit<10000 && totalCredit>5000){
			return "5";
		}else if(totalCredit != null && totalCredit>10000){
			return "6";
		}else{
			return "error";
		}
		
	}
}
