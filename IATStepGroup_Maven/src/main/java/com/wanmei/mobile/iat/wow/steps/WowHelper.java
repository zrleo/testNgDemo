package com.wanmei.mobile.iat.wow.steps;

import com.wanmei.mobile.iat.common.RandomHelper;

public class WowHelper {
	public static String getRankingTypeForCharacter() throws Exception{
		String ranking_type = "";
		switch (RandomHelper.getRandomNum(0, 8)) {
		case 0:
			ranking_type = "raid";
			break;
		case 1:
			ranking_type = "arena_22";
			break;
		case 2:
			ranking_type = "arena_33";
			break;
		case 3:
			ranking_type="arena_rbg";
			break;
		case 4:
			ranking_type="equipment_level";
			break;
		case 5:
			ranking_type="achievement_point";
			break;
		case 6:
			ranking_type="mounts_num";
			break;
		case 7:
			ranking_type="pet_num";
			break;
			
		}
		return ranking_type;
	}
	public static String getType() throws Exception
	{
		String type="";
		switch (RandomHelper.getRandomNum(0, 2)) {
		case 0:
			type="character";
			break;
		case 1:
			type="guild";
			break;

		}
		return type;
		
	}
	public static String getRankingTypeForGuild() throws Exception
	{
		String ranking_type="";
		switch (RandomHelper.getRandomNum(0, 2)) {
		case 0:
			ranking_type="raid";
			break;
		case 1:
			ranking_type="achievement_point";
			break;
		}
		return ranking_type;
		
	}
	

}
