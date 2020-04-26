package org.vcssl.nano.plugin.calc.xci1.function;

import org.vcssl.connect.ExternalFunctionConnectorInterface1;

import org.vcssl.connect.ArrayDataContainerInterface1;
import org.vcssl.connect.ConnectorException;

public class PowFunctionPlugin implements ExternalFunctionConnectorInterface1 {

	@Override
	public final String getFunctionName() {
		return "pow";
	}

	@Override
	public final Class<?>[] getParameterClasses() {
		return new Class<?>[] { double.class, double.class };
	}

	@Override
	public final boolean hasParameterNames() {
		return true;
	}

	@Override
	public final String[] getParameterNames() {
		return new String[] { "arg", "exponent" };
	}

	@Override
	public final boolean[] getParameterClassArbitrarinesses() {
		return new boolean[]{ false, false };
	}

	@Override
	public final boolean[] getParameterRankArbitrarinesses() {
		return new boolean[]{ true, false };
	}

	@Override
	public boolean[] getParameterConstantnesses() {
		return new boolean[]{ true, true };
	}

	@Override
	public boolean[] getParameterReferencenesses() {
		return new boolean[]{ true, true };
	}

	@Override
	public final boolean isParameterCountArbitrary() {
		return false;
	}

	@Override
	public final boolean hasVariadicParameters() {
		return false;
	}

	@Override
	public final Class<?> getReturnClass(Class<?>[] parameterClasses) {
		return parameterClasses[0];
	}

	@Override
	public final boolean isDataConversionNecessary() {
		return false;
	}

	@Override
	public final Object invoke(Object[] arguments) throws ConnectorException {

		// Check types of data containers.
		if (!(arguments[0] instanceof ArrayDataContainerInterface1)
				|| !(arguments[1] instanceof ArrayDataContainerInterface1)
				|| !(arguments[2] instanceof ArrayDataContainerInterface1)) {
			throw new ConnectorException("The type of the data container is not supported by this plug-in.");
		}

		// Check types of data in data containers, and cast data.
		Object inputDataObject = ( (ArrayDataContainerInterface1<?>)arguments[1] ).getData();
		Object exponentDataObject = ( (ArrayDataContainerInterface1<?>)arguments[2] ).getData();
		if (!(inputDataObject instanceof double[]) || !(exponentDataObject instanceof double[])) {
			throw new ConnectorException("The data type of the argument of \"sin\" function should be \"float\" or \"double\".");
		}
		@SuppressWarnings("unchecked")
		ArrayDataContainerInterface1<double[]> inputDataContainer = (ArrayDataContainerInterface1<double[]>)arguments[1];
		double[] inputData = (double[])inputDataObject;
		int dataLength = inputData.length;

		// Get or allocate output data
		Object outputDataObject = ( (ArrayDataContainerInterface1<?>)arguments[0] ).getData();
		double[] outputData = null;
		if (outputDataObject instanceof double[] && ((double[])outputDataObject).length == dataLength) {
			outputData = (double[])outputDataObject;
		} else {
			outputData = new double[ dataLength ];
		}

		// Operate data
		double exponentValue = ((double[])exponentDataObject)[0];
		for (int i=0; i<dataLength; i++) {
			outputData[i] = Math.pow(inputData[i], exponentValue);
		}

		// Store result data
		@SuppressWarnings("unchecked")
		ArrayDataContainerInterface1<double[]> outputDataContainer = (ArrayDataContainerInterface1<double[]>)arguments[0];
		outputDataContainer.setData(outputData, inputDataContainer.getLengths());

		return null;
	}


	// Overridden on subclasses
	public final void operate(double[] outputData, double[] inputData, int dataLength) {

	}

	@Override
	public final void initializeForConnection(Object engineConnector) throws ConnectorException { }
	@Override
	public final void initializeForExecution(Object engineConnector) throws ConnectorException { }
	@Override
	public final void finalizeForDisconnection(Object engineConnector) throws ConnectorException { }
	@Override
	public final void finalizeForTermination(Object engineConnector) throws ConnectorException { }
}
