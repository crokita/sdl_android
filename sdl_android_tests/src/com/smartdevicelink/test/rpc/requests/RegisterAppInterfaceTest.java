package com.smartdevicelink.test.rpc.requests;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.DeviceInfo;
import com.smartdevicelink.proxy.rpc.RegisterAppInterface;
import com.smartdevicelink.proxy.rpc.SdlMsgVersion;
import com.smartdevicelink.proxy.rpc.TTSChunk;
import com.smartdevicelink.proxy.rpc.enums.AppHMIType;
import com.smartdevicelink.proxy.rpc.enums.Language;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;
import com.smartdevicelink.test.utils.Validator;

public class RegisterAppInterfaceTest extends BaseRpcTests {

	private List<AppHMIType> appHmiTypes = new ArrayList<AppHMIType>();	
	private List<TTSChunk> ttsChunkList = new ArrayList<TTSChunk>();
	private SdlMsgVersion sdlVersion;
	private String appName;
	private String mediaScreenAppName;
	private String appId;
	private Language languageDesired;
	private Language hmiLanguageDesired;
	private String hashId;
	private List<String> vrSynonyms;
	private Boolean isMediaApp;
	private DeviceInfo deviceInfo;
	
	private JSONObject paramsJson;
	
	@Override
	protected RPCMessage createMessage() {
		RegisterAppInterface msg = new RegisterAppInterface();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
		
		try {			
			JSONObject sdlVersionObj = paramsJson.getJSONObject(RegisterAppInterface.KEY_SDL_MSG_VERSION);
			sdlVersion = new SdlMsgVersion(JsonRPCMarshaller.deserializeJSONObject(sdlVersionObj));
			msg.setSdlMsgVersion(sdlVersion);
			
			appName = paramsJson.getString(RegisterAppInterface.KEY_APP_NAME);
			msg.setAppName(appName);
			mediaScreenAppName = paramsJson.getString(RegisterAppInterface.KEY_NGN_MEDIA_SCREEN_APP_NAME);
			msg.setNgnMediaScreenAppName(mediaScreenAppName);
			appId = paramsJson.getString(RegisterAppInterface.KEY_APP_ID);
			msg.setAppID(appId);
			languageDesired = Language.valueForString(paramsJson.getString(RegisterAppInterface.KEY_LANGUAGE_DESIRED));
			msg.setLanguageDesired(languageDesired);
			hmiLanguageDesired = Language.valueForString(paramsJson.getString(RegisterAppInterface.KEY_HMI_DISPLAY_LANGUAGE_DESIRED));
			msg.setHmiDisplayLanguageDesired(hmiLanguageDesired);
			hashId = paramsJson.getString(RegisterAppInterface.KEY_HASH_ID);
			msg.setHashID(hashId);
			isMediaApp = paramsJson.getBoolean(RegisterAppInterface.KEY_IS_MEDIA_APPLICATION);
			msg.setIsMediaApplication(isMediaApp);
			
			JSONObject deviceInfoObj = paramsJson.getJSONObject(RegisterAppInterface.KEY_DEVICE_INFO);
			deviceInfo = new DeviceInfo(JsonRPCMarshaller.deserializeJSONObject(deviceInfoObj));
			msg.setDeviceInfo(deviceInfo);
			
			JSONArray vrSynonymsArray = paramsJson.getJSONArray(RegisterAppInterface.KEY_VR_SYNONYMS);
			vrSynonyms = JsonUtils.<String>createListFromJsonArray(vrSynonymsArray);
			msg.setVrSynonyms(vrSynonyms);
			
			JSONArray ttsChunkArray = paramsJson.getJSONArray(RegisterAppInterface.KEY_TTS_NAME);
			for (int index = 0; index < ttsChunkArray.length(); index++) {
				TTSChunk ttsChunk = new TTSChunk(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)ttsChunkArray.get(index)) );
				ttsChunkList.add(ttsChunk);
			}
			msg.setTtsName(ttsChunkList);
			
