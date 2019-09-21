package org.vcssl.nano.plugin.calc.xci1;

import org.vcssl.connect.ConnectorPermissionName;
import org.vcssl.connect.ExternalFunctionConnectorInterface1;
import org.vcssl.connect.ExternalVariableConnectorInterface1;
import org.vcssl.nano.plugin.calc.xci1.function.CosFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.ExpFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.LnFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.Log10FunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.PowFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.SinFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.SqrtFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.TanFunctionPlugin;

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
		assertEquals(8, functions.length);
		assertTrue(functions[0] instanceof SinFunctionPlugin);
		assertTrue(functions[1] instanceof CosFunctionPlugin);
		assertTrue(functions[2] instanceof TanFunctionPlugin);
		assertTrue(functions[3] instanceof SqrtFunctionPlugin);
		assertTrue(functions[4] instanceof LnFunctionPlugin);
		assertTrue(functions[5] instanceof Log10FunctionPlugin);
		assertTrue(functions[6] instanceof PowFunctionPlugin);
		assertTrue(functions[7] instanceof ExpFunctionPlugin);
	}

}
