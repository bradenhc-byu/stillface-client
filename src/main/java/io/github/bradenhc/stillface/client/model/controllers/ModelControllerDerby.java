package io.github.bradenhc.stillface.client.model.controllers;

import java.util.List;

import io.github.bradenhc.stillface.client.api.db.IDataPersister;
import io.github.bradenhc.stillface.client.api.model.IModelController;

public class ModelControllerDerby implements IModelController {
	
	public ModelControllerDerby(){
		
	}
	
	public boolean populateFrom(IDataPersister db) {
		
		return false;
	}

	@Override
	public boolean merge(String id, Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> Object find(String id, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<Object> findAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Object remove(String id, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Object removeAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

}
