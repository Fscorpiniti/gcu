package com.edu.untref.gcu.test.services;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;
import com.edu.untref.gcu.services.PlanEstudioService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/spring/application-context-test.xml" })
public class PlanEstudioServiceTest {

	@Resource(name = "planEstudioService")
	private PlanEstudioService planEstudioService;
	
	private List<PosiblesCursantesMateriaDTO> posiblesCursantesDTOs;

	@Before
	public void inicializarListasDTOs(){
		posiblesCursantesDTOs = planEstudioService.getAllPosiblesCursantesMaterias("1");
	}
	
	@Test
	public void cantidadPlanMateriaEnPlan2008DeberiaSerCincuentaYOcho(){
		Assert.assertEquals(58, planEstudioService.getAllPlanMateriasByIdPlanEstudio("1").size());
	}
	
	/* MATERIAS SIN CORRELATIVAS */
	
	@Test
	public void cantidadDePosiblesCursantesAMetodologiaDeberiaSerDiez(){
		Assert.assertEquals(10, (int)posiblesCursantesDTOs.get(30).getAlumnosPosiblesCursantes().size());
	}
	
	@Test
	public void cantidadDePosiblesCursantesAFisicaUnoDeberiaSerNueve(){
		Assert.assertEquals(9, (int)posiblesCursantesDTOs.get(0).getAlumnosPosiblesCursantes().size());
	}
	
	@Test
	public void cantidadDePosiblesCursantesAAnalisisUnoDeberiaSerNueve(){
		Assert.assertEquals(9, (int)posiblesCursantesDTOs.get(1).getAlumnosPosiblesCursantes().size());
	}

	@Test
	public void cantidadDePosiblesCursantesADiscretaUnoDeberiaSerNueve(){
		Assert.assertEquals(9, (int)posiblesCursantesDTOs.get(2).getAlumnosPosiblesCursantes().size());
	}
	
	@Test
	public void cantidadDePosiblesCursantesAEstructurasUnoDeberiaSerNueve(){
		Assert.assertEquals(9, (int)posiblesCursantesDTOs.get(3).getAlumnosPosiblesCursantes().size());
	}
	
	@Test
	public void cantidadDePosiblesCursantesAAlgebraUnoDeberiaSerNueve(){
		Assert.assertEquals(9, (int)posiblesCursantesDTOs.get(4).getAlumnosPosiblesCursantes().size());
	}
	
	/* MATERIAS CON UNA CORRELATIVA */
	
	@Test
	public void cantidadDePosiblesCursantesADiscretaDosDeberiaSerUno(){
		Assert.assertEquals(1, (int)posiblesCursantesDTOs.get(9).getAlumnosPosiblesCursantes().size());
	}
	
	@Test
	public void cantidadDePosiblesCursantesALenguajeUnoDeberiaSerCero(){
		Assert.assertEquals(0, (int)posiblesCursantesDTOs.get(10).getAlumnosPosiblesCursantes().size());
	}
	
	@Test
	public void cantidadDePosiblesCursantesAProbabilidadUnoDeberiaSerUno(){
		Assert.assertEquals(1, (int)posiblesCursantesDTOs.get(14).getAlumnosPosiblesCursantes().size());
	}
	
	@Test
	public void cantidadDePosiblesCursantesAEstructurasDosDeberiaSerCero(){
		Assert.assertEquals(0, (int)posiblesCursantesDTOs.get(16).getAlumnosPosiblesCursantes().size());
	}
	
	@Test
	public void cantidadDePosiblesCursantesAnalisisDosDeberiaSerUno(){
		Assert.assertEquals(1, (int)posiblesCursantesDTOs.get(8).getAlumnosPosiblesCursantes().size());
	}
	
	/* MATERIAS CON DOS CORRELATIVAS */
	
	@Test
	public void cantidadDePosiblesCursantesAFisicaTresDeberiaSerCero(){
		Assert.assertEquals(0, (int)posiblesCursantesDTOs.get(20).getAlumnosPosiblesCursantes().size());
	}
	
	@Test
	public void cantidadDePosiblesCursantesAAnalsisTresDeberiaSerCero(){
		Assert.assertEquals(0, (int)posiblesCursantesDTOs.get(15).getAlumnosPosiblesCursantes().size());
	}
	
	@Test
	public void cantidadDePosiblesCursantesAEstructurasTresDeberiaSerUno(){
		Assert.assertEquals(1,(int)posiblesCursantesDTOs.get(23).getAlumnosPosiblesCursantes().size());
	}
	
	@Test
	public void cantidadPlanesEstudioDeLaCarreraIngEnComputacionDeberiaSerUno(){
		Assert.assertEquals(1, planEstudioService.getAllPlanesByCarrera(1).size());
	}
}