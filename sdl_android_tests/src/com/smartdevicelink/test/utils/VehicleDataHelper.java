package com.smartdevicelink.test.utils;

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
import com.smartdevicelink.proxy.rpc.GetVehicleData;
import com.smartdevicelink.proxy.rpc.GetVehicleDataResponse;
import com.smartdevicelink.proxy.rpc.HeadLampStatus;
import com.smartdevicelink.proxy.rpc.MyKey;
import com.smartdevicelink.proxy.rpc.OnVehicleData;
import com.smartdevicelink.proxy.rpc.TireStatus;
import com.smartdevicelink.proxy.rpc.enums.ComponentVolumeStatus;
import com.smartdevicelink.proxy.rpc.enums.PRNDL;
import com.smartdevicelink.proxy.rpc.enums.VehicleDataEventStatus;
import com.smartdevicelink.proxy.rpc.enums.WiperStatus;
import com.smartdevicelink.test.json.rpc.JsonFileReader;

public class VehicleDataHelper{
    public static double speed;
	public static int rpm;
	public static double externalTemperature;
	public static double fuelLevel;
	public static String vin;
	public static PRNDL prndl;
	public static TireStatus tirePressure;
	public static double engineTorque;
	public static int odometer;
	public static GPSData gps;
	public static ComponentVolumeStatus fuelLevelState;
	public static double instantFuelConsumption;
	public static BeltStatus beltStatus;
	public static BodyInformation bodyInformation;
	public static DeviceStatus deviceStatus;
	public static VehicleDataEventStatus driverBraking;
	public static WiperStatus wiperStatus;
	public static HeadLampStatus headLampStatus;
	public static double accPedalPosition;
	public static double steeringWheelAngle;
	public static ECallInfo eCallInfo;
	public static AirbagStatus airbagStatus;
	public static EmergencyEvent emergencyEvent;
	public static ClusterModeStatus clusterModeStatus;
	public static MyKey myKey;
	
	public static OnVehicleData vehicleData = new OnVehicleData();
	public static GetVehicleDataResponse vehicleDataResponse = new GetVehicleDataResponse();
	
	//GetVehicleData.json is where the vehicle data is
	private static JSONObject paramsJson = JsonFileReader.getParams(FunctionID.GET_VEHICLE_DATA, RPCMessage.KEY_RESPONSE);
	
