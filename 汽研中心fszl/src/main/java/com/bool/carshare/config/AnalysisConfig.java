package com.bool.carshare.config;
/**
 *解析文件
 *
 */
public class AnalysisConfig {

	/**
	 * 分包的方法
	 */
	public static void distributePackage(byte[] message){
		int start = 0;//起始位置
		int len = 0;//长度
		boolean flag = false;//判断条件
		for (int i = 0; i < message.length; i++) {
			//7E开头
			if(message[i] == 0x7E){
				if(flag == false){
					flag = true;
					start = i;
				//7E结尾
				}else{
					flag = false;
					len = i-start+1;
					//将其中一个包的数据提取
					byte[] copy = new byte[len];
					System.arraycopy(message, start, copy, 0, len);
					//拦截还原
					copy = interceptByte(copy);
					//解析并插入数据库
					analysisByte(copy);
				}
			}
		}
	}
	
	/**
	 * 解析的方法
	 */
	public static void analysisByte(byte[] copy){
//		byte[] jingdu = new byte[10];
//		byte[] weidu = new byte[9];
		
		
	}
	
	/**
	 * 拦截数据将7D 5E 转换成7E
	 */
	public static byte[] interceptByte(byte[] copy){
		byte d7 =0x7D;
		byte e5 =0x5E;
		byte d5 =0x5D;
		System.out.println(copy);
		for (int i = 0; i < copy.length; i++) {
			byte e7 = copy[i];
			if(e7 == d7 || e5 == e7){
				copy[i] = 0x7E;
			}else if(e7 == d5){
				copy[i] = 0x7D;
			}
		}
		return copy;
	}
}
