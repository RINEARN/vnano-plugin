package org.vcssl.nano.plugin.system;

import java.util.LinkedList;
import java.util.List;

import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ExternalFunctionConnectorInterface1;
import org.vcssl.connect.ExternalNamespaceConnectorInterface1;
import org.vcssl.connect.ExternalVariableConnectorInterface1;
import org.vcssl.nano.plugin.system.xfci1.PrintXfci1Plugin;

public class SystemTerminalIOXnci1Plugin implements ExternalNamespaceConnectorInterface1 {

	@Override
	public String getNamespaceName() {
		return "SystemTerminalIOXnci1Plugin";
	}

	@Override
	public ExternalFunctionConnectorInterface1[] getFunctions() {
		List<ExternalFunctionConnectorInterface1> functionList = new LinkedList<ExternalFunctionConnectorInterface1>();
		functionList.add(new PrintXfci1Plugin());
		return functionList.toArray(new ExternalFunctionConnectorInterface1[0]);
	}

	@Override
	public ExternalVariableConnectorInterface1[] getVariables() {
		List<ExternalVariableConnectorInterface1> variableList = new LinkedList<ExternalVariableConnectorInterface1>();
		// variableList.add(...);
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
