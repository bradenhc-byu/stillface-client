package io.github.bradenhc.stillface.client.model.controllers;

import java.sql.SQLException;
import java.util.List;

import io.github.bradenhc.stillface.client.api.db.IDataLoader;
import io.github.bradenhc.stillface.client.api.db.IDataPersister;
import io.github.bradenhc.stillface.client.api.model.IModelController;
import io.github.bradenhc.stillface.client.db.DerbyConnection;
import io.github.bradenhc.stillface.client.db.DerbyDataLoader;
import io.github.bradenhc.stillface.client.db.DerbyDataPersister;

public class ModelControllerDerby implements IModelController {

	private DerbyConnection mConnection;
	private IDataPersister mDataPersister;
	private IDataLoader mDataLoader;

	public ModelControllerDerby(String host, String port, String dbname) throws SQLException {
		mConnection = new DerbyConnection(host, port, dbname);
		mDataPersister = new DerbyDataPersister(mConnection);
		mDataLoader = new DerbyDataLoader(mConnection);
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
