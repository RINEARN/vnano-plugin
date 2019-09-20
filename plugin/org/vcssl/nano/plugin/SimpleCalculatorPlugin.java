package org.vcssl.nano.plugin;

import org.vcssl.connect.ConnectorPermissionName;

import java.util.LinkedList;
import java.util.List;

import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ExternalFunctionConnectorInterface1;
import org.vcssl.connect.ExternalNamespaceConnectorInterface1;
import org.vcssl.connect.ExternalVariableConnectorInterface1;
import org.vcssl.nano.plugin.simplecalculator.SinFunctionPlugin;

public class SimpleCalculatorPlugin implements ExternalNamespaceConnectorInterface1 {

	@Override
	public String getNamespaceName() {
		return "SimpleCalculatorPlugin";
	}

	@Override
	public String[] getNecessaryPermissionNames() {
		return new String[]{ ConnectorPermissionName.NONE };
	}

	@Override
	public String[] getUnnecessaryPermissionNames() {
		return new String[]{ ConnectorPermissionName.ALL };
	}

	@Override
	public ExternalFunctionConnectorInterface1[] getFunctions() {
		List<ExternalFunctionConnectorInterface1> functionList = new LinkedList<ExternalFunctionConnectorInterface1>();
		functionList.add(new SinFunctionPlugin());
		return functionList.toArray(new ExternalFunctionConnectorInterface1[0]);
	}

	@Override
	public ExternalVariableConnectorInterface1[] getVariables() {
		List<ExternalVariableConnectorInterface1> variableList = new LinkedList<ExternalVariableConnectorInterface1>();
		return variableList.toArray(new ExternalVariableConnectorInterface1[0]);
	}

	@Override
	public void initializeForConnection(Object engineConnector) throws ConnectorException {
	}

	@Override
	public void finalizeForDisconnection(Object engineConnector) throws ConnectorException {
	}

	@Override
	public void initializeForExecution(Object engineConnector) throws ConnectorException {
	}

	@Override
	public void finalizeForTermination(Object engineConnector) throws ConnectorException {
	}

}