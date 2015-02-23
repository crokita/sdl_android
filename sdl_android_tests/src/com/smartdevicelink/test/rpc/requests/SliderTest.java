package com.smartdevicelink.test.rpc.requests;

import java.util.Hashtable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.Slider;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;
import com.smartdevicelink.test.utils.Validator;

public class SliderTest extends BaseRpcTests {
	
	private int numTicks;
	private int position;
	private int timeout;
	private String header;
	private List<String> footer;

	private JSONObject paramsJson;
	
	@Override
	protected RPCMessage createMessage() {
		Slider msg = new Slider();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
		
		try {
			numTicks = paramsJson.getInt(Slider.KEY_NUM_TICKS);
			msg.setNumTicks(numTicks);
			position = paramsJson.getInt(Slider.KEY_POSITION);
			msg.setPosition(position);
			timeout = paramsJson.getInt(Slider.KEY_TIMEOUT);
			msg.setTimeout(timeout);
			header = paramsJson.getString(Slider.KEY_SLIDER_HEADER);
			msg.setSliderHeader(header);
			
			JSONArray footerArray = paramsJson.getJSONArray(Slider.KEY_SLIDER_FOOTER);
			footer = JsonUtils.<String>createListFromJsonArray(footerArray);
			msg.setSliderFooter(footer);
			
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
		return FunctionID.SLIDER;
	}

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();

		try {
			result.put(Slider.KEY_SLIDER_HEADER, header);
			result.put(Slider.KEY_SLIDER_FOOTER, paramsJson.getJSONArray(Slider.KEY_SLIDER_FOOTER));
			result.put(Slider.KEY_POSITION, position);
			result.put(Slider.KEY_TIMEOUT, timeout);
			result.put(Slider.KEY_NUM_TICKS, numTicks);
			
		} catch (JSONException e) {
			/* do nothing */
		}

		return result;
	}

	public void testHeader () {
		String copy = ( (Slider) msg ).getSliderHeader();
		
		assertEquals("Data didn't match input data.", header, copy);
	}
	
	public void testFooter () {
		List<String> copy = ( (Slider) msg ).getSliderFooter();
		
	    assertTrue("Input value didn't match expected value.", Validator.validateStringList(footer, copy));
	}
	
	public void testPosition () {
		Integer copy = ( (Slider) msg ).getPosition();
		
		assertEquals("Data didn't match input data.", (Integer) position, copy);
	}
	
	public void testTimeout () {
		Integer copy = ( (Slider) msg ).getTimeout();
		
		assertEquals("Data didn't match input data.", (Integer) timeout, copy);
	}
	
	public void testNumTicks () {
		Integer copy = ( (Slider) msg ).getNumTicks();
		
		assertEquals("Data didn't match input data.", (Integer) numTicks, copy);
	}

	public void testNull() {
		Slider msg = new Slider();
		assertNotNull("Null object creation failed.", msg);

		testNullBase(msg);

		assertNull("Header wasn't set, but getter method returned an object.", msg.getSliderHeader());
		assertNull("Footer wasn't set, but getter method returned an object.", msg.getSliderFooter());
		assertNull("Position wasn't set, but getter method returned an object.", msg.getPosition());
		assertNull("Timeout wasn't set, but getter method returned an object.", msg.getTimeout());
		assertNull("Number of ticks wasn't set, but getter method returned an object.", msg.getNumTicks());
	}
	
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			Slider cmd = new Slider(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			assertEquals("Tick number doesn't match input number", (Integer) parameters.getInt(Slider.KEY_NUM_TICKS), cmd.getNumTicks());
			assertEquals("Slider header doesn't match input header", parameters.getString(Slider.KEY_SLIDER_HEADER), cmd.getSliderHeader());

			List<String> sliderFooterList = JsonUtils.readStringListFromJsonObject(parameters, Slider.KEY_SLIDER_FOOTER);
			List<String> testFooterList = cmd.getSliderFooter();
			assertEquals("Slider footer list length not same as reference footer list length", sliderFooterList.size(), testFooterList.size());
			assertTrue("Slider footer list doesn't match input footer list", Validator.validateStringList(sliderFooterList, testFooterList));
			
			assertEquals("Position doesn't match input position", (Integer) parameters.getInt(Slider.KEY_POSITION), cmd.getPosition());
			assertEquals("Timeout doesn't match input timeout", (Integer) parameters.getInt(Slider.KEY_TIMEOUT), cmd.getTimeout());
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }
	
}
