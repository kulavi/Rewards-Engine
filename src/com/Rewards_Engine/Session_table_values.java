//Storing values in session for further use
package com.Rewards_Engine;

public class Session_table_values {

	private static String user_type;
	private static String UserName;
	private static String LoginId;
	private static int[] reasonid;
	
	
	
	
	
	public static void setSessionuser_type(String user_types) {
	    Session_table_values.user_type = user_types;
	}

	public static String getSessionuser_type() {
	    return user_type;
	}
	
	public static void setSessionLoginId(String LoginIds) {
	    Session_table_values.LoginId = LoginIds;
	}

	public static String getSessionLoginId() {
	    return LoginId;
	}
	
	public static void setSessionUserName(String UserNames) {
	    Session_table_values.UserName = UserNames;
	}

	public static String getSessionUserName() {
	    return UserName;
	}
	public static void setSessionReasonid(int[] reasonids) {
	    Session_table_values.reasonid = reasonids;
	}

	public static int[] getSessionReasonid() {
	    return reasonid;
	}
	
	
}