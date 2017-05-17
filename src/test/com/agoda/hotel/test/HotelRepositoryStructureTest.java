package com.agoda.hotel.test;

import com.agoda.hotel.data.HotelRepositoryUtil;
import com.agoda.hotel.factory.AgodaRepoFactory;

/**
 * Run this to see how repository and index are structured
 * @author SDVOTHI
 *
 */
public class HotelRepositoryStructureTest {
	
	/**
	 * Test Adding hotel to repository
	 * Try to add 25 hotels to repository
	 */
	public void tryToAddHotel(){
		System.out.println("----- TEST ADDING ----- ");
		for (int i=0; i<25;++i){
			HotelRepositoryUtil.addNewHotel();
		}
		System.out.println("----- END TEST ADDING ----- ");
	}
	
	public static void main(String[] agrs){
		HotelRepositoryStructureTest hotelRepositoryTest = new HotelRepositoryStructureTest();
		
		//1. run add
		hotelRepositoryTest.tryToAddHotel();
		
		//print out repo state and index for checking
		System.out.println(AgodaRepoFactory.getHotelRepositoryInstance());
	}

}
