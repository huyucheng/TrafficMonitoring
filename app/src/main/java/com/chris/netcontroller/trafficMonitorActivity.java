package com.chris.netcontroller;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.chris.netcontroller.fragment.fragment_01;
import com.chris.netcontroller.fragment.fragment_02;
import com.chris.netcontroller.fragment.fragment_03;

public class trafficMonitorActivity extends Activity {

	private fragment_01 fragment_01;
	private FragmentManager fragmentManager;
	private fragment_02 fragment_02;
	private fragment_03 fragment_03;
	


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fragment_01=new fragment_01();
		fragmentManager = getFragmentManager();
		FragmentTransaction ft= fragmentManager.beginTransaction();
		ft.replace(R.id.fragment, fragment_01);
		ft.commit();
	}

	public void click1(View v){
		fragment_01=new fragment_01();
		fragmentManager = getFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.replace(R.id.fragment, fragment_01);
		ft.commit();
	}
	public void click2(View v){
		fragment_02=new fragment_02();
		fragmentManager = getFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.replace(R.id.fragment, fragment_02);
		ft.commit();
	}
	public void click3(View v){
		fragment_03=new fragment_03();
		fragmentManager = getFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.replace(R.id.fragment, fragment_03);
		ft.commit();
	}

}
