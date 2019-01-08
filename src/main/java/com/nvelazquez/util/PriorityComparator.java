package com.nvelazquez.util;

import java.util.Comparator;

import com.nvelazquez.model.ObserverWithPriority;

public class PriorityComparator implements Comparator<ObserverWithPriority>{

	@Override
	public int compare(ObserverWithPriority o1, ObserverWithPriority o2) {
		return o1.getPriority() - o2.getPriority();
	}

}
