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
import com.smartdevicelink.proxy.rpc.ButtonCapabilities;
import com.smartdevicelink.proxy.rpc.DisplayCapabilities;
import com.smartdevicelink.proxy.rpc.PresetBankCapabilities;
import com.smartdevicelink.proxy.rpc.SetDisplayLayoutResponse;
import com.smartdevicelink.proxy.rpc.SoftButtonCapabilities;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.Validator;

public class SetDisplayLayoutResponseTest extends BaseRpcTests {

	private DisplayCapabilities displayCapabilities = new DisplayCapabilities();
	private PresetBankCapabilities presetBankCapabilities = new PresetBankCapabilities();
	private List<ButtonCapabilities> buttonCapabilitiesList = new ArrayList<ButtonCapabilities>();
	private List<SoftButtonCapabilities> softButtonCapabilitiesList = new ArrayList<SoftButtonCapabilities>();
	
	private JSONObject paramsJson;
	
	@Override
	protected RPCMessage createMessage() {		
		SetDisplayLayoutResponse msg = new SetDisplayLayoutResponse();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
		
		try {			
			JSONObject displayCapabilitiesObj = paramsJson.getJSONObject(SetDisplayLayoutResponse.KEY_DISPLAY_CAPABILITIES);
			displayCapabilities = new DisplayCapabilities(JsonRPCMarshaller.deserializeJSONObject(displayCapabilitiesObj));
			msg.setDisplayCapabilities(displayCapabilities);
			
			JSONObject presetBankCapabilitiesObj = paramsJson.getJSONObject(SetDisplayLayoutResponse.KEY_PRESET_BANK_CAPABILITIES);
			presetBankCapabilities = new PresetBankCapabilities(JsonRPCMarshaller.deserializeJSONObject(presetBankCapabilitiesObj));
			msg.setPresetBankCapabilities(presetBankCapabilities);
			
			JSONArray buttonCapabilitiesArray = paramsJson.getJSONArray(SetDisplayLayoutResponse.KEY_BUTTON_CAPABILITIES);
			for (int index = 0; index < buttonCapabilitiesArray.length(); index++) {
				ButtonCapabilities button = new ButtonCapabilities(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)buttonCapabilitiesArray.get(index)) );
				buttonCapabilitiesList.add(button);
			}
			msg.setButtonCapabilities(buttonCapabilitiesList);
			
