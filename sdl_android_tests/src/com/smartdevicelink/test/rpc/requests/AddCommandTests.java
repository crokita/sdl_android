package com.smartdevicelink.test.rpc.requests;

import java.util.Hashtable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.AddCommand;
import com.smartdevicelink.proxy.rpc.Image;
import com.smartdevicelink.proxy.rpc.MenuParams;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;
import com.smartdevicelink.test.utils.Validator;

public class AddCommandTests extends BaseRpcTests{

    private Image                  image;
    private MenuParams             menuParams;
    private List<String>         vrCommands;

	private static JSONObject paramsJson;
	
    @Override
    protected RPCMessage createMessage(){
        AddCommand msg = new AddCommand();
        paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
        
		try {			
			JSONObject imageJson = JsonUtils.readJsonObjectFromJsonObject(paramsJson, AddCommand.KEY_CMD_ICON);
			image = new Image(JsonRPCMarshaller.deserializeJSONObject(imageJson));
			msg.setCmdIcon(image);
			JSONObject menuParamsJson = JsonUtils.readJsonObjectFromJsonObject(paramsJson, AddCommand.KEY_MENU_PARAMS);
			menuParams = new MenuParams(JsonRPCMarshaller.deserializeJSONObject(menuParamsJson));
			msg.setMenuParams(menuParams);
			JSONArray vrCommandsArray = JsonUtils.readJsonArrayFromJsonObject(paramsJson, AddCommand.KEY_VR_COMMANDS);
			vrCommands = JsonUtils.<String>createListFromJsonArray(vrCommandsArray);
			msg.setVrCommands(vrCommands);
			Integer cmdIdJson = paramsJson.getInt(AddCommand.KEY_CMD_ID);
			msg.setCmdID( cmdIdJson );

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
        return FunctionID.ADD_COMMAND;
    }

    @Override
    protected JSONObject getExpectedParameters(int sdlVersion){
        JSONObject result = new JSONObject();
        
        try{
            result.put(AddCommand.KEY_CMD_ICON, JsonUtils.readJsonObjectFromJsonObject(paramsJson, AddCommand.KEY_CMD_ICON));
            result.put(AddCommand.KEY_MENU_PARAMS, JsonUtils.readJsonObjectFromJsonObject(paramsJson, AddCommand.KEY_MENU_PARAMS));
            result.put(AddCommand.KEY_VR_COMMANDS, JsonUtils.readJsonArrayFromJsonObject(paramsJson, AddCommand.KEY_VR_COMMANDS));
            result.put(AddCommand.KEY_CMD_ID, paramsJson.getInt(AddCommand.KEY_CMD_ID));
        }catch(JSONException e){
            /* do nothing  */
        }

        return result;
    }

    public void testCommandId(){
        int cmdId = ( (AddCommand) msg ).getCmdID();
        assertEquals("Command ID didn't match input command ID.", (int) JsonUtils.readIntegerFromJsonObject(paramsJson, AddCommand.KEY_CMD_ID), cmdId);
    }

    public void testMenuParams(){
        MenuParams copy = ( (AddCommand) msg ).getMenuParams();

        assertNotNull("Menu parameters were null.", copy);
        
        // MenuParams doesn't override equals, so have to do a manual compare of all variables
        assertTrue("Menu parameters did not match input menu parameters.",
                Validator.validateMenuParams(menuParams, copy));
    }

    public void testImage(){
        Image copy = ( (AddCommand) msg ).getCmdIcon();

        assertNotNull("Image was null.", copy);

        // Image doesn't override equals, so have to do a manual compare of all variables
        assertTrue("Image did not match input image.", Validator.validateImage(copy, image));
    }

    public void testVrCommands(){
        List<String> copy = ( (AddCommand) msg ).getVrCommands();

        assertNotNull("VR commands was null.", copy);
        assertEquals("VR commands size doesn't match input size.", vrCommands.size(), copy.size());
        assertTrue("VR commands did not match input VR commands.", Validator.validateStringList(copy, vrCommands));
    }

    public void testNull(){
        AddCommand msg = new AddCommand();
        assertNotNull("Null object creation failed.", msg);

        testNullBase(msg);

        assertNull("Image wasn't set, but getter method returned an object.", msg.getCmdIcon());
        assertNull("Command ID wasn't set, but getter method returned an object.", msg.getCmdID());
        assertNull("Menu params weren't set, but getter method returned an object.", msg.getMenuParams());
        assertNull("VR commands weren't set, but getter method returned an object.", msg.getVrCommands());
    }
    
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);

    	try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			AddCommand cmd = new AddCommand(hash);
			
			JSONObject body = JsonUtils.readJsonObjectFromJsonObject(commandJson, getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", JsonUtils.readStringFromJsonObject(body, RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", JsonUtils.readIntegerFromJsonObject(body, RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());

			List<String> vrCommandsList = JsonUtils.readStringListFromJsonObject(paramsJson, AddCommand.KEY_VR_COMMANDS);
			List<String> testCommandsList = cmd.getVrCommands();
			assertEquals("VR command list length not same as reference VR command list length", vrCommandsList.size(), testCommandsList.size());
			assertTrue("VR command list doesn't match input command list", Validator.validateStringList(vrCommandsList, testCommandsList));
			
			assertEquals("Command ID doesn't match input ID", JsonUtils.readIntegerFromJsonObject(paramsJson, AddCommand.KEY_CMD_ID), cmd.getCmdID());
			
			JSONObject menuParams = JsonUtils.readJsonObjectFromJsonObject(paramsJson, AddCommand.KEY_MENU_PARAMS);
			MenuParams referenceMenuParams = new MenuParams(JsonRPCMarshaller.deserializeJSONObject(menuParams));
			assertTrue("Menu params doesn't match expected menu params", Validator.validateMenuParams(referenceMenuParams, cmd.getMenuParams()));
			
			JSONObject cmdIcon = JsonUtils.readJsonObjectFromJsonObject(paramsJson, AddCommand.KEY_CMD_ICON);
			Image referenceCmdIcon = new Image(JsonRPCMarshaller.deserializeJSONObject(cmdIcon));
			assertTrue("Image doesn't match expected image", Validator.validateImage(referenceCmdIcon, cmd.getCmdIcon()));
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }
}
