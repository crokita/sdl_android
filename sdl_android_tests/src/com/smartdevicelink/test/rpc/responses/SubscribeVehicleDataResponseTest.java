package com.smartdevicelink.test.rpc.responses;

import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.SubscribeVehicleDataResponse;
import com.smartdevicelink.proxy.rpc.VehicleDataResult;
import com.smartdevicelink.proxy.rpc.enums.VehicleDataType;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.Validator;

public class SubscribeVehicleDataResponseTest extends BaseRpcTests {
	//TODO: the corresponding library class does not include variables for the vehicle data type VEHICLEDATA_BATTVOLTAGE. intentional?

    private VehicleDataResult speed;
	private VehicleDataResult rpm;
	private VehicleDataResult externalTemperature;
	private VehicleDataResult fuelLevel;
	private VehicleDataResult prndl;
	private VehicleDataResult tirePressure;
	private VehicleDataResult engineTorque;
	private VehicleDataResult odometer;
	private VehicleDataResult gps;
	private VehicleDataResult fuelLevelState;
	private VehicleDataResult instantFuelConsumption;
	private VehicleDataResult beltStatus;
	private VehicleDataResult bodyInformation;
	private VehicleDataResult deviceStatus;
	private VehicleDataResult driverBraking;
	private VehicleDataResult wiperStatus;
	private VehicleDataResult headLampStatus;
	private VehicleDataResult accPedalPosition;
	private VehicleDataResult steeringWheelAngle;
	private VehicleDataResult eCallInfo;
	private VehicleDataResult airbagStatus;
	private VehicleDataResult emergencyEvent;
	private VehicleDataResult clusterModeStatus;
	private VehicleDataResult myKey;
	
	private JSONObject paramsJson;
	
