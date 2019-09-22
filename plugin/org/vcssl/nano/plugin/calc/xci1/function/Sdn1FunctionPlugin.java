package org.vcssl.nano.plugin.calc.xci1.function;

public class Sdn1FunctionPlugin extends Float64VectorToScalarOperationFunctionPlugin {

	@Override
	public final String getFunctionName() {
		return "sdn1";
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

		double van1 = squareDiffSum / (n-1);
		double sdn1 = Math.sqrt(van1);

		outputData[0] = sdn1;
	}
}
