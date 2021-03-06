package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class CheckBroadcastTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01CheckBroadcastNoUpdate() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("CheckBroadcastTestCase.xml","Test01CheckBroadcast");
		executor.execute();
	}
	
	@Test
	public void Test02CheckBroadcastWithMultiUpdate() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("CheckBroadcastTestCase.xml","Test02CheckBroadcast");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

