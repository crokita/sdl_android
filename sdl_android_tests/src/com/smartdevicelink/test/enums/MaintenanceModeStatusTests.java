package com.smartdevicelink.test.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import com.smartdevicelink.proxy.rpc.enums.MaintenanceModeStatus;

public class MaintenanceModeStatusTests extends TestCase {

	public void testValidEnums () {	
		String example = "NORMAL";
		MaintenanceModeStatus enumNormal = MaintenanceModeStatus.valueForString(example);
		example = "NEAR";
		MaintenanceModeStatus enumNear = MaintenanceModeStatus.valueForString(example);
		example = "ACTIVE";
		MaintenanceModeStatus enumActive = MaintenanceModeStatus.valueForString(example);
		example = "FEATURE_NOT_PRESENT";
		MaintenanceModeStatus enumFeatureNotPresent = MaintenanceModeStatus.valueForString(example);
		
		assertNotNull("NORMAL returned null", enumNormal);
		assertNotNull("NEAR returned null", enumNear);
		assertNotNull("ACTIVE returned null", enumActive);
		assertNotNull("FEATURE_NOT_PRESENT returned null", enumFeatureNotPresent);
	}
	
	public void testInvalidEnum () {
		String example = "NorMal";
		try {
			MaintenanceModeStatus.valueForString(example);
			fail("Sample string did not throw an IllegalArgumentException");
		}
		catch (IllegalArgumentException exception) {
		}
	}
	
	public void testNullEnum () {
		String example = null;
		try {
			MaintenanceModeStatus.valueForString(example);
			fail("Sample string did not throw a NullPointerException");
		}
		catch (NullPointerException exception) {
		}
	}	
	
	public void testListEnum() {
 		List<MaintenanceModeStatus> enumValueList = Arrays.asList(MaintenanceModeStatus.values());

		List<MaintenanceModeStatus> enumTestList = new ArrayList<MaintenanceModeStatus>();
		enumTestList.add(MaintenanceModeStatus.NORMAL);
		enumTestList.add(MaintenanceModeStatus.NEAR);
		enumTestList.add(MaintenanceModeStatus.ACTIVE);
		enumTestList.add(MaintenanceModeStatus.FEATURE_NOT_PRESENT);

		assertTrue("Enum value list does not match enum class list", 
				enumValueList.containsAll(enumTestList) && enumTestList.containsAll(enumValueList));
	}
	
}