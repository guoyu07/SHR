package com.agoda.hotel.index;

import java.util.Set;

import com.agoda.hotel.entity.Hotel;
import com.agoda.hotel.factory.AgodaRepoFactory;
import com.agoda.hotel.index.inf.HotelIndex;
import com.agoda.hotel.index.inf.SimpleKeyIndex;
import com.agoda.hotel.utils.StringUtil;

public class HotelIndexerImpl implements HotelIndex{
	// indexing on city
	private SimpleKeyIndex<String,String> hotelIndexByCity;
	// indexing on rank
	private SimpleKeyIndex<Integer,String> hotelIndexByRank;
	// indexing on name's words
	private SimpleKeyIndex<String,String> hotelIndexByName; //inverted index
	
	@SuppressWarnings("unchecked")
	public HotelIndexerImpl() {
		hotelIndexByCity = AgodaRepoFactory.<String>createSimpleIndex();
		hotelIndexByRank = AgodaRepoFactory.<Integer>createSimpleIndex();
		hotelIndexByName = AgodaRepoFactory.<String>createSimpleIndex();
	}
	
	@Override
	public void addIndex(Hotel newHotel) {
		hotelIndexByCity.addIndex(newHotel.getAddress().getCity(), newHotel.getId());
		hotelIndexByRank.addIndex(newHotel.getStarRanking(), newHotel.getId());
		hotelIndexByName.addIndex(StringUtil.getWords(newHotel.getName()), newHotel.getId());
	}

	@Override
	public void updateIndex(Hotel oldHotel, Hotel newHotel) {
		hotelIndexByCity.updateIndex(oldHotel.getAddress().getCity(), newHotel.getAddress().getCity(), newHotel.getId());
		hotelIndexByRank.updateIndex(oldHotel.getStarRanking(), newHotel.getStarRanking(), newHotel.getId());
		
		String[] removeKeys = StringUtil.getHalfOuterSec(newHotel.getName(), oldHotel.getName());
		String[] addKeys = StringUtil.getHalfOuterSec(oldHotel.getName(), newHotel.getName());
		hotelIndexByName.updateIndex(removeKeys,addKeys, newHotel.getId());
	}

	@Override
	public void removeIndex(Hotel hotel) {
		hotelIndexByCity.removeIndex(hotel.getAddress().getCity(), hotel.getId());
		hotelIndexByRank.removeIndex(hotel.getStarRanking(), hotel.getId());
		hotelIndexByName.removeIndex(StringUtil.getWords(hotel.getName()), hotel.getId());
	}

	@Override
	public Set<String> searchByHotelInfo(Hotel hotel) {
		Set<String> hotels = null;
		
		if (hotel.getAddress() != null && hotel.getAddress().getCity() != null){
			hotels = intersect(hotels, hotelIndexByCity.searchByKey(hotel.getAddress().getCity()));
		}
		
		if (hotel.getStarRanking() != null){
			hotels = intersect(hotels, hotelIndexByRank.searchByKey(hotel.getStarRanking()));
		}
		
		if (hotel.getName() != null){
			hotels = intersect(hotels, hotelIndexByName.searchByKey(StringUtil.getWords(hotel.getName())));
		}
		
		return hotels;
	}
	
	/**
	 * Get intersect between 2 sets
	 * @param set1
	 * @param set2
	 * @return
	 */
	private Set<String> intersect(Set<String> set1, Set<String> set2){
		if (set1 == null){
			return set2;
		}
		set1.retainAll(set2);
		
		return set1;
	}
	
	/**
	 * Used for print out result
	 */
	public String toString(){
		return  "CITY : \n"+hotelIndexByCity.toString()+"\n "+
				"RANK : \n"+hotelIndexByRank.toString() +"\n "+ 
				"NAMES : \n"+ hotelIndexByName.toString();
	}
}
