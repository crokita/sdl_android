package com.smartdevicelink.test.rpc.requests;

import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.PutFile;
import com.smartdevicelink.proxy.rpc.enums.FileType;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;
import com.smartdevicelink.test.utils.Validator;

public class PutFileTest extends BaseRpcTests {

	private boolean 	isPersistent;
	private boolean 	systemFile;
	private FileType 	fileType;
	private String 		fileName;
	private int 		offset;
	private int		 	length;
	private static final byte[] bulkData = new byte[]{0x00, 0x01, 0x02};
	
	private JSONObject paramsJson;
	
	@Override
	protected RPCMessage createMessage() {
		PutFile msg = new PutFile();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
		
		isPersistent = JsonUtils.readBooleanFromJsonObject(paramsJson, PutFile.KEY_PERSISTENT_FILE);
		msg.setPersistentFile(isPersistent);
		systemFile = JsonUtils.readBooleanFromJsonObject(paramsJson, PutFile.KEY_SYSTEM_FILE);
		msg.setSystemFile(systemFile);
		fileType = FileType.valueForString(JsonUtils.readStringFromJsonObject(paramsJson, PutFile.KEY_FILE_TYPE));
		msg.setFileType(fileType);
		fileName = JsonUtils.readStringFromJsonObject(paramsJson, PutFile.KEY_SDL_FILE_NAME);
		msg.setSdlFileName(fileName);
		offset = JsonUtils.readIntegerFromJsonObject(paramsJson, PutFile.KEY_OFFSET);
		msg.setOffset(offset);
		length = JsonUtils.readIntegerFromJsonObject(paramsJson, PutFile.KEY_LENGTH);
		msg.setLength(length);
		
		msg.setBulkData(bulkData);

		return msg;
	}

	@Override
	protected String getMessageType() {
		return RPCMessage.KEY_REQUEST;
	}

	@Override
	protected String getCommandType() {
		return FunctionID.PUT_FILE;
	}

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();

		try {
			result.put(PutFile.KEY_FILE_TYPE, fileType);
			result.put(PutFile.KEY_PERSISTENT_FILE, isPersistent);
			result.put(PutFile.KEY_SYSTEM_FILE, systemFile);
			result.put(PutFile.KEY_SDL_FILE_NAME, fileName);
			result.put(PutFile.KEY_OFFSET, offset);
			result.put(PutFile.KEY_LENGTH, length);
			
		} catch (JSONException e) {
			/* do nothing */
		}

		return result;
	}
	
	public void testBulkData () {
		byte[] copy = ( (PutFile) msg ).getBulkData();
		
		assertTrue("Data didn't match input data.", Validator.validateBulkData(copy, bulkData));
	}
	
	public void testFileType () {
		FileType copy = ( (PutFile) msg ).getFileType();
		
		assertEquals("Data didn't match input data.", fileType, copy);
	}
	
	public void testIsPersistent () {
		boolean copy = ( (PutFile) msg ).getPersistentFile();
		
		assertEquals("Data didn't match input data.", isPersistent, copy);
	}
	
	public void testSystemFile () {
		boolean copy = ( (PutFile) msg ).getSystemFile();
		
		assertEquals("Data didn't match input data.", systemFile, copy);
	}
	
	public void testSdlFileName () {
		String copy = ( (PutFile) msg ).getSdlFileName();
		
		assertEquals("Data didn't match input data.", fileName, copy);
	}
	
	public void testOffset () {
		Integer copy = ( (PutFile) msg ).getOffset();
		
		assertEquals("Data didn't match input data.", (Integer) offset, copy);
	}
	
	public void testLength () {
		Integer copy = ( (PutFile) msg ).getLength();
		
		assertEquals("Data didn't match input data.", (Integer) length, copy);
	}

	public void testNull() {
		PutFile msg = new PutFile();
		assertNotNull("Null object creation failed.", msg);

		testNullBase(msg);

		assertNull("File type wasn't set, but getter method returned an object.", msg.getFileType());
		assertNull("Persistent file wasn't set, but getter method returned an object.", msg.getPersistentFile());
		assertNull("System file wasn't set, but getter method returned an object.", msg.getSystemFile());
		assertNull("Offset wasn't set, but getter method returned an object.", msg.getOffset());
		assertNull("Length name wasn't set, but getter method returned an object.", msg.getLength());
	}
	
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			PutFile cmd = new PutFile(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			
			assertEquals("Persistent file doesn't match input persistent file", 
					(Boolean) parameters.getBoolean(PutFile.KEY_PERSISTENT_FILE), cmd.getPersistentFile());
			assertEquals("System file doesn't match input system file", 
					(Boolean) parameters.getBoolean(PutFile.KEY_SYSTEM_FILE), cmd.getSystemFile());
			assertEquals("File type doesn't match input type", 
					parameters.getString(PutFile.KEY_FILE_TYPE), cmd.getFileType().toString());
			assertEquals("SDL File name doesn't match input file name", 
					parameters.getString(PutFile.KEY_SDL_FILE_NAME), cmd.getSdlFileName());
			assertEquals("Offset doesn't match input offset", 
					(Integer) parameters.getInt(PutFile.KEY_OFFSET), cmd.getOffset());
			assertEquals("Length doesn't match input length", 
					(Integer) parameters.getInt(PutFile.KEY_LENGTH), cmd.getLength());
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }

}
