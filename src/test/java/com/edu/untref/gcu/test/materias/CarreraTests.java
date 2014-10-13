package com.edu.untref.gcu.test.materias;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.edu.untref.gcu.domain.Carrera;
import com.edu.untref.gcu.domain.PlanEstudio;
import com.edu.untref.gcu.services.CarreraService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/application-context-test.xml"})
public class CarreraTests {

	@Resource(name = "carreraService")
	private CarreraService carreraService;
	
	
	@Test
	public void getCarreraIngComputacion2008Test() {
		
//		Carrera carrera = new Carrera();
//		carrera.setId(15);
//		carrera.setNombre("Ingenieria en computacion");
//		
//		PlanEstudio planEstudio = new PlanEstudio();
//		planEstudio.setAnio(2008);
//		planEstudio.setCarrera(carrera);
//		planEstudio.setId(1);
//		
//		List <PlanEstudio> lista = new ArrayList<PlanEstudio> ();
//		lista.add(planEstudio);
		
		Assert.assertEquals(1, carreraService.getAllPlanesByCarrera(15).size());
//		Assert.assertEquals(lista, carreraService.getAllPlanesByCarrera(15));
	}
	
}