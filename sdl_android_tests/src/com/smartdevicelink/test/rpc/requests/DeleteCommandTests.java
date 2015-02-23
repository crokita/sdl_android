package com.smartdevicelink.test.rpc.requests;

import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.DeleteCommand;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;

public class DeleteCommandTests extends BaseRpcTests{

    private int commandId;

    private JSONObject paramsJson;
    
    @Override
    protected RPCMessage createMessage(){
        DeleteCommand msg = new DeleteCommand();
        paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
        
        commandId = JsonUtils.readIntegerFromJsonObject(paramsJson, DeleteCommand.KEY_CMD_ID);
        msg.setCmdID(commandId);

        return msg;
    }

    @Override
    protected String getMessageType(){
        return RPCMessage.KEY_REQUEST;
    }

    @Override
    protected String getCommandType(){
        return FunctionID.DELETE_COMMAND;
    }

    @Override
    protected JSONObject getExpectedParameters(int sdlVersion){
        JSONObject result = new JSONObject();

        try{
            result.put(DeleteCommand.KEY_CMD_ID, commandId);
        }catch(JSONException e){
            /* do nothing */
        }

        return result;
    }

    public void testCommandId(){
        int commandId = ( (DeleteCommand) msg ).getCmdID();
        assertEquals("Command ID didn't match input command ID.", this.commandId, commandId);
    }

    public void testNull(){
        DeleteCommand msg = new DeleteCommand();
        assertNotNull("Null object creation failed.", msg);

        testNullBase(msg);

        assertNull("Command ID wasn't set, but getter method returned an object.", msg.getCmdID());
    }
    
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			DeleteCommand cmd = new DeleteCommand(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());

			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			assertEquals("Command ID doesn't match input ID", (Integer) parameters.getInt(DeleteCommand.KEY_CMD_ID), cmd.getCmdID());
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }

}
