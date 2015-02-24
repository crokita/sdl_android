package com.smartdevicelink.test.rpc.notifications;

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
import com.smartdevicelink.proxy.rpc.HeadLampStatus;
import com.smartdevicelink.proxy.rpc.MyKey;
import com.smartdevicelink.proxy.rpc.OnVehicleData;
import com.smartdevicelink.proxy.rpc.TireStatus;
import com.smartdevicelink.proxy.rpc.enums.ComponentVolumeStatus;
import com.smartdevicelink.proxy.rpc.enums.PRNDL;
import com.smartdevicelink.proxy.rpc.enums.VehicleDataEventStatus;
import com.smartdevicelink.proxy.rpc.enums.WiperStatus;
import com.smartdevicelink.test.BaseRpcTests;
import com.smartdevicelink.test.utils.JsonUtils;
import com.smartdevicelink.test.utils.Validator;
import com.smartdevicelink.test.utils.VehicleDataHelper;


public class OnVehicleDataTests extends BaseRpcTests{
	
    @Override
    protected RPCMessage createMessage(){
    	return VehicleDataHelper.vehicleData;
    }

    @Override
    protected String getMessageType(){
    	return RPCMessage.KEY_NOTIFICATION;
    }

    @Override
    protected String getCommandType(){
    	return FunctionID.ON_VEHICLE_DATA;
    }

    @Override
    protected JSONObject getExpectedParameters(int sdlVersion){
        JSONObject result = new JSONObject();

        try{
            result.put(OnVehicleData.KEY_SPEED, VehicleDataHelper.speed);
            result.put(OnVehicleData.KEY_RPM, VehicleDataHelper.rpm);
            result.put(OnVehicleData.KEY_EXTERNAL_TEMPERATURE, VehicleDataHelper.externalTemperature);
            result.put(OnVehicleData.KEY_FUEL_LEVEL, VehicleDataHelper.fuelLevel);
            result.put(OnVehicleData.KEY_VIN, VehicleDataHelper.vin);
            result.put(OnVehicleData.KEY_PRNDL, VehicleDataHelper.prndl);
            result.put(OnVehicleData.KEY_TIRE_PRESSURE, VehicleDataHelper.tirePressure.serializeJSON());
            result.put(OnVehicleData.KEY_ENGINE_TORQUE, VehicleDataHelper.engineTorque);
            result.put(OnVehicleData.KEY_ODOMETER, VehicleDataHelper.odometer);
            result.put(OnVehicleData.KEY_GPS, VehicleDataHelper.gps.serializeJSON());
            result.put(OnVehicleData.KEY_FUEL_LEVEL_STATE, VehicleDataHelper.fuelLevelState);
            result.put(OnVehicleData.KEY_INSTANT_FUEL_CONSUMPTION, VehicleDataHelper.instantFuelConsumption);
            result.put(OnVehicleData.KEY_BELT_STATUS, VehicleDataHelper.beltStatus.serializeJSON());
            result.put(OnVehicleData.KEY_BODY_INFORMATION, VehicleDataHelper.bodyInformation.serializeJSON());
            result.put(OnVehicleData.KEY_DEVICE_STATUS, VehicleDataHelper.deviceStatus.serializeJSON());
            result.put(OnVehicleData.KEY_DRIVER_BRAKING, VehicleDataHelper.driverBraking);
            result.put(OnVehicleData.KEY_WIPER_STATUS, VehicleDataHelper.wiperStatus);
            result.put(OnVehicleData.KEY_HEAD_LAMP_STATUS, VehicleDataHelper.headLampStatus.serializeJSON());
            result.put(OnVehicleData.KEY_ACC_PEDAL_POSITION, VehicleDataHelper.accPedalPosition);
            result.put(OnVehicleData.KEY_STEERING_WHEEL_ANGLE, VehicleDataHelper.steeringWheelAngle);
            result.put(OnVehicleData.KEY_E_CALL_INFO, VehicleDataHelper.eCallInfo.serializeJSON());
            result.put(OnVehicleData.KEY_AIRBAG_STATUS, VehicleDataHelper.airbagStatus.serializeJSON());
            result.put(OnVehicleData.KEY_EMERGENCY_EVENT, VehicleDataHelper.emergencyEvent.serializeJSON());
            result.put(OnVehicleData.KEY_CLUSTER_MODE_STATUS, VehicleDataHelper.clusterModeStatus.serializeJSON());
            result.put(OnVehicleData.KEY_MY_KEY, VehicleDataHelper.myKey.serializeJSON());
            
        }catch(JSONException e){
            //do nothing 
        }

        return result;
    }
    
    public void testSpeed() {
    	Double copy = ( (OnVehicleData) msg).getSpeed();
    	assertEquals("Speed does not match input speed", VehicleDataHelper.speed, copy);
    }
    
