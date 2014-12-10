package com.edu.untref.gcu.test.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.edu.untref.gcu.test.services.CargadorDeDatosServiceTest;
import com.edu.untref.gcu.test.services.CarreraServiceTest;
import com.edu.untref.gcu.test.services.PlanEstudioServiceTest;
import com.edu.untref.gcu.test.services.PlanMateriaServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ CarreraServiceTest.class, PlanEstudioServiceTest.class, PlanMateriaServiceTest.class, CargadorDeDatosServiceTest.class })
public class TestSuiteGcu {

}
