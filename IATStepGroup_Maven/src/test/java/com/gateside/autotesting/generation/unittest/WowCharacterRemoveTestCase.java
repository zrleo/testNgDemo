package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class WowCharacterRemoveTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01WowCharacterRemove_NotLogin() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterRemoveTestCase.xml","Test01");
		executor.execute();
	}
	
	@Test
	public void Test02WowCharacterRemove_Login() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterRemoveTestCase.xml","Test02");
		executor.execute();
	}
	
	@Test
	public void Test03WowCharacterRemove_NotExistCharacterId() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterRemoveTestCase.xml","Test03");
		executor.execute();
	}
	
	@Test
	public void Test04WowCharacterRemove_RangeOutCharacterId() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterRemoveTestCase.xml","Test04");
		executor.execute();
	}
	
	@Test
	public void Test05WowCharacterRemove_NoNumberCharacterId() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterRemoveTestCase.xml","Test05");
		executor.execute();
	}
	
	@Test
	public void Test06WowCharacterRemove_UserNoContainCharacterId() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterRemoveTestCase.xml","Test06");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

