package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class WowCharacterListTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01WowCharacterList_NotLogin() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterListTestCase.xml","Test01");
		executor.execute();
	}
	
	@Test
	public void Test02WowCharacterList_Login() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterListTestCase.xml","Test02");
		executor.execute();
	}
	
	@Test
	public void Test03WowCharacterList_AddCharacter() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterListTestCase.xml","Test03");
		executor.execute();
	}
	
	@Test
	public void Test04WowCharacterList_ListNull() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterListTestCase.xml","Test04");
		executor.execute();
	}
	
	@Test
	public void Test05WowCharacterList_RemoveCharacter() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterListTestCase.xml","Test05");
		executor.execute();
	}
	
	@Test
	public void Test06WowCharacterList_AddRemoveCharacter() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterListTestCase.xml","Test06");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

