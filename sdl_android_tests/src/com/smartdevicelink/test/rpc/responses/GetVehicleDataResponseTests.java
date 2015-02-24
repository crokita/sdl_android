package com.smartdevicelink.test.rpc.responses;

import java.util.Hashtable;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.smartdevicelink.marshal.JsonRPCMarshaller;
import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.AirbagStatus;
import com.smartdevicelink.proxy.rpc.BeltStatus;
import com.smartdevicelink.proxy.rpc.BodyInformation;
import com.smartdevicelink.proxy.rpc.ClusterModeStatus;
import com.smartdevicelink.proxy.rpc.DeviceStatus;
import com.smartdevicelink.proxy.rpc.ECallInfo;
import com.smartdevicelink.proxy.rpc.EmergencyEvent;
import com.smartdevicelink.proxy.rpc.GPSData;
import com.smartdevicelink.proxy.rpc.GetVehicleDataResponse;
import com.smartdevicelink.proxy.rpc.HeadLampStatus;
import com.smartdevicelink.proxy.rpc.MyKey;
import com.smartdevicelink.proxy.rpc.TireStatus;
import com.smartdevicelink.proxy.rpc.enums.ComponentVolumeStatus;
import com.smartdevicelink.proxy.rpc.enums.PRNDL;
import com.smartdevicelink.proxy.rpc.enums.VehicleDataEventStatus;
import com.smartdevicelink.proxy.rpc.enums.WiperStatus;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.json.rpc.JsonFileReader;
import com.smartdevicelink.test.utils.JsonUtils;
import com.smartdevicelink.test.utils.Validator;
import com.smartdevicelink.test.utils.VehicleDataHelper;
public class GetVehicleDataResponseTests extends BaseRpcTests{

    @Override
    protected RPCMessage createMessage(){
    	return VehicleDataHelper.vehicleDataResponse;
    }
    
    @Override
    protected String getMessageType(){
        return RPCMessage.KEY_RESPONSE;
    }

    @Override
    protected String getCommandType(){
        return FunctionID.GET_VEHICLE_DATA;
    }

    @Override
    protected JSONObject getExpectedParameters(int sdlVersion){
        JSONObject result = new JSONObject();

        try{
            result.put(GetVehicleDataResponse.KEY_SPEED, VehicleDataHelper.speed);
            result.put(GetVehicleDataResponse.KEY_RPM, VehicleDataHelper.rpm);
            result.put(GetVehicleDataResponse.KEY_EXTERNAL_TEMPERATURE, VehicleDataHelper.externalTemperature);
            result.put(GetVehicleDataResponse.KEY_FUEL_LEVEL, VehicleDataHelper.fuelLevel);
            result.put(GetVehicleDataResponse.KEY_VIN, VehicleDataHelper.vin);
            result.put(GetVehicleDataResponse.KEY_PRNDL, VehicleDataHelper.prndl);
            result.put(GetVehicleDataResponse.KEY_TIRE_PRESSURE, VehicleDataHelper.tirePressure.serializeJSON());
            result.put(GetVehicleDataResponse.KEY_ENGINE_TORQUE, VehicleDataHelper.engineTorque);
            result.put(GetVehicleDataResponse.KEY_ODOMETER, VehicleDataHelper.odometer);
            result.put(GetVehicleDataResponse.KEY_GPS, VehicleDataHelper.gps.serializeJSON());
            result.put(GetVehicleDataResponse.KEY_FUEL_LEVEL_STATE, VehicleDataHelper.fuelLevelState);
            result.put(GetVehicleDataResponse.KEY_INSTANT_FUEL_CONSUMPTION, VehicleDataHelper.instantFuelConsumption);
            result.put(GetVehicleDataResponse.KEY_BELT_STATUS, VehicleDataHelper.beltStatus.serializeJSON());
            result.put(GetVehicleDataResponse.KEY_BODY_INFORMATION, VehicleDataHelper.bodyInformation.serializeJSON());
            result.put(GetVehicleDataResponse.KEY_DEVICE_STATUS, VehicleDataHelper.deviceStatus.serializeJSON());
            result.put(GetVehicleDataResponse.KEY_DRIVER_BRAKING, VehicleDataHelper.driverBraking);
            result.put(GetVehicleDataResponse.KEY_WIPER_STATUS, VehicleDataHelper.wiperStatus);
            result.put(GetVehicleDataResponse.KEY_HEAD_LAMP_STATUS, VehicleDataHelper.headLampStatus.serializeJSON());
            result.put(GetVehicleDataResponse.KEY_ACC_PEDAL_POSITION, VehicleDataHelper.accPedalPosition);
            result.put(GetVehicleDataResponse.KEY_STEERING_WHEEL_ANGLE, VehicleDataHelper.steeringWheelAngle);
            result.put(GetVehicleDataResponse.KEY_E_CALL_INFO, VehicleDataHelper.eCallInfo.serializeJSON());
            result.put(GetVehicleDataResponse.KEY_AIRBAG_STATUS, VehicleDataHelper.airbagStatus.serializeJSON());
            result.put(GetVehicleDataResponse.KEY_EMERGENCY_EVENT, VehicleDataHelper.emergencyEvent.serializeJSON());
            result.put(GetVehicleDataResponse.KEY_CLUSTER_MODE_STATUS, VehicleDataHelper.clusterModeStatus.serializeJSON());
            result.put(GetVehicleDataResponse.KEY_MY_KEY, VehicleDataHelper.myKey.serializeJSON());
            
        }catch(JSONException e){
            //do nothing 
        }

        return result;
    }

