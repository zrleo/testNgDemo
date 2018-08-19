package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class GetBroadcastTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01get_broadcastsGet20Reverse() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("GetBroadcastTestCase.xml","Test01get_broadcasts");
		executor.execute();
	}
	
	@Test
	public void Test02get_broadcastsGet10Reserve() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("GetBroadcastTestCase.xml","Test02get_broadcasts");
		executor.execute();
	}
	
	@Test
	public void Test03get_broadcastsGet10PositiveSequence() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("GetBroadcastTestCase.xml","Test03get_broadcasts");
		executor.execute();
	}
	
	@Test
	public void Test04get_broadcastsGet10PositiveSequenceBelowSpecifiedCid() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("GetBroadcastTestCase.xml","Test04get_broadcasts");
		executor.execute();
	}
	
	@Test
	public void Test05get_broadcastsGet10ReverseBelowSpecifiedCid() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("GetBroadcastTestCase.xml","Test05get_broadcasts");
		executor.execute();
	}
	
	@Test
	public void Test06get_broadcastsLoginToGet10ReverseBelowSpecifiedCid() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("GetBroadcastTestCase.xml","Test06get_broadcasts");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

