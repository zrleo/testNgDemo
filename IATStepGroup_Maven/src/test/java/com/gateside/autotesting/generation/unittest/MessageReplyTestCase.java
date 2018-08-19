package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class MessageReplyTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01NotLoginMessageSend() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("MessageReplyTestCase.xml","Test01");
		executor.execute();
	}
	
	@Test
	public void Test02LoginSubjectLong() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("MessageReplyTestCase.xml","Test02");
		executor.execute();
	}
	
	@Test
	public void Test03LoginsubjectOrContentShort() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("MessageReplyTestCase.xml","Test03");
		executor.execute();
	}
	
	@Test
	public void Test04LoginSubjectOrContentNull() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("MessageReplyTestCase.xml","Test04");
		executor.execute();
	}
	
	@Test
	public void Test05LoginContentLong() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("MessageReplyTestCase.xml","Test05");
		executor.execute();
	}
	
	@Test
	public void Test06LoginFixedMid() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("MessageReplyTestCase.xml","Test06");
		executor.execute();
	}
	
	@Test
	public void Test07LoginRandomMid() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("MessageReplyTestCase.xml","Test07");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

