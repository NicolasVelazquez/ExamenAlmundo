package com.nvelazquez;

import java.util.List;
import java.util.Observable;

public class Dispatcher extends Observable{

	private List Observers;
	
	public void registerObserver(Observer observer) {
		// TODO Auto-generated method stub
		
	}

	public void removeObserver(Observer observer) {
		// TODO Auto-generated method stub
		
	}

	public void notifyObserver(Observer observer) {
		// TODO Auto-generated method stub
		
	}
	
	public synchronized void dispatchCall() {
		setChanged();
		notifyObservers();
	}

	public List getObservers() {
		return Observers;
	}

	public void setObservers(List observers) {
		Observers = observers;
	}

}
