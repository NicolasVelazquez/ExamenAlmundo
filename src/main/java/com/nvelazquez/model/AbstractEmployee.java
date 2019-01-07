package com.nvelazquez.model;

public abstract class AbstractEmployee implements ObserverPrioritiable {

	Attendance attendance;
	private int state;
	
	public void performAttend(Call call) {
		attendance.attend(call);
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	};
}
