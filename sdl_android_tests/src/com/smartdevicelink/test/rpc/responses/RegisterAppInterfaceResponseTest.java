package com.smartdevicelink.test.rpc.responses;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.AudioPassThruCapabilities;
import com.smartdevicelink.proxy.rpc.ButtonCapabilities;
import com.smartdevicelink.proxy.rpc.DisplayCapabilities;
import com.smartdevicelink.proxy.rpc.PresetBankCapabilities;
import com.smartdevicelink.proxy.rpc.RegisterAppInterfaceResponse;
import com.smartdevicelink.proxy.rpc.SdlMsgVersion;
import com.smartdevicelink.proxy.rpc.SoftButtonCapabilities;
import com.smartdevicelink.proxy.rpc.VehicleType;
import com.smartdevicelink.proxy.rpc.enums.HmiZoneCapabilities;
import com.smartdevicelink.proxy.rpc.enums.Language;
import com.smartdevicelink.proxy.rpc.enums.PrerecordedSpeech;
import com.smartdevicelink.proxy.rpc.enums.SpeechCapabilities;
import com.smartdevicelink.proxy.rpc.enums.VrCapabilities;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;
import com.smartdevicelink.test.utils.Validator;

public class RegisterAppInterfaceResponseTest extends BaseRpcTests {

	private Language 						language;
	private Language 						hmiLanguage;
	private VehicleType 					vehicleType;
	private SdlMsgVersion 					sdlMsgVersion;
	private DisplayCapabilities 			displayCapabilities;
	private PresetBankCapabilities 			presetBankCapabilities;
	
	private List<Integer> 					supportedDiagModes;
	private List<SpeechCapabilities> 		speechCapabilities = new ArrayList<SpeechCapabilities>();
	private List<VrCapabilities> 			vrCapabilities = new ArrayList<VrCapabilities>();
	private List<HmiZoneCapabilities>		hmiZoneCapabilities = new ArrayList<HmiZoneCapabilities>();
	private List<PrerecordedSpeech> 		prerecordedSpeech = new ArrayList<PrerecordedSpeech>();
	
	private List<AudioPassThruCapabilities> audioPassThruCapabilities = new ArrayList<AudioPassThruCapabilities>();
    private List<ButtonCapabilities> 		buttonCapabilities= new ArrayList<ButtonCapabilities>();
	private List<SoftButtonCapabilities> 	softButtonCapabilities= new ArrayList<SoftButtonCapabilities>();
	
	private JSONObject paramsJson;
    
