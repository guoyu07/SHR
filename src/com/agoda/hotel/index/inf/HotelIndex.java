package com.agoda.hotel.index.inf;

import java.util.Set;

import com.agoda.hotel.entity.Hotel;

public interface HotelIndex {
	
	/**
	 * Create index on new object
	 * @param newHotel
	 */
	public void addIndex(Hotel newHotel);
	
	/**
	 * Update existing index
	 * @param newHotel
	 */
	public void updateIndex(Hotel oldHotel,Hotel newHotel);
	
	/**
	 * Remove index of a hotel
	 * @param hotel
	 */
	public void removeIndex(Hotel hotel);
	
	/**
	 * Search id list of matching hotel
	 * @param hotel
	 * @return
	 */
	public Set<String> searchByHotelInfo(Hotel hotel);

}
