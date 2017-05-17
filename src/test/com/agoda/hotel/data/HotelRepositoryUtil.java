package com.agoda.hotel.data;

import java.util.List;

import com.agoda.hotel.entity.Hotel;
import com.agoda.hotel.factory.AgodaRepoFactory;
import com.agoda.hotel.repository.inf.HotelRepository;

public class HotelRepositoryUtil {
	
	/**
	 * Add new hotel from sample data
	 */
	public static void addNewHotel(){
		HotelRepository hotelRepository = AgodaRepoFactory.getHotelRepositoryInstance();
		Hotel hotel = hotelRepository.addHotel(SampleDataUtil.generateRandomHotel());
		System.out.println("New hotel was added :"+hotel.toString());
		//store created hotel id for further tests
		SampleDataUtil.validHotelIds.add(hotel.getId());
	}
	
	public static void printOutRepository(){
		System.out.println("------------------------ HOTEL REPOSITORY STATE----------------------------");
		System.out.println(AgodaRepoFactory.getHotelRepositoryInstance().getRepositoryState());
	}
	
	public static void printOutHotelList(List<Hotel> hotels){
		if (hotels != null){
			for (Hotel hotel: hotels){
				System.out.println(hotel);
			}
		}
	}

}
