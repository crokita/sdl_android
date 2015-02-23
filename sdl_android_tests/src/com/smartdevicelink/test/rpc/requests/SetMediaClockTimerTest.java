package com.smartdevicelink.test.rpc.requests;

import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.SetMediaClockTimer;
import com.smartdevicelink.proxy.rpc.StartTime;
import com.smartdevicelink.proxy.rpc.enums.UpdateMode;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.Validator;

public class SetMediaClockTimerTest extends BaseRpcTests {

	private StartTime startTime = new StartTime();
	private StartTime endTime = new StartTime();
	private UpdateMode updateMode;

	private JSONObject paramsJson;
	
	@Override
	protected RPCMessage createMessage() {
		SetMediaClockTimer msg = new SetMediaClockTimer();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
		
		try {
			JSONObject startTimeObj = paramsJson.getJSONObject(SetMediaClockTimer.KEY_START_TIME);
			startTime = new StartTime(JsonRPCMarshaller.deserializeJSONObject(startTimeObj));
			msg.setStartTime(startTime);
			
			JSONObject endTimeObj = paramsJson.getJSONObject(SetMediaClockTimer.KEY_END_TIME);
			endTime = new StartTime(JsonRPCMarshaller.deserializeJSONObject(endTimeObj));
			msg.setEndTime(endTime);
		
			updateMode = UpdateMode.valueForString(paramsJson.getString(SetMediaClockTimer.KEY_UPDATE_MODE));
			msg.setUpdateMode(updateMode);
			
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
		return FunctionID.SET_MEDIA_CLOCK_TIMER;
	}

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();

		try {
			result.put(SetMediaClockTimer.KEY_START_TIME, paramsJson.getJSONObject(SetMediaClockTimer.KEY_START_TIME));
			result.put(SetMediaClockTimer.KEY_END_TIME, paramsJson.getJSONObject(SetMediaClockTimer.KEY_END_TIME));
			result.put(SetMediaClockTimer.KEY_UPDATE_MODE, updateMode);
			
		} catch (JSONException e) {
			/* do nothing */
		}

		return result;
	}

	public void testStartTime() {
		StartTime copy = ( (SetMediaClockTimer) msg ).getStartTime();
		
		assertTrue("Input value didn't match expected value.", Validator.validateStartTime(startTime, copy));
	}
	
	public void testEndTime() {
		StartTime copy = ( (SetMediaClockTimer) msg ).getEndTime();
		
		assertTrue("Input value didn't match expected value.", Validator.validateStartTime(endTime, copy));
	}
	
	public void testUpdateMode () {
		UpdateMode copy = ( (SetMediaClockTimer) msg ).getUpdateMode();
		
		assertEquals("Data didn't match input data.", updateMode, copy);
	}

	public void testNull() {
		SetMediaClockTimer msg = new SetMediaClockTimer();
		assertNotNull("Null object creation failed.", msg);

		testNullBase(msg);

		assertNull("Start time wasn't set, but getter method returned an object.", msg.getStartTime());
		assertNull("End time wasn't set, but getter method returned an object.", msg.getEndTime());
		assertNull("Update mode wasn't set, but getter method returned an object.", msg.getUpdateMode());
	}
	
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			SetMediaClockTimer cmd = new SetMediaClockTimer(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			
			JSONObject startTime = parameters.getJSONObject(SetMediaClockTimer.KEY_START_TIME);
			StartTime referenceStartTime = new StartTime(JsonRPCMarshaller.deserializeJSONObject(startTime));
			assertTrue("Start time doesn't match expected time", Validator.validateStartTime(referenceStartTime, cmd.getStartTime()));
			
			JSONObject endTime = parameters.getJSONObject(SetMediaClockTimer.KEY_END_TIME);
			StartTime referenceEndTime = new StartTime(JsonRPCMarshaller.deserializeJSONObject(endTime));
			assertTrue("End time doesn't match expected time", Validator.validateStartTime(referenceEndTime, cmd.getEndTime()));
			
			assertEquals("Update mode doesn't match input mode", 
					parameters.getString(SetMediaClockTimer.KEY_UPDATE_MODE), cmd.getUpdateMode().toString());
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }	

}
