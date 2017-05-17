package com.agoda.hotel.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.agoda.hotel.entity.Address;
import com.agoda.hotel.entity.Hotel;
import com.agoda.hotel.factory.AgodaRepoFactory;
import com.agoda.hotel.index.inf.HotelIndex;
import com.agoda.hotel.repository.inf.HotelRepository;
import com.agoda.hotel.utils.StringUtil;
import com.agoda.hotel.utils.ValidationUtil;

public class HotelRepositoryImpl implements HotelRepository{
	private AtomicInteger currentIncremental;
	
	private volatile Map<String,Hotel> hotelStorage;
	private HotelIndex hotelIndexer;
	
	public HotelRepositoryImpl(){
		hotelStorage = new ConcurrentHashMap<>();
		hotelIndexer = AgodaRepoFactory.getHotelIndexerInstance();
		currentIncremental  = new AtomicInteger(0);
	}

	@Override
	public Hotel addHotel(Hotel hotel) {
		if (ValidationUtil.isValidHotel(hotel)){
			hotel.setId(generateHotelId());
			hotel.setCreatedDate(Calendar.getInstance().getTime());
			hotelStorage.put(hotel.getId(), hotel);
			hotelIndexer.addIndex(hotel);
			return hotel;
		}
		return null;
	}
	
	@Override
	public boolean updateHotel(Hotel hotel) {
		if (ValidationUtil.isValidHotel(hotel)){
			Hotel currentHotel = hotelStorage.get(hotel.getId());
			Hotel storedHotel = currentHotel.clone();
			currentHotel.updateHotel(hotel);
			hotelIndexer.updateIndex(storedHotel, currentHotel);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeHotel(String id) {
		if (hotelStorage.containsKey(id)){
			Hotel oldHotel = hotelStorage.get(id);
			hotelStorage.remove(id);
			hotelIndexer.removeIndex(oldHotel);
			return true;
		}
		return false;
	}

	@Override
	public List<Hotel> searchByCity(String city) {
		return searchByMultiCondition(city, null, null);
	}

	@Override
	public List<Hotel> searchByRank(int rank) {
		return searchByMultiCondition(null, rank, null);
	}

	@Override
	public List<Hotel> searchByFreeTextOnName(String searchTermForName) {
		return searchByMultiCondition(null, null, searchTermForName);
	}

	@Override
	public List<Hotel> searchByMultiCondition(String city, Integer rank, String searchTermForName) {
		Hotel hotel = new Hotel(searchTermForName);
		hotel.setStarRanking(rank);
		Address address = new Address();
		if (city != null){
			hotel.setAddress(address);
			address.setCity(city);
		}
		Set<String> matchingIds = hotelIndexer.searchByHotelInfo(hotel);
		return findByIds(matchingIds);
	}
	
	@Override
	public String generateHotelId(){
		int currentValue = currentIncremental.incrementAndGet();
		String prefix = DATEFORMATTER.format(Calendar.getInstance().getTime());     
		return prefix+StringUtil.formatString(currentValue+"", INCREMENTAL_LENGTH);
	}
	
	@Override
	public Hotel findById(String id) {
		if (hotelStorage.containsKey(id)){
			Hotel hotel = hotelStorage.get(id); 
			return hotel.clone();
		}
		return null;
	}
	
	@Override
	public List<Hotel> findByIds(Set<String> ids) {
		List<Hotel> result = new ArrayList<>();
		if (ids != null && !ids.isEmpty()){
			for (String id : ids){
				Hotel found = findById(id);
				if (found != null){
					result.add(found);
				}
			}
		}
		return result;
	}
	
	/**
	 * Used to print out onject content
	 */
	public String toString(){
		if (hotelStorage != null){
			return getRepositoryState()+getIndexState();
		}
		return "";
	}
	
	static final SimpleDateFormat DATEFORMATTER = new SimpleDateFormat("yyyyMMdd");
	static final int INCREMENTAL_LENGTH = 8;

	@Override
	public String getRepositoryState() {
		String result ="HOTELS ("+getSize()+"): \n";
		if (hotelStorage != null){
			for (Entry<String,Hotel> item : hotelStorage.entrySet()){
				result+=item.getValue().toString()+"\n ";
			}
		}
		return result+"TOTAL HOTELS : "+getSize()+"\n";
	}

	@Override
	public String getIndexState() {
		return "\n INDEXING: \n"+hotelIndexer.toString();
	}

	@Override
	public int getSize() {
		if (hotelStorage != null){
			return hotelStorage.size();
		}
		return 0;
	}

}
