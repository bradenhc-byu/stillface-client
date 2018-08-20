package io.github.bradenhc.stillface.client.api.db;

import java.util.List;

public interface IDataLoader {

	public <T> List<Object> load(Class<T> clazz);
	
}
