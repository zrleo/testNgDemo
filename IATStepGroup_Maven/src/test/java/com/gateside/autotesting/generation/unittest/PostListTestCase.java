package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class PostListTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01NotLogin() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostListTestCase.xml","Test01");
		executor.execute();
	}
	
	@Test
	public void Test02LoginPostList() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostListTestCase.xml","Test02");
		executor.execute();
	}
	
	@Test
	public void Test03LoginFixedTid() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostListTestCase.xml","Test03");
		executor.execute();
	}
	
	@Test
	public void Test04PageMore() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostListTestCase.xml","Test04");
		executor.execute();
	}
	
	@Test
	public void Test05PidNotNull() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostListTestCase.xml","Test05");
		executor.execute();
	}
	
	@Test
	public void Test06UidNotNull() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostListTestCase.xml","Test06UidNotNull");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

