package com.smartdevicelink.test.rpc.requests;

import java.util.Hashtable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.ResetGlobalProperties;
import com.smartdevicelink.proxy.rpc.enums.GlobalProperty;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;

public class ResetGlobalPropertiesTest extends BaseRpcTests {

	private List<GlobalProperty> properties;

	private JSONObject paramsJson;
	
	@Override
	protected RPCMessage createMessage() {
		ResetGlobalProperties msg = new ResetGlobalProperties();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());

		JSONArray propertiesArray = JsonUtils.readJsonArrayFromJsonObject(paramsJson, ResetGlobalProperties.KEY_PROPERTIES);
		properties = JsonUtils.<GlobalProperty>createListFromJsonArray(propertiesArray);			
		msg.setProperties(properties);
		
		return msg;
	}

	@Override
	protected String getMessageType() {
		return RPCMessage.KEY_REQUEST;
	}

	@Override
	protected String getCommandType() {
		return FunctionID.RESET_GLOBAL_PROPERTIES;
	}

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();

		try {
			result.put(ResetGlobalProperties.KEY_PROPERTIES, JsonUtils.createJsonArray(properties));
		} catch (JSONException e) {
			/* do nothing */
		}

		return result;
	}

	public void testProperties() {
		List<GlobalProperty> copy = ( (ResetGlobalProperties) msg ).getProperties();
		
        assertEquals("List size didn't match expected size.", properties.size(), copy.size());

        for(int i = 0; i < properties.size(); i++){
            assertEquals("Input value didn't match expected value.", properties.get(i), copy.get(i).toString());
        }
	}

	public void testNull() {
		ResetGlobalProperties msg = new ResetGlobalProperties();
		assertNotNull("Null object creation failed.", msg);

		testNullBase(msg);

		assertNull("Properties wasn't set, but getter method returned an object.", msg.getProperties());
	}
	
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			ResetGlobalProperties cmd = new ResetGlobalProperties(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			
			JSONArray propertiesArray = parameters.getJSONArray(ResetGlobalProperties.KEY_PROPERTIES);
			for (int index = 0; index < propertiesArray.length(); index++) {
				GlobalProperty property = GlobalProperty.valueOf(propertiesArray.get(index).toString());
				assertEquals("Global property item doesn't match input property item",  property, cmd.getProperties().get(index));
			}
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }

}
