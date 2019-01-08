package com.nvelazquez.dispatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.nvelazquez.model.AbstractEmployee;
import com.nvelazquez.model.Call;
import com.nvelazquez.model.Observer;
import com.nvelazquez.model.ObserverPrioritiable;
import com.nvelazquez.model.Subject;
import com.nvelazquez.util.PriorityComparator;

public class NewDispatcher  implements Subject{

	private List<ObserverPrioritiable> observers = Collections.synchronizedList(new ArrayList<>());
	private ExecutorService executorService = Executors.newFixedThreadPool(10);
	private Queue<Call> calls = new LinkedList<>();
	
	public NewDispatcher() {}
	
	public NewDispatcher(List<ObserverPrioritiable> observers, List<Call> calls) {
		this.observers = observers;
		Collections.sort(this.observers, new PriorityComparator());
	}
	
	public void dispatchCall(Call call) {

//		calls.add(call);
		
		waitForEmployee();
		
		AbstractEmployee employee = (AbstractEmployee) asignEmployee();
		System.out.println(employee.getPriority());
		
		Runnable dispatchTask = new Runnable() {
			public void run() {
				employee.performAttend(call);
				addObserver(employee);
			};
		};
		executorService.submit(dispatchTask);
	}
	
	public ObserverPrioritiable asignEmployee() {
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

	public synchronized void addObserver(Observer o) {
		this.observers.add((ObserverPrioritiable) o);
		Collections.sort(observers, new PriorityComparator());
	}

	public synchronized void removeObserver(Observer o) {
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

	public Queue<Call> getCalls() {
		return calls;
	}

	public void setCalls(Queue<Call> calls) {
		this.calls = calls;
	}

}