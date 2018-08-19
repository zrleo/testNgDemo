package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class PostCheckTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostCheckTestCase.xml","Test01");
		executor.execute();
	}
	
	@Test
	public void Test02create_resource() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostCheckTestCase.xml","Test02create_resource");
		executor.execute();
	}
	
	@Test
	public void Test03NotLogin() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostCheckTestCase.xml","Test03NotLogin");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

