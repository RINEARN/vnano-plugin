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
		int inputDataOffset = inputDataContainer.getOffset();
		int outputDataOffset = outputDataContainer.getOffset();
		int inputDataSize = inputDataContainer.getSize();
		int outputDataSize = outputDataContainer.getSize();

		// Get or allocate data
		double[] inputData = (double[])inputDataContainer.getData();
		double[] outputData = null;
		if (outputDataContainer.getData() != null && outputDataSize == inputDataSize) {
			outputData = (double[])outputDataObject;
		} else {
			outputDataSize = inputDataSize;
			outputData = new double[ outputDataSize ];
		}

		// Operate data
		this.operate(outputData, inputData, outputDataOffset, inputDataOffset, inputDataSize);

		// Store result data
		if (inputDataContainer.getRank() == 0) { // if input data is scalar
			// Store data as a scalar
			outputDataContainer.setData(outputData, outputDataOffset);
		} else {
			// Store data as an array
			outputDataContainer.setData(outputData, inputDataContainer.getLengths());
		}

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
