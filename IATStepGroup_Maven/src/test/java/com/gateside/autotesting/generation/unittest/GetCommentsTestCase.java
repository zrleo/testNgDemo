package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class GetCommentsTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01get_comments() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("GetCommentsTestCase.xml","Test01get_comments");
		executor.execute();
	}
	
	@Test
	public void Test02get_comments() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("GetCommentsTestCase.xml","Test02get_comments");
		executor.execute();
	}
	
	@Test
	public void Test03get_commentsGet10PositiveSequence() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("GetCommentsTestCase.xml","Test03get_comments");
		executor.execute();
	}
	
	@Test
	public void Test04get_commentsGet10PositiveSequenceBelowSpecifiedCid() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("GetCommentsTestCase.xml","Test04get_comments");
		executor.execute();
	}
	
	@Test
	public void Test05get_commentsGet10ReverseBelowSpecifiedCid() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("GetCommentsTestCase.xml","Test05get_comments");
		executor.execute();
	}
	
	@Test
	public void Test06get_commentsLoginToGet10ReverseBelowSpecifiedCid() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("GetCommentsTestCase.xml","Test06get_comments");
		executor.execute();
	}
	
	@Test
	public void Test07get_commentsLoginToGet10ReverseCid() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("GetCommentsTestCase.xml","Test07get_comments");
		executor.execute();
	}
	
	@Test
	public void Test08get_commentsLoginToGet10ReverseCid() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("GetCommentsTestCase.xml","Test08get_comments");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

