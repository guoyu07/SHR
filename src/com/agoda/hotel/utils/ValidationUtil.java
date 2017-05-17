package com.agoda.hotel.utils;

import com.agoda.hotel.entity.Address;
import com.agoda.hotel.entity.Hotel;

public class ValidationUtil {
	
	/**
	 * Validate if a hotel object is not null and contains name, street, city, country
	 * @param hotel
	 * @return
	 */
	public static boolean isValidHotel(Hotel hotel){
		return hotel != null 
				&& StringUtil.isNonEmptyString(hotel.getName())
				&& isValidAddress(hotel.getAddress());
	}
	
	/**
	 * Validate if an address is valid
	 * @param address
	 * @return
	 */
	public static boolean isValidAddress(Address address){
		return address != null 
				&& StringUtil.isNonEmptyString(address.getCity()) 
				&& StringUtil.isNonEmptyString(address.getStreet()) 
				&& StringUtil.isNonEmptyString(address.getCountry());
	}
	
	
	
}
