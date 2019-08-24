package org.vcssl.nano.plugin;

import org.vcssl.connect.ConnectorPermission;
import org.vcssl.connect.ExternalFunctionConnector1;
import org.vcssl.connect.ExternalVariableConnector1;
import org.vcssl.nano.plugin.simplecalculator.SinFunctionPlugin;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleCalculatorPluginTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		SimpleCalculatorPlugin plugin = new SimpleCalculatorPlugin();

		// Check permissions
		String[] necessaryPermissions = plugin.getNecessaryPermissions();
		assertEquals(1, necessaryPermissions.length);
		assertEquals(ConnectorPermission.NONE, necessaryPermissions[0]);
		String[] unnecessaryPermissions = plugin.getUnnecessaryPermissions();
		assertEquals(1, necessaryPermissions.length);
		assertEquals(ConnectorPermission.ALL, unnecessaryPermissions[0]);

		// Check variables
		ExternalVariableConnector1[] variables = plugin.getVariables();
		assertEquals(0, variables.length);

		// Check functions
		ExternalFunctionConnector1[] functions = plugin.getFunctions();
		assertEquals(1, functions.length);
		assertTrue(functions[0] instanceof SinFunctionPlugin);
	}

}
