package com.agoda.hotel.index.inf;

import java.util.Set;

import com.agoda.hotel.entity.Hotel;

public interface SimpleKeyIndex<T,E> {

	/**
	 * Add new index
	 * @param indexKey
	 * @param hotel
	 */
	public void addIndex(T indexKey, E hotel);
	
	/**
	 * Add new index
	 * @param indexKey
	 * @param hotel
	 */
	public void addIndex(T[] indexKeys, E hotel);
	
	/**
	 * Remove index
	 * @param indexKey
	 * @param hotel
	 */
	public void removeIndex(T indexKey, E hotel);
	
	/**
	 * Remove index
	 * @param indexKey
	 * @param hotel
	 */
	public void removeIndex(T[] indexKeys, E hotel);
	
	
	/**
	 * Update index
	 * @param indexKey
	 * @param hotel
	 */
	public void updateIndex(T oldKey, T newKey, E newHotel);
	
	/**
	 * Update index
	 * @param indexKey
	 * @param hotel
	 */
	public void updateIndex(T[] oldKey, T[] newKey, E newHotel);
	
	/**
	 * Get values by key
	 * @param key
	 * @return
	 */
	public Set<E> searchByKey(T key);
	
	/**
	 * Get values by keys
	 * @param key
	 * @return
	 */
	public Set<E> searchByKey(T[] keys);

}
