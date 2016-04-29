package com.chris.netcontroller.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chris.netcontroller.R;
import com.chris.netcontroller.trafficMonitorService;
import com.chris.netcontroller.utils.trafficUtils;

public class fragment_01 extends Fragment{
	private myReceiver receiver;
	private Activity mActivity;
	private TextView mmobileRx;
	private TextView mmobileTx;
	private TextView mwifiRx;
	private TextView  mwifiTx;
	private Button mStart;
	private Button mStop;
	@Override
	public void onCreate(Bundle savedInstanceState){
		// TODO Auto-generated method stub
		mActivity=getActivity();
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_01, null);
		mmobileRx = (TextView) view.findViewById(R.id.mobileRx);
		mmobileTx = (TextView) view.findViewById(R.id.mobileTx);
		mwifiRx = (TextView) view.findViewById(R.id.wifiRx);
		mwifiTx = (TextView) view.findViewById(R.id.wifiTx);
		mStart=(Button)view.findViewById(R.id.startMonitor);
		mStop=(Button)view.findViewById(R.id.stopMonitor);
		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		// TODO Auto-generated method stub
		mStart.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				// TODO Auto-generated method stub
				if (receiver==null) {
					receiver = new myReceiver();
					IntentFilter filter=new IntentFilter();
					filter.addAction("com.chris.hadoop v");
					mActivity.registerReceiver(receiver, filter);
				}
				Intent intent=new Intent(getActivity(),trafficMonitorService.class);
				mActivity.startService(intent);
				Toast.makeText(mActivity," ",Toast.LENGTH_SHORT).show();
			}
		});
		mStop.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v){
				// TODO Auto-generated method stub
				if (receiver!=null) {
					mActivity.unregisterReceiver(receiver);
					receiver=null;
				}
				Intent intent=new Intent(mActivity,trafficMonitorService.class);
				mActivity.stopService(intent);
				Toast.makeText(mActivity, "ֹͣ",Toast.LENGTH_SHORT).show();
			}
			
		});
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	class myReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent){
			// TODO Auto-generated method stub
			Bundle bundle = intent.getExtras();
			long moblieRx = bundle.getLong("moblieRx");
			long moblieTx = bundle.getLong("mobileTx");
			long wifiRx = bundle.getLong("wifiRx");
			long wifiTx = bundle.getLong("wifiTx");
			String moblieR = trafficUtils.FormetFileSize(moblieRx);
			String mobileT=trafficUtils.FormetFileSize(moblieTx);
			String wifiR=trafficUtils.FormetFileSize(wifiRx);
			String wifiT=trafficUtils.FormetFileSize(wifiTx);
			Log.d("tag", bundle.toString());
			mmobileRx.setText(moblieR);
			mmobileTx.setText(mobileT);
			mwifiRx.setText(wifiR);
			mwifiTx.setText(wifiT);
		}
		
	}
	

}
