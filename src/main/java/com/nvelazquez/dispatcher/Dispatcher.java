package com.nvelazquez.dispatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.nvelazquez.model.AbstractEmployee;
import com.nvelazquez.model.Call;
import com.nvelazquez.model.Observer;
import com.nvelazquez.model.ObserverPrioritiable;
import com.nvelazquez.model.Subject;
import com.nvelazquez.util.PriorityComparator;

public class Dispatcher implements Subject{

	volatile List<ObserverPrioritiable> observers = new ArrayList<>();
	ExecutorService executorService = Executors.newFixedThreadPool(10);
	
	public Dispatcher() {}
	
	public Dispatcher(List<ObserverPrioritiable> observers, List<Call> calls) {
		this.observers = observers;
		Collections.sort(this.observers, new PriorityComparator());
	}
	
	public void dispatchCall(Call call) {
		
		waitForEmployee();

		synchronized(this) {
			call.setEmployee((AbstractEmployee) notifyObservers());
			
			removeObserver(call.getEmployee());
		}
		
		executorService.submit(call);
		
		addObserver(call.getEmployee());
	}
	
	public synchronized Observer notifyObservers() {
		
		return observers.get(0);
	}
	
	private void waitForEmployee() {
		
		while(observers.isEmpty()) {
			try {
				executorService.wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void shutdownExecutor() {
		
		try {
			System.out.println("Shutdown...");
			executorService.awaitTermination(9999, TimeUnit.MILLISECONDS);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void addObserver(Observer o) {
		this.observers.add((ObserverPrioritiable) o);
		
		Collections.sort(observers, new PriorityComparator());
	}

	public synchronized void removeObserver(Observer o) {
		this.observers.remove(o);
	}

	public void setCalls(List<Call> calls) {
		for(Call call : calls) {
			dispatchCall(call);
		}
	}

}
