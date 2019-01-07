package com.nvelazquez.dispatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.nvelazquez.model.AbstractEmployee;
import com.nvelazquez.model.Call;
import com.nvelazquez.model.Observer;
import com.nvelazquez.model.ObserverPrioritiable;
import com.nvelazquez.model.Subject;
import com.nvelazquez.util.PriorityComparator;

public class Dispatcher implements Subject{

	private List<ObserverPrioritiable> observers = Collections.synchronizedList(new ArrayList<>());
	public ExecutorService executorService = Executors.newFixedThreadPool(10);
	
	public Dispatcher() {}
	
	public Dispatcher(List<ObserverPrioritiable> observers, List<Call> calls) {
		this.observers = observers;
		Collections.sort(this.observers, new PriorityComparator());
	}
	
	public void dispatchCall(Call call) {
		
//		waitForEmployee();

//		synchronized(this) {
//			call.setEmployee((AbstractEmployee) notifyObservers(call));
//			
//			removeObserver(call.getEmployee());
//		}
		
		
		
		Runnable dispatchTask = new Runnable(){
			public void run() {
				Dispatcher.this.notifyObservers(call);
			};
		};
		executorService.submit(dispatchTask);
		
//		addObserver(call.getEmployee());
	}
	
	public void notifyObservers(Object call) {
//		int priority = 1;
//		boolean called = false;

		waitForEmployee();
		this.observers.get(0).update(this, call);
		
//		while(priority <= ObserverPrioritiable.maxPriorities && !called){
//			int copyPriority = priority;
//			ObserverPrioritiable observer = this.observers
//					.stream()
//					.filter(o -> o.getPriority() == copyPriority)
//					.findFirst().orElse(null);
//			if(observer == null){
//				priority++;
//			} else {
//				observer.update(this, call);
//				called = true;
//			}
//		}
	}
	
	private void waitForEmployee() {
		
		while(observers.isEmpty()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void shutdownExecutor() {
		
		System.out.println("Shutdown...");
		executorService.shutdown();
		
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