    public void testRpm() {
    	int copy = ( (OnVehicleData) msg).getRpm();
    	assertEquals("RPM does not match input RPM", VehicleDataHelper.rpm, copy);
    }
    
    public void testExternalTemperature() {
    	Double copy = ( (OnVehicleData) msg).getExternalTemperature();
    	assertEquals("External temperature does not match input external temperature", VehicleDataHelper.externalTemperature, copy);
    }
    
    public void testFuelLevel() {
    	Double copy = ( (OnVehicleData) msg).getFuelLevel();
    	assertEquals("Fuel level does not match input fuel level", VehicleDataHelper.fuelLevel, copy);
    }
    
    public void testVin() {
    	String copy = ( (OnVehicleData) msg).getVin();
    	assertEquals("VIN does not match input VIN", VehicleDataHelper.vin, copy);
    }
    
    public void testPRNDL() {
    	PRNDL copy = ( (OnVehicleData) msg).getPrndl();
    	assertEquals("PRNDL does not match input PRNDL", VehicleDataHelper.prndl, copy);
    }
    
    public void testTirePressure() {
    	TireStatus copy = ( (OnVehicleData) msg).getTirePressure();
    	assertTrue("Tire pressure does not match input tire pressure", Validator.validateTireStatus(VehicleDataHelper.tirePressure, copy));
    }
    
    public void testEngineTorque() {
    	Double copy = ( (OnVehicleData) msg).getEngineTorque();
    	assertEquals("Engine torque does not match input engine torque", VehicleDataHelper.engineTorque, copy);
    }
    
    public void testOdometer() {
    	int copy = ( (OnVehicleData) msg).getOdometer();
    	assertEquals("Odometer does not match input odometer", VehicleDataHelper.odometer, copy);
    }
    
    public void testGps() {
    	GPSData copy = ( (OnVehicleData) msg).getGps();
    	assertTrue("GPS does not match input GPS", Validator.validateGpsData(VehicleDataHelper.gps, copy));
    }
    
    public void testFuelLevel_State() {
    	ComponentVolumeStatus copy = ( (OnVehicleData) msg).getFuelLevel_State();
    	assertEquals("Fuel level does not match input fuel level", VehicleDataHelper.fuelLevelState, copy);
    }
    
    public void testInstantFuelConsumption() {
    	Double copy = ( (OnVehicleData) msg).getInstantFuelConsumption();
    	assertEquals("Instant fuel consumption does not match input instant fuel consumption", VehicleDataHelper.instantFuelConsumption, copy);
    }
    
    public void testBeltStatus() {
    	BeltStatus copy = ( (OnVehicleData) msg).getBeltStatus();
    	assertTrue("Belt status does not match input belt status", Validator.validateBeltStatus(VehicleDataHelper.beltStatus, copy));
    }
    
    public void testBodyInformation() {
    	BodyInformation copy = ( (OnVehicleData) msg).getBodyInformation();
    	assertTrue("Body information does not match input body information", Validator.validateBodyInformation(VehicleDataHelper.bodyInformation, copy));
    }
    
    public void testDeviceStatus() {
    	DeviceStatus copy = ( (OnVehicleData) msg).getDeviceStatus();
    	assertTrue("Device status does not match input device status", Validator.validateDeviceStatus(VehicleDataHelper.deviceStatus, copy));
    }
    
    public void testDriverBraking() {
    	VehicleDataEventStatus copy = ( (OnVehicleData) msg).getDriverBraking();
    	assertEquals("Driver braking does not match input driver braking", VehicleDataHelper.driverBraking, copy);
    }
    
    public void testWiperStatus() {
    	WiperStatus copy = ( (OnVehicleData) msg).getWiperStatus();
    	assertEquals("Wiper status does not match input wiper status", VehicleDataHelper.wiperStatus, copy);
    }
    
    public void testHeadLampStatus() {
    	HeadLampStatus copy = ( (OnVehicleData) msg).getHeadLampStatus();
    	assertTrue("Head lamp status does not match input head lamp status", Validator.validateHeadLampStatus(VehicleDataHelper.headLampStatus, copy));
    }
    
    public void testAccPedalPosition() {
    	Double copy = ( (OnVehicleData) msg).getAccPedalPosition();
    	assertEquals("Acc pedal position does not match input acc pedal position", VehicleDataHelper.accPedalPosition, copy);
    }
    
    public void testSteeringWheelAngle() {
    	Double copy = ( (OnVehicleData) msg).getSteeringWheelAngle();
    	assertEquals("Steering wheel angle does not match input steering wheel angle", VehicleDataHelper.steeringWheelAngle, copy);
    }
    
