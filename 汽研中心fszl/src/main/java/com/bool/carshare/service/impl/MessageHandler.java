package com.bool.carshare.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.bool.carshare.entity.CarInfo;
import com.bool.carshare.service.CarInfoService;
import com.bool.carshare.util.ByteAnalysis;
import com.bool.carshare.util.auth.CarCacheManager;
import com.google.common.primitives.Bytes;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
@Component
public class MessageHandler  extends ChannelInboundHandlerAdapter{
	private static final Map<String,ChannelHandlerContext> clientMap = new ConcurrentHashMap<String,ChannelHandlerContext>();
	/**
	 * 添加msg校验
	 */
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg instanceof ByteBuf){
			//创建一个bytebuf
			ByteBuf bmsg = (ByteBuf)msg;
			//新建byte数组
			byte[] message = new byte[bmsg.readableBytes()];//bmsg中的个数
			//把bmsg的值赋予message
			bmsg.readBytes(message);
			//拆包/分包所用逻辑
			int start = 0;
			int length = 0;
			boolean flag = true;
			System.out.println(Arrays.toString(message));
			//拆包拆出来的byte[]
			ArrayList<Byte> blist = new ArrayList<Byte>();
			String cterminal = null;
			String longitude = null;
			String latitude = null;
			byte[] zhongduan = new byte[14];
			byte[] jingdu = new byte[10];
			byte[] weidu = new byte[9];
			
			for (int i = 0; i < message.length; i++) {
				//16进制7E开头
				if(message[i] == 126){
					if(flag){
						start  = i;
						i++;
						flag = false;
					}else {
						length = i-start;
						if(length>70){
							i++;
							flag = true;
							byte[] newbyte = new byte[length];
							System.arraycopy(message, start, newbyte, 0, length);
							//报文中间出现7D5E或者7D5D的情况
							for (int j = 0; j < newbyte.length; j++) {
								if(newbyte[j] == 125){
									//7D5E返回7E
									if(newbyte[j+1] == 94){
										blist.add((byte) 126);
									//7D5D返回7D
									}else if(newbyte[j+1] == 93){
										blist.add((byte) 125);
									}
									j++;
								}else{
									blist.add(newbyte[j]);
								}
							}
							//将数组集合再次拆成数组
							byte[] mes = Bytes.toArray(blist);
							//获取终端id
							CarInfo carInfo = new CarInfo();
							//添加到终端数组
							System.arraycopy(mes, 47, zhongduan, 0, 14);
							cterminal = new String(zhongduan);
							System.out.println("终端："+cterminal);
							carInfo.setCterminal(cterminal);
							//添加到经度数组
							DecimalFormat jwd = new DecimalFormat("######0.00000000");
							System.arraycopy(mes, 18, jingdu, 0, 10);
							if(jingdu[0] >0){
								longitude = new String(jingdu);
								Double ai = Double.parseDouble(longitude.substring(0, 3));
								Double bi = Double.parseDouble(longitude.substring(3, 10));
								Double c = ai+bi/60;
								longitude =jwd.format(c);
								System.out.println("经度："+longitude);
								carInfo.setLongitude(longitude);
							}
							//添加到纬度数组
							System.arraycopy(mes, 8, weidu, 0, 9);
							if(weidu[0] > 0){
								latitude = new String(weidu);
								Double ai = Double.parseDouble(latitude.substring(0, 2));
								Double bi = Double.parseDouble(latitude.substring(2, 9));
								Double c = ai+bi/60;
								latitude =jwd.format(c);
								System.out.println("纬度："+latitude);
								carInfo.setLatitude(latitude);
								//坐标转换
								ByteAnalysis.getLLByLL(carInfo);
								System.out.println("百度经度："+carInfo.getLongitude());
								System.out.println("百度纬度："+carInfo.getLatitude());
								//插入缓存经纬度
								CarCacheManager.removeLongitude();
								CarCacheManager.putLongitude(longitude);
								CarCacheManager.removeLatitude();
								CarCacheManager.putLatitude(latitude);
							}
							//初始无缓存的判断并插入0.0值
							if(CarCacheManager.getLongitude() == null && CarCacheManager.getLatitude() == null){
								CarCacheManager.putLongitude("0.0");
								CarCacheManager.putLatitude("0.0");
							}
							//判断终端id 并将数据存入实体类
//							if(cterminal.equals("61311007978421")){//测试数据
							if(cterminal.equals("61311006548969")||cterminal.equals("61311006539802")||cterminal.equals("61311006832439")){
								//车门状态
								Byte carDoorStateb = blist.get(70);
								String carDoorState = carDoorStateb.toString();
								System.out.println("车门状态："+carDoorState);
								carInfo.setCarDoorState(carDoorState);
								//授权状态
								Byte startupStateb = blist.get(71);
								String startupState = startupStateb.toString();
								System.out.println("授权状态："+startupStateb);
								carInfo.setStartupState(startupState);
								//左车窗状态
								Byte lcarWinStateb = blist.get(72);
								String lcarWinState = lcarWinStateb.toString();
								System.out.println("左车窗状态："+lcarWinStateb);
								carInfo.setLcarWinState(lcarWinState);
								//右车窗状态
								Byte rcarWinStateb = blist.get(73);
								String rcarWinState = rcarWinStateb.toString();
								System.out.println("右车窗状态："+rcarWinStateb);
								carInfo.setRcarWinState(rcarWinState);
							}
//							if(cterminal.equals("61311007978429")){//测试数据
							//荣威E50
							if(cterminal.equals("61311008618497")){
								//车速高字节
								Byte hs = blist.get(69);
								//车速低字节
								Byte ls = blist.get(70);
								//里程高字节
								Byte hm = blist.get(80);
								//里程低字节
								Byte lm = blist.get(81);
								//soc
								Byte soc = blist.get(92);
								//里程
								double dcm;
								//车速
								double dsp;
								//soc
								double dsoc;
								//soc判断
								if(soc<0){
									dsoc = (256+soc)*0.5;
									carInfo.setSoc(dsoc);
								}else{
									dsoc = soc*0.5;
									carInfo.setSoc(dsoc);
								}
								//车速判断
								if(hs<0){
									if(ls<0){
										dsp = ((256+hs)*256+ls+256)/100;
										carInfo.setSpeed(dsp);
									}else{
										dsp = ((256+hs)*256+ls)/100;
										carInfo.setSpeed(dsp);
									}
								}else{
									if(ls<0){
										dsp = ((hs*256)+ls+256)/100;
										carInfo.setSpeed(dsp);
									}else{
										dsp = ((hs*256)+ls)/100;
										carInfo.setSpeed(dsp);
									}
								}
								//里程判断
								if(hm<0){
									if(lm<0){
										dcm = (((256+hm)*256)+lm+256);
										carInfo.setCtotalMileage(dcm);
									}else{
										dcm = (((256+hm)*256)+lm);
										carInfo.setCtotalMileage(dcm);
									}
								}else{
									if(lm<0){
										dcm = ((hm*256)+lm+256);
										carInfo.setCtotalMileage(dcm);
									}else{
										dcm = ((hm*256)+lm);
										carInfo.setCtotalMileage(dcm);
									}
								}
								System.out.println("车速"+carInfo.getSpeed());
								System.out.println("里程"+carInfo.getCtotalMileage());
								System.out.println("soc"+carInfo.getSoc());
							}
							if(cterminal.equals("61311007966624")||cterminal.equals("61311007976185")){
								//车速
								Byte sp = blist.get(69);
								//里程高字节
								Byte hm = blist.get(78);
								//里程低字节
								Byte lm = blist.get(79);
								//soc
								Byte soc = blist.get(86);
								//里程
								double dcm;
								//车速
								double dsp;
								//soc
								double dsoc;
								DecimalFormat df = new DecimalFormat("######0.0");
								//soc判断
								if(soc<0){
									dsoc = Double.valueOf(df.format((256+soc)*100/256));
									carInfo.setSoc(dsoc);
								}else{
									dsoc = Double.valueOf(df.format(soc*100/256));
									carInfo.setSoc(dsoc);
								}
								//车速判断
								if(sp<0){
									dsp = sp+256;
									carInfo.setSpeed(dsp);
								}else{
									dsp = sp;
									carInfo.setSpeed(dsp);
								}
								//里程判断
								if(hm<0){
									if(lm<0){
										dcm = (((256+hm)*256)+lm+256);
										carInfo.setCtotalMileage(dcm);
									}else{
										dcm = (((256+hm)*256)+lm);
										carInfo.setCtotalMileage(dcm);
									}
								}else{
									if(lm<0){
										dcm = ((hm*256)+lm+256);
										carInfo.setCtotalMileage(dcm);
									}else{
										dcm = ((hm*256)+lm);
										carInfo.setCtotalMileage(dcm);
									}
								}
								System.out.println("车速"+carInfo.getSpeed());
								System.out.println("里程"+carInfo.getCtotalMileage());
								System.out.println("soc"+carInfo.getSoc());
							}
							
							try{
								CarInfoService carInfoService = new CarInfoServiceImpl();
								carInfoService.upCarInfo(carInfo);
							}catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			clientMap.put(cterminal, ctx);
			System.out.println("map通道:"+clientMap.get(cterminal));

		}
	}


	/**
	 * 注册
	 */
	
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("register client hashCode:"+ctx.hashCode()+",channel:"+ctx.channel());
		super.channelRegistered(ctx);
	}

	/**
	 * 异常
	 */
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("exception message:" + cause.getMessage());
		
	}


	public static Map<String, ChannelHandlerContext> getClientmap() {
		return clientMap;
	}
	
}
