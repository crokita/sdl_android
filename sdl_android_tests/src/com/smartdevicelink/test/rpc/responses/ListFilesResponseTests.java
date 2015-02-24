package com.smartdevicelink.test.rpc.responses;

import java.util.Hashtable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.ListFilesResponse;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;
import com.smartdevicelink.test.utils.Validator;

public class ListFilesResponseTests extends BaseRpcTests{

    private List<String> fileNames;
    private int          spaceAvailable;

    private JSONObject paramsJson;
    
    @Override
    protected RPCMessage createMessage(){
        ListFilesResponse msg = new ListFilesResponse();
        paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
        
        
		JSONArray fileNamesArray = JsonUtils.readJsonArrayFromJsonObject(paramsJson, ListFilesResponse.KEY_FILENAMES);
		fileNames = JsonUtils.<String>createListFromJsonArray(fileNamesArray);
		msg.setFilenames(fileNames);
		
		spaceAvailable = JsonUtils.readIntegerFromJsonObject(paramsJson, ListFilesResponse.KEY_SPACE_AVAILABLE);
        msg.setSpaceAvailable(spaceAvailable);

        return msg;
    }

    @Override
    protected String getMessageType(){
        return RPCMessage.KEY_RESPONSE;
    }

    @Override
    protected String getCommandType(){
        return FunctionID.LIST_FILES;
    }

    @Override
    protected JSONObject getExpectedParameters(int sdlVersion){
        JSONObject result = new JSONObject();

        try{
            result.put(ListFilesResponse.KEY_FILENAMES, JsonUtils.createJsonArray(fileNames));
            result.put(ListFilesResponse.KEY_SPACE_AVAILABLE, spaceAvailable);
        }catch(JSONException e){
            /* do nothing */
        }

        return result;
    }

    public void testFilenames(){
        List<String> filenames = ( (ListFilesResponse) msg ).getFilenames();
        assertEquals("Filenames size didn't match expected size.", fileNames.size(), filenames.size());
        assertTrue("Filenames didn't match input filenames.", Validator.validateStringList(fileNames, filenames));
    }

    public void testSpaceAvailable(){
        int spaceAvailable = ( (ListFilesResponse) msg ).getSpaceAvailable();
        assertEquals("Space available didn't match expected space available.", spaceAvailable, spaceAvailable);
    }

    public void testNull(){
        ListFilesResponse msg = new ListFilesResponse();
        assertNotNull("Null object creation failed.", msg);

        testNullBase(msg);

        assertNull("Filenames wasn't set, but getter method returned an object.", msg.getFilenames());
        assertNull("Space available wasn't set, but getter method returned an object.", msg.getSpaceAvailable());
    }
    
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			ListFilesResponse cmd = new ListFilesResponse(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			
			List<String> fileNamesList = JsonUtils.readStringListFromJsonObject(parameters, ListFilesResponse.KEY_FILENAMES);
			List<String> testNamesList = cmd.getFilenames();
			assertEquals("File name list length not same as reference file name list", fileNamesList.size(), testNamesList.size());
			assertTrue("File name list doesn't match input file name list", Validator.validateStringList(fileNamesList, testNamesList));
			assertEquals("Space available doesn't match input space available", 
					(Integer) paramsJson.getInt(ListFilesResponse.KEY_SPACE_AVAILABLE), cmd.getSpaceAvailable());

		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }

}
