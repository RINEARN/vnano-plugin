package org.vcssl.nano.plugin.math;

import java.util.LinkedList;
import java.util.List;

import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ExternalFunctionConnectorInterface1;
import org.vcssl.connect.ExternalNamespaceConnectorInterface1;
import org.vcssl.connect.ExternalVariableConnectorInterface1;
import org.vcssl.nano.plugin.math.xvci1.PiXvci1Plugin;
import org.vcssl.nano.plugin.math.xfci1.AbsXfci1Plugin;
import org.vcssl.nano.plugin.math.xfci1.AcosXfci1Plugin;
import org.vcssl.nano.plugin.math.xfci1.AsinXfci1Plugin;
import org.vcssl.nano.plugin.math.xfci1.AtanXfci1Plugin;
import org.vcssl.nano.plugin.math.xfci1.CosXfci1Plugin;
import org.vcssl.nano.plugin.math.xfci1.DegXfci1Plugin;
import org.vcssl.nano.plugin.math.xfci1.ExpXfci1Plugin;
import org.vcssl.nano.plugin.math.xfci1.LnXfci1Plugin;
import org.vcssl.nano.plugin.math.xfci1.Log10Xfci1Plugin;
import org.vcssl.nano.plugin.math.xfci1.PowXfci1Plugin;
import org.vcssl.nano.plugin.math.xfci1.RadXfci1Plugin;
import org.vcssl.nano.plugin.math.xfci1.SinXfci1Plugin;
import org.vcssl.nano.plugin.math.xfci1.SqrtXfci1Plugin;
import org.vcssl.nano.plugin.math.xfci1.TanXfci1Plugin;

//Interface Specification: https://www.vcssl.org/en-us/dev/code/main-jimpl/api/org/vcssl/connect/ExternalNamespaceConnectorInterface1.html
//インターフェース仕様書:  https://www.vcssl.org/ja-jp/dev/code/main-jimpl/api/org/vcssl/connect/ExternalNamespaceConnectorInterface1.html

public class MathElementaryXnci1Plugin implements ExternalNamespaceConnectorInterface1 {

	@Override
	public String getNamespaceName() {
		return "Math";
	}

	@Override
	public ExternalFunctionConnectorInterface1[] getFunctions() {
		List<ExternalFunctionConnectorInterface1> functionList = new LinkedList<ExternalFunctionConnectorInterface1>();
		functionList.add(new RadXfci1Plugin());
		functionList.add(new DegXfci1Plugin());
		functionList.add(new SinXfci1Plugin());
		functionList.add(new CosXfci1Plugin());
		functionList.add(new TanXfci1Plugin());
		functionList.add(new AsinXfci1Plugin());
		functionList.add(new AcosXfci1Plugin());
		functionList.add(new AtanXfci1Plugin());
		functionList.add(new SqrtXfci1Plugin());
		functionList.add(new LnXfci1Plugin());
		functionList.add(new Log10Xfci1Plugin());
		functionList.add(new PowXfci1Plugin());
		functionList.add(new ExpXfci1Plugin());
		functionList.add(new AbsXfci1Plugin());
		return functionList.toArray(new ExternalFunctionConnectorInterface1[0]);
	}

	@Override
	public ExternalVariableConnectorInterface1[] getVariables() {
		List<ExternalVariableConnectorInterface1> variableList = new LinkedList<ExternalVariableConnectorInterface1>();
		variableList.add(new PiXvci1Plugin());
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