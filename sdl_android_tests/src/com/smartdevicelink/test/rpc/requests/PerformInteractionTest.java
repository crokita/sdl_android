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
import com.smartdevicelink.proxy.rpc.PerformInteraction;
import com.smartdevicelink.proxy.rpc.TTSChunk;
import com.smartdevicelink.proxy.rpc.VrHelpItem;
import com.smartdevicelink.proxy.rpc.enums.InteractionMode;
import com.smartdevicelink.proxy.rpc.enums.LayoutMode;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;
import com.smartdevicelink.test.utils.Validator;

public class PerformInteractionTest extends BaseRpcTests {

	private String           				initialText;
	private InteractionMode  				mode;	
	private LayoutMode       				interactionLayout;
	private int          					timeout;
	private List<Integer>    				choiceSetIds     	= new ArrayList<Integer>();
	private List<TTSChunk>   				initialPrompt 		= new ArrayList<TTSChunk>();
	private List<TTSChunk>   				helpPrompt 			= new ArrayList<TTSChunk>();
	private List<TTSChunk>   				timeoutPrompt 		= new ArrayList<TTSChunk>();
	private List<VrHelpItem> 				vrHelp 				= new ArrayList<VrHelpItem>();	
	
	private JSONObject paramsJson;
	
	@Override
	protected RPCMessage createMessage() {
		PerformInteraction msg = new PerformInteraction();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
		
		try {			
			initialText = paramsJson.getString(PerformInteraction.KEY_INITIAL_TEXT);
			msg.setInitialText(initialText);
			mode = InteractionMode.valueForString(paramsJson.getString(PerformInteraction.KEY_INTERACTION_MODE));
			msg.setInteractionMode(mode);
			interactionLayout = LayoutMode.valueForString(paramsJson.getString(PerformInteraction.KEY_INTERACTION_LAYOUT));
			msg.setInteractionLayout(interactionLayout);
			timeout = paramsJson.getInt(PerformInteraction.KEY_TIMEOUT);
			msg.setTimeout(timeout);

			JSONArray choiceSetIdsArray = paramsJson.getJSONArray(PerformInteraction.KEY_INTERACTION_CHOICE_SET_ID_LIST);
			choiceSetIds = JsonUtils.<Integer>createListFromJsonArray(choiceSetIdsArray);
			msg.setInteractionChoiceSetIDList(choiceSetIds);
			
			JSONArray initialPromptArray = paramsJson.getJSONArray(PerformInteraction.KEY_INITIAL_PROMPT);
			for (int index = 0; index < initialPromptArray.length(); index++) {
				TTSChunk ttsChunk = new TTSChunk(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)initialPromptArray.get(index)) );
				initialPrompt.add(ttsChunk);
			}
			msg.setInitialPrompt(initialPrompt);
			
