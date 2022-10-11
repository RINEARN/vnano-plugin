/*
 * Author:  RINEARN (Fumihiro Matsui), 2022
 * License: CC0
 * Interface Specification:
 *     https://www.vcssl.org/en-us/doc/connect/ExternalNamespaceConnectorInterface1_SPEC_ENGLISH
 */

package org.vcssl.nano.plugin.system.xnci1;

import java.util.LinkedList;
import java.util.List;

import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.EngineConnectorInterface1;
import org.vcssl.connect.ExternalFunctionConnectorInterface1;
import org.vcssl.connect.ExternalNamespaceConnectorInterface1;
import org.vcssl.connect.ExternalStructConnectorInterface1;
import org.vcssl.connect.ExternalVariableConnectorInterface1;
import org.vcssl.nano.plugin.system.xvci1.WriteXvci1Plugin;
import org.vcssl.nano.plugin.system.xvci1.ReadXvci1Plugin;
import org.vcssl.nano.plugin.system.xvci1.AppendXvci1Plugin;
import org.vcssl.nano.plugin.system.xvci1.WriteTsvXvci1Plugin;
import org.vcssl.nano.plugin.system.xvci1.ReadTsvXvci1Plugin;
import org.vcssl.nano.plugin.system.xvci1.AppendTsvXvci1Plugin;
import org.vcssl.nano.plugin.system.xvci1.WriteCsvXvci1Plugin;


public class SystemFileIOXnci1Plugin implements ExternalNamespaceConnectorInterface1 {

	@Override
	public String getNamespaceName() {
		return "System";
	}

	@Override
	public boolean isMandatoryToAccessMembers() {
		return false;
	}

	@Override
	public ExternalFunctionConnectorInterface1[] getFunctions() {
		List<ExternalFunctionConnectorInterface1> functionList = new LinkedList<ExternalFunctionConnectorInterface1>();
		// functionList.add( ... );
		return functionList.toArray(new ExternalFunctionConnectorInterface1[0]);
	}

	@Override
	public ExternalVariableConnectorInterface1[] getVariables() {
		List<ExternalVariableConnectorInterface1> variableList = new LinkedList<ExternalVariableConnectorInterface1>();
		variableList.add(new WriteXvci1Plugin());
		variableList.add(new ReadXvci1Plugin());
		variableList.add(new AppendXvci1Plugin());
		variableList.add(new WriteTsvXvci1Plugin());
		variableList.add(new ReadTsvXvci1Plugin());
		variableList.add(new AppendTsvXvci1Plugin());
		variableList.add(new WriteCsvXvci1Plugin());
		return variableList.toArray(new ExternalVariableConnectorInterface1[0]);
	}

	@Override
	public ExternalStructConnectorInterface1[] getStructs() {
		return new ExternalStructConnectorInterface1[0];
	}

	@Override
	public Class<?> getEngineConnectorClass() {
		return EngineConnectorInterface1.class;
	}

	@Override
	public void preInitializeForConnection(Object engineConnector) throws ConnectorException {
	}

	@Override
	public void postInitializeForConnection(Object engineConnector) throws ConnectorException {
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

	@Override
	public void preFinalizeForDisconnection(Object engineConnector) throws ConnectorException {
	}

	@Override
	public void postFinalizeForDisconnection(Object engineConnector) throws ConnectorException {
	}
}
