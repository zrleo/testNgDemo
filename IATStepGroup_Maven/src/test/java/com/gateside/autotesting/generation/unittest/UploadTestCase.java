package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class UploadTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01UploadJpg() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("UploadTestCase.xml","Test01");
		executor.execute();
	}
	
	@Test
	public void Test02UploadPng() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("UploadTestCase.xml","Test02");
		executor.execute();
	}
	
	@Test
	public void Test03UploadBmpNotSupport() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("UploadTestCase.xml","Test03");
		executor.execute();
	}
	
	@Test
	public void Test04UploadJpeg() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("UploadTestCase.xml","Test04");
		executor.execute();
	}
	
	@Test
	public void Test05UploadGif() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("UploadTestCase.xml","Test05");
		executor.execute();
	}
	
	@Test
	public void Test06UploadPNG() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("UploadTestCase.xml","Test06");
		executor.execute();
	}
	
	@Test
	public void Test07UploadJPG() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("UploadTestCase.xml","Test07");
		executor.execute();
	}
	
	@Test
	public void Test08UploadJPEG() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("UploadTestCase.xml","Test08");
		executor.execute();
	}
	
	@Test
	public void Test09UploadGIF() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("UploadTestCase.xml","Test09");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

