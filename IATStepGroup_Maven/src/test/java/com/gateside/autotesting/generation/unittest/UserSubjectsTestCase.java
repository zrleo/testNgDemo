package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class UserSubjectsTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01NotLoginUserSubjects() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("UserSubjectsTestCase.xml","Test01");
		executor.execute();
	}
	
	@Test
	public void Test02LoginGetFixedUserSubjects() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("UserSubjectsTestCase.xml","Test02");
		executor.execute();
	}
	
	@Test
	public void Test03LoginGetRandomUserSubjects() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("UserSubjectsTestCase.xml","Test03");
		executor.execute();
	}
	
	@Test
	public void Test04GetNotExistUser() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("UserSubjectsTestCase.xml","Test04");
		executor.execute();
	}
	
	@Test
	public void Test05LoginGetRandomFid() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("UserSubjectsTestCase.xml","Test05");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