			JSONArray helpPromptArray = paramsJson.getJSONArray(PerformInteraction.KEY_HELP_PROMPT);
			for (int index = 0; index < helpPromptArray.length(); index++) {
				TTSChunk ttsChunk = new TTSChunk(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)helpPromptArray.get(index)) );
				helpPrompt.add(ttsChunk);
			}
			msg.setHelpPrompt(helpPrompt);
			
			JSONArray timeoutPromptArray = paramsJson.getJSONArray(PerformInteraction.KEY_TIMEOUT_PROMPT);
			for (int index = 0; index < timeoutPromptArray.length(); index++) {
				TTSChunk ttsChunk = new TTSChunk(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)timeoutPromptArray.get(index)) );
				timeoutPrompt.add(ttsChunk);
			}
			msg.setTimeoutPrompt(timeoutPrompt);
			
			JSONArray vrHelpArray = paramsJson.getJSONArray(PerformInteraction.KEY_VR_HELP);
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
		return FunctionID.PERFORM_INTERACTION;
	}

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();

		try {			
			result.put(PerformInteraction.KEY_INITIAL_PROMPT,     paramsJson.getJSONArray(PerformInteraction.KEY_INITIAL_PROMPT));
			result.put(PerformInteraction.KEY_HELP_PROMPT,        paramsJson.getJSONArray(PerformInteraction.KEY_HELP_PROMPT));
			result.put(PerformInteraction.KEY_TIMEOUT_PROMPT,     paramsJson.getJSONArray(PerformInteraction.KEY_TIMEOUT_PROMPT));
			result.put(PerformInteraction.KEY_VR_HELP,            paramsJson.getJSONArray(PerformInteraction.KEY_VR_HELP));
			result.put(PerformInteraction.KEY_INTERACTION_CHOICE_SET_ID_LIST, paramsJson.getJSONArray(PerformInteraction.KEY_INTERACTION_CHOICE_SET_ID_LIST));
			result.put(PerformInteraction.KEY_INTERACTION_LAYOUT, interactionLayout);
			result.put(PerformInteraction.KEY_INITIAL_TEXT,       initialText);
			result.put(PerformInteraction.KEY_INTERACTION_MODE,   mode);
			result.put(PerformInteraction.KEY_TIMEOUT,            timeout);
			
		} catch (JSONException e) {
			/* do nothing */
		}

		return result;
	}

	public void testInitialPrompt() {
		List<TTSChunk> copy = ( (PerformInteraction) msg).getInitialPrompt();
		
		assertNotNull("Initial prompt were null.", copy);
		assertTrue("Intial prompt didn't match input TTS chunks.", Validator.validateTtsChunks(initialPrompt, copy));
	}
	
	public void testHelpPrompt () {
		List<TTSChunk> copy = ( (PerformInteraction) msg).getHelpPrompt();
		
		assertNotNull("Help prompt were null.", copy);
		assertTrue("Help prompt didn't match input TTS chunks.", Validator.validateTtsChunks(helpPrompt, copy));
	}
	
	public void testTimeoutPrompt () {
		List<TTSChunk> copy = ( (PerformInteraction) msg).getTimeoutPrompt();
		
		assertNotNull("Timeout prompt were null.", copy);
		assertTrue("Timeout prompt didn't match input TTS chunks.", Validator.validateTtsChunks(timeoutPrompt, copy));
	}
	
	public void testVrHelp () {
		List<VrHelpItem> copy = ( (PerformInteraction) msg).getVrHelp();
		
		assertNotNull("Vr help were null.", copy);
		assertTrue("Vr help items didn't match input data.", Validator.validateVrHelpItems(vrHelp, copy));
	}
	
	public void testChoiceSetIds () {
		List<Integer> copy = ( (PerformInteraction) msg).getInteractionChoiceSetIDList();
		
		assertEquals("Choice set ids didn't match input data.", choiceSetIds, copy);
	}
	
	public void testInteractionLayout () {
		LayoutMode copy = ( (PerformInteraction) msg).getInteractionLayout();
		
		assertEquals("Interaction layout didn't match input data.", interactionLayout, copy);
	}
	
	public void testInitialText () {
		String copy = ( (PerformInteraction) msg).getInitialText();
		
		assertEquals("Initial text didn't match input data.", initialText, copy);
	}

	public void testInteractionMode () {
		InteractionMode copy = ( (PerformInteraction) msg).getInteractionMode();
		
		assertEquals("Interaction mode didn't match input data.", mode, copy);
	}
	
	public void testTimeout () {
		Integer copy = ( (PerformInteraction) msg).getTimeout();
		
		assertEquals("Timeout didn't match input data.", (Integer) timeout, copy);
	}
	
	public void testNull() {
		PerformInteraction msg = new PerformInteraction();
		assertNotNull("Null object creation failed.", msg);

		testNullBase(msg);

		assertNull("Initial prompt wasn't set, but getter method returned an object.",     msg.getInitialPrompt());
		assertNull("Help prompt wasn't set, but getter method returned an object.",        msg.getHelpPrompt());
		assertNull("Timeout prompt wasn't set, but getter method returned an object.",     msg.getTimeoutPrompt());
		assertNull("Vr help wasn't set, but getter method returned an object.",            msg.getVrHelp());
		assertNull("Choice set ids wasn't set, but getter method returned an object.",     msg.getInteractionChoiceSetIDList());
		assertNull("Interaction layout wasn't set, but getter method returned an object.", msg.getInteractionLayout());
		assertNull("Initial text wasn't set, but getter method returned an object.",       msg.getInitialText());
		assertNull("Mode wasn't set, but getter method returned an object.",               msg.getInteractionMode());
		assertNull("Timeout wasn't set, but getter method returned an object.",            msg.getTimeout());
	}
	
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			PerformInteraction cmd = new PerformInteraction(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			assertEquals("Initial text doesn't match input text", 
					parameters.getString(PerformInteraction.KEY_INITIAL_TEXT), cmd.getInitialText());
			assertEquals("Interaction mode doesn't match input mode", 
					parameters.getString(PerformInteraction.KEY_INTERACTION_MODE), cmd.getInteractionMode().toString());

			List<Integer> interactionIDList = JsonUtils.readIntegerListFromJsonObject(parameters, PerformInteraction.KEY_INTERACTION_CHOICE_SET_ID_LIST);
			List<Integer> testIDList = cmd.getInteractionChoiceSetIDList();
			assertEquals("Interaction choice set ID list length not same as reference choice set ID list", interactionIDList.size(), testIDList.size());
			assertTrue("Interaction choice set ID list doesn't match input ID list", Validator.validateIntegerList(interactionIDList, testIDList));
			
			assertEquals("Interaction layout doesn't match input layout", 
					parameters.getString(PerformInteraction.KEY_INTERACTION_LAYOUT), cmd.getInteractionLayout().toString());
			
			JSONArray initalPromptArray = parameters.getJSONArray(PerformInteraction.KEY_INITIAL_PROMPT);
			List<TTSChunk> initalPromptList = new ArrayList<TTSChunk>();
			for (int index = 0; index < initalPromptArray.length(); index++) {
	        	TTSChunk chunk = new TTSChunk(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)initalPromptArray.get(index)) );
	        	initalPromptList.add(chunk);
			}
			assertTrue("Initial prompt list doesn't match input prompt list",  Validator.validateTtsChunks(initalPromptList, cmd.getInitialPrompt()));
			
			JSONArray helpPromptArray = parameters.getJSONArray(PerformInteraction.KEY_HELP_PROMPT);
			List<TTSChunk> helpPromptList = new ArrayList<TTSChunk>();
			for (int index = 0; index < helpPromptArray.length(); index++) {
	        	TTSChunk chunk = new TTSChunk(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)helpPromptArray.get(index)) );
	        	helpPromptList.add(chunk);
			}
			assertTrue("Help prompt list doesn't match input prompt list",  Validator.validateTtsChunks(helpPromptList, cmd.getHelpPrompt()));
			
			JSONArray timeoutPromptArray = parameters.getJSONArray(PerformInteraction.KEY_TIMEOUT_PROMPT);
			List<TTSChunk> timeoutPromptList = new ArrayList<TTSChunk>();
			for (int index = 0; index < timeoutPromptArray.length(); index++) {
	        	TTSChunk chunk = new TTSChunk(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)timeoutPromptArray.get(index)) );
	        	timeoutPromptList.add(chunk);
			}
			assertTrue("Timeout prompt list doesn't match input prompt list",  Validator.validateTtsChunks(timeoutPromptList, cmd.getTimeoutPrompt()));
			
			assertEquals("Timeout doesn't match input timeout", (Integer) parameters.getInt(PerformInteraction.KEY_TIMEOUT), cmd.getTimeout());
			
			JSONArray vrHelpArray = parameters.getJSONArray(PerformInteraction.KEY_VR_HELP);
			List<VrHelpItem> vrHelpList= new ArrayList<VrHelpItem>();
			for (int index = 0; index < vrHelpArray.length(); index++) {
				VrHelpItem vrHelpItem = new VrHelpItem(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)vrHelpArray.get(index)) );
				vrHelpList.add(vrHelpItem);
			}
			assertTrue("VR help item doesn't match input item",  Validator.validateVrHelpItems(vrHelpList, cmd.getVrHelp()) );

		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }
    
}
