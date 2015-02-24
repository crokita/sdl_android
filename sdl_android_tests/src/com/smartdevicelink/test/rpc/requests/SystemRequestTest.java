package com.smartdevicelink.test.rpc.requests;

import java.util.Hashtable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.SystemRequest;
import com.smartdevicelink.proxy.rpc.enums.RequestType;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;
import com.smartdevicelink.test.utils.Validator;

public class SystemRequestTest extends BaseRpcTests {

	private List<String> legacyData;
	private String fileName;
	private RequestType requestType;
	private static final byte[] BULK_DATA = new byte[]{0x00, 0x01, 0x02};
    
	private JSONObject paramsJson;
	
	@Override
	protected RPCMessage createMessage() {
		SystemRequest msg = new SystemRequest();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
		
		try {			
			JSONArray legacyDataArray = paramsJson.getJSONArray(SystemRequest.KEY_DATA);
			legacyData = JsonUtils.<String>createListFromJsonArray(legacyDataArray);
			msg.setLegacyData(legacyData);
			
			fileName = paramsJson.getString(SystemRequest.KEY_FILE_NAME);
			msg.setFileName(fileName);
			
			requestType = RequestType.valueForString(paramsJson.getString(SystemRequest.KEY_REQUEST_TYPE));
			msg.setRequestType(requestType);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		msg.setBulkData(BULK_DATA);

		return msg;
	}

	@Override
	protected String getMessageType() {
		return RPCMessage.KEY_REQUEST;
	}

	@Override
	protected String getCommandType() {
		return FunctionID.SYSTEM_REQUEST;
	}

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();

		try {
			result.put(SystemRequest.KEY_DATA, JsonUtils.createJsonArray(legacyData));
			result.put(SystemRequest.KEY_FILE_NAME, fileName);
			result.put(SystemRequest.KEY_REQUEST_TYPE, requestType);
			
		} catch (JSONException e) {
			/* do nothing */
		}

		return result;
	}

	public void testLegacyData () {
		List<String> copy = ( (SystemRequest) msg ).getLegacyData();
		
	    assertTrue("Input value didn't match expected value.", Validator.validateStringList(legacyData, copy));
	}
	
	public void testFileName () {
		String copy = ( (SystemRequest) msg ).getFileName();
		
		assertEquals("Data didn't match input data.", fileName, copy);
	}
	
	public void testRequestType () {
		RequestType copy = ( (SystemRequest) msg ).getRequestType();
		
		assertEquals("Data didn't match input data.", requestType, copy);
	}
	
	public void testBulkData(){
	    byte[] copy = ( (SystemRequest) msg ).getBulkData();
	    
	    assertTrue("Input value didn't match expected value.", Validator.validateBulkData(copy, BULK_DATA));
	}

	public void testNull() {
		SystemRequest msg = new SystemRequest();
		assertNotNull("Null object creation failed.", msg);

		testNullBase(msg);

		assertNull("Legacy data wasn't set, but getter method returned an object.", msg.getLegacyData());
		assertNull("File name wasn't set, but getter method returned an object.", msg.getFileName());
		assertNull("Request type wasn't set, but getter method returned an object.", msg.getRequestType());
		assertNull("Bulk data wasn't set, but getter method returned an object.", msg.getBulkData());
	}
	
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			SystemRequest cmd = new SystemRequest(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			assertEquals("File name doesn't match input name", parameters.getJSONObject(SystemRequest.KEY_FILE_NAME), cmd.getFileName());
			assertEquals("Request type doesn't match input type", 
					parameters.getString(SystemRequest.KEY_REQUEST_TYPE), cmd.getRequestType().toString());

			List<String> dataList = JsonUtils.readStringListFromJsonObject(parameters, SystemRequest.KEY_DATA);
			List<String> testDataList = cmd.getLegacyData();
			assertEquals("Data list length not same as reference data list length", dataList.size(), testDataList.size());
			assertTrue("Data list doesn't match input data list", Validator.validateStringList(dataList, testDataList));
			
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }
	
}
