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
import com.smartdevicelink.test.utils.Validator;

public class AlertTests extends BaseRpcTests{
	
	private Integer				duration;
	private String				alertText1;
	private String				alertText2;
	private String				alertText3;
	private Boolean				playTone;
	private Boolean				progressIndicator;
    private List<TTSChunk> 		ttsChunkList = new ArrayList<TTSChunk>();
    private List<SoftButton> 	softButtonList = new ArrayList<SoftButton>();
    
    private JSONObject paramsJson;
    
    @Override
    protected RPCMessage createMessage(){
        Alert msg = new Alert();
        paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());

		try {	
			duration = paramsJson.getInt(Alert.KEY_DURATION);
			msg.setDuration(duration);
			alertText1 = paramsJson.getString(Alert.KEY_ALERT_TEXT_1);
			msg.setAlertText1(alertText1);
			alertText2 = paramsJson.getString(Alert.KEY_ALERT_TEXT_2);
			msg.setAlertText2(alertText2);
			alertText3 = paramsJson.getString(Alert.KEY_ALERT_TEXT_3);
			msg.setAlertText3(alertText3);
			playTone = paramsJson.getBoolean(Alert.KEY_PLAY_TONE);
			msg.setPlayTone(playTone);
			progressIndicator = paramsJson.getBoolean(Alert.KEY_PROGRESS_INDICATOR);
			msg.setProgressIndicator(progressIndicator);
		        
			JSONArray ttsChunkArray = paramsJson.getJSONArray(Alert.KEY_TTS_CHUNKS);
			for (int index = 0; index < ttsChunkArray.length(); index++) {
				TTSChunk ttsChunk = new TTSChunk(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)ttsChunkArray.get(index)) );
				ttsChunkList.add(ttsChunk);
			}
			msg.setTtsChunks(ttsChunkList);
			
			JSONArray softButtonsArray = paramsJson.getJSONArray(Alert.KEY_SOFT_BUTTONS);
			for (int index = 0; index < softButtonsArray.length(); index++) {
				SoftButton softButton = new SoftButton(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)softButtonsArray.get(index)) );
				softButtonList.add(softButton);
			}
	        msg.setSoftButtons(softButtonList);

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
            result.put(Alert.KEY_DURATION, duration);
            result.put(Alert.KEY_ALERT_TEXT_1, alertText1);
            result.put(Alert.KEY_ALERT_TEXT_2, alertText2);
            result.put(Alert.KEY_ALERT_TEXT_3, alertText3);
            result.put(Alert.KEY_PLAY_TONE, playTone);
            result.put(Alert.KEY_PROGRESS_INDICATOR, progressIndicator);
        	result.put(Alert.KEY_TTS_CHUNKS, paramsJson.getJSONArray(Alert.KEY_TTS_CHUNKS));
        	result.put(Alert.KEY_SOFT_BUTTONS, paramsJson.getJSONArray(Alert.KEY_SOFT_BUTTONS));
        }catch(JSONException e){
            /* do nothing */
        }

        return result;
    }

    public void testDuration(){
        Integer copy = ( (Alert) msg ).getDuration();
        assertEquals("Duration didn't match input duration.", duration, copy);
    }

    public void testAlertText1(){
        String copy = ( (Alert) msg ).getAlertText1();
        assertEquals("Alert text 1 didn't match input text.", alertText1, copy);
    }

    public void testAlertText2(){
        String copy = ( (Alert) msg ).getAlertText2();
        assertEquals("Alert text 2 didn't match input text.", alertText2, copy);
    }

    public void testAlertText3(){
        String copy = ( (Alert) msg ).getAlertText3();
        assertEquals("Alert text 3 didn't match input text.", alertText3, copy);
    }

    public void testPlayTone(){
        Boolean copy = ( (Alert) msg ).getPlayTone();
        assertEquals("Play tone didn't match input play tone.", playTone, copy);
    }

    public void testProgressIndicator(){
    	Boolean copy = ( (Alert) msg ).getProgressIndicator();
        assertEquals("Progress indicator didn't match input progress indicator.", progressIndicator, copy);
    }

	public void testTtsChunks () {
		List<TTSChunk> copy = ( (Alert) msg ).getTtsChunks();
		
		assertTrue("Input value didn't match expected value.", Validator.validateTtsChunks(ttsChunkList, copy));

	}
	
	public void testSoftButtons () {
		List<SoftButton> copy = ( (Alert) msg ).getSoftButtons();
		
		assertTrue("Input value didn't match expected value.", Validator.validateSoftButtons(softButtonList, copy));
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
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());

			assertEquals("Play tone doesn't match input tone", 	  (Boolean) paramsJson.getBoolean(Alert.KEY_PLAY_TONE), cmd.getPlayTone());
			assertEquals("Duration doesn't match input duration", (Integer) paramsJson.getInt(Alert.KEY_DURATION), cmd.getDuration());
			assertEquals("Alert text 1 doesn't match input text", paramsJson.getString(Alert.KEY_ALERT_TEXT_1), cmd.getAlertText1());
			assertEquals("Alert text 2 doesn't match input text", paramsJson.getString(Alert.KEY_ALERT_TEXT_2), cmd.getAlertText2());
			assertEquals("Alert text 3 doesn't match input text", paramsJson.getString(Alert.KEY_ALERT_TEXT_3), cmd.getAlertText3());
			assertEquals("Progress indicator doesn't match input indicator", 
					(Boolean) paramsJson.getBoolean(Alert.KEY_PROGRESS_INDICATOR), cmd.getProgressIndicator());
			
			JSONArray ttsChunkArray = paramsJson.getJSONArray(Alert.KEY_TTS_CHUNKS);
			List<TTSChunk> ttsChunkList = new ArrayList<TTSChunk>();
			for (int index = 0; index < ttsChunkArray.length(); index++) {
	        	TTSChunk chunk = new TTSChunk(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)ttsChunkArray.get(index)) );
	        	ttsChunkList.add(chunk);
			}
			assertTrue("TTSChunk list doesn't match input TTSChunk list",  Validator.validateTtsChunks(ttsChunkList, cmd.getTtsChunks()));
			
			JSONArray softButtonArray = paramsJson.getJSONArray(Alert.KEY_SOFT_BUTTONS);
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
