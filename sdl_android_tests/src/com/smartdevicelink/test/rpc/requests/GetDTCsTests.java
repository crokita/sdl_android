package com.smartdevicelink.test.rpc.requests;

import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.GetDTCs;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;

public class GetDTCsTests extends BaseRpcTests{

    private Integer ecuName;
    private Integer dtcMask;

    private JSONObject paramsJson;
    
    @Override
    protected RPCMessage createMessage(){
        GetDTCs msg = new GetDTCs();
        paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());

        ecuName = JsonUtils.readIntegerFromJsonObject(paramsJson, GetDTCs.KEY_ECU_NAME);
        msg.setEcuName(ecuName);
        dtcMask = JsonUtils.readIntegerFromJsonObject(paramsJson, GetDTCs.KEY_ECU_NAME);
        msg.setDtcMask(dtcMask);

        return msg;
    }

    @Override
    protected String getMessageType(){
        return RPCMessage.KEY_REQUEST;
    }

    @Override
    protected String getCommandType(){
        return FunctionID.GET_DTCS;
    }

    @Override
    protected JSONObject getExpectedParameters(int sdlVersion){
        JSONObject result = new JSONObject();

        try{
            result.put(GetDTCs.KEY_ECU_NAME, ecuName);
            result.put(GetDTCs.KEY_DTC_MASK, dtcMask);
        }catch(JSONException e){
            /* do nothing */
        }

        return result;
    }

    public void testEcuName(){
    	Integer copy = ( (GetDTCs) msg ).getEcuName();
        assertEquals("ECU name didn't match input ECU name.", ecuName, copy);
    }

    public void testDtcMask(){
    	Integer copy = ( (GetDTCs) msg ).getDtcMask();
        assertEquals("DTC mask didn't match input DTC mask.", dtcMask, copy);
    }

    public void testNull(){
        GetDTCs msg = new GetDTCs();
        assertNotNull("Null object creation failed.", msg);

        testNullBase(msg);

        assertNull("ECU name wasn't set, but getter method returned an object.", msg.getEcuName());
        assertNull("DTC mask wasn't set, but getter method returned an object.", msg.getDtcMask());
    }
    
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			GetDTCs cmd = new GetDTCs(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			
			assertEquals("DTC mask doesn't match input DTC mask", (Integer) parameters.getInt(GetDTCs.KEY_DTC_MASK), cmd.getDtcMask());
			assertEquals("ECU name doesn't match input ECU name", (Integer) parameters.getInt(GetDTCs.KEY_ECU_NAME), cmd.getEcuName());
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }
    
}
