package com.nvelazquez.util;

import java.util.Comparator;

import com.nvelazquez.model.ObserverPrioritiable;

public class PriorityComparator implements Comparator<ObserverPrioritiable>{

	@Override
	public int compare(ObserverPrioritiable o1, ObserverPrioritiable o2) {
		return o1.getPriority() - o2.getPriority();
	}

}
