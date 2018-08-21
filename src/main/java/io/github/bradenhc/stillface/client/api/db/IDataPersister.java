package io.github.bradenhc.stillface.client.api.db;

public interface IDataPersister {

	public boolean persist(Object obj);
	
	public boolean update(Object obj);
	
}
