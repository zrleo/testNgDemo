package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class PostCommentTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01LandOwnerReplyLandOwner() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostCommentTestCase.xml","Test01replyLandOwner");
		executor.execute();
	}
	
	@Test
	public void Test02replyCid260() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostCommentTestCase.xml","Test02reply260");
		executor.execute();
	}
	
	@Test
	public void Test03quoteCid260() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostCommentTestCase.xml","Test03quote260");
		executor.execute();
	}
	
	@Test
	public void Test04PostSenstiveWord() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostCommentTestCase.xml","Test04senstiveWord");
		executor.execute();
	}
	
	@Test
	public void Test05LorderQuoteLorder() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostCommentTestCase.xml","Test05");
		executor.execute();
	}
	
	@Test
	public void Test06postSameContentBetweenDifferentPeople() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostCommentTestCase.xml","Test06");
		executor.execute();
	}
	
	@Test
	public void Test07postSameContentContinuous() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostCommentTestCase.xml","Test07");
		executor.execute();
	}
	
	@Test
	public void Test08postSameContentNotContinuous() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostCommentTestCase.xml","Test08");
		executor.execute();
	}
	
	@Test
	public void Test015quoteLandOwnerUploadJpg() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostCommentTestCase.xml","Test015quoteLandOwnerUploadJpg");
		executor.execute();
	}
	
	@Test
	public void Test16quoteLandOwnerUploadPng() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostCommentTestCase.xml","Test16quoteLandOwnerUploadPng");
		executor.execute();
	}
	
	@Test
	public void Test09quoteLandOwnerUploadJpeg() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostCommentTestCase.xml","Test09quoteLandOwnerUploadJpeg");
		executor.execute();
	}
	
	@Test
	public void Test10quoteLandOwnerUploadGif() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostCommentTestCase.xml","Test10quoteLandOwnerUploadGif");
		executor.execute();
	}
	
	@Test
	public void Test11QuoteLandOwnerUploadJPG() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostCommentTestCase.xml","Test11QuoteLandOwnerUploadJPG");
		executor.execute();
	}
	
	@Test
	public void Test12QuoteLandOwnerUploadPNG() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostCommentTestCase.xml","Test12QuoteLandOwnerUploadPNG");
		executor.execute();
	}
	
	@Test
	public void Test13QuoteLandOwnerUploadJPEG() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostCommentTestCase.xml","Test13QuoteLandOwnerUploadJPEG");
		executor.execute();
	}
	
	@Test
	public void Test14QuoteLandOwnerUploadGIF() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("PostCommentTestCase.xml","Test14QuoteLandOwnerUploadGIF");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

