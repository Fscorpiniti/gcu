package com.edu.untref.gcu.test.materias;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.edu.untref.gcu.services.MateriaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/application-context-test.xml"})
public class MateriaTests {

	@Resource(name = "materiaService")
	private MateriaService materiaService;
	
	@Test
	public void getAllMateriasIngComputacion2008Test() {
		Assert.assertEquals(58, materiaService.getAllMaterias().size());
	}
	
}