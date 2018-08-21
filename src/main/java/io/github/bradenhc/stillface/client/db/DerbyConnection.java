package io.github.bradenhc.stillface.client.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyConnection {

	private Connection mConnection = null;
	private boolean mOpen = false;

	public DerbyConnection() {

	}

	public DerbyConnection(String host, String port, String dbname) throws SQLException {
		open(host, port, dbname);
	}

	public void open(String host, String port, String dbname) throws SQLException {
		mConnection = DriverManager
				.getConnection(String.format("jdbc:derby://%s:%s/%s;create=true", host, port, dbname));
		mOpen = true;
	}

	public void close() throws SQLException {
		if (mOpen) {
			mConnection.close();
		}
	}

	public Connection connection() {
		return mConnection;
	}

	public boolean established() {
		return mOpen;
	}
}
