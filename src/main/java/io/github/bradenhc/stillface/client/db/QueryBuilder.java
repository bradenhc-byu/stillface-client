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
import static io.github.bradenhc.querybaker.sql.Insert.*;

public class QueryBuilder implements IQueryBuilder {

	// Profile Info
	private final Table mTableProfileInfo = Table.create("sf_imports").alias("sfi");
	private final Column mColImportId = new Column("iid", DataType.INTEGER, 1, true, true);
	private final Column mColFilename = new Column("filename", DataType.VARCHAR, 255, true);
	private final Column mColYear = new Column("syear", DataType.INTEGER, 1, true);
	private final Column mColFamilyId = new Column("fid", DataType.INTEGER, 1, true);
	private final Column mColProfileId = new Column("pid", DataType.INTEGER, 1, true);
	private final Column mColProfileTagId = new Column("tid", DataType.INTEGER, 1, true);
	private final Column mColProfileAlias = new Column("alias", DataType.INTEGER, 1);
	private final Column mColDate = new Column("date", DataType.DATE, 1, true);

	// Data Points
	private final Table mTableDataPoints = Table.create("sf_data").alias("sfd");
	private final Column mColDataPointId = new Column("did", DataType.INTEGER, 1, true, true);
	private final Column mColDataImportId = new Column("iid", DataType.INTEGER, 1, true);
	private final Column mColDataTime = new Column("time", DataType.INTEGER, 1, true);
	private final Column mColDataDuration = new Column("duration", DataType.INTEGER, 1, true);
	private final Column mColDataCodeId = new Column("cid", DataType.INTEGER, 1, true);
	private final Column mColComment = new Column("comment", DataType.VARCHAR, 500);

	// Codes
	private final Table mTableCodes = Table.create("sf_codes").alias("sfc");
	private final Column mColCodeId = new Column("cid", DataType.INTEGER, 1, true, true);
	private final Column mColCodeName = new Column("name", DataType.VARCHAR, 200, true);
	private final Column mColDelimiter = new Column("delimiter", DataType.INTEGER, 1, true);

	// Tags
	private final Table mTableTags = Table.create("sf_tags").alias("sft");
	private final Column mColTagId = new Column("tid", DataType.INTEGER, 1, true, true);
	private final Column mColTagValue = new Column("value", DataType.VARCHAR, 200, true);

	public QueryBuilder() {
		// Initialze the tables with their columns
		// Profile info table
		mColImportId.setAutoIncrement(true);
		mTableProfileInfo.columns(mColImportId, mColFilename, mColYear, mColFamilyId, mColProfileId, mColProfileTagId,
				mColProfileAlias, mColDate);

		// Data points table
		mColDataPointId.setAutoIncrement(true);
		mTableDataPoints.columns(mColDataPointId, mColDataImportId, mColDataTime, mColDataDuration, mColDataCodeId,
				mColComment);

		// Code table
		mColCodeId.setAutoIncrement(true);
		mTableCodes.columns(mColCodeId, mColCodeName, mColDelimiter);

		// Tag table
		mColTagId.setAutoIncrement(true);
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
		return mTableTags.insert()
				.values(pair(mColCodeName, code.getName()), pair(mColDelimiter, code.getDelimiterIndex())).build();
	}

	@Override
	public String addProfileInfo(ProfileInfo info) {
		return mTableProfileInfo.insert()
				.values(pair(mColFilename, info.getFilename()), pair(mColYear, info.getYear()),
						pair(mColFamilyId, info.getFamilyID()), pair(mColProfileId, info.getParticipantNumber()),
						pair(mColTagId, info.getTag().getTagID()), pair(mColProfileAlias, info.getAlias()),
						pair(mColDate, info.getDate()))
				.build();
	}

	@Override
	public String addDataPoint(DataPoint dp) {
		return mTableDataPoints.insert()
				.values(pair(mColDataImportId, dp.getImportID()), pair(mColDataTime, dp.getTime()),
						pair(mColDataDuration, dp.getDuration()), pair(mColCodeId, dp.getCode().getCodeID()),
						pair(mColComment, dp.getComment()))
				.build();
	}

	@Override
	public String addTag(Tag tag) {
		return mTableTags.insert().values(pair(mColTagValue, tag.getTagValue())).build();
	}

	@Override
	public String updateCode(Code code) {
		return mTableTags.update()
				.values(pair(mColCodeName, code.getName()), pair(mColDelimiter, code.getDelimiterIndex()))
				.where(equal(mColCodeId, code.getCodeID())).build();
	}

	@Override
	public String updateProfileInfo(ProfileInfo info) {
		return mTableProfileInfo.update()
				.values(pair(mColYear, info.getYear()), pair(mColFamilyId, info.getFamilyID()),
						pair(mColProfileId, info.getParticipantNumber()), pair(mColTagId, info.getTag().getTagID()),
						pair(mColProfileAlias, info.getAlias()))
				.where(equal(mColImportId, info.getImportID())).build();
	}

	@Override
	public String updateDataPoint(DataPoint dp) {
		return mTableDataPoints.update()
				.values(pair(mColDataTime, dp.getTime()), pair(mColDataDuration, dp.getDuration()),
						pair(mColCodeId, dp.getCode().getCodeID()), pair(mColComment, dp.getComment()))
				.where(equal(mColDataPointId, dp.getDataID())).build();
	}

	@Override
	public String updateTag(Tag tag) {
		return mTableTags.update().values(pair(mColTagValue, tag.getTagValue())).where(equal(mColTagId, tag.getTagID()))
				.build();
	}

	@Override
	public String removeCode(int id) {
		return mTableCodes.delete().where(equal(mColCodeId, id)).build();
	}

	@Override
	public String removeProfileInfo(int id) {
		return mTableProfileInfo.delete().where(equal(mColDataImportId, id)).build();
	}

	@Override
	public String removeDataPoint(int id) {
		return mTableDataPoints.delete().where(equal(mColDataPointId, id)).build();
	}

	@Override
	public String removeTag(int id) {
		return mTableTags.delete().where(equal(mColTagId, id)).build();
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
