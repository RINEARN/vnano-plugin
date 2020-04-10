package org.vcssl.nano.plugin.calc.xci1.function;

import org.vcssl.connect.ExternalFunctionConnectorInterface1;

import org.vcssl.connect.ArrayDataContainerInterface1;
import org.vcssl.connect.ConnectorException;

public class Float64VectorizableOperationFunctionPlugin implements ExternalFunctionConnectorInterface1 {

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
		return new String[] { "arg" };
	}

	@Override
	public boolean[] getParameterClassArbitrarinesses() {
		return new boolean[]{ false };
	}


	@Override
	public boolean[] getParameterRankArbitrarinesses() {
		return new boolean[]{ true };
	}

	@Override
	public boolean isParameterCountArbitrary() {
		return false;
	}

	@Override
	public boolean hasVariadicParameters() {
		return false;
	}

	@Override
	public Class<?> getReturnClass(Class<?>[] parameterClasses) {
		return parameterClasses[0];
	}

	@Override
	public boolean isDataConversionNecessary() {
		return false;
	}

	@Override
	public Object invoke(Object[] arguments) throws ConnectorException {

		// Check types of data containers.
		if (!(arguments[0] instanceof ArrayDataContainerInterface1) || !(arguments[1] instanceof ArrayDataContainerInterface1)) {
			throw new ConnectorException("The type of the data container is not supported by this plug-in.");
		}

		// Check types of data in data containers, and cast data.
		Object inputDataObject = ( (ArrayDataContainerInterface1<?>)arguments[1] ).getData();
		if (!(inputDataObject instanceof double[])) {
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
		this.operate(outputData, inputData, dataLength);

		// Store result data
		@SuppressWarnings("unchecked")
		ArrayDataContainerInterface1<double[]> outputDataContainer = (ArrayDataContainerInterface1<double[]>)arguments[0];
		outputDataContainer.setData(outputData, inputDataContainer.getLengths());

		return null;
	}


	// Overridden on subclasses
	public void operate(double[] outputData, double[] inputData, int dataLength) {

	}


	@Override
	public void initializeForConnection(Object engineConnector) throws ConnectorException { }
	@Override
	public void initializeForExecution(Object engineConnector) throws ConnectorException { }
	@Override
	public void finalizeForDisconnection(Object engineConnector) throws ConnectorException { }
	@Override
	public void finalizeForTermination(Object engineConnector) throws ConnectorException { }
}