	@Override
	protected RPCMessage createMessage() {
		RegisterAppInterfaceResponse msg = new RegisterAppInterfaceResponse();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
		
		try {			
			language = Language.valueForString(paramsJson.getString(RegisterAppInterfaceResponse.KEY_LANGUAGE));
			msg.setLanguage(language);
			hmiLanguage = Language.valueForString(paramsJson.getString(RegisterAppInterfaceResponse.KEY_HMI_DISPLAY_LANGUAGE));
			msg.setHmiDisplayLanguage(hmiLanguage);
			
			JSONObject vehicleTypeObj = paramsJson.getJSONObject(RegisterAppInterfaceResponse.KEY_VEHICLE_TYPE);
			vehicleType = new VehicleType(JsonRPCMarshaller.deserializeJSONObject(vehicleTypeObj));
			msg.setVehicleType(vehicleType);
			
			JSONObject sdlMsgVersionObj = paramsJson.getJSONObject(RegisterAppInterfaceResponse.KEY_SDL_MSG_VERSION);
			sdlMsgVersion = new SdlMsgVersion(JsonRPCMarshaller.deserializeJSONObject(sdlMsgVersionObj));
			msg.setSdlMsgVersion(sdlMsgVersion);
			
			JSONObject displayCapabilitiesObj = paramsJson.getJSONObject(RegisterAppInterfaceResponse.KEY_DISPLAY_CAPABILITIES);
			displayCapabilities = new DisplayCapabilities(JsonRPCMarshaller.deserializeJSONObject(displayCapabilitiesObj));
			msg.setDisplayCapabilities(displayCapabilities);
			
			JSONObject presetBankCapabilitiesObj = paramsJson.getJSONObject(RegisterAppInterfaceResponse.KEY_PRESET_BANK_CAPABILITIES);
			presetBankCapabilities = new PresetBankCapabilities(JsonRPCMarshaller.deserializeJSONObject(presetBankCapabilitiesObj));
			msg.setPresetBankCapabilities(presetBankCapabilities);
			
			JSONArray supportedDiagModesArray = paramsJson.getJSONArray(RegisterAppInterfaceResponse.KEY_SUPPORTED_DIAG_MODES);
			supportedDiagModes = JsonUtils.<Integer>createListFromJsonArray(supportedDiagModesArray);
			msg.setSupportedDiagModes(supportedDiagModes);
			
			JSONArray speechCapabilitiesArray = paramsJson.getJSONArray(RegisterAppInterfaceResponse.KEY_SPEECH_CAPABILITIES);
			speechCapabilities = JsonUtils.<SpeechCapabilities>createListFromJsonArray(speechCapabilitiesArray);			
			msg.setSpeechCapabilities(speechCapabilities);
			
			JSONArray vrCapabilitiesArray = paramsJson.getJSONArray(RegisterAppInterfaceResponse.KEY_VR_CAPABILITIES);
			vrCapabilities = JsonUtils.<VrCapabilities>createListFromJsonArray(vrCapabilitiesArray);			
			msg.setVrCapabilities(vrCapabilities);
			
			JSONArray hmiZoneCapabilitiesArray = paramsJson.getJSONArray(RegisterAppInterfaceResponse.KEY_HMI_ZONE_CAPABILITIES);
			hmiZoneCapabilities = JsonUtils.<HmiZoneCapabilities>createListFromJsonArray(hmiZoneCapabilitiesArray);			
			msg.setHmiZoneCapabilities(hmiZoneCapabilities);
			
			JSONArray prerecordedSpeechArray = paramsJson.getJSONArray(RegisterAppInterfaceResponse.KEY_PRERECORDED_SPEECH);
			prerecordedSpeech = JsonUtils.<PrerecordedSpeech>createListFromJsonArray(prerecordedSpeechArray);			
			msg.setPrerecordedSpeech(prerecordedSpeech);
			
			JSONArray audioPassThruCapabilitiesArray = paramsJson.getJSONArray(RegisterAppInterfaceResponse.KEY_AUDIO_PASS_THRU_CAPABILITIES);
			for (int index = 0; index < audioPassThruCapabilitiesArray.length(); index++) {
				AudioPassThruCapabilities audioPassThru = 
						new AudioPassThruCapabilities(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)audioPassThruCapabilitiesArray.get(index)) );
				audioPassThruCapabilities.add(audioPassThru);
			}
			msg.setAudioPassThruCapabilities(audioPassThruCapabilities);
			
