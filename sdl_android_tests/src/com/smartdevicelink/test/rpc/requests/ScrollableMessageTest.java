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
import com.smartdevicelink.proxy.rpc.ScrollableMessage;
import com.smartdevicelink.proxy.rpc.SoftButton;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.Validator;

public class ScrollableMessageTest extends BaseRpcTests {

	private String			 	message;
	private Integer 			timeout;
	private List<SoftButton> 	softButtonList = new ArrayList<SoftButton>();
    
	private JSONObject paramsJson;
	
	@Override
	protected RPCMessage createMessage() {
		ScrollableMessage msg = new ScrollableMessage();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
		
		try {			
			message = paramsJson.getString(ScrollableMessage.KEY_SCROLLABLE_MESSAGE_BODY);
			msg.setScrollableMessageBody(message);
			timeout = paramsJson.getInt(ScrollableMessage.KEY_TIMEOUT);
			msg.setTimeout(timeout);
			
			JSONArray softButtonArray = paramsJson.getJSONArray(ScrollableMessage.KEY_SOFT_BUTTONS);
			for (int index = 0; index < softButtonArray.length(); index++) {
				SoftButton ttsChunk = new SoftButton(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)softButtonArray.get(index)) );
				softButtonList.add(ttsChunk);
			}
			msg.setSoftButtons(softButtonList);

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
		return FunctionID.SCROLLABLE_MESSAGE;
	}

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();

		try {
			result.put(ScrollableMessage.KEY_SCROLLABLE_MESSAGE_BODY, message);
			result.put(ScrollableMessage.KEY_TIMEOUT, timeout);
			result.put(ScrollableMessage.KEY_SOFT_BUTTONS, paramsJson.getJSONArray(ScrollableMessage.KEY_SOFT_BUTTONS));
			
		} catch (JSONException e) {
			/* do nothing */
		}

		return result;
	}

	public void testMessage () {
		String copy = ( (ScrollableMessage) msg ).getScrollableMessageBody();
		
		assertEquals("Data didn't match input data.", message, copy);
	}
	
	public void testTimeout () {
		Integer copy = ( (ScrollableMessage) msg ).getTimeout();
		
		assertEquals("Data didn't match input data.", (Integer) timeout, copy);
	}
	
	public void testSoftButton () {
		List<SoftButton> copy = ( (ScrollableMessage) msg ).getSoftButtons();

		assertEquals("List size didn't match expected size.", softButtonList.size(), copy.size());
		
		for (int i = 0; i < softButtonList.size(); i++) {
			assertEquals("Input value didn't match expected value.", softButtonList.get(i), copy.get(i));
		}
	}

	public void testNull() {
		ScrollableMessage msg = new ScrollableMessage();
		assertNotNull("Null object creation failed.", msg);

		testNullBase(msg);

		assertNull("Message wasn't set, but getter method returned an object.", msg.getScrollableMessageBody());
		assertNull("Timeout wasn't set, but getter method returned an object.", msg.getTimeout());
		assertNull("Soft button wasn't set, but getter method returned an object.", msg.getSoftButtons());
	}
	
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			ScrollableMessage cmd = new ScrollableMessage(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			assertEquals("Scrollable message body doesn't match input body", 
					parameters.getString(ScrollableMessage.KEY_SCROLLABLE_MESSAGE_BODY), cmd.getScrollableMessageBody());
			assertEquals("Timeout doesn't match input timeout", (Integer) parameters.getInt(ScrollableMessage.KEY_TIMEOUT), cmd.getTimeout());

			JSONArray softButtonArray = parameters.getJSONArray(ScrollableMessage.KEY_SOFT_BUTTONS);
			List<SoftButton> softButtonList = new ArrayList<SoftButton>();
			for (int index = 0; index < softButtonArray.length(); index++) {
				SoftButton chunk = new SoftButton(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)softButtonArray.get(index)) );
				softButtonList.add(chunk);
			}
			assertTrue("Soft button list doesn't match input button list",  Validator.validateSoftButtons(softButtonList, cmd.getSoftButtons()));
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }
	
}
