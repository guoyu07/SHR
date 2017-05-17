package com.agoda.hotel.test;

import com.agoda.hotel.data.SampleDataUtil;
import com.agoda.hotel.entity.Hotel;
import com.agoda.hotel.factory.AgodaRepoFactory;
import com.agoda.hotel.repository.inf.HotelRepository;
import com.agoda.hotel.repository.thread.AbstractConcurrentTest;

/**
 * Run this class for testing adding 10000 new hotels concurrently
 * @author SDVOTHI
 *
 */
public class ConcurrentAddHotel extends AbstractConcurrentTest{
	
	public ConcurrentAddHotel() {
	}
	
	public ConcurrentAddHotel(int i) {
		super(i);
	}

	public static void main(String[] agrs){
		ConcurrentAddHotel repositoryUnitTest1 = new ConcurrentAddHotel(10000);
		repositoryUnitTest1.createThreads();
		repositoryUnitTest1.onComplete(null);//print out the repository and index on complete
	}

	@Override
	public Runnable getThreadRunnable() {
		return new Runnable() {
			@Override
			public void run() {
				HotelRepository hotelRepository = AgodaRepoFactory.getHotelRepositoryInstance();
				Hotel hotel = hotelRepository.addHotel(SampleDataUtil.generateRandomHotel());
				System.out.println("New hotel was added :"+hotel.toString());
				//store created hotel id for further tests
				SampleDataUtil.validHotelIds.add(hotel.getId());
			}
		};
	}

}
