package com.bool.carshare.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bool.carshare.entity.CarInfo;

public class ByteAnalysis {
	public static CarInfo getLLByLL(CarInfo carInfo) throws JSONException{
		String coords = carInfo.getLongitude()+","+carInfo.getLatitude();
		String url = "http://api.map.baidu.com/geoconv/v1/?coords="+coords+"&from=1&to=5&ak=Dfjx9AGpG8abAea38d66swjHlWEBNZou&qq-pf-to=pcqq.temporaryc2c";
		String json = loadJSON(url);
		JSONObject obj = new JSONObject(json);
		if(obj.get("status").toString().equals("0")){
			JSONArray jsonArray = obj.getJSONArray("result");
			JSONObject obj2 = jsonArray.getJSONObject(0);
			String lng = obj2.get("x").toString();
			String lat = obj2.get("y").toString();
			carInfo.setLongitude(lng);
			carInfo.setLatitude(lat);
		}
		return carInfo;
	}

	private static String loadJSON(String url) {
		StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
	}
}
