package com.bool.carshare.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bool.carshare.service.UploadFileService;
import com.bool.carshare.util.Assert;
import com.bool.carshare.util.Message;
import com.bool.carshare.util.Result;
import com.bool.carshare.util.UUIDUtils;
import com.bool.carshare.util.validate.Validator;
import com.bool.carshare.util.validate.entities.ValidateHolder;
import com.bool.carshare.util.validate.interceptors.NotNullInterceptor;
import com.bool.carshare.util.validate.interceptors.RegexInterceptor;

 
/**
 * upload file service 
 * create date 2017/6/6
 * @author tzw
 */
@Service("uploadFileService")
public class UploadFileServiceImpl implements UploadFileService{

	
	
	public Result upload(MultipartFile file,String basePath, String httpPath) throws IllegalStateException, IOException {
		
		Validator validate = new Validator();
		validate.addInterceptor(new ValidateHolder(file.getOriginalFilename(),Message.UPLOAD_FILE_INVALID), 
					new NotNullInterceptor());
		validate.addInterceptor(new ValidateHolder(file.getOriginalFilename(), Message.UPLOAD_FILE_NAME_FORMAT_ERROR), 
					new RegexInterceptor(RegexInterceptor.PHTOT));
		ValidateHolder validateHolder = null;
		if(Assert.isNull((validateHolder = validate.fristError()))){
			// baseFile 
			File baseFile = new File(basePath);
			//判断file是否存在
			if(!baseFile.exists()){
				baseFile.mkdirs();
			}
			String uuid = UUIDUtils.uuid()+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")-1);
			//生产环境下，指定一个固定位置\home\car\ user\
			//项目内储存位置
			String thisFilePath = basePath+"/"+uuid;
			//创建指定路径文件
			File thisFile = new File(thisFilePath);
			//复制文件
			file.transferTo(thisFile);
			//固定储存位置
			String fixPath = "C:/File/images/"+uuid;
			File fixFile = new File(fixPath);
			FileInputStream in=null;
			FileOutputStream out=null;
			FileChannel inf =null;
			FileChannel outf =null;
			//通过流创建通道
			try{
				in=new FileInputStream(thisFile);  
				out=new FileOutputStream(fixFile);
				inf = in.getChannel();
				outf = out.getChannel();
				//通过通道复制文件
				inf.transferTo(0, inf.size(), outf);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
	            	in.close();
	            	inf.close();
	            	outf.close();
	                out.close();
			}
			//网络访问路径，插入数据库
			String httpFilePath = httpPath+"/upload/"+uuid;
			return Result.ResultBuilder.buildSuccessResult(Message.OK, httpFilePath);
		}
		return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(),null);
	}

}
