package com.smartdevicelink.test.rpc.responses;

import java.util.Hashtable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.GetDTCsResponse;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;
import com.smartdevicelink.test.utils.Validator;

public class GetDTCsResponseTests extends BaseRpcTests{

    private List<String> dtcList;

    private JSONObject paramsJson;
    
    @Override
    protected RPCMessage createMessage(){
        GetDTCsResponse msg = new GetDTCsResponse();
        paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
        
		try {			
			JSONArray dtcListArray = paramsJson.getJSONArray(GetDTCsResponse.KEY_DTC);
			dtcList = JsonUtils.<String>createListFromJsonArray(dtcListArray);
			msg.setDtc(dtcList);
			
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
        return FunctionID.GET_DTCS;
    }

    @Override
    protected JSONObject getExpectedParameters(int sdlVersion){
        JSONObject result = new JSONObject();

        try{
            result.put(GetDTCsResponse.KEY_DTC, paramsJson.getJSONArray(GetDTCsResponse.KEY_DTC));
        }catch(JSONException e){
            /* do nothing */
        }

        return result;
    }

    public void testDtcList(){
        List<String> cmdId = ( (GetDTCsResponse) msg ).getDtc();

        assertEquals("DTC list size didn't match expected size.", dtcList.size(), cmdId.size());

        for(int i = 0; i < dtcList.size(); i++){
            assertEquals("DTC value at index " + i + " didn't match expected value.", dtcList.get(i), cmdId.get(i));
        }
    }

    public void testNull(){
        GetDTCsResponse msg = new GetDTCsResponse();
        assertNotNull("Null object creation failed.", msg);

        testNullBase(msg);

        assertNull("DTC list wasn't set, but getter method returned an object.", msg.getDtc());
    }
    
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			GetDTCsResponse cmd = new GetDTCsResponse(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			
			List<String> dtcList = JsonUtils.readStringListFromJsonObject(parameters, GetDTCsResponse.KEY_DTC);
			List<String> testDtcList = cmd.getDtc();
			assertEquals("DTC list length not same as reference DTC list", dtcList.size(), testDtcList.size());
			assertTrue("DTC list doesn't match input DTC list", Validator.validateStringList(dtcList, testDtcList));
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }
    
}
