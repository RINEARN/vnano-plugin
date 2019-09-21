package org.vcssl.nano.plugin.calc.xci1.function;

public class VanFunctionPlugin extends Float64VectorToScalarOperationFunctionPlugin {

	@Override
	public final String getFunctionName() {
		return "van";
	}

	@Override
	public final void operate(double[] outputData, double[] inputData) {
		int n = inputData.length;

		double sum = 0.0;
		for (double v: inputData) {
			sum += v;
		}

		double mean = sum / n;

		double squareDiffSum = 0.0;
		for (double v: inputData) {
			double diff = v - mean;
			squareDiffSum += diff * diff;
		}

		double van = squareDiffSum / n;

		outputData[0] = van;
	}
}
