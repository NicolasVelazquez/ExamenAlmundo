package com.nvelazquez.model;

public interface ObserverPrioritiable extends Observer {
	
	int maxPriorities = 3;
	
	public int getPriority();

}
