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
import com.smartdevicelink.proxy.rpc.ReadDID;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;
import com.smartdevicelink.test.utils.Validator;

public class ReadDIDTest extends BaseRpcTests {
	
	private int 			ecuName;
	private List<Integer> 	didLocations = new ArrayList<Integer>();
	
	private JSONObject paramsJson;
	
	@Override
	protected RPCMessage createMessage() {
		ReadDID msg = new ReadDID();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());

		ecuName = JsonUtils.readIntegerFromJsonObject(paramsJson, ReadDID.KEY_ECU_NAME);
		msg.setEcuName(ecuName);
		
		JSONArray didLocationsArray = JsonUtils.readJsonArrayFromJsonObject(paramsJson, ReadDID.KEY_DID_LOCATION);
		didLocations = JsonUtils.<Integer>createListFromJsonArray(didLocationsArray);
		msg.setDidLocation(didLocations);
		
		return msg;
	}

	@Override
	protected String getMessageType() {
		return RPCMessage.KEY_REQUEST;
	}

	@Override
	protected String getCommandType() {
		return FunctionID.READ_DID;
	}

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();

		try {
			result.put(ReadDID.KEY_ECU_NAME, ecuName);
			result.put(ReadDID.KEY_DID_LOCATION, paramsJson.getJSONArray(ReadDID.KEY_DID_LOCATION));
			
		} catch (JSONException e) {
			/* do nothing */
		}

		return result;
	}

	public void testEcuName() {
		Integer copy = ( (ReadDID) msg ).getEcuName();
		
		assertEquals("Data didn't match input data.", (Integer) ecuName, copy);
	}
	
	public void testDidLocation () {
		List<Integer> copy = ( (ReadDID) msg ).getDidLocation();
		
		assertEquals("Data didn't match input data.", didLocations, copy);
	}

	public void testNull() {
		ReadDID msg = new ReadDID();
		assertNotNull("Null object creation failed.", msg);

		testNullBase(msg);

		assertNull("Ecu name wasn't set, but getter method returned an object.", msg.getEcuName());
		assertNull("Did locations wasn't set, but getter method returned an object.", msg.getDidLocation());
	}

    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			ReadDID cmd = new ReadDID(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			assertEquals("ECU name doesn't match input name", (Integer) parameters.getInt(ReadDID.KEY_ECU_NAME), cmd.getEcuName());

			List<Integer> didLocationList = JsonUtils.readIntegerListFromJsonObject(parameters, ReadDID.KEY_DID_LOCATION);
			List<Integer> testLocationList = cmd.getDidLocation();
			assertEquals("DID location list length not same as reference location list length", didLocationList.size(), testLocationList.size());
			assertTrue("DID location list doesn't match input location list", Validator.validateIntegerList(didLocationList, testLocationList));
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }
	
}
