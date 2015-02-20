package com.smartdevicelink.test.json.rpc;

import org.json.JSONArray;
import org.json.JSONObject;

import com.smartdevicelink.test.utils.JsonUtils;

//a class to help ease the pain of getting different type values from the JSONObject
public class JsonExtractor {	
	JSONObject obj;
	
	JsonExtractor (JSONObject obj) {
		this.obj = obj;
	}

	public JsonExtractor to (String parameter) {
		obj = JsonUtils.readJsonObjectFromJsonObject(obj, parameter);
		return this;
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
}
