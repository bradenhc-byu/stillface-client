package io.github.bradenhc.stillface.client.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.github.bradenhc.stillface.client.api.db.IDataLoader;
import io.github.bradenhc.stillface.client.model.Code;
import io.github.bradenhc.stillface.client.model.DataPoint;
import io.github.bradenhc.stillface.client.model.ProfileInfo;
import io.github.bradenhc.stillface.client.model.Tag;

@SuppressWarnings("unchecked")
public class DerbyDataLoader implements IDataLoader {
	
	private DerbyConnection mConnection;
	
	public DerbyDataLoader(DerbyConnection connection) {
		mConnection = connection;
	}

	
	@Override
	public <T> List<T> load(Class<T> clazz) {
		return loadWithQuery(clazz, null);
	}
	
	@Override
	public <T> List<T> load(Class<T> clazz, String query){
		return loadWithQuery(clazz, query);
	}
	
	private <T> List<T> loadWithQuery(Class<T> clazz, String query) {
		if(clazz.equals(ProfileInfo.class)) {
			return (List<T>) loadProfileInfo(query);
		} else if (clazz.equals(DataPoint.class)) {
			return (List<T>) loadDataPoint(query);
		} else if(clazz.equals(Code.class)) {
			return (List<T>) loadCodes(query);
		} else if(clazz.equals(Tag.class)) {
			return (List<T>) loadTags(query);
		}
		return null;
	}
	
	private List<ProfileInfo> loadProfileInfo(String query){
		List<ProfileInfo> profiles = new ArrayList<>();
		String dbq = (query != null) ? query : DerbyQueryBuilder.instance().getAllProfileInfo();
		try {
			if(!mConnection.connection().isClosed()) {
				Statement s = mConnection.connection().createStatement();
				ResultSet rs = s.executeQuery(query);
				while(rs.next()) {
					ProfileInfo info = new ProfileInfo(rs);
					info.getTag().setTagValue(rs.getString("value"));
					profiles.add(info);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return profiles;
	}
	
	private List<DataPoint> loadDataPoint(String query){
		List<DataPoint> points = new ArrayList<>();
		String dbq = (query != null) ? query : DerbyQueryBuilder.instance().getAllDataPoints();
		return points;
	}
	
	private List<Code> loadCodes(String query){
		List<Code> codes = new ArrayList<>();
		String dbq = (query != null) ? query : DerbyQueryBuilder.instance().getAllCodes();
		return codes;
	}
	
	private List<Tag> loadTags(String query){
		List<Tag> tags = new ArrayList<>();
		String dbq = (query != null) ? query : DerbyQueryBuilder.instance().getAllTags();
		return tags;
	}

}
