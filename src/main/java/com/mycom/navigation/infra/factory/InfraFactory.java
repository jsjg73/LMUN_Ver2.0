package com.mycom.navigation.infra.factory;

import com.mycom.navigation.infra.Infrastructure;

public abstract class InfraFactory {
	protected Infrastructure instance;
	public Infrastructure getInfraInstance() {
		if(instance != null)return instance;
		
		instance = createInstance();
		initialize();
		return instance;
	}
	protected abstract Infrastructure createInstance();
	protected abstract void initialize();
}
