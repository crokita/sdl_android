package com.smartdevicelink.test.rpc.responses;

import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.AlertResponse;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;

public class AlertResponseTests extends BaseRpcTests{

    private Integer tryAgainTime;

    private JSONObject paramsJson;
    
    @Override
    protected RPCMessage createMessage(){
    	AlertResponse alert = new AlertResponse();
    	paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
    	
    	tryAgainTime = JsonUtils.readIntegerFromJsonObject(paramsJson, AlertResponse.KEY_TRY_AGAIN_TIME);
    	alert.setTryAgainTime(tryAgainTime);
        return alert;
    }

    @Override
    protected String getMessageType(){
        return RPCMessage.KEY_RESPONSE;
    }

    @Override
    protected String getCommandType(){
        return FunctionID.ALERT;
    }

    @Override
    protected JSONObject getExpectedParameters(int sdlVersion){
        JSONObject result = new JSONObject();

        try{
            result.put(AlertResponse.KEY_TRY_AGAIN_TIME, tryAgainTime);
        }catch(JSONException e){
            /* do nothing */
        }

        return result;
    }

    public void testTryAgainTime(){
        Integer copy = ( (AlertResponse) msg ).getTryAgainTime();
        assertEquals("Try again time didn't match expected time.", tryAgainTime, copy);
    }

    public void testNull(){
        AlertResponse msg = new AlertResponse();
        assertNotNull("Null object creation failed.", msg);

        testNullBase(msg);

        assertNull("Try again time wasn't set, but getter method returned an object.", msg.getTryAgainTime());
    }
    
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			AlertResponse cmd = new AlertResponse(hash);
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			assertEquals("Try again time doesn't match input time", JsonUtils.readIntegerFromJsonObject(paramsJson, AlertResponse.KEY_TRY_AGAIN_TIME), cmd.getTryAgainTime());
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }

}
