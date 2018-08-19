package com.wanmei.mobile.iat.common;



public class Constant {



//	public final static String MEMOCACHE_IP = "10.3.254.227";
//	public final static String DB_URL_KA = "jdbc:mysql://10.3.254.227:3306/ngaboard";
//	public final static String DB_USER = "apple_user";
//	public final static String DB_PWD = "aabbccdd";
	public final static String MEMOCACHE_IP = "10.3.247.56";
	public final static String DB_URL_KA = "jdbc:mysql://10.3.247.56:3306/ngaboard";
	public final static String DB_USER = "root";
	public final static String DB_PWD= "123456";
//	public final static String MEMOCACHE_IP = "10.3.254.228";
//	public final static String DB_URL_KA = "jdbc:mysql://10.3.254.228:3306/ngaboard";
//	public final static String DB_USER = "root";
//	public final static String DB_PWD= "z228qa";
	public final static int MEMOCACHE_PORT = 11211;
	public final static String RANDOM_VALUE_ALL = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public final static String RANDOM_VALUE= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public final static String HTTP_METHOD = "POST";
	public final static String APP_ID="1010";
	public final static String APP_KEY="392e916a6d1d8b7523e2701470000c30bc2165a1";
	public final static String PWD = "asd123";
    public class Url {
		public final static String SyncFavorForum = "http://test.bbs.ngacn.cc/app_api.php?__lib=favorforum&__act=sync";
		public final static String AddFavorForum = "http://test.bbs.ngacn.cc/app_api.php?__lib=favorforum&__act=add";
		public final static String DeleteFavorForum = "http://test.bbs.ngacn.cc/app_api.php?__lib=favorforum&__act=del";
		public final static String ForumSearch = "http://test.bbs.ngacn.cc/app_api.php?__lib=forum&__act=search";
		public final static String SubjectList = "http://test.bbs.ngacn.cc/app_api.php?__lib=subject&__act=list";
		public final static String SubjectSubscription = "http://test.bbs.ngacn.cc/app_api.php?__lib=subject&__act=subscription";
		public final static String SubjectSearch ="http://test.bbs.ngacn.cc/app_api.php?__lib=subject&__act=search";
		public final static String GiftUserList = "http://test.bbs.ngacn.cc/app_api.php?__lib=gift&__act=userlist";
		public final static String GiftSend = "http://test.bbs.ngacn.cc/app_api.php?__lib=gift&__act=send";
		public final static String GiftList = "http://test.bbs.ngacn.cc/app_api.php?__lib=gift&__act=list";
		public final static String BlackStoreList = "http://test.bbs.ngacn.cc/app_api.php?__lib=blackstore&__act=list";
		public final static String BlackStoreOrder = "http://test.bbs.ngacn.cc/app_api.php?__lib=blackstore&__act=order";
		public final static String BlackStoreExchange = "http://test.bbs.ngacn.cc/app_api.php?__lib=blackstore&__act=exchange";
		public final static String BlackStoreAddress = "http://test.bbs.ngacn.cc/app_api.php?__lib=blackstore&__act=address";
		public final static String BlackStorePurchased = "http://test.bbs.ngacn.cc/app_api.php?__lib=blackstore&__act=purchased";
		public final static String BlackStoreLastaddr = "http://test.bbs.ngacn.cc/app_api.php?__lib=blackstore&__act=lastaddr";
		public final static String LOGIN = "http://account.178.com/app_api.php?_act=login";
		public final static String REGISTER ="http://account.178.com/app_api.php?_act=register";
		public final static String AddBlock ="http://test.bbs.ngacn.cc/app_api.php?__lib=block&__act=add";
		public final static String DelBlock ="http://test.bbs.ngacn.cc/app_api.php?__lib=block&__act=del";
		public final static String BlockList ="http://test.bbs.ngacn.cc/app_api.php?__lib=block&__act=list";
		public final static String UserDetail ="http://test.bbs.ngacn.cc/app_api.php?__lib=user&__act=detail";
		public final static String UserSubjects ="http://test.bbs.ngacn.cc/app_api.php?__lib=user&__act=subjects";
		public final static String UserReplys ="http://test.bbs.ngacn.cc/app_api.php?__lib=user&__act=replys";
		public final static String HomeHasnew ="http://test.bbs.ngacn.cc/app_api.php?__lib=home&__act=hasnew";
		public final static String HomeBannerrecm ="http://test.bbs.ngacn.cc/app_api.php?__lib=home&__act=bannerrecm";
		public final static String HomeRecmthreads ="http://test.bbs.ngacn.cc/app_api.php?__lib=home&__act=recmthreads";
		public final static String HomeHot ="http://test.bbs.ngacn.cc/app_api.php?__lib=home&__act=hot";
		public final static String HomeCategory ="http://test.bbs.ngacn.cc/app_api.php?__lib=home&__act=category";
		public final static String PostList ="http://test.bbs.ngacn.cc/app_api.php?__lib=post&__act=list";
		public final static String PostCheck ="http://test.bbs.ngacn.cc/app_api.php?__lib=post&__act=check";
		public final static String PostNew ="http://test.bbs.ngacn.cc/app_api.php?__lib=post&__act=new";
		public final static String PostTitletype ="http://test.bbs.ngacn.cc/app_api.php?__lib=post&__act=titletype";
		public final static String PostTietiao ="http://test.bbs.ngacn.cc/app_api.php?__lib=post&__act=tietiao";
		public final static String PostReply ="http://test.bbs.ngacn.cc/app_api.php?__lib=post&__act=reply";
		public final static String PostModify ="http://test.bbs.ngacn.cc/app_api.php?__lib=post&__act=modify";
		public final static String PostReport ="http://test.bbs.ngacn.cc/app_api.php?__lib=post&__act=report";
		public final static String PostQuote ="http://test.bbs.ngacn.cc/app_api.php?__lib=post&__act=quote";
		public final static String MessageList ="http://test.bbs.ngacn.cc/app_api.php?__lib=message&__act=list";
		public final static String MessageDetail ="http://test.bbs.ngacn.cc/app_api.php?__lib=message&__act=detail";
		public final static String MessageSend ="http://test.bbs.ngacn.cc/app_api.php?__lib=message&__act=send";
		public final static String MessageReply ="http://test.bbs.ngacn.cc/app_api.php?__lib=message&__act=reply";
		public final static String GetAddressList ="http://test.bbs.ngacn.cc/app_api.php?__lib=addresslist&__act=get";
		public final static String GetVideoHistories ="http://test.bbs.ngacn.cc/app_api.php?__lib=videohistories&__act=get";
//		public final static String GetNotification ="http://test.appcheck.178.com/app_api.php?get_notification";
		public final static String GetNotification ="http://test.bbs.ngacn.cc/misc/app_check/app_api.php?get_notification";
		public final static String GetPhoneCode ="http://account.178.com/app_api.php?_act=bindphone_send_vcode";
		public final static String BindPhone ="http://account.178.com/app_api.php?_act=bindphone";
		public final static String GetOldPhoneCode ="http://account.178.com/app_api.php?_act=updphone_send_vcode_old";
		public final static String CheckCode ="http://account.178.com/app_api.php?_act=updphone_check_vcode";
		public final static String GetNewPhoneCode ="http://account.178.com/app_api.php?_act=updphone_send_vcode_new";
		public final static String UpdPhone ="http://account.178.com/app_api.php?_act=updphone";
		public final static String UserUpdate ="http://test.bbs.ngacn.cc/app_api.php?__lib=user&__act=update";
		public final static String UsersNearLoc ="http://test.bbs.ngacn.cc/app_api.php?__lib=nearby&__act=usersNearLoc";
		public final static String CheckInDoTask ="http://test.bbs.ngacn.cc/app_api.php?__lib=checkin&__act=dotask";
		public final static String SubjectHot ="http://test.bbs.ngacn.cc/app_api.php?__lib=subject&__act=hot";
		public final static String HomeTagforums ="http://test.bbs.ngacn.cc/app_api.php?__lib=home&__act=tagforums";
		public final static String WowRealmList ="http://test.bbs.ngacn.cc/app_api.php?__lib=wow&__act=realmlist";
		public final static String WowCharacterList ="http://test.bbs.ngacn.cc/app_api.php?__lib=wow&__act=characterlist";
		public final static String WowCharacterAdd ="http://test.bbs.ngacn.cc/app_api.php?__lib=wow&__act=characteradd";
		public final static String WowCharacterRemove ="http://test.bbs.ngacn.cc/app_api.php?__lib=wow&__act=characterremove";
		public final static String WowCharacterShow ="http://test.bbs.ngacn.cc/app_api.php?__lib=wow&__act=charactershow";
		public final static String WowCharacterRanking ="http://test.bbs.ngacn.cc/app_api.php?__lib=wow&__act=characterranking";
		public final static String WowRaidList ="http://test.bbs.ngacn.cc/app_api.php?__lib=wow&__act=raidlist";
		public final static String WowGuildRanking ="http://test.bbs.ngacn.cc/app_api.php?__lib=wow&__act=guildranking";
		public final static String WowCharacterRefresh ="http://test.bbs.ngacn.cc/app_api.php?__lib=wow&__act=characterrefresh";
    }
    public class Sql {
    	public final static String Block_Member_From_Block_Sql="select p.from from pw_message_block p join pw_members m on m.uid=p.from where p.to=26228182 ORDER BY p.id";
    	public final static String Rand_Uid_From_Members_Sql="select uid from pw_members ORDER BY RAND() LIMIT 1";
    	public final static String Rand_Fid_From_Forums_Sql="select fid from pw_forums ORDER BY RAND() LIMIT 1";
    	public final static String Rand_OrderSn_From_Order_Sql="select order_sn from pw_black_store_order ORDER BY RAND() LIMIT 1";
    	public final static String Rand_Id_From_Order_Sql="select id from pw_black_store ORDER BY RAND() LIMIT 1";
    	public final static String Rand_Fid_Sql="select fid from pw_forums ORDER BY RAND() LIMIT 1";
    	public final static String Get_Fid_Sql="select fid,postdate,subject,author,authorid from pw_threads where tid=";
    	public final static String Get_User_Info_Sql="select username,credit,groupid,memberid,yz,regdate,postnum,rvrc,money,thisvisit from pw_members where uid=";
    	public final static String Get_Id_Medal_Sql="select id from pw_medal where owner_uid=";
    	public final static String Get_Name_Groups_Sql="select bit_data,name from pw_groups where id=";
    	public final static String Get_Home_TAG_FORUMS_Sql="SELECT target_id FROM `pw_home_recommend` WHERE type=4 ORDER BY id DESC";
    	public final static String Get_NAME_FID_Sql="SELECT name FROM `pw_forums` where fid=FID";
    	public final static String Get_SUBJECT_HOT="SELECT `tid`,`fid`,`quote_from`,`quote_to`,`titlefont`,`titlefont` AS `topic_misc`,`author`,`authorid`,`subject`,`type`,`postdate`,`lastpost`,`lastposter`,`replies`,`lastmodify`,`recommend` FROM `pw_threads` WHERE `tid` IN ( 7172820,7173076,7174755,7174333,7174518,7174111,7174271,7173563,7172849,7173122,7173376,7174857,7173246,7173820,7175294,7173814,7174206,7173736,7174751,7173392,7173346,7175274,7173714,7173410,7172838,7174140,7174351,7173487,7175103,7173344,7172892,7173891,7174301,7174769,7172842) ORDER BY replies DESC";
    	public final static String Get_NAME_Sql="SELECT id,`name` FROM `pw_wow_realm` ORDER BY id ASC";
    	public final static String Get_WC_ID_Sql="SELECT id FROM `pw_wow_character` ORDER BY RAND() limit 1";
    	public final static String Get_PROFILR_Sql="SELECT bind_uid,locale,realm_id,guild_id,equipment_level,achievement_point,base_info FROM `pw_wow_character` WHERE id=ID";
    	public final static String Get_GUILD_RAID_Sql="SELECT distinct(raid_id) FROM `pw_wow_guild_raid` ORDER BY id DESC ";
    	public final static String Get_CHARACTER_RAID_Sql="SELECT distinct(raid_id) FROM `pw_wow_character_raid` ";
    	public final static String Get_GUILD_ACHIEVEMENT_ID_Sql="SELECT distinct(achievement_id) FROM `pw_wow_guild_raid` where raid_id=ID ORDER BY id ASC";
    	public final static String Get_CHARACTER_ACHIEVEMENT_ID_Sql="SELECT distinct(mythicAchievementId) FROM `pw_wow_character_raid` where raid_id=ID ORDER BY id ASC";
    	public final static String Get_REALM_ID_Sql="SELECT DISTINCT(realm_id) FROM `pw_wow_character` ORDER BY RAND() LIMIT 1";
    	public final static String Get_CHARACTER_RANKING_Sql="SELECT id,bind_uid,locale,realm_id,base_info,arena_22,arena_33,arena_rbg,equipment_level,achievement_point,mounts_num,pet_num FROM `pw_wow_character` WHERE realm_id=ID ORDER BY TYPE DESC";
    	public final static String Get_CHARACTER_RANKING1_Sql="SELECT id,bind_uid,locale,realm_id,base_info,arena_22,arena_33,arena_rbg,equipment_level,achievement_point,mounts_num,pet_num FROM `pw_wow_character`  ORDER BY TYPE DESC";
    	public final static String Get_GUILD_RANKING_Sql="SELECT id,locale,realm_id,achievement_point FROM `pw_wow_guild` WHERE realm_id=ID ORDER BY achievement_point DESC";
    	public final static String Get_GUILD_RANKING1_Sql="SELECT id,locale,realm_id,achievement_point FROM `pw_wow_guild` ORDER BY achievement_point DESC";
    	
    	
    }
    
}
