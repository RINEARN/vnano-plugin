package org.vcssl.nano.plugin.calc.xci1.function;

public class MeanFunctionPlugin extends Float64VectorToScalarOperationFunctionPlugin {

	@Override
	public final String getFunctionName() {
		return "mean";
	}

	@Override
	public final void operate(double[] outputData, double[] inputData) {

		int n = inputData.length;

		double sum = 0.0;
		for (double v: inputData) {
			sum += v;
		}

		double mean = sum / n;

		outputData[0] = mean;
	}
}
