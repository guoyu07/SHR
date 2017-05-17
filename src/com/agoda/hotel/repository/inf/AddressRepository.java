package com.agoda.hotel.repository.inf;

import java.util.List;

/**
 * @author SDVOTHI
 *
 */
public interface AddressRepository {
	
	/**
	 * Add new country to repository
	 * Return true if successful, otherwise return false
	 * @param country
	 * @return
	 */
	public boolean addNewCountry(String country);
	
	/**
	 * Add new city within a country to repository
	 * Return true if successful, otherwise return false
	 * @param country
	 * @return
	 */
	public boolean addNewCity(String country,String city);
	
	/**
	 * Return list of all supported countries
	 * @return
	 */
	public List<String> getCountryList();
	
	/**
	 * Return list of all city within a country
	 * @param country
	 * @return
	 */
	public List<String> getCityByCountry(String country);

}
