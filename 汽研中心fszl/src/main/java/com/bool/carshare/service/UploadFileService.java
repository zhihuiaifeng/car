package com.bool.carshare.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.bool.carshare.util.Result;

public interface UploadFileService {
	
	
	Result upload(MultipartFile file,String basePath, String httpPath) throws IllegalStateException, IOException;

}
