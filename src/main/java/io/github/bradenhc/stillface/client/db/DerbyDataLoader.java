package io.github.bradenhc.stillface.client.db;

import java.util.ArrayList;
import java.util.List;

import io.github.bradenhc.stillface.client.api.db.IDataLoader;
import io.github.bradenhc.stillface.client.model.ProfileInfo;

public class DerbyDataLoader implements IDataLoader {
	
	private DerbyConnection mConnection;
	
	public DerbyDataLoader(DerbyConnection connection) {
		mConnection = connection;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> load(Class<T> clazz) {
		if(clazz.equals(ProfileInfo.class)) {
			return (List<T>) loadProfileInfo();
		}
		return null;
	}
	
	private List<ProfileInfo> loadProfileInfo(){
		List<ProfileInfo> profiles = new ArrayList<>();
		
		return profiles;
	}

}
