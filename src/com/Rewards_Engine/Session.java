//This class uses soem getter and setter methods to use the values later on when required
package com.Rewards_Engine;

public class Session {

	private static String user_type;
	private static String UserName;
	private static String LoginId;
	private static int[] reasonid;
	private static String[] reason;
	private static int used_point;
	private static int yearly_point;
	private static int avail_point;
	private static int redim_point;
	private static int earned_point;
	private static int negative_point;
	private static String[] user_type1;
	private static String[] user_point;
	private static int flag;
	private static int flag1;
	private static int flag2;
	private static int radio_flag;
	private static String users_type;
	private static int posi;
	
	

		
	public static void setSessionuser_type(String user_types) {
	    Session.user_type = user_types;
	}

	public static String getSessionuser_type() {
	    return user_type;
	}
	
	public static void setSessionLoginId(String LoginIds) {
	    Session.LoginId = LoginIds;
	}

	public static String getSessionLoginId() {
	    return LoginId;
	}
	
	public static void setSessionUserName(String UserNames) {
	    Session.UserName = UserNames;
	}

	public static String getSessionUserName() {
	    return UserName;
	}
	public static void setSessionReasonid(int[] reasonids) {
	    Session.reasonid = reasonids;
	}

	public static int[] getSessionReasonid() {
	    return reasonid;
	}
	
	public static void setSessionReason(String[] reasons) {
	    Session.reason = reasons;
	}

	public static String[] getSessionReason() {
	    return reason;
	}
	
	public static void setSessionused_point(int used_points) {
	    Session.used_point = used_points;
	}

	public static int getSessionused_point() {
	    return used_point;
	}
	public static void setSessionyearly_point(int yearly_points) {
	    Session.yearly_point = yearly_points;
	}

	public static int getSessionyearly_point() {
	    return yearly_point;
	}
	public static void setSessionavail_point(int avail_points) {
	    Session.avail_point = avail_points;
	}

	public static int getSessionavail_point() {
	    return avail_point;
	}
	public static void setSessionredim_point(int redim_points) {
	    Session.redim_point = redim_points;
	}

	public static int getSessionredim_point() {
	    return redim_point;
	}
	public static void setSessionearned_point(int earned_points) {
	    Session.earned_point = earned_points;
	}

	public static int getSessionearned_point() {
	    return earned_point;
	}
	public static void setSessionnegative_point(int negative_points) {
	    Session.negative_point = negative_points;
	}

	public static int getSessionnegative_point() {
	    return negative_point;
	}
	public static void setSessionuser_type1(String[] user_types1) {
	    Session.user_type1 = user_types1;
	}

	public static String[] getSessionuser_type1() {
	    return user_type1;
	}
	public static void setSessionuser_point(String[] user_points) {
	    Session.user_point = user_points;
	}

	public static String[] getSessionuser_point() {
	    return user_point;
	}
	
	public static void setSessionflag(int flags) {
	    Session.flag = flags;
	}

	public static int getSessionflag() {
	    return flag;
	}
	
	public static void setSessionflag1(int flags1) {
	    Session.flag1 = flags1;
	}

	public static int getSessionflag1() {
	    return flag1; 
	}
	public static void setSessionflag2(int flags2) {
	    Session.flag2 = flags2;
	}

	public static int getSessionflag2() {
	    return flag2;
	    
	}
	public static void setSessionradio_flag(int radio_flags) {
	    Session.radio_flag = radio_flags;
	}

	public static int getSessionradio_flag() {  
	    return radio_flag;
	    
	}
	public static void setSessionusers_type(String users_types) {
	    Session.users_type = users_types;
	}

	public static String getSessionusers_type() {
	    return users_type;
	}
	public static void setSessionposi(int posis) {
	    Session.posi = posis;
	}

	public static int getSessionposi() {  
	    return posi;
	    
	}
	
}
