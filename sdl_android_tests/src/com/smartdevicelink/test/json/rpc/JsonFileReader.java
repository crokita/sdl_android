package com.smartdevicelink.test.json.rpc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.test.utils.JsonUtils;

public class JsonFileReader {
	private static final String PATH = "src/com/smartdevicelink/test/json/rpc/";
	private static final String EXT = ".json";
	private static HashMap<String, JSONObject> jsonMap = new HashMap<String, JSONObject>();
	
	//load all the json files into a hashmap. the string parameter is the command name
	static {
        File directory = new File(PATH);
        
        //get all the files from a directory
        File[] fileList = directory.listFiles();
 
        for (File file : fileList) {
            if (file.isFile() && getFileExtension(file).equals(".json")) { 
                //only deal with json files and remove the extension
            	//TODO: is there a way to grab the file's name parameter instead of relying on the file name itself?
            	String id = file.getName().substring(0, file.getName().indexOf(".")); 
            	
            	//attempt to read the file's request, response and notification message types
            	//if it doesn't exist, it will return null. just ignore it
            	JSONObject requestJson = readId(id, RPCMessage.KEY_REQUEST);
            	
            	if (requestJson != null) {
            		jsonMap.put(id + RPCMessage.KEY_REQUEST, requestJson);
            	}
        		JSONObject responseJson = readId(id, RPCMessage.KEY_RESPONSE);
            	if (responseJson != null) {
            		jsonMap.put(id + RPCMessage.KEY_RESPONSE, responseJson);
            	}
        		JSONObject notificationJson = readId(id, RPCMessage.KEY_NOTIFICATION);
            	if (notificationJson != null) {
            		jsonMap.put(id + RPCMessage.KEY_NOTIFICATION, notificationJson);
            	}

            }
        }
        
	}
	
	public static JSONObject readId (String id, String type) {
		String fileName = id + EXT;
		File file = new File(PATH + fileName);

		JSONObject commandJsonWithType = null;
		JSONObject commandJson = null;
		
		try {
			//get the file which holds the desired object
			FileInputStream fileStream = new FileInputStream(file);
			BufferedInputStream bufferStream = new BufferedInputStream(fileStream);
			byte[] buffer = new byte[(int)file.length()];
			bufferStream.read(buffer);
			fileStream.close();
			commandJson = JsonUtils.createJsonObject(buffer);
			
			//now use the type parameter to get the specific message type
			//it also makes sure to return it with the message type in the top level because that's needed to eventually make this into a class object

			commandJson = JsonUtils.readJsonObjectFromJsonObject(commandJson, type);
			if (commandJson == null) {
				return null;
			}
			commandJsonWithType = new JSONObject();
			commandJsonWithType = commandJsonWithType.put(type, commandJson);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		
		return commandJsonWithType;

	}
	
	private static String getFileExtension (File file) {
	    String name = file.getName();
	    int lastIndexOf = name.lastIndexOf(".");
	    if (lastIndexOf == -1) {
	        return ""; // empty extension
	    }
	    return name.substring(lastIndexOf);
	}
	/*
	//given a command, a message type and the parameter name it will retrieve the appropriate data from the hashmap. returns null if not found
	//this method is suited only for getting RPC fields inside "parameters"
	public static Object get(String command, String messageType, String... paramNames) {
		JSONObject commandJson = jsonMap.get(command + messageType);
	    commandJson = JsonUtils.readJsonObjectFromJsonObject(commandJson, messageType);
	    //go inside the parameter field of the JSON
	    commandJson = JsonUtils.readJsonObjectFromJsonObject(commandJson, RPCMessage.KEY_PARAMETERS);
	    //continuously move deeper into the JSONObject for every parameter name given
	    for (String paramName: paramNames) {
		    commandJson = JsonUtils.readJsonObjectFromJsonObject(commandJson, paramName);
	    }
	    return commandJson;
	}

	//same as the get() above, only it retrieves data from the JSONObject given instead of from the hashmap
	public static Object get(JSONObject command, String... paramNames) {
	    JSONObject commandJson = command;
	    //continuously move deeper into the JSONObject for every parameter name given
	    for (String paramName: paramNames) {
		    commandJson = JsonUtils.readJsonObjectFromJsonObject(commandJson, paramName);
	    }
	    return commandJson;
	}
	*/
	
	//returns the whole JSONObject for a given RPC (includes the message type) as a JsonExtractor
	public static JSONObject get(String command, String messageType) {
		JSONObject commandJson = jsonMap.get(command + messageType);
	    return commandJson;
	}
	
	//returns a the "parameters" field of the given RPC's JSONObject as a JsonExtractor
	public static JSONObject getParams(String command, String messageType) {
		JSONObject commandJson = jsonMap.get(command + messageType);
	    commandJson = JsonUtils.readJsonObjectFromJsonObject(commandJson, messageType);
	    //go inside the parameter field of the JSON
	    commandJson = JsonUtils.readJsonObjectFromJsonObject(commandJson, RPCMessage.KEY_PARAMETERS);
	    return commandJson;
	}
	
	
	
}
