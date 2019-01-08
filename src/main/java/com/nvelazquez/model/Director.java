package com.nvelazquez.model;

public class Director extends AbstractEmployee{

	public Director() {
		attendance = new DefaultAttendance();
	}
	
    public void update(Subject o, Object arg) {
        performAttend((Call) arg);
        o.addObserver(this);
    }

	public int getPriority() {
		return 3;
	}
	
	public void update(Object arg) {
        performAttend(((Call) arg));
	}

}
