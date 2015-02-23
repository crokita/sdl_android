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
import com.smartdevicelink.proxy.rpc.Speak;
import com.smartdevicelink.proxy.rpc.TTSChunk;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.Validator;

public class SpeakTest extends BaseRpcTests {

	private List<TTSChunk> ttsChunks = new ArrayList<TTSChunk>();
	
	private JSONObject paramsJson;
	
	@Override
	protected RPCMessage createMessage() {
		Speak msg = new Speak();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
		
		try {			
			JSONArray ttsChunkArray = paramsJson.getJSONArray(Speak.KEY_TTS_CHUNKS);
			for (int index = 0; index < ttsChunkArray.length(); index++) {
				TTSChunk ttsChunk = new TTSChunk(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)ttsChunkArray.get(index)) );
				ttsChunks.add(ttsChunk);
			}
			msg.setTtsChunks(ttsChunks);
			
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
		return FunctionID.SPEAK;
	}

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();

		try {
			result.put(Speak.KEY_TTS_CHUNKS, paramsJson.getJSONArray(Speak.KEY_TTS_CHUNKS));
		} catch (JSONException e) {
			/* do nothing */
		}

		return result;
	}

	public void testTtsChunks() {
		List<TTSChunk> copy = ( (Speak) msg ).getTtsChunks();
		
	    assertTrue("Input value didn't match expected value.", Validator.validateTtsChunks(ttsChunks, copy));
	}

	public void testNull() {
		Speak msg = new Speak();
		assertNotNull("Null object creation failed.", msg);

		testNullBase(msg);

		assertNull("Tts chunks wasn't set, but getter method returned an object.", msg.getTtsChunks());
	}
	
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			Speak cmd = new Speak(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			
			JSONArray ttsChunkArray = parameters.getJSONArray(Speak.KEY_TTS_CHUNKS);
			List<TTSChunk> ttsChunkList = new ArrayList<TTSChunk>();
			for (int index = 0; index < ttsChunkArray.length(); index++) {
	        	TTSChunk chunk = new TTSChunk(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)ttsChunkArray.get(index)) );
	        	ttsChunkList.add(chunk);
			}
			assertTrue("TTSChunk list doesn't match input TTSChunk list",  Validator.validateTtsChunks(ttsChunkList, cmd.getTtsChunks()));
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }

}
