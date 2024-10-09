package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.helper.FileuploadHelper;

@RestController
public class FileUploadeController {
	
	@Autowired
	FileuploadHelper fileuploadHelper;
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
		
//		System.out.println(file.getOriginalFilename());
//		System.out.println(file.getContentType());
//		System.out.println(file.getSize());
//		System.out.println(file.getName());
//		System.out.println(file.isEmpty());
		
		/*validation*/
		
		try {
			if(file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("request must contatin file");
			}
			
			
			if(!file.getContentType().equals("image/png")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("request must contatin file");
			}
			
			/*file upload code
			 * E:\Eclipse\Spring Tool Suit 4\bootrestbook\src\main\resources\static\image*/
			boolean f = fileuploadHelper.uploadFile(file);
			
			if(f) {
//				return ResponseEntity.ok("file is successfully uploaded");
				
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image").path(file.getOriginalFilename()).toUriString());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong ! try again");
		
	}
}