			JSONArray buttonCapabilitiesArray = paramsJson.getJSONArray(RegisterAppInterfaceResponse.KEY_BUTTON_CAPABILITIES);
			for (int index = 0; index < buttonCapabilitiesArray.length(); index++) {
				ButtonCapabilities button = 
						new ButtonCapabilities(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)buttonCapabilitiesArray.get(index)) );
				buttonCapabilities.add(button);
			}
			msg.setButtonCapabilities(buttonCapabilities);
			
			JSONArray softButtonCapabilitiesArray = paramsJson.getJSONArray(RegisterAppInterfaceResponse.KEY_SOFT_BUTTON_CAPABILITIES);
			for (int index = 0; index < softButtonCapabilitiesArray.length(); index++) {
				SoftButtonCapabilities button = 
						new SoftButtonCapabilities(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)softButtonCapabilitiesArray.get(index)) );
				softButtonCapabilities.add(button);
			}
			msg.setSoftButtonCapabilities(softButtonCapabilities);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return msg;
	}

	@Override
	protected String getMessageType() {
		return RPCMessage.KEY_RESPONSE;
	}
	
	@Override
	protected String getCommandType() {
		return FunctionID.REGISTER_APP_INTERFACE;
	}
	

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();
		
		try {		
			result.put(RegisterAppInterfaceResponse.KEY_LANGUAGE, language);
			result.put(RegisterAppInterfaceResponse.KEY_HMI_DISPLAY_LANGUAGE, hmiLanguage);
			result.put(RegisterAppInterfaceResponse.KEY_SUPPORTED_DIAG_MODES, JsonUtils.createJsonArray(supportedDiagModes));
			result.put(RegisterAppInterfaceResponse.KEY_SDL_MSG_VERSION, sdlMsgVersion.serializeJSON());
			result.put(RegisterAppInterfaceResponse.KEY_VEHICLE_TYPE, vehicleType.serializeJSON());
			result.put(RegisterAppInterfaceResponse.KEY_PRESET_BANK_CAPABILITIES, presetBankCapabilities.serializeJSON());
			result.put(RegisterAppInterfaceResponse.KEY_DISPLAY_CAPABILITIES, paramsJson.getJSONObject(RegisterAppInterfaceResponse.KEY_DISPLAY_CAPABILITIES));	
			result.put(RegisterAppInterfaceResponse.KEY_BUTTON_CAPABILITIES, paramsJson.getJSONArray(RegisterAppInterfaceResponse.KEY_BUTTON_CAPABILITIES));
			result.put(RegisterAppInterfaceResponse.KEY_SOFT_BUTTON_CAPABILITIES, paramsJson.getJSONArray(RegisterAppInterfaceResponse.KEY_SOFT_BUTTON_CAPABILITIES));
			result.put(RegisterAppInterfaceResponse.KEY_AUDIO_PASS_THRU_CAPABILITIES, paramsJson.getJSONArray(RegisterAppInterfaceResponse.KEY_AUDIO_PASS_THRU_CAPABILITIES));				
			result.put(RegisterAppInterfaceResponse.KEY_SPEECH_CAPABILITIES, paramsJson.getJSONArray(RegisterAppInterfaceResponse.KEY_SPEECH_CAPABILITIES));
			result.put(RegisterAppInterfaceResponse.KEY_VR_CAPABILITIES, paramsJson.getJSONArray(RegisterAppInterfaceResponse.KEY_VR_CAPABILITIES));	
			result.put(RegisterAppInterfaceResponse.KEY_HMI_ZONE_CAPABILITIES, paramsJson.getJSONArray(RegisterAppInterfaceResponse.KEY_HMI_ZONE_CAPABILITIES));
			result.put(RegisterAppInterfaceResponse.KEY_PRERECORDED_SPEECH, paramsJson.getJSONArray(RegisterAppInterfaceResponse.KEY_PRERECORDED_SPEECH));
		} catch (JSONException e) {
			/* do nothing */
		}

		return result;
	}

	public void testSdlMsgVersion () {
		SdlMsgVersion copy = ( (RegisterAppInterfaceResponse) msg ).getSdlMsgVersion();
		
		assertTrue("Data didn't match input data.", Validator.validateSdlMsgVersion(sdlMsgVersion, copy));
	}
	
	public void testLanguage () {
		Language copy = ( (RegisterAppInterfaceResponse) msg ).getLanguage();
		
		assertEquals("Data didn't match input data.", language, copy);
	}
	
	public void testHmiLanguage () {
		Language copy = ( (RegisterAppInterfaceResponse) msg ).getHmiDisplayLanguage();
		
		assertEquals("Data didn't match input data.", hmiLanguage, copy);
	}
	
	public void testDisplayCapabilities () {
		DisplayCapabilities copy = ( (RegisterAppInterfaceResponse) msg ).getDisplayCapabilities();
		
		assertNotNull("Display capabilities prompt were null.", copy);
		assertTrue("Data didn't match input data.", Validator.validateDisplayCapabilities(displayCapabilities, copy));
	}
	
	public void testPresetBankCapabilities () {
		PresetBankCapabilities copy = ( (RegisterAppInterfaceResponse) msg ).getPresetBankCapabilities();
		
		assertTrue("Data didn't match input data.", Validator.validatePresetBankCapabilities(presetBankCapabilities, copy));
	}
	
	public void testVehicleType () {
		VehicleType copy = ( (RegisterAppInterfaceResponse) msg ).getVehicleType();
		
		assertTrue("Data didn't match input data.", Validator.validateVehicleType(vehicleType, copy));
	}
	
	public void testButtonCapabilities () {
		List<ButtonCapabilities> copy = ( (RegisterAppInterfaceResponse) msg ).getButtonCapabilities();
		
		assertNotNull("Button capabilties were null.", copy);
		assertTrue("Button capabilities didn't match input.", Validator.validateButtonCapabilities(buttonCapabilities, copy));
	}
	
	public void testSoftButtonCapabilities () {
		List<SoftButtonCapabilities> copy = ( (RegisterAppInterfaceResponse) msg ).getSoftButtonCapabilities();
		
		assertNotNull("Soft button capabilties were null.", copy);
		assertTrue("Soft button capabilities didn't match input.", Validator.validateSoftButtonCapabilities(softButtonCapabilities, copy));
	}

	public void testAudioPassThruCapabilities () {
		List<AudioPassThruCapabilities> copy = ( (RegisterAppInterfaceResponse) msg ).getAudioPassThruCapabilities();
		
		assertNotNull("Audio pass thru capabilties were null.", copy);
		assertTrue("Audio pass thru capabilities didn't match input.", Validator.validateAudioPassThruCapabilities(audioPassThruCapabilities, copy));
		
	}
	
	public void testHmiZoneCapabilities () {
		List<HmiZoneCapabilities> copy = ( (RegisterAppInterfaceResponse) msg ).getHmiZoneCapabilities();
		
        assertNotNull("HMI zone capability list was null.", copy);
        assertEquals("HMI zone capability list size doesn't match input size.", hmiZoneCapabilities.size(), copy.size());
        
        for (int index = 0; index < copy.size(); index++) {
        	assertEquals("Data didn't match input data.", hmiZoneCapabilities.get(index), copy.get(index).toString());
        }
        
	}
	
	public void testSpeechCapabilities () {
		List<SpeechCapabilities> copy = ( (RegisterAppInterfaceResponse) msg ).getSpeechCapabilities();
		
        assertNotNull("Speech capability list was null.", copy);
        assertEquals("Speech capability list size doesn't match input size.", speechCapabilities.size(), copy.size());
        
        for (int index = 0; index < copy.size(); index++) {
        	assertEquals("Data didn't match input data.", speechCapabilities.get(index), copy.get(index).toString());
        }
        
	}
	
	public void testVrCapabilities () {
		List<VrCapabilities> copy = ( (RegisterAppInterfaceResponse) msg ).getVrCapabilities();
		
        assertNotNull("VR capability list was null.", copy);
        assertEquals("VR capability list size doesn't match input size.", vrCapabilities.size(), copy.size());
        
        for (int index = 0; index < copy.size(); index++) {
        	assertEquals("Data didn't match input data.", vrCapabilities.get(index), copy.get(index).toString());
        }
        
	}
	
	public void testPrerecordedSpeech () {
		List<PrerecordedSpeech> copy = ( (RegisterAppInterfaceResponse) msg ).getPrerecordedSpeech();
		
        assertNotNull("Prerecorded speech list was null.", copy);
        assertEquals("Prerecorded speech list size doesn't match input size.", prerecordedSpeech.size(), copy.size());
        
        for (int index = 0; index < copy.size(); index++) {
        	assertEquals("Data didn't match input data.", prerecordedSpeech.get(index), copy.get(index).toString());
        }
        
	}
	
	public void testSupportedDiagModes () {
		List<Integer> copy = ( (RegisterAppInterfaceResponse) msg ).getSupportedDiagModes();
		
		assertEquals("Supported diagnostic modes list size doesn't match input size.", supportedDiagModes.size(), copy.size());
		assertTrue("Supported diagnostic modes not the same", Validator.validateIntegerList(supportedDiagModes, copy));
	}
	
	public void testNull() {
		RegisterAppInterfaceResponse msg = new RegisterAppInterfaceResponse();
		assertNotNull("Null object creation failed.", msg);

		testNullBase(msg);

		assertNull("Sdl message version wasn't set, but getter method returned an object.", msg.getSdlMsgVersion());
		assertNull("Language wasn't set, but getter method returned an object.", msg.getLanguage());
		assertNull("Hmi language wasn't set, but getter method returned an object.", msg.getHmiDisplayLanguage());
		assertNull("Display capabilities wasn't set, but getter method returned an object.", msg.getDisplayCapabilities());
		assertNull("Preset bank capabilities wasn't set, but getter method returned an object.", msg.getPresetBankCapabilities());
		assertNull("Vehicle type wasn't set, but getter method returned an object.", msg.getVehicleType());
		assertNull("Button capabilities wasn't set, but getter method returned an object.", msg.getButtonCapabilities());
		assertNull("Soft button capabilities wasn't set, but getter method returned an object.", msg.getSoftButtonCapabilities());
		assertNull("Audio pass thru capabilities wasn't set, but getter method returned an object.", msg.getAudioPassThruCapabilities());
		assertNull("Hmi zone capabilities wasn't set, but getter method returned an object.", msg.getHmiZoneCapabilities());
		assertNull("Speech capabilities wasn't set, but getter method returned an object.", msg.getSpeechCapabilities());
		assertNull("Vr capabilities wasn't set, but getter method returned an object.", msg.getVrCapabilities());
		assertNull("Prerecorded speech wasn't set, but getter method returned an object.", msg.getPrerecordedSpeech());
		assertNull("Supported diag modes wasn't set, but getter method returned an object.", msg.getSupportedDiagModes());
	}
	
	//TODO: what to do about getProxyVersionInfo?
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			RegisterAppInterfaceResponse cmd = new RegisterAppInterfaceResponse(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			
			JSONObject vehicleTypeObj = parameters.getJSONObject(RegisterAppInterfaceResponse.KEY_VEHICLE_TYPE);
			VehicleType vehicleType = new VehicleType(JsonRPCMarshaller.deserializeJSONObject(vehicleTypeObj));
			assertTrue("Vehicle type doesn't match input vehicle type",  Validator.validateVehicleType(vehicleType, cmd.getVehicleType()));
			
			JSONArray speechCapabilitiesArray = parameters.getJSONArray(RegisterAppInterfaceResponse.KEY_SPEECH_CAPABILITIES);
			for (int index = 0; index < speechCapabilitiesArray.length(); index++) {
				SpeechCapabilities speechCapability = SpeechCapabilities.valueForString( speechCapabilitiesArray.get(index).toString() );
				assertEquals("Speech capabilities item doesn't match input capabilities item", speechCapability, cmd.getSpeechCapabilities().get(index));
			}
			
			JSONArray vrCapabilitiesArray = parameters.getJSONArray(RegisterAppInterfaceResponse.KEY_VR_CAPABILITIES);
			for (int index = 0; index < vrCapabilitiesArray.length(); index++) {
				VrCapabilities vrCapability = VrCapabilities.valueForString( vrCapabilitiesArray.get(index).toString() );
				assertEquals("VR capabilities item doesn't match input capabilities item", vrCapability, cmd.getVrCapabilities().get(index));
			}
			
			JSONArray audioPassThruCapabilitiesArray = parameters.getJSONArray(RegisterAppInterfaceResponse.KEY_AUDIO_PASS_THRU_CAPABILITIES);
			List<AudioPassThruCapabilities> audioPassThruCapabilitiesList = new ArrayList<AudioPassThruCapabilities>();
			for (int index = 0; index < audioPassThruCapabilitiesArray.length(); index++) {
				AudioPassThruCapabilities audioPassThruCapability = 
						new AudioPassThruCapabilities(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)audioPassThruCapabilitiesArray.get(index) ));
				audioPassThruCapabilitiesList.add(audioPassThruCapability);
			}
			assertTrue("Audio pass-through capabilities list doesn't match input capabilities list",  
					Validator.validateAudioPassThruCapabilities(audioPassThruCapabilitiesList, cmd.getAudioPassThruCapabilities() ));
			
			JSONArray hmiZoneCapabilitiesArray = parameters.getJSONArray(RegisterAppInterfaceResponse.KEY_HMI_ZONE_CAPABILITIES);
			for (int index = 0; index < hmiZoneCapabilitiesArray.length(); index++) {
				HmiZoneCapabilities hmiZoneCapability = HmiZoneCapabilities.valueForString( hmiZoneCapabilitiesArray.get(index).toString() );
				assertEquals("HMI zone capabilities item doesn't match input capabilities item", hmiZoneCapability, cmd.getHmiZoneCapabilities().get(index));
			}
			
			JSONArray prerecordedSpeechArray = parameters.getJSONArray(RegisterAppInterfaceResponse.KEY_PRERECORDED_SPEECH);
			for (int index = 0; index < prerecordedSpeechArray.length(); index++) {
				PrerecordedSpeech prerecordedSpeech = PrerecordedSpeech.valueForString( prerecordedSpeechArray.get(index).toString() );
				assertEquals("Pre-recorded speech item doesn't match input speech item", prerecordedSpeech, cmd.getPrerecordedSpeech().get(index));
			}
			
			List<Integer> supportedDiagnosticModesList = JsonUtils.readIntegerListFromJsonObject(parameters, RegisterAppInterfaceResponse.KEY_SUPPORTED_DIAG_MODES);
			List<Integer> testDiagnosticModesList = cmd.getSupportedDiagModes();
			assertEquals("Supported diagnostic modes list length not same as reference modes list length", supportedDiagnosticModesList.size(), testDiagnosticModesList.size());
			assertTrue("Supported diagnostic modes list doesn't match input modes list", Validator.validateIntegerList(supportedDiagnosticModesList, testDiagnosticModesList));
			
			JSONObject sdlMsgVersionObj = parameters.getJSONObject(RegisterAppInterfaceResponse.KEY_SDL_MSG_VERSION);
			SdlMsgVersion sdlMsgVersion = new SdlMsgVersion(JsonRPCMarshaller.deserializeJSONObject(sdlMsgVersionObj));
			assertTrue("SDL message version doesn't match input version",  Validator.validateSdlMsgVersion(sdlMsgVersion, cmd.getSdlMsgVersion()) );
			
			assertEquals("Language doesn't match input language", 
					JsonUtils.readStringFromJsonObject(parameters, RegisterAppInterfaceResponse.KEY_LANGUAGE), cmd.getLanguage().toString());
			
			JSONArray buttonCapabilitiesArray = parameters.getJSONArray(RegisterAppInterfaceResponse.KEY_BUTTON_CAPABILITIES);
			List<ButtonCapabilities> buttonCapabilitiesList = new ArrayList<ButtonCapabilities>();
			for (int index = 0; index < buttonCapabilitiesArray.length(); index++) {
				ButtonCapabilities buttonCapability = new ButtonCapabilities(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)buttonCapabilitiesArray.get(index) ));
				buttonCapabilitiesList.add(buttonCapability);
			}
			assertTrue("Button capabilities list doesn't match input capabilities list",  
					Validator.validateButtonCapabilities(buttonCapabilitiesList, cmd.getButtonCapabilities() ));
			
			JSONObject displayCapabilitiesObj = parameters.getJSONObject(RegisterAppInterfaceResponse.KEY_DISPLAY_CAPABILITIES);
			DisplayCapabilities displayCapabilities = new DisplayCapabilities(JsonRPCMarshaller.deserializeJSONObject(displayCapabilitiesObj));
			assertTrue("Display capabilities doesn't match input capabilities",  Validator.validateDisplayCapabilities(displayCapabilities, cmd.getDisplayCapabilities()) );
			
			assertEquals("HMI Language doesn't match input language", 
					JsonUtils.readStringFromJsonObject(parameters, RegisterAppInterfaceResponse.KEY_HMI_DISPLAY_LANGUAGE), cmd.getHmiDisplayLanguage().toString());
			
			JSONArray softButtonCapabilitiesArray = parameters.getJSONArray(RegisterAppInterfaceResponse.KEY_SOFT_BUTTON_CAPABILITIES);
			List<SoftButtonCapabilities> softButtonCapabilitiesList = new ArrayList<SoftButtonCapabilities>();
			for (int index = 0; index < softButtonCapabilitiesArray.length(); index++) {
				SoftButtonCapabilities softButtonCapability = 
						new SoftButtonCapabilities(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)softButtonCapabilitiesArray.get(index) ));
				softButtonCapabilitiesList.add(softButtonCapability);
			}
			assertTrue("Soft button capabilities list doesn't match input capabilities list",  
					Validator.validateSoftButtonCapabilities(softButtonCapabilitiesList, cmd.getSoftButtonCapabilities() ));
			
			JSONObject presetBankCapabilitiesObj = parameters.getJSONObject(RegisterAppInterfaceResponse.KEY_PRESET_BANK_CAPABILITIES);
			PresetBankCapabilities presetBankCapabilities = new PresetBankCapabilities(JsonRPCMarshaller.deserializeJSONObject(presetBankCapabilitiesObj));
			assertTrue("Preset bank capabilities doesn't match input capabilities",  Validator.validatePresetBankCapabilities(presetBankCapabilities, cmd.getPresetBankCapabilities()) );
			
			

		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }
}
