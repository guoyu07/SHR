package com.agoda.hotel.utils;

import java.util.Set;

public class CollectionUtil {
	public static String getStringContent(Set<String> collections){
		String result="";
		if (collections != null && !collections.isEmpty()){
			for (String s: collections){
				result+=s+" ";
			}
		}
		return result;
	}
}
