package com.nvelazquez.model;

public class DefaultAttendance implements Attendance{

	private static final long toMilliseconds = 1000l;
	
	public void attend(Call call) {
		System.out.println("Atendiendo llamada... Thead " +Thread.currentThread().getId());
//		try {
//			Thread.sleep(call.getDuration()*toMilliseconds);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		System.out.println("Llamada finalizada. Thead " +Thread.currentThread().getId());
	}
}
