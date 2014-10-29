package com.edu.untref.gcu.test.services;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.edu.untref.gcu.services.PlanMateriaService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/application-context-test.xml"})
public class PlanMateriaServiceTest {

	@Resource(name = "planMateriaService")
	private PlanMateriaService planMateriaService;
	
	@Test
	public void cantidadPlanMateriaPlan2008(){
		Assert.assertEquals(58, planMateriaService.getAllPlanMateriasByIdPlanEstudio("1").size());
	}
}
