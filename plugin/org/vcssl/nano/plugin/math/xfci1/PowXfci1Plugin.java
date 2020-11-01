package org.vcssl.nano.plugin.math.xfci1;

import org.vcssl.connect.ExternalFunctionConnectorInterface1;

import org.vcssl.connect.ArrayDataAccessorInterface1;
import org.vcssl.connect.ConnectorException;

public class PowXfci1Plugin implements ExternalFunctionConnectorInterface1 {

	@Override
	public final String getFunctionName() {
		return "pow";
	}

	@Override
	public final Class<?>[] getParameterClasses() {
		return new Class<?>[] { double.class, double.class };
	}

	@Override
	public Class<?>[] getParameterUnconvertedClasses() {
		return new Class<?>[] { ArrayDataAccessorInterface1.class };
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
	public Class<?> getReturnUnconvertedClass(Class<?>[] parameterClasses) {
		return ArrayDataAccessorInterface1.class;
	}

	@Override
	public final boolean isDataConversionNecessary() {
		return false;
	}

	@Override
	public final Object invoke(Object[] arguments) throws ConnectorException {

		// Check types of data containers.
		if (!(arguments[0] instanceof ArrayDataAccessorInterface1)
				|| !(arguments[1] instanceof ArrayDataAccessorInterface1)
				|| !(arguments[2] instanceof ArrayDataAccessorInterface1)) {
			throw new ConnectorException("The type of the data container is not supported by this plug-in.");
		}

		// Check types of data in data containers, and cast data.
		Object inputDataObject = ( (ArrayDataAccessorInterface1<?>)arguments[1] ).getData();
		Object exponentDataObject = ( (ArrayDataAccessorInterface1<?>)arguments[2] ).getData();
		if (!(inputDataObject instanceof double[]) || !(exponentDataObject instanceof double[])) {
			throw new ConnectorException("The data type of the argument of \"sin\" function should be \"float\" or \"double\".");
		}
		@SuppressWarnings("unchecked")
		ArrayDataAccessorInterface1<double[]> inputDataContainer = (ArrayDataAccessorInterface1<double[]>)arguments[1];
		@SuppressWarnings("unchecked")
		ArrayDataAccessorInterface1<double[]> exponentDataContainer = (ArrayDataAccessorInterface1<double[]>)arguments[2];
		int inputDataSize = inputDataContainer.getSize();
		int inputDataOffset = inputDataContainer.getOffset();
		int exponentDataOffset = exponentDataContainer.getOffset();
		double[] inputData = (double[])inputDataObject;
		double[] exponentData = (double[])exponentDataObject;

		// Get or allocate output data
		@SuppressWarnings("unchecked")
		ArrayDataAccessorInterface1<double[]> outputDataContainer = (ArrayDataAccessorInterface1<double[]>)arguments[0];
		double[] outputData = outputDataContainer.getData();
		int outputDataSize = outputDataContainer.getSize();
		int outputDataOffset = outputDataContainer.getOffset();
		if (outputData == null || outputDataSize != inputDataSize) {
			outputData = new double[ inputDataSize ];
			outputDataSize = inputDataSize;
			outputDataOffset = 0;
		}

		// Operate data
		double exponentValue = exponentData[ exponentDataOffset ];
		for (int i=0; i<inputDataSize; i++) {
			outputData[ i + outputDataOffset ] = Math.pow( inputData[ i + inputDataOffset ], exponentValue );
		}

		// Store result data
		int[] outputDataLengths = new int[ inputDataContainer.getRank() ];
		System.arraycopy(inputDataContainer.getLengths(), 0, outputDataLengths, 0, inputDataContainer.getRank());
		outputDataContainer.setData(outputData, outputDataOffset, outputDataLengths);

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