    public void testSpeed() {
    	Double copy = ( (GetVehicleDataResponse) msg).getSpeed();
    	assertEquals("Speed does not match input speed", VehicleDataHelper.speed, copy);
    }
    
    public void testRpm() {
    	int copy = ( (GetVehicleDataResponse) msg).getRpm();
    	assertEquals("RPM does not match input RPM", VehicleDataHelper.rpm, copy);
    }
    
    public void testExternalTemperature() {
    	Double copy = ( (GetVehicleDataResponse) msg).getExternalTemperature();
    	assertEquals("External temperature does not match input external temperature", VehicleDataHelper.externalTemperature, copy);
    }
    
    public void testFuelLevel() {
    	Double copy = ( (GetVehicleDataResponse) msg).getFuelLevel();
    	assertEquals("Fuel level does not match input fuel level", VehicleDataHelper.fuelLevel, copy);
    }
    
    public void testVin() {
    	String copy = ( (GetVehicleDataResponse) msg).getVin();
    	assertEquals("VIN does not match input VIN", VehicleDataHelper.vin, copy);
    }
    
    public void testPRNDL() {
    	PRNDL copy = ( (GetVehicleDataResponse) msg).getPrndl();
    	assertEquals("PRNDL does not match input PRNDL", VehicleDataHelper.prndl, copy);
    }
    
    public void testTirePressure() {
    	TireStatus copy = ( (GetVehicleDataResponse) msg).getTirePressure();
    	assertTrue("Tire pressure does not match input tire pressure", Validator.validateTireStatus(VehicleDataHelper.tirePressure, copy));
    }
    
    public void testEngineTorque() {
    	Double copy = ( (GetVehicleDataResponse) msg).getEngineTorque();
    	assertEquals("Engine torque does not match input engine torque", VehicleDataHelper.engineTorque, copy);
    }
    
    public void testOdometer() {
    	int copy = ( (GetVehicleDataResponse) msg).getOdometer();
    	assertEquals("Odometer does not match input odometer", VehicleDataHelper.odometer, copy);
    }
    
    public void testGps() {
    	GPSData copy = ( (GetVehicleDataResponse) msg).getGps();
    	assertTrue("GPS does not match input GPS", Validator.validateGpsData(VehicleDataHelper.gps, copy));
    }
    
