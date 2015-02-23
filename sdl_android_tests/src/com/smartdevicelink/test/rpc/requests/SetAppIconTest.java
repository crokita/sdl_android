package com.smartdevicelink.test.rpc.requests;

import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.SetAppIcon;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;

public class SetAppIconTest extends BaseRpcTests {
	
	private String fileName;

	private JSONObject paramsJson;
	
	@Override
	protected RPCMessage createMessage() {
		SetAppIcon msg = new SetAppIcon();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
		
		fileName = JsonUtils.readStringFromJsonObject(paramsJson, SetAppIcon.KEY_SDL_FILE_NAME);
		msg.setSdlFileName(fileName);

		return msg;
	}

	@Override
	protected String getMessageType() {
		return RPCMessage.KEY_REQUEST;
	}

	@Override
	protected String getCommandType() {
		return FunctionID.SET_APP_ICON;
	}

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();

		try {
			result.put(SetAppIcon.KEY_SDL_FILE_NAME, fileName);
			
		} catch (JSONException e) {
			/* do nothing */
		}

		return result;
	}

	public void testFileName() {
		String copy = ( (SetAppIcon) msg ).getSdlFileName();
		
		assertEquals("Data didn't match input data.", fileName, copy);
	}

	public void testNull() {
		SetAppIcon msg = new SetAppIcon();
		assertNotNull("Null object creation failed.", msg);

		testNullBase(msg);

		assertNull("File name wasn't set, but getter method returned an object.", msg.getSdlFileName());
	}
	
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			SetAppIcon cmd = new SetAppIcon(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			assertEquals("File name doesn't match input name", parameters.getString(SetAppIcon.KEY_SDL_FILE_NAME), cmd.getSdlFileName());
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }

}
