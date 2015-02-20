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
import com.smartdevicelink.proxy.rpc.Alert;
import com.smartdevicelink.proxy.rpc.SoftButton;
import com.smartdevicelink.proxy.rpc.TTSChunk;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;
import com.smartdevicelink.test.utils.Validator;

public class AlertTests extends BaseRpcTests{
    
    private final List<TTSChunk> TTS_CHUNK_LIST = new ArrayList<TTSChunk>();
    private final List<SoftButton> SOFT_BUTTON_LIST = new ArrayList<SoftButton>();
    
    private static JSONObject paramsJson;
    
    @Override
    protected RPCMessage createMessage(){
        Alert msg = new Alert();
        paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
        
		try {	
	        msg.setDuration(JsonUtils.readIntegerFromJsonObject(paramsJson, Alert.KEY_DURATION));
	        msg.setAlertText1(JsonUtils.readStringFromJsonObject(paramsJson, Alert.KEY_ALERT_TEXT_1));
	        msg.setAlertText2(JsonUtils.readStringFromJsonObject(paramsJson, Alert.KEY_ALERT_TEXT_2));
	        msg.setAlertText3(JsonUtils.readStringFromJsonObject(paramsJson, Alert.KEY_ALERT_TEXT_3));
	        msg.setPlayTone(JsonUtils.readBooleanFromJsonObject(paramsJson, Alert.KEY_PLAY_TONE));
	        msg.setProgressIndicator(JsonUtils.readBooleanFromJsonObject(paramsJson, Alert.KEY_PROGRESS_INDICATOR));
	        
			JSONArray ttsChunkArray = JsonUtils.readJsonArrayFromJsonObject(paramsJson, Alert.KEY_TTS_CHUNKS);
			for (int index = 0; index < ttsChunkArray.length(); index++) {
				TTSChunk ttsChunk = new TTSChunk(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)ttsChunkArray.get(index)) );
				TTS_CHUNK_LIST.add(ttsChunk);
			}
			
			msg.setTtsChunks(TTS_CHUNK_LIST);
			
