package com.edu.untref.gcu.controllers;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.edu.untref.gcu.services.CargadorDeDatosService;
import com.wordnik.swagger.annotations.Api;

@Controller
@RequestMapping(value = "/upload")
@Api(value = "uploadController", description = "EndPoint que permite realizar acciones sobre los uploads.")
public class FileUploadController {
	
	@Resource (name="cargadorDeDatosService")
	private CargadorDeDatosService cargadorDeDatosService;
	
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void uploadFileHandler(@RequestParam("file") MultipartFile file) throws IOException {
 
        if (!file.isEmpty()) {
                File datos = multipartToFile(file);
                cargadorDeDatosService.evaluarXlsx(datos);
        }
		
    }
    
	public File multipartToFile(MultipartFile multipart)
			throws IllegalStateException, IOException {
		File convFile = new File(multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}

}
