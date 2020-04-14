package org.vcssl.nano.plugin.calc.xci1;

import org.vcssl.connect.ExternalFunctionConnectorInterface1;
import org.vcssl.connect.ExternalVariableConnectorInterface1;
import org.vcssl.nano.plugin.calc.xci1.function.AbsFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.AcosFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.AsinFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.AtanFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.CosFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.DegFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.ExpFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.LengthFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.LnFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.Log10FunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.MeanFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.PowFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.RadFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.Sdn1FunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.SdnFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.SinFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.SqrtFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.SumFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.TanFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.Van1FunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.VanFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.variable.PiVariablePlugin;

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

		// Check variables
		ExternalVariableConnectorInterface1[] variables = plugin.getVariables();
		int variableIndex = 0;
		assertTrue(variables[variableIndex++] instanceof PiVariablePlugin);
		assertEquals(variables.length, variableIndex);

		// Check functions
		ExternalFunctionConnectorInterface1[] functions = plugin.getFunctions();
		int functionIndex = 0;
		assertTrue(functions[functionIndex++] instanceof LengthFunctionPlugin);
		assertTrue(functions[functionIndex++] instanceof RadFunctionPlugin);
		assertTrue(functions[functionIndex++] instanceof DegFunctionPlugin);
		assertTrue(functions[functionIndex++] instanceof SinFunctionPlugin);
		assertTrue(functions[functionIndex++] instanceof CosFunctionPlugin);
		assertTrue(functions[functionIndex++] instanceof TanFunctionPlugin);
		assertTrue(functions[functionIndex++] instanceof AsinFunctionPlugin);
		assertTrue(functions[functionIndex++] instanceof AcosFunctionPlugin);
		assertTrue(functions[functionIndex++] instanceof AtanFunctionPlugin);
		assertTrue(functions[functionIndex++] instanceof SqrtFunctionPlugin);
		assertTrue(functions[functionIndex++] instanceof LnFunctionPlugin);
		assertTrue(functions[functionIndex++] instanceof Log10FunctionPlugin);
		assertTrue(functions[functionIndex++] instanceof PowFunctionPlugin);
		assertTrue(functions[functionIndex++] instanceof ExpFunctionPlugin);
		assertTrue(functions[functionIndex++] instanceof AbsFunctionPlugin);
		assertTrue(functions[functionIndex++] instanceof SumFunctionPlugin);
		assertTrue(functions[functionIndex++] instanceof MeanFunctionPlugin);
		assertTrue(functions[functionIndex++] instanceof VanFunctionPlugin);
		assertTrue(functions[functionIndex++] instanceof Van1FunctionPlugin);
		assertTrue(functions[functionIndex++] instanceof SdnFunctionPlugin);
		assertTrue(functions[functionIndex++] instanceof Sdn1FunctionPlugin);
		assertEquals(functions.length, functionIndex);
	}
}
