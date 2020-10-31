package org.vcssl.nano.plugin.math.xvci1;

import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ExternalVariableConnectorInterface1;

public class PiXvci1Plugin implements ExternalVariableConnectorInterface1 {

	@Override
	public String getVariableName() { return "PI"; }
	@Override
	public Class<?> getDataClass() { return double.class; }
	@Override
	public Class<?> getDataUnconvertedClass() { return null; }
	@Override
	public boolean isDataClassArbitrary() { return false; }
	@Override
	public boolean isDataRankArbitrary() { return false; }
	@Override
	public boolean isConstant() { return true; }
	@Override
	public boolean isReference() { return false; }
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
		return (Double)3.141592653589793;
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
