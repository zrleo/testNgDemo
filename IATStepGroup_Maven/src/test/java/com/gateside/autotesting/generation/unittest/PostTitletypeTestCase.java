package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class PostTitletypeTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01AccountFack() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostTitletypeTestCase.xml","Test01AccountFack");
		executor.execute();
	}
	
	@Test
	public void Test02LoginRandfid() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostTitletypeTestCase.xml","Test02LoginRandfid");
		executor.execute();
	}
	
	@Test
	public void Test03StableContent() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostTitletypeTestCase.xml","Test03StableContent");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

