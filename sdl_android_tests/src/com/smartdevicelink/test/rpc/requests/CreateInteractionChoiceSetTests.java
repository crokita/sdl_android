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
import com.smartdevicelink.proxy.rpc.Choice;
import com.smartdevicelink.proxy.rpc.CreateInteractionChoiceSet;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.Validator;

public class CreateInteractionChoiceSetTests extends BaseRpcTests{

    private Integer    			choiceId;
    private List<Choice>        choiceList = new ArrayList<Choice>();

    private JSONObject paramsJson;
    
    @Override
    protected RPCMessage createMessage(){
        CreateInteractionChoiceSet msg = new CreateInteractionChoiceSet();
        paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());

        try {
        	choiceId = paramsJson.getInt(CreateInteractionChoiceSet.KEY_INTERACTION_CHOICE_SET_ID);
	        msg.setInteractionChoiceSetID(choiceId);
	        
			JSONArray choiceArray = paramsJson.getJSONArray(CreateInteractionChoiceSet.KEY_CHOICE_SET);
			for (int index = 0; index < choiceArray.length(); index++) {
				Choice choice = new Choice(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)choiceArray.get(index)) );
				choiceList.add(choice);
			}
			msg.setChoiceSet(choiceList);
			
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
        return FunctionID.CREATE_INTERACTION_CHOICE_SET;
    }

    @Override
    protected JSONObject getExpectedParameters(int sdlVersion){
        JSONObject result = new JSONObject();

        try{
            result.put(CreateInteractionChoiceSet.KEY_INTERACTION_CHOICE_SET_ID, choiceId);
            result.put(CreateInteractionChoiceSet.KEY_CHOICE_SET, paramsJson.getJSONArray(CreateInteractionChoiceSet.KEY_CHOICE_SET));
        }catch(JSONException e){
            /* do nothing */
        }

        return result;
    }

    public void testChoiceSetId(){
    	Integer copy = ( (CreateInteractionChoiceSet) msg ).getInteractionChoiceSetID();
        assertEquals("Command ID didn't match input command ID.", choiceId, copy);
    }

    public void testChoiceList(){
        List<Choice> copy = ( (CreateInteractionChoiceSet) msg ).getChoiceSet();

        assertEquals("Choice list size didn't match expected size.", choiceList.size(), copy.size());
        for(int i = 0; i < copy.size(); i++){
            assertTrue("Choice at index " + i + " didn't match expected value.", Validator.validateChoice(choiceList.get(i), copy.get(i)));
        }
    }

    public void testNull(){
        CreateInteractionChoiceSet msg = new CreateInteractionChoiceSet();
        assertNotNull("Null object creation failed.", msg);

        testNullBase(msg);

        assertNull("Choice list wasn't set, but getter method returned an object.", msg.getChoiceSet());
        assertNull("Choice set ID wasn't set, but getter method returned an object.", msg.getInteractionChoiceSetID());
    }

    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			CreateInteractionChoiceSet cmd = new CreateInteractionChoiceSet(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());

			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			assertEquals("Interaction choice set ID doesn't match input ID", 
					(Integer) parameters.getInt(CreateInteractionChoiceSet.KEY_INTERACTION_CHOICE_SET_ID), cmd.getInteractionChoiceSetID());
			
			JSONArray choiceSetArray = parameters.getJSONArray(CreateInteractionChoiceSet.KEY_CHOICE_SET);
			for (int index = 0; index < choiceSetArray.length(); index++) {
				Choice chunk = new Choice(JsonRPCMarshaller.deserializeJSONObject( (JSONObject)choiceSetArray.get(index)) );
				assertTrue("Choice item doesn't match input Choice item",  Validator.validateChoice(chunk, cmd.getChoiceSet().get(index)) );
			}
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }    

}
