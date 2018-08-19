package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class PostNewTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01AccountFack() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostNewTestCase.xml","Test01AccountFack");
		executor.execute();
	}
	
	@Test
	public void Test02LoginRandfid() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostNewTestCase.xml","Test02LoginRandfid");
		executor.execute();
	}
	
	@Test
	public void Test03StableContent() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostNewTestCase.xml","Test03StableContent");
		executor.execute();
	}
	
	@Test
	public void Test04AddressNotNull() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostNewTestCase.xml","Test04AddressNotNull");
		executor.execute();
	}
	
	@Test
	public void Test05NotLogin() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostNewTestCase.xml","Test05NotLogin");
		executor.execute();
	}
	
	@Test
	public void Test06VideoAttachments() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostNewTestCase.xml","Test06VideoAttachments");
		executor.execute();
	}
	
	@Test
	public void Test07AudioAttachments() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostNewTestCase.xml","Test07AudioAttachments");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

