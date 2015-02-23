package com.smartdevicelink.test.rpc.requests;

import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.AddSubMenu;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;

public class AddSubmenuTests extends BaseRpcTests{

    private int    menuId;
    private String menuName;
    private int    position;

	private JSONObject paramsJson;
	
    @Override
    protected RPCMessage createMessage(){
        AddSubMenu msg = new AddSubMenu();
        paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
        
        menuId = JsonUtils.readIntegerFromJsonObject(paramsJson, AddSubMenu.KEY_MENU_ID);
        msg.setMenuID(menuId);
        menuName = JsonUtils.readStringFromJsonObject(paramsJson, AddSubMenu.KEY_MENU_NAME);
        msg.setMenuName(menuName);
        position = JsonUtils.readIntegerFromJsonObject(paramsJson, AddSubMenu.KEY_POSITION);
        msg.setPosition(position);

        return msg;
    }

    @Override
    protected String getMessageType(){
        return RPCMessage.KEY_REQUEST;
    }

    @Override
    protected String getCommandType(){
        return FunctionID.ADD_SUB_MENU;
    }

    @Override
    protected JSONObject getExpectedParameters(int sdlVersion){
        JSONObject result = new JSONObject();

        try{
            result.put(AddSubMenu.KEY_MENU_ID, menuId);
            result.put(AddSubMenu.KEY_MENU_NAME, paramsJson.getString(AddSubMenu.KEY_MENU_NAME));
            result.put(AddSubMenu.KEY_POSITION, position);
        }catch(JSONException e){
            /* do nothing */
        }

        return result;
    }

    public void testMenuId(){
        int menuId = ( (AddSubMenu) msg ).getMenuID();
        assertEquals("Menu ID didn't match input menu ID.", this.menuId, menuId);
    }

    public void testMenuName(){
        String menuName = ( (AddSubMenu) msg ).getMenuName();
        assertEquals("Menu name didn't match input menu name.", this.menuName, menuName);
    }

    public void testMenuPosition(){
        int position = ( (AddSubMenu) msg ).getPosition();
        assertEquals("Position didn't match input position.", this.position, position);
    }

    public void testNull(){
        AddSubMenu msg = new AddSubMenu();
        assertNotNull("Null object creation failed.", msg);

        testNullBase(msg);

        assertNull("Menu ID wasn't set, but getter method returned an object.", msg.getMenuID());
        assertNull("Menu name wasn't set, but getter method returned an object.", msg.getMenuName());
        assertNull("Position wasn't set, but getter method returned an object.", msg.getPosition());
    }
    
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			AddSubMenu cmd = new AddSubMenu(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (int) body.getInt(RPCMessage.KEY_CORRELATION_ID), (int) cmd.getCorrelationID());

			assertEquals("Menu ID doesn't match input ID", (int) paramsJson.getInt(AddSubMenu.KEY_MENU_ID), (int) cmd.getMenuID());
			assertEquals("Position doesn't match input position", JsonUtils.readIntegerFromJsonObject(paramsJson, AddSubMenu.KEY_POSITION), cmd.getPosition());
			assertEquals("Menu name doesn't match input name", JsonUtils.readStringFromJsonObject(paramsJson, AddSubMenu.KEY_MENU_NAME), cmd.getMenuName());
			
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }

}
