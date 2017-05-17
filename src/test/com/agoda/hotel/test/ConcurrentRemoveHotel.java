package com.agoda.hotel.test;

import com.agoda.hotel.data.HotelRepositoryUtil;
import com.agoda.hotel.data.SampleDataUtil;
import com.agoda.hotel.factory.AgodaRepoFactory;
import com.agoda.hotel.repository.thread.AbstractConcurrentTest;

/**
 * Run this class for testing removing hotel randomly from 1000 threads
 * @author SDVOTHI
 *
 */
public class ConcurrentRemoveHotel extends AbstractConcurrentTest {
	public ConcurrentRemoveHotel() {
	}
	
	public ConcurrentRemoveHotel(int i) {
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
				ConcurrentRemoveHotel removeHotel = new ConcurrentRemoveHotel();
				removeHotel.createThreads();
				
				//print out repository again after finish updating
				removeHotel.onComplete(null);
			}
		});
		
	}

	@Override
	public Runnable getThreadRunnable() {
		return new Runnable() {
			
			@Override
			public void run() {
				String id = SampleDataUtil.getRandomValidId();
				boolean removed = AgodaRepoFactory.getHotelRepositoryInstance().removeHotel(id);
				if (removed){
					System.out.println("Removed hotel "+id);
				}else{
					System.out.println("Removed hotel "+id+" failed! Maybe id doesnt exist!");
				}
			}
		};
	}

}
