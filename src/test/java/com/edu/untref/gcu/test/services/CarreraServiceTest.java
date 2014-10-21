package com.edu.untref.gcu.test.services;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.edu.untref.gcu.services.CarreraService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/application-context-test.xml"})
public class CarreraServiceTest {

	@Resource(name = "carreraService")
	private CarreraService carreraService;
	
	@Test
	public void testCantidadPlanesDeEstudioConSoloPlan2008() {
		Assert.assertEquals(1, carreraService.getAllPlanesByCarrera(1).size());
	}
	
}