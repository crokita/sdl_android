package com.smartdevicelink.test.rpc.requests;

import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.GetVehicleData;
import com.smartdevicelink.proxy.rpc.SubscribeVehicleData;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;

public class SubscribeVehicleDataTest extends BaseRpcTests {
	
    public boolean speed;
	public boolean rpm;
	public boolean externalTemperature;
	public boolean fuelLevel;
	public boolean prndl;
	public boolean tirePressure;
	public boolean engineTorque;
	public boolean odometer;
	public boolean gps;
	public boolean fuelLevelState;
	public boolean instantFuelConsumption;
	public boolean beltStatus;
	public boolean bodyInformation;
	public boolean deviceStatus;
	public boolean driverBraking;
	public boolean wiperStatus;
	public boolean headLampStatus;
	public boolean accPedalPosition;
	public boolean steeringWheelAngle;
	public boolean eCallInfo;
	public boolean airbagStatus;
	public boolean emergencyEvent;
	public boolean clusterModeStatus;
	public boolean myKey;
    
	private JSONObject paramsJson;
	
	@Override
	protected RPCMessage createMessage() {
		SubscribeVehicleData msg = new SubscribeVehicleData();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
		
        speed = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_SPEED);
		msg.setSpeed(speed);
		rpm = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_RPM);
		msg.setRpm(rpm);
		externalTemperature = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_EXTERNAL_TEMPERATURE);
		msg.setExternalTemperature(externalTemperature);
		fuelLevel = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_FUEL_LEVEL);
		msg.setFuelLevel(fuelLevel);
		prndl = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_PRNDL);
		msg.setPrndl(prndl);
		tirePressure = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_TIRE_PRESSURE);
		msg.setTirePressure(tirePressure);
		engineTorque = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_ENGINE_TORQUE);
		msg.setEngineTorque(engineTorque);
		odometer = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_ODOMETER);
		msg.setOdometer(odometer);
		gps = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_GPS);
		msg.setGps(gps);
		fuelLevelState = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_FUEL_LEVEL_STATE);
		msg.setFuelLevel_State(fuelLevelState);
		instantFuelConsumption = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_INSTANT_FUEL_CONSUMPTION);
		msg.setInstantFuelConsumption(instantFuelConsumption);
		beltStatus = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_BELT_STATUS);
		msg.setBeltStatus(beltStatus);
		bodyInformation = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_BODY_INFORMATION);
		msg.setBodyInformation(bodyInformation);
		deviceStatus = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_DEVICE_STATUS);
		msg.setDeviceStatus(deviceStatus);
		driverBraking = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_DRIVER_BRAKING);
		msg.setDriverBraking(driverBraking);
		wiperStatus = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_WIPER_STATUS);
		msg.setWiperStatus(wiperStatus);
		headLampStatus = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_HEAD_LAMP_STATUS);
		msg.setHeadLampStatus(headLampStatus);
		accPedalPosition = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_ACC_PEDAL_POSITION);
		msg.setAccPedalPosition(accPedalPosition);
		steeringWheelAngle = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_STEERING_WHEEL_ANGLE);
		msg.setSteeringWheelAngle(steeringWheelAngle);
		eCallInfo = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_E_CALL_INFO);
		msg.setECallInfo(eCallInfo);
		airbagStatus = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_AIRBAG_STATUS);
		msg.setAirbagStatus(airbagStatus);
		emergencyEvent = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_EMERGENCY_EVENT);
		msg.setEmergencyEvent(emergencyEvent);
		clusterModeStatus = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_CLUSTER_MODE_STATUS);
		msg.setClusterModeStatus(clusterModeStatus);
		myKey = JsonUtils.readBooleanFromJsonObject(paramsJson, SubscribeVehicleData.KEY_MY_KEY);
		msg.setMyKey(myKey);
		
		return msg;
	}

	@Override
	protected String getMessageType() {
		return RPCMessage.KEY_REQUEST;
	}

	@Override
	protected String getCommandType() {
		return FunctionID.SUBSCRIBE_VEHICLE_DATA;
	}

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();

		try {
            result.put(SubscribeVehicleData.KEY_SPEED, speed);
            result.put(SubscribeVehicleData.KEY_RPM, rpm);
            result.put(SubscribeVehicleData.KEY_EXTERNAL_TEMPERATURE, externalTemperature);
            result.put(SubscribeVehicleData.KEY_FUEL_LEVEL, fuelLevel);
            result.put(SubscribeVehicleData.KEY_PRNDL, prndl);
            result.put(SubscribeVehicleData.KEY_TIRE_PRESSURE, tirePressure);
            result.put(SubscribeVehicleData.KEY_ENGINE_TORQUE, engineTorque);
            result.put(SubscribeVehicleData.KEY_ODOMETER, odometer);
            result.put(SubscribeVehicleData.KEY_GPS, gps);
            result.put(SubscribeVehicleData.KEY_FUEL_LEVEL_STATE, fuelLevelState);
            result.put(SubscribeVehicleData.KEY_INSTANT_FUEL_CONSUMPTION, instantFuelConsumption);
            result.put(SubscribeVehicleData.KEY_BELT_STATUS, beltStatus);
            result.put(SubscribeVehicleData.KEY_BODY_INFORMATION, bodyInformation);
            result.put(SubscribeVehicleData.KEY_DEVICE_STATUS, deviceStatus);
            result.put(SubscribeVehicleData.KEY_DRIVER_BRAKING, driverBraking);
            result.put(SubscribeVehicleData.KEY_WIPER_STATUS, wiperStatus);
            result.put(SubscribeVehicleData.KEY_HEAD_LAMP_STATUS, headLampStatus);
            result.put(SubscribeVehicleData.KEY_ACC_PEDAL_POSITION, accPedalPosition);
            result.put(SubscribeVehicleData.KEY_STEERING_WHEEL_ANGLE, steeringWheelAngle);
            result.put(SubscribeVehicleData.KEY_E_CALL_INFO, eCallInfo);
            result.put(SubscribeVehicleData.KEY_AIRBAG_STATUS, airbagStatus);
            result.put(SubscribeVehicleData.KEY_EMERGENCY_EVENT, emergencyEvent);
            result.put(SubscribeVehicleData.KEY_CLUSTER_MODE_STATUS, clusterModeStatus);
            result.put(SubscribeVehicleData.KEY_MY_KEY, myKey);
		} catch (JSONException e) {
			/* do nothing */
		}

		return result;
	}

	public void testBatchAdd(){		
		assertEquals("Speed vehicle data wasn't set correctly.", (Boolean) speed, ( (SubscribeVehicleData) msg ).getSpeed());
		assertEquals("RPM vehicle data wasn't set correctly.", (Boolean) rpm, ( (SubscribeVehicleData) msg ).getRpm());
		assertEquals("External temperature vehicle data wasn't set correctly.", (Boolean) externalTemperature, ( (SubscribeVehicleData) msg ).getExternalTemperature());
		assertEquals("Fuel level vehicle data wasn't set correctly.", (Boolean) fuelLevel, ( (SubscribeVehicleData) msg ).getFuelLevel());
		assertEquals("PRNDL vehicle data wasn't set correctly.", (Boolean) prndl, ( (SubscribeVehicleData) msg ).getPrndl());
		assertEquals("Tire pressure vehicle data wasn't set correctly.", (Boolean) tirePressure, ( (SubscribeVehicleData) msg ).getTirePressure());
		assertEquals("Engine torque vehicle data wasn't set correctly.", (Boolean) engineTorque, ( (SubscribeVehicleData) msg ).getEngineTorque());
		assertEquals("Odometer vehicle data wasn't set correctly.", (Boolean) odometer, ( (SubscribeVehicleData) msg ).getOdometer());
		assertEquals("GPS vehicle data wasn't set correctly.", (Boolean) gps, ( (SubscribeVehicleData) msg ).getGps());
		assertEquals("Fuel level state vehicle data wasn't set correctly.", (Boolean) fuelLevelState, ( (SubscribeVehicleData) msg ).getFuelLevel_State());
		assertEquals("Instant fuel consumption vehicle data wasn't set correctly.", (Boolean) instantFuelConsumption, ( (SubscribeVehicleData) msg ).getInstantFuelConsumption());
		assertEquals("Belt status vehicle data wasn't set correctly.", (Boolean) beltStatus, ( (SubscribeVehicleData) msg ).getBeltStatus());
		assertEquals("Body information vehicle data wasn't set correctly.", (Boolean) bodyInformation, ( (SubscribeVehicleData) msg ).getBodyInformation());
		assertEquals("Device status vehicle data wasn't set correctly.", (Boolean) deviceStatus, ( (SubscribeVehicleData) msg ).getDeviceStatus());
		assertEquals("Driver braking vehicle data wasn't set correctly.", (Boolean) driverBraking, ( (SubscribeVehicleData) msg ).getDriverBraking());
		assertEquals("Wiper status vehicle data wasn't set correctly.", (Boolean) wiperStatus, ( (SubscribeVehicleData) msg ).getWiperStatus());
		assertEquals("Head lamp status vehicle data wasn't set correctly.", (Boolean) headLampStatus, ( (SubscribeVehicleData) msg ).getHeadLampStatus());
		assertEquals("Acceleration pedal position vehicle data wasn't set correctly.", (Boolean) accPedalPosition, ( (SubscribeVehicleData) msg ).getAccPedalPosition());
		assertEquals("Steering wheel angle vehicle data wasn't set correctly.", (Boolean) steeringWheelAngle, ( (SubscribeVehicleData) msg ).getSteeringWheelAngle());
		assertEquals("Emergency call info vehicle data wasn't set correctly.", (Boolean) eCallInfo, ( (SubscribeVehicleData) msg ).getECallInfo());
		assertEquals("Airbag status vehicle data wasn't set correctly.", (Boolean) airbagStatus, ( (SubscribeVehicleData) msg ).getAirbagStatus());
		assertEquals("Emergency event vehicle data wasn't set correctly.", (Boolean) emergencyEvent, ( (SubscribeVehicleData) msg ).getEmergencyEvent());
		assertEquals("Cluster mode status vehicle data wasn't set correctly.", (Boolean) clusterModeStatus, ( (SubscribeVehicleData) msg ).getClusterModeStatus());
		assertEquals("My key vehicle data wasn't set correctly.", (Boolean) myKey, ( (SubscribeVehicleData) msg ).getMyKey());
    }
 
	public void testNull() {
		SubscribeVehicleData msg = new SubscribeVehicleData();
		assertNotNull("Null object creation failed.", msg);
		testNullBase(msg);

        assertNull("Accel pedal vehicle data wasn't set, but getter method returned an object.", msg.getAccPedalPosition());
        assertNull("Airbag vehicle data wasn't set, but getter method returned an object.", msg.getAirbagStatus());
        assertNull("Belt vehicle data wasn't set, but getter method returned an object.", msg.getBeltStatus());
        assertNull("Driver braking vehicle data wasn't set, but getter method returned an object.", msg.getDriverBraking());
        assertNull("Fuel level vehicle data wasn't set, but getter method returned an object.", msg.getFuelLevel());
        assertNull("Tire pressure vehicle data wasn't set, but getter method returned an object.", msg.getTirePressure());
        assertNull("Wiper vehicle data wasn't set, but getter method returned an object.", msg.getWiperStatus());
        assertNull("Gps data wasn't set, but getter method returned an object.", msg.getGps());
        assertNull("Speed data wasn't set, but getter method returned an object.", msg.getSpeed());
        assertNull("Rpm data wasn't set, but getter method returned an object.", msg.getRpm());
        assertNull("Fuel level state data wasn't set, but getter method returned an object.", msg.getFuelLevel_State());
        assertNull("Fuel consumption data wasn't set, but getter method returned an object.", msg.getInstantFuelConsumption());
        assertNull("External temperature data wasn't set, but getter method returned an object.", msg.getExternalTemperature());
        assertNull("PRNDL data wasn't set, but getter method returned an object.", msg.getPrndl());
        assertNull("Odometer data wasn't set, but getter method returned an object.", msg.getOdometer());
        assertNull("Body information data wasn't set, but getter method returned an object.", msg.getBodyInformation());
        assertNull("Device status data wasn't set, but getter method returned an object.", msg.getDeviceStatus());
        assertNull("Head lamp status data wasn't set, but getter method returned an object.", msg.getHeadLampStatus());
        assertNull("Engine torque data wasn't set, but getter method returned an object.", msg.getEngineTorque());
        assertNull("Steering wheel angle data wasn't set, but getter method returned an object.", msg.getSteeringWheelAngle());
        assertNull("ECall info data wasn't set, but getter method returned an object.", msg.getECallInfo());
        assertNull("Emergency event data wasn't set, but getter method returned an object.", msg.getEmergencyEvent());
        assertNull("Cluster mode data wasn't set, but getter method returned an object.", msg.getClusterModeStatus());
        assertNull("My key data wasn't set, but getter method returned an object.", msg.getMyKey());
	}
	
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			SubscribeVehicleData cmd = new SubscribeVehicleData(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			assertEquals("Speed doesn't match input speed", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_SPEED), cmd.getSpeed());
			assertEquals("Rpm doesn't match input rpm", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_RPM), cmd.getRpm());
			assertEquals("External temperature doesn't match input temperature", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_EXTERNAL_TEMPERATURE), cmd.getExternalTemperature());
			assertEquals("Fuel level doesn't match input level", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_FUEL_LEVEL), cmd.getFuelLevel());
			assertEquals("PRNDL doesn't match input PRDNL", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_PRNDL), cmd.getPrndl());
			assertEquals("Tire pressure doesn't match input pressure", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_TIRE_PRESSURE), cmd.getTirePressure());
			assertEquals("Engine torque doesn't match input torque", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_ENGINE_TORQUE), cmd.getEngineTorque());
			assertEquals("Odometer doesn't match input odometer", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_ODOMETER), cmd.getOdometer());
			assertEquals("GPS doesn't match input GPS", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_GPS), cmd.getGps());
			assertEquals("Fuel level state doesn't match input state", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_FUEL_LEVEL_STATE), cmd.getFuelLevel_State());
			assertEquals("Instant fuel consumption doesn't match input consumption", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_INSTANT_FUEL_CONSUMPTION), cmd.getInstantFuelConsumption());
			assertEquals("Belt status doesn't match input status", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_BELT_STATUS), cmd.getBeltStatus());
			assertEquals("Body information doesn't match input information", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_BODY_INFORMATION), cmd.getBodyInformation());
			assertEquals("Device status doesn't match input status", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_DEVICE_STATUS), cmd.getDeviceStatus());
			assertEquals("Driver braking doesn't match input braking", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_DRIVER_BRAKING), cmd.getDriverBraking());
			assertEquals("Wiper status doesn't match input status", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_WIPER_STATUS), cmd.getWiperStatus());
			assertEquals("Head lamp status doesn't match input status", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_HEAD_LAMP_STATUS), cmd.getHeadLampStatus());
			assertEquals("Acceleration pedal position doesn't match input position", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_ACC_PEDAL_POSITION), cmd.getAccPedalPosition());
			assertEquals("Steering wheel angle doesn't match input angle", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_STEERING_WHEEL_ANGLE), cmd.getSteeringWheelAngle());
			assertEquals("Emergency call info doesn't match input info", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_E_CALL_INFO), cmd.getECallInfo());
			assertEquals("Airbag status doesn't match input status", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_AIRBAG_STATUS), cmd.getAirbagStatus());
			assertEquals("Emergency event doesn't match input event", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_EMERGENCY_EVENT), cmd.getEmergencyEvent());
			assertEquals("Cluster mode status doesn't match input status", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_CLUSTER_MODE_STATUS), cmd.getClusterModeStatus());
			assertEquals("My key doesn't match input key", 
					(Boolean) parameters.getBoolean(GetVehicleData.KEY_MY_KEY), cmd.getMyKey());
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }

}
