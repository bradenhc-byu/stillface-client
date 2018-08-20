package io.github.bradenhc.stillface.client.api.db;

import io.github.bradenhc.stillface.client.model.Code;
import io.github.bradenhc.stillface.client.model.DataPoint;
import io.github.bradenhc.stillface.client.model.ProfileInfo;
import io.github.bradenhc.stillface.client.model.Tag;

public interface IQueryBuilder {

	public String getCode(int id);
	public String getAllCodes();
	public String getProfileInfo(int id);
	public String getAllProfileInfo();
	public String getDataPoint(int id);
	public String getAllDataPoints();
	public String getTag(int id);
	public String getAllTags();
	
	public String addCode(Code code);
	public String addProfileInfo(ProfileInfo info);
	public String addDataPoint(DataPoint dp);
	public String addTag(Tag tag);
	
	public String updateCode(Code code);
	public String updateProfileInfo(ProfileInfo info);
	public String updateDataPoint(DataPoint dp);
	public String updateTag(Tag tag);
	
	public String removeCode(int id);
	public String removeProfileInfo(int id);
	public String removeDataPoint(int id);
	public String removeTag(int id);
	
	public String initProfileInfo();
	public String initDataPoints();
	public String initCodes();
	public String initTags();
}
