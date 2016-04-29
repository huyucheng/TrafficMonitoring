package com.chris.netcontroller;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.net.TrafficStats;
import android.os.Bundle;
import android.os.IBinder;
import android.text.format.Time;

import com.chris.netcontroller.dao.MyOpenHelper;
import com.chris.netcontroller.dao.trafficInfoDao;
import com.chris.netcontroller.model.TrafficInfo;
import com.chris.netcontroller.utils.trafficUtils;

public class trafficMonitorService extends Service {

	private TrafficInfo info;
	private TrafficInfo trafficInfo;
	Timer time=new Timer(true);
	TimerTask task=new TimerTask(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			trafficInfo = getInfo();
			Intent intent=new Intent("com.chris.hadoop");
			Bundle bundle=new Bundle();
			bundle.putLong("moblieRx", trafficInfo.getMobileReceive());
			bundle.putLong("mobileTx", trafficInfo.getMobileTreasfer());
			bundle.putLong("wifiRx", trafficInfo.getWifiReceive());
			bundle.putLong("wifiTx", trafficInfo.getWifiTransfer());
			bundle.putString("time", trafficInfo.getTime());
			intent.putExtras(bundle);
			sendBroadcast(intent);
			trafficInfoDao.save(trafficInfo,helper);
		}
		
	};
	private MyOpenHelper helper;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		helper=new MyOpenHelper(this);
		time.schedule(task, 0, 10000);
	}
	
	@Override
	public void onDestroy(){
		// TODO Auto-generated method stub
		helper.close();
		super.onDestroy();
	}
	public TrafficInfo getInfo(){
		long totalRxBytes = TrafficStats.getTotalRxBytes();
		long totalTxBytes = TrafficStats.getTotalTxBytes();
		long mobileRxBytes = TrafficStats.getMobileRxBytes();
		long mobileTxBytes = TrafficStats.getMobileTxBytes();
		long wifiRxBytes=totalRxBytes-mobileRxBytes;
		long wifiTxBytes=totalTxBytes-mobileTxBytes;
		info = new TrafficInfo();
		info.setMobileReceive(mobileRxBytes);
		info.setMobileTreasfer(mobileTxBytes);
		info.setWifiReceive(wifiRxBytes);
		info.setWifiTransfer(wifiTxBytes);
		Time time=new Time();
		time.setToNow();
		int minute=time.minute;
		int hour=time.hour;
		int second=time.second;
		info.setTime(hour+"-"+minute+"-"+second);
		android.util.Log.d("tag", info.getTime());
		return info;
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
