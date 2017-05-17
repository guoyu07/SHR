package com.agoda.hotel.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
	/**
	 * If a String is null or empty, return false
	 * Otherwise return true
	 * @param value
	 * @return
	 */
	public static boolean isNonEmptyString(String value){
		if (value == null || value.isEmpty()){
			return false;
		}
		return true;
	}
	
	/**
	 * Add 0 before string to match expectedLength if necessary
	 * @param value
	 * @param expectedLength
	 */
	public static String formatString(String value, int expectedLength){
		while (value.length()< expectedLength){
			value= "0"+value;
		}
		
		return value;
	}
	
	/**
	 * Return all words which exist in s2 but not s1
	 * @param main
	 * @param check
	 * @return
	 */
	public static String[] getHalfOuterSec(String s1, String s2){
		List<String> words = new ArrayList<>();
		if (isNonEmptyString(s2)){
			String[] s2Words = getWords(s2);
			for (String s : s2Words){
				if (!s1.contains(s)){
					words.add(s);
				}
			}
		}
		return words.toArray(new String[0]);
	}
	
	/**
	 * Split string to words if possible
	 * @param s
	 * @return
	 */
	public static String[] getWords(String s){
		if (isNonEmptyString(s)){
			return s.split(" ");
		}
		return new String[0];
	}

}