	static {
		
		try {
			speed = paramsJson.getDouble(GetVehicleData.KEY_SPEED);
			rpm = paramsJson.getInt(GetVehicleData.KEY_RPM);
			externalTemperature = paramsJson.getDouble(GetVehicleData.KEY_EXTERNAL_TEMPERATURE);
			fuelLevel = paramsJson.getDouble(GetVehicleData.KEY_FUEL_LEVEL);
			vin = paramsJson.getString(GetVehicleData.KEY_VIN);
			prndl = PRNDL.valueForString(paramsJson.getString(GetVehicleData.KEY_PRNDL));
			JSONObject tirePressureJson = paramsJson.getJSONObject(GetVehicleData.KEY_TIRE_PRESSURE);
			tirePressure = new TireStatus(JsonRPCMarshaller.deserializeJSONObject(tirePressureJson));
			engineTorque = paramsJson.getDouble(GetVehicleData.KEY_ENGINE_TORQUE);
			odometer = paramsJson.getInt(GetVehicleData.KEY_ODOMETER);
			JSONObject gpsJson = paramsJson.getJSONObject(GetVehicleData.KEY_GPS);
			gps = new GPSData(JsonRPCMarshaller.deserializeJSONObject(gpsJson));
			fuelLevelState = ComponentVolumeStatus.valueForString(paramsJson.getString(GetVehicleData.KEY_FUEL_LEVEL_STATE));
			instantFuelConsumption = paramsJson.getDouble(GetVehicleData.KEY_INSTANT_FUEL_CONSUMPTION);
			JSONObject beltStatusJson = paramsJson.getJSONObject(GetVehicleData.KEY_BELT_STATUS);
			beltStatus = new BeltStatus(JsonRPCMarshaller.deserializeJSONObject(beltStatusJson));
			JSONObject bodyInformationJson = paramsJson.getJSONObject(GetVehicleData.KEY_BODY_INFORMATION);
			bodyInformation = new BodyInformation(JsonRPCMarshaller.deserializeJSONObject(bodyInformationJson));
			JSONObject deviceStatusJson = paramsJson.getJSONObject(GetVehicleData.KEY_DEVICE_STATUS);
			deviceStatus = new DeviceStatus(JsonRPCMarshaller.deserializeJSONObject(deviceStatusJson));
			driverBraking = VehicleDataEventStatus.valueForString(paramsJson.getString(GetVehicleData.KEY_DRIVER_BRAKING));
			wiperStatus = WiperStatus.valueForString(paramsJson.getString(GetVehicleData.KEY_WIPER_STATUS));
			JSONObject headLampStatusJson = paramsJson.getJSONObject(GetVehicleData.KEY_HEAD_LAMP_STATUS);
			headLampStatus = new HeadLampStatus(JsonRPCMarshaller.deserializeJSONObject(headLampStatusJson));
			accPedalPosition = paramsJson.getDouble(GetVehicleData.KEY_ACC_PEDAL_POSITION);
			steeringWheelAngle = paramsJson.getDouble(GetVehicleData.KEY_STEERING_WHEEL_ANGLE);
			JSONObject eCallInfoJson = paramsJson.getJSONObject(GetVehicleData.KEY_E_CALL_INFO);
			eCallInfo = new ECallInfo(JsonRPCMarshaller.deserializeJSONObject(eCallInfoJson));
			JSONObject airbagStatusJson = paramsJson.getJSONObject(GetVehicleData.KEY_AIRBAG_STATUS);
			airbagStatus = new AirbagStatus(JsonRPCMarshaller.deserializeJSONObject(airbagStatusJson));
			JSONObject emergencyEventJson = paramsJson.getJSONObject(GetVehicleData.KEY_EMERGENCY_EVENT);
			emergencyEvent = new EmergencyEvent(JsonRPCMarshaller.deserializeJSONObject(emergencyEventJson));
			JSONObject clusterModeStatusJson = paramsJson.getJSONObject(GetVehicleData.KEY_CLUSTER_MODE_STATUS);
			clusterModeStatus = new ClusterModeStatus(JsonRPCMarshaller.deserializeJSONObject(clusterModeStatusJson));
			JSONObject myKeyJson = paramsJson.getJSONObject(GetVehicleData.KEY_MY_KEY);
			myKey = new MyKey(JsonRPCMarshaller.deserializeJSONObject(myKeyJson));
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
    	//set up the OnVehicleData object
		vehicleData.setSpeed(speed);
		vehicleData.setRpm(rpm);
		vehicleData.setExternalTemperature(externalTemperature);
		vehicleData.setFuelLevel(fuelLevel);
		vehicleData.setVin(vin);
		vehicleData.setPrndl(prndl);
		vehicleData.setTirePressure(tirePressure);
		vehicleData.setEngineTorque(engineTorque);
		vehicleData.setOdometer(odometer);
		vehicleData.setGps(gps);
		vehicleData.setFuelLevel_State(fuelLevelState);
		vehicleData.setInstantFuelConsumption(instantFuelConsumption);
		vehicleData.setBeltStatus(beltStatus);
		vehicleData.setBodyInformation(bodyInformation);
		vehicleData.setDeviceStatus(deviceStatus);
		vehicleData.setDriverBraking(driverBraking);
		vehicleData.setWiperStatus(wiperStatus);
		vehicleData.setHeadLampStatus(headLampStatus);
		vehicleData.setAccPedalPosition(accPedalPosition);
		vehicleData.setSteeringWheelAngle(steeringWheelAngle);
		vehicleData.setECallInfo(eCallInfo);
		vehicleData.setAirbagStatus(airbagStatus);
		vehicleData.setEmergencyEvent(emergencyEvent);
		vehicleData.setClusterModeStatus(clusterModeStatus);
		vehicleData.setMyKey(myKey);
		
		//set up the GetVehicleDataResponse object
		vehicleDataResponse.setSpeed(speed);
		vehicleDataResponse.setRpm(rpm);
		vehicleDataResponse.setExternalTemperature(externalTemperature);
		vehicleDataResponse.setFuelLevel(fuelLevel);
		vehicleDataResponse.setVin(vin);
		vehicleDataResponse.setPrndl(prndl);
		vehicleDataResponse.setTirePressure(tirePressure);
		vehicleDataResponse.setEngineTorque(engineTorque);
		vehicleDataResponse.setOdometer(odometer);
		vehicleDataResponse.setGps(gps);
		vehicleDataResponse.setFuelLevel_State(fuelLevelState);
		vehicleDataResponse.setInstantFuelConsumption(instantFuelConsumption);
		vehicleDataResponse.setBeltStatus(beltStatus);
		vehicleDataResponse.setBodyInformation(bodyInformation);
		vehicleDataResponse.setDeviceStatus(deviceStatus);
		vehicleDataResponse.setDriverBraking(driverBraking);
		vehicleDataResponse.setWiperStatus(wiperStatus);
		vehicleDataResponse.setHeadLampStatus(headLampStatus);
		vehicleDataResponse.setAccPedalPosition(accPedalPosition);
		vehicleDataResponse.setSteeringWheelAngle(steeringWheelAngle);
		vehicleDataResponse.setECallInfo(eCallInfo);
		vehicleDataResponse.setAirbagStatus(airbagStatus);
		vehicleDataResponse.setEmergencyEvent(emergencyEvent);
		vehicleDataResponse.setClusterModeStatus(clusterModeStatus);
		vehicleDataResponse.setMyKey(myKey);
	}
	
    private VehicleDataHelper(){}	

}
