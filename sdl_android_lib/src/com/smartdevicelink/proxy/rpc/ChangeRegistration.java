package com.smartdevicelink.proxy.rpc;

import java.util.Hashtable;

import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCRequest;
import com.smartdevicelink.proxy.rpc.enums.Language;
import com.smartdevicelink.util.DebugTool;

/**
 * If the app recognizes during the app registration that the Sdl HMI language
 * (voice/TTS and/or display) does not match the app language, the app will be
 * able (but does not need) to change this registration with changeRegistration
 * prior to app being brought into focus
 * <p>
 * Function Group: Base
 * <p>
 * <b>HMILevel can by any</b>
 * <p>
 * 
 * @since SmartDeviceLink 2.0
 * @see RegisterAppInterface
 */
public class ChangeRegistration extends RPCRequest {
	public static final String KEY_LANGUAGE = "language";
	public static final String KEY_HMI_DISPLAY_LANGUAGE = "hmiDisplayLanguage";

	/**
	 * Constructs a new ChangeRegistration object
	 */
    public ChangeRegistration() {
        super(FunctionID.CHANGE_REGISTRATION);
    }

	/**
	 * Constructs a new ChangeRegistration object indicated by the Hashtable
	 * parameter
	 * <p>
	 * 
	 * @param hash
	 *            The Hashtable to use
	 */
    public ChangeRegistration(Hashtable<String, Object> hash) {
        super(hash);
    }

	/**
	 * Sets language
	 * 
	 * @param language
	 *            a language value
	 */
    public void setLanguage(Language language) {
        if (language != null) {
            parameters.put(KEY_LANGUAGE, language);
        } else {
        	parameters.remove(KEY_LANGUAGE);
        }
    }

	/**
	 * Gets language
	 * 
	 * @return Language -a Language value
	 */
    public Language getLanguage() {
    	Object obj = parameters.get(KEY_LANGUAGE);
        if (obj instanceof Language) {
            return (Language) obj;
        } else if (obj instanceof String) {
        	Language theCode = null;
            try {
                theCode = Language.valueForString((String) obj);
            } catch (Exception e) {
            	DebugTool.logError("Failed to parse " + getClass().getSimpleName() + "." + KEY_LANGUAGE, e);
            }
            return theCode;
        }
        return null;
    }

	/**
	 * Sets HMI display language
	 * 
	 * @param hmiDisplayLanguage
	 *            a Language value
	 */
    public void setHmiDisplayLanguage(Language hmiDisplayLanguage) {
        if (hmiDisplayLanguage != null) {
            parameters.put(KEY_HMI_DISPLAY_LANGUAGE, hmiDisplayLanguage);
        } else {
        	parameters.remove(KEY_HMI_DISPLAY_LANGUAGE);
        }
    }

	/**
	 * Gets HMI display language
	 * 
	 * @return Language -a Language value
	 */
    public Language getHmiDisplayLanguage() {
    	Object obj = parameters.get(KEY_HMI_DISPLAY_LANGUAGE);
        if (obj instanceof Language) {
            return (Language) obj;
        } else if (obj instanceof String) {
        	Language theCode = null;
            try {
                theCode = Language.valueForString((String) obj);
            } catch (Exception e) {
            	DebugTool.logError("Failed to parse " + getClass().getSimpleName() + "." + KEY_HMI_DISPLAY_LANGUAGE, e);
            }
            return theCode;
        }
        return null;
    }
}
