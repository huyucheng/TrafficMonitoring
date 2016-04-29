package com.chris.netcontroller.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.chris.netcontroller.R;
import com.chris.netcontroller.dao.MyOpenHelper;
import com.chris.netcontroller.dao.trafficInfoDao;
import com.chris.netcontroller.fragment.fragment_02.myAdapter;
import com.chris.netcontroller.model.TrafficInfo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

public class fragment_03 extends Fragment {

	private Activity mActivity;
	private EditText medit;
	private Button mQuery;
	private BarChart mchart;
	private List<String> xVals;
	private BarDataSet dataSet;
	private MyOpenHelper helper;
	private List<BarEntry> yVals;
	private ArrayList<TrafficInfo> list;
	private BarData data;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mActivity=getActivity();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_03, null);
		medit=(EditText)view.findViewById(R.id.edit_02);
		mQuery=(Button)view.findViewById(R.id.query_02);
		mchart=(BarChart)view.findViewById(R.id.chart);
		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mQuery.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v){
				// TODO Auto-generated method stub
				if(mQuery.getText()!=null){
					
					initData();
				}
				
			}
			
		});
		medit.setOnClickListener(new OnClickListener(){

			private Calendar calendar;

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
	private void initData(){
		helper=new MyOpenHelper(mActivity);
		list = trafficInfoDao.query(helper);
		xVals=new ArrayList<String>();
		yVals=new ArrayList<BarEntry>();
		for(int i=0;i<list.size();i++){
			xVals.add(list.get(i).getTime());
			android.util.Log.d("tag", "ʱ�䣺"+xVals.get(i));
			yVals.add(new BarEntry(list.get(i).getMobileReceive(),i));
		}
		dataSet=new BarDataSet(yVals,"������������");
		dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
		data = new BarData(xVals,dataSet);
		mchart.setData(data);
		mchart.animateY(2000);
		mchart.setDescription("��������");
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	
}
