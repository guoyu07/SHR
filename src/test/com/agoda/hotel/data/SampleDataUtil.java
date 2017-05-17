package com.agoda.hotel.data;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.agoda.hotel.entity.Address;
import com.agoda.hotel.entity.Hotel;
import com.agoda.hotel.factory.AgodaRepoFactory;
import com.agoda.hotel.repository.inf.AddressRepository;

public class SampleDataUtil{
	
	static AtomicInteger CURRENT_SAMPLE_CITY = new AtomicInteger(0);
	static AtomicInteger CURRENT_SAMPLE_HOTEL_NAME = new AtomicInteger(0);
	
	public static List<String> validHotelIds = new CopyOnWriteArrayList<>();
	
	public static int MAX_NO_OF_HOTEL = 100;
	
	static final String[] COUNTRY_CITY_NAMES = {"Vietnam,Da Nang","Vietnam,Ha Noi","Vietnam,Ho Chi Minh","Malaysia,Kuala Lumpur","Malaysia,Langkawi",
			"Germany,Stugatt","Germany,Berlin","Germany,Munich","Japan,Tokyo","Japan,Kyoto",
			"Singapore,Singapore","England,London","China,Beijing","Korea,Seoul","France,Paris",
			"Italy,Florence","Italy,Venezia","Italy,Rome","Egypt,Cairo","Spain,Bacerlona"};
	
	static final String[] HOTEL_NAMES = {"First World Hotel","One World Hotel","The Palazzo","Sands Cotai Central","Luxor Las Vegas",
			"Wynn Las Vegas","Encore Las Vegas","Circus Circus Las Vegas","Hilton Hawaiian Village","The Venetian Macau",
			"Harrah's Atlantic City","Galaxy Macau","City of Dreams","Hilton New York","Manchester Grand Hyatt Hotel",
			"Hilton Anatole","Chelsea Hotel","Circus Circus Reno","Hilton Chicago","Sheraton Centre Hotel",
			"Westin Bonaventure Hotel","Hilton Waikoloa Beach Resort","Hilton San Diego Bayfront","Caesars Atlantic City","Yalta Hotel Complex",
			"Estrel Hotel","Washington Hilton","Hilton London Metropole","Roosevelt Hotel","Los Angeles Airport Marriott",
			"APA Hotel and Resort","Sheraton Phoenix Downtown","Resorts World","Hilton Worldwide","Hongkong and Shanghai Hotels",
			"Jin Jiang International","Marriott International","Rosewood Hotel Group","Wyndham Worldwide","Caribbean Motel",
			"Holiday Inn","Madonna Inn","Masters Inn","Red Carpet Inn","Star Lite Motel"};
	
	public static final String[] FACILITIES = {"Swimming pool","Breakfirst included","Wifi","Early check in","Laundry","Restaurant"};
	
	public static final int MIN_RANKING = 0;
	public static final int MAX_RANKING = 5;
	
	public static final int MIN_ROOM = 1;
	public static final int MAX_ROOM = 20;
	
	
	public static void addCities(){
		AddressRepository addressRepository = AgodaRepoFactory.getAddressInstance();
		
		for (String cites : COUNTRY_CITY_NAMES){
			String[] countryCity = cites.split(",");
			addressRepository.addNewCity(countryCity[0], countryCity[1]);
		}
	}
	
	public static Address getNextSampleAddress(){
		int currIndex = CURRENT_SAMPLE_CITY.incrementAndGet();
		currIndex = currIndex % COUNTRY_CITY_NAMES.length;
		String[] cites = COUNTRY_CITY_NAMES[currIndex].split(",");
		return new Address(cites[0],cites[1],"Street "+currIndex);
	}
	
	public static String getNextSampleName(){
		int currIndex = CURRENT_SAMPLE_HOTEL_NAME.incrementAndGet();
		currIndex = currIndex % HOTEL_NAMES.length;
		return HOTEL_NAMES[currIndex];
	}
	
	public static int getRandomNumber(int maxValue){
		return getRandomNumber(0,maxValue);
	}
	
	public static int getRandomNumber(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public static Set<String> getSampleFacilities(){
		Set<String> facilities = new LinkedHashSet<>();
		for (int i=0; i<FACILITIES.length;++i){
			facilities.add(FACILITIES[getRandomNumber(FACILITIES.length-1)]);
		}
		return facilities;
	}
	
	public static String getSamplePhoneNo(){
		return "01672398709";
	}
	
	public static int generateRandomRanking(){
		return getRandomNumber(MIN_RANKING,MAX_RANKING);
	}
	
	/**
	 * Load Hotel from repository based on id or random id
	 * @param id
	 * @return
	 */
	public static Hotel getRandomHotel(String id){
		if (id == null){
			id = getRandomValidId();
		}
		
		return AgodaRepoFactory.getHotelRepositoryInstance().findById(id);
	}
	
	public static String getRandomValidId(){
		int index = SampleDataUtil.getRandomNumber(0, SampleDataUtil.validHotelIds.size()-1);
		return SampleDataUtil.validHotelIds.get(index);
	}
	
	public static Hotel generateRandomHotel(){
		Hotel hotel = new Hotel(SampleDataUtil.getNextSampleName());
		Address address = getNextSampleAddress();
		hotel.setAddress(address);
		hotel.setFacilities(getSampleFacilities());
		hotel.setPhoneNumer(getSamplePhoneNo());
		hotel.setNoOfRoom(getRandomNumber(MIN_ROOM, MAX_ROOM));
		hotel.setStarRanking(generateRandomRanking());
		return hotel;
	}
	
}
