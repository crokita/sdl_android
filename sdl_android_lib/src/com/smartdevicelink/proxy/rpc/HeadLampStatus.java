package com.smartdevicelink.proxy.rpc;

import java.util.Hashtable;

import com.smartdevicelink.proxy.RPCStruct;
import com.smartdevicelink.proxy.rpc.enums.AmbientLightStatus;
import com.smartdevicelink.util.DebugTool;

public class HeadLampStatus extends RPCStruct {
	public static final String KEY_AMBIENT_LIGHT_SENSOR_STATUS = "ambientLightSensorStatus";
	public static final String KEY_HIGH_BEAMS_ON = "highBeamsOn";
    public static final String KEY_LOW_BEAMS_ON = "lowBeamsOn";

    public HeadLampStatus() {}
    public HeadLampStatus(Hashtable<String, Object> hash) {
        super(hash);
    }
    public void setAmbientLightStatus(AmbientLightStatus ambientLightSensorStatus) {
        if (ambientLightSensorStatus != null) {
            store.put(KEY_AMBIENT_LIGHT_SENSOR_STATUS, ambientLightSensorStatus);
        } else {
        	store.remove(KEY_AMBIENT_LIGHT_SENSOR_STATUS);
        }
    }
    public AmbientLightStatus getAmbientLightStatus() {
        Object obj = store.get(KEY_AMBIENT_LIGHT_SENSOR_STATUS);
        if (obj instanceof AmbientLightStatus) {
            return (AmbientLightStatus) obj;
        } else if (obj instanceof String) {
        	AmbientLightStatus theCode = null;
            try {
                theCode = AmbientLightStatus.valueForString((String) obj);
            } catch (Exception e) {
            	DebugTool.logError("Failed to parse " + getClass().getSimpleName() + "." + KEY_AMBIENT_LIGHT_SENSOR_STATUS, e);
            }
            return theCode;
        }
        return null;
    }
    public void setHighBeamsOn(Boolean highBeamsOn) {
        if (highBeamsOn != null) {
            store.put(KEY_HIGH_BEAMS_ON, highBeamsOn);
        } else {
        	store.remove(KEY_HIGH_BEAMS_ON);
        }
    }
    public Boolean getHighBeamsOn() {
    	return (Boolean) store.get(KEY_HIGH_BEAMS_ON);
    }
    public void setLowBeamsOn(Boolean lowBeamsOn) {
        if (lowBeamsOn != null) {
            store.put(KEY_LOW_BEAMS_ON, lowBeamsOn);
        } else {
        	store.remove(KEY_LOW_BEAMS_ON);
        }
    }
    public Boolean getLowBeamsOn() {
    	return (Boolean) store.get(KEY_LOW_BEAMS_ON);
    }
}
