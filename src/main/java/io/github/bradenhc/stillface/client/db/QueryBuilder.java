package io.github.bradenhc.stillface.client.db;

import io.github.bradenhc.stillface.client.api.db.IQueryBuilder;
import io.github.bradenhc.stillface.client.model.Code;
import io.github.bradenhc.stillface.client.model.DataPoint;
import io.github.bradenhc.stillface.client.model.ProfileInfo;
import io.github.bradenhc.stillface.client.model.Tag;

import io.github.bradenhc.querybaker.sql.Table;
import io.github.bradenhc.querybaker.sql.Column;
import io.github.bradenhc.querybaker.sql.DataType;

import static io.github.bradenhc.querybaker.cond.Condition.*;

public class QueryBuilder implements IQueryBuilder {

	// Define the tables and their columns
	private final Table mTableProfileInfo = Table.create("sf_imports").alias("sfi");
	private final Table mTableDataPoints = Table.create("sf_data").alias("sfd");
	private final Table mTableCodes = Table.create("sf_codes").alias("sfc");
	private final Table mTableTags = Table.create("sf_tags").alias("sft");

	private final Column mColImportId = new Column("iid", DataType.INTEGER, 1, true);
	private final Column mColFilename = new Column("filename", DataType.VARCHAR, 255, true);
	private final Column mColYear = new Column("syear", DataType.INTEGER, 1, true);
	private final Column mColFamilyId = new Column("fid", DataType.INTEGER, 1, true);
	private final Column mColProfileId = new Column("pid", DataType.INTEGER, 1, true);
	private final Column mColTagId = new Column("tid", DataType.INTEGER, 1, true);
	private final Column mColProfileAlias = new Column("alias", DataType.INTEGER, 1);
	private final Column mColDate = new Column("date", DataType.DATE, 1, true);
	private final Column mColDataPointId = new Column("did", DataType.INTEGER, 1, true);
	private final Column mColDataTime = new Column("time", DataType.INTEGER, 1, true);
	private final Column mColDataDuration = new Column("duration", DataType.INTEGER, 1, true);
	private final Column mColCodeId = new Column("cid", DataType.INTEGER, 1, true);
	private final Column mColComment = new Column("comment", DataType.VARCHAR, 500);
	private final Column mColCodeName = new Column("name", DataType.VARCHAR, 200, true);
	private final Column mColDelimiter = new Column("delimiter", DataType.INTEGER, 1, true);
	private final Column mColTagValue = new Column("value", DataType.VARCHAR, 200, true);

	public QueryBuilder() {
		// Initialze the tables with their columns
		// Profile info table
		mTableProfileInfo.columns(mColImportId, mColFilename, mColYear, mColFamilyId, mColProfileId, mColTagId,
				mColProfileAlias, mColDate);

		// Data points table
		mTableDataPoints.columns(mColDataPointId, mColImportId, mColDataTime, mColDataDuration, mColCodeId,
				mColComment);
		
		// Code table
		mTableCodes.columns(mColCodeId, mColCodeName, mColDelimiter);
		
		// Tag table
		mTableTags.columns(mColTagId, mColTagValue);
	}

	@Override
	public String getCode(int id) {
		return mTableCodes.select().all().where(equal(mColCodeId, id)).build();
	}

	@Override
	public String getAllCodes() {
		return mTableCodes.select().all().build();
	}

	@Override
	public String getProfileInfo(int id) {
		return mTableProfileInfo.select().all().where(equal(mColImportId, id)).build();
	}

	@Override
	public String getAllProfileInfo() {
		return mTableProfileInfo.select().all().build();
	}

	@Override
	public String getDataPoint(int id) {
		return mTableDataPoints.select().all().where(equal(mColDataPointId, id)).build();
	}

	@Override
	public String getAllDataPoints() {
		return mTableDataPoints.select().all().build();
	}

	@Override
	public String getTag(int id) {
		return mTableTags.select().all().where(equal(mColTagId, id)).build();
	}

	@Override
	public String getAllTags() {
		return mTableTags.select().all().build();
	}

	@Override
	public String addCode(Code code) {
		return mTableTags.insert().values().build();
	}

	@Override
	public String addProfileInfo(ProfileInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addDataPoint(DataPoint dp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addTag(Tag tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateCode(Code code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateProfileInfo(ProfileInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateDataPoint(DataPoint dp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateTag(Tag tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeCode(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeProfileInfo(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeDataPoint(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeTag(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String initProfileInfo() {
		return mTableProfileInfo.build();
	}
	
	@Override
	public String initDataPoints() {
		return mTableDataPoints.build();
	}
	
	@Override
	public String initCodes() {
		return mTableCodes.build();
	}
	
	@Override
	public String initTags() {
		return mTableTags.build();
	}

}
