package com.edu.untref.gcu.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.untref.gcu.dtos.LoginDTO;
import com.edu.untref.gcu.services.AuthenticationService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value="/login") 
@Api(value="loginController", description="EndPoint donde se realiza el login de los usuarios a la aplicacion.")
public class LoginController {

	@Resource(name = "authenticationService")
	private AuthenticationService authenticationService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Login del usuario.")
	public String login(@ApiParam (name = "loginDTO", required = true) @RequestBody LoginDTO loginDTO) {
		
		String token = authenticationService.authenticate(loginDTO.getUserName(), loginDTO.getPassword());
		
		return token;
	}
	
}
