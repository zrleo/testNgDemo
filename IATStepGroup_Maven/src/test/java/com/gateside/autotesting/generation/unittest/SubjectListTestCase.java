package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class SubjectListTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01NotLoginSubjectList() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("SubjectListTestCase.xml","Test01");
		executor.execute();
	}
	
	@Test
	public void Test02AddSubscription() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("SubjectListTestCase.xml","Test02");
		executor.execute();
	}
	
	@Test
	public void Test03DelSubscription() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("SubjectListTestCase.xml","Test03");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

