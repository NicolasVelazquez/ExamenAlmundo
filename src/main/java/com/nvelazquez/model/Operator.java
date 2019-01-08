package com.nvelazquez.model;

public class Operator extends AbstractEmployee {

	public Operator() {
		attendance = new DefaultAttendance();
	}
	
    public void update(Subject o, Object arg) {
        performAttend(((Call) arg));
        o.addObserver(this);
    }

	public int getPriority() {
		return 1;
	}

	public void update(Object arg) {
        performAttend(((Call) arg));
	}

}