	//this method makes setting up the VehicleDataResult objects easier 
	private VehicleDataResult createVehicleResult (String type) throws JSONException {
		JSONObject vehicleObj = paramsJson.getJSONObject(type);
		return new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(vehicleObj));
	}
	
	@Override
	protected RPCMessage createMessage() {
		SubscribeVehicleDataResponse msg = new SubscribeVehicleDataResponse();
		paramsJson = JsonFileReader.getParams(getCommandType(), getMessageType());
		
		try {					
			speed = createVehicleResult(SubscribeVehicleDataResponse.KEY_SPEED) ;
			msg.setSpeed(speed);
			rpm = createVehicleResult(SubscribeVehicleDataResponse.KEY_RPM);
			msg.setRpm(rpm);
			externalTemperature = createVehicleResult(SubscribeVehicleDataResponse.KEY_EXTERNAL_TEMPERATURE);
			msg.setExternalTemperature(externalTemperature);
			fuelLevel = createVehicleResult(SubscribeVehicleDataResponse.KEY_FUEL_LEVEL);
			msg.setFuelLevel(fuelLevel);
			prndl = createVehicleResult(SubscribeVehicleDataResponse.KEY_PRNDL);
			msg.setPrndl(prndl);
			tirePressure = createVehicleResult(SubscribeVehicleDataResponse.KEY_TIRE_PRESSURE);
			msg.setTirePressure(tirePressure);
			engineTorque = createVehicleResult(SubscribeVehicleDataResponse.KEY_ENGINE_TORQUE);
			msg.setEngineTorque(engineTorque);
			odometer = createVehicleResult(SubscribeVehicleDataResponse.KEY_ODOMETER);
			msg.setOdometer(odometer);
			gps = createVehicleResult(SubscribeVehicleDataResponse.KEY_GPS);
			msg.setGps(gps);
			fuelLevelState = createVehicleResult(SubscribeVehicleDataResponse.KEY_FUEL_LEVEL_STATE);
			msg.setFuelLevel_State(fuelLevelState);
			instantFuelConsumption = createVehicleResult(SubscribeVehicleDataResponse.KEY_INSTANT_FUEL_CONSUMPTION);
			msg.setInstantFuelConsumption(instantFuelConsumption);
			beltStatus = createVehicleResult(SubscribeVehicleDataResponse.KEY_BELT_STATUS);
			msg.setBeltStatus(beltStatus);
			bodyInformation = createVehicleResult(SubscribeVehicleDataResponse.KEY_BODY_INFORMATION);
			msg.setBodyInformation(bodyInformation);
			deviceStatus = createVehicleResult(SubscribeVehicleDataResponse.KEY_DEVICE_STATUS);
			msg.setDeviceStatus(deviceStatus);
			driverBraking = createVehicleResult(SubscribeVehicleDataResponse.KEY_DRIVER_BRAKING);
			msg.setDriverBraking(driverBraking);
			wiperStatus = createVehicleResult(SubscribeVehicleDataResponse.KEY_WIPER_STATUS);
			msg.setWiperStatus(wiperStatus);
			headLampStatus = createVehicleResult(SubscribeVehicleDataResponse.KEY_HEAD_LAMP_STATUS);
			msg.setHeadLampStatus(headLampStatus);
			accPedalPosition = createVehicleResult(SubscribeVehicleDataResponse.KEY_ACC_PEDAL_POSITION);
			msg.setAccPedalPosition(accPedalPosition);
			steeringWheelAngle = createVehicleResult(SubscribeVehicleDataResponse.KEY_STEERING_WHEEL_ANGLE);
			msg.setSteeringWheelAngle(steeringWheelAngle);
			eCallInfo = createVehicleResult(SubscribeVehicleDataResponse.KEY_E_CALL_INFO);
			msg.setECallInfo(eCallInfo);
			airbagStatus = createVehicleResult(SubscribeVehicleDataResponse.KEY_AIRBAG_STATUS);
			msg.setAirbagStatus(airbagStatus);
			emergencyEvent = createVehicleResult(SubscribeVehicleDataResponse.KEY_EMERGENCY_EVENT);
			msg.setEmergencyEvent(emergencyEvent);
			clusterModeStatus = createVehicleResult(SubscribeVehicleDataResponse.KEY_CLUSTER_MODE_STATUS);
			msg.setClusterModeStatus(clusterModeStatus);
			myKey = createVehicleResult(SubscribeVehicleDataResponse.KEY_MY_KEY);
			msg.setMyKey(myKey);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return msg;
	}

	@Override
	protected String getMessageType() {
		return RPCMessage.KEY_RESPONSE;
	}

	@Override
	protected String getCommandType() {
		return FunctionID.SUBSCRIBE_VEHICLE_DATA;
	}

	@Override
	protected JSONObject getExpectedParameters(int sdlVersion) {
		JSONObject result = new JSONObject();

		try {
			result.put(SubscribeVehicleDataResponse.KEY_SPEED, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_SPEED));
	        result.put(SubscribeVehicleDataResponse.KEY_RPM, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_RPM));
	        result.put(SubscribeVehicleDataResponse.KEY_EXTERNAL_TEMPERATURE, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_EXTERNAL_TEMPERATURE));
	        result.put(SubscribeVehicleDataResponse.KEY_FUEL_LEVEL, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_FUEL_LEVEL));
	        result.put(SubscribeVehicleDataResponse.KEY_PRNDL, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_PRNDL));
	        result.put(SubscribeVehicleDataResponse.KEY_TIRE_PRESSURE, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_TIRE_PRESSURE));
	        result.put(SubscribeVehicleDataResponse.KEY_ENGINE_TORQUE, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_ENGINE_TORQUE));
	        result.put(SubscribeVehicleDataResponse.KEY_ODOMETER, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_ODOMETER));
	        result.put(SubscribeVehicleDataResponse.KEY_GPS, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_GPS));
	        result.put(SubscribeVehicleDataResponse.KEY_FUEL_LEVEL_STATE, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_FUEL_LEVEL_STATE));
	        result.put(SubscribeVehicleDataResponse.KEY_INSTANT_FUEL_CONSUMPTION, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_INSTANT_FUEL_CONSUMPTION));
	        result.put(SubscribeVehicleDataResponse.KEY_BELT_STATUS, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_BELT_STATUS));
	        result.put(SubscribeVehicleDataResponse.KEY_BODY_INFORMATION, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_BODY_INFORMATION));
	        result.put(SubscribeVehicleDataResponse.KEY_DEVICE_STATUS, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_DEVICE_STATUS));
	        result.put(SubscribeVehicleDataResponse.KEY_DRIVER_BRAKING, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_DRIVER_BRAKING));
	        result.put(SubscribeVehicleDataResponse.KEY_WIPER_STATUS, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_WIPER_STATUS));
	        result.put(SubscribeVehicleDataResponse.KEY_HEAD_LAMP_STATUS, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_HEAD_LAMP_STATUS));
	        result.put(SubscribeVehicleDataResponse.KEY_ACC_PEDAL_POSITION, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_ACC_PEDAL_POSITION));
	        result.put(SubscribeVehicleDataResponse.KEY_STEERING_WHEEL_ANGLE, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_STEERING_WHEEL_ANGLE));
	        result.put(SubscribeVehicleDataResponse.KEY_E_CALL_INFO, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_E_CALL_INFO));
	        result.put(SubscribeVehicleDataResponse.KEY_AIRBAG_STATUS, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_AIRBAG_STATUS));
	        result.put(SubscribeVehicleDataResponse.KEY_EMERGENCY_EVENT, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_EMERGENCY_EVENT));
	        result.put(SubscribeVehicleDataResponse.KEY_CLUSTER_MODE_STATUS, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_CLUSTER_MODE_STATUS));
	        result.put(SubscribeVehicleDataResponse.KEY_MY_KEY, paramsJson.getJSONObject(SubscribeVehicleDataResponse.KEY_MY_KEY));
		} catch (JSONException e) {
			// do nothing
		}

		return result;
	}

	/* The VehicleDataResult class contains the following retrievable variables:
	 *    (VehicleDataType)       dataType     Enum value being tested
	 *    (VehicleDataResultCode) resultCode   Enum value being ignored
	 *    
	 * Should we test the result code as follows? Are we to assume success?
	 * 
	 * assertTrue("Result code didn't match expected value.", data.getResultCode().equals(VehicleDataResultCode.SUCCESS));
	 */
	
	public void testGps(){
		VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getGps();
		
		assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_GPS));
    }
	
	public void testOdometer(){
		VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getOdometer();
		
		assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_ODOMETER));
    }

    public void testTireStatus(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getTirePressure();
        
        assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_TIREPRESSURE));
    }

    public void testBeltStatus(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getBeltStatus();
    	
    	assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_BELTSTATUS));
    }

    public void testBodyInformation(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getBodyInformation();
    	
    	assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_BODYINFO));
    }

    public void testDeviceStatus(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getDeviceStatus();
    	
    	assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_DEVICESTATUS));
    }

    public void testHeadLampStatus(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getHeadLampStatus();
    	
    	assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_HEADLAMPSTATUS));
    }

    public void testECallInfo(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getECallInfo();
    	
    	assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_ECALLINFO));
    }

    public void testAirbagStatus(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getAirbagStatus();
    	
    	assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_AIRBAGSTATUS));
    }

    public void testEmergencyEvent(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getEmergencyEvent();
    	
    	assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_EMERGENCYEVENT));
    }

    public void testClusterModeStatus(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getClusterModeStatus();
    	
    	assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_CLUSTERMODESTATUS));
    }

    public void testMyKey(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getMyKey();
    	
    	assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_MYKEY));
    }

    public void testSpeed(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getSpeed();
    	
    	assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_SPEED));
    }

    public void testRpm(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getRpm();
    	
        assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_RPM));
    }

    public void testFuelLevel(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getFuelLevel();
    	
        assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_FUELLEVEL));
    }

    public void testInstantFuelConsumption(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getInstantFuelConsumption();
    	
        assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_FUELCONSUMPTION));
    }

    public void testExternalTemperature(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getExternalTemperature();
    	
    	assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_EXTERNTEMP));
    }

    public void testEngineTorque(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getEngineTorque();

    	assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_ENGINETORQUE));
    }

    public void testAccPedalPosition(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getAccPedalPosition();
    	
    	assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_ACCPEDAL));
    }

    public void testSteeringWheelAngle(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getSteeringWheelAngle();
    	
        assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_STEERINGWHEEL));
    }

    public void testFuelLevelState(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getFuelLevel_State();
    	
        assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_FUELLEVEL_STATE));
    }

    public void testPRNDL(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getPrndl();
    	
        assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_PRNDL));
    }

    public void testDriverBraking(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getDriverBraking();
    	
        assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_BRAKING));
    }

    public void testWiperStatus(){
    	VehicleDataResult data = ( (SubscribeVehicleDataResponse) msg ).getWiperStatus();
    	
        assertTrue("Returned data was not of expected type.", data.getDataType().equals(VehicleDataType.VEHICLEDATA_WIPERSTATUS));
    }
	
	public void testNull(){
		SubscribeVehicleDataResponse msg = new SubscribeVehicleDataResponse();
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
			SubscribeVehicleDataResponse cmd = new SubscribeVehicleDataResponse(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);

			JSONObject speed = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_SPEED);
			VehicleDataResult referenceSpeed = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(speed));
			assertTrue("Speed doesn't match expected speed", Validator.validateVehicleDataResult(referenceSpeed, cmd.getSpeed()));
			
			JSONObject rpm = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_RPM);
			VehicleDataResult referenceRpm = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(rpm));
			assertTrue("RPM doesn't match expected RPM", Validator.validateVehicleDataResult(referenceRpm, cmd.getRpm()));
			
			JSONObject fuelLevel = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_FUEL_LEVEL);
			VehicleDataResult referenceFuelLevel = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(fuelLevel));
			assertTrue("Fuel level doesn't match expected level", Validator.validateVehicleDataResult(referenceFuelLevel, cmd.getFuelLevel()));
			
			JSONObject externalTemperature = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_EXTERNAL_TEMPERATURE);
			VehicleDataResult referenceExternalTemperature = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(externalTemperature));
			assertTrue("External temperature doesn't match expected temperature", 
					Validator.validateVehicleDataResult(referenceExternalTemperature, cmd.getExternalTemperature()));
			
			JSONObject prndl = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_PRNDL);
			VehicleDataResult referencePrndl = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(prndl));
			assertTrue("PRNDL doesn't match expected PRNDL", Validator.validateVehicleDataResult(referencePrndl, cmd.getPrndl()));
			
			JSONObject tirePressure = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_TIRE_PRESSURE);
			VehicleDataResult referenceTirePressure = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(tirePressure));
			assertTrue("Tire pressure doesn't match expected pressure", Validator.validateVehicleDataResult(referenceTirePressure, cmd.getTirePressure()));
			
			JSONObject engineTorque = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_ENGINE_TORQUE);
			VehicleDataResult referenceEngineTorque = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(engineTorque));
			assertTrue("Engine torque doesn't match expected torque", Validator.validateVehicleDataResult(referenceEngineTorque, cmd.getEngineTorque()));
			
			JSONObject odometer = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_ODOMETER);
			VehicleDataResult referenceOdometer = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(odometer));
			assertTrue("Odometer doesn't match expected odometer", Validator.validateVehicleDataResult(referenceOdometer, cmd.getOdometer()));
			
			JSONObject gps = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_GPS);
			VehicleDataResult referenceGps = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(gps));
			assertTrue("GPS doesn't match expected GPS", Validator.validateVehicleDataResult(referenceGps, cmd.getGps()));
			
			JSONObject fuelLevelState = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_FUEL_LEVEL_STATE);
			VehicleDataResult referenceFuelLevelState = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(fuelLevelState));
			assertTrue("Fuel level state doesn't match expected state", Validator.validateVehicleDataResult(referenceFuelLevelState, cmd.getFuelLevel_State()));
			
			JSONObject fuelConsumption = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_INSTANT_FUEL_CONSUMPTION);
			VehicleDataResult referenceFuelConsumption = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(fuelConsumption));
			assertTrue("Fuel consumption doesn't match expected consumption", 
					Validator.validateVehicleDataResult(referenceFuelConsumption, cmd.getInstantFuelConsumption()));
			
			JSONObject beltStatus = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_BELT_STATUS);
			VehicleDataResult referenceBeltStatus = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(beltStatus));
			assertTrue("Belt status doesn't match expected status", Validator.validateVehicleDataResult(referenceBeltStatus, cmd.getBeltStatus()));
			
			JSONObject bodyInformation = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_BODY_INFORMATION);
			VehicleDataResult referenceBodyInformation = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(bodyInformation));
			assertTrue("Body information doesn't match expected information", 
					Validator.validateVehicleDataResult(referenceBodyInformation, cmd.getBodyInformation()));
			
			JSONObject deviceStatus = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_DEVICE_STATUS);
			VehicleDataResult referenceDeviceStatus = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(deviceStatus));
			assertTrue("Device status doesn't match expected status", Validator.validateVehicleDataResult(referenceDeviceStatus, cmd.getDeviceStatus()));
			
			JSONObject driverBraking = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_DRIVER_BRAKING);
			VehicleDataResult referenceDriverBraking = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(driverBraking));
			assertTrue("Driver braking doesn't match expected braking", Validator.validateVehicleDataResult(referenceDriverBraking, cmd.getDriverBraking()));
			
			JSONObject wiperStatus = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_WIPER_STATUS);
			VehicleDataResult referenceWiperStatus = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(wiperStatus));
			assertTrue("Wiper status doesn't match expected status", Validator.validateVehicleDataResult(referenceWiperStatus, cmd.getWiperStatus()));
			
			JSONObject headLampStatus = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_HEAD_LAMP_STATUS);
			VehicleDataResult referenceHeadLampStatus = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(headLampStatus));
			assertTrue("Head lamp status doesn't match expected status", Validator.validateVehicleDataResult(referenceHeadLampStatus, cmd.getHeadLampStatus()));
			
			JSONObject accPedalPosition = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_ACC_PEDAL_POSITION);
			VehicleDataResult referenceAccPedalPosition = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(accPedalPosition));
			assertTrue("Acc pedal position doesn't match expected position", 
					Validator.validateVehicleDataResult(referenceAccPedalPosition, cmd.getAccPedalPosition()));
			
			JSONObject steeringWheelAngle = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_STEERING_WHEEL_ANGLE);
			VehicleDataResult referenceSteeringWheelAngle = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(steeringWheelAngle));
			assertTrue("Steering wheel angle doesn't match expected angle", 
					Validator.validateVehicleDataResult(referenceSteeringWheelAngle, cmd.getSteeringWheelAngle()));
			
			JSONObject eCallInfo = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_E_CALL_INFO);
			VehicleDataResult referenceECallInfo = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(eCallInfo));
			assertTrue("Emergency call info doesn't match expected info", Validator.validateVehicleDataResult(referenceECallInfo, cmd.getECallInfo()));
			
			JSONObject airbagStatus = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_AIRBAG_STATUS);
			VehicleDataResult referenceAirbagStatus = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(airbagStatus));
			assertTrue("Airbag status doesn't match expected status", Validator.validateVehicleDataResult(referenceAirbagStatus, cmd.getAirbagStatus()));
			
			JSONObject emergencyEvent = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_EMERGENCY_EVENT);
			VehicleDataResult referenceEmergencyEvent = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(emergencyEvent));
			assertTrue("Emergency event doesn't match expected event", Validator.validateVehicleDataResult(referenceEmergencyEvent, cmd.getEmergencyEvent()));
			
			JSONObject clusterModeStatus = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_CLUSTER_MODE_STATUS);
			VehicleDataResult referenceClusterModeStatus = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(clusterModeStatus));
			assertTrue("Cluster mode status doesn't match expected status", 
					Validator.validateVehicleDataResult(referenceClusterModeStatus, cmd.getClusterModeStatus()));
			
			JSONObject myKey = parameters.getJSONObject(SubscribeVehicleDataResponse.KEY_MY_KEY);
			VehicleDataResult referenceMyKey = new VehicleDataResult(JsonRPCMarshaller.deserializeJSONObject(myKey));
			assertTrue("My key doesn't match expected key", Validator.validateVehicleDataResult(referenceMyKey, cmd.getMyKey()));
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }

}
