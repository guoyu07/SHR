package com.agoda.hotel.test;

import java.util.List;

import com.agoda.hotel.data.HotelRepositoryUtil;
import com.agoda.hotel.data.SampleDataUtil;
import com.agoda.hotel.entity.Hotel;
import com.agoda.hotel.factory.AgodaRepoFactory;

public class HotelRepositoryTest {
	
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
		//print out current repository state
		HotelRepositoryUtil.printOutRepository();
	}
	
	/**
	 * Test Updating hotel to repository
	 * update first hotel name from One World Hotel to Whole World Hotel, from city Hanoi to Da Nang, ranking to 5
	 */
	public void tryToUpdateHotel(){
		System.out.println("----- TEST UPDATING ----- ");
		Hotel hotel = AgodaRepoFactory.getHotelRepositoryInstance().findById(SampleDataUtil.validHotelIds.get(0));
		System.out.println("Current hotel info "+hotel);
		hotel.setName("Whole World Hotel");
		hotel.getAddress().setCity("Da Nang");
		hotel.setStarRanking(5);
		System.out.println("Update to "+hotel);
		AgodaRepoFactory.getHotelRepositoryInstance().updateHotel(hotel);
		System.out.println("----- END UPDATING ----- ");
		//print out current repository state
		HotelRepositoryUtil.printOutRepository();
	}
	
	/**
	 * Try removing second hotel out of repository
	 */
	public void tryToRemoveHotel(){
		System.out.println("----- TEST REMOVING ----- ");
		Hotel hotel = AgodaRepoFactory.getHotelRepositoryInstance().findById(SampleDataUtil.validHotelIds.get(1));
		System.out.println("Current hotel info "+hotel);
		AgodaRepoFactory.getHotelRepositoryInstance().removeHotel(hotel.getId());
		System.out.println("----- END REMOVING ----- ");
		HotelRepositoryUtil.printOutRepository();
	}
	
	private void searchByCity(String city){
		List<Hotel> hotels = AgodaRepoFactory.getHotelRepositoryInstance().searchByCity(city);
		if (hotels != null && !hotels.isEmpty()){
			System.out.println("***FOUND in city "+city+ " : ");
			HotelRepositoryUtil.printOutHotelList(hotels);
		}else{
			System.out.println("***NOT FOUND in "+city);
		}
	}
	
	public void testSearchByCity(){
		System.out.println("----- TEST SEARCH BY CITY ----- ");
		//1. search by city only
		searchByCity("Ha Noi");
		searchByCity("Da Nang");
		searchByCity("London");
		searchByCity("Russia");
		System.out.println("----- END SEARCH BY CITY ----- ");
		System.out.println();
	}
	
	private void searchByRanking(int rank){
		List<Hotel> hotels = AgodaRepoFactory.getHotelRepositoryInstance().searchByRank(rank);
		if (hotels != null && !hotels.isEmpty()){
			System.out.println("***FOUND hotels with "+rank+ " star(s) : ");
			HotelRepositoryUtil.printOutHotelList(hotels);
		}else{
			System.out.println("***NOT FOUND with "+rank+" star(s)");
		}
	}
	
	public void testSearchByRanking(){
		System.out.println("----- TEST SEARCH BY RANKING ----- ");
		searchByRanking(1);
		searchByRanking(5);
		System.out.println("----- END SEARCH BY RANKING ----- ");
		System.out.println();
	}
	
	private void searchByName(String name){
		List<Hotel> hotels = AgodaRepoFactory.getHotelRepositoryInstance().searchByFreeTextOnName(name);
		if (hotels != null && !hotels.isEmpty()){
			System.out.println("***FOUND hotels has "+name+ " name : ");
			HotelRepositoryUtil.printOutHotelList(hotels);
		}else{
			System.out.println("***NOT FOUND with "+name);
		}
	}
	
	public void testSearchByName(){
		System.out.println("----- TEST SEARCH BY HOTEL NAME ----- ");
		searchByName("Las Circus");
		searchByName("Hilton");
		searchByName("Yalta2");
		System.out.println("----- END SEARCH BY HOTEL NAME ----- ");
		System.out.println();
	}
	
	private void testSearchComplexCondition(String city,Integer rank, String name){
		List<Hotel> hotels = AgodaRepoFactory.getHotelRepositoryInstance().searchByMultiCondition(city, rank, name);
		if (hotels != null && !hotels.isEmpty()){
			System.out.println("***FOUND hotels has "+name+ " - "+city+" - "+rank);
			HotelRepositoryUtil.printOutHotelList(hotels);
		}else{
			System.out.println("***NOT FOUND with "+name+ " - "+city+" - "+rank);
		}
	}
	
	public void testSearchComplexCondition(){
		System.out.println("----- TEST SEARCH BY COMPLEX CONDITION ----- ");
		testSearchComplexCondition("Da Nang",5,"World");
		testSearchComplexCondition(null,5,"World");
		testSearchComplexCondition("Da Nang",5,null);
		testSearchComplexCondition("Ha Noi",null,"Hotel");
		testSearchComplexCondition("Ho Chi Minh",null,"Hilton");
		
		System.out.println("----- END SEARCH BY COMPLEX CONDITION ----- ");
		System.out.println();
	}
	
	public static void main(String[] agrs){
		HotelRepositoryTest hotelRepositoryTest = new HotelRepositoryTest();
		
		//1. run add
		hotelRepositoryTest.tryToAddHotel();
		
		//2. run update
		hotelRepositoryTest.tryToUpdateHotel();
		
		//3. run remove
		hotelRepositoryTest.tryToRemoveHotel();
		
		//4. run search
		hotelRepositoryTest.testSearchByCity();
		hotelRepositoryTest.testSearchByRanking();
		hotelRepositoryTest.testSearchByName();
		hotelRepositoryTest.testSearchComplexCondition();
		
		//print out index for checking
//		System.out.println(AgodaRepoFactory.getHotelRepositoryInstance().getIndexState());
	}

}
