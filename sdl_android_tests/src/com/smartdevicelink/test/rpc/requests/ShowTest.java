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
import com.smartdevicelink.proxy.rpc.Image;
import com.smartdevicelink.proxy.rpc.Show;
import com.smartdevicelink.proxy.rpc.SoftButton;
import com.smartdevicelink.proxy.rpc.enums.TextAlignment;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;
import com.smartdevicelink.test.utils.Validator;

public class ShowTest extends BaseRpcTests {

	private String text1;
	private String text2;
	private String text3;
	private String text4;
	private String statusBar;
	private String mediaClock;
	private String mediaTrack;
	private TextAlignment textAlignment;
	private Image graphic;
	private Image secondaryGraphic;
	private List<String> customPresets;
	private List<SoftButton> softButtons = new ArrayList<SoftButton>();
    
	private JSONObject paramsJson;
	
	@SuppressWarnings("deprecation")
    @Override
	protected RPCMessage createMessage() {
		Show msg = new Show();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
		
		try {			
			text1 = paramsJson.getString(Show.KEY_MAIN_FIELD_1);
			msg.setMainField1(text1);
			text2 = paramsJson.getString(Show.KEY_MAIN_FIELD_2);
			msg.setMainField2(text2);
			text3 = paramsJson.getString(Show.KEY_MAIN_FIELD_3);
			msg.setMainField3(text3);
			text4 = paramsJson.getString(Show.KEY_MAIN_FIELD_4);
			msg.setMainField4(text4);
			statusBar = paramsJson.getString(Show.KEY_STATUS_BAR);
			msg.setStatusBar(statusBar);
			mediaClock = paramsJson.getString(Show.KEY_MEDIA_CLOCK);
			msg.setMediaClock(mediaClock);
			mediaTrack = paramsJson.getString(Show.KEY_MEDIA_TRACK);
			msg.setMediaTrack(mediaTrack);
			textAlignment = TextAlignment.valueForString(paramsJson.getString(Show.KEY_ALIGNMENT));
			msg.setAlignment(textAlignment);
			
			JSONObject graphicObj = paramsJson.getJSONObject(Show.KEY_GRAPHIC);
			graphic = new Image(JsonRPCMarshaller.deserializeJSONObject(graphicObj));
			msg.setGraphic(graphic);
			
			JSONObject secondaryGraphicObj = paramsJson.getJSONObject(Show.KEY_SECONDARY_GRAPHIC);
			secondaryGraphic = new Image(JsonRPCMarshaller.deserializeJSONObject(secondaryGraphicObj));
			msg.setSecondaryGraphic(secondaryGraphic);
			
			JSONArray customPresetsArray = paramsJson.getJSONArray(Show.KEY_CUSTOM_PRESETS);
			customPresets = JsonUtils.<String>createListFromJsonArray(customPresetsArray);
			msg.setCustomPresets(customPresets);
			
			JSONArray softButtonsArray = paramsJson.getJSONArray(Show.KEY_SOFT_BUTTONS);
			for (int index = 0; index < softButtonsArray.length(); index++) {
				SoftButton ttsChunk = new SoftButton(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)softButtonsArray.get(index)) );
				softButtons.add(ttsChunk);
			}
			msg.setSoftButtons(softButtons);
			
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
		return FunctionID.SHOW;
	}

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();

		try {   
			result.put(Show.KEY_MAIN_FIELD_1, text1);
			result.put(Show.KEY_MAIN_FIELD_2, text2);
			result.put(Show.KEY_MAIN_FIELD_3, text3);
			result.put(Show.KEY_MAIN_FIELD_4, text4);
			result.put(Show.KEY_STATUS_BAR, statusBar);
			result.put(Show.KEY_MEDIA_CLOCK, mediaClock);
			result.put(Show.KEY_MEDIA_TRACK, mediaTrack);			
			result.put(Show.KEY_GRAPHIC, paramsJson.getJSONObject(Show.KEY_GRAPHIC));
			result.put(Show.KEY_SECONDARY_GRAPHIC, paramsJson.getJSONObject(Show.KEY_SECONDARY_GRAPHIC));
			result.put(Show.KEY_ALIGNMENT, textAlignment);
			result.put(Show.KEY_CUSTOM_PRESETS, paramsJson.getJSONArray(Show.KEY_CUSTOM_PRESETS));			
			result.put(Show.KEY_SOFT_BUTTONS, paramsJson.getJSONArray(Show.KEY_SOFT_BUTTONS));
			
		} catch (JSONException e) {
			/* do nothing */
		}

		return result;
	}

	public void testText1 () {
		String copy = ( (Show) msg ).getMainField1();
		
		assertEquals("Data didn't match input data.", text1, copy);
	}
	
	public void testText2 () {
		String copy = ( (Show) msg ).getMainField2();
		
		assertEquals("Data didn't match input data.", text2, copy);
	}
	
	public void testText3 () {
		String copy = ( (Show) msg ).getMainField3();
		
		assertEquals("Data didn't match input data.", text3, copy);
	}
	
	public void testText4 () {
		String copy = ( (Show) msg ).getMainField4();
		
		assertEquals("Data didn't match input data.", text4, copy);
	}
	
	public void testStatusBar () {
		String copy = ( (Show) msg ).getStatusBar();
		
		assertEquals("Data didn't match input data.", statusBar, copy);
	}
	
	public void testMediaClock () {
		@SuppressWarnings("deprecation")
        String copy = ( (Show) msg ).getMediaClock();
		
		assertEquals("Data didn't match input data.", mediaClock, copy);
	}
	
	public void testTextAlignment () {
		TextAlignment copy = ( (Show) msg ).getAlignment();
		
		assertEquals("Data didn't match input data.", textAlignment, copy);
	}

	public void testImage1 () {
		Image copy = ( (Show) msg ).getGraphic();
		
		assertTrue("Input value didn't match expected value.", Validator.validateImage(graphic, copy));
	}
	
	public void testImage2 () {
		Image copy = ( (Show) msg ).getSecondaryGraphic();
		
		assertTrue("Input value didn't match expected value.", Validator.validateImage(secondaryGraphic, copy));
	}
	
	public void testCustomPresets () {
		List<String> copy = ( (Show) msg ).getCustomPresets();
		
		assertEquals("List size didn't match expected size.", customPresets.size(), copy.size());
		
		for (int i = 0; i < customPresets.size(); i++) {
			assertEquals("Input value didn't match expected value.", customPresets.get(i), copy.get(i));
		}
	}
	
	public void testMediaTrack () {
		String copy = ( (Show) msg ).getMediaTrack();
		
		assertEquals("Data didn't match input data.", mediaTrack, copy);
	}
	
	public void testSoftButtons () {
		List<SoftButton> copy = ( (Show) msg ).getSoftButtons();
		
		assertTrue("Input value didn't match expected value.", Validator.validateSoftButtons(softButtons, copy));
	}
	
	@SuppressWarnings("deprecation")
    public void testNull() {
		Show msg = new Show();
		assertNotNull("Null object creation failed.", msg);

		testNullBase(msg);

		assertNull("Text 1 wasn't set, but getter method returned an object.", msg.getMainField1());
		assertNull("Text 2 wasn't set, but getter method returned an object.", msg.getMainField2());
		assertNull("Text 3 wasn't set, but getter method returned an object.", msg.getMainField3());
		assertNull("Text 4 wasn't set, but getter method returned an object.", msg.getMainField4());
		assertNull("Status bar wasn't set, but getter method returned an object.", msg.getStatusBar());
		assertNull("Media clock wasn't set, but getter method returned an object.", msg.getMediaClock());
		assertNull("Alignment wasn't set, but getter method returned an object.", msg.getAlignment());
		assertNull("Image 1 wasn't set, but getter method returned an object.", msg.getGraphic());
		assertNull("Image 2 wasn't set, but getter method returned an object.", msg.getSecondaryGraphic());
		assertNull("Custom presets wasn't set, but getter method returned an object.", msg.getCustomPresets());
		assertNull("Media track wasn't set, but getter method returned an object.", msg.getMediaTrack());
		assertNull("Soft buttons wasn't set, but getter method returned an object.", msg.getSoftButtons());		
	}

    @SuppressWarnings("deprecation")
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			Show cmd = new Show(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			
			JSONObject graphic = parameters.getJSONObject(Show.KEY_GRAPHIC);
			Image referenceGraphic = new Image(JsonRPCMarshaller.deserializeJSONObject(graphic));
			assertTrue("Graphic doesn't match expected graphic", Validator.validateImage(referenceGraphic, cmd.getGraphic()));
			
			List<String> customPresetsList = JsonUtils.readStringListFromJsonObject(parameters, Show.KEY_CUSTOM_PRESETS);
			List<String> testPresetsList = cmd.getCustomPresets();
			assertEquals("Custom presets list length not same as reference presets list length", customPresetsList.size(), testPresetsList.size());
			assertTrue("Custom presets list doesn't match input presets list", Validator.validateStringList(customPresetsList, testPresetsList));

			assertEquals("Main field 1 doesn't match input field", parameters.getString(Show.KEY_MAIN_FIELD_1), cmd.getMainField1());
			assertEquals("Main field 2 doesn't match input field", parameters.getString(Show.KEY_MAIN_FIELD_2), cmd.getMainField2());
			assertEquals("Main field 3 doesn't match input field", parameters.getString(Show.KEY_MAIN_FIELD_3), cmd.getMainField3());
			assertEquals("Main field 4 doesn't match input field", parameters.getString(Show.KEY_MAIN_FIELD_4), cmd.getMainField4());
			assertEquals("Status bar doesn't match input status bar", parameters.getString(Show.KEY_STATUS_BAR), cmd.getStatusBar());
			assertEquals("Media clock doesn't match input clock", parameters.getString(Show.KEY_MEDIA_CLOCK), cmd.getMediaClock());
			assertEquals("Key alignment doesn't match input alignment", parameters.getString(Show.KEY_ALIGNMENT), cmd.getAlignment().toString());
			assertEquals("Media track doesn't match input track", parameters.getString(Show.KEY_MEDIA_TRACK), cmd.getMediaTrack());

			JSONObject secondaryGraphic = parameters.getJSONObject(Show.KEY_SECONDARY_GRAPHIC);
			Image referenceSecondaryGraphic = new Image(JsonRPCMarshaller.deserializeJSONObject(secondaryGraphic));
			assertTrue("Secondary graphic doesn't match expected graphic", Validator.validateImage(referenceSecondaryGraphic, cmd.getSecondaryGraphic()));
			
			JSONArray softButtonArray = parameters.getJSONArray(Show.KEY_SOFT_BUTTONS);
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
