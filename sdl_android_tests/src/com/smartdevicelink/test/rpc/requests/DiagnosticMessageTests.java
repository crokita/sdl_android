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
import com.smartdevicelink.proxy.rpc.DiagnosticMessage;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;
import com.smartdevicelink.test.utils.Validator;

public class DiagnosticMessageTests extends BaseRpcTests{

    private int           targetId;
    private List<Integer> messageData = new ArrayList<Integer>();
    private int           messageLength;
    
    private JSONObject paramsJson;

    @Override
    protected RPCMessage createMessage(){
        DiagnosticMessage msg = new DiagnosticMessage();
        paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());

        try {
	        targetId = JsonUtils.readIntegerFromJsonObject(paramsJson, DiagnosticMessage.KEY_TARGET_ID);
	        msg.setTargetID(targetId);
	        
			JSONArray messageDataArray = paramsJson.getJSONArray(DiagnosticMessage.KEY_MESSAGE_DATA);
			messageData = JsonUtils.<Integer>createListFromJsonArray(messageDataArray);
			msg.setMessageData(messageData);
			
			messageLength = JsonUtils.readIntegerFromJsonObject(paramsJson, DiagnosticMessage.KEY_MESSAGE_LENGTH);
	        msg.setMessageLength(messageLength);
	        
		} catch (JSONException e) {
			e.printStackTrace();
		}

        return msg;
    }

    @Override
    protected String getMessageType(){
        return RPCMessage.KEY_REQUEST;
    }

    @Override
    protected String getCommandType(){
        return FunctionID.DIAGNOSTIC_MESSAGE;
    }

    @Override
    protected JSONObject getExpectedParameters(int sdlVersion){
        JSONObject result = new JSONObject();

        try{
            result.put(DiagnosticMessage.KEY_TARGET_ID, targetId);
            result.put(DiagnosticMessage.KEY_MESSAGE_LENGTH, messageLength);
            result.put(DiagnosticMessage.KEY_MESSAGE_DATA, paramsJson.getJSONArray(DiagnosticMessage.KEY_MESSAGE_DATA));
        }catch(JSONException e){
            /* do nothing */
        }

        return result;
    }

    public void testTargetId(){
        int targetId = ( (DiagnosticMessage) msg ).getTargetID();
        assertEquals("Target ID didn't match input target ID.", this.targetId, targetId);
    }

    public void testMessageLength(){
        int messageLength = ( (DiagnosticMessage) msg ).getMessageLength();
        assertEquals("Message length didn't match input message length.", this.messageLength, messageLength);
    }

    public void testMessageData(){
        List<Integer> copy = ( (DiagnosticMessage) msg ).getMessageData();

        assertEquals("Message data size didn't match expected size.", messageData.size(), copy.size());

        for(int i = 0; i < copy.size(); i++){
            assertEquals("Value didn't match input value.", messageData.get(i), copy.get(i));
        }
    }

    public void testNull(){
        DiagnosticMessage msg = new DiagnosticMessage();
        assertNotNull("Null object creation failed.", msg);

        testNullBase(msg);

        assertNull("Target ID wasn't set, but getter method returned an object.", msg.getTargetID());
        assertNull("Message length wasn't set, but getter method returned an object.", msg.getMessageLength());
        assertNull("Message data wasn't set, but getter method returned an object.", msg.getMessageData());
    }
    
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			DiagnosticMessage cmd = new DiagnosticMessage(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());

			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			assertEquals("Target ID doesn't match input ID", (Integer) parameters.getInt(DiagnosticMessage.KEY_TARGET_ID), cmd.getTargetID());
			assertEquals("Message length doesn't match input length", (Integer) parameters.getInt(DiagnosticMessage.KEY_MESSAGE_LENGTH), cmd.getMessageLength());
			
			List<Integer> messageDataList = JsonUtils.readIntegerListFromJsonObject(parameters, DiagnosticMessage.KEY_MESSAGE_DATA);
			List<Integer> testDataList = cmd.getMessageData();
			assertEquals("Message data list length not same as reference list length", messageDataList.size(), testDataList.size());
			assertTrue("Integer list doesn't match input integer list", Validator.validateIntegerList(messageDataList, testDataList));
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }

}
