package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class GetNavsTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01get_navsEmpty() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("GetNavsTestCase.xml","Test01get_navs");
		executor.execute();
	}
	
	@Test
	public void Test02get_navsNotEmpty() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("GetNavsTestCase.xml","Test02get_navs");
		executor.execute();
	}
	
	@Test
	public void Test03get_navsNotExist() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("GetNavsTestCase.xml","Test03get_navs");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

