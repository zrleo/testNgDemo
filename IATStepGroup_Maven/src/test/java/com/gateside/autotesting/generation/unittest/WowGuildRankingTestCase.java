package com.gateside.autotesting.generation.unittest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.gateside.autotesting.Gat.executor.CaseExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceSingleStepExecutor;
import com.gateside.autotesting.Gat.executor.InterfaceStepsExecutor;

public class WowGuildRankingTestCase {

	@BeforeTest
	public void beforeTestMethod(){
	}
	
	@BeforeMethod
	public void beforeMethod(){
	}
	
	
	@Test
	public void Test01WowGuildRanking_NotLogin() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowGuildRankingTestCase.xml","Test01");
		executor.execute();
	}
	
	@Test
	public void Test02WowGuildRanking_Login() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowGuildRankingTestCase.xml","Test02");
		executor.execute();
	}
	
	@Test
	public void Test03WowGuildRanking_NotExistCharcter() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowGuildRankingTestCase.xml","Test03");
		executor.execute();
	}
	
	@Test
	public void Test04WowGuildRanking_RangeoutCharacter() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowGuildRankingTestCase.xml","Test04");
		executor.execute();
	}
	
	@Test
	public void Test05WowGuildRanking_NoNumberCharacter() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowGuildRankingTestCase.xml","Test05");
		executor.execute();
	}
	
	@Test
	public void Test06WowGuildRanking_Page() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowGuildRankingTestCase.xml","Test06");
		executor.execute();
	}
	
	@Test
	public void Test07WowGuildRanking_NoExistBossId() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowGuildRankingTestCase.xml","Test07");
		executor.execute();
	}
	
	@Test
	public void Test08WowCharacterRanking_RealmIdNull() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowGuildRankingTestCase.xml","Test08");
		executor.execute();
	}
	
	@Test
	public void Test09WowGuildRanking_RankingTypeNull() throws Exception{
		CaseExecutor executor = new InterfaceStepsExecutor("WowGuildRankingTestCase.xml","Test09");
		executor.execute();
	}
	
	
	@AfterMethod
	public void afterMethod(){
	}
	
	@AfterClass
	public void afterClassMethod(){
	}
}

