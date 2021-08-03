package com.mycom.navigation.infra;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfraEdge {
	private int cost;
	private boolean enable;
	private List<Double[]> realPath;
	
	public InfraEdge(int cost, List<Double[]> realPath) {
		this.cost = cost;
		this.realPath = realPath;
		enable = realPath!=null;
	}
	
	public void setRealPath(List<Double[]> realPath) {
		this.realPath = realPath;
		enable = realPath!=null;
	}
}
