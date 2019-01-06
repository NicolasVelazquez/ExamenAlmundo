package com.nvelazquez.model;

public abstract class AbstractEmployee implements ObserverPrioritiable {

	Attendance attendance;
	
	public void performAttend(Call call) {
		attendance.attend(call);
	};
}
