package com.api.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileuploadHelper {
	
	/* Static path*/
//	public final String UPLOAD_DIR="E:\\Eclipse\\Spring Tool Suit 4\\bootrestbook\\src\\main\\resources\\static\\image";
	
	public final String UPLOAD_DIR= new ClassPathResource("static/image/").getFile().getAbsolutePath();
	
	/* made default constructor*/
	
	public FileuploadHelper() throws IOException{
		
	}
	
	public boolean uploadFile(MultipartFile multipartFile) {
		
		boolean f = false;
		
		try {
			InputStream is = multipartFile.getInputStream();
			byte data[] = new byte[is.available()];
			
			is.read(data);
			
			FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+"\\"+multipartFile.getOriginalFilename());
			fos.write(data);
			
			fos.flush();
			fos.close();
//			Files.copy(multipartFile.getInputStream(), UPLOAD_DIR + File.separator + multipartFile.getOriginalFilename(), StandardCopyOption.REPLACE_EXISTING);
			f = true;
			
//			Files.copy(null, null, null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
}
