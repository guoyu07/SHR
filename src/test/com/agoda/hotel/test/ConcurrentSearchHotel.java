package com.agoda.hotel.test;

import java.util.List;

import com.agoda.hotel.data.HotelRepositoryUtil;
import com.agoda.hotel.data.SampleDataUtil;
import com.agoda.hotel.entity.Hotel;
import com.agoda.hotel.factory.AgodaRepoFactory;
import com.agoda.hotel.repository.thread.AbstractConcurrentTest;

/**
 * Run this class for searching hotel with 1000 threads
 * 
 * @author SDVOTHI
 *
 */
public class ConcurrentSearchHotel extends AbstractConcurrentTest {
	public ConcurrentSearchHotel() {
	}
	
	public ConcurrentSearchHotel(int i) {
		super(i);
	}

	public static void main(String[] agrs) {
		// 1. Setup hotel repository with 4000 hotels
		ConcurrentAddHotel addNewHotel = new ConcurrentAddHotel(4000);
		addNewHotel.createThreads();
		addNewHotel.onComplete(new Runnable() {
			@Override
			public void run() {
				// print out current repository
				HotelRepositoryUtil.printOutRepository();

				ConcurrentSearchHotel testUpdateHotel = new ConcurrentSearchHotel();
				testUpdateHotel.createThreads();

			}
		});
	}

	@Override
	public Runnable getThreadRunnable() {
		return new Runnable() {
			@Override
			public void run() {
				String city = SampleDataUtil.getNextSampleAddress().getCity();
				String name = SampleDataUtil.getNextSampleName();
				int rank = SampleDataUtil.generateRandomRanking();
				List<Hotel> hotels = AgodaRepoFactory.getHotelRepositoryInstance().searchByMultiCondition(city, rank, name);
				
				synchronized (hotels) {
					if (hotels != null && !hotels.isEmpty()){
						System.out.println("FOUND "+name+" - "+ city+" - "+rank);
						HotelRepositoryUtil.printOutHotelList(hotels);
					}else{
						System.out.println("NOT FOUND "+name+" - "+ city+" - "+rank);
					}
				}
			}
		};
	}

}
