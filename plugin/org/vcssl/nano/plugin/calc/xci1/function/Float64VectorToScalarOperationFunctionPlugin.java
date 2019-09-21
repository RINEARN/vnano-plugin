package org.vcssl.nano.plugin.calc.xci1.function;

import org.vcssl.connect.ExternalFunctionConnectorInterface1;

import org.vcssl.connect.ArrayDataContainerInterface1;
import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ConnectorPermissionName;

public class Float64VectorToScalarOperationFunctionPlugin implements ExternalFunctionConnectorInterface1 {

	@Override
	public String getFunctionName() {
		return "this_function_name_should_be_overridden";
	}

	@Override
	public Class<?>[] getParameterClasses() {
		return new Class<?>[] { double.class };
	}

	@Override
	public boolean hasParameterNames() {
		return true;
	}

	@Override
	public String[] getParameterNames() {
		return new String[] { "args" };
	}

	@Override
	public Class<?> getReturnClass(Class<?>[] parameterClasses) {
		return double.class;
	}

	@Override
	public boolean[] getParameterClassArbitrarinesses() {
		return new boolean[]{ false };
	}


	@Override
	public boolean[] getParameterRankArbitrarinesses() {
		return new boolean[]{ false };
	}

	@Override
	public boolean isParameterCountArbitrary() {
		return true;
	}

	@Override
	public boolean hasVariadicParameters() {
		return false;
	}

	@Override
	public boolean isDataConversionNecessary() {
		return true;
	}

	@Override
	public Object invoke(Object[] arguments) throws ConnectorException {

		// Check types of data containers.
		for (Object arg: arguments) {
			if (!(arg instanceof ArrayDataContainerInterface1)) {
				throw new ConnectorException("The type of the data container is not supported by this plug-in.");
			}
		}

		// Get or allocate output data
		Object outputDataObject = ( (ArrayDataContainerInterface1<?>)arguments[0] ).getData();
		double[] outputData = null;
		if (outputDataObject instanceof double[] && ((double[])outputDataObject).length == 1) {
			outputData = (double[])outputDataObject;
		} else {
			outputData = new double[ 1 ];
		}

		// Check types of data in data containers, and cast data.
		int inputArgN = arguments.length - 1;
		double[] inputData = new double[inputArgN];
		for (int inputArgIndex=0; inputArgIndex<inputArgN; inputArgIndex++) {
			int argIndex = inputArgIndex + 1;
			Object inputDataObject = ( (ArrayDataContainerInterface1<?>)arguments[argIndex] ).getData();
			if (!(inputDataObject instanceof double[])) {
				throw new ConnectorException("The data type of the argument of \"sin\" function should be \"float\" or \"double\".");
			}
			inputData[inputArgIndex] = ((double[])inputDataObject)[0]; // each argument stores scalar value at [0]
		}

		// Operate data
		this.operate(outputData, inputData);

		// Store result data
		@SuppressWarnings("unchecked")
		ArrayDataContainerInterface1<double[]> outputDataContainer = (ArrayDataContainerInterface1<double[]>)arguments[0];
		outputDataContainer.setData(outputData);

		return null;
	}

	// Overridden on subclasses
	public void operate(double[] outputData, double[] inputData) {
	}


	@Override
	public String[] getNecessaryPermissionNames() { return new String[] { ConnectorPermissionName.NONE }; }
	@Override
	public String[] getUnnecessaryPermissionNames() { return new String[] { ConnectorPermissionName.ALL }; }
	@Override
	public void initializeForConnection(Object engineConnector) throws ConnectorException { }
	@Override
	public void initializeForExecution(Object engineConnector) throws ConnectorException { }
	@Override
	public void finalizeForDisconnection(Object engineConnector) throws ConnectorException { }
	@Override
	public void finalizeForTermination(Object engineConnector) throws ConnectorException { }
}
