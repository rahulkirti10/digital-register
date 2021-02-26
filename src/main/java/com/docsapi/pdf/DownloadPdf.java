package com.docsapi.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class DownloadPdf {

	public static final String path = "C:\\Users\\rahul\\Documents\\filename.pdf";
	public ResponseEntity<Object> Download() 
	{
		
		try {
		File file=new File(path);
		InputStreamResource resource=new InputStreamResource(new FileInputStream(file)); 
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
		return responseEntity;
		} catch (Exception e ) {
			return new ResponseEntity<>("error occurred", HttpStatus.INTERNAL_SERVER_ERROR);	
		} 
	}
	
}
