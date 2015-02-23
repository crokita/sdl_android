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
import com.smartdevicelink.proxy.rpc.PerformAudioPassThru;
import com.smartdevicelink.proxy.rpc.TTSChunk;
import com.smartdevicelink.proxy.rpc.enums.AudioType;
import com.smartdevicelink.proxy.rpc.enums.BitsPerSample;
import com.smartdevicelink.proxy.rpc.enums.SamplingRate;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.Validator;

public class PerformAudioPassThruTests extends BaseRpcTests {
	
	private int 					maxDuration;
	private String 					text1;
	private String 					text2;
	private boolean 				muteAudio;
	private SamplingRate 			samplingRate;
	private AudioType 				audioType;
	private final List<TTSChunk> 	initialPrompt  = new ArrayList<TTSChunk>();
	private BitsPerSample 			bitsPerSample;

	private JSONObject paramsJson;
	
	@Override
	protected RPCMessage createMessage() {
		PerformAudioPassThru msg = new PerformAudioPassThru();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());

		try {	
			maxDuration = paramsJson.getInt(PerformAudioPassThru.KEY_MAX_DURATION);
			msg.setMaxDuration(maxDuration);
			text1 = paramsJson.getString(PerformAudioPassThru.KEY_AUDIO_PASS_THRU_DISPLAY_TEXT_1);
			msg.setAudioPassThruDisplayText1(text1);
			text2 = paramsJson.getString(PerformAudioPassThru.KEY_AUDIO_PASS_THRU_DISPLAY_TEXT_2);
			msg.setAudioPassThruDisplayText2(text2);
			muteAudio = paramsJson.getBoolean(PerformAudioPassThru.KEY_MUTE_AUDIO);
			msg.setMuteAudio(muteAudio);
			samplingRate = SamplingRate.valueForString(paramsJson.getString(PerformAudioPassThru.KEY_SAMPLING_RATE));
			msg.setSamplingRate(samplingRate);
			audioType = AudioType.valueForString(paramsJson.getString(PerformAudioPassThru.KEY_AUDIO_TYPE));
			msg.setAudioType(audioType);
			
			JSONArray initialPromptArray = paramsJson.getJSONArray(PerformAudioPassThru.KEY_INITIAL_PROMPT);
			for (int index = 0; index < initialPromptArray.length(); index++) {
				TTSChunk ttsChunk = new TTSChunk(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)initialPromptArray.get(index)) );
				initialPrompt.add(ttsChunk);
			}
			msg.setInitialPrompt(initialPrompt);
						
			bitsPerSample = BitsPerSample.valueForString(paramsJson.getString(PerformAudioPassThru.KEY_BITS_PER_SAMPLE));
			msg.setBitsPerSample(bitsPerSample);


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
		return FunctionID.PERFORM_AUDIO_PASS_THRU;
	}

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();

		try {
			result.put(PerformAudioPassThru.KEY_AUDIO_PASS_THRU_DISPLAY_TEXT_1,           text1);
			result.put(PerformAudioPassThru.KEY_AUDIO_PASS_THRU_DISPLAY_TEXT_2,           text2);			
			result.put(PerformAudioPassThru.KEY_MUTE_AUDIO,      muteAudio);	
			result.put(PerformAudioPassThru.KEY_MAX_DURATION,    maxDuration);
			result.put(PerformAudioPassThru.KEY_AUDIO_TYPE,      audioType);
			result.put(PerformAudioPassThru.KEY_SAMPLING_RATE,   samplingRate);
			result.put(PerformAudioPassThru.KEY_BITS_PER_SAMPLE, bitsPerSample);
			result.put(PerformAudioPassThru.KEY_INITIAL_PROMPT,  paramsJson.getJSONArray(PerformAudioPassThru.KEY_INITIAL_PROMPT));
			
		} catch (JSONException e) {
			/* do nothing */
		}

		return result;
	}

	public void testInitialPrompt () {
		List<TTSChunk> copy = ( (PerformAudioPassThru) msg ).getInitialPrompt();
		assertEquals("List size didn't match expected size.", initialPrompt.size(), copy.size());
		
		for (int i = 0; i < initialPrompt.size(); i++) {
			assertEquals("Input value didn't match expected value.", initialPrompt.get(i), copy.get(i));
		}
	}
	
	public void testAudioPassThruDisplayText1 () {
		String copy = ( (PerformAudioPassThru) msg ).getAudioPassThruDisplayText1();
		
		assertEquals("Input value didn't match expected value.", this.text1, copy);
	}
	
	public void testAudioPassThruDisplayText2 () {
		String copy = ( (PerformAudioPassThru) msg ).getAudioPassThruDisplayText2();
		
		assertEquals("Input value didn't match expected value.", this.text2, copy);
	}
	
	public void testSamplingRate () {
		SamplingRate copy = ( (PerformAudioPassThru) msg ).getSamplingRate();
		
		assertEquals("Input value didn't match expected value.", this.samplingRate, copy);
	}
	
	public void testBitsPerSample () {
		BitsPerSample copy = ( (PerformAudioPassThru) msg ).getBitsPerSample();
		
		assertEquals("Input value didn't match expected value.", this.bitsPerSample, copy);
	}
	
	public void testAudioType () {
		AudioType copy = ( (PerformAudioPassThru) msg ).getAudioType();
		
		assertEquals("Input value didn't match expected value.", this.audioType, copy);
	}
	
	public void testMaxDuration () {
		int copy = ( (PerformAudioPassThru) msg ).getMaxDuration();

		assertEquals("Input value didn't match expected value.", this.maxDuration, copy);
	}
	
	public void testMuteAudio () {
		boolean copy = ( (PerformAudioPassThru) msg ).getMuteAudio();
		
		assertEquals("Input value didn't match expected value.", this.muteAudio, copy);
	}

	public void testNull() {
		PerformAudioPassThru msg = new PerformAudioPassThru();
		assertNotNull("Null object creation failed.", msg);

		testNullBase(msg);

		assertNull("Initial prompt wasn't set, but getter method returned an object.", msg.getInitialPrompt());
		assertNull("Text 1 wasn't set, but getter method returned an object.", msg.getAudioPassThruDisplayText1());
		assertNull("Text 2  wasn't set, but getter method returned an object.", msg.getAudioPassThruDisplayText2());
		assertNull("Sampling rate wasn't set, but getter method returned an object.", msg.getSamplingRate());
		assertNull("Bits per sample wasn't set, but getter method returned an object.", msg.getBitsPerSample());
		assertNull("Audio type wasn't set, but getter method returned an object.", msg.getAudioType());
		assertNull("Max duration wasn't set, but getter method returned an object.", msg.getMaxDuration());
		assertNull("Mute audio wasn't set, but getter method returned an object.", msg.getMuteAudio());
	}
	
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			PerformAudioPassThru cmd = new PerformAudioPassThru(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			
			assertEquals("Max duration doesn't match input duration", 
					(Integer) parameters.getInt(PerformAudioPassThru.KEY_MAX_DURATION), cmd.getMaxDuration());
			assertEquals("Audio pass-through display text 1 doesn't match input text", 
					parameters.getString(PerformAudioPassThru.KEY_AUDIO_PASS_THRU_DISPLAY_TEXT_1), cmd.getAudioPassThruDisplayText1());
			assertEquals("Audio pass-through display text 2 doesn't match input text", 
					parameters.getString(PerformAudioPassThru.KEY_AUDIO_PASS_THRU_DISPLAY_TEXT_2), cmd.getAudioPassThruDisplayText2());
			assertEquals("Mute audio doesn't match input mute audio", 
					(Boolean) parameters.getBoolean(PerformAudioPassThru.KEY_MUTE_AUDIO), cmd.getMuteAudio());
			assertEquals("Sampling rate doesn't match input rate", 
					parameters.getString(PerformAudioPassThru.KEY_SAMPLING_RATE), cmd.getSamplingRate().toString());
			assertEquals("Audio type doesn't match input type", 
					parameters.getString(PerformAudioPassThru.KEY_AUDIO_TYPE), cmd.getAudioType().toString());

			JSONArray ttsChunkArray = parameters.getJSONArray(PerformAudioPassThru.KEY_INITIAL_PROMPT);
			List<TTSChunk> ttsChunkList = new ArrayList<TTSChunk>();
			for (int index = 0; index < ttsChunkArray.length(); index++) {
	        	TTSChunk chunk = new TTSChunk(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)ttsChunkArray.get(index)) );
	        	ttsChunkList.add(chunk);
			}
			assertTrue("Initial prompt list doesn't match input prompt list",  Validator.validateTtsChunks(ttsChunkList, cmd.getInitialPrompt()));
			
			assertEquals("Bits per sample doesn't match input bits per sample", 
					parameters.getString(PerformAudioPassThru.KEY_BITS_PER_SAMPLE), cmd.getBitsPerSample().toString());
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }

}
