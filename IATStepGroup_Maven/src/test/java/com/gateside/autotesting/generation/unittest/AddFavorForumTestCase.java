package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class AddFavorForumTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01NotLoginAddForum() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("AddFavorForumTestCase.xml","Test01");
		executor.execute();
	}
	
	@Test
	public void Test02LoginAddForum() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("AddFavorForumTestCase.xml","Test02");
		executor.execute();
	}
	
	@Test
	public void Test03LoginAddSameForumTwice() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("AddFavorForumTestCase.xml","Test03");
		executor.execute();
	}
	
	@Test
	public void Test04AddMoreDiffForum() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("AddFavorForumTestCase.xml","Test04");
		executor.execute();
	}
	
	@Test
	public void Test05AddNotExistForum() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("AddFavorForumTestCase.xml","Test05");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

