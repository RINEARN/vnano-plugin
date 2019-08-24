package org.vcssl.nano.plugin;

import org.vcssl.connect.ConnectorPermission;

import java.util.LinkedList;
import java.util.List;

import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ExternalFunctionConnector1;
import org.vcssl.connect.ExternalNamespaceConnector1;
import org.vcssl.connect.ExternalVariableConnector1;

public class SimpleCalculatorPlugin implements ExternalNamespaceConnector1 {

	@Override
	public String getNamespaceName() {
		return "SimpleCalculatorPlugin";
	}

	@Override
	public String[] getNecessaryPermissions() {
		return new String[]{ ConnectorPermission.NONE };
	}

	@Override
	public String[] getUnnecessaryPermissions() {
		return new String[]{ ConnectorPermission.ALL };
	}

	@Override
	public ExternalFunctionConnector1[] getFunctions() {
		List<ExternalFunctionConnector1> functionList = new LinkedList<ExternalFunctionConnector1>();
		return functionList.toArray(new ExternalFunctionConnector1[0]);
	}

	@Override
	public ExternalVariableConnector1[] getVariables() {
		List<ExternalVariableConnector1> variableList = new LinkedList<ExternalVariableConnector1>();
		return variableList.toArray(new ExternalVariableConnector1[0]);
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