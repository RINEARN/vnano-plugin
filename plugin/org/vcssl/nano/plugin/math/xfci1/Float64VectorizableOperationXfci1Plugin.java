package org.vcssl.nano.plugin.math.xfci1;

import org.vcssl.connect.ExternalFunctionConnectorInterface1;

import org.vcssl.connect.ArrayDataContainerInterface1;
import org.vcssl.connect.ConnectorException;

public class Float64VectorizableOperationXfci1Plugin implements ExternalFunctionConnectorInterface1 {

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
	public boolean[] getParameterConstantnesses() {
		return new boolean[]{ true };
	}

	@Override
	public boolean[] getParameterReferencenesses() {
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
	public Class<?> getReturnUnconvertedClass(Class<?>[] parameterClasses) {
		return ArrayDataContainerInterface1.class;
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

		// Check types of stored data.
		Object inputDataObject = ( (ArrayDataContainerInterface1<?> )arguments[1]).getData();
		Object outputDataObject = ( (ArrayDataContainerInterface1<?> )arguments[0]).getData(); // データ型変換無効化時は [0] が戻り値格納用
		if (!( inputDataObject instanceof double[] )) {
			throw new ConnectorException("The data type of the argument of this function should be \"float\" or \"double\".");
		}
		if (!( outputDataObject instanceof double[] || outputDataObject == null )) {
			throw new ConnectorException("The data type of the argument of this function should be \"float\" or \"double\".");
		}

		@SuppressWarnings("unchecked")
		ArrayDataContainerInterface1<double[]> inputDataContainer = (ArrayDataContainerInterface1<double[]>)arguments[1];
		@SuppressWarnings("unchecked")
		ArrayDataContainerInterface1<double[]> outputDataContainer = (ArrayDataContainerInterface1<double[]>)arguments[0];

		// Get or allocate input data
		double[] inputData = (double[])inputDataContainer.getData();
		int inputDataOffset = inputDataContainer.getOffset();
		int inputDataSize = inputDataContainer.getSize();

		// Get or allocate output data
		double[] outputData = outputDataContainer.getData();
		int outputDataOffset = outputDataContainer.getOffset();
		int outputDataSize = outputDataContainer.getSize();
		if (outputDataObject == null || outputDataSize != inputDataSize) {
			outputData = new double[ inputDataSize ];
			outputDataSize = inputDataSize;
			outputDataOffset = 0;
		}

		// Operate data
		this.operate(outputData, inputData, outputDataOffset, inputDataOffset, inputDataSize);

		// Store result data
		int[] outputDataLengths = new int[ inputDataContainer.getRank() ];
		System.arraycopy(inputDataContainer.getLengths(), 0, outputDataLengths, 0, inputDataContainer.getRank());
		outputDataContainer.setData(outputData, outputDataOffset, outputDataLengths);

		return null;
	}


	// Overridden on subclasses
	public void operate(double[] outputData, double[] inputData, int outputDataOffset, int inputDataOffset, int dataSize) {

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
