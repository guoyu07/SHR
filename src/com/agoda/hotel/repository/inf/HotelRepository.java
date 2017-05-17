package com.agoda.hotel.repository.inf;

import java.util.List;
import java.util.Set;

import com.agoda.hotel.entity.Hotel;

public interface HotelRepository {
	
	/**
	 * Add new Hotel object, return created Hotel
	 * @param hotel
	 * @return
	 */
	public Hotel addHotel(Hotel hotel);
	
	/**
	 * Update hotel, using hotel.id to retreive hotel and perform update
	 * Update all non-emty value in hotel param to corresponding object in repository
	 * @param id
	 * @param hotel
	 * @return
	 */
	public boolean updateHotel(Hotel hotel);
	
	/**
	 * Remove hotel out of repository by id
	 * return true if successful, otherwise return false
	 * @param id
	 * @return
	 */
	public boolean removeHotel(String id);
	
	/**
	 * Generate id for new hotel
	 * @param id
	 * @return
	 */
	public String generateHotelId();
	
	/**
	 * Return list of hotel by city name
	 * @param city
	 * @return
	 */
	public List<Hotel> searchByCity(String city);
	
	/**
	 * Return list of hotel by rank
	 * @param rank
	 * @return
	 */
	public List<Hotel> searchByRank(int rank);
	
	/**
	 * Perform free text search on name and return list of matching hotels
	 * @param searchTerm
	 * @return
	 */
	public List<Hotel> searchByFreeTextOnName(String searchTerm);
	
	/**
	 * Perform search with multiple condition
	 * @param city
	 * @param rank
	 * @param searchTermForName
	 * @return
	 */
	public List<Hotel> searchByMultiCondition(String city, Integer rank, String searchTermForName);
	
	/**
	 * Find hotel by id
	 * @return
	 */
	public Hotel findById(String id);
	
	/**
	 * Find hotels by many ids
	 * @param ids
	 * @return
	 */
	public List<Hotel> findByIds(Set<String> ids);
	
	/**
	 * Return string representation of repository
	 * @return
	 */
	public String getRepositoryState();
	
	/**
	 * Return string representation of index storage
	 * @return
	 */
	public String getIndexState();
	
	/**
	 * Count total hotels
	 * @return
	 */
	public int getSize();

}
