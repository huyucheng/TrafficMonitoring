package com.chris.netcontroller.fragment;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.chris.netcontroller.R;
import com.chris.netcontroller.dao.MyOpenHelper;
import com.chris.netcontroller.dao.trafficInfoDao;
import com.chris.netcontroller.model.TrafficInfo;

public class fragment_02 extends Fragment{

	private EditText medit;
	private Button mquery;
	private ListView mlist;
	private Activity mActivity;
	private myAdapter adapter;
	private MyOpenHelper helper;
	private ArrayList<TrafficInfo> list;
	private Calendar calendar;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mActivity=getActivity();
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_02, null);
		medit = (EditText)view.findViewById(R.id.edit);
		mquery=(Button)view.findViewById(R.id.query);
		mlist=(ListView)view.findViewById(R.id.list);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mquery.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initData();
				adapter=new myAdapter();
				mlist.setAdapter(adapter);
			}
		});
		medit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				calendar=Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int monthOfYear = calendar.get(Calendar.MONDAY);
				int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
				new android.app.DatePickerDialog(mActivity, new OnDateSetListener(){

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// TODO Auto-generated method stub
						String text=year+"-"+monthOfYear+"-"+dayOfMonth;
						medit.setText(text);
					}
					
				}, year, monthOfYear, dayOfMonth).show();
			}
			
		});
	}
	public void initData(){
		helper=new MyOpenHelper(mActivity);
		list = trafficInfoDao.query(helper);
	}
	class myAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder;
			View view;
			if (convertView==null) {
				holder=new ViewHolder();
				view = View.inflate(mActivity, R.layout.item_listview, null);
				holder.time = (TextView) view.findViewById(R.id.item_time);
				holder.mobileR = (TextView)view.findViewById(R.id.item_moblieRx);
				holder.mobileT = (TextView)view.findViewById(R.id.item_moblieTx);
				holder.wifiR = (TextView)view.findViewById(R.id.item_wifiRx);
				holder.wifiT = (TextView)view.findViewById(R.id.item_wifiTx);
				view.setTag(holder);
			}else {
				view=convertView;
				holder=(ViewHolder) view.getTag();
			}
			TrafficInfo info=list.get(position);
			holder.time.setText(info.getTime());
			holder.mobileR.setText(info.getMobileReceive()+"");
			holder.mobileT.setText(info.getMobileTreasfer()+"");
			holder.wifiR.setText(info.getWifiReceive()+"");
			holder.wifiT.setText(info.getWifiTransfer()+"");
			android.util.Log.d("tag", "---------------------------------------------");
			return view;
		}
		
	}
	class ViewHolder{
		TextView time;
		TextView mobileR;
		TextView mobileT;
		TextView wifiR;
		TextView wifiT ;
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
