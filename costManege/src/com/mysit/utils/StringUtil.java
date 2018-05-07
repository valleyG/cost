package com.mysit.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	/**
	 *判断一个对象是否为空
	 *	
	 * @param obj
	 * @return 当对象不为null也不为“”，同时也不为0时，返回true，否则放回false
	 */
	public static boolean isNotNull(Object obj){
		if(obj == null)
			return false;
		if(obj instanceof String){
			String str=String.valueOf(obj);
			if(str.equals(""))
				return false;
		}
		if(obj instanceof Integer){
			Integer itg=(int)obj;
			if(itg==0)
				return false;
		}
		return true;
	}

	/**
	 * 将时间转化为string类型
	 * yyyy-MM-dd HH:mm:ss格式
	 * @return
	 */
	public static String dateToString(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
}
