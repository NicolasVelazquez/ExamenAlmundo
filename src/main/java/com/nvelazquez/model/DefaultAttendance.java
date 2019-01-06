package com.nvelazquez.model;

public class DefaultAttendance implements Attendance{

	public void attend(Call call) {
		try {
			Thread.sleep(call.getDuration());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
