package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class DeleteFavorForumTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01NotLoginDelFavorForum() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("DeleteFavorForumTestCase.xml","Test01");
		executor.execute();
	}
	
	@Test
	public void Test02LoginDelFavorForum() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("DeleteFavorForumTestCase.xml","Test02");
		executor.execute();
	}
	
	@Test
	public void Test03DelNotAddForum() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("DeleteFavorForumTestCase.xml","Test03");
		executor.execute();
	}
	
	@Test
	public void Test04DelSameForumTwice() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("DeleteFavorForumTestCase.xml","Test04");
		executor.execute();
	}
	
	@Test
	public void Test05DelMoreDiffForum() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("DeleteFavorForumTestCase.xml","Test05");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

