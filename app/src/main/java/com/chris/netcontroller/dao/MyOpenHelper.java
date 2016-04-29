package com.chris.netcontroller.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {
	String sql="create table traffic(" +
				"_id integer primary key autoincrement,"+
				"mobileReceive integer,"+
				"mobileTreasfer integer,"+
				"mRx integer,"+
				"mTx integer,"+
				"wifiReceive integer,"+
				"wifiTransfer integer,"+
				"wRx integer,"+
				"wTx integer,"+
				"interval integer,"+
				"time varchar(20),"+
				"state integer"+
			")";
	public MyOpenHelper(Context context) {
		super(context, "trafficInfo.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
