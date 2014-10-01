package com.edu.untref.gcu.test.materias;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.edu.untref.gcu.services.PlanEstudioService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/application-context-test.xml"})
public class PlanEstudioTests {

	@Resource(name = "planEstudioService")
	private PlanEstudioService planEstudioService;
	
	@Test
	public void getPlanEstudioIngComputacion2008Test() {
		Assert.assertEquals(1, planEstudioService.getAllPlanesByCarrera(15).size());
	}
	
}