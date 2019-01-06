package com.nvelazquez.model;

public class Call implements Runnable {
	
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

	@Override
	public void run() {
		System.out.println("Atendiendo llamada...");
		try {
			Thread.sleep(this.getDuration()*1000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Llamada finalizada.");		
	}
	
}
