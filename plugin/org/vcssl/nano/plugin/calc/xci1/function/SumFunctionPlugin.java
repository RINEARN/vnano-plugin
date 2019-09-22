package org.vcssl.nano.plugin.calc.xci1.function;

public class SumFunctionPlugin extends Float64VectorToScalarOperationFunctionPlugin {

	@Override
	public final String getFunctionName() {
		return "sum";
	}

	@Override
	public final void operate(double[] outputData, double[] inputData) {
		double sum = 0.0;
		for (double v: inputData) {
			sum += v;
		}
		outputData[0] = sum;
	}
}
