package com.smartdevicelink.test.rpc.requests;

import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.SubscribeButton;
import com.smartdevicelink.proxy.rpc.enums.ButtonName;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;

public class SubscribeButtonTest extends BaseRpcTests {

	private ButtonName button;
	
	private JSONObject paramsJson;
	
	@Override
	protected RPCMessage createMessage() {
		SubscribeButton msg = new SubscribeButton();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
		
		button = ButtonName.valueForString(JsonUtils.readStringFromJsonObject(paramsJson, SubscribeButton.KEY_BUTTON_NAME));
		msg.setButtonName(button);

		return msg;
	}

	@Override
	protected String getMessageType() {
		return RPCMessage.KEY_REQUEST;
	}

	@Override
	protected String getCommandType() {
		return FunctionID.SUBSCRIBE_BUTTON;
	}

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();

		try {
			result.put(SubscribeButton.KEY_BUTTON_NAME, button);
			
		} catch (JSONException e) {
			/* do nothing */
		}

		return result;
	}

	public void testButtonName() {
		ButtonName copy = ( (SubscribeButton) msg ).getButtonName();
		
		assertEquals("Data didn't match input data.", button, copy);
	}

	public void testNull() {
		SubscribeButton msg = new SubscribeButton();
		assertNotNull("Null object creation failed.", msg);

		testNullBase(msg);

		assertNull("Button name wasn't set, but getter method returned an object.", msg.getButtonName());
	}
	
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			SubscribeButton cmd = new SubscribeButton(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			assertEquals("Button name doesn't match input name", parameters.getString(SubscribeButton.KEY_BUTTON_NAME), cmd.getButtonName().toString());
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }

}
