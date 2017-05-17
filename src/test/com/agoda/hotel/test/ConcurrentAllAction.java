package com.agoda.hotel.test;

/**
 * Run this to test all 4 actions concurrently
 * @author SDVOTHI
 *
 */
public class ConcurrentAllAction{
	public static void main(String[] agrs) throws InterruptedException{
		ConcurrentAddHotel addHotel = new ConcurrentAddHotel(10000);
		addHotel.createThreads();
		
		Thread.sleep(200);
		
		ConcurrentUpdateHotel updateHotel = new ConcurrentUpdateHotel(5000);
		updateHotel.createThreads();
		
		ConcurrentRemoveHotel removeHotel = new ConcurrentRemoveHotel(5000);
		removeHotel.createThreads();
		
		ConcurrentSearchHotel searchHotel = new ConcurrentSearchHotel(2000);
		searchHotel.createThreads();
	}
}
