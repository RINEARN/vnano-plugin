package org.vcssl.nano.plugin.calc.xci1.function;

public class AbsFunctionPlugin extends Float64VectorizableOperationFunctionPlugin {

	@Override
	public final String getFunctionName() {
		return "abs";
	}

	@Override
	public final void operate(double[] outputData, double[] inputData, int dataLength) {
		for (int i=0; i<dataLength; i++) {
			outputData[i] = Math.abs(inputData[i]);
		}
	}
}
