package com.nvelazquez.ExamenAlmundo;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.nvelazquez.dispatcher.Dispatcher;
import com.nvelazquez.model.Call;
import com.nvelazquez.model.Director;
import com.nvelazquez.model.Operator;
import com.nvelazquez.model.Supervisor;

public class DispatcherTest {

	Random rand = new Random();
	
    @Test
    public void tenCallsFiveEmployeesTest(){
    	
    	Dispatcher dispatcher = new Dispatcher();

    	dispatcher.addObserver(new Operator());
    	dispatcher.addObserver(new Operator());
    	dispatcher.addObserver(new Operator());
    	dispatcher.addObserver(new Supervisor());
    	dispatcher.addObserver(new Director());

    	for(int i = 0; i< 10; i++){
    		dispatcher.dispatchCall(new Call(getRandomNumber()));
    	}
    	
    	dispatcher.shutdownExecutor();
    	
    	waitForDispatcherTofinish(dispatcher);

        assertTrue( dispatcher.getExecutorService().isTerminated() );
    }
    

    @Test
    public void twentyCallsElevenEmployeesTest(){
    	
    	Dispatcher dispatcher = new Dispatcher();
    	
    	dispatcher.addObserver(new Operator());
    	dispatcher.addObserver(new Operator());
    	dispatcher.addObserver(new Operator());
    	dispatcher.addObserver(new Operator());
    	dispatcher.addObserver(new Operator());
    	dispatcher.addObserver(new Operator());
    	dispatcher.addObserver(new Supervisor());
    	dispatcher.addObserver(new Supervisor());
    	dispatcher.addObserver(new Supervisor());
    	dispatcher.addObserver(new Director());
    	dispatcher.addObserver(new Director());
    	
    	for(int i = 0; i<=20; i++){
    		dispatcher.dispatchCall(new Call(getRandomNumber()));
    	}
    	
    	dispatcher.shutdownExecutor();
    	
    	waitForDispatcherTofinish(dispatcher);
    	
        assertTrue( dispatcher.getExecutorService().isTerminated() );
    }
    
    @Test
    public void twelveConcurrentCallsTest(){
    	
    	ExecutorService executorService = Executors.newFixedThreadPool(10);
    	
    	Dispatcher dispatcher = new Dispatcher();
    	
    	dispatcher.addObserver(new Operator());
    	dispatcher.addObserver(new Operator());
    	dispatcher.addObserver(new Operator());
    	dispatcher.addObserver(new Supervisor());
    	dispatcher.addObserver(new Director());

    	for(int i = 0; i<12; i++){
        	Runnable testTask = new Runnable() {
    			public void run() {
    		    	dispatcher.dispatchCall(new Call(getRandomNumber()));
    			};
    		};
    		executorService.submit(testTask);
    	}
    	
    	waitForDispatcherTofinish(dispatcher);
    	
        assertTrue( dispatcher.getExecutorService().isTerminated() );
    }
    
	private int getRandomNumber(){
		return (rand.nextInt(6)+5);
	}
	
    private void waitForDispatcherTofinish(Dispatcher dispatcher){
    	while(!dispatcher.getExecutorService().isTerminated()){
	    	try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }
}
