package com.nvelazquez.dispatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.nvelazquez.model.Call;
import com.nvelazquez.model.Observer;
import com.nvelazquez.model.ObserverPrioritiable;
import com.nvelazquez.model.Subject;
import com.nvelazquez.util.PriorityComparator;

public class Dispatcher implements Subject {

	private List<ObserverPrioritiable> observers = Collections.synchronizedList(new ArrayList<>());
	private ExecutorService executorService = Executors.newFixedThreadPool(10);
	private Queue<Call> calls = new LinkedList<>();
	private Thread thread;

	public Dispatcher() {
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				for(;;){
					if(calls.peek() != null){
						
						waitForEmployee();
						
						ObserverPrioritiable observer = notifyObservers(calls.poll());
						removeObserver(observer);
						
						Runnable dispatchTask = new Runnable() {
							public void run() {
								observer.update(calls.poll());
								addObserver(observer);
							};
						};
						executorService.submit(dispatchTask);
					}
				}
			}
		});
		
		thread.start();
	}

	public Dispatcher(List<ObserverPrioritiable> observers, List<Call> calls) {
		this();
		this.observers = observers;
		Collections.sort(this.observers, new PriorityComparator());
	}

	public void dispatchCall(Call call) {

		calls.add(call);
//		Runnable dispatchTask = new Runnable() {
//			public void run() {
//				// waitForEmployee();
//				Dispatcher.this.notifyObservers(call);
//			};
//		};
//		executorService.submit(dispatchTask);
	}

	public ObserverPrioritiable notifyObservers(Object call) {
		// System.out.println("notifyObservers");
		// int priority = 1;
		// boolean called = false;

//		synchronized (this.observers) {
//			waitForEmployee();
//		}
		return this.observers.get(0);

		// while(priority <= ObserverPrioritiable.maxPriorities && !called){
		// int copyPriority = priority;
		// ObserverPrioritiable observer = this.observers
		// .stream()
		// .filter(o -> o.getPriority() == copyPriority)
		// .findFirst().orElse(null);
		// if(observer == null){
		// priority++;
		// } else {
		// observer.update(this, call);
		// called = true;
		// }
		// }
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

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

}
