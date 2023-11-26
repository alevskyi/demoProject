package ua.training.quotes.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MiscController{
	
	private static final int BUFFER_SIZE = 4096;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String root(){
		return "hello";
	}
	
	@RequestMapping(value="/api", method=RequestMethod.GET)
	public String refference(){
		return "help";
	}
	
	@RequestMapping(value="/about", method=RequestMethod.GET)
	public String about(){
		return "about";
	}
	
	@RequestMapping(value="/download/quote_template", method=RequestMethod.GET)
	public void downloadTemplate(HttpServletResponse response, OutputStream os){
		
		try {
			File template = new ClassPathResource("quotes_template.xml").getFile();
			
			String mime = Files.probeContentType(template.toPath());
			if (mime == null) {
			//set to binary type if MIME mapping not found
				mime = "application/octet-stream";
			}
			System.out.println("MIME type: " + mime);
			response.setContentType(mime);
			response.setContentLength((int) template.length());
	    
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
	                template.getName());
			response.setHeader(headerKey, headerValue);
	    	
			InputStream is = new FileInputStream(template);
			byte[] buffer = new byte[BUFFER_SIZE];
	        int bytesRead = -1;
	 
	        while ((bytesRead = is.read(buffer)) != -1) {
	            os.write(buffer, 0, bytesRead);
	        }
	        is.close();
	        os.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