    public void testECallInfo() {
    	ECallInfo copy = ( (OnVehicleData) msg).getECallInfo();
    	assertTrue("Emergency call info does not match input emergency call info", Validator.validateECallInfo(VehicleDataHelper.eCallInfo, copy));
    }
    
    public void testAirbagStatus() {
    	AirbagStatus copy = ( (OnVehicleData) msg).getAirbagStatus();
    	assertTrue("Airbag status does not match input airbag status", Validator.validateAirbagStatus(VehicleDataHelper.airbagStatus, copy));
    }
    
    public void testEmergencyEvent() {
    	EmergencyEvent copy = ( (OnVehicleData) msg).getEmergencyEvent();
    	assertTrue("Emergency event does not match input emergency event", Validator.validateEmergencyEvent(VehicleDataHelper.emergencyEvent, copy));
    }
    
    public void testClusterModeStatus() {
    	ClusterModeStatus copy = ( (OnVehicleData) msg).getClusterModeStatus();
    	assertTrue("Cluster mode status does not match cluster mode status", Validator.validateClusterModeStatus(VehicleDataHelper.clusterModeStatus, copy));
    }
    
    public void testMyKey() {
    	MyKey copy = ( (OnVehicleData) msg).getMyKey();
    	assertTrue("My key does not match my key", Validator.validateMyKey(VehicleDataHelper.myKey, copy));
    }

