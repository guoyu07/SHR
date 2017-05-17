package com.agoda.hotel.repository.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import com.agoda.hotel.factory.AgodaRepoFactory;

public abstract class AbstractConcurrentTest {
	int maxThread;
	
	public ExecutorService executor = Executors.newFixedThreadPool(5);
	
	public AbstractConcurrentTest(){
		maxThread = 1000;
	}
	
	public AbstractConcurrentTest(int maxThread){
		this.maxThread = maxThread;
	}
	
	public void createThreads(){
		System.out.println("------------START CREATING THREAD ------------------");
		
		IntStream.range(0,getMaxThread())
		    .forEach(i -> {
			        executor.submit(getThreadRunnable());
		    });
		executor.shutdown();
	}
	
	/**
	 * 	Action which will be executed when all threads completed
	 * @Default: print hotel repository
	 */
	public void onComplete(Runnable runable){
		//print out current database
		new Thread(new Runnable() {
			        public void run() {
			            while (!executor.isTerminated()) {
			                try {
			                	executor.awaitTermination(60, TimeUnit.SECONDS);
			                } catch (InterruptedException e) {
			                	
			                }
			            }
			            System.out.println("---- END ALL THREADs ---");
			            
			            if (runable != null){
			            	runable.run();
			            }else{
			            	new Runnable() {
								@Override
								public void run() {
									 System.out.println(AgodaRepoFactory.getHotelRepositoryInstance().getRepositoryState());
								}
							}.run();
			            }
			        }
		}).start();
	}
	
	public abstract Runnable getThreadRunnable();
	
	public int getMaxThread(){
		return maxThread;
	};
	
}
