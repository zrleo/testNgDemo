package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class CheckCommentTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01CheckCommentNoUpdate() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("CheckCommentTestCase.xml","Test01CheckComment");
		executor.execute();
	}
	
	@Test
	public void Test02CheckCommentWIthMultiUpdate() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("CheckCommentTestCase.xml","Test02CheckComment");
		executor.execute();
	}
	
	@Test
	public void Test03PostDeleteAndCheckComment() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("CheckCommentTestCase.xml","Test03CheckComment");
		executor.execute();
	}
	
	@Test
	public void Test04PostDeleteAndCheckBroadcast() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("CheckCommentTestCase.xml","Test04CheckComment");
		executor.execute();
	}
	
	@Test
	public void Test05PostDeleteAndCheckBroadcast_QuoteLandOwnerAfterDeleteHisContent() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("CheckCommentTestCase.xml","Test05CheckComment");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

