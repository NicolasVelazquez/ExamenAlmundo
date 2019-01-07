package com.nvelazquez.model;

public class Call {
	
	private long duration;
	
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

}