			JSONArray appHmiTypesArray = paramsJson.getJSONArray(RegisterAppInterface.KEY_APP_HMI_TYPE);
			appHmiTypes = JsonUtils.<AppHMIType>createListFromJsonArray(appHmiTypesArray);			
			msg.setAppHMIType(appHmiTypes);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return msg;
	}

	@Override
	protected String getMessageType() {
		return RPCMessage.KEY_REQUEST;
	}

	@Override
	protected String getCommandType() {
		return FunctionID.REGISTER_APP_INTERFACE;
	}

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();

		try {
			result.put(RegisterAppInterface.KEY_SDL_MSG_VERSION, paramsJson.getJSONObject(RegisterAppInterface.KEY_SDL_MSG_VERSION));
			result.put(RegisterAppInterface.KEY_APP_NAME, appName);
			result.put(RegisterAppInterface.KEY_NGN_MEDIA_SCREEN_APP_NAME, mediaScreenAppName);
			result.put(RegisterAppInterface.KEY_APP_ID, appId);
			result.put(RegisterAppInterface.KEY_LANGUAGE_DESIRED, languageDesired);
			result.put(RegisterAppInterface.KEY_HMI_DISPLAY_LANGUAGE_DESIRED, hmiLanguageDesired);
			result.put(RegisterAppInterface.KEY_HASH_ID, hashId);
			result.put(RegisterAppInterface.KEY_TTS_NAME, paramsJson.getJSONArray(RegisterAppInterface.KEY_TTS_NAME));
			result.put(RegisterAppInterface.KEY_VR_SYNONYMS, paramsJson.getJSONArray(RegisterAppInterface.KEY_VR_SYNONYMS));
			result.put(RegisterAppInterface.KEY_APP_HMI_TYPE, paramsJson.getJSONArray(RegisterAppInterface.KEY_APP_HMI_TYPE));
			result.put(RegisterAppInterface.KEY_IS_MEDIA_APPLICATION, isMediaApp);
			result.put(RegisterAppInterface.KEY_DEVICE_INFO, paramsJson.getJSONObject(RegisterAppInterface.KEY_DEVICE_INFO));
		} catch (JSONException e) {
			/* do nothing */
		}
		
		return result;
	}

	public void testSdlVersion() {
		SdlMsgVersion copy = ( (RegisterAppInterface) msg).getSdlMsgVersion();
		
		assertTrue("Input value didn't match expected value.", Validator.validateSdlMsgVersion(sdlVersion,copy));
	}
	
	public void testAppName () {
		String copy = ( (RegisterAppInterface) msg).getAppName();
		
		assertEquals("Data didn't match input data.", appName, copy);
	}
	
	public void testNgnMediaAppName () {
		String copy = ( (RegisterAppInterface) msg).getNgnMediaScreenAppName();
		
		assertEquals("Data didn't match input data.", mediaScreenAppName, copy);
	}
	
	public void testAppId () {
		String copy = ( (RegisterAppInterface) msg).getAppID();
		
		assertEquals("Data didn't match input data.", appId, copy);
	}
	
	public void testLanguageDesired () {
		Language copy = ( (RegisterAppInterface) msg).getLanguageDesired();
		
		assertEquals("Data didn't match input data.", languageDesired, copy);
	}
	
	public void testHmiLanguageDesired () {
		Language copy = ( (RegisterAppInterface) msg).getHmiDisplayLanguageDesired();
		
		assertEquals("Data didn't match input data.", hmiLanguageDesired, copy);
	}
	
	public void testHashId () {
		String copy = ( (RegisterAppInterface) msg).getHashID();
		
		assertEquals("Data didn't match input data.", hashId, copy);
	}
	
	public void testTtsName () {
		List<TTSChunk> copy = ( (RegisterAppInterface) msg).getTtsName();
		
		assertNotNull("Tts names were null.", copy);
		assertTrue("Tts names items didn't match input data.", Validator.validateTtsChunks(ttsChunkList, copy));
	}
	
	public void testVrSynonyms () {
		List<String> copy = ( (RegisterAppInterface) msg).getVrSynonyms();
		
		assertEquals("Data didn't match input data.", vrSynonyms, copy);
	}
	
	public void testAppHmiType () {
		List<AppHMIType> copy = ( (RegisterAppInterface) msg).getAppHMIType();
		
        assertNotNull("App HMI list was null.", copy);
        assertEquals("App HMI list size doesn't match input size.", appHmiTypes.size(), copy.size());
        
        for (int index = 0; index < copy.size(); index++) {
        	assertEquals("Data didn't match input data.", appHmiTypes.get(index), copy.get(index).toString());
        }
	}
	
	public void testIsMediaApp () {
		Boolean copy = ( (RegisterAppInterface) msg).getIsMediaApplication();
		
		assertEquals("Data didn't match input data.", (Boolean) isMediaApp, copy);
	}
	
	public void testDeviceInfo () {
		DeviceInfo copy = ( (RegisterAppInterface) msg).getDeviceInfo();
		
		assertTrue("Input value didn't match expected value.", Validator.validateDeviceInfo(deviceInfo,copy));
	}

	public void testNull() {
		RegisterAppInterface msg = new RegisterAppInterface();
		assertNotNull("Null object creation failed.", msg);

		testNullBase(msg);

		assertNull("Sdl version wasn't set, but getter method returned an object.", msg.getSdlMsgVersion());
		assertNull("App name wasn't set, but getter method returned an object.", msg.getAppName());
		assertNull("Media app name wasn't set, but getter method returned an object.", msg.getNgnMediaScreenAppName());
		assertNull("App id wasn't set, but getter method returned an object.", msg.getAppID());
		assertNull("Language desired wasn't set, but getter method returned an object.", msg.getLanguageDesired());
		assertNull("Hmi language desired wasn't set, but getter method returned an object.", msg.getHmiDisplayLanguageDesired());
		assertNull("Hash id wasn't set, but getter method returned an object.", msg.getHashID());
		assertNull("Tts name wasn't set, but getter method returned an object.", msg.getTtsName());
		assertNull("Vr synonyms wasn't set, but getter method returned an object.", msg.getVrSynonyms());
		assertNull("App hmi type wasn't set, but getter method returned an object.", msg.getAppHMIType());
		assertNull("Is media app wasn't set, but getter method returned an object.", msg.getIsMediaApplication());
		assertNull("Device info wasn't set, but getter method returned an object.", msg.getDeviceInfo());
	}
	
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			RegisterAppInterface cmd = new RegisterAppInterface(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			
			JSONArray ttsNameArray = parameters.getJSONArray(RegisterAppInterface.KEY_TTS_NAME);
			List<TTSChunk> ttsNameList = new ArrayList<TTSChunk>();
			for (int index = 0; index < ttsNameArray.length(); index++) {
	        	TTSChunk chunk = new TTSChunk(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)ttsNameArray.get(index)) );
	        	ttsNameList.add(chunk);
			}
			assertTrue("TTSName list doesn't match input TTSName list",  Validator.validateTtsChunks(ttsNameList, cmd.getTtsName()));
			
			assertEquals("HMI display language desired doesn't match input language desired", 
					parameters.getString(RegisterAppInterface.KEY_HMI_DISPLAY_LANGUAGE_DESIRED), cmd.getHmiDisplayLanguageDesired().toString());
			
			JSONArray appHmiTypeArray = parameters.getJSONArray(RegisterAppInterface.KEY_APP_HMI_TYPE);
			for (int index = 0; index < appHmiTypeArray.length(); index++) {
				AppHMIType appHmiTypeItem = AppHMIType.valueForString( appHmiTypeArray.get(index).toString() );
				assertEquals("App HMI type item doesn't match input HMI type", appHmiTypeItem, cmd.getAppHMIType().get(index) );
			}
			
			assertEquals("App ID doesn't match input ID", parameters.getString(RegisterAppInterface.KEY_APP_ID), cmd.getAppID());
			assertEquals("Language desired doesn't match input language", 
					JsonUtils.readStringFromJsonObject(parameters, RegisterAppInterface.KEY_LANGUAGE_DESIRED), cmd.getLanguageDesired().toString());
			
			JSONObject deviceInfoObj = parameters.getJSONObject(RegisterAppInterface.KEY_DEVICE_INFO);
			DeviceInfo deviceInfo = new DeviceInfo(JsonRPCMarshaller.deserializeJSONObject(deviceInfoObj));
			assertTrue("Device info doesn't match input device info",  Validator.validateDeviceInfo(deviceInfo, cmd.getDeviceInfo()) );
			
			assertEquals("App name doesn't match input name", parameters.getString(RegisterAppInterface.KEY_APP_NAME), cmd.getAppName());
			assertEquals("NGN media screen app name doesn't match input name", 
					parameters.getString(RegisterAppInterface.KEY_NGN_MEDIA_SCREEN_APP_NAME), cmd.getNgnMediaScreenAppName());
			assertEquals("Media application doesn't match input media application", 
					(Boolean) parameters.getBoolean(RegisterAppInterface.KEY_IS_MEDIA_APPLICATION), cmd.getIsMediaApplication());

			List<String> vrSynonymsList = JsonUtils.readStringListFromJsonObject(parameters, RegisterAppInterface.KEY_VR_SYNONYMS);
			List<String> testSynonymsList = cmd.getVrSynonyms();
			assertEquals("VR synonym list length not same as reference VR synonym list", vrSynonymsList.size(), testSynonymsList.size());
			assertTrue("VR synonym list doesn't match input synonym list", Validator.validateStringList(vrSynonymsList, testSynonymsList));
			
			JSONObject sdlMsgVersionObj = parameters.getJSONObject(RegisterAppInterface.KEY_SDL_MSG_VERSION);
			SdlMsgVersion sdlMsgVersion = new SdlMsgVersion(JsonRPCMarshaller.deserializeJSONObject(sdlMsgVersionObj));
			assertTrue("SDL message version doesn't match input version",  Validator.validateSdlMsgVersion(sdlMsgVersion, cmd.getSdlMsgVersion()) );
			
			assertEquals("Hash ID doesn't match input ID", parameters.getString(RegisterAppInterface.KEY_HASH_ID), cmd.getHashID());
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }
	
}
