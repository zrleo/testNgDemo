package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class AddBlockTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01NotLoginAddBlock() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("AddBlockTestCase.xml","Test01");
		executor.execute();
	}
	
	@Test
	public void Test02LoginAddBlockFixedUid() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("AddBlockTestCase.xml","Test02");
		executor.execute();
	}
	
	@Test
	public void Test03LoginAddBlockRandomUid() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("AddBlockTestCase.xml","Test03");
		executor.execute();
	}
	
	@Test
	public void Test04AddSameBlockTwice() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("AddBlockTestCase.xml","Test04");
		executor.execute();
	}
	
	@Test
	public void Test05AddDiffBlockMore() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("AddBlockTestCase.xml","Test05");
		executor.execute();
	}
	
	@Test
	public void Test06AddOwnerBlock() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("AddBlockTestCase.xml","Test06");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

