package com.agoda.hotel.factory;

import com.agoda.hotel.index.HotelIndexerImpl;
import com.agoda.hotel.index.SimpleKeyIndexImpl;
import com.agoda.hotel.index.inf.HotelIndex;
import com.agoda.hotel.index.inf.SimpleKeyIndex;
import com.agoda.hotel.repository.AddressRepositoryImpl;
import com.agoda.hotel.repository.HotelRepositoryImpl;
import com.agoda.hotel.repository.inf.AddressRepository;
import com.agoda.hotel.repository.inf.HotelRepository;

public class AgodaRepoFactory {
	
	static AddressRepository currentAddressRepository = new AddressRepositoryImpl();
	static HotelIndex hotelIndexer = new HotelIndexerImpl();
	static HotelRepository currentHotelRepository = new HotelRepositoryImpl();
	
	public static AddressRepository getAddressInstance(){
		return currentAddressRepository;
	}
	
	public static HotelIndex getHotelIndexerInstance(){
		return hotelIndexer;
	}
	
	public static HotelRepository getHotelRepositoryInstance(){
		return currentHotelRepository;
	}
	
	@SuppressWarnings("rawtypes")
	public static <T> SimpleKeyIndex createSimpleIndex(){
		return new SimpleKeyIndexImpl<T,String>();
	}

}
