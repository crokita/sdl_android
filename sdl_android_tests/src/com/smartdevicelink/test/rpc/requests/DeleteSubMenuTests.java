package com.smartdevicelink.test.rpc.requests;

import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.DeleteSubMenu;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;

public class DeleteSubMenuTests extends BaseRpcTests{

    private int menuId;
    
    private JSONObject paramsJson;

    @Override
    protected RPCMessage createMessage(){
        DeleteSubMenu msg = new DeleteSubMenu();
        paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
        
        menuId = JsonUtils.readIntegerFromJsonObject(paramsJson, DeleteSubMenu.KEY_MENU_ID);
        msg.setMenuID(menuId);

        return msg;
    }

    @Override
    protected String getMessageType(){
        return RPCMessage.KEY_REQUEST;
    }

    @Override
    protected String getCommandType(){
        return FunctionID.DELETE_SUB_MENU;
    }

    @Override
    protected JSONObject getExpectedParameters(int sdlVersion){
        JSONObject result = new JSONObject();

        try{
            result.put(DeleteSubMenu.KEY_MENU_ID, menuId);
        }catch(JSONException e){
            /* do nothing */
        }

        return result;
    }

    public void testMenuId(){
        int menuId = ( (DeleteSubMenu) msg ).getMenuID();
        assertEquals("Menu ID didn't match input menu ID.", this.menuId, menuId);
    }

    public void testNull(){
        DeleteSubMenu msg = new DeleteSubMenu();
        assertNotNull("Null object creation failed.", msg);

        testNullBase(msg);

        assertNull("Menu ID wasn't set, but getter method returned an object.", msg.getMenuID());
    }
    
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			DeleteSubMenu cmd = new DeleteSubMenu(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());

			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			assertEquals("Menu ID doesn't match input ID", (Integer) parameters.getInt(DeleteSubMenu.KEY_MENU_ID), cmd.getMenuID());
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }
    
}
