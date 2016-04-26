package com.tcs.ilp.util;

public class QueryConstants {
	
	static final String TABLE = "TBL_919070_ACCOUNT";
	
	public static final String ADD = "INSERT INTO " + TABLE +
			" VALUES(?,?,?)";
	public static final String VIEW = "SELECT * FROM " + TABLE + 
			" WHERE ID=?";
	public static final String UPDATE = "UPDATE " + TABLE + " SET NAME=?," +
			"ACCTTYPE=? WHERE ID=?";
	public static final String DELETE = "DELETE FROM " + TABLE + 
			" WHERE ID=?";
	public static final String VIEW_ALL = "SELECT * FROM " + TABLE;
	public static final String DEPOSIT = "INSERT INTO " + TABLE + 
			" (BALANCE, ACCUM_INTEREST) VALUES(?,?)";

}
