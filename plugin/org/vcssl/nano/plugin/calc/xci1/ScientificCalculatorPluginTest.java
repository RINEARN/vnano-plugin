package org.vcssl.nano.plugin.calc.xci1;

import org.vcssl.connect.ConnectorPermissionName;
import org.vcssl.connect.ExternalFunctionConnectorInterface1;
import org.vcssl.connect.ExternalVariableConnectorInterface1;
import org.vcssl.nano.plugin.calc.xci1.function.SinFunctionPlugin;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ScientificCalculatorPluginTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ScientificCalculatorPlugin plugin = new ScientificCalculatorPlugin();

		// Check permissions
		String[] necessaryPermissionNames = plugin.getNecessaryPermissionNames();
		assertEquals(1, necessaryPermissionNames.length);
		assertEquals(ConnectorPermissionName.NONE, necessaryPermissionNames[0]);
		String[] unnecessaryPermissionNames = plugin.getUnnecessaryPermissionNames();
		assertEquals(1, necessaryPermissionNames.length);
		assertEquals(ConnectorPermissionName.ALL, unnecessaryPermissionNames[0]);

		// Check variables
		ExternalVariableConnectorInterface1[] variables = plugin.getVariables();
		assertEquals(0, variables.length);

		// Check functions
		ExternalFunctionConnectorInterface1[] functions = plugin.getFunctions();
		assertEquals(1, functions.length);
		assertTrue(functions[0] instanceof SinFunctionPlugin);
	}

}
