package org.vcssl.nano.plugin.system.xfci1;

import org.vcssl.connect.ArrayDataContainerInterface1;
import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ExternalFunctionConnectorInterface1;

public class LengthXfci1Plugin implements ExternalFunctionConnectorInterface1 {

	@Override
	public final String getFunctionName() {
		return "length";
	}

	@Override
	public Class<?>[] getParameterClasses() {
		return new Class<?>[] { Object.class, long.class };
	}

	@Override
	public boolean hasParameterNames() {
		return true;
	}

	@Override
	public String[] getParameterNames() {
		return new String[] { "length" };
	}

	@Override
	public boolean[] getParameterClassArbitrarinesses() {
		return new boolean[]{ true, false };
	}


	@Override
	public boolean[] getParameterRankArbitrarinesses() {
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
	public boolean isParameterCountArbitrary() {
		return false;
	}

	@Override
	public boolean hasVariadicParameters() {
		return false;
	}

	@Override
	public Class<?> getReturnClass(Class<?>[] parameterClasses) {
		return long.class;
	}

	@Override
	public boolean isDataConversionNecessary() {
		return false;
	}

	@Override
	public Object invoke(Object[] arguments) throws ConnectorException {

		// Check types of data containers.
		if (!(arguments[0] instanceof ArrayDataContainerInterface1)
				|| !(arguments[1] instanceof ArrayDataContainerInterface1)
				|| !(arguments[2] instanceof ArrayDataContainerInterface1) ) {
			throw new ConnectorException("The type of the data container is not supported by this plug-in.");
		}

		// Get rank and lengths of the array argument
		ArrayDataContainerInterface1<?> arrayArgDataContainer = (ArrayDataContainerInterface1<?>)arguments[1];
		int rank = arrayArgDataContainer.getRank();
		int[] lengths = arrayArgDataContainer.getLengths();
		if (rank == 0) {
			throw new ConnectorException("\"length\" function is not available for scalar argument.");
		}

		// Get value of the dim-index argument
		@SuppressWarnings("unchecked")
		ArrayDataContainerInterface1<long[]> dimIndexArgDataContainer = (ArrayDataContainerInterface1<long[]>)arguments[2];
		long dimIndex = dimIndexArgDataContainer.getData()[ dimIndexArgDataContainer.getOffset() ];

		// Get or allocate output data
		@SuppressWarnings("unchecked")
		ArrayDataContainerInterface1<long[]> outputContainer = (ArrayDataContainerInterface1<long[]>)arguments[0];
		Object outputDataObject = outputContainer.getData();
		long[] outputData = null;
		int outputOffset = -1;
		if (outputDataObject instanceof long[] && ((long[])outputDataObject).length == lengths.length) {
			outputData = (long[])outputDataObject;
			outputOffset = outputContainer.getOffset();
		} else {
			outputData = new long[ 1 ];
			outputOffset = 0;
		}

		// Store result data
		outputData[outputOffset] = (long)lengths[ (int)dimIndex ];

		@SuppressWarnings("unchecked")
		ArrayDataContainerInterface1<long[]> outputDataContainer = (ArrayDataContainerInterface1<long[]>)arguments[0];
		outputDataContainer.setData(outputData, outputOffset);

		return null;
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
