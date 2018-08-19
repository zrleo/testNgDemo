package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class DelCommentTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01delUnExist() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("DelCommentTestCase.xml","Test01delUnExist");
		executor.execute();
	}
	
	@Test
	public void Test02deleteBroadcast() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("DelCommentTestCase.xml","Test02deleteBroadcast");
		executor.execute();
	}
	
	@Test
	public void Test03deleteRandomComment() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("DelCommentTestCase.xml","Test03deleteRandomComment");
		executor.execute();
	}
	
	@Test
	public void Test04DeleteOtherBroadcast() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("DelCommentTestCase.xml","Test04DeleteOther");
		executor.execute();
	}
	
	@Test
	public void Test05DeleteOtherComment() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("DelCommentTestCase.xml","Test05DeleteOther");
		executor.execute();
	}
	
	@Test
	public void Test06DeleteSameCommentTwice() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("DelCommentTestCase.xml","Test06Delete");
		executor.execute();
	}
	
	@Test
	public void Test07DeleteSameBroadcastTwice() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("DelCommentTestCase.xml","Test07Delete");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

