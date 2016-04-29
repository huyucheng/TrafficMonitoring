package com.chris.netcontroller.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chris.netcontroller.model.TrafficInfo;
import com.chris.netcontroller.utils.trafficUtils;

public class trafficInfoDao {
	static ArrayList<TrafficInfo> list;
	private static String endTime;
	private static String startTime;
	public static void save(TrafficInfo info,MyOpenHelper helper){
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put("mobileReceive", info.getMobileReceive());
		values.put("mobileTreasfer",info.getMobileReceive());
		values.put("wifiReceive", info.getWifiReceive());
		values.put("wifiTransfer", info.getWifiTransfer());
		values.put("time", info.getTime());
		db.insert("traffic", null, values);
	}
	public static ArrayList<TrafficInfo> query(MyOpenHelper helper){
		if (list!=null) {
			list=null;
		}
		 list = new ArrayList<TrafficInfo>();
		SQLiteDatabase db = helper.getWritableDatabase();
		String sql="select SUM(mobileReceive),SUM(mobileTreasfer),SUM(wifiReceive)" +
				",SUM(wifiTransfer) from traffic where ?<=time and ?>=time";
		for(int i=0;i<=24;i=i+2)
		{
			if (i<10) {
				startTime="0"+i+"-"+"00"+"-"+"00";
				if (i+2<10) {
					endTime ="0"+(i+2)+"-"+"00"+"-"+"00";
				}else {
					endTime="0"+(i+2)+"-"+"00"+"-"+"00";
				}
			}else {
				startTime=i+"-"+"00"+"-"+"00";
				endTime=i+2+"-"+"00"+"-"+"00";
			}
			android.util.Log.d("tag", startTime+"-----------------------"+endTime);
			Cursor cursor = db.rawQuery(sql,new String[]{startTime,endTime});
			android.util.Log.d("tag", cursor.getCount()+"--------------------");
			android.util.Log.d("tag", cursor.getColumnCount()+"列的总数--------------------");
			if (cursor.moveToNext()) {
					TrafficInfo info=new TrafficInfo();
					Long mobileR = cursor.getLong(0);
//					trafficUtils.FormetFileSize(mobileR);
					info.setMobileReceive(mobileR);
					Long mobileT = cursor.getLong(1);
//					trafficUtils.FormetFileSize(mobileT);
					info.setMobileReceive(mobileT);
					Long wifiR=cursor.getLong(2);
//					trafficUtils.FormetFileSize(wifiR);
					info.setWifiReceive(wifiR);
					Long wifiT=cursor.getLong(3);
//					wifiT=trafficUtils.FormetFileSize(wifiT);
					info.setWifiTransfer(wifiT);
					info.setTime(i+"");
					list.add(info);
				}
			cursor.close();
			}
		return list;
	}
}
