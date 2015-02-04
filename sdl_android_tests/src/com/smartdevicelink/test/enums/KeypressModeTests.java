package com.smartdevicelink.test.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import com.smartdevicelink.proxy.rpc.enums.KeypressMode;

public class KeypressModeTests extends TestCase {

	public void testValidEnums () {	
		String example = "SINGLE_KEYPRESS";
		KeypressMode enumSingleKeypress = KeypressMode.valueForString(example);
		example = "QUEUE_KEYPRESSES";
		KeypressMode enumQueueKeypresses = KeypressMode.valueForString(example);
		example = "RESEND_CURRENT_ENTRY";
		KeypressMode enumResendCurrentEntry = KeypressMode.valueForString(example);
		
		assertNotNull("SINGLE_KEYPRESS returned null", enumSingleKeypress);
		assertNotNull("QUEUE_KEYPRESSES returned null", enumQueueKeypresses);
		assertNotNull("RESEND_CURRENT_ENTRY returned null", enumResendCurrentEntry);
	}
	
	public void testInvalidEnum () {
		String example = "sIngLe_KeyPrESs";
		try {
			KeypressMode.valueForString(example);
			fail("Sample string did not throw an IllegalArgumentException");
		}
		catch (IllegalArgumentException exception) {
		}
	}
	
	public void testNullEnum () {
		String example = null;
		try {
			KeypressMode.valueForString(example);
			fail("Sample string did not throw a NullPointerException");
		}
		catch (NullPointerException exception) {
		}
	}	
	
	public void testListEnum() {
 		List<KeypressMode> enumValueList = Arrays.asList(KeypressMode.values());

		List<KeypressMode> enumTestList = new ArrayList<KeypressMode>();
		enumTestList.add(KeypressMode.SINGLE_KEYPRESS);
		enumTestList.add(KeypressMode.QUEUE_KEYPRESSES);
		enumTestList.add(KeypressMode.RESEND_CURRENT_ENTRY);

		assertTrue("Enum value list does not match enum class list", 
				enumValueList.containsAll(enumTestList) && enumTestList.containsAll(enumValueList));
	}
	
}