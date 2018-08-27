package io.github.bradenhc.stillface.client.api.db;

import java.util.List;

public interface IDataLoader {

	public <T> List<T> load(Class<T> clazz);
	
	public <T> List<T> load(Class<T> clazz, String query);
	
}
