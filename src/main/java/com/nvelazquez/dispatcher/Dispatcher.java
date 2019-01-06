package com.nvelazquez.dispatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.nvelazquez.model.Call;
import com.nvelazquez.model.Observer;
import com.nvelazquez.model.ObserverPrioritiable;
import com.nvelazquez.model.Subject;
import com.nvelazquez.util.PriorityComparator;

public class Dispatcher implements Subject{

	List<ObserverPrioritiable> observers = new ArrayList<>();
	
	public Dispatcher() {}
	
	public Dispatcher(List<ObserverPrioritiable> observers, List<Call> calls) {
		this.observers = observers;
		Collections.sort(this.observers, new PriorityComparator());
	}
	
	public void dispatchCall(Call call) {
		
		waitForEmployee();
		
		notifyObservers(call);
	}
	
	public void notifyObservers(Call call) {
		
		observers.get(0).update(this, call);
	}
	
	private void waitForEmployee() {
		
		while(observers.isEmpty()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
