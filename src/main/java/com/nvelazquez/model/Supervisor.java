package com.nvelazquez.model;

public class Supervisor extends AbstractEmployee{

	public Supervisor() {
		attendance = new DefaultAttendance();
	}
	
    public void update(Subject o, Object arg) {
        o.removeObserver(this);
        performAttend((Call) arg);
        o.addObserver(this);
    }

	public int getPriority() {
		return 2;
	}
	
}
