package com.agoda.hotel.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.agoda.hotel.repository.inf.AddressRepository;

/**
 * Managed country/cities which are supported
 * Staff must select values from this repository for Address when perform Create/Update hotel
 * @author SDVOTHI
 *
 */
public class AddressRepositoryImpl implements AddressRepository{
	//country -> cities mapping
	private Map<String,List<String>> countries;
	
	public AddressRepositoryImpl(){
		countries = new ConcurrentHashMap<>();
	}

	@Override
	public List<String> getCountryList() {
		return new ArrayList<String>(countries.keySet());
	}

	@Override
	public List<String> getCityByCountry(String country) {
		if (countries.containsKey(country)){
			return countries.get(country);
		}
		return new ArrayList<>();
	}

	@Override
	public boolean addNewCountry(String country) {
		if (!countries.containsKey(country)){
			countries.put(country, new CopyOnWriteArrayList<String>());
			return true; 
		}
		return false;
	}

	@Override
	public boolean addNewCity(String country, String city) {
		List<String> cities;
		if (countries.containsKey(country)){
			cities = countries.get(country);
		}else{
			cities = new CopyOnWriteArrayList<>();
			countries.put(country, cities);
		}
		cities.add(city);
		
		return true;		
	}
	
	
	
}
