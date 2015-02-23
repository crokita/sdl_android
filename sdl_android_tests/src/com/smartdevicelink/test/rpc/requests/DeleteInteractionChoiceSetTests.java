package com.smartdevicelink.test.rpc.requests;

import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.DeleteInteractionChoiceSet;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;

public class DeleteInteractionChoiceSetTests extends BaseRpcTests{

    private int choiceSetId;

    private JSONObject paramsJson;
    
    @Override
    protected RPCMessage createMessage(){
        DeleteInteractionChoiceSet msg = new DeleteInteractionChoiceSet();
        paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());

        choiceSetId = JsonUtils.readIntegerFromJsonObject(paramsJson, DeleteInteractionChoiceSet.KEY_INTERACTION_CHOICE_SET_ID);
        msg.setInteractionChoiceSetID(choiceSetId);

        return msg;
    }

    @Override
    protected String getMessageType(){
        return RPCMessage.KEY_REQUEST;
    }

    @Override
    protected String getCommandType(){
        return FunctionID.DELETE_INTERACTION_CHOICE_SET;
    }

    @Override
    protected JSONObject getExpectedParameters(int sdlVersion){
        JSONObject result = new JSONObject();

        try{
            result.put(DeleteInteractionChoiceSet.KEY_INTERACTION_CHOICE_SET_ID, choiceSetId);
        }catch(JSONException e){
            /* do nothing */
        }

        return result;
    }

    public void testChoiceSetId(){
        int choiceSetId = ( (DeleteInteractionChoiceSet) msg ).getInteractionChoiceSetID();
        assertEquals("Choice set ID didn't match input choice set ID.", this.choiceSetId, choiceSetId);
    }

    public void testNull(){
        DeleteInteractionChoiceSet msg = new DeleteInteractionChoiceSet();
        assertNotNull("Null object creation failed.", msg);

        testNullBase(msg);

        assertNull("Choice set ID wasn't set, but getter method returned an object.", msg.getInteractionChoiceSetID());
    }
    
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			DeleteInteractionChoiceSet cmd = new DeleteInteractionChoiceSet(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());

			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			assertEquals("Command ID doesn't match input ID", 
					(Integer) parameters.getInt(DeleteInteractionChoiceSet.KEY_INTERACTION_CHOICE_SET_ID), cmd.getInteractionChoiceSetID());
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }
    
}
