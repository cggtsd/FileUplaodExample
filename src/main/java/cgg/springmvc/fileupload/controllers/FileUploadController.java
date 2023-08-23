package cgg.springmvc.fileupload.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;


@Controller
public class FileUploadController {

	@RequestMapping("/user/{userId}/{userName}")
	public String getUserDetails(@PathVariable("userId") int id,@PathVariable("userName") String name) {
		
		System.out.println(id);
		System.out.println(name);
		Integer.parseInt(name);
		String str=null;
		System.out.println(str.length());
		return "home";
		
	}
	@RequestMapping("/fileform")
	public String showUploadFrom() {
		System.out.println("file upload form...");
		int [] a = {12};
		a[3]=5;
		return "fileform";
	}
	
	@RequestMapping(path="/uploadimage", method=RequestMethod.POST)
	public String fileUpload(@RequestParam("profile") MultipartFile file,HttpSession s,Model m) {
		System.out.println("file upload handler");
		System.out.println(file.getSize());
		System.out.println(file.getContentType());
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		byte[] data=null;
		try {
			 data = file.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//we have to save file to server
		String path=s.getServletContext().getRealPath("/")+"WEB-INF"
		+File.separator+"resources"+File.separator+"images"+File.separator+
		file.getOriginalFilename();
		System.out.println(path);
		
		try {
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(data);
			fos.close();
			System.out.println("File uploaded successfully.....");
			m.addAttribute("msg","File uploaded....");
			m.addAttribute("filename",file.getOriginalFilename());
			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("uploading error...");
			m.addAttribute("msg","uploading error...");
		} catch (IOException e) {
			
			e.printStackTrace();
		}		
		
		return "filesuccess";
	}
	
	//handling exceptions in spring mvc
//	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler(value= NullPointerException.class)
//	public String exceptionHandlerNull(Model m) {
//		m.addAttribute("msg","Null Pointer Exception Occurred");
//		return "error_page";
//	}
//	
//	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler(value=NumberFormatException.class)
//	public String exceptionHandlerNumberFormat(Model m) {
//		m.addAttribute("msg","Number Format Exception Occurred");
//		return "error_page";
//	}
//	
//	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler(value=Exception.class)
//	public String exceptionHandlerGeneric(Model m) {
//		m.addAttribute("msg","Exception Occurred");
//		return "error_page";
//	}
//	
	
	
	
	
	
	
	
	
	
	
	
}
