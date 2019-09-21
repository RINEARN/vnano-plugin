package org.vcssl.nano.plugin.calc.xci1;

import org.vcssl.connect.ConnectorPermissionName;
import org.vcssl.connect.ExternalFunctionConnectorInterface1;
import org.vcssl.connect.ExternalVariableConnectorInterface1;
import org.vcssl.nano.plugin.calc.xci1.function.AbsFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.CosFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.ExpFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.LnFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.Log10FunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.MeanFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.PowFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.SinFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.SqrtFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.SumFunctionPlugin;
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
		int index = 0;
		assertTrue(functions[index++] instanceof SinFunctionPlugin);
		assertTrue(functions[index++] instanceof CosFunctionPlugin);
		assertTrue(functions[index++] instanceof TanFunctionPlugin);
		assertTrue(functions[index++] instanceof SqrtFunctionPlugin);
		assertTrue(functions[index++] instanceof LnFunctionPlugin);
		assertTrue(functions[index++] instanceof Log10FunctionPlugin);
		assertTrue(functions[index++] instanceof PowFunctionPlugin);
		assertTrue(functions[index++] instanceof ExpFunctionPlugin);
		assertTrue(functions[index++] instanceof AbsFunctionPlugin);
		assertTrue(functions[index++] instanceof SumFunctionPlugin);
		assertTrue(functions[index++] instanceof MeanFunctionPlugin);
		assertEquals(functions.length, index);
	}

}
