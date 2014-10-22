package com.edu.untref.gcu.test.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.edu.untref.gcu.test.services.CarreraServiceTest;
import com.edu.untref.gcu.test.services.PlanEstudioServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ CarreraServiceTest.class, PlanEstudioServiceTest.class })
public class TestSuiteGcu {

}