			JSONArray softButtonCapabilitiesArray = paramsJson.getJSONArray(SetDisplayLayoutResponse.KEY_SOFT_BUTTON_CAPABILITIES);
			for (int index = 0; index < softButtonCapabilitiesArray.length(); index++) {
				SoftButtonCapabilities button = 
						new SoftButtonCapabilities(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)softButtonCapabilitiesArray.get(index)) );
				softButtonCapabilitiesList.add(button);
			}
			msg.setSoftButtonCapabilities(softButtonCapabilitiesList);
			
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
		return FunctionID.SET_DISPLAY_LAYOUT;
	}

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();
		
		try {			
			result.put(SetDisplayLayoutResponse.KEY_DISPLAY_CAPABILITIES, displayCapabilities.serializeJSON());
			result.put(SetDisplayLayoutResponse.KEY_PRESET_BANK_CAPABILITIES, presetBankCapabilities.serializeJSON());
			result.put(SetDisplayLayoutResponse.KEY_BUTTON_CAPABILITIES, paramsJson.getJSONArray(SetDisplayLayoutResponse.KEY_BUTTON_CAPABILITIES));
			result.put(SetDisplayLayoutResponse.KEY_SOFT_BUTTON_CAPABILITIES, paramsJson.getJSONArray(SetDisplayLayoutResponse.KEY_SOFT_BUTTON_CAPABILITIES));
			
		} catch (JSONException e) {
			/* do nothing */
		}

		return result;
	}

	public void testDisplayCapabilities() {
		DisplayCapabilities copy = ( (SetDisplayLayoutResponse) msg ).getDisplayCapabilities();
		
		assertTrue("Input value didn't match expected value.", Validator.validateDisplayCapabilities(displayCapabilities, copy));
	}
	
	public void testPresetBankCapabilities() {
		PresetBankCapabilities copy = ( (SetDisplayLayoutResponse) msg ).getPresetBankCapabilities();
		
		assertTrue("Input value didn't match expected value.", Validator.validatePresetBankCapabilities(presetBankCapabilities, copy));
	}
	
	public void testButtonCapabilities () {
		List<ButtonCapabilities> copy = ( (SetDisplayLayoutResponse) msg ).getButtonCapabilities();
		
		assertEquals("List size didn't match expected size.", buttonCapabilitiesList.size(), copy.size());
		
		for (int i = 0; i < buttonCapabilitiesList.size(); i++) {
			assertEquals("Input value didn't match expected value.", buttonCapabilitiesList.get(i), copy.get(i));
		}
	}
	
	public void testSoftButtonCapabilities () {
		List<SoftButtonCapabilities> copy = ( (SetDisplayLayoutResponse) msg ).getSoftButtonCapabilities();
		
		assertEquals("List size didn't match expected size.", softButtonCapabilitiesList.size(), copy.size());
		
		for (int i = 0; i < softButtonCapabilitiesList.size(); i++) {
			assertEquals("Input value didn't match expected value.", softButtonCapabilitiesList.get(i), copy.get(i));
		}
	}

	public void testNull() {
		SetDisplayLayoutResponse msg = new SetDisplayLayoutResponse();
		assertNotNull("Null object creation failed.", msg);

		testNullBase(msg);

		assertNull("Display capabilities wasn't set, but getter method returned an object.", msg.getDisplayCapabilities());
		assertNull("Preset bank capabilities wasn't set, but getter method returned an object.", msg.getPresetBankCapabilities());
		assertNull("Button capabilities wasn't set, but getter method returned an object.", msg.getButtonCapabilities());
		assertNull("Soft button capabilities wasn't set, but getter method returned an object.", msg.getSoftButtonCapabilities());
	}
	
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			SetDisplayLayoutResponse cmd = new SetDisplayLayoutResponse(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);

			JSONArray buttonCapabilitiesArray = parameters.getJSONArray(SetDisplayLayoutResponse.KEY_BUTTON_CAPABILITIES);
			List<ButtonCapabilities> buttonCapabilitiesList = new ArrayList<ButtonCapabilities>();
			for (int index = 0; index < buttonCapabilitiesArray.length(); index++) {
				ButtonCapabilities buttonCapability = new ButtonCapabilities(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)buttonCapabilitiesArray.get(index) ));
				buttonCapabilitiesList.add(buttonCapability);
			}
			assertTrue("Button capabilities list doesn't match input capabilities list",  
					Validator.validateButtonCapabilities(buttonCapabilitiesList, cmd.getButtonCapabilities() ));
			
			JSONObject displayCapabilitiesObj = parameters.getJSONObject(SetDisplayLayoutResponse.KEY_DISPLAY_CAPABILITIES);
			DisplayCapabilities displayCapabilities = new DisplayCapabilities(JsonRPCMarshaller.deserializeJSONObject(displayCapabilitiesObj));
			assertTrue("Display capabilities doesn't match input capabilities",  Validator.validateDisplayCapabilities(displayCapabilities, cmd.getDisplayCapabilities()) );
			
			JSONArray softButtonCapabilitiesArray = parameters.getJSONArray(SetDisplayLayoutResponse.KEY_SOFT_BUTTON_CAPABILITIES);
			List<SoftButtonCapabilities> softButtonCapabilitiesList = new ArrayList<SoftButtonCapabilities>();
			for (int index = 0; index < softButtonCapabilitiesArray.length(); index++) {
				SoftButtonCapabilities softButtonCapability = 
						new SoftButtonCapabilities(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)softButtonCapabilitiesArray.get(index) ));
				softButtonCapabilitiesList.add(softButtonCapability);
			}
			assertTrue("Soft button capabilities list doesn't match input capabilities list",  
					Validator.validateSoftButtonCapabilities(softButtonCapabilitiesList, cmd.getSoftButtonCapabilities() ));
			
			JSONObject presetBankCapabilitiesObj = parameters.getJSONObject(SetDisplayLayoutResponse.KEY_PRESET_BANK_CAPABILITIES);
			PresetBankCapabilities presetBankCapabilities = new PresetBankCapabilities(JsonRPCMarshaller.deserializeJSONObject(presetBankCapabilitiesObj));
			assertTrue("Preset bank capabilities doesn't match input capabilities",  Validator.validatePresetBankCapabilities(presetBankCapabilities, cmd.getPresetBankCapabilities()) );
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }
	
}
