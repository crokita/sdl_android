package com.smartdevicelink.test.json.rpc;

import org.json.JSONArray;
import org.json.JSONObject;

import com.smartdevicelink.test.utils.JsonUtils;

//a class to help ease the pain of getting different type values from the JSONObject
public class JsonExtractor {	
	private final JSONObject obj;
	
	public JsonExtractor (JSONObject obj) {
		this.obj = obj;
	}

	public JsonExtractor to (String parameter) {
		JsonExtractor copy = new JsonExtractor(JsonUtils.readJsonObjectFromJsonObject(obj, parameter));
		return copy; //make a new copy because the obj variable itself shouldn't change
	}
	
	public JSONObject getObj () {
		return obj;
	}
	
	public JSONObject getObj (String parameter) {
		return JsonUtils.readJsonObjectFromJsonObject(obj, parameter);
	}
	
	public JSONArray getArr (String parameter) {
		return JsonUtils.readJsonArrayFromJsonObject(obj, parameter);
	}

	public String getStr (String parameter) {
		return JsonUtils.readStringFromJsonObject(obj, parameter);
	}
	
	public Integer getInt (String parameter) {
		return JsonUtils.readIntegerFromJsonObject(obj, parameter);
	}
	
	public Boolean getBool (String parameter) {
		return JsonUtils.readBooleanFromJsonObject(obj, parameter);
	}
	
	public Double getDouble (String parameter) {
		return JsonUtils.readDoubleFromJsonObject(obj, parameter);
	}
}
