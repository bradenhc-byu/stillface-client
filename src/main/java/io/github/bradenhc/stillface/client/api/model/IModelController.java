package io.github.bradenhc.stillface.client.api.model;

import java.util.List;

public interface IModelController {
	
	public boolean merge(String id, Object obj);
	
	public <T> Object find(String id, Class<T> clazz);
	
	public <T> List<Object> findAll(Class<T> clazz);
	
	public <T> Object remove(String id, Class<T> clazz);
	
	public <T> Object removeAll(Class<T> clazz);
	
}