    public void testFuelLevel_State() {
    	ComponentVolumeStatus copy = ( (GetVehicleDataResponse) msg).getFuelLevel_State();
    	assertEquals("Fuel level does not match input fuel level", VehicleDataHelper.fuelLevelState, copy);
    }
    
    public void testInstantFuelConsumption() {
    	Double copy = ( (GetVehicleDataResponse) msg).getInstantFuelConsumption();
    	assertEquals("Instant fuel consumption does not match input instant fuel consumption", VehicleDataHelper.instantFuelConsumption, copy);
    }
    
    public void testBeltStatus() {
    	BeltStatus copy = ( (GetVehicleDataResponse) msg).getBeltStatus();
    	assertTrue("Belt status does not match input belt status", Validator.validateBeltStatus(VehicleDataHelper.beltStatus, copy));
    }
    
    public void testBodyInformation() {
    	BodyInformation copy = ( (GetVehicleDataResponse) msg).getBodyInformation();
    	assertTrue("Body information does not match input body information", Validator.validateBodyInformation(VehicleDataHelper.bodyInformation, copy));
    }
    
    public void testDeviceStatus() {
    	DeviceStatus copy = ( (GetVehicleDataResponse) msg).getDeviceStatus();
    	assertTrue("Device status does not match input device status", Validator.validateDeviceStatus(VehicleDataHelper.deviceStatus, copy));
    }
    
    public void testDriverBraking() {
    	VehicleDataEventStatus copy = ( (GetVehicleDataResponse) msg).getDriverBraking();
    	assertEquals("Driver braking does not match input driver braking", VehicleDataHelper.driverBraking, copy);
    }
    
    public void testWiperStatus() {
    	WiperStatus copy = ( (GetVehicleDataResponse) msg).getWiperStatus();
    	assertEquals("Wiper status does not match input wiper status", VehicleDataHelper.wiperStatus, copy);
    }
    
    public void testHeadLampStatus() {
    	HeadLampStatus copy = ( (GetVehicleDataResponse) msg).getHeadLampStatus();
    	assertTrue("Head lamp status does not match input head lamp status", Validator.validateHeadLampStatus(VehicleDataHelper.headLampStatus, copy));
    }
    
    public void testAccPedalPosition() {
    	Double copy = ( (GetVehicleDataResponse) msg).getAccPedalPosition();
    	assertEquals("Acc pedal position does not match input acc pedal position", VehicleDataHelper.accPedalPosition, copy);
    }
    
    public void testSteeringWheelAngle() {
    	Double copy = ( (GetVehicleDataResponse) msg).getSteeringWheelAngle();
    	assertEquals("Steering wheel angle does not match input steering wheel angle", VehicleDataHelper.steeringWheelAngle, copy);
    }
    
    public void testECallInfo() {
    	ECallInfo copy = ( (GetVehicleDataResponse) msg).getECallInfo();
    	assertTrue("Emergency call info does not match input emergency call info", Validator.validateECallInfo(VehicleDataHelper.eCallInfo, copy));
    }
    
    public void testAirbagStatus() {
    	AirbagStatus copy = ( (GetVehicleDataResponse) msg).getAirbagStatus();
    	assertTrue("Airbag status does not match input airbag status", Validator.validateAirbagStatus(VehicleDataHelper.airbagStatus, copy));
    }
    
    public void testEmergencyEvent() {
    	EmergencyEvent copy = ( (GetVehicleDataResponse) msg).getEmergencyEvent();
    	assertTrue("Emergency event does not match input emergency event", Validator.validateEmergencyEvent(VehicleDataHelper.emergencyEvent, copy));
    }
    
    public void testClusterModeStatus() {
    	ClusterModeStatus copy = ( (GetVehicleDataResponse) msg).getClusterModeStatus();
    	assertTrue("Cluster mode status does not match cluster mode status", Validator.validateClusterModeStatus(VehicleDataHelper.clusterModeStatus, copy));
    }
    
