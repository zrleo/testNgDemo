package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class LoginTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01login() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("LoginTestCase.xml","Test01");
		executor.execute();
	}
	
	@Test
	public void Test02login() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("LoginTestCase.xml","Test02");
		executor.execute();
	}
	
	@Test
	public void Test03login() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("LoginTestCase.xml","Test03");
		executor.execute();
	}
	
	@Test
	public void Test04login() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("LoginTestCase.xml","Test04");
		executor.execute();
	}
	
	@Test
	public void Test05login() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("LoginTestCase.xml","Test05");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

