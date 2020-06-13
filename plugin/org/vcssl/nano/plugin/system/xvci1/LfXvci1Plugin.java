package org.vcssl.nano.plugin.system.xvci1;

import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ExternalVariableConnectorInterface1;

public class LfXvci1Plugin implements ExternalVariableConnectorInterface1 {

	@Override
	public String getVariableName() { return "LF"; }
	@Override
	public Class<?> getDataClass() { return String.class; }
	@Override
	public boolean isConstant() { return true; }
	@Override
	public void initializeForConnection(Object engineConnector) { }
	@Override
	public void finalizeForDisconnection(Object engineConnector) { }
	@Override
	public void initializeForExecution(Object engineConnector) { }
	@Override
	public void finalizeForTermination(Object engineConnector) { }

	@Override
	public boolean isDataConversionNecessary() { return true; }

	@Override
	public Object getData() throws ConnectorException {
		return "\n";
	}

	@Override
	public void getData(Object dataContainer) throws ConnectorException {
		// This method is for the case of the data conversion is disabled.
	}

	@Override
	public void setData(Object data) throws ConnectorException {
		throw new ConnectorException("\"" + getVariableName() + "\" is a constant, so this value shoud not be changed.");
	}
}
