package com.nvelazquez.dispatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.nvelazquez.model.Call;
import com.nvelazquez.model.Observer;
import com.nvelazquez.model.ObserverPrioritiable;
import com.nvelazquez.model.Subject;

public class Dispatcher implements Subject{

	List<ObserverPrioritiable> observers = new ArrayList<>();
	List<Call> calls = new ArrayList<Call>();
	
	public Dispatcher(List<ObserverPrioritiable> observers, List<Call> calls) {
		this.observers = observers;
		this.calls = calls;
	}
	
	public void dispatchCall(Call call) {
		notifyObservers();
	}
	
	public void addCall(Call call) {
		this.calls.add(call);
		
		dispatchCall(call);
	}
	
	public synchronized void addObserver(Observer o) {
		this.observers.add((ObserverPrioritiable) o);
		
		Collections.sort(observers, (a, b) -> {
			return a.getPriority() - b.getPriority();
		});
	}

	public synchronized void removeObserver(Observer o) {
		this.observers.remove(o);
	}

	public Observer notifyObservers() {
		
		return null;		
	}

	public List<Call> getCalls() {
		return calls;
	}

	public void setCalls(List<Call> calls) {
		for(Call call : calls) {
			addCall(call);
		}
	}

}
