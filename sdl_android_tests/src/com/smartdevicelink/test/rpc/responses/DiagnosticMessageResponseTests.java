package com.smartdevicelink.test.rpc.responses;

import java.util.Hashtable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.DiagnosticMessageResponse;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;
import com.smartdevicelink.test.utils.Validator;

public class DiagnosticMessageResponseTests extends BaseRpcTests{

    private List<Integer> messageDataResult;
    
    private JSONObject paramsJson;

    @Override
    protected RPCMessage createMessage(){
        DiagnosticMessageResponse msg = new DiagnosticMessageResponse();
        paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
        
		try {			
			JSONArray messageDataResultArray = paramsJson.getJSONArray(DiagnosticMessageResponse.KEY_MESSAGE_DATA_RESULT);
			messageDataResult = JsonUtils.<Integer>createListFromJsonArray(messageDataResultArray);
			msg.setMessageDataResult(messageDataResult);
		} catch (JSONException e) {
			e.printStackTrace();
		}
        

        return msg;
    }

    @Override
    protected String getMessageType(){
        return RPCMessage.KEY_RESPONSE;
    }

    @Override
    protected String getCommandType(){
        return FunctionID.DIAGNOSTIC_MESSAGE;
    }

    @Override
    protected JSONObject getExpectedParameters(int sdlVersion){
        JSONObject result = new JSONObject();

        try{
            result.put(DiagnosticMessageResponse.KEY_MESSAGE_DATA_RESULT, paramsJson.getJSONArray(DiagnosticMessageResponse.KEY_MESSAGE_DATA_RESULT));
        }catch(JSONException e){
            /* do nothing */
        }

        return result;
    }

    public void testMessageData(){
        List<Integer> copy = ( (DiagnosticMessageResponse) msg ).getMessageDataResult();

        assertEquals("Array size didn't match expected size.", messageDataResult.size(), copy.size());

        for(int i = 0; i < messageDataResult.size(); i++){
            assertEquals("Message data didn't match input message data.", messageDataResult.get(i), copy.get(i));
        }
    }

    public void testNull(){
        DiagnosticMessageResponse msg = new DiagnosticMessageResponse();
        assertNotNull("Null object creation failed.", msg);

        testNullBase(msg);

        assertNull("Message data wasn't set, but getter method returned an object.", msg.getMessageDataResult());
    }
    
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			DiagnosticMessageResponse cmd = new DiagnosticMessageResponse(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			
			List<Integer> dataResultList = JsonUtils.readIntegerListFromJsonObject(parameters, DiagnosticMessageResponse.KEY_MESSAGE_DATA_RESULT);
			List<Integer> testResultList = cmd.getMessageDataResult();
			
			assertEquals("Data result list length not same as reference list length", dataResultList.size(), testResultList.size());
			assertTrue("Data result list doesn't match input data result", Validator.validateIntegerList(dataResultList, testResultList));
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }
}
