package com.nvelazquez.dispatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.nvelazquez.model.Call;
import com.nvelazquez.model.Observer;
import com.nvelazquez.model.ObserverPrioritiable;
import com.nvelazquez.model.Subject;
import com.nvelazquez.util.PriorityComparator;

public class NewDispatcher  implements Subject{

	private List<ObserverPrioritiable> observers = Collections.synchronizedList(new ArrayList<>());
	private ExecutorService executorService = Executors.newFixedThreadPool(10);
	
	public NewDispatcher() {}
	
	public NewDispatcher(List<ObserverPrioritiable> observers, List<Call> calls) {
		this.observers = observers;
		Collections.sort(this.observers, new PriorityComparator());
	}
	
	public void dispatchCall(Call call) {

		waitForEmployee();
		
		ObserverPrioritiable employee = asignEmployee();
		
		Runnable dispatchTask = new Runnable() {
			public void run() {
				employee.update(NewDispatcher.this, call);
			};
		};
		executorService.submit(dispatchTask);
	}
	
	public synchronized ObserverPrioritiable asignEmployee() {
		ObserverPrioritiable o = this.observers.get(0);
		removeObserver(o);
		return o;
	}

	private void waitForEmployee() {

		while (observers.isEmpty()) {
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

	public void addObserver(Observer o) {
		if(!this.observers.contains(o)){
			this.observers.add((ObserverPrioritiable) o);
			Collections.sort(observers, new PriorityComparator());
		}
	}

	public void removeObserver(Observer o) {
		this.observers.remove(o);
	}
	
	public List<ObserverPrioritiable> getObservers() {
		return observers;
	}

	public void setObservers(List<ObserverPrioritiable> observers) {
		this.observers = observers;
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

}