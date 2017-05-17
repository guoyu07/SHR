package com.agoda.hotel.index;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import com.agoda.hotel.index.inf.SimpleKeyIndex;

public class SimpleKeyIndexImpl<T,E> implements SimpleKeyIndex<T,E> {

	private volatile Map<T,Set<E>> indexStorage;
	
	public SimpleKeyIndexImpl(){
		indexStorage = new ConcurrentHashMap<>();
	}
	
	@Override
	public void addIndex(T indexKey, E newHotel) {
		Set<E> hotels;
		if (indexStorage.containsKey(indexKey)){
			hotels = indexStorage.get(indexKey);
		}else{
			hotels = new ConcurrentSkipListSet<>();
			indexStorage.put(indexKey, hotels);
		}
		hotels.add(newHotel);
	}
	
	@Override
	public void addIndex(T[] indexKeys, E hotel) {
		for (T t : indexKeys){
			addIndex(t, hotel);
		}
	}

	@Override
	public void removeIndex(T indexKey, E hotel) {
		Set<E> hotels = indexStorage.get(indexKey);
		removeFromListIfFound(hotels,hotel);
	}

	@Override
	public void removeIndex(T[] indexKeys, E hotel) {
		for (T t : indexKeys){
			removeIndex(t, hotel);
		}
	}

	@Override
	public void updateIndex(T oldKey, T newKey, E hotel) {
		if (oldKey != newKey){
			removeIndex(oldKey,hotel);
			addIndex(newKey,hotel);
		}
	}
	
	@Override
	public void updateIndex(T[] oldKey, T[] newKey, E hotel) {
		for (T t : oldKey){
			removeIndex(t, hotel);
		}
		
		for (T t : newKey){
			addIndex(t, hotel);
		}
	}
	
	@Override
	public Set<E> searchByKey(T key) {
		if (key != null && indexStorage.containsKey(key)){
			return new LinkedHashSet<E>(indexStorage.get(key));
		}
		return new LinkedHashSet<E>();
	}

	@Override
	public Set<E> searchByKey(T[] keys) {
		Set<E> hotels = new LinkedHashSet<>();
		for (T t : keys){
			hotels.addAll(searchByKey(t));
		}
		return hotels;
	}
	
	/**
	 * Remove value from list if exist
	 * @param hotels
	 * @param oldHotel
	 */
	private void removeFromListIfFound(Set<E> hotels, E oldHotel){
		E foundHotel = null;
		for (E hotel : hotels){
			if (hotel.equals(oldHotel)){
				foundHotel = hotel;
				break;
			}
		}
		if (foundHotel != null){
			hotels.remove(foundHotel);
		}
	}
	
	/**
	 * Used for print out result
	 */
	public String toString(){
		String result="";
		if (indexStorage != null){
			for (Entry<T, Set<E>> item : indexStorage.entrySet()){
				result+="("+item.getKey()+") - ("+item.getValue().size()+") : ";
				if (item.getValue() != null){
					for (E hotel : item.getValue()){
						result+=hotel.toString()+" ; ";
					}
				}
				result+="\n ";
			}
		}
		
		return result;
	}

}
