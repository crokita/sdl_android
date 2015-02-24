package com.smartdevicelink.test.rpc.responses;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.DIDResult;
import com.smartdevicelink.proxy.rpc.ReadDIDResponse;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.Validator;

public class ReadDIDResponseTest extends BaseRpcTests {

	private List<DIDResult> didResults = new ArrayList<DIDResult>();
	
	private JSONObject paramsJson;
	
	@Override
	protected RPCMessage createMessage() {
		ReadDIDResponse msg = new ReadDIDResponse();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
		
		try {			
			JSONArray didResultArray = paramsJson.getJSONArray(ReadDIDResponse.KEY_DID_RESULT);
			for (int index = 0; index < didResultArray.length(); index++) {
				DIDResult didResult = new DIDResult(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)didResultArray.get(index)) );
				didResults.add(didResult);
			}
			msg.setDidResult(didResults);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return msg;
	}

	@Override
	protected String getMessageType() {
		return RPCMessage.KEY_RESPONSE;
	}

	@Override
	protected String getCommandType() {
		return FunctionID.READ_DID;
	}

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();

		try {
			result.put(ReadDIDResponse.KEY_DID_RESULT, paramsJson.getJSONArray(ReadDIDResponse.KEY_DID_RESULT));
		} catch (JSONException e) {
			/* do nothing */
		}

		return result;
	}

	public void testDidResults() {
		List<DIDResult> copy = ( (ReadDIDResponse) msg ).getDidResult();
		
		assertNotNull("Did results were null.", copy);
		assertTrue("Did results didn't match input data.", Validator.validateDIDResults(didResults, copy));
	}

	public void testNull() {
		ReadDIDResponse msg = new ReadDIDResponse();
		assertNotNull("Null object creation failed.", msg);

		testNullBase(msg);

		assertNull("Did response wasn't set, but getter method returned an object.", msg.getDidResult());
	}
	
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			ReadDIDResponse cmd = new ReadDIDResponse(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			
			JSONArray didResultArray = parameters.getJSONArray(ReadDIDResponse.KEY_DID_RESULT);
			List<DIDResult> didResultList = new ArrayList<DIDResult>();
			for (int index = 0; index < didResultArray.length(); index++) {
				DIDResult chunk = new DIDResult(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)didResultArray.get(index)) );
				didResultList.add(chunk);
			}
			assertTrue("TTSChunk list doesn't match input TTSChunk list",  Validator.validateDIDResults(didResultList, cmd.getDidResult()));
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }

}
