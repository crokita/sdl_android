package com.smartdevicelink.test.rpc.responses;

import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.PerformInteractionResponse;
import com.smartdevicelink.proxy.rpc.enums.TriggerSource;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;

public class PerformInteractionResponseTest extends BaseRpcTests {

	private int      	 	choiceId;
	private TriggerSource 	triggerSource;
	private String        	manualTextEntry;
    
	private JSONObject paramsJson;
	
	@Override
	protected RPCMessage createMessage() {
		PerformInteractionResponse msg = new PerformInteractionResponse();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
		
		choiceId = JsonUtils.readIntegerFromJsonObject(paramsJson, PerformInteractionResponse.KEY_CHOICE_ID);
		msg.setChoiceID(choiceId);
		triggerSource = TriggerSource.valueForString(JsonUtils.readStringFromJsonObject(paramsJson, PerformInteractionResponse.KEY_TRIGGER_SOURCE));
		msg.setTriggerSource(triggerSource);
		manualTextEntry = JsonUtils.readStringFromJsonObject(paramsJson, PerformInteractionResponse.KEY_MANUAL_TEXT_ENTRY);
		msg.setManualTextEntry(manualTextEntry);

		return msg;
	}

	@Override
	protected String getMessageType() {
		return RPCMessage.KEY_RESPONSE;
	}

	@Override
	protected String getCommandType() {
		return FunctionID.PERFORM_INTERACTION;
	}

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();

		try {
			result.put(PerformInteractionResponse.KEY_CHOICE_ID, choiceId);
			result.put(PerformInteractionResponse.KEY_TRIGGER_SOURCE, triggerSource);
			result.put(PerformInteractionResponse.KEY_MANUAL_TEXT_ENTRY, manualTextEntry);
			
		} catch (JSONException e) {
			/* do nothing */
		}

		return result;
	}

	public void testChoiceId() {
		Integer copy = ( (PerformInteractionResponse) msg).getChoiceID();
		
		assertEquals("Choice id didn't match input data.", (Integer) choiceId, copy);
	}
	
	public void testTriggerSource () {
		TriggerSource copy = ( (PerformInteractionResponse) msg).getTriggerSource();
		
		assertEquals("Trigger source didn't match input data.", triggerSource, copy);
	}
	
	public void testManualTextEntry () {
		String copy = ( (PerformInteractionResponse) msg).getManualTextEntry();
		
		assertEquals("Manual text entry didn't match input data.", manualTextEntry, copy);
	}

	public void testNull() {
		PerformInteractionResponse msg = new PerformInteractionResponse();
		assertNotNull("Null object creation failed.", msg);

		testNullBase(msg);

		assertNull("Choice id wasn't set, but getter method returned an object.",         msg.getChoiceID());
		assertNull("Trigger source wasn't set, but getter method returned an object.",    msg.getTriggerSource());
		assertNull("Manual text entry wasn't set, but getter method returned an object.", msg.getManualTextEntry());
	}
	
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			PerformInteractionResponse cmd = new PerformInteractionResponse(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			assertEquals("Manual text entry doesn't match input text entry", 
					parameters.getString(PerformInteractionResponse.KEY_MANUAL_TEXT_ENTRY), cmd.getManualTextEntry());
			assertEquals("Trigger source doesn't match input trigger source", 
					parameters.getString(PerformInteractionResponse.KEY_TRIGGER_SOURCE), cmd.getTriggerSource().toString());
			assertEquals("Choice ID doesn't match input ID", 
					(Integer) parameters.getInt(PerformInteractionResponse.KEY_CHOICE_ID), cmd.getChoiceID());
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }

}
