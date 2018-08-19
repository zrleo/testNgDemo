package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class DelBlockTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01NotLoginDelBlock() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("DelBlockTestCase.xml","Test01");
		executor.execute();
	}
	
	@Test
	public void Test02LoginDelBlockList() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("DelBlockTestCase.xml","Test02");
		executor.execute();
	}
	
	@Test
	public void Test03DelNotExistBlock() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("DelBlockTestCase.xml","Test03");
		executor.execute();
	}
	
	@Test
	public void Test04DelSameBlockTwice() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("DelBlockTestCase.xml","Test04");
		executor.execute();
	}
	
	@Test
	public void Test05DelDiffBlockMore() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("DelBlockTestCase.xml","Test05");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

