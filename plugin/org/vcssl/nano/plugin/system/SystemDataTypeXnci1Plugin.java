/*
 * Author:  RINEARN (Fumihiro Matsui), 2020
 * License: CC0
 */

package org.vcssl.nano.plugin.system;

import java.util.LinkedList;
import java.util.List;

import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ExternalFunctionConnectorInterface1;
import org.vcssl.connect.ExternalNamespaceConnectorInterface1;
import org.vcssl.connect.ExternalVariableConnectorInterface1;
import org.vcssl.nano.plugin.system.xfci1.InfXfci1Plugin;
import org.vcssl.nano.plugin.system.xfci1.LengthXfci1Plugin;
import org.vcssl.nano.plugin.system.xfci1.NanXfci1Plugin;
import org.vcssl.nano.plugin.system.xvci1.FloatMaxXvci1Plugin;
import org.vcssl.nano.plugin.system.xvci1.FloatMinAbsDenormalXvci1Plugin;
import org.vcssl.nano.plugin.system.xvci1.FloatMinAbsNormalXvci1Plugin;
import org.vcssl.nano.plugin.system.xvci1.InfXvci1Plugin;
import org.vcssl.nano.plugin.system.xvci1.IntMaxXvci1Plugin;
import org.vcssl.nano.plugin.system.xvci1.IntMinXvci1Plugin;
import org.vcssl.nano.plugin.system.xvci1.NanXvci1Plugin;

//Interface Specification: https://www.vcssl.org/en-us/dev/code/main-jimpl/api/org/vcssl/connect/ExternalNamespaceConnectorInterface1.html
//インターフェース仕様書:  https://www.vcssl.org/ja-jp/dev/code/main-jimpl/api/org/vcssl/connect/ExternalNamespaceConnectorInterface1.html

public class SystemDataTypeXnci1Plugin implements ExternalNamespaceConnectorInterface1 {

	@Override
	public String getNamespaceName() {
		return "System";
	}

	@Override
	public ExternalFunctionConnectorInterface1[] getFunctions() {
		List<ExternalFunctionConnectorInterface1> functionList = new LinkedList<ExternalFunctionConnectorInterface1>();
		functionList.add(new NanXfci1Plugin());
		functionList.add(new InfXfci1Plugin());
		functionList.add(new LengthXfci1Plugin());
		return functionList.toArray(new ExternalFunctionConnectorInterface1[0]);
	}

	@Override
	public ExternalVariableConnectorInterface1[] getVariables() {
		List<ExternalVariableConnectorInterface1> variableList = new LinkedList<ExternalVariableConnectorInterface1>();
		variableList.add(new NanXvci1Plugin());
		variableList.add(new InfXvci1Plugin());
		variableList.add(new IntMaxXvci1Plugin());
		variableList.add(new IntMinXvci1Plugin());
		variableList.add(new FloatMaxXvci1Plugin());
		variableList.add(new FloatMinAbsNormalXvci1Plugin());
		variableList.add(new FloatMinAbsDenormalXvci1Plugin());
		return variableList.toArray(new ExternalVariableConnectorInterface1[0]);
	}

	@Override
	public void preInitializeForConnection(Object engineConnector) throws ConnectorException { }

	@Override
	public void postInitializeForConnection(Object engineConnector) throws ConnectorException { }

	@Override
	public void preInitializeForExecution(Object engineConnector) throws ConnectorException { }

	@Override
	public void postInitializeForExecution(Object engineConnector) throws ConnectorException { }

	@Override
	public void preFinalizeForTermination(Object engineConnector) throws ConnectorException { }

	@Override
	public void postFinalizeForTermination(Object engineConnector) throws ConnectorException { }

	@Override
	public void preFinalizeForDisconnection(Object engineConnector) throws ConnectorException { }

	@Override
	public void postFinalizeForDisconnection(Object engineConnector) throws ConnectorException { }
}
