package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class WowCharacterAddTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01WowCharacterAdd_NotLogin() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterAddTestCase.xml","Test01");
		executor.execute();
	}
	
	@Test
	public void Test02WowCharacterAdd_Login() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterAddTestCase.xml","Test02");
		executor.execute();
	}
	
	@Test
	public void Test03WowCharacterAdd_NotExistRealm() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterAddTestCase.xml","Test03");
		executor.execute();
	}
	
	@Test
	public void Test04WowCharacterAdd_NoCharacterRealm() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterAddTestCase.xml","Test04");
		executor.execute();
	}
	
	@Test
	public void Test05WowCharacterAdd_NoExistCharacter() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterAddTestCase.xml","Test05");
		executor.execute();
	}
	
	@Test
	public void Test06WowCharacterAdd_NoCharacter() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterAddTestCase.xml","Test06");
		executor.execute();
	}
	
	@Test
	public void Test07WowCharacterAdd_AllParamNoExist() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterAddTestCase.xml","Test07");
		executor.execute();
	}
	
	@Test
	public void Test08WowCharacterAdd_AllParamNull() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterAddTestCase.xml","Test08");
		executor.execute();
	}
	
	@Test
	public void Test09WowCharacterAdd_RemoveAdd() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterAddTestCase.xml","Test09");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

