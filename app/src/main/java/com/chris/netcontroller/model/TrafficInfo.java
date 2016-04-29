package com.chris.netcontroller.model;


public class TrafficInfo {
	private int id;
	private long mobileReceive;
	private long mobileTreasfer;
	private long mRx;
	private long mTx;
	private long wifiReceive;
	private long wifiTransfer;
	private long wRx;
	private long wTx;
	private int interval;
	private String time;
	private int state;
	public long getmRx() {
		return mRx;
	}
	public void setmRx(long mRx) {
		this.mRx = mRx;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getMobileReceive() {
		return mobileReceive;
	}
	public void setMobileReceive(long mobileReceive) {
		this.mobileReceive = mobileReceive;
	}
	public long getMobileTreasfer() {
		return mobileTreasfer;
	}
	public void setMobileTreasfer(long mobileTreasfer) {
		this.mobileTreasfer = mobileTreasfer;
	}
	public long getmTx() {
		return mTx;
	}
	public void setmTx(long mTx) {
		this.mTx = mTx;
	}
	public long getWifiReceive() {
		return wifiReceive;
	}
	public void setWifiReceive(long wifiReceive) {
		this.wifiReceive = wifiReceive;
	}
	public long getWifiTransfer() {
		return wifiTransfer;
	}
	public void setWifiTransfer(long wifiTransfer) {
		this.wifiTransfer = wifiTransfer;
	}
	public long getwRx() {
		return wRx;
	}
	public void setwRx(long wRx) {
		this.wRx = wRx;
	}
	public long getwTx() {
		return wTx;
	}
	public void setwTx(long wTx) {
		this.wTx = wTx;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
