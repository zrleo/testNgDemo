package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class WowRaidListTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01WowRaidList_NotLogin() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowRaidListTestCase.xml","Test01");
		executor.execute();
	}
	
	@Test
	public void Test02WowRaidList_Login() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowRaidListTestCase.xml","Test02");
		executor.execute();
	}
	
	@Test
	public void Test03WowRaidList_NotExistType() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowRaidListTestCase.xml","Test03");
		executor.execute();
	}
	
	@Test
	public void Test04WowRaidList_RangeoutType() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowRaidListTestCase.xml","Test04");
		executor.execute();
	}
	
	@Test
	public void Test05WowRaidList_NoNumberType() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowRaidListTestCase.xml","Test05");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

