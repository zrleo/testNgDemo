package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class PostBroadcastTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01replyLandOwner() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostBroadcastTestCase.xml","Test01reply0");
		executor.execute();
	}
	
	@Test
	public void Test02replyCid260() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostBroadcastTestCase.xml","Test02reply260");
		executor.execute();
	}
	
	@Test
	public void Test03quoteCid260() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostBroadcastTestCase.xml","Test03quote260");
		executor.execute();
	}
	
	@Test
	public void Test04PostSenstiveWord() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostBroadcastTestCase.xml","Test04senstiveWord");
		executor.execute();
	}
	
	@Test
	public void Test05senstiveWordNotLorder() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostBroadcastTestCase.xml","Test05senstiveWord");
		executor.execute();
	}
	
	@Test
	public void Test06quoteLandOwner() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostBroadcastTestCase.xml","Test06quoteLandOwner");
		executor.execute();
	}
	
	@Test
	public void Test07quoteLandOwnerUploadJpg() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostBroadcastTestCase.xml","Test07quoteLandOwnerUploadJpg");
		executor.execute();
	}
	
	@Test
	public void Test08quoteLandOwnerUploadPng() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostBroadcastTestCase.xml","Test08quoteLandOwnerUploadPng");
		executor.execute();
	}
	
	@Test
	public void Test09quoteLandOwnerUploadJpeg() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostBroadcastTestCase.xml","Test09quoteLandOwnerUploadJpeg");
		executor.execute();
	}
	
	@Test
	public void Test10quoteLandOwnerUploadGif() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostBroadcastTestCase.xml","Test10quoteLandOwnerUploadGif");
		executor.execute();
	}
	
	@Test
	public void Test11QuoteLandOwnerUploadJPG() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostBroadcastTestCase.xml","Test11QuoteLandOwnerUploadJPG");
		executor.execute();
	}
	
	@Test
	public void Test12QuoteLandOwnerUploadPNG() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostBroadcastTestCase.xml","Test12QuoteLandOwnerUploadPNG");
		executor.execute();
	}
	
	@Test
	public void Test13QuoteLandOwnerUploadJPEG() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostBroadcastTestCase.xml","Test13QuoteLandOwnerUploadJPEG");
		executor.execute();
	}
	
	@Test
	public void Test14QuoteLandOwnerUploadGIF() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostBroadcastTestCase.xml","Test14QuoteLandOwnerUploadGIF");
		executor.execute();
	}
	
	@Test
	public void Test15PostSameContentContinuous() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostBroadcastTestCase.xml","Test15PostSameContentContinuous");
		executor.execute();
	}
	
	@Test
	public void Test16PostDifferentContentContinuous() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostBroadcastTestCase.xml","Test16PostDifferentContentContinuous");
		executor.execute();
	}
	
	@Test
	public void Test17PostTooLongContent() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostBroadcastTestCase.xml","Test17PostTooLongContent");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

