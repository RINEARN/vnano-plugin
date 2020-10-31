package org.vcssl.nano.plugin.math.xfci1;

import org.vcssl.connect.ExternalFunctionConnectorInterface1;

import org.vcssl.connect.ArrayDataContainerInterface1;
import org.vcssl.connect.ConnectorException;

public class Float64VectorToScalarOperationXfci1Plugin implements ExternalFunctionConnectorInterface1 {

	@Override
	public String getFunctionName() {
		return "this_function_name_should_be_overridden";
	}

	@Override
	public Class<?>[] getParameterClasses() {
		return new Class<?>[] { double.class };
	}

	@Override
	public Class<?>[] getParameterUnconvertedClasses() {
		return new Class<?>[] { ArrayDataContainerInterface1.class };
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
	public Class<?> getReturnUnconvertedClass(Class<?>[] parameterClasses) {
		return ArrayDataContainerInterface1.class;
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
	public boolean[] getParameterConstantnesses() {
		return new boolean[]{ true };
	}

	@Override
	public boolean[] getParameterReferencenesses() {
		return new boolean[]{ true };
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
		return false;
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
		@SuppressWarnings("unchecked")
		ArrayDataContainerInterface1<double[]> outputDataContainer = (ArrayDataContainerInterface1<double[]>)arguments[0];
		int outputDataOffset = outputDataContainer.getOffset();
		double[] outputData = outputDataContainer.getData();
		if (outputData == null || outputDataContainer.getSize() != 1) {
			outputData = new double[ 1 ];
			outputDataOffset = 0;
		}

		// Check types of data in data containers, and cast data.
		int inputArgN = arguments.length - 1;
		double[] inputData = new double[inputArgN];
		for (int inputArgIndex=0; inputArgIndex<inputArgN; inputArgIndex++) {
			int argIndex = inputArgIndex + 1;
			ArrayDataContainerInterface1<?> inputDataContainer = (ArrayDataContainerInterface1<?>)arguments[argIndex];
			Object inputDataObject = inputDataContainer.getData();
			int inputDataOffset = inputDataContainer.getOffset();
			if (!(inputDataObject instanceof double[])) {
				throw new ConnectorException("The data type of the argument of this function should be \"float\" or \"double\".");
			}
			inputData[inputArgIndex] = ((double[])inputDataObject)[ inputDataOffset ];
		}

		// Operate data
		this.operate(outputData, inputData, outputDataOffset);

		// Store result data
		outputDataContainer.setData(outputData, outputDataOffset, ArrayDataContainerInterface1.SCALAR_LENGTHS);

		return null;
	}

	// Overridden on subclasses
	public void operate(double[] outputData, double[] inputData, int outputDataOffset) {

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
