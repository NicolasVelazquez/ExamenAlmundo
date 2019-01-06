package com.nvelazquez.model;

public class Operator extends AbstractEmployee {

	public Operator() {
		attendance = new DefaultAttendance();
	}
	
    public void update(Subject o, Object arg) {
        o.removeObserver(this);
//        performAttend((Call) arg);
        o.addObserver(this);
    }

	public int getPriority() {
		return 1;
	}

}