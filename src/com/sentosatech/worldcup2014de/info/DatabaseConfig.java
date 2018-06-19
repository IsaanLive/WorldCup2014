package com.sentosatech.worldcup2014de.info;

import com.sentosatech.worldcup2014de.PacketUtility;

public final class DatabaseConfig {

	private static int DB_VERSION = 1;
	private static String DB_NAME = "Worldcup2014.sqlite";
	private static DatabaseConfig instance = null;

	/**
	 * constructor
	 */
	public DatabaseConfig() {

	}

	/**
	 * get database version
	 * @return
	 */
	public int getDB_VERSION() {
		return DB_VERSION;
	}

	/**
	 * get database name
	 * @return
	 */
	public String getDB_NAME() {
		return DB_NAME;
	}

	/**
	 * get class instance
	 * @return
	 */

	public static DatabaseConfig getInstance() {
		if (instance == null) {
			instance = new DatabaseConfig();
		}
		return instance;
	}
	/**
	 * get database path
	 * @return
	 */
	public String getDatabasepath()
	{
		PacketUtility packetUtility=new PacketUtility();
		return "data/data/"+packetUtility.getPacketName()+"/databases/";
		
	}
	/**
	 * get database full path
	 * @return
	 */
	public String getDatabasefullpath()
	{
		return getDatabasepath()+ DB_NAME;
		
	}
}
