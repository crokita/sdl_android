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
import com.smartdevicelink.proxy.rpc.Image;
import com.smartdevicelink.proxy.rpc.KeyboardProperties;
import com.smartdevicelink.proxy.rpc.SetGlobalProperties;
import com.smartdevicelink.proxy.rpc.TTSChunk;
import com.smartdevicelink.proxy.rpc.VrHelpItem;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.Validator;

public class SetGlobalPropertiesTest extends BaseRpcTests {

	private String 				helpTitle;
	private String 				menuTitle;
	private Image 				menuIcon;	
	private KeyboardProperties 	keyboardProperties;
	private List<TTSChunk>     	helpPrompt = new ArrayList<TTSChunk>();
	private List<TTSChunk>     	timeoutPrompt = new ArrayList<TTSChunk>();
	private List<VrHelpItem>   	vrHelp = new ArrayList<VrHelpItem>();

	private JSONObject paramsJson;
	
	@Override
	protected RPCMessage createMessage() {
		SetGlobalProperties msg = new SetGlobalProperties();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
		
		try {			
			helpTitle = paramsJson.getString(SetGlobalProperties.KEY_VR_HELP_TITLE);
			msg.setVrHelpTitle(helpTitle);
			menuTitle = paramsJson.getString(SetGlobalProperties.KEY_MENU_TITLE);
			msg.setMenuTitle(menuTitle);
			
			JSONObject menuIconJson = paramsJson.getJSONObject(SetGlobalProperties.KEY_MENU_ICON);
			menuIcon = new Image(JsonRPCMarshaller.deserializeJSONObject(menuIconJson));
			msg.setMenuIcon(menuIcon);
			
			JSONObject keyboardPropertiesJson = paramsJson.getJSONObject(SetGlobalProperties.KEY_KEYBOARD_PROPERTIES);
			keyboardProperties = new KeyboardProperties(JsonRPCMarshaller.deserializeJSONObject(keyboardPropertiesJson));
			msg.setKeyboardProperties(keyboardProperties);
			
			JSONArray helpPromptArray = paramsJson.getJSONArray(SetGlobalProperties.KEY_HELP_PROMPT);
			for (int index = 0; index < helpPromptArray.length(); index++) {
				TTSChunk ttsChunk = new TTSChunk(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)helpPromptArray.get(index)) );
				helpPrompt.add(ttsChunk);
			}
			msg.setHelpPrompt(helpPrompt);
			
