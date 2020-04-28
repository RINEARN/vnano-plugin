package org.vcssl.nano.plugin.calc.xci1;

import java.util.LinkedList;
import java.util.List;

import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ExternalFunctionConnectorInterface1;
import org.vcssl.connect.ExternalNamespaceConnectorInterface1;
import org.vcssl.connect.ExternalVariableConnectorInterface1;
import org.vcssl.nano.plugin.calc.xci1.function.SinFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.SqrtFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.SumFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.TanFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.Van1FunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.function.VanFunctionPlugin;
import org.vcssl.nano.plugin.calc.xci1.variable.PiVariablePlugin;
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

public class ScientificCalculatorPlugin implements ExternalNamespaceConnectorInterface1 {

	@Override
	public String getNamespaceName() {
		return "SimpleCalculatorPlugin";
	}

	@Override
	public ExternalFunctionConnectorInterface1[] getFunctions() {
		List<ExternalFunctionConnectorInterface1> functionList = new LinkedList<ExternalFunctionConnectorInterface1>();
		functionList.add(new LengthFunctionPlugin());
		functionList.add(new RadFunctionPlugin());
		functionList.add(new DegFunctionPlugin());
		functionList.add(new SinFunctionPlugin());
		functionList.add(new CosFunctionPlugin());
		functionList.add(new TanFunctionPlugin());
		functionList.add(new AsinFunctionPlugin());
		functionList.add(new AcosFunctionPlugin());
		functionList.add(new AtanFunctionPlugin());
		functionList.add(new SqrtFunctionPlugin());
		functionList.add(new LnFunctionPlugin());
		functionList.add(new Log10FunctionPlugin());
		functionList.add(new PowFunctionPlugin());
		functionList.add(new ExpFunctionPlugin());
		functionList.add(new AbsFunctionPlugin());
		functionList.add(new SumFunctionPlugin());
		functionList.add(new MeanFunctionPlugin());
		functionList.add(new VanFunctionPlugin());
		functionList.add(new Van1FunctionPlugin());
		functionList.add(new SdnFunctionPlugin());
		functionList.add(new Sdn1FunctionPlugin());
		return functionList.toArray(new ExternalFunctionConnectorInterface1[0]);
	}

	@Override
	public ExternalVariableConnectorInterface1[] getVariables() {
		List<ExternalVariableConnectorInterface1> variableList = new LinkedList<ExternalVariableConnectorInterface1>();
		variableList.add(new PiVariablePlugin());
		return variableList.toArray(new ExternalVariableConnectorInterface1[0]);
	}

	@Override
	public void preInitializeForConnection(Object engineConnector) throws ConnectorException {
	}

	@Override
	public void postInitializeForConnection(Object engineConnector) throws ConnectorException {
	}

	@Override
	public void preFinalizeForDisconnection(Object engineConnector) throws ConnectorException {
	}

	@Override
	public void postFinalizeForDisconnection(Object engineConnector) throws ConnectorException {
	}

	@Override
	public void preInitializeForExecution(Object engineConnector) throws ConnectorException {
	}

	@Override
	public void postInitializeForExecution(Object engineConnector) throws ConnectorException {
	}

	@Override
	public void preFinalizeForTermination(Object engineConnector) throws ConnectorException {
	}

	@Override
	public void postFinalizeForTermination(Object engineConnector) throws ConnectorException {
	}
}