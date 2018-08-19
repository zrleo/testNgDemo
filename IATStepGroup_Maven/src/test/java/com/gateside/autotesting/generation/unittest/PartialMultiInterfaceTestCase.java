package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class PartialMultiInterfaceTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PartialMultiInterfaceTestCase.xml","Test01");
		executor.execute();
	}
	
	@Test
	public void Test02() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PartialMultiInterfaceTestCase.xml","Test02");
		executor.execute();
	}
	
	@Test
	public void Test03() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PartialMultiInterfaceTestCase.xml","Test03");
		executor.execute();
	}
	
	@Test
	public void Test04() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PartialMultiInterfaceTestCase.xml","Test04");
		executor.execute();
	}
	
	@Test
	public void Test05() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PartialMultiInterfaceTestCase.xml","Test05");
		executor.execute();
	}
	
	@Test
	public void Test06() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PartialMultiInterfaceTestCase.xml","Test06");
		executor.execute();
	}
	
	@Test
	public void Test07() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PartialMultiInterfaceTestCase.xml","Test07");
		executor.execute();
	}
	
	@Test
	public void Test08() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PartialMultiInterfaceTestCase.xml","Test08");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

