package com.nvelazquez.model;

public class Call implements Runnable {
	
	private long duration;
	private AbstractEmployee employee;
	
	public Call() {}
	
	public Call(long duration) {
		this.duration = duration;
	}
	
	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	@Override
	public void run() {
		employee.performAttend(this);
	}

	public AbstractEmployee getEmployee() {
		return employee;
	}

	public void setEmployee(AbstractEmployee employee) {
		this.employee = employee;
	}
	
}
