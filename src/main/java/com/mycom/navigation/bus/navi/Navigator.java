package com.mycom.navigation.bus.navi;

import java.util.List;

import com.mycom.navigation.infra.Infrastructure;

public abstract class Navigator {
	
	protected Infrastructure infrastructure;
	
	public Navigator(Infrastructure infrastructure) {
		this.infrastructure = infrastructure;
	}
	
	public abstract List<Double[]> navigate(double sx, double sy, double ex, double ey);
	
}
