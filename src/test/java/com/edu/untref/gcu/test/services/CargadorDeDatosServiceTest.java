package com.edu.untref.gcu.test.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.edu.untref.gcu.daos.AlumnoDAO;
import com.edu.untref.gcu.daos.MateriaDAO;
import com.edu.untref.gcu.domain.Alumno;
import com.edu.untref.gcu.domain.Materia;
import com.edu.untref.gcu.services.CargadorDeDatosService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/application-context-test.xml"})
@Transactional
public class CargadorDeDatosServiceTest {
	
	@Resource(name = "cargadorDeDatosService")
	private CargadorDeDatosService cargadorDeDatosService;
	
	@Resource(name = "alumnoDAO")
	private AlumnoDAO alumnoDAO;
	
	@Resource(name = "materiaDAO")
	private MateriaDAO materiaDAO;

	@Test
	public void cantidadAlumnosPlan2007TestDebeSer40() throws IOException{
		
		String path = new File("src/test/resources/prueba con codigos.xlsx").getCanonicalPath();
		File inputFile = new File (path);
		
		List<Alumno> alumnosAntesDeEvaluarXls = alumnoDAO.findAll();
		alumnosAntesDeEvaluarXls.size();
		List<Materia> materias = materiaDAO.findAll();
		materias.iterator().next().getNombre();
		Assert.assertEquals(10, alumnosAntesDeEvaluarXls.size());

		cargadorDeDatosService.evaluarXlsx(inputFile);
		
		List<Alumno> alumnosDespuesDeEvaluarXls = alumnoDAO.findAll();
		Assert.assertEquals(40, alumnosDespuesDeEvaluarXls.size());
	}
	
	@Test
	public void cantidadAlumnosPlan2013TestDebeSer10(){
//		Assert.assertEquals(10, cargadorDeDatosService.getCantidadAlumnosPorPlan("2013"));
	}

	@Test
	public void cantidadAprobadosMetodologiaDeLaInvestigacionPlan2007TestDebeSer8(){
//		Assert.assertEquals(8, cargadorDeDatosService.getCantidadAlumnosAprobados("Metodología de la Investigación",2007));
	}
	
	@Test
	public void cantidadAprobadosMetodologiaDeLaInvestigacionPlan2013TestDebeSer0(){
//		Assert.assertEquals(0, cargadorDeDatosService.getCantidadAlumnosAprobados("Metodología de la Investigación",2013));
	}
	
	@Test
	public void cantidadCursandoMetodologiaDeLaInvestigacionPlan2007TestDebeSer10(){
//		Assert.assertEquals(10, cargadorDeDatosService.getCantidadAlumnosCursando("Metodología de la Investigación",2007));
	}
	
	@Test
	public void cantidadCursandoMetodologiaDeLaInvestigacionPlan2013TestDebeSer3(){
//		Assert.assertEquals(3, cargadorDeDatosService.getCantidadAlumnosCursando("Metodología de la Investigación",2013));
	}
	
	@Test
	public void cantidadAbandonosMetodologiaDeLaInvestigacionPlan2007TestDebeSer9(){
//		Assert.assertEquals(9, cargadorDeDatosService.getCantidadAlumnosAbandonaron("Metodología de la Investigación",2007));
	}
	
	@Test
	public void cantidadAbandonosMetodologiaDeLaInvestigacionPlan2013TestDebeSer1(){
//		Assert.assertEquals(1, cargadorDeDatosService.getCantidadAlumnosAbandonaron("Metodología de la Investigación",2013));
	}
	
	@Test
	public void cantidadAprobadosCon4MetodologiaDeLaInvestigacionPlan2007TestDebeSer3(){
//		Assert.assertEquals(3, cargadorDeDatosService.getCantidadAlumnosAprobadosPorPlanYNota("Metodología de la Investigación",2007, 4));
	}
	
	@Test
	public void cantidadAprobadosCon5MetodologiaDeLaInvestigacionPlan2007TestDebeSer1(){
//		Assert.assertEquals(1, cargadorDeDatosService.getCantidadAlumnosAprobadosPorPlanYNota("Metodología de la Investigación",2007, 5));
	}
	
	@Test
	public void cantidadAprobadosCon6MetodologiaDeLaInvestigacionPlan2007TestDebeSer1(){
//		Assert.assertEquals(1, cargadorDeDatosService.getCantidadAlumnosAprobadosPorPlanYNota("Metodología de la Investigación",2007, 6));
	}
	
	@Test
	public void cantidadAprobadosCon7MetodologiaDeLaInvestigacionPlan2007TestDebeSer1(){
//		Assert.assertEquals(1, cargadorDeDatosService.getCantidadAlumnosAprobadosPorPlanYNota("Metodología de la Investigación",2007, 7));
	}
	
	@Test
	public void cantidadAprobadosCon8MetodologiaDeLaInvestigacionPlan2007TestDebeSer1(){
//		Assert.assertEquals(1, cargadorDeDatosService.getCantidadAlumnosAprobadosPorPlanYNota("Metodología de la Investigación",2007, 8));
	}
	
	@Test
	public void cantidadAprobadosCon9MetodologiaDeLaInvestigacionPlan2007TestDebeSer1(){
//		Assert.assertEquals(1, cargadorDeDatosService.getCantidadAlumnosAprobadosPorPlanYNota("Metodología de la Investigación",2007, 9));
	}
	
	@Test
	public void cantidadAprobadosCon10MetodologiaDeLaInvestigacionPlan2007TestDebeSer0(){
//		Assert.assertEquals(0, cargadorDeDatosService.getCantidadAlumnosAprobadosPorPlanYNota("Metodología de la Investigación",2007, 10));
	}

}
