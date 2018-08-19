package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class PostQuoteTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01NotLogin() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostQuoteTestCase.xml","Test01NotLogin");
		executor.execute();
	}
	
	@Test
	public void Test02LoginRandTid() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostQuoteTestCase.xml","Test02LoginRandTid");
		executor.execute();
	}
	
	@Test
	public void Test03StableContent() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostQuoteTestCase.xml","Test03StableContent");
		executor.execute();
	}
	
	@Test
	public void Test04AddressNotNull() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostQuoteTestCase.xml","Test04AddressNotNull");
		executor.execute();
	}
	
	@Test
	public void Test05PostTimeout() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostQuoteTestCase.xml","Test05PostTimeout");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