    public void testJson() {
		JSONObject reference = new JSONObject();
		
		try {
			reference.put(OnVehicleData.KEY_SPEED, VehicleDataHelper.speed);
			reference.put(OnVehicleData.KEY_RPM, VehicleDataHelper.rpm);
			reference.put(OnVehicleData.KEY_EXTERNAL_TEMPERATURE, VehicleDataHelper.externalTemperature);
			reference.put(OnVehicleData.KEY_FUEL_LEVEL, VehicleDataHelper.fuelLevel);
			reference.put(OnVehicleData.KEY_VIN, VehicleDataHelper.vin);
			reference.put(OnVehicleData.KEY_PRNDL, VehicleDataHelper.prndl);
			reference.put(OnVehicleData.KEY_TIRE_PRESSURE, VehicleDataHelper.tirePressure.serializeJSON());
			reference.put(OnVehicleData.KEY_ENGINE_TORQUE, VehicleDataHelper.engineTorque);
			reference.put(OnVehicleData.KEY_ODOMETER, VehicleDataHelper.odometer);
			reference.put(OnVehicleData.KEY_GPS, VehicleDataHelper.gps.serializeJSON());
			reference.put(OnVehicleData.KEY_FUEL_LEVEL_STATE, VehicleDataHelper.fuelLevelState);
			reference.put(OnVehicleData.KEY_INSTANT_FUEL_CONSUMPTION, VehicleDataHelper.instantFuelConsumption);
			reference.put(OnVehicleData.KEY_BELT_STATUS, VehicleDataHelper.beltStatus.serializeJSON());
			reference.put(OnVehicleData.KEY_BODY_INFORMATION, VehicleDataHelper.bodyInformation.serializeJSON());
			reference.put(OnVehicleData.KEY_DEVICE_STATUS, VehicleDataHelper.deviceStatus.serializeJSON());
			reference.put(OnVehicleData.KEY_DRIVER_BRAKING, VehicleDataHelper.driverBraking);
			reference.put(OnVehicleData.KEY_WIPER_STATUS, VehicleDataHelper.wiperStatus);
			reference.put(OnVehicleData.KEY_HEAD_LAMP_STATUS, VehicleDataHelper.headLampStatus.serializeJSON());
			reference.put(OnVehicleData.KEY_ACC_PEDAL_POSITION, VehicleDataHelper.accPedalPosition);
			reference.put(OnVehicleData.KEY_STEERING_WHEEL_ANGLE, VehicleDataHelper.steeringWheelAngle);
			reference.put(OnVehicleData.KEY_E_CALL_INFO, VehicleDataHelper.eCallInfo.serializeJSON());
			reference.put(OnVehicleData.KEY_AIRBAG_STATUS, VehicleDataHelper.airbagStatus.serializeJSON());
			reference.put(OnVehicleData.KEY_EMERGENCY_EVENT, VehicleDataHelper.emergencyEvent.serializeJSON());
			reference.put(OnVehicleData.KEY_CLUSTER_MODE_STATUS, VehicleDataHelper.clusterModeStatus.serializeJSON());
			reference.put(OnVehicleData.KEY_MY_KEY, VehicleDataHelper.myKey.serializeJSON());
			
			JSONObject underTest = msg.serializeJSON();
			//go inside underTest and only return the JSONObject inside the parameters key inside the notification key
			underTest = underTest.getJSONObject("notification").getJSONObject("parameters");
			
			assertEquals("JSON size didn't match expected size.", reference.length(), underTest.length());

			Iterator<?> iterator = reference.keys();
			
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				
				if (key.equals(OnVehicleData.KEY_TIRE_PRESSURE)) {
					JSONObject tirePressureReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject tirePressureTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);
					
					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateTireStatus(
									new TireStatus(JsonRPCMarshaller.deserializeJSONObject(tirePressureReference)),
									new TireStatus(JsonRPCMarshaller.deserializeJSONObject(tirePressureTest))));
					
				}
				else if (key.equals(OnVehicleData.KEY_GPS)) {
					JSONObject GPSObjReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject GPSObjTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);
					
					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateGpsData(
									new GPSData(JsonRPCMarshaller.deserializeJSONObject(GPSObjReference)),
									new GPSData(JsonRPCMarshaller.deserializeJSONObject(GPSObjTest))));
				}
				else if (key.equals(OnVehicleData.KEY_BELT_STATUS)) {
					JSONObject beltObjReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject beltObjTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);
					
					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateBeltStatus(
									new BeltStatus(JsonRPCMarshaller.deserializeJSONObject(beltObjReference)),
									new BeltStatus(JsonRPCMarshaller.deserializeJSONObject(beltObjTest))));
				}
				else if (key.equals(OnVehicleData.KEY_BODY_INFORMATION)) {
					JSONObject bodyInfoObjReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject bodyInfoObjTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);
					
					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateBodyInformation(
									new BodyInformation(JsonRPCMarshaller.deserializeJSONObject(bodyInfoObjReference)),
									new BodyInformation(JsonRPCMarshaller.deserializeJSONObject(bodyInfoObjTest))));
				}
				else if (key.equals(OnVehicleData.KEY_DEVICE_STATUS)) {
					JSONObject deviceObjReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject deviceObjTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);
					
					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateDeviceStatus(
									new DeviceStatus(JsonRPCMarshaller.deserializeJSONObject(deviceObjReference)),
									new DeviceStatus(JsonRPCMarshaller.deserializeJSONObject(deviceObjTest))));
				}
				else if (key.equals(OnVehicleData.KEY_HEAD_LAMP_STATUS)) {
					JSONObject headLampObjReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject headLampObjTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);
					
					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateHeadLampStatus(
									new HeadLampStatus(JsonRPCMarshaller.deserializeJSONObject(headLampObjReference)),
									new HeadLampStatus(JsonRPCMarshaller.deserializeJSONObject(headLampObjTest))));
				}
				else if (key.equals(OnVehicleData.KEY_E_CALL_INFO)) {
					JSONObject callInfoObjReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject callInfoObjTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);
					
					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateECallInfo(
									new ECallInfo(JsonRPCMarshaller.deserializeJSONObject(callInfoObjReference)),
									new ECallInfo(JsonRPCMarshaller.deserializeJSONObject(callInfoObjTest))));
				}
				else if (key.equals(OnVehicleData.KEY_AIRBAG_STATUS)) {
					JSONObject airbagObjReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject airbagObjTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);
					
					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateAirbagStatus(
									new AirbagStatus(JsonRPCMarshaller.deserializeJSONObject(airbagObjReference)),
									new AirbagStatus(JsonRPCMarshaller.deserializeJSONObject(airbagObjTest))));
				}
				else if (key.equals(OnVehicleData.KEY_EMERGENCY_EVENT)) {
					JSONObject emergencyObjReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject emergencyObjTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);
					
					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateEmergencyEvent(
									new EmergencyEvent(JsonRPCMarshaller.deserializeJSONObject(emergencyObjReference)),
									new EmergencyEvent(JsonRPCMarshaller.deserializeJSONObject(emergencyObjTest))));
				}
				else if (key.equals(OnVehicleData.KEY_CLUSTER_MODE_STATUS)) {
					JSONObject clusterModeObjReference = JsonUtils.readJsonObjectFromJsonObject(reference, key);
					JSONObject clusterModeObjTest = JsonUtils.readJsonObjectFromJsonObject(underTest, key);
					
					assertTrue("JSON value didn't match expected value for key \"" + key + "\".",
							Validator.validateClusterModeStatus(
									new ClusterModeStatus(JsonRPCMarshaller.deserializeJSONObject(clusterModeObjReference)),
									new ClusterModeStatus(JsonRPCMarshaller.deserializeJSONObject(clusterModeObjTest))));
				}
				else if (key.equals(OnVehicleData.KEY_MY_KEY)) {
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
        OnVehicleData msg = new OnVehicleData();
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
    

}