			JSONArray timeoutPromptArray = paramsJson.getJSONArray(SetGlobalProperties.KEY_TIMEOUT_PROMPT);
			for (int index = 0; index < timeoutPromptArray.length(); index++) {
				TTSChunk ttsChunk = new TTSChunk(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)timeoutPromptArray.get(index)) );
				timeoutPrompt.add(ttsChunk);
			}
			msg.setTimeoutPrompt(timeoutPrompt);
			
			JSONArray vrHelpArray = paramsJson.getJSONArray(SetGlobalProperties.KEY_VR_HELP);
			for (int index = 0; index < vrHelpArray.length(); index++) {
				VrHelpItem ttsChunk = new VrHelpItem(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)vrHelpArray.get(index)) );
				vrHelp.add(ttsChunk);
			}
			msg.setVrHelp(vrHelp);
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
		return FunctionID.SET_GLOBAL_PROPERTIES;
	}

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();
		
		try {		
			result.put(SetGlobalProperties.KEY_MENU_ICON,      		paramsJson.getJSONObject(SetGlobalProperties.KEY_MENU_ICON));	
			result.put(SetGlobalProperties.KEY_VR_HELP,        		paramsJson.getJSONArray(SetGlobalProperties.KEY_VR_HELP));
			result.put(SetGlobalProperties.KEY_HELP_PROMPT,    		paramsJson.getJSONArray(SetGlobalProperties.KEY_HELP_PROMPT));
			result.put(SetGlobalProperties.KEY_TIMEOUT_PROMPT, 		paramsJson.getJSONArray(SetGlobalProperties.KEY_TIMEOUT_PROMPT));
			result.put(SetGlobalProperties.KEY_MENU_TITLE,			menuTitle);
			result.put(SetGlobalProperties.KEY_VR_HELP_TITLE,	 	helpTitle);							
			result.put(SetGlobalProperties.KEY_KEYBOARD_PROPERTIES, paramsJson.getJSONObject(SetGlobalProperties.KEY_KEYBOARD_PROPERTIES));
			
		} catch (JSONException e) {
			/* do nothing */
		}

		return result;
	}

	public void testHelpPrompt () {
		List<TTSChunk> copy = ( (SetGlobalProperties) msg ).getHelpPrompt();
		
		assertTrue("Input value didn't match expected value.", Validator.validateTtsChunks(helpPrompt, copy));
	}
	
	public void testMenuTitle () {
		String copy = ( (SetGlobalProperties) msg ).getMenuTitle();
		
		assertEquals("Data didn't match input data.", menuTitle, copy);
	}
	
	public void testMenuIcon () {
		Image copy = ( (SetGlobalProperties) msg ).getMenuIcon();
		
		assertTrue("Input value didn't match expected value.", Validator.validateImage(menuIcon, copy));
	}
	
	public void testVrHelp () {
		List<VrHelpItem> copy = ( (SetGlobalProperties) msg ).getVrHelp();
		
		assertTrue("Input value didn't match expected value.", Validator.validateVrHelpItems(vrHelp, copy));
	}
	
	public void testHelpTitle () {
		String copy = ( (SetGlobalProperties) msg ).getVrHelpTitle();
		
		assertEquals("Data didn't match input data.", helpTitle, copy);
	}
	
	public void testTimeoutPrompt () {
		List<TTSChunk> copy = ( (SetGlobalProperties) msg ).getTimeoutPrompt();
		
		assertTrue("Input value didn't match expected value.", Validator.validateTtsChunks(timeoutPrompt, copy));
	}
	
	public void testKeyboardProperties () {
		KeyboardProperties copy = ( (SetGlobalProperties) msg ).getKeyboardProperties();
		
		assertTrue("Input value didn't match expected value.", Validator.validateKeyboardProperties(keyboardProperties, copy));
	}

	public void testNull() {
		SetGlobalProperties msg = new SetGlobalProperties();
		assertNotNull("Null object creation failed.", msg);

		testNullBase(msg);

		assertNull("Menu icon wasn't set, but getter method returned an object.", msg.getMenuIcon());
		assertNull("Menu title wasn't set, but getter method returned an object.", msg.getMenuTitle());
		assertNull("Vr help wasn't set, but getter method returned an object.", msg.getVrHelp());
		assertNull("Help prompt wasn't set, but getter method returned an object.", msg.getHelpPrompt());
		assertNull("Timeout prompt wasn't set, but getter method returned an object.", msg.getTimeoutPrompt());
		assertNull("Keyboard properties wasn't set, but getter method returned an object.", msg.getKeyboardProperties());
		assertNull("Help title wasn't set, but getter method returned an object.", msg.getVrHelpTitle());
	}
	
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			SetGlobalProperties cmd = new SetGlobalProperties(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			assertEquals("VR help title doesn't match input title", 
					parameters.getString(SetGlobalProperties.KEY_VR_HELP_TITLE), cmd.getVrHelpTitle());
			assertEquals("Menu title doesn't match input title", 
					parameters.getString(SetGlobalProperties.KEY_MENU_TITLE), cmd.getMenuTitle());
			
			JSONObject menuIcon = parameters.getJSONObject(SetGlobalProperties.KEY_MENU_ICON);
			Image referenceMenuIcon = new Image(JsonRPCMarshaller.deserializeJSONObject(menuIcon));
			assertTrue("Menu icon doesn't match expected menu icon", Validator.validateImage(referenceMenuIcon, cmd.getMenuIcon()));
			
			JSONObject keyboardProperties = parameters.getJSONObject(SetGlobalProperties.KEY_KEYBOARD_PROPERTIES);
			KeyboardProperties referenceKeyboardProperties = new KeyboardProperties(JsonRPCMarshaller.deserializeJSONObject(keyboardProperties));
			assertTrue("Keyboard properties doesn't match expected properties", 
					Validator.validateKeyboardProperties(referenceKeyboardProperties, cmd.getKeyboardProperties()));
			
			JSONArray helpPromptArray = parameters.getJSONArray(SetGlobalProperties.KEY_HELP_PROMPT);
			List<TTSChunk> helpPromptList = new ArrayList<TTSChunk>();
			for (int index = 0; index < helpPromptArray.length(); index++) {
	        	TTSChunk chunk = new TTSChunk(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)helpPromptArray.get(index)) );
	        	helpPromptList.add(chunk);
			}
			assertTrue("Help prompt doesn't match input help prompt",  Validator.validateTtsChunks(helpPromptList, cmd.getHelpPrompt()));
			
			JSONArray timeoutPromptArray = parameters.getJSONArray(SetGlobalProperties.KEY_TIMEOUT_PROMPT);
			List<TTSChunk> timeoutPromptList = new ArrayList<TTSChunk>();
			for (int index = 0; index < timeoutPromptArray.length(); index++) {
				TTSChunk chunk = new TTSChunk(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)timeoutPromptArray.get(index)) );
				timeoutPromptList.add(chunk);
			}
			assertTrue("Timeout prompt doesn't match input timeout prompt",  Validator.validateTtsChunks(timeoutPromptList, cmd.getTimeoutPrompt()));
			
			JSONArray vrHelpArray = parameters.getJSONArray(SetGlobalProperties.KEY_VR_HELP);
			List<VrHelpItem> vrHelpList = new ArrayList<VrHelpItem>();
			for (int index = 0; index < vrHelpArray.length(); index++) {
				VrHelpItem chunk = new VrHelpItem(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)vrHelpArray.get(index)) );
				vrHelpList.add(chunk);
			}
			assertTrue("VR help list doesn't match input help list",  Validator.validateVrHelpItems(vrHelpList, cmd.getVrHelp()));
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }

}
