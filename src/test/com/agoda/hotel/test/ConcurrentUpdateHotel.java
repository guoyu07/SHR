package com.agoda.hotel.test;

import com.agoda.hotel.data.HotelRepositoryUtil;
import com.agoda.hotel.data.SampleDataUtil;
import com.agoda.hotel.entity.Hotel;
import com.agoda.hotel.factory.AgodaRepoFactory;
import com.agoda.hotel.repository.thread.AbstractConcurrentTest;

/**
 * Run this class for testing updating hotel randomly from 1000 threads
 * @author SDVOTHI
 *
 */
public class ConcurrentUpdateHotel extends AbstractConcurrentTest {
	public ConcurrentUpdateHotel() {
	}
	
	public ConcurrentUpdateHotel(int i) {
		super(i);
	}

	public static void main(String[] agrs){
		//1. Setup hotel repository with 4000 hotels
		ConcurrentAddHotel addNewHotel = new ConcurrentAddHotel(4000);
		addNewHotel.createThreads();
		addNewHotel.onComplete(new Runnable() {
			@Override
			public void run() {
				//print out current repository
				HotelRepositoryUtil.printOutRepository();
				
				//after repository is set up, try to update same hotel from 1000 threads
				ConcurrentUpdateHotel testUpdateHotel = new ConcurrentUpdateHotel();
				testUpdateHotel.createThreads();
				
				//print out repository again after finish updating
				testUpdateHotel.onComplete(null);
			}
		});
		
	}

	@Override
	public Runnable getThreadRunnable() {
		return new Runnable() {
			@Override
			public void run() {
				Hotel hotel = SampleDataUtil.getRandomHotel(null);
				Hotel oldHotel = hotel.clone();
				hotel.setName(SampleDataUtil.getNextSampleName());
				hotel.setStarRanking(SampleDataUtil.generateRandomRanking());
				hotel.setAddress(SampleDataUtil.getNextSampleAddress());
				boolean result = AgodaRepoFactory.getHotelRepositoryInstance().updateHotel(hotel);
				System.out.println("Old Hotel : "+oldHotel.toString() + "\n"+(result ? "Successful " : "Failed " )+"update to : "+hotel.toString());
			}
		};
	}

}