    public void testMyKey() {
    	MyKey copy = ( (GetVehicleDataResponse) msg).getMyKey();
    	assertTrue("My key does not match my key", Validator.validateMyKey(VehicleDataHelper.myKey, copy));
    }

    public void testJson() {
		JSONObject reference = new JSONObject();
		
		try {
			reference.put(GetVehicleDataResponse.KEY_SPEED, VehicleDataHelper.speed);
			reference.put(GetVehicleDataResponse.KEY_RPM, VehicleDataHelper.rpm);
			reference.put(GetVehicleDataResponse.KEY_EXTERNAL_TEMPERATURE, VehicleDataHelper.externalTemperature);
			reference.put(GetVehicleDataResponse.KEY_FUEL_LEVEL, VehicleDataHelper.fuelLevel);
			reference.put(GetVehicleDataResponse.KEY_VIN, VehicleDataHelper.vin);
			reference.put(GetVehicleDataResponse.KEY_PRNDL, VehicleDataHelper.prndl);
			reference.put(GetVehicleDataResponse.KEY_TIRE_PRESSURE, VehicleDataHelper.tirePressure.serializeJSON());
			reference.put(GetVehicleDataResponse.KEY_ENGINE_TORQUE, VehicleDataHelper.engineTorque);
			reference.put(GetVehicleDataResponse.KEY_ODOMETER, VehicleDataHelper.odometer);
			reference.put(GetVehicleDataResponse.KEY_GPS, VehicleDataHelper.gps.serializeJSON());
			reference.put(GetVehicleDataResponse.KEY_FUEL_LEVEL_STATE, VehicleDataHelper.fuelLevelState);
			reference.put(GetVehicleDataResponse.KEY_INSTANT_FUEL_CONSUMPTION, VehicleDataHelper.instantFuelConsumption);
			reference.put(GetVehicleDataResponse.KEY_BELT_STATUS, VehicleDataHelper.beltStatus.serializeJSON());
			reference.put(GetVehicleDataResponse.KEY_BODY_INFORMATION, VehicleDataHelper.bodyInformation.serializeJSON());
			reference.put(GetVehicleDataResponse.KEY_DEVICE_STATUS, VehicleDataHelper.deviceStatus.serializeJSON());
			reference.put(GetVehicleDataResponse.KEY_DRIVER_BRAKING, VehicleDataHelper.driverBraking);
			reference.put(GetVehicleDataResponse.KEY_WIPER_STATUS, VehicleDataHelper.wiperStatus);
			reference.put(GetVehicleDataResponse.KEY_HEAD_LAMP_STATUS, VehicleDataHelper.headLampStatus.serializeJSON());
			reference.put(GetVehicleDataResponse.KEY_ACC_PEDAL_POSITION, VehicleDataHelper.accPedalPosition);
			reference.put(GetVehicleDataResponse.KEY_STEERING_WHEEL_ANGLE, VehicleDataHelper.steeringWheelAngle);
			reference.put(GetVehicleDataResponse.KEY_E_CALL_INFO, VehicleDataHelper.eCallInfo.serializeJSON());
			reference.put(GetVehicleDataResponse.KEY_AIRBAG_STATUS, VehicleDataHelper.airbagStatus.serializeJSON());
			reference.put(GetVehicleDataResponse.KEY_EMERGENCY_EVENT, VehicleDataHelper.emergencyEvent.serializeJSON());
			reference.put(GetVehicleDataResponse.KEY_CLUSTER_MODE_STATUS, VehicleDataHelper.clusterModeStatus.serializeJSON());
			reference.put(GetVehicleDataResponse.KEY_MY_KEY, VehicleDataHelper.myKey.serializeJSON());
			
			JSONObject underTest = msg.serializeJSON();
			
			//go inside underTest and only return the JSONObject inside the parameters key inside the response key
			underTest = underTest.getJSONObject("response").getJSONObject("parameters");

			assertEquals("JSON size didn't match expected size.", reference.length(), underTest.length());

			Iterator<?> iterator = reference.keys();
			
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				
				if (key.equals(GetVehicleDataResponse.KEY_TIRE_PRESSURE)) {
					JSONObject tirePressureReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject tirePressureTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);

					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateTireStatus(
									new TireStatus(JsonRPCMarshaller.deserializeJSONObject(tirePressureReference)),
									new TireStatus(JsonRPCMarshaller.deserializeJSONObject(tirePressureTest))));
					
				}
				else if (key.equals(GetVehicleDataResponse.KEY_GPS)) {
					JSONObject GPSObjReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject GPSObjTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);
					
					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateGpsData(
									new GPSData(JsonRPCMarshaller.deserializeJSONObject(GPSObjReference)),
									new GPSData(JsonRPCMarshaller.deserializeJSONObject(GPSObjTest))));
				}
				else if (key.equals(GetVehicleDataResponse.KEY_BELT_STATUS)) {
					JSONObject beltObjReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject beltObjTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);
					
					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateBeltStatus(
									new BeltStatus(JsonRPCMarshaller.deserializeJSONObject(beltObjReference)),
									new BeltStatus(JsonRPCMarshaller.deserializeJSONObject(beltObjTest))));
				}
				else if (key.equals(GetVehicleDataResponse.KEY_BODY_INFORMATION)) {
					JSONObject bodyInfoObjReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject bodyInfoObjTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);
					
					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateBodyInformation(
									new BodyInformation(JsonRPCMarshaller.deserializeJSONObject(bodyInfoObjReference)),
									new BodyInformation(JsonRPCMarshaller.deserializeJSONObject(bodyInfoObjTest))));
				}
				else if (key.equals(GetVehicleDataResponse.KEY_DEVICE_STATUS)) {
					JSONObject deviceObjReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject deviceObjTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);
					
					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateDeviceStatus(
									new DeviceStatus(JsonRPCMarshaller.deserializeJSONObject(deviceObjReference)),
									new DeviceStatus(JsonRPCMarshaller.deserializeJSONObject(deviceObjTest))));
				}
				else if (key.equals(GetVehicleDataResponse.KEY_HEAD_LAMP_STATUS)) {
					JSONObject headLampObjReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject headLampObjTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);
					
					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateHeadLampStatus(
									new HeadLampStatus(JsonRPCMarshaller.deserializeJSONObject(headLampObjReference)),
									new HeadLampStatus(JsonRPCMarshaller.deserializeJSONObject(headLampObjTest))));
				}
				else if (key.equals(GetVehicleDataResponse.KEY_E_CALL_INFO)) {
					JSONObject callInfoObjReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject callInfoObjTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);
					
					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateECallInfo(
									new ECallInfo(JsonRPCMarshaller.deserializeJSONObject(callInfoObjReference)),
									new ECallInfo(JsonRPCMarshaller.deserializeJSONObject(callInfoObjTest))));
				}
				else if (key.equals(GetVehicleDataResponse.KEY_AIRBAG_STATUS)) {
					JSONObject airbagObjReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject airbagObjTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);
					
					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateAirbagStatus(
									new AirbagStatus(JsonRPCMarshaller.deserializeJSONObject(airbagObjReference)),
									new AirbagStatus(JsonRPCMarshaller.deserializeJSONObject(airbagObjTest))));
				}
				else if (key.equals(GetVehicleDataResponse.KEY_EMERGENCY_EVENT)) {
					JSONObject emergencyObjReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject emergencyObjTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);
					
					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateEmergencyEvent(
									new EmergencyEvent(JsonRPCMarshaller.deserializeJSONObject(emergencyObjReference)),
									new EmergencyEvent(JsonRPCMarshaller.deserializeJSONObject(emergencyObjTest))));
				}
				else if (key.equals(GetVehicleDataResponse.KEY_CLUSTER_MODE_STATUS)) {
					JSONObject clusterModeObjReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject clusterModeObjTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);
					
					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateClusterModeStatus(
									new ClusterModeStatus(JsonRPCMarshaller.deserializeJSONObject(clusterModeObjReference)),
									new ClusterModeStatus(JsonRPCMarshaller.deserializeJSONObject(clusterModeObjTest))));
				}
				else if (key.equals(GetVehicleDataResponse.KEY_MY_KEY)) {
					JSONObject myKeyObjReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject myKeyObjTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);
					
					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateMyKey(
									new MyKey(JsonRPCMarshaller.deserializeJSONObject(myKeyObjReference)),
									new MyKey(JsonRPCMarshaller.deserializeJSONObject(myKeyObjTest))));
				}
				else {
					assertEquals("JSON value didn't match expected value for key \"" + key + "\".",
							JsonUtils.readObjectFromJsonObject(reference, key),
							JsonUtils.readObjectFromJsonObject(underTest, key));
	            }
				
			}
			
		} catch (JSONException e) {
			/* do nothing */
		}
    }
    
    public void testNull(){
        GetVehicleDataResponse msg = new GetVehicleDataResponse();
        assertNotNull("Null object creation failed.", msg);

        testNullBase(msg);
        
        assertNull("Speed wasn't set, but getter method returned an object.", msg.getSpeed());
        assertNull("RPM wasn't set, but getter method returned an object.", msg.getRpm());
        assertNull("External temperature wasn't set, but getter method returned an object.", msg.getExternalTemperature());
        assertNull("Fuel level wasn't set, but getter method returned an object.", msg.getFuelLevel());
        assertNull("VIN wasn't set, but getter method returned an object.", msg.getVin());
        assertNull("PRNDL wasn't set, but getter method returned an object.", msg.getPrndl());
        assertNull("Tire pressure wasn't set, but getter method returned an object.", msg.getTirePressure());
        assertNull("Engine torque wasn't set, but getter method returned an object.", msg.getEngineTorque());
        assertNull("Odometer wasn't set, but getter method returned an object.", msg.getOdometer());
        assertNull("GPS wasn't set, but getter method returned an object.", msg.getGps());
        assertNull("Fuel level state wasn't set, but getter method returned an object.", msg.getFuelLevel_State());
        assertNull("Instant fuel consumption wasn't set, but getter method returned an object.", msg.getInstantFuelConsumption());
        assertNull("Belt status wasn't set, but getter method returned an object.", msg.getBeltStatus());
        assertNull("Body information wasn't set, but getter method returned an object.", msg.getBodyInformation());
        assertNull("Device status wasn't set, but getter method returned an object.", msg.getDeviceStatus());
        assertNull("Driver braking wasn't set, but getter method returned an object.", msg.getDriverBraking());
        assertNull("Wiper status wasn't set, but getter method returned an object.", msg.getWiperStatus());
        assertNull("Head lamp status wasn't set, but getter method returned an object.", msg.getHeadLampStatus());
        assertNull("Acceleration pedal position wasn't set, but getter method returned an object.", msg.getAccPedalPosition());
        assertNull("Steering wheel angle wasn't set, but getter method returned an object.", msg.getSteeringWheelAngle());
        assertNull("Emergency call info wasn't set, but getter method returned an object.", msg.getECallInfo());
        assertNull("Airbag status wasn't set, but getter method returned an object.", msg.getAirbagStatus());
        assertNull("Emergency event wasn't set, but getter method returned an object.", msg.getEmergencyEvent());
        assertNull("Cluster mode status wasn't set, but getter method returned an object.", msg.getClusterModeStatus());
        assertNull("My key wasn't set, but getter method returned an object.", msg.getMyKey());
    }
    
    public void testJsonConstructor () {
    	JSONObject commandJson = JsonFileReader.get(getCommandType(), getMessageType());
    	assertNotNull("Command object is null", commandJson);
    	
		try {
			Hashtable<String, Object> hash = JsonRPCMarshaller.deserializeJSONObject(commandJson);
			GetVehicleDataResponse cmd = new GetVehicleDataResponse(hash);
			
			JSONObject body = commandJson.getJSONObject(getMessageType());
			assertNotNull("Command type doesn't match expected message type", body);
			
			// test everything in the body
			assertEquals("Command name doesn't match input name", body.getString(RPCMessage.KEY_FUNCTION_NAME), cmd.getFunctionName());
			assertEquals("Correlation ID doesn't match input ID", (Integer) body.getInt(RPCMessage.KEY_CORRELATION_ID), cmd.getCorrelationID());
			
			JSONObject parameters = body.getJSONObject(RPCMessage.KEY_PARAMETERS);
			assertEquals("Speed doesn't match input speed", 
					parameters.getDouble(GetVehicleDataResponse.KEY_SPEED), cmd.getSpeed());
			assertEquals("Rpm doesn't match input rpm", 
					(Integer) parameters.getInt(GetVehicleDataResponse.KEY_RPM), cmd.getRpm());
			assertEquals("External temperature doesn't match input temperature", 
					parameters.getDouble(GetVehicleDataResponse.KEY_EXTERNAL_TEMPERATURE), cmd.getExternalTemperature());
			assertEquals("Fuel level doesn't match input level", 
					parameters.getDouble(GetVehicleDataResponse.KEY_FUEL_LEVEL), cmd.getFuelLevel());
			assertEquals("VIN doesn't match input VIN", 
					parameters.getString(GetVehicleDataResponse.KEY_VIN), cmd.getVin());
			assertEquals("PRNDL doesn't match input PRDNL", 
					parameters.getString(GetVehicleDataResponse.KEY_PRNDL), cmd.getPrndl().toString());
			
			JSONObject tireStatusObj = parameters.getJSONObject(GetVehicleDataResponse.KEY_TIRE_PRESSURE);
			TireStatus tireStatus = new TireStatus(JsonRPCMarshaller.deserializeJSONObject(tireStatusObj));
			assertTrue("Tire pressure doesn't match input pressure", Validator.validateTireStatus(tireStatus, cmd.getTirePressure()) );
			
			assertEquals("Engine torque doesn't match input torque", 
					parameters.getDouble(GetVehicleDataResponse.KEY_ENGINE_TORQUE), cmd.getEngineTorque());
			assertEquals("Odometer doesn't match input odometer", 
					(Integer) parameters.getInt(GetVehicleDataResponse.KEY_ODOMETER), cmd.getOdometer());
			
			JSONObject gpsDataObj = parameters.getJSONObject(GetVehicleDataResponse.KEY_GPS);
			GPSData gpsData = new GPSData(JsonRPCMarshaller.deserializeJSONObject(gpsDataObj));
			assertTrue("GPS doesn't match input GPS", Validator.validateGpsData(gpsData, cmd.getGps()) );
			
			assertEquals("Fuel level state doesn't match input state", 
					parameters.getString(GetVehicleDataResponse.KEY_FUEL_LEVEL_STATE), cmd.getFuelLevel_State().toString());
			assertEquals("Instant fuel consumption doesn't match input consumption", 
					parameters.getDouble(GetVehicleDataResponse.KEY_INSTANT_FUEL_CONSUMPTION), cmd.getInstantFuelConsumption());
			
			JSONObject beltStatusObj = parameters.getJSONObject(GetVehicleDataResponse.KEY_BELT_STATUS);
			BeltStatus beltStatus = new BeltStatus(JsonRPCMarshaller.deserializeJSONObject(beltStatusObj));
			assertTrue("Belt status doesn't match input status", Validator.validateBeltStatus(beltStatus, cmd.getBeltStatus()) );
			
			JSONObject bodyInformationObj = parameters.getJSONObject(GetVehicleDataResponse.KEY_BODY_INFORMATION);
			BodyInformation bodyInformation = new BodyInformation(JsonRPCMarshaller.deserializeJSONObject(bodyInformationObj));
			assertTrue("Body information doesn't match input information", Validator.validateBodyInformation(bodyInformation, cmd.getBodyInformation()) );
			
			JSONObject deviceStatusObj = parameters.getJSONObject(GetVehicleDataResponse.KEY_DEVICE_STATUS);
			DeviceStatus deviceStatus = new DeviceStatus(JsonRPCMarshaller.deserializeJSONObject(deviceStatusObj));
			assertTrue("Device status doesn't match input status", Validator.validateDeviceStatus(deviceStatus, cmd.getDeviceStatus()) );
			
			assertEquals("Driver braking doesn't match input braking", 
					parameters.getString(GetVehicleDataResponse.KEY_DRIVER_BRAKING), cmd.getDriverBraking().toString());
			assertEquals("Wiper status doesn't match input status", 
					parameters.getString(GetVehicleDataResponse.KEY_WIPER_STATUS), cmd.getWiperStatus().toString());
			
			JSONObject headLampStatusObj = parameters.getJSONObject(GetVehicleDataResponse.KEY_HEAD_LAMP_STATUS);
			HeadLampStatus headLampStatus = new HeadLampStatus(JsonRPCMarshaller.deserializeJSONObject(headLampStatusObj));
			assertTrue("Head lamp status doesn't match input status", Validator.validateHeadLampStatus(headLampStatus, cmd.getHeadLampStatus()) );
			
			assertEquals("Acceleration pedal position doesn't match input position", 
					parameters.getDouble(GetVehicleDataResponse.KEY_ACC_PEDAL_POSITION), cmd.getAccPedalPosition());
			assertEquals("Steering wheel angle doesn't match input angle", 
					parameters.getDouble(GetVehicleDataResponse.KEY_STEERING_WHEEL_ANGLE), cmd.getSteeringWheelAngle());
			
			JSONObject eCallInfoObj = parameters.getJSONObject(GetVehicleDataResponse.KEY_E_CALL_INFO);
			ECallInfo eCallInfo = new ECallInfo(JsonRPCMarshaller.deserializeJSONObject(eCallInfoObj));
			assertTrue("Emergency call info doesn't match input info", Validator.validateECallInfo(eCallInfo, cmd.getECallInfo()) );
			
			JSONObject airbagStatusObj = parameters.getJSONObject(GetVehicleDataResponse.KEY_AIRBAG_STATUS);
			AirbagStatus airbagStatus = new AirbagStatus(JsonRPCMarshaller.deserializeJSONObject(airbagStatusObj));
			assertTrue("Airbag status doesn't match input status", Validator.validateAirbagStatus(airbagStatus, cmd.getAirbagStatus()) );
			
			JSONObject emergencyEventObj = parameters.getJSONObject(GetVehicleDataResponse.KEY_EMERGENCY_EVENT);
			EmergencyEvent emergencyEvent = new EmergencyEvent(JsonRPCMarshaller.deserializeJSONObject(emergencyEventObj));
			assertTrue("Emergency event doesn't match input event", Validator.validateEmergencyEvent(emergencyEvent, cmd.getEmergencyEvent()) );
			
			JSONObject clusterModeStatusObj = parameters.getJSONObject(GetVehicleDataResponse.KEY_CLUSTER_MODE_STATUS);
			ClusterModeStatus clusterModeStatus = new ClusterModeStatus(JsonRPCMarshaller.deserializeJSONObject(clusterModeStatusObj));
			assertTrue("Cluster mode status doesn't match input status", Validator.validateClusterModeStatus(clusterModeStatus, cmd.getClusterModeStatus()) );
			
			JSONObject myKeyObj = parameters.getJSONObject(GetVehicleDataResponse.KEY_MY_KEY);
			MyKey myKey = new MyKey(JsonRPCMarshaller.deserializeJSONObject(myKeyObj));
			assertTrue("My key doesn't match input key", Validator.validateMyKey(myKey, cmd.getMyKey()) );
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }
    
}
