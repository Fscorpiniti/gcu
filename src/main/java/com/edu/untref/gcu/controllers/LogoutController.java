package com.edu.untref.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;


@Controller
@RequestMapping(value="/logout") 
@Api(value="logoutController", description="EndPoint donde se realiza el logout de los usuarios a la aplicacion.")
public class LogoutController extends MaestroController {

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Logout del usuario.")
	public void logout(@RequestParam("access_token") String token) {
		this.removeToken(token);
	}

}