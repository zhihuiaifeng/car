package com.bool.carshare.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RunTimeByOrder {

	 public  static String   getTimeDifference  (String startTime , String  endTime) {
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String result = null;
	        try{
	            Date d1 = df.parse(endTime);
	            Date d2 = df.parse(startTime);
	            long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
	            if(diff<0){
	            	result="error";
	            }else{
		            long hours = diff/(1000* 60 * 60);
		            long minutes = (diff-hours*(1000* 60 * 60))/(1000* 60);
		            long second = (diff-hours*(1000* 60 * 60)-minutes*(1000* 60))/(1000);
		            result =String.valueOf(hours)+":" +String.valueOf(minutes)+":"+String.valueOf(second);
	            }
	        }catch (Exception e){
	        	e.printStackTrace();
	        	result = "timeError";
	        }
	        return result;
	    }

}
