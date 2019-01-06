package com.nvelazquez.model;

public class Supervisor extends AbstractEmployee{

	public Supervisor() {
		attendance = new DefaultAttendance();
	}
	
	public void update(Call call) {
		performAttend(call);		
	}

	public int getPriority() {
		return 2;
	}
}
