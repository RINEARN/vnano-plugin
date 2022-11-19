package org.vcssl.nano.plugin.math.xfci1;

public class VectorMeanXfci1Plugin extends Float64VectorToScalarOperationXfci1Plugin {

	@Override
	public final String getFunctionName() {
		return "mean";
	}

	@Override
	public final double operate(double[] inputData, int inputDataOffset, int inputDataSize) {
		double sum = 0.0;
		int end = inputDataOffset + inputDataSize - 1;
		for (int i=inputDataOffset; i<=end; i++) {
			sum += inputData[i];
		}
		double mean = sum / inputDataSize;
		return mean;
	}
}
