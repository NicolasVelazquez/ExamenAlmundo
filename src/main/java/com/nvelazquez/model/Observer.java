package com.nvelazquez.model;

public interface Observer {

	public void update(Object arg);
	public void update(Subject o, Object arg);

}
