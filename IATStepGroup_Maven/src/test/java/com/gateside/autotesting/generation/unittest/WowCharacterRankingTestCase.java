package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class WowCharacterRankingTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01WowCharacterRanking_NotLogin() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterRankingTestCase.xml","Test01");
		executor.execute();
	}
	
	@Test
	public void Test02WowCharacterRanking_Login() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterRankingTestCase.xml","Test02");
		executor.execute();
	}
	
	@Test
	public void Test03WowCharacterSRanking_NotExistCharcter() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterRankingTestCase.xml","Test03");
		executor.execute();
	}
	
	@Test
	public void Test04WowCharacterRanking_RangeoutCharacter() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterRankingTestCase.xml","Test04");
		executor.execute();
	}
	
	@Test
	public void Test05WowCharacterRanking_NoNumberCharacter() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterRankingTestCase.xml","Test05");
		executor.execute();
	}
	
	@Test
	public void Test06WowCharacterRanking_Page() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterRankingTestCase.xml","Test06");
		executor.execute();
	}
	
	@Test
	public void Test07WowCharacterRanking_NoExistBossId() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterRankingTestCase.xml","Test07");
		executor.execute();
	}
	
	@Test
	public void Test08WowCharacterRanking_RealmIdNull() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterRankingTestCase.xml","Test08");
		executor.execute();
	}
	
	@Test
	public void Test09WowCharacterRanking_RankingTypeNull() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowCharacterRankingTestCase.xml","Test09");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

