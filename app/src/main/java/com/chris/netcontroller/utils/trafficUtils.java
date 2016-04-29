package com.chris.netcontroller.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class trafficUtils {
	 private static SimpleDateFormat format;
	public static String FormetFileSize(long fileS) {
	        DecimalFormat df = new DecimalFormat("#.00");
	        String fileSizeString = "";
	        if (fileS < 1024) {
	            fileSizeString = df.format((double) fileS) + "B";
	        } else if (fileS < 1048576) {
	            fileSizeString = df.format((double) fileS / 1024) + "K";
	        } else if (fileS < 1073741824) {
	            fileSizeString = df.format((double) fileS / 1048576) + "M";
	        } else {
	            fileSizeString = df.format((double) fileS / 1073741824) + "G";
	        }
	        return fileSizeString;
	    }
	 public static String currentTime(){
		 format = new SimpleDateFormat("yyyy-mm-dd  hh:mm:ss");
		 Date date=new Date(System.currentTimeMillis());
		 String time = format.format(date);
		 return time;
	 }
}
