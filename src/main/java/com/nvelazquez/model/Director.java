package com.nvelazquez.model;

public class Director extends AbstractEmployee{

	public Director() {
		attendance = new DefaultAttendance();
	}
	
	public void update(Call call) {
		performAttend(call);		
	}

	public int getPriority() {
		return 3;
	}

}
