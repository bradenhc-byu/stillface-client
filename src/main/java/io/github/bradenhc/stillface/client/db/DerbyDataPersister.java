package io.github.bradenhc.stillface.client.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.bradenhc.stillface.client.api.db.IDataPersister;
import io.github.bradenhc.stillface.client.model.Code;
import io.github.bradenhc.stillface.client.model.DataPoint;
import io.github.bradenhc.stillface.client.model.ProfileInfo;
import io.github.bradenhc.stillface.client.model.Tag;

public class DerbyDataPersister implements IDataPersister {

	private DerbyConnection mDatabaseConnection;

	public DerbyDataPersister(DerbyConnection connection) {
		mDatabaseConnection = connection;
	}

	@Override
	public boolean persist(Object obj) {
		if (obj instanceof ProfileInfo) {
			return persistProfileInfo((ProfileInfo) obj);
		} else if (obj instanceof DataPoint) {
			return persistDataPoint((DataPoint) obj);
		} else if (obj instanceof Code) {
			return persistCode((Code) obj);
		} else if (obj instanceof Tag) {
			return persistTag((Tag) obj);
		}
		return false;
	}

	@Override
	public boolean update(Object obj) {
		if (obj instanceof ProfileInfo) {
			return updateProfileInfo((ProfileInfo) obj);
		} else if (obj instanceof DataPoint) {
			return updateDataPoint((DataPoint) obj);
		} else if (obj instanceof Code) {
			return updateCode((Code) obj);
		} else if (obj instanceof Tag) {
			return updateTag((Tag) obj);
		}
		return false;
	}

	private boolean persistProfileInfo(ProfileInfo info) {

		return false;
	}

	private boolean persistDataPoint(DataPoint dp) {

		return false;
	}

	private boolean persistCode(Code code) {

		return false;
	}

	private boolean persistTag(Tag tag) {

		return false;
	}

	private boolean updateProfileInfo(ProfileInfo info) {

		return false;
	}

	private boolean updateDataPoint(DataPoint dp) {

		return false;
	}

	private boolean updateCode(Code code) {

		return false;
	}

	private boolean updateTag(Tag tag) {

		return false;
	}

}