			JSONArray softButtonsArray = JsonUtils.readJsonArrayFromJsonObject(paramsJson, Alert.KEY_SOFT_BUTTONS);
			for (int index = 0; index < softButtonsArray.length(); index++) {
				SoftButton softButton = new SoftButton(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)softButtonsArray.get(index)) );
				SOFT_BUTTON_LIST.add(softButton);
			}
	        msg.setSoftButtons(SOFT_BUTTON_LIST);

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
        return FunctionID.ALERT;
    }

    @Override
    protected JSONObject getExpectedParameters(int sdlVersion){
        JSONObject result = new JSONObject();
        
        try{
            result.put(Alert.KEY_DURATION, JsonUtils.readIntegerFromJsonObject(paramsJson, Alert.KEY_DURATION));
            result.put(Alert.KEY_ALERT_TEXT_1, JsonUtils.readStringFromJsonObject(paramsJson, Alert.KEY_ALERT_TEXT_1));
            result.put(Alert.KEY_ALERT_TEXT_2, JsonUtils.readStringFromJsonObject(paramsJson, Alert.KEY_ALERT_TEXT_2));
            result.put(Alert.KEY_ALERT_TEXT_3, JsonUtils.readStringFromJsonObject(paramsJson, Alert.KEY_ALERT_TEXT_3));
            result.put(Alert.KEY_PLAY_TONE, JsonUtils.readBooleanFromJsonObject(paramsJson, Alert.KEY_PLAY_TONE));
            result.put(Alert.KEY_PROGRESS_INDICATOR, JsonUtils.readBooleanFromJsonObject(paramsJson, Alert.KEY_PROGRESS_INDICATOR));
        	result.put(Alert.KEY_TTS_CHUNKS, JsonUtils.readJsonArrayFromJsonObject(paramsJson, Alert.KEY_TTS_CHUNKS));
        	result.put(Alert.KEY_SOFT_BUTTONS, JsonUtils.readJsonArrayFromJsonObject(paramsJson, Alert.KEY_SOFT_BUTTONS));
        }catch(JSONException e){
            /* do nothing */
        }

        return result;
    }

    public void testDuration(){
        int duration = ( (Alert) msg ).getDuration();
        assertEquals("Duration didn't match input duration.", (int) JsonUtils.readIntegerFromJsonObject(paramsJson, Alert.KEY_DURATION), duration);
    }

    public void testAlertText1(){
        String alertText1 = ( (Alert) msg ).getAlertText1();
        assertEquals("Alert text 1 didn't match input text.", JsonUtils.readStringFromJsonObject(paramsJson, Alert.KEY_ALERT_TEXT_1), alertText1);
    }

    public void testAlertText2(){
        String alertText2 = ( (Alert) msg ).getAlertText2();
        assertEquals("Alert text 2 didn't match input text.", JsonUtils.readStringFromJsonObject(paramsJson, Alert.KEY_ALERT_TEXT_2), alertText2);
    }

    public void testAlertText3(){
        String alertText3 = ( (Alert) msg ).getAlertText3();
        assertEquals("Alert text 3 didn't match input text.", JsonUtils.readStringFromJsonObject(paramsJson, Alert.KEY_ALERT_TEXT_3), alertText3);
    }

    public void testPlayTone(){
        boolean playTone = ( (Alert) msg ).getPlayTone();
        assertEquals("Play tone didn't match input play tone.", (boolean) JsonUtils.readBooleanFromJsonObject(paramsJson, Alert.KEY_PLAY_TONE), playTone);
    }

    public void testProgressIndicator(){
        boolean progressIndicator = ( (Alert) msg ).getProgressIndicator();
        assertEquals("Progress indicator didn't match input progress indicator.", 
        		(boolean) JsonUtils.readBooleanFromJsonObject(paramsJson, Alert.KEY_PROGRESS_INDICATOR), progressIndicator);
    }

	public void testTtsChunks () {
		List<TTSChunk> copy = ( (Alert) msg ).getTtsChunks();
		
		assertTrue("Input value didn't match expected value.", Validator.validateTtsChunks(TTS_CHUNK_LIST, copy));

	}
	
	public void testSoftButtons () {
		List<SoftButton> copy = ( (Alert) msg ).getSoftButtons();
		
		assertTrue("Input value didn't match expected value.", Validator.validateSoftButtons(SOFT_BUTTON_LIST, copy));
	}
    
    public void testNull(){
        Alert msg = new Alert();
        assertNotNull("Null object creation failed.", msg);

        testNullBase(msg);

        assertNull("Alert text 1 wasn't set, but getter method returned an object.", msg.getAlertText1());
        assertNull("Alert text 2 wasn't set, but getter method returned an object.", msg.getAlertText2());
        assertNull("Alert text 3 wasn't set, but getter method returned an object.", msg.getAlertText3());
        assertNull("Duration wasn't set, but getter method returned an object.", msg.getDuration());
        assertNull("Play tone wasn't set, but getter method returned an object.", msg.getPlayTone());
        assertNull("Progress indicator wasn't set, but getter method returned an object.", msg.getProgressIndicator());
        assertNull("TTS chunks wasn't set, but getter method returned an object.", msg.getTtsChunks());
        assertNull("Soft buttons wasn't set, but getter method returned an object.", msg.getSoftButtons());
    }
    
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			Alert cmd = new Alert(hash);
			JSONObject body = JsonUtils.readJsonObjectFromJsonObject(commandJson, getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", JsonUtils.readStringFromJsonObject(body, RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", JsonUtils.readIntegerFromJsonObject(body, RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());

			assertEquals("Play tone doesn't match input tone", JsonUtils.readBooleanFromJsonObject(paramsJson, Alert.KEY_PLAY_TONE), cmd.getPlayTone());
			assertEquals("Duration doesn't match input duration", JsonUtils.readIntegerFromJsonObject(paramsJson, Alert.KEY_DURATION), cmd.getDuration());
			assertEquals("Alert text 1 doesn't match input text", JsonUtils.readStringFromJsonObject(paramsJson, Alert.KEY_ALERT_TEXT_1), cmd.getAlertText1());
			assertEquals("Alert text 2 doesn't match input text", JsonUtils.readStringFromJsonObject(paramsJson, Alert.KEY_ALERT_TEXT_2), cmd.getAlertText2());
			assertEquals("Alert text 3 doesn't match input text", JsonUtils.readStringFromJsonObject(paramsJson, Alert.KEY_ALERT_TEXT_3), cmd.getAlertText3());
			assertEquals("Progress indicator doesn't match input indicator", 
					JsonUtils.readBooleanFromJsonObject(paramsJson, Alert.KEY_PROGRESS_INDICATOR), cmd.getProgressIndicator());
			
			JSONArray ttsChunkArray = JsonUtils.readJsonArrayFromJsonObject(paramsJson, Alert.KEY_TTS_CHUNKS);
			List<TTSChunk> ttsChunkList = new ArrayList<TTSChunk>();
			for (int index = 0; index < ttsChunkArray.length(); index++) {
	        	TTSChunk chunk = new TTSChunk(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)ttsChunkArray.get(index)) );
	        	ttsChunkList.add(chunk);
			}
			assertTrue("TTSChunk list doesn't match input TTSChunk list",  Validator.validateTtsChunks(ttsChunkList, cmd.getTtsChunks()));
			
			JSONArray softButtonArray = JsonUtils.readJsonArrayFromJsonObject(paramsJson, Alert.KEY_SOFT_BUTTONS);
			List<SoftButton> softButtonList = new ArrayList<SoftButton>();
			for (int index = 0; index < softButtonArray.length(); index++) {
				SoftButton chunk = new SoftButton(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)softButtonArray.get(index)) );
				softButtonList.add(chunk);
			}
			assertTrue("Soft button list doesn't match input button list",  Validator.validateSoftButtons(softButtonList, cmd.getSoftButtons()));
			
						
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }

}
